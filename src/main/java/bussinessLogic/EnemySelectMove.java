package bussinessLogic;

import Enum.MoveSpecies;
import Enum.Type;
import field.Field;
import field.Weather;
import move.BaseMvPrm;
import move.DetailMvSpecies;
import move.Move;
import pokemon.PokeInfo;

import java.util.*;

public class EnemySelectMove {
    // 相手が技を選択する際のアルゴリズムを実装するクラス
    public static Move enemySelectMove(Field enemyField, Field myField, Weather weather) {
        // 持つ技の個数
        int moveSize = enemyField.poke().haveMove().size();
        // ランダムな数値1~20
        int r = new Random().nextInt(20) + 1;
        // 選んだ技のindex
        int selectedIndex = 0;

        if (r < 13) {
            selectedIndex = highPriorityCanDefeat(enemyField, myField, moveSize, weather);
        } else {
            selectedIndex = completelyRandom(moveSize);
        }

        return enemyField.poke().haveMove().get(selectedIndex);
    }

    // 敵の回避ランクが2以上の場合は、必中技だけ候補にする
    private static List<Move> filterEffectiveHit(PokeInfo enemyPk, PokeInfo myPk) {
        // 敵の回避ランク
        int avoidRank = myPk.statusRank().avoidRate();
        // 回避ランクが2以上の場合、必中技を使用する
        List<Move> filteredMoves = null;
        if (avoidRank >= 2) {
            filteredMoves = enemyPk.haveMove().stream().filter(e -> e.baseMPrm().detailedSpecies() == DetailMvSpecies.HIT).toList();
        } else {
            filteredMoves = enemyPk.haveMove();
        }
        if (filteredMoves.size() == 0) {
            // フィルターした結果技候補がなくなる場合はすべての技を候補に入れる
            filteredMoves = enemyPk.haveMove();
        }
        return filteredMoves;
    }

    // 残HPが75%以上の場合は回復技を候補からはずす
    private static List<Move> filterDisableRecovery(PokeInfo enemyPk) {
        // 残HP
        int remainingHP = enemyPk.currentHP().val();
        // 最大HP
        int maxHP = enemyPk.realHP();
        // 残HPの%
        int remainingHPRate = remainingHP / maxHP * 100;
        // 残HPが75%以下なら回復技を候補から外す
        List<Move> filteredMoves;
        if (remainingHPRate <= 75) {
            filteredMoves = enemyPk.haveMove().stream().filter(e -> e.baseMPrm().detailedSpecies() != DetailMvSpecies.RECOVERY).toList();
        } else {
            filteredMoves = enemyPk.haveMove();
        }
        if (filteredMoves.size() == 0) {
            // フィルターした結果技候補がなくなる場合はすべての技を候補に入れる
            return enemyPk.haveMove();
        }
        return filteredMoves;
    }

    // 敵が既に状態異常になっている場合は状態異常になる技を技候補からはずす
    private static List<Move> filterDisableAilment(PokeInfo enemyPk, PokeInfo myPk) {
        // 敵の状態異常を取得
        boolean isAilment = myPk.ailment().isSick();
        List<Move> filteredMoves;
        if (isAilment) {
            // すでに状態異常になっている場合、状態異常にする技以外でフィルターする
            filteredMoves = enemyPk.haveMove().stream().filter(m -> m.baseMPrm().detailedSpecies() != DetailMvSpecies.AILMENT).toList();
        } else {
            // 状態異常になっていない場合、すべての技を候補に入れる
            filteredMoves = enemyPk.haveMove();
        }
        if (filteredMoves.size() == 0) {
            // フィルターした結果技候補がなくなる場合はすべての技を候補に入れる
            return enemyPk.haveMove();
        }
        return filteredMoves;
    }

    // すでに変更される天候と同じ天候にする技を候補から外す
    private static List<Move> filterDisableWeather(PokeInfo enemyPk, Field field, Weather weather) {
        List<Move> filteredMoves;
        // 天候操作技を持っている場合、天候が重複する技を候補からはずす
        switch (weather.val()) {
            case DROUGHT -> filteredMoves = enemyPk.haveMove().stream().filter(m -> m.baseMPrm() != BaseMvPrm.SUNNY_DAY).toList();
            case RAIN -> filteredMoves = enemyPk.haveMove().stream().filter(m -> m.baseMPrm() != BaseMvPrm.RAIN_DANCE).toList();
            case SANDSTORM -> filteredMoves = enemyPk.haveMove().stream().filter(m -> m.baseMPrm() != BaseMvPrm.SAND_STORM).toList();
            case HAIL -> filteredMoves = enemyPk.haveMove().stream().filter(m -> m.baseMPrm() != BaseMvPrm.HAIL).toList();
            default -> filteredMoves = enemyPk.haveMove();
        }
        if (filteredMoves.size() == 0) {
            // フィルターした結果技候補がなくなる場合はすべての技を候補に入れる
            return enemyPk.haveMove();
        }
        return filteredMoves;
    }

