package statusAilment;

import Enum.Type;
import pokemon.PokeInfo;

import java.util.List;
import java.util.Objects;
import java.util.Random;

import static bussinessLogic.ConsoleOutManager.*;
import static statusAilment.AilmentEnum.*;

public class AilmentI implements Ailment {
    private final AilmentEnum val;
    private final int countRecoverySleep;
    private final int elapsedTurn;

    public AilmentEnum val() {
        return this.val;
    }

    public int countRecoverySleep() {
        return this.countRecoverySleep;
    }

    public int elapsedTurn() {
        return this.elapsedTurn;
    }

    // 状態異常の継続する場合(ねむり経過ターン+1)
    public static Ailment keepAilment(AilmentEnum value, int countForRecovery, int elapsedTurn) {
        return new AilmentI(value, countForRecovery, elapsedTurn);
    }

    private AilmentI(AilmentEnum value, int countForRecovery, int elapsedTurn) {
        this.val = value;
        this.countRecoverySleep = countForRecovery;
        this.elapsedTurn = elapsedTurn;
    }

    // 初期化したい場合
    public static Ailment init() {
        return new AilmentI();
    }

    private AilmentI() {
        this.val = FINE;
        this.countRecoverySleep = 0;
        this.elapsedTurn = 0;
    }

    // 状態異常を変化させる場合
    public static Ailment changeAilment(PokeInfo target, AilmentEnum value) {
        Type type1 = target.basePrm().type1();
        Type type2 = target.basePrm().type2();
        List<Type> typeList = List.of(type1, type2);
        // こおりタイプはこおり状態にならない
        if (typeList.contains(Type.ICE) && value == FREEZE) {
            showMessageParChar(target.basePrm().pName() + "にはきかなかった!");
            return target.ailment();
        }
        // ほのおタイプはやけど状態にならない
        if (typeList.contains(Type.FIRE) && value == BURN) {
            showMessageParChar(target.basePrm().pName() + "にはきかなかった!");
            return target.ailment();
        }
        // どくタイプ、はがねタイプはどく、もうどく状態にならない
        if (value == BAD_POISON || value == POISON) {
            if (typeList.contains(Type.POISON) || typeList.contains(Type.STEEL)) {
                showMessageParChar(target.basePrm().pName() + "にはきかなかった!");
                return target.ailment();
            }
        }
        return new AilmentI(target, value);
    }

    private AilmentI(PokeInfo target, AilmentEnum value) {

        if (isOverwrite(target, value)) {
            int count = 0;
            if (value == SLEEP) {
                // 2~5ターン
                count = (new Random().nextInt(4) + 2);
            }
            if (value == SELF_SLEEP) {
                // 3ターン
                count = 3;
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

    private boolean isOverwrite(PokeInfo target, AilmentEnum value) {
        // 元の状態異常
        AilmentEnum beforeAilment = target.ailment().val();

        if (beforeAilment == FINE) {
            // 元の状態が健康ならすべて上書きされる
            return true;
        }

        if (beforeAilment == FAINTING) {
            // 元の状態が瀕死なら健康でしか上書きできない
            if (value == FINE) {
                return true;
            }
        }
        if (beforeAilment == SLEEP || beforeAilment == SELF_SLEEP) {
            // 元の状態がねむりの場合、引数の経過ターンが大きい、またはひんしか健康の場合上書きする
            if (target.ailment().elapsedTurn() < this.elapsedTurn() || value == FINE || value == FAINTING) {
                return true;
            }
        }
        if (beforeAilment == PARALYSIS || beforeAilment == POISON || beforeAilment == BAD_POISON || beforeAilment == BURN || beforeAilment == FREEZE) {
            // 元の状態がまひ、どく、もうどく、やけど、こおりなら健康と瀕死でしか上書きできない
            if (value == FINE || value == FAINTING || value == SELF_SLEEP) {
                return true;
            }
            showAlreadyAilmentMessage(target);
        }
        // それ以外の場合は更新しない
        return false;
    }

    public Ailment comeTurn(String pokeName) {

        // ねむりの場合、経過ターンを1増やしカウンタを同じになればねむりを解く
        if (this.val == SLEEP || this.val == SELF_SLEEP) {
            if (this.countRecoverySleep <= this.elapsedTurn + 1) {
                showMessageParChar(pokeName + "はめをさました!");
                return init();
            }
            return keepAilment(this.val, this.countRecoverySleep, this.elapsedTurn + 1);
        }
        // こおりの場合、20%の確率でこおりを解く
        if (this.val == FREEZE) {
            if ((new Random().nextInt(5)) == 0) {
                showMessageParChar(pokeName + "のこおりがとけた!");
                return init();
            }
        }
        // もうどくの場合、経過ターン+1
        if (this.val == BAD_POISON) {
            return keepAilment(this.val, this.countRecoverySleep, this.elapsedTurn + 1);
        }
        // その他の状態異常の場合、継続
        return keepAilment(this.val, this.countRecoverySleep, this.elapsedTurn);
    }

    public boolean canMove(String pokeName) {
        if (this.val == SLEEP || this.val == SELF_SLEEP) {
            showMessageParChar(pokeName + "はぐうぐうねむっている・・・");
            return false;
        }
        if (this.val == FREEZE) {
            showMessageParChar(pokeName + "はこおってしまってうごけない！");
            return false;
        }
        if (this.val == PARALYSIS && (new Random().nextInt(4)) == 0) {
            showMessageParChar(pokeName + "はしびれてうごけない!");
            return false;
        }
        return true;
    }

    public boolean isFine() {
        return this.val == FINE;
    }

    public boolean isSick() {
        return this.val != FINE && this.val != FAINTING;
    }

    public double dmgRateByBurn() {
        return Objects.equals(this.val, BURN) ? 0.5 : 1.0;
    }

    public double spdRateByParalysis() {
        return Objects.equals(this.val, PARALYSIS) ? 0.5 : 1.0;
    }

    public PokeInfo slipDmgByAilment(PokeInfo target) {
        AilmentEnum ailment = target.ailment().val();
        int damage;
        switch (ailment) {
            case POISON -> {
                showMessageParChar(target.basePrm().pName() + "はどくでダメージをうけた！");
                damage = target.realHP() / 8;
            }
            case BAD_POISON -> {
                showMessageParChar(target.basePrm().pName() + "はどくでダメージをうけた！");
                damage = (target.realHP() / 16) * (elapsedTurn + 1);
            }
            case BURN -> {
                showMessageParChar(target.basePrm().pName() + "はやけどでダメージをうけた！");
                damage = target.realHP() / 8;
            }
            default -> {
                return target;
            }
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException ignored) {
        }
        return target.damage(damage);
    }
}
