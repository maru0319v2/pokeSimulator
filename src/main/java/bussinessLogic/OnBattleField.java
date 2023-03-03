package bussinessLogic;

import field.Field;
import lombok.Getter;
import pokemon.PokemonInfo;

@Getter
public class OnBattleField {
    PokemonInfo attackPoke;
    PokemonInfo defencePoke;
    Field field;

    public OnBattleField(PokemonInfo attackPoke, PokemonInfo defencePoke, Field field) {
        this.attackPoke = attackPoke;
        this.defencePoke = defencePoke;
        this.field = field;
    }
}
