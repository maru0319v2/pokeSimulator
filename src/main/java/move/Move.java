package move;

import pokemonStatus.CurrentPowerPoint;

// 技を表現するクラス
public interface Move {
    // 技固有のパラメータ
    BaseMPrm baseMPrm();
    // 現在のPP
    CurrentPowerPoint getCurrentPowerPoint();
    Move withCurrentPowerPoint(Move move, CurrentPowerPoint decrementedPowerPoint);
    boolean canUse();
}
