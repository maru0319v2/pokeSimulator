package main.java.org.example.pokemon;

import main.java.org.example.*;
import main.java.org.example.impl.*;
import main.java.org.example.move.Tackle;

public class Charmander implements PokemonInfo {
    private final BaseStatsImpl baseStats;
    private final IndividualValueImpl individualValue;
    private final EffortValueImpl effortValue;
    private final Level level;
    private final Gender gender;
    private final Nature nature;
    private final Move haveMove;
    private final Experience experience;
    private final CurrentHitPoint currentHitPoint;

    private final int BASE_HP = 39;
    private final int BASE_ATTACK = 52;
    private final int BASE_BLOCK = 43;
    private final int BASE_CONTACT = 60;
    private final int BASE_DEFENSE = 50;
    private final int BASE_SPEED = 65;

    private final String pokeName = "ヒトカゲ";

    public String pokeName() {
        return this.pokeName;
    }

    public String pokeDexNo() {
        return "004";
    }

    public String species() {
        return "とかげポケモン";
    }

    public ExperienceType experienceType() { return ExperienceType.TYPE1050000; }

    public PokemonType pokemonType1() {
        return PokemonType.FIRE;
    }

    public PokemonType pokemonType2() { return PokemonType.NONE; }

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

    public Move haveMove() { return this.haveMove; }

    @Override
    public CurrentHitPoint currentHitPoint() { return this.currentHitPoint; }

    public Experience experience() { return this.experience; }

    public int realValHitPoint() {
        return ((this.baseStats().hitPoint() * 2 + this.individualValue().hitPoint() + (this.effortValue().hitPoint() / 4)) * this.level().value() / 100) + 10 + this.level().value();
    }

    public int realValAttack() {
        return (int)((((this.baseStats().attack() * 2 + this.individualValue().attack() + (this.effortValue().attack() / 4)) * this.level().value() / 100) + 5) * 1.0); // 最後の1.0が性格補正
    }

    public int realValBlock() {
        return (int)((((this.baseStats().block() * 2 + this.individualValue().block() + (this.effortValue().block() / 4)) * this.level().value() / 100) + 5) * 1.0);
    }

    public int realValContact() {
        return (int)((((this.baseStats().contact() * 2 + this.individualValue().contact() + (this.effortValue().contact() / 4)) * this.level().value() / 100) + 5) * 1.0);
    }

    public int realValDefense() {
        return (int)((((this.baseStats().defense() * 2 + this.individualValue().defense() + (this.effortValue().defense() / 4)) * this.level().value() / 100) + 5) * 1.0);
    }

    public int realValSpeed() {
        return (int)((((this.baseStats().speed() * 2 + this.individualValue().speed() + (this.effortValue().speed() / 4)) * this.level().value() / 100) + 5) * 1.0);
    }

    public Charmander() {
        this.gender = new GenderImpl();
        this.nature = new NatureImpl();
        this.baseStats = new BaseStatsImpl(BASE_HP, BASE_ATTACK, BASE_BLOCK, BASE_CONTACT, BASE_DEFENSE, BASE_SPEED);
        this.individualValue = new IndividualValueImpl();
        this.effortValue = new EffortValueImpl();
        this.level = new LevelImpl(5);
        this.haveMove = new Tackle();
        this.experience = new ExperienceImpl(0);
        this.currentHitPoint = new CurrentHitPointImpl(realValHitPoint());
    }

    public Charmander(
            Gender gender,
            Nature nature,
            IndividualValue individualValue,
            EffortValue effortValue,
            Level level,
            Experience experience,
            CurrentHitPoint currentHitPoint
    ) {
        this.gender = new GenderImpl(gender);
        this.nature = new NatureImpl(nature);
        this.baseStats = new BaseStatsImpl(BASE_HP, BASE_ATTACK, BASE_BLOCK, BASE_CONTACT, BASE_DEFENSE, BASE_SPEED);
        this.individualValue = new IndividualValueImpl(individualValue.hitPoint(), individualValue.attack(), individualValue.block(), individualValue.contact(), individualValue.defense(), individualValue.speed());
        this.effortValue = new EffortValueImpl(effortValue.hitPoint(), effortValue.attack(), effortValue.block(), effortValue.contact(), effortValue.defense(), effortValue.speed());
        this.level = new LevelImpl(level.value());
        this.haveMove = new Tackle();
        this.experience = new ExperienceImpl(experience.totalExperience());
        this.currentHitPoint = new CurrentHitPointImpl(currentHitPoint.value());
    }


    // TODO テスト用コード
    @Override
    public PokemonInfo withCurrentHitPoint(CurrentHitPoint currentHitPoint) {
        return new Charmander(gender, nature, individualValue, effortValue, level, experience, currentHitPoint);
    }
}
