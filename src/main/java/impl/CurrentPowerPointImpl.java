package impl;

import bussinessLogic.CurrentPowerPoint;
import bussinessLogic.Move;
import bussinessLogic.PokemonInfo;

import java.util.List;

public class CurrentPowerPointImpl implements CurrentPowerPoint {
    private static final int MIN = 0;
    private final int value;

    public CurrentPowerPointImpl(int value) {
        this.value = value;
    }

    @Override
    public int value() {
        return this.value;
    }

    @Override
    public CurrentPowerPoint recovery(Move target, CurrentPowerPoint currentPowerPoint) {
        if (currentPowerPoint.value() <= MIN) {
            throw new IllegalArgumentException("PP回復量は1以上を指定してください。");
        }
        final int recovered = this.value + currentPowerPoint.value();
        int result = Math.min(recovered, target.powerPoint());

        return new CurrentPowerPointImpl(result);
    }

    @Override
    public CurrentPowerPoint decrement(CurrentPowerPoint currentPowerPoint) {
        if (currentPowerPoint.value() <= MIN) {
            throw new IllegalArgumentException("PP減少量は1以上を指定してください。");
        }
        final int decremented = this.value - currentPowerPoint.value();
        return new CurrentPowerPointImpl(decremented);
    }
}
