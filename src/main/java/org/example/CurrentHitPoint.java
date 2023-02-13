package main.java.org.example;

import main.java.org.example.impl.CurrentHitPointImpl;

public interface CurrentHitPoint {

    public int value();

    // 体力を回復する。
    public CurrentHitPointImpl recovery(PokemonInfo target, CurrentHitPointImpl currentHitPointImpl);

    // 体力を減らす。
    public CurrentHitPointImpl damage(CurrentHitPointImpl currentHitPointImpl);
}
