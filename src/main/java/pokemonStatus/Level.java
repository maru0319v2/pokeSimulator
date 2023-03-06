package pokemonStatus;

// レベルを表現するクラス
public interface Level {
    Level add();

    int val();

    Level add(int value);
}
