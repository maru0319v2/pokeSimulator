package pokemon;

import bussinessLogic.*;
import impl.*;
import move.Growl;
import move.Tackle;
import move.VineWhip;

import java.util.List;

public class Bulbasaur implements PokemonInfo {
    private final String pokeName = "フシギダネ";
    public String pokeDexNo() {
        return "001";
    }
    public String species() {
        return "たねポケモン";
    }
    private final int BASE_HP = 45;
    private final int BASE_ATTACK = 49;
    private final int BASE_BLOCK = 49;
    private final int BASE_CONTACT = 65;
    private final int BASE_DEFENSE = 65;
    private final int BASE_SPEED = 45;
    private final int BASIC_EXPERIENCE = 64;
    public ExperienceType experienceType() { return ExperienceType.TYPE1050000; }
    public Type pokemonType1() {
        return Type.GRASS;
    }
    public Type pokemonType2() { return Type.POISON; }

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
    public EffortValue effortValue() { return this.effortValue; }
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

    public Bulbasaur() {
        this.gender = new GenderImpl();
        this.nature = new NatureImpl();
        this.baseStats = new BaseStatsImpl(BASE_HP, BASE_ATTACK, BASE_BLOCK, BASE_CONTACT, BASE_DEFENSE, BASE_SPEED);
        this.individualValue = new IndividualValueImpl();
        this.effortValue = new EffortValueImpl();
        this.level = new LevelImpl(5);
        this.haveMove = List.of(new Tackle(), new VineWhip(), new Growl());
        this.experience = new ExperienceImpl(135); // TODO 固定化したくない
        this.currentHitPoint = new CurrentHitPointImpl(realValHitPoint());
        this.statusRank = new StatusRankImpl();
    }

    public Bulbasaur(
            Gender gender,
            Nature nature,
            IndividualValue individualValue,
            EffortValue effortValue,
            Level level,
            Experience experience,
            CurrentHitPoint currentHitPoint,
            StatusRank statusRankImpl
    ) {
        this.gender = new GenderImpl(gender);
        this.nature = new NatureImpl(nature);
        this.baseStats = new BaseStatsImpl(BASE_HP, BASE_ATTACK, BASE_BLOCK, BASE_CONTACT, BASE_DEFENSE, BASE_SPEED);
        this.individualValue = new IndividualValueImpl(individualValue.hitPoint(), individualValue.attack(), individualValue.block(), individualValue.contact(), individualValue.defense(), individualValue.speed());
        this.effortValue = new EffortValueImpl(effortValue.hitPoint(), effortValue.attack(), effortValue.block(), effortValue.contact(), effortValue.defense(), effortValue.speed());
        this.level = new LevelImpl(level.value());
        this.haveMove = List.of(new Tackle(), new VineWhip(), new Growl());
        this.experience = new ExperienceImpl(experience.totalExperience());
        this.currentHitPoint = new CurrentHitPointImpl(currentHitPoint.value());
        this.statusRank = new StatusRankImpl(statusRankImpl.attack(), statusRankImpl.block(), statusRankImpl.contact(), statusRankImpl.defense(), statusRankImpl.speed());
    }

    // TODO テスト用コード
    @Override
    public PokemonInfo withCurrentHitPoint(CurrentHitPoint currentHitPoint) {
        return new Bulbasaur(this.gender, this.nature, this.individualValue, this.effortValue, this.level, this.experience, currentHitPoint, this.statusRank);
    }
    @Override
    public PokemonInfo withExperience(int addingExperience) {
        return new Bulbasaur(this.gender, this.nature, this.individualValue, this.effortValue, this.level, this.experience().add(addingExperience), this.currentHitPoint, this.statusRank);
    }
    @Override
    public PokemonInfo withLevel(int addLevel) {
        return new Bulbasaur(this.gender, this.nature, this.individualValue, this.effortValue, this.level().add(addLevel), this.experience, this.currentHitPoint, this.statusRank);
    }
    @Override
    public PokemonInfo withAddedStatusRank(final int attack, final int block, final int contact, final int defense, final int speed) {
        return new Bulbasaur(this.gender, this.nature, this.individualValue, this.effortValue, this.level(), this.experience, this.currentHitPoint, this.statusRank.add(attack, block, contact, defense, speed));
    }
    @Override
    public PokemonInfo withResetStatusRank() {
        return new Bulbasaur(this.gender, this.nature, this.individualValue, this.effortValue, this.level(), this.experience, this.currentHitPoint, this.statusRank.reset());
    }
}
