package pokemonStatus.impl;

import pokemon.PokemonInfo;
import pokemonStatus.CurrentHitPoint;

import static bussinessLogic.ConsoleOutManager.showMessageParChar;

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

    @Override
    public CurrentHitPointImpl recovery(PokemonInfo target, CurrentHitPointImpl currentHitPointImpl) throws InterruptedException {
        if (currentHitPointImpl.value <= MIN) {
            throw new IllegalArgumentException("回復量は1以上を指定してください。");
        }
        final int added = this.value + currentHitPointImpl.value;
        int result = Math.min(added, target.getRealValHitPoint());
        showMessageParChar(target.getBasePrm().getName() + "は体力を" + currentHitPointImpl.value + "回復!");
        return new CurrentHitPointImpl(result);
    }

    @Override
    public CurrentHitPointImpl damage(CurrentHitPointImpl currentHitPointImpl) {
        if (currentHitPointImpl.value <= MIN) {
            throw new IllegalArgumentException("ダメージは1以上を指定してください。");
        }
        final int damaged = this.value - currentHitPointImpl.value;
        if(damaged <= MIN) {
            // TODO 瀕死状態になる
            return new CurrentHitPointImpl(0);
        }
        return new CurrentHitPointImpl(damaged);
    }

    @Override
    public boolean isAlive() {
        return this.value > 0;
    }

    @Override
    public boolean isDead() {
        return this.value == 0;
    }
}
