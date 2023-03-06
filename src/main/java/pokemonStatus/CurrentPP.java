package pokemonStatus;

import move.Move;

public interface CurrentPP {
    // 現在PPの値
    int val();

    // PPを回復する。
    CurrentPP recovery(Move target, CurrentPP currentPowerPoint);

    // PPを減らす。
    CurrentPP decrement(CurrentPP currentPowerPoint);
}
