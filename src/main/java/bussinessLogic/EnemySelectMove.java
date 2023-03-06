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
    public static Move enemySelectMove(PokeInfo enemyPokemon, PokeInfo myPokemon, Field field) {
        // 持つ技の個数
        int moveSize = enemyPokemon.getHaveMove().size();
        // ランダムな数値1~20
        int r = new Random().nextInt(20) + 1;
        // 選んだ技のindex
        int selectedIndex = 0;

        if (r < 13) {
            selectedIndex = highPriorityCanDefeat(enemyPokemon, myPokemon, field, moveSize);
        } else {
            selectedIndex = completelyRandom(moveSize);
        }

        return enemyPokemon.getHaveMove().get(selectedIndex);
    }

    // 敵の回避ランクが2以上の場合は、必中技だけ候補にする
    private static List<Move> filterEffectiveHit(PokeInfo enemyPokemon, PokeInfo myPokemon) {
        // 敵の回避ランク
        int avoidRank = myPokemon.getStatusRank().getAvoidRate();
        // 回避ランクが2以上の場合、必中技を使用する
        List<Move> filteredMoves = null;
        if (avoidRank >= 2) {
            filteredMoves = enemyPokemon.getHaveMove().stream().filter(e -> e.baseMPrm().getDetailedSpecies() == DetailMvSpecies.HIT).toList();
        } else {
            filteredMoves = enemyPokemon.getHaveMove();
        }
        if (filteredMoves.size() == 0) {
            // フィルターした結果技候補がなくなる場合はすべての技を候補に入れる
            filteredMoves = enemyPokemon.getHaveMove();
        }
        return filteredMoves;
    }

    // 残HPが75%以上の場合は回復技を候補からはずす
    private static List<Move> filterDisableRecovery(PokeInfo enemyPokemon) {
        // 残HP
        int remainingHP = enemyPokemon.getCurrentHitPoint().val();
        // 最大HP
        int maxHP = enemyPokemon.getRealValHitPoint();
        // 残HPの%
        int remainingHPRate = remainingHP / maxHP * 100;
        // 残HPが75%以下なら回復技を候補から外す
        List<Move> filteredMoves;
        if (remainingHPRate <= 75) {
            filteredMoves = enemyPokemon.getHaveMove().stream().filter(e -> e.baseMPrm().getDetailedSpecies() != DetailMvSpecies.RECOVERY).toList();
        } else {
            filteredMoves = enemyPokemon.getHaveMove();
        }
        if (filteredMoves.size() == 0) {
            // フィルターした結果技候補がなくなる場合はすべての技を候補に入れる
            return enemyPokemon.getHaveMove();
        }
        return filteredMoves;
    }

    // 敵が既に状態異常になっている場合は状態異常になる技を技候補からはずす
    private static List<Move> filterDisableAilment(PokeInfo enemyPokemon, PokeInfo myPokemon) {
        // 敵の状態異常を取得
        boolean isAilment = myPokemon.getStatusAilment().isSick();
        List<Move> filteredMoves;
        if (isAilment) {
            // すでに状態異常になっている場合、状態異常にする技以外でフィルターする
            filteredMoves = enemyPokemon.getHaveMove().stream().filter(m -> m.baseMPrm().getDetailedSpecies() != DetailMvSpecies.AILMENT).toList();
        } else {
            // 状態異常になっていない場合、すべての技を候補に入れる
            filteredMoves = enemyPokemon.getHaveMove();
        }
        if (filteredMoves.size() == 0) {
            // フィルターした結果技候補がなくなる場合はすべての技を候補に入れる
            return enemyPokemon.getHaveMove();
        }
        return filteredMoves;
    }

    // すでに変更される天候と同じ天候にする技を候補から外す
    private static List<Move> filterDisableWeather(PokeInfo enemyPokemon, Field field) {
        // 現在の天候
        Weather weather = field.getWeather();
        List<Move> filteredMoves;
        // 天候操作技を持っている場合、天候が重複する技を候補からはずす
        switch (weather) {
            case DROUGHT ->
                    filteredMoves = enemyPokemon.getHaveMove().stream().filter(m -> m.baseMPrm() != BaseMvPrm.SUNNY_DAY).toList();
            case RAIN ->
                    filteredMoves = enemyPokemon.getHaveMove().stream().filter(m -> m.baseMPrm() != BaseMvPrm.RAIN_DANCE).toList();
            case SANDSTORM ->
                    filteredMoves = enemyPokemon.getHaveMove().stream().filter(m -> m.baseMPrm() != BaseMvPrm.SAND_STORM).toList();
            case HAIL ->
                    filteredMoves = enemyPokemon.getHaveMove().stream().filter(m -> m.baseMPrm() != BaseMvPrm.HAIL).toList();
            default -> filteredMoves = enemyPokemon.getHaveMove();
        }
        if (filteredMoves.size() == 0) {
            // フィルターした結果技候補がなくなる場合はすべての技を候補に入れる
            return enemyPokemon.getHaveMove();
        }
        return filteredMoves;
    }

    // 技を完全ランダムに選択する 戻り値は技番号0~4
    private static int completelyRandom(int moveSize) {
        return new Random().nextInt(moveSize);
    }

    // 持つ技の中から最もダメージを与えられる技を選択する。
    private static int maxDamage(PokeInfo enemyPokemon, PokeInfo myPokemon, Field field, int moveSize) {
        List<Integer> damageList = new ArrayList<>(moveSize);
        for (Move move : enemyPokemon.getHaveMove()) {
            damageList.add(calcDamageForCPU(enemyPokemon, myPokemon, field, move));
        }
        return damageList.indexOf(Collections.max(damageList));
    }

    // 先制技を持っていてかつ、その技で相手を倒せる可能性がある場合は先制技を選択する。先制技で倒せない場合はすべての技から最大打点を選択。
    private static int highPriorityCanDefeat(PokeInfo enemyPokemon, PokeInfo myPokemon, Field field, int moveSize) {
        // 優先度1以上の技でフィルターする
        List<Move> priorityMove = enemyPokemon.getHaveMove().stream().filter(m -> m.baseMPrm().getPriority() >= 1).toList();
        // 優先度1以上の技で仮ダメージ計算する
        List<Integer> damageList = new ArrayList<>(priorityMove.size());
        for (Move move : priorityMove) {
            damageList.add(calcDamageForCPU(enemyPokemon, myPokemon, field, move));
        }
        // 最大ダメージ
        int maxDamage = Collections.max(damageList);
        // 最大ダメージのインデックス
        int maxDamageIndex = damageList.indexOf(maxDamage);
        // 防御側の残りHP
        int remainingHP = myPokemon.getCurrentHitPoint().val();
        // ダメージが防御側の残りHPより大きければ先制技を使う、倒せない場合はすべての技から最大打点を選択
        if (maxDamageIndex >= remainingHP) {
            return maxDamageIndex;
        } else {
            return maxDamage(enemyPokemon, myPokemon, field, moveSize);
        }
    }


    // CPUが技を選択するときに事前にダメージ計算を行う為の処理、乱数固定、急所は当たらない
    private static int calcDamageForCPU(PokeInfo attackPoke, PokeInfo defencePoke, Field field, Move move) {
        // 攻撃側のレベル
        int attackPokeLv = attackPoke.getLevel().value();
        // 技の威力
        int moveDamage = move.baseMPrm().getDamage();
        // 技の分類
        MoveSpecies moveSpecies = move.baseMPrm().getMoveSpecies();
        // ダメージの乱数
        double randomNum = 93;
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
        double totalDamageRate = randomNum * typeMatchRate * effectiveRate * burnedRate * weatherRate;

        // すなあらしによる岩タイプの特防上昇
        double defenceRateRock = field.defenceRateBySandStorm(defencePoke);
        int attackVal = 0;
        int defenceVal = 0;
        // ステータス実数値にランク補正を乗せる
        if (moveSpecies == MoveSpecies.PHYSICAL) {
            attackVal = attackPoke.getRealValAttack();
            defenceVal = defencePoke.getRealValBlock();
        } else if (moveSpecies == MoveSpecies.SPECIAL) {
            attackVal = attackPoke.getRealValContact();
            defenceVal = (int) (defencePoke.getRealValDefense() * defenceRateRock);
        }
        return (int) Math.floor(Math.floor(Math.floor(Math.floor(attackPokeLv * 2 / 5 + 2) * moveDamage * attackVal / defenceVal) / 50 + 2) * totalDamageRate);
    }
}
