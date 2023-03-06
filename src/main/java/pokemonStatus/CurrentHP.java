package pokemonStatus;

import pokemon.PokeInfo;

public interface CurrentHP {

    // 現在HPの値
    int val();

    // 体力を回復する。
    CurrentHP recovery(PokeInfo target, CurrentHP currentHP) throws InterruptedException;

    // 体力を減らす。
    CurrentHP damage(CurrentHP currentHP);

    boolean isAlive();

    boolean isDead();
}
