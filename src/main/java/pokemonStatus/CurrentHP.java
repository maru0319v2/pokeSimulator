package pokemonStatus;

import pokemon.PokeInfo;
import pokemonStatus.impl.CurrentHPI;

public interface CurrentHP {

    // 現在HPの値
    public int value();

    // 体力を回復する。
    public CurrentHP recovery(PokeInfo target, CurrentHPI currentHitPointImpl) throws InterruptedException;

    // 体力を減らす。
    public CurrentHP damage(CurrentHPI currentHitPointImpl);

    boolean isAlive();

    boolean isDead();
}
