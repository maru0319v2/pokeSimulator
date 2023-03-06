package pokemonStatus.impl;

import lombok.AllArgsConstructor;
import move.Move;
import pokemonStatus.CurrentPP;

@AllArgsConstructor
public class CurrentPPI implements CurrentPP {
    private static final int MIN = 0;
    private final int val;

    public int val() {
        return this.val;
    }

    @Override
    public CurrentPP recovery(Move target, CurrentPP currentPP) {
        if (currentPP.val() <= MIN) {
            throw new IllegalArgumentException("PP回復量は1以上を指定してください。");
        }
        final int recovered = this.val() + currentPP.val();
        int result = Math.min(recovered, target.baseMPrm().pp());

        return new CurrentPPI(result);
    }

    @Override
    public CurrentPP decrement(CurrentPP currentPP) {
        if (currentPP.val() <= MIN) {
            throw new IllegalArgumentException("PP減少量は1以上を指定してください。");
        }
        final int decremented = this.val() - currentPP.val();
        return new CurrentPPI(decremented);
    }
}
