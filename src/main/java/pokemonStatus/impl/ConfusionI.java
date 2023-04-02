package pokemonStatus.impl;

import pokemon.PokeInfo;
import pokemonStatus.Confusion;

import java.util.Random;

import static bussinessLogic.ConsoleOutManager.showMessageParChar;

public class ConfusionI implements Confusion {
    // 混乱ありtrue,混乱なしfalse
    private final boolean val;
    private final int countRecovery;
    private final int elapsedTurn;

    @Override
    public boolean val() {
        return this.val;
    }

    @Override
    public int countRecovery() {
        return this.countRecovery;
    }

    @Override
    public int elapsedTurn() {
        return this.elapsedTurn;
    }

    // 初期化のときに使う
    public static Confusion init() {
        return new ConfusionI(false);
    }

    // 混乱状態にするときに使う
    public static Confusion beConfusion(PokeInfo target) {
        if (target.confusion().val()) {
            showMessageParChar(target.basePrm().pName() + "はすでにこんらんしている!");
            return target.confusion();
        }
        showMessageParChar(target.basePrm().pName() + "はこんらんした!");
        return new ConfusionI(true);
    }

    private ConfusionI(boolean val) {
        int countRecovery = 0;
        if (val) {
            // 2~5ターン
            countRecovery = (new Random().nextInt(4) + 2);
        }
        this.val = val;
        this.countRecovery = countRecovery;
        this.elapsedTurn = 0;
    }


    public ConfusionI(boolean value, int countRecovery, int elapsedTurn) {
        this.val = value;
        this.countRecovery = countRecovery;
        this.elapsedTurn = elapsedTurn;
    }

    public Confusion elapseTurn(String name) {
        if (this.val && this.countRecovery <= this.elapsedTurn + 1) {
            showMessageParChar(name + "のこんらんがとけた!");
            try {
                Thread.sleep(200);
            } catch (InterruptedException ignored) {
            }
            return init();
        }
        return new ConfusionI(this.val, this.countRecovery, this.elapsedTurn + 1);
    }

    public boolean canMove(String name) {
        if (this.val) {
            showMessageParChar(name + "はこんらんしている!");
            try {
                Thread.sleep(200);
            } catch (InterruptedException ignored) {
            }
            return (new Random().nextInt(2)) != 0;
        }
        return true;
    }

    public PokeInfo damageMe(PokeInfo target) {
        showMessageParChar(target.basePrm().pName() + "はわけもわからずじぶんをこうげきした!");
        try {
            Thread.sleep(200);
        } catch (InterruptedException ignored) {
        }
        return target.damage(calcDamage(target));
    }

    // 自傷ダメージの計算
    private static int calcDamage(PokeInfo pk) {
        // ダメージ計算参考　https://wiki.xn--rckteqa2e.com/wiki/%E3%81%93%E3%82%93%E3%82%89%E3%82%93#.E3.81.93.E3.82.93.E3.82.89.E3.82.93.E3.81.AE.E3.83.80.E3.83.A1.E3.83.BC.E3.82.B8
        // 攻撃側のレベル
        int lv = pk.level().val();
        // 技の威力
        int moveDamage = 40;
        // ダメージの乱数
        double randomNum = (new Random().nextInt((100 - 85) + 1) + 85) / 100.0;
        // 急所の場合は攻撃側のランク下降、防御側のランク上昇補正を無視する
        double attackRateByStatusRank = pk.statusRank().atkRateByStatusRank();
        double blockRateByStatusRank = pk.statusRank().blcRateByStatusRank();
        // ステータス実数値にランク補正を乗せる
        int attackVal = (int) (pk.realAtk() * attackRateByStatusRank);
        int defenceVal = (int) (pk.realBlk() * blockRateByStatusRank);

        return (int) Math.floor(Math.floor(Math.floor(Math.floor(lv * 2 / 5 + 2) * moveDamage * attackVal / defenceVal) / 50 + 2) * randomNum);
    }
}
