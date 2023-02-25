package move;

import bussinessLogic.*;
import impl.CurrentPowerPointImpl;
import Enum.*;

public class Tackle implements Move {
    private String name = "たいあたり";
    private final Type moveType = Type.NORMAL;
    private final MoveSpecies moveSpecies = MoveSpecies.PHYSICAL;
    private int damage = 40;
    private int hitRate = 95;
    private int powerPoint = 35;
    private final CurrentPowerPoint currentPowerPoint;

    public Tackle() {
        this.currentPowerPoint = new CurrentPowerPointImpl(powerPoint);
    }

    public Tackle(CurrentPowerPoint currentHitPoint) {
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
        return new Tackle(decrementedPowerPoint);
    }
}
