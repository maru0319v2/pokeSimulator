package main.java.org.example.move;

import main.java.org.example.Move;
import main.java.org.example.MoveSpecies;
import main.java.org.example.Type;

public class VineWhip implements Move {
    private String name = "つるのムチ";
    private final Type moveType = Type.GRASS;
    private final MoveSpecies moveSpecies = MoveSpecies.PHYSICAL;
    private int damage = 45;
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
}
