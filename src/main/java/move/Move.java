package move;

import pokemonStatus.CurrentPP;

// 技を表現するクラス
public interface Move {
    // 技固有のパラメータ
    BaseMvPrm baseMPrm();

    // 現在のPP
    CurrentPP getCurrentPowerPoint();

    Move withCurrentPowerPoint(Move move, CurrentPP decrementedPowerPoint);

    boolean canUse();
}
