package main.java.org.example.move;

import main.java.org.example.Move;
import main.java.org.example.MoveType;

public class Tackle implements Move {
    private String value = "たいあたり";
    private MoveType moveType = MoveType.NORMAL;
    private String moveSpecies = "Physical";
    private int damage = 40;
    private int hitRate = 95;

    public String value() {
        return this.value;
    }

    public int damage() {
        return this.damage;
    }

    public int hitRate() {
        return this.hitRate;
    }

    public MoveType moveType() {
        return this.moveType;
    }

    public String moveSpecies() {
        return this.moveSpecies;
    }
}
