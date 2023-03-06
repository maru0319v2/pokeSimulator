package pokemonStatus.impl;

import move.Move;
import pokemonStatus.CurrentPP;

public class CurrentPPI implements CurrentPP {
    private static final int MIN = 0;
    private final int value;

    public CurrentPPI(int value) {
        this.value = value;
    }

    @Override
    public int value() {
        return this.value;
    }

    @Override
    public CurrentPP recovery(Move target, CurrentPP currentPowerPoint) {
        if (currentPowerPoint.value() <= MIN) {
            throw new IllegalArgumentException("PP回復量は1以上を指定してください。");
        }
        final int recovered = this.value + currentPowerPoint.value();
        int result = Math.min(recovered, target.baseMPrm().getPowerPoint());

        return new CurrentPPI(result);
    }

    @Override
    public CurrentPP decrement(CurrentPP currentPowerPoint) {
        if (currentPowerPoint.value() <= MIN) {
            throw new IllegalArgumentException("PP減少量は1以上を指定してください。");
        }
        final int decremented = this.value - currentPowerPoint.value();
        return new CurrentPPI(decremented);
    }
}
