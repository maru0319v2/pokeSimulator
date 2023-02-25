package bussinessLogic;

import impl.CurrentHitPointImpl;

public interface CurrentPowerPoint {
    // 現在PPの値
    public int value();

    // PPを回復する。
    public CurrentPowerPoint recovery(Move target, CurrentPowerPoint currentPowerPoint);

    // PPを減らす。
    public CurrentPowerPoint decrement(CurrentPowerPoint currentPowerPoint);
}
