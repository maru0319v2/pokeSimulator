package move;

import lombok.Getter;
import pokemonStatus.CurrentPP;
import pokemonStatus.impl.CurrentPPI;


@Getter
public class MoveI implements Move {
    BaseMvPrm baseMPrm;
    CurrentPP currentPowerPoint;

    public MoveI(BaseMvPrm baseMPrm) {
        this.baseMPrm = baseMPrm;
        this.currentPowerPoint = new CurrentPPI(baseMPrm().getPowerPoint());
    }

    public MoveI(Move move, CurrentPP currentPowerPoint) {
        this.baseMPrm = move.baseMPrm();
        this.currentPowerPoint = currentPowerPoint;
    }

    @Override
    public BaseMvPrm baseMPrm() {
        return this.baseMPrm;
    }

    @Override
    public CurrentPP getCurrentPowerPoint() {
        return this.currentPowerPoint;
    }

    @Override
    public Move withCurrentPowerPoint(Move move, CurrentPP decrementedPowerPoint) {
        return new MoveI(move, decrementedPowerPoint);
    }

    @Override
    public boolean canUse() {
        return this.getCurrentPowerPoint().val() > 0;
    }
}
