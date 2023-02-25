package bussinessLogic;

import org.jetbrains.annotations.NotNull;
import pokemonStatus.impl.CurrentHitPointImpl;
import pokemonStatus.CurrentPowerPoint;
import pokemonStatus.impl.CurrentPowerPointImpl;
import Enum.*;
import move.Move;
import pokemon.PokemonInfo;

import java.util.*;

import static bussinessLogic.ConsoleOutManager.*;

public class BattleLogic {

    // 先行後攻を決める
    public static boolean isPreemptiveMe(PokemonInfo myPokemon, PokemonInfo enemyPokemon) {
        int calculatedMySpeed = (int)(myPokemon.getRealValSpeed() * myPokemon.getStatusRank().speedRateByStatusRank());
        int calculatedEnemySpeed = (int)(enemyPokemon.getRealValSpeed() * enemyPokemon.getStatusRank().speedRateByStatusRank());

        if(calculatedMySpeed == calculatedEnemySpeed) {
            // 同速の場合は1~10をランダムで生成して偶数なら先行
            return (new Random().nextInt(10) + 1) % 2 == 0;
        }
        return calculatedMySpeed > calculatedEnemySpeed;
    }

    // 技を選択する
    public static Move selectMove(List<Move> moves, PokemonInfo target) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("　 　　　　　　     PP    タイプ");
        int i = 1;
        for (Move move : moves) {
            System.out.print(i + ": " + move.getName());
            for(int j = 0;j < 8 - move.getName().length(); j++) {
                System.out.print("　");
            }
            System.out.println(move.getCurrentPowerPoint().value() + "/" + move.getPowerPoint() + " " + move.getMoveType().value());
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

            if(Objects.equals(inputCommand, "i")) {
                showParametersInBattle(target);
            } else if (Objects.equals(inputCommand, "m")) {
                showMoveDetail(target.getHaveMove());
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
        // PPを1減らす
        attackPoke = decrementPowerPoint(attackPoke, move);

        if (move.getMoveSpecies() == MoveSpecies.PHYSICAL || move.getMoveSpecies() == MoveSpecies.SPECIAL) {
            if (isHit(move)) {
                int damage = calcDamage(attackPoke, defencePoke, move);
                defencePoke = damagePoke(defencePoke, damage);
            } else {
                showMessageParChar(attackPoke.getPokeName() + "の" + move.getName() + "!");
                showMessageParChar(attackPoke.getPokeName() + "の" + move.getName() + "は外れた");
            }
            return new InBattlePokemons(attackPoke, defencePoke);
        } else {
            if (isHit(move)) {
                showMessageParChar(attackPoke.getPokeName() + "の" + move.getName() + "!");
                return move.effect(attackPoke, defencePoke);
            } else {
                return new InBattlePokemons(attackPoke, defencePoke);
            }
        }
    }

    private static boolean isHit(Move move) {
        return (new Random().nextInt(100) + 1) <= move.getHitRate();
    }

    private static int calcDamage(PokemonInfo attackPoke, PokemonInfo defencePoke, Move move) throws InterruptedException {
        // ダメージ計算参考　https://latest.pokewiki.net/%E3%83%80%E3%83%A1%E3%83%BC%E3%82%B8%E8%A8%88%E7%AE%97%E5%BC%8F
        // 攻撃側のレベル
        int attackPokeLv = attackPoke.getLevel().value();
        // 技の威力
        int moveDamage = move.getDamage();
        // 技の分類
        MoveSpecies moveSpecies = move.getMoveSpecies();
        // ダメージの乱数
        double randomNum = (new Random().nextInt((100 - 85) + 1) + 85) / 100.0;
        // 急所の判定
        boolean isCritical = (new Random().nextInt(24) + 1) == 1;
        double criticalRate = isCritical ? 1.5 : 1;
        // 急所の場合は攻撃側のランク下降、防御側のランク上昇補正を無視する
        double attackRateByStatusRank  = isCritical? Math.max(attackPoke.getStatusRank().attackRateByStatusRank(), 1.0) : attackPoke.getStatusRank().attackRateByStatusRank();
        double blockRateByStatusRank   = isCritical? Math.min(defencePoke.getStatusRank().blockRateByStatusRank(), 1.0) : defencePoke.getStatusRank().blockRateByStatusRank();
        double contactRateByStatusRank = isCritical? Math.max(attackPoke.getStatusRank().contactRateByStatusRank(), 1.0) : attackPoke.getStatusRank().contactRateByStatusRank();
        double defenseRateByStatusRank = isCritical? Math.min(defencePoke.getStatusRank().defenseRateByStatusRank(), 1.0) : defencePoke.getStatusRank().defenseRateByStatusRank();
        // タイプ一致判定
        boolean isTypeMatch = (Objects.equals(move.getMoveType().value(), attackPoke.getType1().value())) || (Objects.equals(move.getMoveType().value(), attackPoke.getType2().value()));
        double typeMatchRate = isTypeMatch ? 1.5 : 1;
        // タイプ相性判定
        double effectiveRate = Type.damageRateByType(defencePoke.getType1(), defencePoke.getType2(), move);
        // やけど判定
        double burnedRate = moveSpecies == MoveSpecies.PHYSICAL? attackPoke.getStatusAilment().dameRateByBurn() : 1.0;

        int attackVal = 0;
        int defenceVal = 0;
        // ステータス実数値にランク補正を乗せる
        if(moveSpecies == MoveSpecies.PHYSICAL) {
            attackVal = (int)(attackPoke.getRealValAttack() * attackRateByStatusRank);
            defenceVal = (int)(defencePoke.getRealValBlock() * blockRateByStatusRank);
        } else if (moveSpecies == MoveSpecies.SPECIAL) {
            attackVal = (int)(attackPoke.getRealValContact() * contactRateByStatusRank);
            defenceVal = (int)(defencePoke.getRealValDefense() * defenseRateByStatusRank);
        }

        int result = (int)Math.floor(Math.floor(Math.floor(Math.floor(attackPokeLv * 2 / 5 + 2) * moveDamage * attackVal / defenceVal) / 50 + 2) * randomNum * criticalRate * typeMatchRate * effectiveRate * burnedRate);
        showMessageParChar(attackPoke.getPokeName() + "の" + move.getName() + "!");
        if(isCritical) { showMessageParChar("急所に当った!"); }
        if(effectiveRate >= 2.0) { showMessageParChar("効果は抜群だ!"); }
        if(effectiveRate <= 0.5) { showMessageParChar("効果はいまひとつのようだ"); }
        if(effectiveRate == 0.0) { showMessageParChar("効果はないようだ"); }
        return result;
    }

    private static PokemonInfo damagePoke(PokemonInfo target, int value) throws InterruptedException {
        PokemonInfo result = target.withCurrentHitPoint(
                target.getCurrentHitPoint().damage(new CurrentHitPointImpl(value))
        );
        showMessageParChar(result.getPokeName() + "は" + value + "のダメージ!");
        return result;
    }

    public static PokemonInfo recoveryAll(PokemonInfo target) {
        target = recoveryHitPoint(target, 999);

        for(Move move : target.getHaveMove()) {
            target = recoveryPowerPoint(target, move, 99);
        }
        return target;
    }

    public static PokemonInfo recoveryHitPoint(PokemonInfo target, int value) {

        PokemonInfo result = target.withCurrentHitPoint(
                target.getCurrentHitPoint().recovery(target, new CurrentHitPointImpl(value))
        );
        System.out.println(result.getPokeName() + "は体力を" + value + "回復!  HP" + result.getCurrentHitPoint().value() + "/" + result.getRealValHitPoint());
        System.out.println();
        return result;
    }

    public static PokemonInfo recoveryPowerPoint(PokemonInfo target, Move move, int value) {

        Move targetMoves = null;
        List<Move> haveMoves = target.getHaveMove();
        for(Move haveMove : haveMoves) {
            if(move.getClass() == haveMove.getClass()) {
                targetMoves = haveMove;
            }
        }

        CurrentPowerPoint recoveredPP = targetMoves.getCurrentPowerPoint().recovery(targetMoves, new CurrentPowerPointImpl(value));
        Move recoveredPPMove = targetMoves.withCurrentPowerPoint(recoveredPP);
        PokemonInfo result = target.withMove(recoveredPPMove);

        return result;
    }

    private static PokemonInfo decrementPowerPoint(PokemonInfo attackPoke, Move usedMove) {
        CurrentPowerPoint decrementedPowerPoint = usedMove.getCurrentPowerPoint().decrement(new CurrentPowerPointImpl(1));
        Move decrementedPPMove = usedMove.withCurrentPowerPoint(decrementedPowerPoint);
        PokemonInfo pokemonInfo = attackPoke.withMove(decrementedPPMove);
        return pokemonInfo;
    }

    public static int calcExp(PokemonInfo enemyPoke) {
        return enemyPoke.getLevel().value() * enemyPoke.getBasicExperience() / 7;
    }

    public static PokemonInfo addExp(PokemonInfo target, int exp) throws InterruptedException {
        PokemonInfo result = target.withExperience(exp);
        showMessageParChar(result.getPokeName() + "は" + exp + "の経験値を獲得!");
        while(result.getExperience().isLevelUp(result)) {
            result = result.withLevel(1);
            showMessageParChar(result.getPokeName() + "はLv." + result.getLevel().value() + "にレベルアップした!");
        }
        return result;
    }
}
