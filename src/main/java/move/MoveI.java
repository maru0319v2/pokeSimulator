package move;

import pokemonStatus.CurrentPP;
import pokemonStatus.impl.CurrentPPI;


public class MoveI implements Move {
    BaseMvPrm baseMPrm;
    CurrentPP currentPP;

    public static Move initMv(BaseMvPrm baseMPrm) {
        return new MoveI(baseMPrm);
    }

    private MoveI(BaseMvPrm baseMPrm) {
        this.baseMPrm = baseMPrm;
        this.currentPP = new CurrentPPI(baseMPrm().pp());
    }

    public MoveI(Move move, CurrentPP currentPP) {
        this.baseMPrm = move.baseMPrm();
        this.currentPP = currentPP;
    }

    @Override
    public BaseMvPrm baseMPrm() {
        return this.baseMPrm;
    }

    @Override
    public CurrentPP currentPP() {
        return this.currentPP;
    }

    @Override
    public Move withCurrentPP(Move move, CurrentPP decrementedPowerPoint) {
        return new MoveI(move, decrementedPowerPoint);
    }

    @Override
    public boolean canUse() {
        return this.currentPP().val() > 0;
    }
}
