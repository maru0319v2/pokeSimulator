package pokemonStatus.impl;

import pokemon.PokeInfo;
import pokemonStatus.StatusRank;

public class StatusRankI implements StatusRank {
    private static final int MIN = -6;
    private static final int MAX = 6;
    private final int attack;
    private final int block;
    private final int contact;
    private final int defense;
    private final int speed;
    private final int hitRate;
    private final int avoidRate;

    public static StatusRank initStatusRank() {
        return new StatusRankI();
    }

    private StatusRankI() {
        this.attack = 0;
        this.block = 0;
        this.contact = 0;
        this.defense = 0;
        this.speed = 0;
        this.hitRate = 0;
        this.avoidRate = 0;
    }

    public StatusRankI(int attack, int block, int contact, int defense, int speed, int hitRate, int avoidRate) {
        int resultAttack;
        if (attack < MIN) {
            resultAttack = MIN;
        } else resultAttack = Math.min(attack, MAX);

        int resultBlock;
        if (block < MIN) {
            resultBlock = MIN;
        } else resultBlock = Math.min(block, MAX);

        int resultContact;
        if (contact < MIN) {
            resultContact = MIN;
        } else resultContact = Math.min(contact, MAX);

        int resultDefense;
        if (defense < MIN) {
            resultDefense = MIN;
        } else resultDefense = Math.min(defense, MAX);

        int resultSpeed;
        if (speed < MIN) {
            resultSpeed = MIN;
        } else resultSpeed = Math.min(speed, MAX);

        int resultHitRate;
        if (hitRate < MIN) {
            resultHitRate = MIN;
        } else resultHitRate = Math.min(hitRate, MAX);

        int resultAvoidRate;
        if (avoidRate < MIN) {
            resultAvoidRate = MIN;
        } else resultAvoidRate = Math.min(avoidRate, MAX);

        this.attack = resultAttack;
        this.block = resultBlock;
        this.contact = resultContact;
        this.defense = resultDefense;
        this.speed = resultSpeed;
        this.hitRate = resultHitRate;
        this.avoidRate = resultAvoidRate;
    }

    public StatusRankI add(final int attack, final int block, final int contact, final int defense, final int speed, final int hitRate, final int avoidRate) {
        int addedAttack = this.attack + attack;
        int addedBlock = this.block + block;
        int addedContact = this.contact + contact;
        int addedDefense = this.defense + defense;
        int addedSpeed = this.speed + speed;
        int addedHitRate = this.hitRate + hitRate;
        int addedAvoidRate = this.avoidRate + avoidRate;

        return new StatusRankI(addedAttack, addedBlock, addedContact, addedDefense, addedSpeed, addedHitRate, addedAvoidRate);
    }

    public StatusRankI reset() {
        return new StatusRankI(0, 0, 0, 0, 0, 0, 0);
    }

    public double attackRateByStatusRank() {
        double result;
        if (this.attack >= 0) {
            result = (double) (2 + this.attack) / 2;
        } else {
            result = (2 / (double) (2 + Math.abs(this.attack)));
        }
        return result;
    }

    public double blockRateByStatusRank() {
        double result;
        if (this.block >= 0) {
            result = (double) (2 + this.block) / 2;
        } else {
            result = (2 / (double) (2 + Math.abs(this.block)));
        }
        return result;
    }

    public double contactRateByStatusRank() {
        double result;
        if (this.contact >= 0) {
            result = (double) (2 + this.contact) / 2;
        } else {
            result = (2 / (double) (2 + Math.abs(this.contact)));
        }
        return result;
    }

    public double defenseRateByStatusRank() {
        double result;
        if (this.defense >= 0) {
            result = (double) (2 + this.defense) / 2;
        } else {
            result = (2 / (double) (2 + Math.abs(this.defense)));
        }
        return result;
    }

    public double speedRateByStatusRank() {
        double result;
        if (this.speed >= 0) {
            result = (double) (2 + this.speed) / 2;
        } else {
            result = (2 / (double) (2 + Math.abs(this.speed)));
        }
        return result;
    }

    public double hitRateByStatusRank(PokeInfo defencePoke) {
        double result;
        int avoidRate = defencePoke.statusRank().avoidRate();
        int total = this.hitRate - avoidRate;
        if (total >= 0) {
            result = (double) (3 + total) / 3;
        } else {
            result = (3 / (double) (3 + Math.abs(total)));
        }
        return result;
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

    public int hitRate() {
        return this.hitRate;
    }

    public int avoidRate() {
        return this.avoidRate;
    }
}
