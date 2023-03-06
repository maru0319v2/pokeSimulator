package pokemonStatus;

import pokemon.PokeInfo;

// 経験値を表現するクラス
public interface Experience {

    // 獲得総経験値
    int totalExp();

    // 次のレベルに必要な経験値
    int nextRequireExp(PokeInfo target);

    // 経験値を加算する
    Experience add(int addExperience);

    // そのレベルに達するまでに必要な経験値
    int requireExp(PokeInfo target);

    // レベルアップ可能か
    boolean isLevelUp(PokeInfo target);
}
