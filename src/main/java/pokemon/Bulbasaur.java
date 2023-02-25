package pokemon;

import lombok.Getter;
import pokemonStatus.*;
import move.*;
import Enum.*;
import pokemonStatus.impl.*;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Bulbasaur implements PokemonInfo {
    private final String pokeName = "フシギダネ";
    private final String pokeDexNo = "001";
    private final String species = "たねポケモン";
    private final Type type1 = Type.GRASS;
    private final Type type2 = Type.POISON;
    private final int baseHp = 45;
    private final int baseAttack = 49;
    private final int baseBlock = 49;
    private final int baseContact = 65;
    private final int baseDefense = 65;
    private final int baseSpeed = 45;
    private final int basicExperience = 64;
    private final ExperienceType experienceType = ExperienceType.TYPE1050000;
    private final BaseStatsImpl baseStats;
    private final IndividualValueImpl individualValue;
    private final EffortValueImpl effortValue;
    private final Level level;
    private final Gender gender;
    private final Nature nature;
    private final List<Move> haveMove;
    private final Experience experience;
    private final CurrentHitPoint currentHitPoint;
    private final StatusRank statusRank;
    private final StatusAilment statusAilment;

    public int getRealValHitPoint() {
        return ((this.baseStats.hitPoint() * 2 + this.individualValue.hitPoint() + (this.effortValue.hitPoint() / 4)) * this.level.value() / 100) + 10 + this.level.value();
    }
    public int getRealValAttack() {
        return (int)((((this.baseStats.attack() * 2 + this.individualValue.attack() + (this.effortValue.attack() / 4)) * this.level.value() / 100) + 5) * this.nature.attackRateByNature());
    }
    public int getRealValBlock() {
        return (int)((((this.baseStats.block() * 2 + this.individualValue.block() + (this.effortValue.block() / 4)) * this.level.value() / 100) + 5) * this.nature.blockRateByNature());
    }
    public int getRealValContact() {
        return (int)((((this.baseStats.contact() * 2 + this.individualValue.contact() + (this.effortValue.contact() / 4)) * this.level.value() / 100) + 5) * this.nature.contactRateByNature());
    }
    public int getRealValDefense() {
        return (int)((((this.baseStats.defense() * 2 + this.individualValue.defense() + (this.effortValue.defense() / 4)) * this.level.value() / 100) + 5) * this.nature.defenceRateByNature());
    }
    public int getRealValSpeed() {
        return (int)((((this.baseStats.speed() * 2 + this.individualValue.speed() + (this.effortValue.speed() / 4)) * this.level.value() / 100) + 5) * this.nature.speedRateByNature());
    }

    public Bulbasaur() {
        this.gender = new GenderImpl();
        this.nature = new NatureImpl();
        this.baseStats = new BaseStatsImpl(baseHp, baseAttack, baseBlock, baseContact, baseDefense, baseSpeed);
        this.individualValue = new IndividualValueImpl();
        this.effortValue = new EffortValueImpl();
        this.level = new LevelImpl(5);
        this.haveMove = List.of(new Tackle(), new VineWhip(), new Growl(), new SwordsDance());
        this.experience = new ExperienceImpl(135); // TODO 固定化したくない
        this.currentHitPoint = new CurrentHitPointImpl(getRealValHitPoint());
        this.statusRank = new StatusRankImpl();
        this.statusAilment = StatusAilment.NONE;
    }

    private Bulbasaur(
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
        this.gender = new GenderImpl(gender);
        this.nature = new NatureImpl(nature);
        this.baseStats = new BaseStatsImpl(baseHp, baseAttack, baseBlock, baseContact, baseDefense, baseSpeed);
        this.individualValue = new IndividualValueImpl(individualValue.hitPoint(), individualValue.attack(), individualValue.block(), individualValue.contact(), individualValue.defense(), individualValue.speed());
        this.effortValue = new EffortValueImpl(effortValue.hitPoint(), effortValue.attack(), effortValue.block(), effortValue.contact(), effortValue.defense(), effortValue.speed());
        this.level = new LevelImpl(level.value());
        this.haveMove = haveMove;
        this.experience = new ExperienceImpl(experience.totalExperience());
        this.currentHitPoint = new CurrentHitPointImpl(currentHitPoint.value());
        this.statusRank = new StatusRankImpl(statusRankImpl.attack(), statusRankImpl.block(), statusRankImpl.contact(), statusRankImpl.defense(), statusRankImpl.speed());
        this.statusAilment = statusAilment;
    }

    // TODO テスト用コード
    @Override
    public PokemonInfo withCurrentHitPoint(CurrentHitPoint currentHitPoint) {
        return new Bulbasaur(this.gender, this.nature, this.individualValue, this.effortValue, this.level, this.experience,this.haveMove, currentHitPoint, this.statusRank, this.statusAilment);
    }
    @Override
    public PokemonInfo withExperience(int addingExperience) {
        return new Bulbasaur(this.gender, this.nature, this.individualValue, this.effortValue, this.level, this.experience.add(addingExperience),this.haveMove, this.currentHitPoint, this.statusRank, this.statusAilment);
    }
    @Override
    public PokemonInfo withLevel(int addLevel) {
        return new Bulbasaur(this.gender, this.nature, this.individualValue, this.effortValue, this.level.add(addLevel), this.experience,this.haveMove, this.currentHitPoint, this.statusRank, this.statusAilment);
    }
    @Override
    public PokemonInfo withAddedStatusRank(final int attack, final int block, final int contact, final int defense, final int speed) {
        return new Bulbasaur(this.gender, this.nature, this.individualValue, this.effortValue, this.level, this.experience,this.haveMove, this.currentHitPoint, this.statusRank.add(attack, block, contact, defense, speed), this.statusAilment);
    }
    @Override
    public PokemonInfo withResetStatusRank() {
        return new Bulbasaur(this.gender, this.nature, this.individualValue, this.effortValue, this.level, this.experience, this.haveMove, this.currentHitPoint, this.statusRank.reset(), this.statusAilment);
    }

    @Override
    public PokemonInfo withMove(Move move) {
        List<Move> newMoves = new ArrayList<>(4);
        for(Move haveMove : this.haveMove) {
            if(move.getClass() == haveMove.getClass()) {
                newMoves.add(move);
            } else {
                newMoves.add(haveMove);
            }
        }
        return new Bulbasaur(this.gender, this.nature, this.individualValue, this.effortValue, this.level, this.experience, newMoves, this.currentHitPoint, this.statusRank, this.statusAilment);
    }
    @Override
    public PokemonInfo withStatusAilment(StatusAilment statusAilment) {
        return new Bulbasaur(this.gender, this.nature, this.individualValue, this.effortValue, this.level, this.experience, this.haveMove, this.currentHitPoint, this.statusRank, statusAilment);
    }
}
