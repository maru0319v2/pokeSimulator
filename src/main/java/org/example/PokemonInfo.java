package main.java.org.example;

public interface PokemonInfo {
    // 種族値
    BaseStats baseStats();
    // 個体値
    IndividualValue individualValue();
    // 努力値
    EffortValue effortValue();
}
