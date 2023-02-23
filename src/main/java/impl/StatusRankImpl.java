package impl;

import bussinessLogic.StatusRank;

public class StatusRankImpl implements StatusRank {
    private static final int MIN = -6;
    private static final int MAX = 6;
    private final int attack;
    private final int block;
    private final int contact;
    private final int defense;
    private final int speed;

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

    public StatusRankImpl() {
        this.attack = 0;
        this.block = 0;
        this.contact = 0;
        this.defense = 0;
        this.speed = 0;
    }

    public StatusRankImpl(int attack, int block, int contact, int defense, int speed) {
        if(attack < MIN || attack > MAX) throw new IllegalArgumentException("攻撃ランクは" + MIN + "以上 " + MAX +"以下を指定してください。");
        if(block < MIN || block > MAX) throw new IllegalArgumentException("防御ランクは" + MIN + "以上 " + MAX +"以下を指定してください。");
        if(contact < MIN || contact > MAX) throw new IllegalArgumentException("特攻ランクは" + MIN + "以上 " + MAX +"以下を指定してください。");
        if(defense < MIN || defense > MAX) throw new IllegalArgumentException("特防ランクは" + MIN + "以上 " + MAX +"以下を指定してください。");
        if(speed < MIN || speed > MAX) throw new IllegalArgumentException("素早ランクは" + MIN + "以上 " + MAX +"以下を指定してください。");

        this.attack = attack;
        this.block = block;
        this.contact = contact;
        this.defense = defense;
        this.speed = speed;
    }
}
