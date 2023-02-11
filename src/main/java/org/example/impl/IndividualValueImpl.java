package main.java.org.example.impl;

import main.java.org.example.IndividualValue;

import java.util.Random;

// 個体値を表現するクラス
public class IndividualValueImpl implements IndividualValue {
    public final int hitPoint;
    public final int attack;
    public final int block;
    public final int contact;
    public final int defense;
    public final int speed;

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
}
