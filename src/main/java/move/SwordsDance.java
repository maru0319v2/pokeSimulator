package move;

import bussinessLogic.*;
import lombok.Getter;
import pokemonStatus.CurrentPowerPoint;
import pokemonStatus.impl.CurrentPowerPointImpl;
import Enum.*;
import pokemon.PokemonInfo;

import static bussinessLogic.ConsoleOutManager.showMessageParChar;
@Getter
public class SwordsDance implements Move {
    private final String name = "つるぎのまい";
    private final Type moveType = Type.NORMAL;
    private final MoveSpecies moveSpecies = MoveSpecies.CHANGE;
    private final int damage = 0;
    private final int hitRate = 100;
    private final int powerPoint = 20;
    private final CurrentPowerPoint currentPowerPoint;

    public SwordsDance() {
        this.currentPowerPoint = new CurrentPowerPointImpl(powerPoint);
    }

    public SwordsDance(CurrentPowerPoint currentHitPoint) {
        this.currentPowerPoint = currentHitPoint;
    }

    public InBattlePokemons effect(PokemonInfo attackPoke, PokemonInfo defensePoke) throws InterruptedException {
        showMessageParChar(attackPoke.pokeName() + "の攻撃がぐーんと上がった!");
        return new InBattlePokemons(attackPoke.withAddedStatusRank(2,0, 0, 0, 0), defensePoke);
    }
    @Override
    public Move withCurrentPowerPoint(CurrentPowerPoint decrementedPowerPoint) {
        return new SwordsDance(decrementedPowerPoint);
    }
}
