package impl;

import bussinessLogic.CurrentHitPoint;
import bussinessLogic.PokemonInfo;

public class CurrentHitPointImpl implements CurrentHitPoint {
    private static final int MIN = 0;
    private final int value;

    public CurrentHitPointImpl(int value) {
        this.value = value;
    }

    @Override
    public int value() {
        return this.value;
    }

    public CurrentHitPointImpl recovery(PokemonInfo target, CurrentHitPointImpl currentHitPointImpl) {
        if (currentHitPointImpl.value <= MIN) {
            throw new IllegalArgumentException("回復量は1以上を指定してください。");
        }
        final int added = this.value + currentHitPointImpl.value;
        int result = Math.min(added, target.realValHitPoint());
        return new CurrentHitPointImpl(result);
    }

    public CurrentHitPointImpl damage(CurrentHitPointImpl currentHitPointImpl) {
        if (currentHitPointImpl.value <= MIN) {
            throw new IllegalArgumentException("回復量は1以上を指定してください。");
        }
        final int damaged = this.value - currentHitPointImpl.value;
        if(damaged <= MIN) {
            // TODO 瀕死状態になる
            return new CurrentHitPointImpl(0);
        }
        return new CurrentHitPointImpl(damaged);
    }
}
