package pokemonStatus.impl;

import lombok.AllArgsConstructor;
import pokemon.PokeInfo;
import pokemonStatus.CurrentHP;

import static bussinessLogic.ConsoleOutManager.showMessageParChar;

@AllArgsConstructor
public class CurrentHPI implements CurrentHP {
    private static final int MIN = 0;
    private final int val;

    public int val() {
        return this.val;
    }

    @Override
    public CurrentHPI recovery(PokeInfo target, CurrentHP recoveryVal) throws InterruptedException {
        if (recoveryVal.val() <= MIN) {
            throw new IllegalArgumentException("回復量は1以上を指定してください。");
        }
        final int added = this.val + recoveryVal.val();
        int result = Math.min(added, target.realHP());
        int diff = result - this.val;
        if (this.val == result) {
            showMessageParChar("しかし" + target.basePrm().pName() + "はたいりょくはまんたんだ！");
        } else {
            showMessageParChar(target.basePrm().pName() + "は体力を" + diff + "回復!");
        }
        return new CurrentHPI(result);
    }

    @Override
    public CurrentHPI damage(CurrentHP currentHP) {
        if (currentHP.val() <= MIN) {
            throw new IllegalArgumentException("ダメージは1以上を指定してください。");
        }
        final int damaged = this.val - currentHP.val();
        if (damaged <= MIN) {
            return new CurrentHPI(0);
        }
        return new CurrentHPI(damaged);
    }

    @Override
    public boolean isAlive() {
        return this.val > 0;
    }

    @Override
    public boolean isDead() {
        return this.val == 0;
    }
}
