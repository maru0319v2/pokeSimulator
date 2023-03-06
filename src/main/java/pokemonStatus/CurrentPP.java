package pokemonStatus;

import move.Move;

public interface CurrentPP {
    // 現在PPの値
    public int value();

    // PPを回復する。
    public CurrentPP recovery(Move target, CurrentPP currentPowerPoint);

    // PPを減らす。
    public CurrentPP decrement(CurrentPP currentPowerPoint);
}
