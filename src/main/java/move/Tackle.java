package move;

import bussinessLogic.*;

public class Tackle implements Move {
    private String name = "たいあたり";
    private final Type moveType = Type.NORMAL;
    private final MoveSpecies moveSpecies = MoveSpecies.PHYSICAL;
    private int damage = 40;
    private int hitRate = 95;

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
}
