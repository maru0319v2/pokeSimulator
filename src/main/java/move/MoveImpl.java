package move;

import lombok.Getter;
import pokemonStatus.CurrentPowerPoint;
import pokemonStatus.impl.CurrentPowerPointImpl;

import java.util.Random;

@Getter
public class MoveImpl implements Move {
    BaseMPrm baseMPrm;
    CurrentPowerPoint currentPowerPoint;

    public MoveImpl(BaseMPrm baseMPrm) {
        this.baseMPrm = baseMPrm;
        this.currentPowerPoint = new CurrentPowerPointImpl(baseMPrm().getPowerPoint());
    }

    public MoveImpl(Move move, CurrentPowerPoint currentPowerPoint) {
        this.baseMPrm = move.baseMPrm();
        this.currentPowerPoint = currentPowerPoint;
    }

    @Override
    public BaseMPrm baseMPrm() {
        return this.baseMPrm;
    }

    @Override
    public CurrentPowerPoint getCurrentPowerPoint() {
        return this.currentPowerPoint;
    }

    @Override
    public Move withCurrentPowerPoint(Move move, CurrentPowerPoint decrementedPowerPoint) {
        return new MoveImpl(move, decrementedPowerPoint);
    }

    @Override
    public boolean canUse() {
        return this.getCurrentPowerPoint().value() > 0;
    }

    @Override
    public boolean isHit() {
        if(this.baseMPrm.getHitRate() == -1) {
            return true;
        }
        return (new Random().nextInt(100) + 1) <= this.baseMPrm().getHitRate();
    }
}
