package pokemonStatus;

import pokemon.PokeInfo;

// 経験値を表現するクラス
public interface Experience {

    // 獲得総経験値
    public int totalExperience();

    // 次のレベルに必要な経験値
    public int nextRequireExperience(PokeInfo target);

    // 経験値を加算する
    public Experience add(int addExperience);

    // そのレベルに達するまでに必要な経験値
    public int requireExperience(PokeInfo target);

    // レベルアップ可能か
    public boolean isLevelUp(PokeInfo target);
}
