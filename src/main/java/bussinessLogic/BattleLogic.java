package bussinessLogic;

import impl.CurrentHitPointImpl;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

import static bussinessLogic.ConsoleOutManager.showMessageParChar;
import static bussinessLogic.ConsoleOutManager.showParametersInBattle;

public class BattleLogic {

    // 先行後攻を決める
    public static boolean isPreemptiveMe(PokemonInfo myPokemon, PokemonInfo enemyPokemon) {
        int calculatedMySpeed = (int)(myPokemon.realValSpeed() * myPokemon.statusRank().speedRateByStatusRank());
        int calculatedEnemySpeed = (int)(enemyPokemon.realValSpeed() * enemyPokemon.statusRank().speedRateByStatusRank());

        if(calculatedMySpeed == calculatedEnemySpeed) {
            // 同速の場合は1~10をランダムで生成して偶数なら先行
            return (new Random().nextInt(10) + 1) % 2 == 0;
        }
        return calculatedMySpeed > calculatedEnemySpeed;
    }

    // 技を選択する
    public static Move selectMove(List<Move> moves, PokemonInfo target) {
        Scanner scanner = new Scanner(System.in);
        int i = 1;
        for (Move move : moves) {
            System.out.println(i + ": " + move.name());
            i++;
        }
        System.out.println();
        System.out.println("i: ステータス確認");

        Move result = null;
        boolean isNoNumberSelected = true;
        while (isNoNumberSelected) {
            System.out.print("技を選択してください > ");
            String inputCommand = scanner.nextLine();
            System.out.println("");

            if(Objects.equals(inputCommand, "i")) {
                showParametersInBattle(target);
            } else {
                for(int j = 1; i > j; j++) {
                    if (Integer.parseInt(inputCommand) == j) {
                        result = moves.get(j-1);
                        isNoNumberSelected = false;
                    }
                }
            }
        }
        return result;
    }

    // ターンごとの行動 命中判定、ダメージ計算、ダメージ付与を一括で行う
    public static InBattlePokemons doAction(PokemonInfo attackPoke, PokemonInfo defencePoke, Move move) throws InterruptedException {
        if (move.moveSpecies() == MoveSpecies.PHYSICAL || move.moveSpecies() == MoveSpecies.SPECIAL) {
            if (isHit(move)) {
                int damage = calcDamage(attackPoke, defencePoke, move);
                defencePoke = damagePoke(defencePoke, damage);
            } else {
                showMessageParChar(attackPoke.pokeName() + "の" + move.name() + "!");
                showMessageParChar(attackPoke.pokeName() + "の" + move.name() + "は外れた");
            }
            return new InBattlePokemons(attackPoke, defencePoke);
        } else {
            if (isHit(move)) {
                showMessageParChar(attackPoke.pokeName() + "の" + move.name() + "!");
                return move.effect(attackPoke, defencePoke);
            } else {
                return new InBattlePokemons(attackPoke, defencePoke);
            }
        }
    }

    private static boolean isHit(Move move) {
        return (new Random().nextInt(100) + 1) <= move.hitRate();
    }

    private static int calcDamage(PokemonInfo attackPoke, PokemonInfo defencePoke, Move move) throws InterruptedException {
        // ダメージ計算参考　https://latest.pokewiki.net/%E3%83%80%E3%83%A1%E3%83%BC%E3%82%B8%E8%A8%88%E7%AE%97%E5%BC%8F
        // 攻撃側のレベル
        int attackPokeLv = attackPoke.level().value();
        // 技の威力
        int moveDamage = move.damage();
        // 技の分類
        MoveSpecies moveSpecies = move.moveSpecies();
        // ダメージの乱数
        double randomNum = (new Random().nextInt((100 - 85) + 1) + 85) / 100.0;
        // 急所の判定
        boolean isCritical = (new Random().nextInt(24) + 1) == 1;
        double criticalRate = isCritical ? 1.5 : 1;
        // タイプ一致判定
        boolean isTypeMatch = (Objects.equals(move.moveType().value(), attackPoke.pokemonType1().value())) || (Objects.equals(move.moveType().value(), attackPoke.pokemonType2().value()));
        double typeMatchRate = isTypeMatch ? 1.5 : 1;
        // タイプ相性判定
        double effectiveRate = Type.damageRateByType(defencePoke.pokemonType1(), defencePoke.pokemonType2(), move);

        int attackVal = 0;
        int defenceVal = 0;
        // ステータス実数値にランク補正を乗せる
        if(moveSpecies == MoveSpecies.PHYSICAL) {
            attackVal = (int)(attackPoke.realValAttack() * attackPoke.statusRank().attackRateByStatusRank());
            defenceVal = (int)(defencePoke.realValBlock() * defencePoke.statusRank().blockRateByStatusRank());
        } else if (moveSpecies == MoveSpecies.SPECIAL) {
            attackVal = (int)(attackPoke.realValContact() * attackPoke.statusRank().contactRateByStatusRank());
            defenceVal = (int)(defencePoke.realValDefense() * defencePoke.statusRank().defenseRateByStatusRank());
        }

        int result = (int)Math.floor(Math.floor(Math.floor(Math.floor(attackPokeLv * 2 / 5 + 2) * moveDamage * attackVal / defenceVal) / 50 + 2) * randomNum * criticalRate * typeMatchRate * effectiveRate);
        showMessageParChar(attackPoke.pokeName() + "の" + move.name() + "!");
        if(isCritical) { showMessageParChar("急所に当った!"); }
        if(effectiveRate >= 2.0) { showMessageParChar("効果は抜群だ!"); }
        if(effectiveRate <= 0.5) { showMessageParChar("効果はいまひとつのようだ"); }
        if(effectiveRate == 0.0) { showMessageParChar("効果はないようだ"); }
        return result;
    }

    public static PokemonInfo damagePoke(PokemonInfo target, int value) throws InterruptedException {
        PokemonInfo result = target.withCurrentHitPoint(
                target.currentHitPoint().damage(new CurrentHitPointImpl(value))
        );
        showMessageParChar(result.pokeName() + "は" + value + "のダメージ!");
        return result;
    }

    public static PokemonInfo recoveryPoke(PokemonInfo target, int value) {

        PokemonInfo result = target.withCurrentHitPoint(
                target.currentHitPoint().recovery(target, new CurrentHitPointImpl(value))
        );
        System.out.println(result.pokeName() + "は体力を" + value + "回復!  HP" + result.currentHitPoint().value() + "/" + result.realValHitPoint());
        System.out.println();
        return result;
    }

    public static int calcExp(PokemonInfo enemyPoke) {
        return enemyPoke.level().value() * enemyPoke.basicExperience() / 7;
    }

    public static PokemonInfo addExp(PokemonInfo target, int exp) throws InterruptedException {
        PokemonInfo result = target.withExperience(exp);
        showMessageParChar(result.pokeName() + "は" + exp + "の経験値を獲得!");
        while(result.experience().isLevelUp(result)) {
            result = result.withLevel(1);
            showMessageParChar(result.pokeName() + "はLv." + result.level().value() + "にレベルアップした!");
        }
        return result;
    }
}
