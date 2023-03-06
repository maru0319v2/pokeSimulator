package bussinessLogic;

import Enum.MoveSpecies;
import Enum.Type;
import field.Field;
import move.Move;
import pokemon.PokeInfo;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

import static bussinessLogic.ConsoleOutManager.*;

public class BattleLogic {

    // 先行後攻を決める
    public static boolean isFirstMe(PokeInfo myPokemon, PokeInfo enemyPokemon, Move selectedMove, Move enemyMove) {
        int calculatedMySpeed = (int) (myPokemon.realSpeed() * myPokemon.statusRank().speedRateByStatusRank() * myPokemon.ailment().speedRateByParalysis());
        int calculatedEnemySpeed = (int) (enemyPokemon.realSpeed() * enemyPokemon.statusRank().speedRateByStatusRank() * enemyPokemon.ailment().speedRateByParalysis());
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
    public static Move selectMove(List<Move> moves, PokeInfo target) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("　 　　　　　　     PP    タイプ");
        int i = 1;
        for (Move move : moves) {
            System.out.print(i + ": " + move.baseMPrm().mvName());
            for (int j = 0; j < 8 - move.baseMPrm().mvName().length(); j++) {
                System.out.print("　");
            }
            System.out.println(move.currentPP().val() + "/" + move.baseMPrm().pp() + " " + move.baseMPrm().moveType().getValue());
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
    public static OnBattleField doAction(PokeInfo attackPoke, PokeInfo defencePoke, Field field, Move move) throws InterruptedException {
        // PPを1減らす
        attackPoke = attackPoke.decrementPP(move);

        // TODO 型で処理を分けてif文なくせそう
        if (move.baseMPrm().moveSpecies() == MoveSpecies.PHYSICAL || move.baseMPrm().moveSpecies() == MoveSpecies.SPECIAL) {
            if (isHit(attackPoke, defencePoke, move)) {
                int damage = calcDamage(attackPoke, defencePoke, field, move);
                defencePoke = defencePoke.damage(damage);
                return move.baseMPrm().effect(attackPoke, defencePoke, field, damage);
            } else {
                showMessageParChar(attackPoke.basePrm().pName() + "の" + move.baseMPrm().mvName() + "!");
                showMessageParChar(attackPoke.basePrm().pName() + "の" + move.baseMPrm().mvName() + "は外れた");
                return new OnBattleField(attackPoke, defencePoke, field);
            }
        } else {
            if (isHit(attackPoke, defencePoke, move)) {
                showMessageParChar(attackPoke.basePrm().pName() + "の" + move.baseMPrm().mvName() + "!");
                return move.baseMPrm().effect(attackPoke, defencePoke, field, 0);
            } else {
                showMessageParChar(attackPoke.basePrm().pName() + "の" + move.baseMPrm().mvName() + "!");
                showMessageParChar(attackPoke.basePrm().pName() + "の" + move.baseMPrm().mvName() + "は外れた");
                return new OnBattleField(attackPoke, defencePoke, field);
            }
        }
    }

    private static int calcDamage(PokeInfo attackPoke, PokeInfo defencePoke, Field field, Move move) throws InterruptedException {
        // ダメージ計算参考　https://latest.pokewiki.net/%E3%83%80%E3%83%A1%E3%83%BC%E3%82%B8%E8%A8%88%E7%AE%97%E5%BC%8F
        // 攻撃側のレベル
        int attackPokeLv = attackPoke.level().val();
        // 技の威力
        int moveDamage = move.baseMPrm().damage();
        // 技の分類
        MoveSpecies moveSpecies = move.baseMPrm().moveSpecies();
        // ダメージの乱数
        double randomNum = (new Random().nextInt((100 - 85) + 1) + 85) / 100.0;
        // 急所の判定
        boolean isCritical = (new Random().nextInt(24) + 1) == 1;
        double criticalRate = isCritical ? 1.5 : 1;
        // 急所の場合は攻撃側のランク下降、防御側のランク上昇補正を無視する
        double attackRateByStatusRank = isCritical ? Math.max(attackPoke.statusRank().attackRateByStatusRank(), 1.0) : attackPoke.statusRank().attackRateByStatusRank();
        double blockRateByStatusRank = isCritical ? Math.min(defencePoke.statusRank().blockRateByStatusRank(), 1.0) : defencePoke.statusRank().blockRateByStatusRank();
        double contactRateByStatusRank = isCritical ? Math.max(attackPoke.statusRank().contactRateByStatusRank(), 1.0) : attackPoke.statusRank().contactRateByStatusRank();
        double defenseRateByStatusRank = isCritical ? Math.min(defencePoke.statusRank().defenseRateByStatusRank(), 1.0) : defencePoke.statusRank().defenseRateByStatusRank();
        // タイプ一致判定
        boolean isTypeMatch = (Objects.equals(move.baseMPrm().moveType(), attackPoke.basePrm().type1())) || (Objects.equals(move.baseMPrm().moveType(), attackPoke.basePrm().type2()));
        double typeMatchRate = isTypeMatch ? 1.5 : 1;
        // タイプ相性判定
        double effectiveRate = Type.damageRateByType(defencePoke.basePrm().type1(), defencePoke.basePrm().type2(), move);
        // やけど判定
        double burnedRate = moveSpecies == MoveSpecies.PHYSICAL ? attackPoke.ailment().damageRateByBurn() : 1.0;
        // 天候によるダメージ倍率
        double weatherRate = field.damageRateByWeather(move);
        // ダメージ倍率合算
        double totalDamageRate = randomNum * criticalRate * typeMatchRate * effectiveRate * burnedRate * weatherRate;

        // すなあらしによる岩タイプの特防上昇
        double defenceRateRock = field.defenceRateBySandStorm(defencePoke);
        int attackVal = 0;
        int defenceVal = 0;
        // ステータス実数値にランク補正を乗せる
        if (moveSpecies == MoveSpecies.PHYSICAL) {
            attackVal = (int) (attackPoke.realAttack() * attackRateByStatusRank);
            defenceVal = (int) (defencePoke.realBlock() * blockRateByStatusRank);
        } else if (moveSpecies == MoveSpecies.SPECIAL) {
            attackVal = (int) (attackPoke.realContact() * contactRateByStatusRank);
            defenceVal = (int) (defencePoke.realDefense() * defenseRateByStatusRank * defenceRateRock);
        }

        int result = (int) Math.floor(Math.floor(Math.floor(Math.floor(attackPokeLv * 2 / 5 + 2) * moveDamage * attackVal / defenceVal) / 50 + 2) * totalDamageRate);
        showMessageParChar(attackPoke.basePrm().pName() + "の" + move.baseMPrm().mvName() + "!");
        if (isCritical) {
            showMessageParChar("急所に当った!");
        }
        if (effectiveRate >= 2.0) {
            showMessageParChar("効果は抜群だ!");
        }
        if (effectiveRate <= 0.5) {
            showMessageParChar("効果はいまひとつのようだ");
        }
        if (effectiveRate == 0.0) {
            showMessageParChar("効果はないようだ");
        }
        return result;
    }

    public static boolean isHit(PokeInfo attackPoke, PokeInfo defencePoke, Move move) {
        // 技の命中率が-1のときは必中
        if (move.baseMPrm().hitRate() == -1) {
            return true;
        }
        // 攻撃側の命中ランクと防御側の回避ランクから算出した命中補正
        double statusRank = attackPoke.statusRank().hitRateByStatusRank(defencePoke);
        // ランダムな数値(1~100)
        int randomNum = (new Random().nextInt(100) + 1);
        // 技の命中率
        int hitRate = move.baseMPrm().hitRate();

        return randomNum <= hitRate * statusRank;
    }
}
