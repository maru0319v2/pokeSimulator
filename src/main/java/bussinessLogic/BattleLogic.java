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
        int calculatedMySpeed = (int) (myPokemon.getRealValSpeed() * myPokemon.getStatusRank().speedRateByStatusRank() * myPokemon.getStatusAilment().speedRateByParalysis());
        int calculatedEnemySpeed = (int) (enemyPokemon.getRealValSpeed() * enemyPokemon.getStatusRank().speedRateByStatusRank() * enemyPokemon.getStatusAilment().speedRateByParalysis());
        int myPriority = selectedMove.baseMPrm().getPriority();
        int enemyPriority = enemyMove.baseMPrm().getPriority();

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
            System.out.print(i + ": " + move.baseMPrm().getName());
            for (int j = 0; j < 8 - move.baseMPrm().getName().length(); j++) {
                System.out.print("　");
            }
            System.out.println(move.getCurrentPowerPoint().value() + "/" + move.baseMPrm().getPowerPoint() + " " + move.baseMPrm().getMoveType().getValue());
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
                showMoveDetail(target.getHaveMove());
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
        attackPoke = attackPoke.decrementPowerPoint(move);

        // TODO 型で処理を分けてif文なくせそう
        if (move.baseMPrm().getMoveSpecies() == MoveSpecies.PHYSICAL || move.baseMPrm().getMoveSpecies() == MoveSpecies.SPECIAL) {
            if (isHit(attackPoke, defencePoke, move)) {
                int damage = calcDamage(attackPoke, defencePoke, field, move);
                defencePoke = defencePoke.damagePoke(damage);
                return move.baseMPrm().effect(attackPoke, defencePoke, field, damage);
            } else {
                showMessageParChar(attackPoke.getBasePrm().getName() + "の" + move.baseMPrm().getName() + "!");
                showMessageParChar(attackPoke.getBasePrm().getName() + "の" + move.baseMPrm().getName() + "は外れた");
                return new OnBattleField(attackPoke, defencePoke, field);
            }
        } else {
            if (isHit(attackPoke, defencePoke, move)) {
                showMessageParChar(attackPoke.getBasePrm().getName() + "の" + move.baseMPrm().getName() + "!");
                return move.baseMPrm().effect(attackPoke, defencePoke, field, 0);
            } else {
                showMessageParChar(attackPoke.getBasePrm().getName() + "の" + move.baseMPrm().getName() + "!");
                showMessageParChar(attackPoke.getBasePrm().getName() + "の" + move.baseMPrm().getName() + "は外れた");
                return new OnBattleField(attackPoke, defencePoke, field);
            }
        }
    }

    private static int calcDamage(PokeInfo attackPoke, PokeInfo defencePoke, Field field, Move move) throws InterruptedException {
        // ダメージ計算参考　https://latest.pokewiki.net/%E3%83%80%E3%83%A1%E3%83%BC%E3%82%B8%E8%A8%88%E7%AE%97%E5%BC%8F
        // 攻撃側のレベル
        int attackPokeLv = attackPoke.getLevel().value();
        // 技の威力
        int moveDamage = move.baseMPrm().getDamage();
        // 技の分類
        MoveSpecies moveSpecies = move.baseMPrm().getMoveSpecies();
        // ダメージの乱数
        double randomNum = (new Random().nextInt((100 - 85) + 1) + 85) / 100.0;
        // 急所の判定
        boolean isCritical = (new Random().nextInt(24) + 1) == 1;
        double criticalRate = isCritical ? 1.5 : 1;
        // 急所の場合は攻撃側のランク下降、防御側のランク上昇補正を無視する
        double attackRateByStatusRank = isCritical ? Math.max(attackPoke.getStatusRank().attackRateByStatusRank(), 1.0) : attackPoke.getStatusRank().attackRateByStatusRank();
        double blockRateByStatusRank = isCritical ? Math.min(defencePoke.getStatusRank().blockRateByStatusRank(), 1.0) : defencePoke.getStatusRank().blockRateByStatusRank();
        double contactRateByStatusRank = isCritical ? Math.max(attackPoke.getStatusRank().contactRateByStatusRank(), 1.0) : attackPoke.getStatusRank().contactRateByStatusRank();
        double defenseRateByStatusRank = isCritical ? Math.min(defencePoke.getStatusRank().defenseRateByStatusRank(), 1.0) : defencePoke.getStatusRank().defenseRateByStatusRank();
        // タイプ一致判定
        boolean isTypeMatch = (Objects.equals(move.baseMPrm().getMoveType(), attackPoke.getBasePrm().getType1())) || (Objects.equals(move.baseMPrm().getMoveType(), attackPoke.getBasePrm().getType2()));
        double typeMatchRate = isTypeMatch ? 1.5 : 1;
        // タイプ相性判定
        double effectiveRate = Type.damageRateByType(defencePoke.getBasePrm().getType1(), defencePoke.getBasePrm().getType2(), move);
        // やけど判定
        double burnedRate = moveSpecies == MoveSpecies.PHYSICAL ? attackPoke.getStatusAilment().damageRateByBurn() : 1.0;
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
            attackVal = (int) (attackPoke.getRealValAttack() * attackRateByStatusRank);
            defenceVal = (int) (defencePoke.getRealValBlock() * blockRateByStatusRank);
        } else if (moveSpecies == MoveSpecies.SPECIAL) {
            attackVal = (int) (attackPoke.getRealValContact() * contactRateByStatusRank);
            defenceVal = (int) (defencePoke.getRealValDefense() * defenseRateByStatusRank * defenceRateRock);
        }

        int result = (int) Math.floor(Math.floor(Math.floor(Math.floor(attackPokeLv * 2 / 5 + 2) * moveDamage * attackVal / defenceVal) / 50 + 2) * totalDamageRate);
        showMessageParChar(attackPoke.getBasePrm().getName() + "の" + move.baseMPrm().getName() + "!");
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
        if (move.baseMPrm().getHitRate() == -1) {
            return true;
        }
        // 攻撃側の命中ランクと防御側の回避ランクから算出した命中補正
        double statusRank = attackPoke.getStatusRank().hitRateByStatusRank(defencePoke);
        // ランダムな数値(1~100)
        int randomNum = (new Random().nextInt(100) + 1);
        // 技の命中率
        int hitRate = move.baseMPrm().getHitRate();

        return randomNum <= hitRate * statusRank;
    }
}
