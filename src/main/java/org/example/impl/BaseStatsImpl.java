package main.java.org.example.impl;

import main.java.org.example.BaseStats;

// 種族値を表現するクラス
public class BaseStatsImpl implements BaseStats {
    private static final int MIN = 1;
    private static final int MAX = 255;
    public final int hitPoint;
    public final int attack;
    public final int block;
    public final int contact;
    public final int defense;
    public final int speed;

    public BaseStatsImpl(int hitPoint, int attack, int block, int contact, int defense, int speed) {
        if(hitPoint < MIN || hitPoint > MAX) throw new IllegalArgumentException("HP種族値は" + MIN + "以上" + MAX + "以下を指定してください。");
        if(attack < MIN || attack > MAX) throw new IllegalArgumentException("攻撃種族値は" + MIN + "以上" + MAX + "以下を指定してください。");
        if(block < MIN || block > MAX) throw new IllegalArgumentException("防御種族値は" + MIN + "以上" + MAX + "以下を指定してください。");
        if(contact < MIN || contact > MAX) throw new IllegalArgumentException("特攻種族値は" + MIN + "以上" + MAX + "以下を指定してください。");
        if(defense < MIN || defense > MAX) throw new IllegalArgumentException("特防種族値は" + MIN + "以上" + MAX + "以下を指定してください。");
        if(speed < MIN || speed > MAX) throw new IllegalArgumentException("素早さ種族値は" + MIN + "以上" + MAX + "以下を指定してください。");

        this.hitPoint = hitPoint;
        this.attack = attack;
        this.block = block;
        this.contact = contact;
        this.defense = defense;
        this.speed = speed;
    }
}
