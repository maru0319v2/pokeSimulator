package pokemonStatus.impl;

import pokemonStatus.IndividualValue;

import java.util.Random;

// 個体値を表現するクラス
public class IndividualValueI implements IndividualValue {
    private final int hp;
    private final int attack;
    private final int block;
    private final int contact;
    private final int defence;
    private final int speed;

    public static IndividualValue initIndividualValue() {
        return new IndividualValueI();
    }

    private IndividualValueI() {
        Random r = new Random();
        this.hp = r.nextInt(32);
        this.attack = r.nextInt(32);
        this.block = r.nextInt(32);
        this.contact = r.nextInt(32);
        this.defence = r.nextInt(32);
        this.speed = r.nextInt(32);
    }

    public IndividualValueI(int hp, int attack, int block, int contact, int defence, int speed) {
        this.hp = hp;
        this.attack = attack;
        this.block = block;
        this.contact = contact;
        this.defence = defence;
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

    public int defence() {
        return this.defence;
    }

    public int speed() {
        return this.speed;
    }
}
