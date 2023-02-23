package move;

import bussinessLogic.Move;
import bussinessLogic.MoveSpecies;
import bussinessLogic.PokemonInfo;
import bussinessLogic.Type;

public class Growl implements Move {
    private String name = "なきごえ";
    private final Type moveType = Type.NORMAL;
    private final MoveSpecies moveSpecies = MoveSpecies.CHANGE;
    private int damage = 0;
    private int hitRate = 100;

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


    public void effect(PokemonInfo attackPoke, PokemonInfo defensePoke) {

    }
}
