package move;

import bussinessLogic.*;
import lombok.Getter;
import pokemonStatus.CurrentPowerPoint;
import pokemonStatus.impl.CurrentPowerPointImpl;
import Enum.*;
import pokemon.PokemonInfo;
@Getter
public class Tackle implements Move {
    private final String name = "たいあたり";
    private final Type moveType = Type.NORMAL;
    private final MoveSpecies moveSpecies = MoveSpecies.PHYSICAL;
    private final int damage = 40;
    private final int hitRate = 95;
    private final int powerPoint = 35;
    private final CurrentPowerPoint currentPowerPoint;

    public Tackle() {
        this.currentPowerPoint = new CurrentPowerPointImpl(powerPoint);
    }

    public Tackle(CurrentPowerPoint currentHitPoint) {
        this.currentPowerPoint = currentHitPoint;
    }

    public InBattlePokemons effect(PokemonInfo attackPoke, PokemonInfo defensePoke) {
        return new InBattlePokemons(attackPoke, defensePoke);
    }

    @Override
    public Move withCurrentPowerPoint(CurrentPowerPoint decrementedPowerPoint) {
        return new Tackle(decrementedPowerPoint);
    }
}
