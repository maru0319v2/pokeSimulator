package bussinessLogic;

import Enum.Item;
import Enum.MoveSpecies;
import Enum.Type;
import field.Field;
import field.OnBattleField;
import field.Weather;
import move.BaseMvPrm;
import move.Move;
import pokemon.PokeInfo;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

import static bussinessLogic.ConsoleOutManager.*;

public class BattleLogic {

    // 先行後攻を決める
    public static boolean isFirstMe(Field myField, Field eneField, Move selectedMove, Move enemyMove) {
        PokeInfo myPk = myField.poke();
        PokeInfo enePk = eneField.poke();
        int calculatedMySpeed = (int) (myPk.realSpd() * myPk.statusRank().spdRateByStatusRank() * myPk.ailment().spdRateByParalysis());
        int calculatedEnemySpeed = (int) (enePk.realSpd() * enePk.statusRank().spdRateByStatusRank() * enePk.ailment().spdRateByParalysis());
        int myPriority = selectedMove.baseMPrm().priority();
        int enemyPriority = enemyMove.baseMPrm().priority();

        if (myPriority != enemyPriority) {
            return myPriority > enemyPriority;
        }

        if (calculatedMySpeed == calculatedEnemySpeed) {
            // 同速の場合は0~1をランダムで生成して0なら先行
            return (new Random().nextInt(2)) == 0;
        }
        return calculatedMySpeed > calculatedEnemySpeed;
    }

    // 技を選択する
    public static Move selectMove(PokeInfo target) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("　 　　　　　　     PP    タイプ");
        List<Move> moves = target.haveMove();
        int i = 1;
        for (Move move : moves) {
            System.out.print(i + ": " + move.baseMPrm().mvName());
            for (int j = 0; j < 8 - move.baseMPrm().mvName().length(); j++) {
                System.out.print("　");
            }
            System.out.println(move.currentPP().val() + "/" + move.baseMPrm().pp() + " " + move.baseMPrm().moveType().val());
            i++;
        }
        System.out.println();
        System.out.println("i: ステータス確認");
        System.out.println("m: 技確認");

