package bussinessLogic;

import impl.CurrentHitPointImpl;

public interface CurrentHitPoint {

    // 現在HPの値
    public int value();

    // 体力を回復する。
    public CurrentHitPoint recovery(PokemonInfo target, CurrentHitPointImpl currentHitPointImpl);

    // 体力を減らす。
    public CurrentHitPoint damage(CurrentHitPointImpl currentHitPointImpl);
}
