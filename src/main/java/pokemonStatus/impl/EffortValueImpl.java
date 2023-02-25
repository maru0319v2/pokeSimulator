package pokemonStatus.impl;

import pokemonStatus.EffortValue;

// 努力値を表現するクラス
public class EffortValueImpl implements EffortValue {
    private static final int MIN = 0;
    private static final int MAX_OF_EACH = 255;
    private static final int MAX_OF_TOTAL = 510;
    private final int hitPoint;
    private final int attack;
    private final int block;
    private final int contact;
    private final int defense;
    private final int speed;

    public EffortValueImpl() {
        this.hitPoint = MIN;
        this.attack = MIN;
        this.block = MIN;
        this.contact = MIN;
        this.defense = MIN;
        this.speed = MIN;
    }

    public EffortValueImpl(final int hitPoint, final int attack, final int block, final int contact, final int defense, final int speed) {

        int resultHitPoint;
        if(hitPoint < MIN) {
            resultHitPoint = MIN;
        } else resultHitPoint = Math.min(hitPoint, MAX_OF_EACH);

        int resultAttack;
        if(attack < MIN) {
            resultAttack = MIN;
        } else resultAttack = Math.min(attack, MAX_OF_EACH);

        int resultBlock;
        if(block < MIN) {
            resultBlock = MIN;
        } else resultBlock = Math.min(block, MAX_OF_EACH);

        int resultContact;
        if(contact < MIN) {
            resultContact = MIN;
        } else resultContact = Math.min(contact, MAX_OF_EACH);

        int resultDefense;
        if(defense < MIN) {
            resultDefense = MIN;
        } else resultDefense = Math.min(defense, MAX_OF_EACH);

        int resultSpeed;
        if(speed < MIN) {
            resultSpeed = MIN;
        } else resultSpeed = Math.min(speed, MAX_OF_EACH);

        if(resultHitPoint + resultAttack + resultBlock + resultContact + resultDefense + resultSpeed > MAX_OF_TOTAL) {
            // TODO 合計努力値が510を超えた場合、暫定で努力値加算をしない
            this.hitPoint = hitPoint;
            this.attack = attack;
            this.block = block;
            this.contact = contact;
            this.defense = defense;
            this.speed = speed;
        } else {
            this.hitPoint = resultHitPoint;
            this.attack = resultAttack;
            this.block = resultBlock;
            this.contact = resultContact;
            this.defense = resultDefense;
            this.speed = resultSpeed;
        }
    }

    public EffortValueImpl add(final int hitPoint, final int attack, final int block, final int contact, final int defense, final int speed) {
        int addedHitPoint = this.hitPoint + hitPoint;
        int addedAttack = this.attack + attack;
        int addedBlock = this.block + block;
        int addedContact = this.contact + contact;
        int addedDefense = this.defense + defense;
        int addedSpeed = this.speed + speed;

        return new EffortValueImpl(addedHitPoint, addedAttack, addedBlock, addedContact, addedDefense, addedSpeed);
    }

    public EffortValue reset() {
        return new EffortValueImpl(0, 0, 0, 0, 0, 0);
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
