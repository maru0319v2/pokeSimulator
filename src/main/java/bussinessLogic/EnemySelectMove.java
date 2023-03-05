package bussinessLogic;

import Enum.MoveSpecies;
import Enum.Type;
import field.Field;
import move.Move;
import pokemon.PokemonInfo;

import java.util.*;

public class EnemySelectMove {
    // 相手が技を選択する際のアルゴリズムを実装するクラス
    // 敵の技を選択する
    public static Move enemySelectMove(PokemonInfo enemyPokemon, PokemonInfo myPokemon, Field field) {
        // 持つ技の個数
        int moveSize = enemyPokemon.getHaveMove().size();

        // 技を完全ランダムに選択する
        int selectRandom = (new Random().nextInt(moveSize));

        // 持つ技の中から最もダメージを与えられる技を選択する。
        List<Integer> damageList = new ArrayList<>(moveSize);
        for (Move move : enemyPokemon.getHaveMove()) {
            damageList.add(calcDamageForCPU(enemyPokemon, myPokemon, field, move));
        }
        int maxDamageIndex = damageList.indexOf(Collections.max(damageList));

        // 先制技を持っていてかつ、その技で相手を倒せる可能性がある場合は先制技を選択する。


        return enemyPokemon.getHaveMove().get(maxDamageIndex);
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
        double randomNum = 90;
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
