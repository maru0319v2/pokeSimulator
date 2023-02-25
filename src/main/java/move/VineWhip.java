package move;

import bussinessLogic.*;
import impl.CurrentPowerPointImpl;
import Enum.*;

public class VineWhip implements Move {
    private String name = "つるのムチ";
    private final Type moveType = Type.GRASS;
    private final MoveSpecies moveSpecies = MoveSpecies.PHYSICAL;
    private int damage = 45;
    private int hitRate = 100;
    private int powerPoint = 25;
    private final CurrentPowerPoint currentPowerPoint;

    public VineWhip() {
        this.currentPowerPoint = new CurrentPowerPointImpl(powerPoint);
    }

    public VineWhip(CurrentPowerPoint currentHitPoint) {
        this.currentPowerPoint = currentHitPoint;
    }

    public String name() {
        return this.name;
    }

    public Type moveType() {
        return this.moveType;
    }

    public MoveSpecies moveSpecies() {
        return this.moveSpecies;
    }

    public int damage() {
        return this.damage;
    }

    public int hitRate() {
        return this.hitRate;
    }
    public InBattlePokemons effect(PokemonInfo attackPoke, PokemonInfo defensePoke) {
        return new InBattlePokemons(attackPoke, defensePoke);
    }
    public int powerPoint() {
        return this.powerPoint;
    }

    public CurrentPowerPoint currentPowerPoint() {
        return this.currentPowerPoint;
    }
    @Override
    public Move withCurrentPowerPoint(CurrentPowerPoint decrementedPowerPoint) {
        return new VineWhip(decrementedPowerPoint);
    }
}
