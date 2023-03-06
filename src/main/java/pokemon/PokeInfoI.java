package pokemon;

import Enum.Gender;
import Enum.Nature;
import lombok.Getter;
import move.Move;
import pokemonStatus.*;
import pokemonStatus.impl.*;
import statusAilment.Ailment;
import statusAilment.AilmentE;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static bussinessLogic.ConsoleOutManager.showMessageParChar;
import static pokemonStatus.impl.FlinchI.initializeFlinch;
import static statusAilment.AilmentI.changeAilment;
import static statusAilment.AilmentI.initializeAilment;

@Getter
public class PokeInfoI implements PokeInfo {
    private final BasePrm basePrm;
    private final IndividualValue individualValue;
    private final EffortValue effortValue;
    private final Level level;
    private final Gender gender;
    private final Nature nature;
    private final List<Move> haveMove;
    private final Experience experience;
    private final CurrentHP currentHitPoint;
    private final StatusRank statusRank;
    private final Ailment statusAilment;
    private final Flinch flinch;

    public int getRealValHitPoint() {
        return ((this.basePrm.getHitPoint() * 2 + this.individualValue.hitPoint() + (this.effortValue.hitPoint() / 4)) * this.level.value() / 100) + 10 + this.level.value();
    }

    public int getRealValAttack() {
        return (int) ((((this.basePrm.getAttack() * 2 + this.individualValue.attack() + (this.effortValue.attack() / 4)) * this.level.value() / 100) + 5) * this.nature.getAttackRate());
    }

    public int getRealValBlock() {
        return (int) ((((this.basePrm.getBlock() * 2 + this.individualValue.block() + (this.effortValue.block() / 4)) * this.level.value() / 100) + 5) * this.nature.getBlockRate());
    }

    public int getRealValContact() {
        return (int) ((((this.basePrm.getContact() * 2 + this.individualValue.contact() + (this.effortValue.contact() / 4)) * this.level.value() / 100) + 5) * this.nature.getContactRate());
    }

    public int getRealValDefense() {
        return (int) ((((this.basePrm.getDefense() * 2 + this.individualValue.defense() + (this.effortValue.defense() / 4)) * this.level.value() / 100) + 5) * this.nature.getDefenceRate());
    }

    public int getRealValSpeed() {
        return (int) ((((this.basePrm.getSpeed() * 2 + this.individualValue.speed() + (this.effortValue.speed() / 4)) * this.level.value() / 100) + 5) * this.nature.getSpeedRate());
    }

    @Override
    public int giveExp() {
        return this.getLevel().value() * this.getBasePrm().getBasicExperience() / 7;
    }

    @Override
    public PokeInfo addExp(int exp) throws InterruptedException {
        PokeInfo result = this.withExperience(exp);
        showMessageParChar(result.getBasePrm().getName() + "は" + exp + "の経験値を獲得!");
        while (result.getExperience().isLevelUp(result)) {
            result = result.withLevel(1);
            showMessageParChar(result.getBasePrm().getName() + "はLv." + result.getLevel().value() + "にレベルアップした!");
        }
        return result;
    }

    @Override
    public PokeInfo recoveryHitPoint(int value) throws InterruptedException {
        PokeInfo result = this.withCurrentHitPoint(this.getCurrentHitPoint().recovery(this, new CurrentHPI(value)));
        System.out.println(result.getBasePrm().getName() + "は体力を" + value + "回復!  HP" + result.getCurrentHitPoint().val() + "/" + result.getRealValHitPoint());
        System.out.println();
        return result.withStatusAilment(changeAilment(result, AilmentE.FINE));
    }

    @Override
    public PokeInfo damagePoke(int value) throws InterruptedException {
        PokeInfo result = this.withCurrentHitPoint(this.getCurrentHitPoint().damage(new CurrentHPI(value)));
        showMessageParChar(result.getBasePrm().getName() + "は" + value + "のダメージ!");
        if (result.getCurrentHitPoint().isDead()) {
            return result.withStatusAilment(changeAilment(result, AilmentE.FAINTING));
        }
        return result;
    }

    @Override
    public PokeInfo recoveryPowerPoint(Move move, int value) {
        Move targetMoves = null;
        List<Move> haveMoves = this.getHaveMove();
        for (Move haveMove : haveMoves) {
            if (move.getClass() == haveMove.getClass()) {
                targetMoves = haveMove;
            }
        }
        CurrentPP recoveredPP = targetMoves.getCurrentPowerPoint().recovery(targetMoves, new CurrentPPI(value));
        Move recoveredPPMove = targetMoves.withCurrentPowerPoint(targetMoves, recoveredPP);
        PokeInfo result = this.withMove(recoveredPPMove);

        return result;
    }

    @Override
    public PokeInfo recoveryAll() throws InterruptedException {
        PokeInfo result = this.recoveryHitPoint(999);

        for (Move move : result.getHaveMove()) {
            result = result.recoveryPowerPoint(move, 99);
        }
        return result;
    }

    @Override
    public PokeInfo decrementPowerPoint(Move usedMove) {
        CurrentPP decrementedPowerPoint = usedMove.getCurrentPowerPoint().decrement(new CurrentPPI(1));
        Move decrementedPPMove = usedMove.withCurrentPowerPoint(usedMove, decrementedPowerPoint);
        PokeInfo pokemonInfo = this.withMove(decrementedPPMove);
        return pokemonInfo;
    }

