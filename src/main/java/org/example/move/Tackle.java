package main.java.org.example.move;

import main.java.org.example.Move;
import main.java.org.example.MoveSpecies;
import main.java.org.example.MoveType;

public class Tackle implements Move {
    private String name = "たいあたり";
    private final MoveType moveType = MoveType.NORMAL;
    private final MoveSpecies moveSpecies = MoveSpecies.PHYSICAL;
    private int damage = 40;
    private int hitRate = 95;

    public String name() {
        return this.name;
    }

    public MoveType moveType() {
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
