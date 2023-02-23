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
        int resultAttack;
        if(attack < MIN) {
            resultAttack = MIN;
        } else resultAttack = Math.min(attack, MAX);

        int resultBlock;
        if(block < MIN) {
            resultBlock = MIN;
        } else resultBlock = Math.min(block, MAX);

        int resultContact;
        if(contact < MIN) {
            resultContact = MIN;
        } else resultContact = Math.min(contact, MAX);

        int resultDefense;
        if(defense < MIN) {
            resultDefense = MIN;
        } else resultDefense = Math.min(defense, MAX);

        int resultSpeed;
        if(speed < MIN) {
            resultSpeed = MIN;
        } else resultSpeed = Math.min(speed, MAX);

        this.attack = resultAttack;
        this.block = resultBlock;
        this.contact = resultContact;
        this.defense = resultDefense;
        this.speed = resultSpeed;
    }

    public StatusRankImpl add(final int attack, final int block, final int contact, final int defense, final int speed) {
        int addedAttack = this.attack + attack;
        int addedBlock = this.block + block;
        int addedContact = this.contact + contact;
        int addedDefense = this.defense + defense;
        int addedSpeed = this.speed + speed;

        return new StatusRankImpl(addedAttack, addedBlock, addedContact, addedDefense, addedSpeed);
    }

    public StatusRankImpl reset() {
        return new StatusRankImpl(0, 0, 0, 0, 0);
    }

    public double attackRateByStatusRank() {
        double result;
        if(this.attack >= 0) {
            result = (double)(2 + this.attack) / 2;
        } else {
            result = (2 / (double)(2 + Math.abs(this.attack)));
        }
        return result;
    }

    public double blockRateByStatusRank() {
        double result;
        if(this.block >= 0) {
            result = (double)(2 + this.block) / 2;
        } else {
            result = (2 / (double)(2 + Math.abs(this.block)));
        }
        return result;
    }

    public double contactRateByStatusRank() {
        double result;
        if(this.contact >= 0) {
            result = (double)(2 + this.contact) / 2;
        } else {
            result = (2 / (double)(2 + Math.abs(this.contact)));
        }
        return result;
    }

    public double defenseRateByStatusRank() {
        double result;
        if(this.defense >= 0) {
            result = (double)(2 + this.defense) / 2;
        } else {
            result = (2 / (double)(2 + Math.abs(this.defense)));
        }
        return result;
    }

    public double speedRateByStatusRank() {
        double result;
        if(this.speed >= 0) {
            result = (double)(2 + this.speed) / 2;
        } else {
            result = (2 / (double)(2 + Math.abs(this.speed)));
        }
        return result;
    }
}
