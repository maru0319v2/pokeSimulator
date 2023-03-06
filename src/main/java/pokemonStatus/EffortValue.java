package pokemonStatus;

public interface EffortValue {
    // 努力値を加算する。
    EffortValue add(final int hitPoint, final int attack, final int block, final int contact, final int defence, final int speed);

    // 努力値をリセットする。
    EffortValue reset();

    int hp();

    int attack();

    int block();

    int contact();

    int defence();

    int speed();
}
