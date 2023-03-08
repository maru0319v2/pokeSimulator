package statusAilment;

import Enum.Type;
import pokemon.PokeInfo;

import java.util.List;
import java.util.Objects;
import java.util.Random;

import static bussinessLogic.ConsoleOutManager.*;

public class AilmentI implements Ailment {
    private final AilmentE val;
    private final int countRecoverySleep;
    private final int elapsedTurn;

    public AilmentE val() {
        return this.val;
    }

    public int countRecoverySleep() {
        return this.countRecoverySleep;
    }

    public int elapsedTurn() {
        return this.elapsedTurn;
    }

    // 状態異常の継続する場合(ねむり経過ターン+1)
    public static Ailment keepAilment(AilmentE value, int countForRecovery, int elapsedTurn) {
        return new AilmentI(value, countForRecovery, elapsedTurn);
    }

    private AilmentI(AilmentE value, int countForRecovery, int elapsedTurn) {
        this.val = value;
        this.countRecoverySleep = countForRecovery;
        this.elapsedTurn = elapsedTurn;
    }

    // 初期化したい場合
    public static Ailment initAilment() {
        return new AilmentI();
    }

    private AilmentI() {
        this.val = AilmentE.FINE;
        this.countRecoverySleep = 0;
        this.elapsedTurn = 0;
    }

    // 状態異常を変化させる場合
    public static Ailment changeAilment(PokeInfo target, AilmentE value) throws InterruptedException {
        Type type1 = target.basePrm().type1();
        Type type2 = target.basePrm().type2();
        List<Type> typeList = List.of(type1, type2);
        // こおりタイプはこおり状態にならない
        if (typeList.contains(Type.ICE) && value == AilmentE.FREEZE) {
            showMessageParChar(target.basePrm().pName() + "にはきかなかった!");
            return target.ailment();
        }
        // ほのおタイプはやけど状態にならない
        if (typeList.contains(Type.FIRE) && value == AilmentE.BURN) {
            showMessageParChar(target.basePrm().pName() + "にはきかなかった!");
            return target.ailment();
        }
        // どくタイプ、はがねタイプはどく、もうどく状態にならない
        if (value == AilmentE.BAD_POISON || value == AilmentE.POISON) {
            if (typeList.contains(Type.POISON) || typeList.contains(Type.STEEL)) {
                showMessageParChar(target.basePrm().pName() + "にはきかなかった!");
                return target.ailment();
            }
        }
        return new AilmentI(target, value);
    }

    private AilmentI(PokeInfo target, AilmentE value) throws InterruptedException {

        if (isOverwrite(target, value)) {
            int count = 0;
            if (value == AilmentE.SLEEP) {
                // 2~5ターン
                count = (new Random().nextInt(4) + 2);
            }
            this.val = value;
            this.countRecoverySleep = count;
            this.elapsedTurn = 0;
            showChangeAilmentMessage(target, value);
        } else {
            this.val = target.ailment().val();
            this.countRecoverySleep = target.ailment().countRecoverySleep();
            this.elapsedTurn = target.ailment().elapsedTurn();
        }
    }

    private boolean isOverwrite(PokeInfo target, AilmentE value) throws InterruptedException {
        // 元の状態異常
        AilmentE beforeAilment = target.ailment().val();

        if (beforeAilment == AilmentE.FINE) {
            // 元の状態が健康ならすべて上書きされる
            return true;
        }

        if (beforeAilment == AilmentE.FAINTING) {
            // 元の状態が瀕死なら健康でしか上書きできない
            if (value == AilmentE.FINE) {
                return true;
            }
        }
        if (beforeAilment == AilmentE.SLEEP) {
            // 元の状態がねむりの場合、引数の経過ターンが大きい、またはひんしか健康の場合上書きする
            if (target.ailment().elapsedTurn() < this.elapsedTurn() || value == AilmentE.FINE || value == AilmentE.FAINTING) {
                return true;
            }
        }
        if (beforeAilment == AilmentE.PARALYSIS || beforeAilment == AilmentE.POISON || beforeAilment == AilmentE.BAD_POISON || beforeAilment == AilmentE.BURN || beforeAilment == AilmentE.FREEZE) {
            // 元の状態がまひ、どく、もうどく、やけど、こおりなら健康と瀕死でしか上書きできない
            if (value == AilmentE.FINE || value == AilmentE.FAINTING) {
                return true;
            }
            showAlreadyAilmentMessage(target);
        }
        // それ以外の場合は更新しない
        return false;
    }

    public Ailment comeTurn(String pokeName) throws InterruptedException {

        // ねむりの場合、経過ターンを1増やしカウンタを同じになればねむりを解く
        if (this.val == AilmentE.SLEEP) {
            if (this.countRecoverySleep <= this.elapsedTurn + 1) {
                showMessageParChar(pokeName + "はめをさました!");
                return initAilment();
            }
            return keepAilment(this.val, this.countRecoverySleep, this.elapsedTurn + 1);
        }
        // こおりの場合、20%の確率でこおりを解く
        if (this.val == AilmentE.FREEZE) {
            if ((new Random().nextInt(5)) == 0) {
                showMessageParChar(pokeName + "のこおりがとけた!");
                return initAilment();
            }
        }
        // その他の状態異常の場合、継続
        return keepAilment(this.val, this.countRecoverySleep, this.elapsedTurn);
    }

    public boolean canMove(String pokeName) throws InterruptedException {
        if (this.val == AilmentE.SLEEP) {
            showMessageParChar(pokeName + "はぐうぐうねむっている・・・");
            return false;
        }
        if (this.val == AilmentE.FREEZE) {
            showMessageParChar(pokeName + "はこおってしまってうごけない！");
            return false;
        }
        if (this.val == AilmentE.PARALYSIS && (new Random().nextInt(4)) == 0) {
            showMessageParChar(pokeName + "はしびれてうごけない!");
            return false;
        }
        return true;
    }

    public boolean isFine() {
        return this.val == AilmentE.FINE;
    }

    public boolean isSick() {
        return this.val != AilmentE.FINE;
    }

    public double dmgRateByBurn() {
        return Objects.equals(this.val, AilmentE.BURN) ? 0.5 : 1.0;
    }

    public double spdRateByParalysis() {
        return Objects.equals(this.val, AilmentE.PARALYSIS) ? 0.5 : 1.0;
    }

    public PokeInfo slipDmgByAilment(PokeInfo target) throws InterruptedException {
        AilmentE ailment = target.ailment().val();
        int damage;
        switch (ailment) {
            case POISON -> {
                showMessageParChar(target.basePrm().pName() + "はどくでダメージをうけた！");
                damage = target.realHP() / 8;
            }
            case BAD_POISON -> {
                showMessageParChar(target.basePrm().pName() + "はどくでダメージをうけた！");
                int rate = elapsedTurn / 16;
                damage = target.realHP() / rate;
            }
            case BURN -> {
                showMessageParChar(target.basePrm().pName() + "はやけどでダメージをうけた！");
                damage = target.realHP() / 8;
            }
            default -> {
                return target;
            }
        }
        Thread.sleep(500);
        return target.damage(damage);
    }
}
