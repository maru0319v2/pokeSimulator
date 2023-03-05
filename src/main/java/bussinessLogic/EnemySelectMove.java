package bussinessLogic;

import Enum.MoveSpecies;
import Enum.Type;
import field.Field;
import move.Move;
import pokemon.PokemonInfo;

import java.util.*;

public class EnemySelectMove {
    // 相手が技を選択する際のアルゴリズムを実装するクラス
    public static Move enemySelectMove(PokemonInfo enemyPokemon, PokemonInfo myPokemon, Field field) {
        // 持つ技の個数
        int moveSize = enemyPokemon.getHaveMove().size();

        return enemyPokemon.getHaveMove().get(0);
    }

    // 技を完全ランダムに選択する 戻り値は技番号0~4
    private static int completelyRandom(int moveSize) {
        return new Random().nextInt(moveSize);
    }

    // 持つ技の中から最もダメージを与えられる技を選択する。
    private static int maxDamage(PokemonInfo enemyPokemon, PokemonInfo myPokemon, Field field, int moveSize) {
        List<Integer> damageList = new ArrayList<>(moveSize);
        for (Move move : enemyPokemon.getHaveMove()) {
            damageList.add(calcDamageForCPU(enemyPokemon, myPokemon, field, move));
        }
        return damageList.indexOf(Collections.max(damageList));
    }

    // 先制技を持っていてかつ、その技で相手を倒せる可能性がある場合は先制技を選択する。先制技で倒せない場合はすべての技から最大打点を選択。
    private static int highPriorityCanDefeat(PokemonInfo enemyPokemon, PokemonInfo myPokemon, Field field, int moveSize) {
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
        int remainingHP = myPokemon.getCurrentHitPoint().value();
        // ダメージが防御側の残りHPより大きければ先制技を使う、倒せない場合はすべての技から最大打点を選択
        if (maxDamageIndex >= remainingHP) {
            return maxDamageIndex;
        } else {
            return maxDamage(enemyPokemon, myPokemon, field, moveSize);
        }
    }


    // CPUが技を選択するときに事前にダメージ計算を行う為の処理、乱数固定、急所は当たらない
    private static int calcDamageForCPU(PokemonInfo attackPoke, PokemonInfo defencePoke, Field field, Move move) {
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
