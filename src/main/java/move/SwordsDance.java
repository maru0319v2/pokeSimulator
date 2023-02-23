package move;

import bussinessLogic.*;

import static bussinessLogic.ConsoleOutManager.showMessageParChar;

public class SwordsDance implements Move {
    private String name = "つるぎのまい";
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

    public InBattlePokemons effect(PokemonInfo attackPoke, PokemonInfo defensePoke) throws InterruptedException {
        showMessageParChar(attackPoke.pokeName() + "の攻撃がぐーんと上がった!");
        return new InBattlePokemons(attackPoke.withAddedStatusRank(2,0, 0, 0, 0), defensePoke);
    }
}
