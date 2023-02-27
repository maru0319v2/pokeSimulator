package pokemon;

import lombok.Getter;
import move.*;
import pokemonStatus.*;
import pokemonStatus.impl.*;
import Enum.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        return (int)((((this.basePrm.getAttack() * 2 + this.individualValue.attack() + (this.effortValue.attack() / 4)) * this.level.value() / 100) + 5) * this.nature.attackRateByNature());
    }
    public int getRealValBlock() {
        return (int)((((this.basePrm.getBlock() * 2 + this.individualValue.block() + (this.effortValue.block() / 4)) * this.level.value() / 100) + 5) * this.nature.blockRateByNature());
    }
    public int getRealValContact() {
        return (int)((((this.basePrm.getContact() * 2 + this.individualValue.contact() + (this.effortValue.contact() / 4)) * this.level.value() / 100) + 5) * this.nature.contactRateByNature());
    }
    public int getRealValDefense() {
        return (int)((((this.basePrm.getDefense() * 2 + this.individualValue.defense() + (this.effortValue.defense() / 4)) * this.level.value() / 100) + 5) * this.nature.defenceRateByNature());
    }
    public int getRealValSpeed() {
        return (int)((((this.basePrm.getSpeed() * 2 + this.individualValue.speed() + (this.effortValue.speed() / 4)) * this.level.value() / 100) + 5) * this.nature.speedRateByNature());
    }

    public PokemonInfoImpl(BasePrm basePokemonInfo) {
        this.basePrm = basePokemonInfo;
        this.gender = new GenderImpl();
        this.nature = new NatureImpl();
        this.individualValue = new IndividualValueImpl();
        this.effortValue = new EffortValueImpl();
        this.level = new LevelImpl(5);
        this.haveMove = basePokemonInfo.getInitialMove();
        this.experience = new ExperienceImpl(135); // TODO 固定化したくない
        this.currentHitPoint = new CurrentHitPointImpl(getRealValHitPoint());
        this.statusRank = new StatusRankImpl();
        this.statusAilment = StatusAilment.NONE;
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
        this.gender = new GenderImpl(gender);
        this.nature = new NatureImpl(nature);
        this.individualValue = new IndividualValueImpl(individualValue.hitPoint(), individualValue.attack(), individualValue.block(), individualValue.contact(), individualValue.defense(), individualValue.speed());
        this.effortValue = new EffortValueImpl(effortValue.hitPoint(), effortValue.attack(), effortValue.block(), effortValue.contact(), effortValue.defense(), effortValue.speed());
        this.level = new LevelImpl(level.value());
        this.haveMove = haveMove;
        this.experience = new ExperienceImpl(0);
        this.currentHitPoint = new CurrentHitPointImpl(getRealValHitPoint());
        this.statusRank = new StatusRankImpl();
        this.statusAilment = StatusAilment.NONE;
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
        this.gender = new GenderImpl(gender);
        this.nature = new NatureImpl(nature);
        this.individualValue = new IndividualValueImpl(individualValue.hitPoint(), individualValue.attack(), individualValue.block(), individualValue.contact(), individualValue.defense(), individualValue.speed());
        this.effortValue = new EffortValueImpl(effortValue.hitPoint(), effortValue.attack(), effortValue.block(), effortValue.contact(), effortValue.defense(), effortValue.speed());
        this.level = new LevelImpl(level.value());
        this.haveMove = haveMove;
        this.experience = new ExperienceImpl(experience.totalExperience());
        this.currentHitPoint = new CurrentHitPointImpl(currentHitPoint.value());
        this.statusRank = new StatusRankImpl(statusRankImpl.getAttack(), statusRankImpl.getBlock(), statusRankImpl.getContact(), statusRankImpl.getDefense(), statusRankImpl.getSpeed());
        this.statusAilment = statusAilment;
    }

    // TODO テスト用コード
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
    public PokemonInfo withAddedStatusRank(final int attack, final int block, final int contact, final int defense, final int speed) {
        return new PokemonInfoImpl(this.basePrm, this.gender, this.nature, this.individualValue, this.effortValue, this.level, this.experience,this.haveMove, this.currentHitPoint, this.statusRank.add(attack, block, contact, defense, speed), this.statusAilment);
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
