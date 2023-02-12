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
    // 覚えている技
    Move haveMove();

    // TODO 現在の残りHP
    // HP実数値
    int realValHitPoint();
    // 攻撃実数値
    int realValAttack();
    // 防御実数値
    int realValBlock();
    // 特攻実数値
    int realValContact();
    // 特防実数値
    int realValDefense();
    // 素早実数値
    int realValSpeed();
    // 経験値タイプ
    String experienceType();
    // 総獲得経験値
    Experience experience();
}
