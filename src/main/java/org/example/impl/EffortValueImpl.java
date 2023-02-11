package main.java.org.example.impl;

import main.java.org.example.EffortValue;

// 努力値を表現するクラス
public class EffortValueImpl implements EffortValue {
    private static final int MIN = 0;
    private static final int MAX_OF_EACH = 255;
    private static final int MAX_OF_TOTAL = 510;
    public final int hitPoint;
    public final int attack;
    public final int block;
    public final int contact;
    public final int defense;
    public final int speed;

    public EffortValueImpl() {
        this.hitPoint = MIN;
        this.attack = MIN;
        this.block = MIN;
        this.contact = MIN;
        this.defense = MIN;
        this.speed = MIN;
    }

    public EffortValueImpl(final int hitPoint, final int attack, final int block, final int contact, final int defense, final int speed) {
        if(hitPoint < MIN) throw new IllegalArgumentException("HP努力値は" + MIN + "以上を指定してください。");
        if(attack < MIN) throw new IllegalArgumentException("攻撃努力値は" + MIN + "以上を指定してください。");
        if(block < MIN) throw new IllegalArgumentException("防御努力値は" + MIN + "以上を指定してください。");
        if(contact < MIN) throw new IllegalArgumentException("特攻努力値は" + MIN + "以上を指定してください。");
        if(defense < MIN) throw new IllegalArgumentException("特防努力値は" + MIN + "以上を指定してください。");
        if(speed < MIN) throw new IllegalArgumentException("素早さ努力値は" + MIN + "以上を指定してください。");

        int addHitPoint = Math.min(hitPoint, MAX_OF_EACH);
        int addAttack = Math.min(attack, MAX_OF_EACH);
        int addBlock = Math.min(block, MAX_OF_EACH);
        int addContact = Math.min(contact, MAX_OF_EACH);
        int addDefense = Math.min(defense, MAX_OF_EACH);
        int addSpeed = Math.min(speed, MAX_OF_EACH);

        if(addHitPoint + addAttack + addBlock + addContact + addDefense + addSpeed > MAX_OF_TOTAL) {
            // TODO 合計努力値が510を超えた場合、暫定で努力値加算をしない
            this.hitPoint = hitPoint;
            this.attack = attack;
            this.block = block;
            this.contact = contact;
            this.defense = defense;
            this.speed = speed;
        } else {
            this.hitPoint = addHitPoint;
            this.attack = addAttack;
            this.block = addBlock;
            this.contact = addContact;
            this.defense = addDefense;
            this.speed = addSpeed;
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
}
