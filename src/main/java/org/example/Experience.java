package main.java.org.example;

// 経験値を表現するクラス
public interface Experience {

    // 獲得総経験値
    public int totalExperience();

    // 次のレベルに必要な経験値
    public int nextRequireExperience(PokemonInfo target);

    // 経験値を加算する
    public Experience add(int addExperience);

    // そのレベルに達するまでに必要な経験値
    public int requireExperience(PokemonInfo target);
}