    // 技を完全ランダムに選択する 戻り値は技番号0~4
    private static int completelyRandom(int moveSize) {
        return new Random().nextInt(moveSize);
    }

    // 持つ技の中から最もダメージを与えられる技を選択する。
    private static int maxDamage(Field enemyField, Field myField, int moveSize, Weather weather) {
        List<Integer> damageList = new ArrayList<>(moveSize);
        for (Move move : enemyField.poke().haveMove()) {
            damageList.add(calcDamageForCPU(enemyField, myField, move, weather));
        }
        return damageList.indexOf(Collections.max(damageList));
    }

    // 先制技を持っていてかつ、その技で相手を倒せる可能性がある場合は先制技を選択する。先制技で倒せない場合はすべての技から最大打点を選択。
    private static int highPriorityCanDefeat(Field enemyField, Field myField, int moveSize, Weather weather) {
        // 優先度1以上の技でフィルターする
        List<Move> priorityMove = enemyField.poke().haveMove().stream().filter(m -> m.baseMPrm().priority() >= 1).toList();
        if (priorityMove.size() >= 1) {
            // 優先度1以上の技で仮ダメージ計算する
            List<Integer> damageList = new ArrayList<>(priorityMove.size());
            for (Move move : priorityMove) {
                damageList.add(calcDamageForCPU(enemyField, myField, move, weather));
            }
            // 最大ダメージ
            int maxDamage = Collections.max(damageList);
            // 最大ダメージのインデックス
            int maxDamageIndex = damageList.indexOf(maxDamage);
            // 防御側の残りHP
            int remainingHP = myField.poke().currentHP().val();
            if (maxDamageIndex >= remainingHP) {
                // ダメージが防御側の残りHPより大きければ先制技を使う
                return maxDamageIndex;
            }
        }
        // 先制技が存在しない、または先制技で倒せない場合はすべての技から最大打点を選択
        return maxDamage(enemyField, myField, moveSize, weather);
    }


    // CPUが技を選択するときに事前にダメージ計算を行う為の処理、乱数固定、急所は当たらない
    private static int calcDamageForCPU(Field atkField, Field dfcField, Move move, Weather weather) {
        // 攻撃側のレベル
        int attackPokeLv = atkField.poke().level().val();
        // 技の威力
        int moveDamage = null == move.baseMPrm().damage() ? 1 : move.baseMPrm().damage();
        // 技の分類
        MoveSpecies moveSpecies = move.baseMPrm().moveSpecies();
        // ダメージの乱数
        double randomNum = 93;
        // タイプ一致判定
        boolean isTypeMatch = (Objects.equals(move.baseMPrm().moveType(), atkField.poke().basePrm().type1())) || (Objects.equals(move.baseMPrm().moveType(), atkField.poke().basePrm().type2()));
        double typeMatchRate = isTypeMatch ? 1.5 : 1;
        // タイプ相性判定
        double effectiveRate = Type.dmgRateByType(dfcField.poke().basePrm().type1(), dfcField.poke().basePrm().type2(), move);
        // やけど判定
        double burnedRate = moveSpecies == MoveSpecies.PHYSICAL ? atkField.poke().ailment().dmgRateByBurn() : 1.0;
        // 天候によるダメージ倍率
        double weatherRate = weather.dmgRateByWeather(move);
        // ダメージ倍率合算
        double totalDmgRate = randomNum * typeMatchRate * effectiveRate * burnedRate * weatherRate;

        // すなあらしによる岩タイプの特防上昇
        double defenceRateRock = weather.dfcRateBySandStorm(dfcField.poke());
        int attackVal = 0;
        int defenceVal = 0;
        // ステータス実数値にランク補正を乗せる
        if (moveSpecies == MoveSpecies.PHYSICAL) {
            attackVal = atkField.poke().realAtk();
            defenceVal = dfcField.poke().realBlk();
        } else if (moveSpecies == MoveSpecies.SPECIAL) {
            attackVal = atkField.poke().realCnt();
            defenceVal = (int) (dfcField.poke().realDfc() * defenceRateRock);
        }
        return (int) Math.floor(Math.floor(Math.floor(Math.floor(attackPokeLv * 2 / 5 + 2) * moveDamage * attackVal / defenceVal) / 50 + 2) * totalDmgRate);
    }
}
