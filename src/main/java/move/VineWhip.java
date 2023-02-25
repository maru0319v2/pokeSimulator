package move;

import bussinessLogic.*;
import lombok.Getter;
import pokemonStatus.CurrentPowerPoint;
import pokemonStatus.impl.CurrentPowerPointImpl;
import Enum.*;
import pokemon.PokemonInfo;
@Getter
public class VineWhip implements Move {
    private final String name = "つるのムチ";
    private final Type moveType = Type.GRASS;
    private final MoveSpecies moveSpecies = MoveSpecies.PHYSICAL;
    private final int damage = 45;
    private final int hitRate = 100;
    private final int powerPoint = 25;
    private final CurrentPowerPoint currentPowerPoint;

    public VineWhip() {
        this.currentPowerPoint = new CurrentPowerPointImpl(powerPoint);
    }

    public VineWhip(CurrentPowerPoint currentHitPoint) {
        this.currentPowerPoint = currentHitPoint;
    }

    public InBattlePokemons effect(PokemonInfo attackPoke, PokemonInfo defensePoke) {
        return new InBattlePokemons(attackPoke, defensePoke);
    }

    @Override
    public Move withCurrentPowerPoint(CurrentPowerPoint decrementedPowerPoint) {
        return new VineWhip(decrementedPowerPoint);
    }
}
