package impl;

import bussinessLogic.IndividualValue;

import java.util.Random;

// 個体値を表現するクラス
public class IndividualValueImpl implements IndividualValue {
    private final int hitPoint;
    private final int attack;
    private final int block;
    private final int contact;
    private final int defense;
    private final int speed;

    public IndividualValueImpl(){
        Random rand = new Random();
        this.hitPoint = rand.nextInt(32);
        this.attack = rand.nextInt(32);
        this.block = rand.nextInt(32);
        this.contact = rand.nextInt(32);
        this.defense = rand.nextInt(32);
        this.speed = rand.nextInt(32);
    }

    public IndividualValueImpl(int hitPoint, int attack, int block, int contact, int defense, int speed){
        this.hitPoint = hitPoint;
        this.attack = attack;
        this.block = block;
        this.contact = contact;
        this.defense = defense;
        this.speed = speed;
    }

    public int hitPoint() {
        return this.hitPoint;
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
