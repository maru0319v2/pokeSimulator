package main.java.org.example.pokemon;

import main.java.org.example.*;
import main.java.org.example.impl.*;
import main.java.org.example.move.Tackle;

public class Bulbasaur implements PokemonInfo {

    private final BaseStatsImpl baseStats;
    private final IndividualValueImpl individualValue;
    private final EffortValueImpl effortValue;
    private final Level level;
    private final Gender gender;
    private final Nature nature;
    private final Move haveMove;

    public String pokeName() {
        return "フシギダネ";
    }

    public String pokeDexNo() {
        return "001";
    }

    public String species() {
        return "たねポケモン";
    }

    public PokemonType pokemonType1() {
        return PokemonType.GRASS;
    }

    public PokemonType pokemonType2() {
        return PokemonType.POISON;
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

    public Move haveMove() { return this.haveMove; }

    public Bulbasaur() {
        this.gender = new GenderImpl();
        this.nature = new NatureImpl();
        this.baseStats = new BaseStatsImpl(45, 49, 49, 65, 65, 45);
        this.individualValue = new IndividualValueImpl();
        this.effortValue = new EffortValueImpl(0, 0, 0, 0, 0, 0);
        this.level = new LevelImpl(1);
        this.haveMove = new Tackle();
    }

    public Bulbasaur(
            Gender gender,
            Nature nature,
            IndividualValue individualValue,
            EffortValue effortValue,
            Level level
    ) {
        this.gender = new GenderImpl(gender);
        this.nature = new NatureImpl(nature);
        this.baseStats = new BaseStatsImpl(45, 49, 49, 65, 65, 45);
        this.individualValue = new IndividualValueImpl(individualValue.hitPoint(), individualValue.attack(), individualValue.block(), individualValue.contact(), individualValue.defense(), individualValue.speed());
        this.effortValue = new EffortValueImpl(effortValue.hitPoint(), effortValue.attack(), effortValue.block(), effortValue.contact(), effortValue.defense(), effortValue.speed());
        this.level = new LevelImpl(level.value());
        this.haveMove = new Tackle();
    }
}
