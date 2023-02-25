package pokemon;

import pokemonStatus.*;
import move.Move;
import move.Tackle;
import Enum.*;
import pokemonStatus.impl.*;

import java.util.ArrayList;
import java.util.List;

public class Squirtle implements PokemonInfo {
    private final String pokeName = "ゼニガメ";
    public String pokeDexNo() {
        return "006";
    }
    public String species() {
        return "かめのこポケモン";
    }
    private final int BASE_HP = 44;
    private final int BASE_ATTACK = 48;
    private final int BASE_BLOCK = 65;
    private final int BASE_CONTACT = 50;
    private final int BASE_DEFENSE = 64;
    private final int BASE_SPEED = 43;
    private final int BASIC_EXPERIENCE = 63;
    public ExperienceType experienceType() { return ExperienceType.TYPE1050000; }
    public Type pokemonType1() {
        return Type.WATER;
    }
    public Type pokemonType2() { return Type.NONE; }
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
    public String pokeName() {
        return this.pokeName;
    }
    public BaseStats baseStats() {
        return this.baseStats;
    }
    public IndividualValue individualValue() {
        return this.individualValue;
    }
    public EffortValue effortValue() {
        return this.effortValue;
    }
    public Level level() {
        return this.level;
    }
    public Gender gender() { return this.gender; }
    public Nature nature() { return this.nature; }
    public List<Move> haveMove() { return this.haveMove; }
    public int basicExperience() { return this.BASIC_EXPERIENCE; }
    public CurrentHitPoint currentHitPoint() { return this.currentHitPoint; }
    public Experience experience() { return this.experience; }
    public StatusRank statusRank() { return this.statusRank; }
    public int realValHitPoint() {
        return ((this.baseStats().hitPoint() * 2 + this.individualValue().hitPoint() + (this.effortValue().hitPoint() / 4)) * this.level().value() / 100) + 10 + this.level().value();
    }
    public int realValAttack() {
        return (int)((((this.baseStats().attack() * 2 + this.individualValue().attack() + (this.effortValue().attack() / 4)) * this.level().value() / 100) + 5) * this.nature.attackRateByNature());
    }
    public int realValBlock() {
        return (int)((((this.baseStats().block() * 2 + this.individualValue().block() + (this.effortValue().block() / 4)) * this.level().value() / 100) + 5) * this.nature.blockRateByNature());
    }
    public int realValContact() {
        return (int)((((this.baseStats().contact() * 2 + this.individualValue().contact() + (this.effortValue().contact() / 4)) * this.level().value() / 100) + 5) * this.nature.contactRateByNature());
    }
    public int realValDefense() {
        return (int)((((this.baseStats().defense() * 2 + this.individualValue().defense() + (this.effortValue().defense() / 4)) * this.level().value() / 100) + 5) * this.nature.defenceRateByNature());
    }
    public int realValSpeed() {
        return (int)((((this.baseStats().speed() * 2 + this.individualValue().speed() + (this.effortValue().speed() / 4)) * this.level().value() / 100) + 5) * this.nature.speedRateByNature());
    }

    public Squirtle() {
        this.gender = new GenderImpl();
        this.nature = new NatureImpl();
        this.baseStats = new BaseStatsImpl(BASE_HP, BASE_ATTACK, BASE_BLOCK, BASE_CONTACT, BASE_DEFENSE, BASE_SPEED);
        this.individualValue = new IndividualValueImpl();
        this.effortValue = new EffortValueImpl();
        this.level = new LevelImpl(5);
        this.haveMove = List.of(new Tackle());
        this.experience = new ExperienceImpl(135); // TODO 固定化したくない
        this.currentHitPoint = new CurrentHitPointImpl(realValHitPoint());
        this.statusRank = new StatusRankImpl();
    }

    public Squirtle(
            Gender gender,
            Nature nature,
            IndividualValue individualValue,
            EffortValue effortValue,
            Level level,
            Experience experience,
            List<Move> haveMove,
            CurrentHitPoint currentHitPoint,
            StatusRank statusRankImpl
    ) {
        this.gender = new GenderImpl(gender);
        this.nature = new NatureImpl(nature);
        this.baseStats = new BaseStatsImpl(BASE_HP, BASE_ATTACK, BASE_BLOCK, BASE_CONTACT, BASE_DEFENSE, BASE_SPEED);
        this.individualValue = new IndividualValueImpl(individualValue.hitPoint(), individualValue.attack(), individualValue.block(), individualValue.contact(), individualValue.defense(), individualValue.speed());
        this.effortValue = new EffortValueImpl(effortValue.hitPoint(), effortValue.attack(), effortValue.block(), effortValue.contact(), effortValue.defense(), effortValue.speed());
        this.level = new LevelImpl(level.value());
        this.haveMove = haveMove;
        this.experience = new ExperienceImpl(experience.totalExperience());
        this.currentHitPoint = new CurrentHitPointImpl(currentHitPoint.value());
        this.statusRank = new StatusRankImpl(statusRankImpl.attack(), statusRankImpl.block(), statusRankImpl.contact(), statusRankImpl.defense(), statusRankImpl.speed());
    }


    // TODO テスト用コード
    @Override
    public PokemonInfo withCurrentHitPoint(CurrentHitPoint currentHitPoint) {
        return new Squirtle(this.gender, this.nature, this.individualValue, this.effortValue, this.level, this.experience,this.haveMove, currentHitPoint, this.statusRank);
    }
    @Override
    public PokemonInfo withExperience(int addingExperience) {
        return new Squirtle(this.gender, this.nature, this.individualValue, this.effortValue, this.level, this.experience().add(addingExperience),this.haveMove, this.currentHitPoint, this.statusRank);
    }
    @Override
    public PokemonInfo withLevel(int addLevel) {
        return new Squirtle(this.gender, this.nature, this.individualValue, this.effortValue, this.level().add(addLevel), this.experience,this.haveMove, this.currentHitPoint, this.statusRank);
    }
    @Override
    public PokemonInfo withAddedStatusRank(final int attack, final int block, final int contact, final int defense, final int speed) {
        return new Squirtle(this.gender, this.nature, this.individualValue, this.effortValue, this.level(), this.experience,this.haveMove, this.currentHitPoint, this.statusRank.add(attack, block, contact, defense, speed));
    }
    @Override
    public PokemonInfo withResetStatusRank() {
        return new Squirtle(this.gender, this.nature, this.individualValue, this.effortValue, this.level(), this.experience, this.haveMove, this.currentHitPoint, this.statusRank.reset());
    }
    @Override
    public PokemonInfo withMove(Move move) {
        List<Move> newMoves = new ArrayList<>(4);
        List<Move> haveMoves = this.haveMove();
        for(Move haveMove : haveMoves) {
            if(move.getClass() == haveMove.getClass()) {
                newMoves.add(move);
            } else {
                newMoves.add(haveMove);
            }
        }
        return new Squirtle(this.gender, this.nature, this.individualValue, this.effortValue, this.level(), this.experience, newMoves, this.currentHitPoint, this.statusRank);
    }
}
