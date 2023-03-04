package pokemon;

import lombok.Getter;
import move.*;
import pokemonStatus.*;
import pokemonStatus.impl.*;
import Enum.*;
import statusAilment.Ailment;
import statusAilment.StatusAilment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static bussinessLogic.ConsoleOutManager.showMessageParChar;
import static statusAilment.StatusAilmentImpl.changeAilment;
import static statusAilment.StatusAilmentImpl.initializeAilment;

@Getter
public class PokemonInfoImpl implements PokemonInfo {
    private final BasePrm basePrm;
    private final IndividualValue individualValue;
    private final EffortValue effortValue;
    private final Level level;
    private final Gender gender;
    private final Nature nature;
    private final List<Move> haveMove;
    private final Experience experience;
    private final CurrentHitPoint currentHitPoint;
    private final StatusRank statusRank;
    private final StatusAilment statusAilment;

    public int getRealValHitPoint() {
        return ((this.basePrm.getHitPoint() * 2 + this.individualValue.hitPoint() + (this.effortValue.hitPoint() / 4)) * this.level.value() / 100) + 10 + this.level.value();
    }
    public int getRealValAttack() {
        return (int)((((this.basePrm.getAttack() * 2 + this.individualValue.attack() + (this.effortValue.attack() / 4)) * this.level.value() / 100) + 5) * this.nature.getAttackRate());
    }
    public int getRealValBlock() {
        return (int)((((this.basePrm.getBlock() * 2 + this.individualValue.block() + (this.effortValue.block() / 4)) * this.level.value() / 100) + 5) * this.nature.getBlockRate());
    }
    public int getRealValContact() {
        return (int)((((this.basePrm.getContact() * 2 + this.individualValue.contact() + (this.effortValue.contact() / 4)) * this.level.value() / 100) + 5) * this.nature.getContactRate());
    }
    public int getRealValDefense() {
        return (int)((((this.basePrm.getDefense() * 2 + this.individualValue.defense() + (this.effortValue.defense() / 4)) * this.level.value() / 100) + 5) * this.nature.getDefenceRate());
    }
    public int getRealValSpeed() {
        return (int)((((this.basePrm.getSpeed() * 2 + this.individualValue.speed() + (this.effortValue.speed() / 4)) * this.level.value() / 100) + 5) * this.nature.getSpeedRate());
    }

    @Override
    public int giveExp() {
        return this.getLevel().value() * this.getBasePrm().getBasicExperience() / 7;
    }

    @Override
    public PokemonInfo addExp(int exp) throws InterruptedException {
        PokemonInfo result = this.withExperience(exp);
        showMessageParChar(result.getBasePrm().getName() + "は" + exp + "の経験値を獲得!");
        while(result.getExperience().isLevelUp(result)) {
            result = result.withLevel(1);
            showMessageParChar(result.getBasePrm().getName() + "はLv." + result.getLevel().value() + "にレベルアップした!");
        }
        return result;
    }

    @Override
    public PokemonInfo recoveryHitPoint(int value) throws InterruptedException {
        PokemonInfo result = this.withCurrentHitPoint(this.getCurrentHitPoint().recovery(this, new CurrentHitPointImpl(value)));
        System.out.println(result.getBasePrm().getName() + "は体力を" + value + "回復!  HP" + result.getCurrentHitPoint().value() + "/" + result.getRealValHitPoint());
        System.out.println();
        return result.withStatusAilment(changeAilment(result, Ailment.FINE));
    }

    @Override
    public PokemonInfo damagePoke(int value) throws InterruptedException {
        PokemonInfo result = this.withCurrentHitPoint(this.getCurrentHitPoint().damage(new CurrentHitPointImpl(value)));
        showMessageParChar(result.getBasePrm().getName() + "は" + value + "のダメージ!");
        if (result.getCurrentHitPoint().isDead()) { return result.withStatusAilment(changeAilment(result, Ailment.FAINTING)); }
        return result;
    }

    @Override
    public PokemonInfo recoveryPowerPoint(Move move, int value) {
        Move targetMoves = null;
        List<Move> haveMoves = this.getHaveMove();
        for(Move haveMove : haveMoves) {
            if(move.getClass() == haveMove.getClass()) {
                targetMoves = haveMove;
            }
        }
        CurrentPowerPoint recoveredPP = targetMoves.getCurrentPowerPoint().recovery(targetMoves, new CurrentPowerPointImpl(value));
        Move recoveredPPMove = targetMoves.withCurrentPowerPoint(targetMoves, recoveredPP);
        PokemonInfo result = this.withMove(recoveredPPMove);

        return result;
    }

    @Override
    public PokemonInfo recoveryAll() throws InterruptedException {
        PokemonInfo result = this.recoveryHitPoint(999);

        for(Move move : result.getHaveMove()) {
            result = result.recoveryPowerPoint(move, 99);
        }
        return result;
    }

    @Override
    public PokemonInfo decrementPowerPoint(Move usedMove) {
        CurrentPowerPoint decrementedPowerPoint = usedMove.getCurrentPowerPoint().decrement(new CurrentPowerPointImpl(1));
        Move decrementedPPMove = usedMove.withCurrentPowerPoint(usedMove, decrementedPowerPoint);
        PokemonInfo pokemonInfo = this.withMove(decrementedPPMove);
        return pokemonInfo;
    }

