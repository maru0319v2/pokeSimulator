package pokemonStatus.impl;

import pokemonStatus.IndividualValue;

import java.util.Random;

// 個体値を表現するクラス
public class IndividualValueI implements IndividualValue {
    private final int hp;
    private final int attack;
    private final int block;
    private final int contact;
    private final int defense;
    private final int speed;

    public static IndividualValue initializeIndividualValue() {
        return new IndividualValueI();
    }

    private IndividualValueI() {
        Random r = new Random();
        this.hp = r.nextInt(32);
        this.attack = r.nextInt(32);
        this.block = r.nextInt(32);
        this.contact = r.nextInt(32);
        this.defense = r.nextInt(32);
        this.speed = r.nextInt(32);
    }

    public IndividualValueI(int hp, int attack, int block, int contact, int defense, int speed) {
        this.hp = hp;
        this.attack = attack;
        this.block = block;
        this.contact = contact;
        this.defense = defense;
        this.speed = speed;
    }

    public int hp() {
        return this.hp;
    }

    public int attack() {
        return this.attack;
    }

    public int block() {
        return this.block;
    }

    public int contact() {
        return this.contact;
    }

    public int defense() {
        return this.defense;
    }

    public int speed() {
        return this.speed;
    }
}
