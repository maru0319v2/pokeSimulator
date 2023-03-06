package pokemonStatus;

import pokemon.PokeInfo;
import pokemonStatus.impl.CurrentHitPointImpl;

public interface CurrentHitPoint {

    // 現在HPの値
    public int value();

    // 体力を回復する。
    public CurrentHitPoint recovery(PokeInfo target, CurrentHitPointImpl currentHitPointImpl) throws InterruptedException;

    // 体力を減らす。
    public CurrentHitPoint damage(CurrentHitPointImpl currentHitPointImpl);

    boolean isAlive();

    boolean isDead();
}