    public PokemonInfoImpl(BasePrm basePokemonInfo) {
        this.basePrm = basePokemonInfo;
        this.gender = Gender.decide();
        this.nature = Nature.decide();
        this.individualValue = new IndividualValueImpl();
        this.effortValue = new EffortValueImpl();
        this.level = new LevelImpl(5);
        this.haveMove = basePokemonInfo.getInitialMove();
        this.experience = new ExperienceImpl(135); // TODO 固定化したくない
        this.currentHitPoint = new CurrentHitPointImpl(getRealValHitPoint());
        this.statusRank = new StatusRankImpl();
        this.statusAilment = initializeAilment();
    }

    // 指定パラメータポケモンインスタンスを作成する。
    public PokemonInfoImpl(
            BasePrm basePokemonInfo,
            Gender gender,
            Nature nature,
            IndividualValue individualValue,
            EffortValue effortValue,
            Level level,
            List<Move> haveMove
    ) {
        this.basePrm = basePokemonInfo;
        this.gender = gender;
        this.nature = nature;
        this.individualValue = new IndividualValueImpl(individualValue.hitPoint(), individualValue.attack(), individualValue.block(), individualValue.contact(), individualValue.defense(), individualValue.speed());
        this.effortValue = new EffortValueImpl(effortValue.hitPoint(), effortValue.attack(), effortValue.block(), effortValue.contact(), effortValue.defense(), effortValue.speed());
        this.level = new LevelImpl(level.value());
        this.haveMove = haveMove;
        this.experience = new ExperienceImpl(0);
        this.currentHitPoint = new CurrentHitPointImpl(getRealValHitPoint());
        this.statusRank = new StatusRankImpl();
        this.statusAilment = initializeAilment();
    }

    private PokemonInfoImpl(
            BasePrm basePokemonInfo,
            Gender gender,
            Nature nature,
            IndividualValue individualValue,
            EffortValue effortValue,
            Level level,
            Experience experience,
            List<Move> haveMove,
            CurrentHitPoint currentHitPoint,
            StatusRank statusRankImpl,
            StatusAilment statusAilment
    ) {
        this.basePrm = basePokemonInfo;
        this.gender = gender;
        this.nature = nature;
        this.individualValue = new IndividualValueImpl(individualValue.hitPoint(), individualValue.attack(), individualValue.block(), individualValue.contact(), individualValue.defense(), individualValue.speed());
        this.effortValue = new EffortValueImpl(effortValue.hitPoint(), effortValue.attack(), effortValue.block(), effortValue.contact(), effortValue.defense(), effortValue.speed());
        this.level = new LevelImpl(level.value());
        this.haveMove = haveMove;
        this.experience = new ExperienceImpl(experience.totalExperience());
        this.currentHitPoint = new CurrentHitPointImpl(currentHitPoint.value());
        this.statusRank = new StatusRankImpl(statusRankImpl.getAttack(), statusRankImpl.getBlock(), statusRankImpl.getContact(), statusRankImpl.getDefense(), statusRankImpl.getSpeed(), statusRankImpl.getHitRate(), statusRankImpl.getAvoidRate());
        this.statusAilment = statusAilment;
    }

    @Override
    public PokemonInfo withCurrentHitPoint(CurrentHitPoint currentHitPoint) {
        return new PokemonInfoImpl(this.basePrm, this.gender, this.nature, this.individualValue, this.effortValue, this.level, this.experience,this.haveMove, currentHitPoint, this.statusRank, this.statusAilment);
    }
    @Override
    public PokemonInfo withExperience(int addingExperience) {
        return new PokemonInfoImpl(this.basePrm, this.gender, this.nature, this.individualValue, this.effortValue, this.level, this.experience.add(addingExperience),this.haveMove, this.currentHitPoint, this.statusRank, this.statusAilment);
    }
    @Override
    public PokemonInfo withLevel(int addLevel) {
        return new PokemonInfoImpl(this.basePrm, this.gender, this.nature, this.individualValue, this.effortValue, this.level.add(addLevel), this.experience,this.haveMove, this.currentHitPoint, this.statusRank, this.statusAilment);
    }
    @Override
    public PokemonInfo withAddedStatusRank(final int attack, final int block, final int contact, final int defense, final int speed, final int hitRate, final int avoidRate) {
        return new PokemonInfoImpl(this.basePrm, this.gender, this.nature, this.individualValue, this.effortValue, this.level, this.experience,this.haveMove, this.currentHitPoint, this.statusRank.add(attack, block, contact, defense, speed, hitRate, avoidRate), this.statusAilment);
    }
    @Override
    public PokemonInfo withResetStatusRank() {
        return new PokemonInfoImpl(this.basePrm, this.gender, this.nature, this.individualValue, this.effortValue, this.level, this.experience, this.haveMove, this.currentHitPoint, this.statusRank.reset(), this.statusAilment);
    }
    @Override
    public PokemonInfo withMove(Move move) {
        List<Move> newMoves = new ArrayList<>(4);
        for(Move haveMove : this.haveMove) {
            if(Objects.equals(move.baseMPrm().getName(), haveMove.baseMPrm().getName())) {
                newMoves.add(move);
            } else {
                newMoves.add(haveMove);
            }
        }
        return new PokemonInfoImpl(this.basePrm, this.gender, this.nature, this.individualValue, this.effortValue, this.level, this.experience, newMoves, this.currentHitPoint, this.statusRank, this.statusAilment);
    }
    @Override
    public PokemonInfo withStatusAilment(StatusAilment statusAilment) {
        return new PokemonInfoImpl(this.basePrm, this.gender, this.nature, this.individualValue, this.effortValue, this.level, this.experience, this.haveMove, this.currentHitPoint, this.statusRank, statusAilment);
    }
}
