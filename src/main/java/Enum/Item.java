package Enum;

import field.Field;
import field.OnBattleField;
import field.Weather;
import lombok.AllArgsConstructor;
import move.Move;
import pokemon.PokeInfo;
import pokemonStatus.impl.CurrentHPI;
import pokemonStatus.impl.FlinchI;

import java.util.Random;

import static bussinessLogic.ConsoleOutManager.showMessageParChar;

// 性格を表現するクラス
@AllArgsConstructor
public enum Item {
    PEAL_OF_LIFE("いのちのたま"),
    MARK_OF_KINGS("おうじゃのしるし"),
    SEASHELL_BELL("かいがらのすず"),
    FLAME_BALL("かえんだま"),
    LAPEL_OF_SPIRIT("きあいのタスキ"),
    OBON_FRUIT("オボンの実"),
    RAMU_FRUIT("ラムの実"),
    LOSTOVER_FOOD("たべのこし"),
    NONE("-");

    private final String val;

    public String val() {
        return this.val;
    }

    // 技の威力を変動させる
    public static double dmgRate(PokeInfo poke, Move usedMove) {
        Item hasItem = poke.item();
        double result;
        switch (hasItem) {
            case PEAL_OF_LIFE -> result = 1.3;
            default -> result = 1.0;
        }
        return result;
    }

    // 物理、特殊技を使った後の効果
    public static OnBattleField afterMove(OnBattleField onBF) throws InterruptedException {
        Item hasItem = onBF.atkField().poke().item();
        switch (hasItem) {
            case PEAL_OF_LIFE -> {
                // いのちのたま 最大HPの1/10の自傷ダメージ
                showMessageParChar(onBF.atkField().poke().basePrm().pName() + "はいのちたまできずついた!");
                return dmgMeHP1_10(onBF.atkField(), onBF.dfcField(), onBF.weather());
            }
            case MARK_OF_KINGS -> {
                // おうじゃのしるし 1/10でひるませる
                return beFlinch(10, onBF.atkField(), onBF.dfcField(), onBF.weather());
            }
            default -> {
                return onBF;
            }
        }
    }

    // ターン終了時の処理
    public static OnBattleField endTurn(OnBattleField onBF) throws InterruptedException {
        Item hasItem = onBF.atkField().poke().item();
        switch (hasItem) {
            case LOSTOVER_FOOD -> {
                // たべのこし　HPを1/16回復
                showMessageParChar(onBF.atkField().poke().basePrm().pName() + "はたべのこしですこしかいふく!");
                return recoveryMeHP1_16(onBF.atkField(), onBF.dfcField(), onBF.weather());
            }
            default -> {
                return onBF;
            }
        }
    }

    // ダメージを受けたあとの処理
    public static PokeInfo afterDamaged(PokeInfo poke) throws InterruptedException {
        Item hasItem = poke.item();
        switch (hasItem) {
            case OBON_FRUIT -> {
                // オボンの実　HPが半分以下になったときに最大HP1/4回復
                if (hpLessThen50Per(poke)) {
                    showMessageParChar(poke.basePrm().pName() + "はオボンのみでたいりょくをかいふくした!");
                    return recoveryMeHP1_4(poke).withItem(Item.NONE);
                }
                return poke;
            }
            default -> {
                return poke;
            }
        }
    }

    private static OnBattleField doNothing(Field atkField, Field dfcField, Weather weather) {
        return new OnBattleField(atkField, dfcField, weather);
    }

    private static OnBattleField beFlinch(int percent, Field atkField, Field dfcField, Weather weather) {
        if ((new Random().nextInt(10)) <= percent / 10 - 1) {
            return new OnBattleField(atkField, dfcField.withPokeInfo(dfcField.poke().withFlinch(new FlinchI(true))), weather);
        } else {
            return doNothing(atkField, dfcField, weather);
        }
    }

    private static OnBattleField recoveryMeHP1_16(Field atkField, Field dfcField, Weather weather) throws InterruptedException {
        int recovery = atkField.poke().realHP() / 16;
        return new OnBattleField(atkField.withPokeInfo(atkField.poke().withCurrentHP(atkField.poke().currentHP().recovery(atkField.poke(), new CurrentHPI(recovery)))), dfcField, weather);
    }

    private static OnBattleField dmgMeHP1_10(Field atkField, Field dfcField, Weather weather) throws InterruptedException {
        int dmg = atkField.poke().realHP() / 10;
        return new OnBattleField(atkField.withPokeInfo(atkField.poke().damage(dmg)), dfcField, weather);
    }

    private static PokeInfo recoveryMeHP1_4(PokeInfo target) throws InterruptedException {
        int recovery = target.realHP() / 4;
        return target.recoveryHP(recovery);
    }

    private static boolean random10Per() {
        return (new Random().nextInt(10)) == 0;
    }

    private static boolean random20Per() {
        return (new Random().nextInt(10)) <= 1;
    }

    private static boolean random30Per() {
        return (new Random().nextInt(10)) <= 2;
    }

    private static boolean hpLessThen50Per(PokeInfo target) {
        return (double) (target.currentHP().val()) / (double) (target.realHP()) < 0.5;
    }
}