        Move result = null;
        boolean isNoNumberSelected = true;
        while (isNoNumberSelected) {
            System.out.print("技を選択してください > ");
            String inputCommand = scanner.nextLine();
            System.out.println("");

            if (Objects.equals(inputCommand, "i")) {
                showParametersInBattle(target);
            } else if (Objects.equals(inputCommand, "m")) {
                showMoveDetail(target.haveMove());
            } else {
                for (int j = 1; i > j; j++) {
                    if (Integer.parseInt(inputCommand) == j) {
                        if (moves.get(j - 1).canUse()) {
                            result = moves.get(j - 1);
                            isNoNumberSelected = false;
                        } else {
                            System.out.println("技のPPが足りない!");
                        }
                    }
                }
            }
        }
        return result;
    }

    // ターンごとの行動 命中判定、ダメージ計算、ダメージ付与を一括で行う
    public static OnBattleField doAction(Field atkField, Field dfcField, Move move, Weather weather) {
        // PPを1減らす
        atkField = atkField.updatePokeInfo(atkField.poke().decrementPP(move));

        // TODO 型で処理を分けてif文なくせそう
        if (move.baseMPrm().moveSpecies() == MoveSpecies.PHYSICAL || move.baseMPrm().moveSpecies() == MoveSpecies.SPECIAL) {
            if (isHit(atkField.poke(), dfcField.poke(), move, weather)) {
                int damage = calcDamage(atkField, dfcField, move, weather);
                // 相手にダメージを与える
                dfcField = dfcField.updatePokeInfo(dfcField.poke().damage(damage));
                // 技の追加効果を与える
                OnBattleField onBF = move.baseMPrm().effect(atkField, dfcField, damage, weather);
                // アイテムによる追加効果
                return Item.afterMove(onBF);
            } else {
                showMessageParChar(atkField.poke().basePrm().pName() + "の" + move.baseMPrm().mvName() + "!");
                showMessageParChar(atkField.poke().basePrm().pName() + "の" + move.baseMPrm().mvName() + "は外れた");
                return new OnBattleField(atkField, dfcField, weather);
            }
        } else {
            if (BaseMvPrm.powderMove.contains(move.baseMPrm())) {
                if (dfcField.poke().basePrm().type1() == Type.GRASS || dfcField.poke().basePrm().type2() == Type.GRASS) {
                    showMessageParChar(atkField.poke().basePrm().pName() + "の" + move.baseMPrm().mvName() + "!");
                    showMessageParChar("しかし" + atkField.poke().basePrm().pName() + "にはきかなかった!");
                    return new OnBattleField(atkField, dfcField, weather);
                }
            }
            if (isHit(atkField.poke(), dfcField.poke(), move, weather)) {
                showMessageParChar(atkField.poke().basePrm().pName() + "の" + move.baseMPrm().mvName() + "!");
                return move.baseMPrm().effect(atkField, dfcField, 0, weather);
            } else {
                showMessageParChar(atkField.poke().basePrm().pName() + "の" + move.baseMPrm().mvName() + "!");
                showMessageParChar(atkField.poke().basePrm().pName() + "の" + move.baseMPrm().mvName() + "は外れた");
                return new OnBattleField(atkField, dfcField, weather);
            }
        }
    }

    private static int calcDamage(Field atkField, Field dfcField, Move move, Weather weather) {
        // ダメージ計算参考　https://latest.pokewiki.net/%E3%83%80%E3%83%A1%E3%83%BC%E3%82%B8%E8%A8%88%E7%AE%97%E5%BC%8F
        showMessageParChar(atkField.poke().basePrm().pName() + "の" + move.baseMPrm().mvName() + "!");
        // 固定ダメージ用の特別処理
        if (BaseMvPrm.constDmgMove.contains(move.baseMPrm())) {
            return move.baseMPrm().constDmgCalc(atkField, dfcField);
        }

        // 攻撃側ポケモン
        PokeInfo atkPk = atkField.poke();
        // 防御側ポケモン
        PokeInfo dfcPk = dfcField.poke();
        // 攻撃側のレベル
        int attackPokeLv = atkPk.level().val();
        // 技の威力
        int moveDamage = move.baseMPrm().damage(atkField, dfcField);
        // 技の分類
        MoveSpecies moveSpecies = move.baseMPrm().moveSpecies();
        // ダメージの乱数
        double randomNum = (new Random().nextInt((100 - 85) + 1) + 85) / 100.0;
        // 急所の判定
        boolean isCritical = isCritical(atkField.poke(), move);
        double criticalRate = isCritical ? 1.5 : 1;
        // 急所の場合は攻撃側のランク下降、防御側のランク上昇補正を無視する
        double attackRateByStatusRank = isCritical ? Math.max(atkPk.statusRank().atkRateByStatusRank(), 1.0) : atkPk.statusRank().atkRateByStatusRank();
        double blockRateByStatusRank = isCritical ? Math.min(dfcPk.statusRank().blcRateByStatusRank(), 1.0) : dfcPk.statusRank().blcRateByStatusRank();
        double contactRateByStatusRank = isCritical ? Math.max(atkPk.statusRank().cntRateByStatusRank(), 1.0) : atkPk.statusRank().cntRateByStatusRank();
        double defenceRateByStatusRank = isCritical ? Math.min(dfcPk.statusRank().dfcRateByStatusRank(), 1.0) : dfcPk.statusRank().dfcRateByStatusRank();
        // タイプ一致判定
        boolean isTypeMatch = (Objects.equals(move.baseMPrm().moveType(), atkPk.basePrm().type1())) || (Objects.equals(move.baseMPrm().moveType(), atkPk.basePrm().type2()));
        double typeMatchRate = isTypeMatch ? 1.5 : 1;
        // タイプ相性判定
        double effectiveRate = Type.dmgRateByType(dfcPk.basePrm().type1(), dfcPk.basePrm().type2(), move);
        // やけど判定
        double burnedRate = moveSpecies == MoveSpecies.PHYSICAL ? atkPk.ailment().dmgRateByBurn() : 1.0;
        // 天候によるダメージ倍率
        double weatherRate = weather.dmgRateByWeather(move);
        // リフレクター、ひかりのかべによるダメージ倍率
        double reflectRate = dfcField.reflect().dmgRateByReflect(move.baseMPrm().moveSpecies());
        double lightScreenRate = dfcField.lightScreen().dmgRateByLightScreen(move.baseMPrm().moveSpecies());
        // 所持道具による倍率
        double itemRate = Item.dmgRate(atkField.poke(), move);
        // ダメージ倍率合算
        double totalDmgRate = randomNum * criticalRate * typeMatchRate * effectiveRate * burnedRate * weatherRate * reflectRate * lightScreenRate * itemRate;

        // すなあらしによる岩タイプの特防上昇
        double defenceRateRock = weather.dfcRateBySandStorm(dfcPk);
        int attackVal = 0;
        int defenceVal = 0;
        // ステータス実数値にランク補正を乗せる
        if (moveSpecies == MoveSpecies.PHYSICAL) {
            attackVal = (int) (atkPk.realAtk() * attackRateByStatusRank);
            defenceVal = (int) (dfcPk.realBlk() * blockRateByStatusRank);
        } else if (moveSpecies == MoveSpecies.SPECIAL) {
            attackVal = (int) (atkPk.realCnt() * contactRateByStatusRank);
            defenceVal = (int) (dfcPk.realDfc() * defenceRateByStatusRank * defenceRateRock);
        }

        int result = (int) Math.floor(Math.floor(Math.floor(Math.floor(attackPokeLv * 2 / 5 + 2) * moveDamage * attackVal / defenceVal) / 50 + 2) * totalDmgRate);
        if (isCritical) {
            showMessageParChar("きゅうしょにあたった!");
        }
        if (effectiveRate >= 2.0) {
            showMessageParChar("こうかはばつぐんだ!");
        } else if (effectiveRate <= 0.5 && effectiveRate != 0.0) {
            showMessageParChar("こうかはいまひとつのようだ");
        } else if (effectiveRate == 0.0) {
            showMessageParChar("こうかはないようだ");
        }
        return Math.min(result, dfcField.poke().currentHP().val());
    }

    private static boolean isHit(PokeInfo atkPk, PokeInfo dfcPk, Move move, Weather weather) {
        // 技の命中率がnullのときは必中
        if (null == move.baseMPrm().hitRate(weather)) {
            return true;
        }
        // 攻撃側の命中ランクと防御側の回避ランクから算出した命中補正
        double statusRank = atkPk.statusRank().hitRateByStatusRank(dfcPk);
        // 道具による命中率補正
        double itemRate = 1.0;
        itemRate = atkPk.item() == Item.WIDE_LENS ? itemRate * 1.1 : itemRate;
        itemRate = dfcPk.item() == Item.LIGHT_POWDER ? itemRate * 0.9 : itemRate;
        // ランダムな数値(1~100)
        int randomNum = (new Random().nextInt(100) + 1);
        // 技の命中率
        int hitRate = move.baseMPrm().hitRate(weather);

        return randomNum <= hitRate * statusRank * itemRate;
    }

    private static boolean isCritical(PokeInfo poke, Move move) {
        Item item = poke.item();
        int itemRate = 0;
        if (item == Item.PINTO_LENS) {
            itemRate = 1;
        }
        int criticalRank = move.baseMPrm().criticalRank() + itemRate;
        boolean result;
        switch (criticalRank) {
            case 0 -> result = (new Random().nextInt(24)) == 0;
            case 1 -> result = (new Random().nextInt(8)) == 0;
            case 2 -> result = (new Random().nextInt(2)) == 0;
            default -> result = true;
        }
        return result;
    }
}
