package bussinessLogic;

import field.Field;
import lombok.Getter;
import pokemon.PokeInfo;

@Getter
public class OnBattleField {
    private final PokeInfo attackPoke;
    private final PokeInfo defencePoke;
    private final Field field;

    public OnBattleField(PokeInfo attackPoke, PokeInfo defencePoke, Field field) {
        this.attackPoke = attackPoke;
        this.defencePoke = defencePoke;
        this.field = field;
    }

    public boolean isBothFine() {
        return attackPoke.getCurrentHitPoint().isAlive() && defencePoke.getCurrentHitPoint().isAlive();
    }

    public boolean isDeadEither() {
        return attackPoke.getCurrentHitPoint().isDead() || defencePoke.getCurrentHitPoint().isDead();
    }
}
