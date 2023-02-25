package move;

import bussinessLogic.*;
import pokemonStatus.CurrentPowerPoint;
import pokemonStatus.impl.CurrentPowerPointImpl;
import Enum.*;
import pokemon.PokemonInfo;
import lombok.Getter;

import static bussinessLogic.ConsoleOutManager.showMessageParChar;

@Getter
public class Growl implements Move {
    private final String name = "なきごえ";
    private final Type moveType = Type.NORMAL;
    private final MoveSpecies moveSpecies = MoveSpecies.CHANGE;
    private final int damage = 0;
    private final int hitRate = 100;
    private final int powerPoint = 20;
    private final CurrentPowerPoint currentPowerPoint;

    public Growl() {
        this.currentPowerPoint = new CurrentPowerPointImpl(powerPoint);
    }
    public Growl(CurrentPowerPoint currentHitPoint) {
        this.currentPowerPoint = currentHitPoint;
    }

    public InBattlePokemons effect(PokemonInfo attackPoke, PokemonInfo defensePoke) throws InterruptedException {
        showMessageParChar(defensePoke.pokeName() + "の攻撃が下がった!");
        return new InBattlePokemons(attackPoke, defensePoke.withAddedStatusRank(-1,0, 0, 0, 0));
    }

    @Override
    public Move withCurrentPowerPoint(CurrentPowerPoint decrementedPowerPoint) {
        return new Growl(decrementedPowerPoint);
    }
}
