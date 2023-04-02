package Enum;

import field.Field;
import field.OnBattleField;
import field.Weather;
import lombok.AllArgsConstructor;
import move.Move;
import pokemon.PokeInfo;
import pokemonStatus.impl.FlinchI;
import statusAilment.AilmentEnum;
import statusAilment.AilmentI;

import java.util.Random;

import static bussinessLogic.ConsoleOutManager.showMessageParChar;

// 性格を表現するクラス
@AllArgsConstructor
public enum Item {
    PEAL_OF_LIFE("いのちのたま"),
    MARK_OF_KINGS("おうじゃのしるし"),
    LAPEL_OF_SPIRIT("きあいのタスキ"),
    OBON_FRUIT("オボンの実"),
    KAMURA_FRUIT("カムラの実"),
    RAMU_FRUIT("ラムの実"),
    LOSTOVER_FOOD("たべのこし"),
    PINTO_LENS("ピントレンズ"),
    WIDE_LENS("こうかくレンズ"),
    LIGHT_POWDER("ひかりのこな"),
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
    public static OnBattleField afterMove(OnBattleField onBF) {
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
    public static OnBattleField endTurn(OnBattleField onBF) {
        Field atkField = onBF.atkField();
        Field dfcField = onBF.dfcField();


        Item atkHasItem = onBF.atkField().poke().item();
        switch (atkHasItem) {
            case LOSTOVER_FOOD -> {
                // たべのこし　HPを1/16回復
                showMessageParChar(onBF.atkField().poke().basePrm().pName() + "はたべのこしですこしかいふく!");
                atkField = recoveryMeHP1_16(onBF.atkField(), onBF.dfcField(), onBF.weather()).atkField();
            }
        }
        Item dfcHasItem = onBF.dfcField().poke().item();
        switch (dfcHasItem) {
            case LOSTOVER_FOOD -> {
                // たべのこし　HPを1/16回復
                showMessageParChar(onBF.dfcField().poke().basePrm().pName() + "はたべのこしですこしかいふく!");
                dfcField = recoveryEnemyHP1_16(onBF.atkField(), onBF.dfcField(), onBF.weather()).dfcField();
            }
        }
        return new OnBattleField(atkField, dfcField, onBF.weather());
    }

    // ダメージを受けたあとの処理
    public static PokeInfo afterDamaged(PokeInfo poke) {
        Item hasItem = poke.item();
        switch (hasItem) {
            case OBON_FRUIT -> {
                // オボンの実　HPが半分以下になったときに最大HP1/4回復
                if (hpLessThen50Per(poke)) {
                    showMessageParChar(poke.basePrm().pName() + "はオボンのみでたいりょくをかいふくした!");
                    return recoveryMeHP1_4(poke).updateItem(Item.NONE);
                }
                return poke;
            }
            case KAMURA_FRUIT -> {
                // カムラの実　HPが1/4以下になったときにすばやさが1段階上がる
                if (hpLessThen25Per(poke)) {
                    showMessageParChar(poke.basePrm().pName() + "はカムラのみをつかった!");
                    return poke.changeStatusRank(0, 0, 0, 0, 1, 0, 0).updateItem(Item.NONE);
                }
                return poke;
            }
            default -> {
                return poke;
            }
        }
    }

    // 状態異常が変化した後の処理
    public static PokeInfo afterUpdateAilment(PokeInfo poke) {
        Item hasItem = poke.item();
        switch (hasItem) {
            case RAMU_FRUIT -> {
                // ラムの実　ひんし以外のすべての状態異常を回復
                if (poke.ailment().isSick()) {
                    showMessageParChar(poke.basePrm().pName() + "はラムのみでじょうたいいじょうをかいふくした!");
                    return recoveryMeAllAilment(poke).updateItem(Item.NONE);
                }
                return poke;
            }
            default -> {
                return poke;
            }
        }
    }

    // HPが満タンのときにひんしになるダメージをうけた場合の処理
    public static PokeInfo deadWithFullHP(PokeInfo poke) {
        Item hasItem = poke.item();
        switch (hasItem) {
            case LAPEL_OF_SPIRIT -> {
                // 気合いの襷　HPを1残す
                showMessageParChar(poke.basePrm().pName() + "はきあいのタスキをこうげきをたえた!");
                int dmg = poke.realHP() - 1;
                return poke.damage(dmg).updateItem(Item.NONE);
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
            return new OnBattleField(atkField, dfcField.updatePokeInfo(dfcField.poke().updateFlinch(new FlinchI(true))), weather);
        } else {
            return doNothing(atkField, dfcField, weather);
        }
    }

    private static OnBattleField recoveryMeHP1_16(Field atkField, Field dfcField, Weather weather) {
        int recovery = atkField.poke().realHP() / 16;
        return new OnBattleField(atkField.updatePokeInfo(atkField.poke().recoveryHP(recovery)), dfcField, weather);
    }

    private static OnBattleField recoveryEnemyHP1_16(Field atkField, Field dfcField, Weather weather) {
        int recovery = dfcField.poke().realHP() / 16;
        return new OnBattleField(atkField, dfcField.updatePokeInfo(dfcField.poke().recoveryHP(recovery)), weather);
    }

    private static OnBattleField dmgMeHP1_10(Field atkField, Field dfcField, Weather weather) {
        int dmg = atkField.poke().realHP() / 10;
        return new OnBattleField(atkField.updatePokeInfo(atkField.poke().damage(dmg)), dfcField, weather);
    }

    private static PokeInfo recoveryMeHP1_4(PokeInfo target) {
        int recovery = target.realHP() / 4;
        return target.recoveryHP(recovery);
    }

    private static PokeInfo recoveryMeAllAilment(PokeInfo target) {
        return target.updateAilment(AilmentI.changeAilment(target, AilmentEnum.FINE));
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

    private static boolean hpLessThen25Per(PokeInfo target) {
        return (double) (target.currentHP().val()) / (double) (target.realHP()) < 0.25;
    }
}
