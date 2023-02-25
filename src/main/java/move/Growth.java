package move;

import bussinessLogic.*;
import lombok.Getter;
import pokemonStatus.CurrentPowerPoint;
import pokemonStatus.impl.CurrentPowerPointImpl;
import Enum.*;
import pokemon.PokemonInfo;

import static bussinessLogic.ConsoleOutManager.showMessageParChar;

@Getter
public class Growth implements Move {
    private final String name = "せいちょう";
    private final Type moveType = Type.NORMAL;
    private final MoveSpecies moveSpecies = MoveSpecies.CHANGE;
    private final int damage = 0;
    private final int hitRate = 100;
    private final int powerPoint = 20;
    private final CurrentPowerPoint currentPowerPoint;

    public Growth() {
        this.currentPowerPoint = new CurrentPowerPointImpl(powerPoint);
    }

    public Growth(CurrentPowerPoint currentHitPoint) {
        this.currentPowerPoint = currentHitPoint;
    }

    public InBattlePokemons effect(PokemonInfo attackPoke, PokemonInfo defensePoke) throws InterruptedException {
        showMessageParChar(attackPoke.getPokeName() + "の特攻が上がった!");
        return new InBattlePokemons(attackPoke.withAddedStatusRank(0,0, 1, 0, 0), defensePoke);
    }
    @Override
    public Move withCurrentPowerPoint(CurrentPowerPoint decrementedPowerPoint) {
        return new Growth(decrementedPowerPoint);
    }
}
