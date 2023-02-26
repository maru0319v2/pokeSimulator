package bussinessLogic;

import pokemon.PokemonInfo;

public class InBattlePokemons {
    PokemonInfo attackPoke;
    PokemonInfo defencePoke;

    public InBattlePokemons(PokemonInfo attackPoke, PokemonInfo defencePoke) {
        this.attackPoke = attackPoke;
        this.defencePoke = defencePoke;
    }
}
