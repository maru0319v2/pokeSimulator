package main.java.org.example;

// ポケモンの情報を表現するクラス
public interface PokemonInfo {

    // 名前
    String pokeName();
    // 図鑑No.
    String pokeDexNo();
    // 分類
    String species();
    // タイプ1
    PokemonType pokemonType1();
    // タイプ2
    PokemonType pokemonType2();
    // 種族値
    BaseStats baseStats();
    // 個体値
    IndividualValue individualValue();
    // 努力値
    EffortValue effortValue();
    // レベル
    Level level();
    // 性別
    Gender gender();
    // 性格
    Nature nature();
    Move haveMove();
}