    public PokeInfoI(BasePrm basePokemonInfo) {
        this.basePrm = basePokemonInfo;
        this.gender = Gender.decide();
        this.nature = Nature.decide();
        this.individualValue = new IndividualValueI();
        this.effortValue = new EffortValueI();
        this.level = new LevelI(5);
        this.haveMove = basePokemonInfo.getInitialMove();
        this.experience = new ExperienceI(135); // TODO 固定化したくない
        this.currentHitPoint = new CurrentHPI(getRealValHitPoint());
        this.statusRank = new StatusRankI();
        this.statusAilment = initializeAilment();
        this.flinch = initializeFlinch();
    }

    // 指定パラメータポケモンインスタンスを作成する。
    public PokeInfoI(
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
        this.individualValue = new IndividualValueI(individualValue.hitPoint(), individualValue.attack(), individualValue.block(), individualValue.contact(), individualValue.defense(), individualValue.speed());
        this.effortValue = new EffortValueI(effortValue.hitPoint(), effortValue.attack(), effortValue.block(), effortValue.contact(), effortValue.defense(), effortValue.speed());
        this.level = new LevelI(level.value());
        this.haveMove = haveMove;
        this.experience = new ExperienceI(0);
        this.currentHitPoint = new CurrentHPI(getRealValHitPoint());
        this.statusRank = new StatusRankI();
        this.statusAilment = initializeAilment();
        this.flinch = initializeFlinch();
    }

    private PokeInfoI(
            BasePrm basePokemonInfo,
            Gender gender,
            Nature nature,
            IndividualValue individualValue,
            EffortValue effortValue,
            Level level,
            Experience experience,
            List<Move> haveMove,
            CurrentHP currentHitPoint,
            StatusRank statusRankImpl,
            Ailment statusAilment,
            Flinch flinch
    ) {
        this.basePrm = basePokemonInfo;
        this.gender = gender;
        this.nature = nature;
        this.individualValue = new IndividualValueI(individualValue.hitPoint(), individualValue.attack(), individualValue.block(), individualValue.contact(), individualValue.defense(), individualValue.speed());
        this.effortValue = new EffortValueI(effortValue.hitPoint(), effortValue.attack(), effortValue.block(), effortValue.contact(), effortValue.defense(), effortValue.speed());
        this.level = new LevelI(level.value());
        this.haveMove = haveMove;
        this.experience = new ExperienceI(experience.totalExperience());
        this.currentHitPoint = new CurrentHPI(currentHitPoint.val());
        this.statusRank = new StatusRankI(statusRankImpl.getAttack(), statusRankImpl.getBlock(), statusRankImpl.getContact(), statusRankImpl.getDefense(), statusRankImpl.getSpeed(), statusRankImpl.getHitRate(), statusRankImpl.getAvoidRate());
        this.statusAilment = statusAilment;
        this.flinch = flinch;
    }

    @Override
    public PokeInfo withCurrentHitPoint(CurrentHP currentHitPoint) {
        return new PokeInfoI(this.basePrm, this.gender, this.nature, this.individualValue, this.effortValue, this.level, this.experience, this.haveMove, currentHitPoint, this.statusRank, this.statusAilment, this.flinch);
    }

    @Override
    public PokeInfo withExperience(int addingExperience) {
        return new PokeInfoI(this.basePrm, this.gender, this.nature, this.individualValue, this.effortValue, this.level, this.experience.add(addingExperience), this.haveMove, this.currentHitPoint, this.statusRank, this.statusAilment, this.flinch);
    }

    @Override
    public PokeInfo withLevel(int addLevel) {
        return new PokeInfoI(this.basePrm, this.gender, this.nature, this.individualValue, this.effortValue, this.level.add(addLevel), this.experience, this.haveMove, this.currentHitPoint, this.statusRank, this.statusAilment, this.flinch);
    }

    @Override
    public PokeInfo withAddedStatusRank(final int attack, final int block, final int contact, final int defense, final int speed, final int hitRate, final int avoidRate) {
        return new PokeInfoI(this.basePrm, this.gender, this.nature, this.individualValue, this.effortValue, this.level, this.experience, this.haveMove, this.currentHitPoint, this.statusRank.add(attack, block, contact, defense, speed, hitRate, avoidRate), this.statusAilment, this.flinch);
    }

    @Override
    public PokeInfo withResetStatusRank() {
        return new PokeInfoI(this.basePrm, this.gender, this.nature, this.individualValue, this.effortValue, this.level, this.experience, this.haveMove, this.currentHitPoint, this.statusRank.reset(), this.statusAilment, this.flinch);
    }

    @Override
    public PokeInfo withMove(Move move) {
        List<Move> newMoves = new ArrayList<>(4);
        for (Move haveMove : this.haveMove) {
            if (Objects.equals(move.baseMPrm().getName(), haveMove.baseMPrm().getName())) {
                newMoves.add(move);
            } else {
                newMoves.add(haveMove);
            }
        }
        return new PokeInfoI(this.basePrm, this.gender, this.nature, this.individualValue, this.effortValue, this.level, this.experience, newMoves, this.currentHitPoint, this.statusRank, this.statusAilment, this.flinch);
    }

    @Override
    public PokeInfo withStatusAilment(Ailment statusAilment) {
        return new PokeInfoI(this.basePrm, this.gender, this.nature, this.individualValue, this.effortValue, this.level, this.experience, this.haveMove, this.currentHitPoint, this.statusRank, statusAilment, this.flinch);
    }

    @Override
    public PokeInfo withFlinch(Flinch flinch) {
        return new PokeInfoI(this.basePrm, this.gender, this.nature, this.individualValue, this.effortValue, this.level, this.experience, this.haveMove, this.currentHitPoint, this.statusRank, this.statusAilment, flinch);
    }
}
