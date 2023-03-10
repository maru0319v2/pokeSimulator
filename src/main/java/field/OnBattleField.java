package field;

import lombok.Getter;
import pokemon.PokeInfo;

@Getter
public class OnBattleField {
    private final PokeInfo atkPk;
    private final PokeInfo dfcPk;
    private final Field field;

    public OnBattleField(PokeInfo atkPk, PokeInfo dfcPk, Field field) {
        this.atkPk = atkPk;
        this.dfcPk = dfcPk;
        this.field = field;
    }

    public boolean isBothFine() {
        return atkPk.currentHP().isAlive() && dfcPk.currentHP().isAlive();
    }

    public boolean isDeadEither() {
        return atkPk.currentHP().isDead() || dfcPk.currentHP().isDead();
    }
}
