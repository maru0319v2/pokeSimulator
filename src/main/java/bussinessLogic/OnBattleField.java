package bussinessLogic;

import field.Field;
import lombok.Getter;
import pokemon.PokemonInfo;

@Getter
public class OnBattleField {
    private final PokemonInfo attackPoke;
    private final PokemonInfo defencePoke;
    private final Field field;

    public OnBattleField(PokemonInfo attackPoke, PokemonInfo defencePoke, Field field) {
        this.attackPoke = attackPoke;
        this.defencePoke = defencePoke;
        this.field = field;
    }

    public boolean isBothFine() {
        return attackPoke.getCurrentHitPoint().isAlive() && defencePoke.getCurrentHitPoint().isAlive();
    }
}