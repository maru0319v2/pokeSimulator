package statusAilment;

import lombok.Getter;
import pokemon.PokemonInfo;

import java.util.Objects;
import java.util.Random;

import static bussinessLogic.ConsoleOutManager.showMessageParChar;

@Getter
public class StatusAilmentImpl implements StatusAilmentInterface {
    private final Ailment value;
    private final int countRecoverySleep;
    private final int elapsedTurn;

    public StatusAilmentImpl(Ailment value, int countForRecovery, int elapsedTurn) {
        this.value = value;
        this.countRecoverySleep = countForRecovery;
        this.elapsedTurn = elapsedTurn;
    }

    public StatusAilmentImpl(Ailment value) {
        int count = 0;
        if(value == Ailment.SLEEP) {
            // 2~5ターン
            count = (new Random().nextInt(4) + 2);
        }
        this.value = value;
        this.countRecoverySleep = count;
        this.elapsedTurn = 0;
    }

    public StatusAilmentImpl comeTurn() {

        // ねむりの場合、経過ターンを1増やしカウンタを同じになればねむりを解く
        if(this.value == Ailment.SLEEP) {
            if(this.countRecoverySleep <= this.elapsedTurn + 1) {
                return new StatusAilmentImpl(Ailment.NONE);
            }
            return new StatusAilmentImpl(this.value, this.countRecoverySleep, this.elapsedTurn + 1);
        }
        // こおりの場合、20%の確率でこおりを解く
        if(this.value == Ailment.FREEZE) {
            if((new Random().nextInt(5)) == 0) {
                return new StatusAilmentImpl(Ailment.NONE);
            }
        }
        // その他の状態異常の場合、継続
        return new StatusAilmentImpl(this.value, this.countRecoverySleep, this.elapsedTurn);
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
        return this.value == Ailment.NONE;
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
