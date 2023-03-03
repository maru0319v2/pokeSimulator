package statusAilment;

import lombok.Getter;
import pokemon.PokemonInfo;

import java.util.Objects;
import java.util.Random;

import static bussinessLogic.ConsoleOutManager.showChangeAilmentMessage;
import static bussinessLogic.ConsoleOutManager.showMessageParChar;

@Getter
public class StatusAilmentImpl implements StatusAilment {
    private final Ailment value;
    private final int countRecoverySleep;
    private final int elapsedTurn;

    // 状態異常の継続する場合(ねむり経過ターン+1)
    public static StatusAilment keepAilment(Ailment value, int countForRecovery, int elapsedTurn) {
        return new StatusAilmentImpl(value, countForRecovery, elapsedTurn);
    }
    private StatusAilmentImpl(Ailment value, int countForRecovery, int elapsedTurn) {
        this.value = value;
        this.countRecoverySleep = countForRecovery;
        this.elapsedTurn = elapsedTurn;
    }

    // 初期化したい場合
    public static StatusAilment initializeAilment() {
        return new StatusAilmentImpl();
    }
    private StatusAilmentImpl() {
        this.value = Ailment.FINE;
        this.countRecoverySleep = 0;
        this.elapsedTurn = 0;
    }

    // 状態異常を変化させる場合
    public static StatusAilment changeAilment(PokemonInfo target, Ailment value) throws InterruptedException {
        return new StatusAilmentImpl(target, value);
    }
    private StatusAilmentImpl(PokemonInfo target, Ailment value) throws InterruptedException {

        if(isOverwrite(target, value)){
            int count = 0;
            if(value == Ailment.SLEEP) {
                // 2~5ターン
                count = (new Random().nextInt(4) + 2);
            }
            this.value = value;
            this.countRecoverySleep = count;
            this.elapsedTurn = 0;
            showChangeAilmentMessage(target, value);
        } else {
            this.value = target.getStatusAilment().getValue();
            this.countRecoverySleep = target.getStatusAilment().getCountRecoverySleep();
            this.elapsedTurn = target.getStatusAilment().getElapsedTurn();
        }
    }

    private boolean isOverwrite(PokemonInfo target, Ailment value) {
        // 元の状態異常
        Ailment beforeAilment = target.getStatusAilment().getValue();

        if(beforeAilment == Ailment.FINE) {
            // 元の状態が健康ならすべて上書きされる
            return true;
        }

        if(beforeAilment == Ailment.FAINTING) {
            // 元の状態が瀕死なら健康でしか上書きできない
            if(value == Ailment.FINE) {
                return true;
            }
        }
        if(beforeAilment == Ailment.SLEEP) {
            // 元の状態がねむりの場合、引数の経過ターンが大きい、またはひんしか健康の場合上書きする
            if(target.getStatusAilment().getElapsedTurn() < this.getElapsedTurn() || value == Ailment.FINE || value == Ailment.FAINTING) {
                return true;
            }
        }
        if(beforeAilment == Ailment.PARALYSIS || beforeAilment == Ailment.POISON || beforeAilment == Ailment.BAD_POISON || beforeAilment == Ailment.BURN || beforeAilment == Ailment.FREEZE) {
            // 元の状態がまひ、どく、もうどく、やけど、こおりなら健康と瀕死でしか上書きできない
            if(value == Ailment.FINE || value == Ailment.FAINTING) {
                return true;
            }
        }
        // それ以外の場合は更新しない
        return false;
    }

    public StatusAilment comeTurn() throws InterruptedException {

        // ねむりの場合、経過ターンを1増やしカウンタを同じになればねむりを解く
        if(this.value == Ailment.SLEEP) {
            if(this.countRecoverySleep <= this.elapsedTurn + 1) {
                showMessageParChar("めをさました!");
                return initializeAilment();
            }
            return keepAilment(this.value, this.countRecoverySleep, this.elapsedTurn + 1);
        }
        // こおりの場合、20%の確率でこおりを解く
        if(this.value == Ailment.FREEZE) {
            if((new Random().nextInt(5)) == 0) {
                showMessageParChar("こおりがとけた!");
                return initializeAilment();
            }
        }
        // その他の状態異常の場合、継続
        return keepAilment(this.value, this.countRecoverySleep, this.elapsedTurn);
    }

    public boolean canMove() throws InterruptedException {
        if(this.value == Ailment.SLEEP) {
            showMessageParChar("ぐうぐうねむっている・・・");
            return false;
        }
        if(this.value == Ailment.FREEZE) {
            showMessageParChar("こおってしまってうごけない！");
            return false;
        }
        if(this.value == Ailment.PARALYSIS && (new Random().nextInt(4)) == 0) {
            showMessageParChar("しびれてうごけない!");
            return false;
        }
        return true;
    }

    public boolean isNoAilment() {
        return this.value == Ailment.FINE;
    }

    public double damageRateByBurn() {
        return Objects.equals(this.value, Ailment.BURN) ? 0.5 : 1.0;
    }

    public double speedRateByParalysis() {
        return Objects.equals(this.value, Ailment.PARALYSIS) ? 0.5 : 1.0;
    }

    public PokemonInfo slipDamageByBurn(PokemonInfo target) throws InterruptedException {
        int damage = target.getRealValHitPoint() / 8;
        return target.damagePoke(damage);
    }

    public PokemonInfo slipDamageByPoison(PokemonInfo target) throws InterruptedException {
        int damage = target.getRealValHitPoint() / 8;
        return target.damagePoke(damage);
    }

    public PokemonInfo slipDamageByBadPoison(PokemonInfo target) throws InterruptedException {
        int rate = elapsedTurn / 16;
        int damage = target.getRealValHitPoint() / rate;
        return target.damagePoke(damage);
    }
}
