package main.java.org.example;

import main.java.org.example.impl.CurrentHitPointImpl;
import main.java.org.example.pokemon.Bulbasaur;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class BattleLogic {

    // 先行後攻を決める
    public static boolean isPreemptiveMe(int mySpeed, int enemySpeed) {
        if(mySpeed == enemySpeed) {
            // 同速の場合は1~10をランダムで生成して偶数なら先行
            return (new Random().nextInt(10) + 1) % 2 == 0;
        }
        return mySpeed > enemySpeed;
    }

    // 技を選択する
    public static Move selectMove(List<Move> moves) {
        Scanner scanner = new Scanner(System.in);
        int i = 1;
        for (Move move : moves) {
            System.out.println(i + ": " + move.name());
            i++;
        }

        Move result = null;
        boolean isNoNumberSelected = true;
        while (isNoNumberSelected) {
            System.out.print("技を選択してください > ");
            String inputCommand = scanner.nextLine();
            System.out.println("");
            for(int j = 1; i > j; j++) {
                if(Integer.parseInt(inputCommand) == j) {
                    result = moves.get(j-1);
                    isNoNumberSelected = false;
                }
            }
        }
        return result;
    }

    public static int calcDamage(PokemonInfo attackPoke, PokemonInfo defencePoke, Move move) {
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
        if(moveSpecies == MoveSpecies.PHYSICAL) {
            attackVal = attackPoke.realValAttack();
            defenceVal = defencePoke.realValBlock();
        } else if (moveSpecies == MoveSpecies.SPECIAL) {
            attackVal = attackPoke.realValContact();
            defenceVal = defencePoke.realValDefense();
        }

        int result = (int)Math.floor(Math.floor(Math.floor(Math.floor(attackPokeLv * 2 / 5 + 2) * moveDamage * attackVal / defenceVal) / 50 + 2) * randomNum * criticalRate * typeMatchRate * effectiveRate);
        System.out.print(attackPoke.pokeName() + "の" + move.name() + "! ");
        if(isCritical) { System.out.print(" 急所に当った! "); }
        if(effectiveRate >= 2.0) { System.out.print("効果は抜群だ! "); }
        if(effectiveRate <= 0.5) { System.out.print("効果はいまひとつのようだ "); }
        if(effectiveRate == 0.0) { System.out.print("効果はないようだ "); }
        return result;
    }

    public static PokemonInfo damagePoke(PokemonInfo target, int value) {
        PokemonInfo result = target.withCurrentHitPoint(
                target.currentHitPoint().damage(new CurrentHitPointImpl(value))
        );
        System.out.println(result.pokeName() + "は" + value + "のダメージ!");
        System.out.println();
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

    // TODO 経験値を得るタイミングと努力値を得るタイミングは別にしたほうがいい
    public static PokemonInfo addExp(PokemonInfo target, int exp)  {
        Bulbasaur bulbasaur = new Bulbasaur(
                target.gender(),
                target.nature(),
                target.individualValue(),
                target.effortValue().add(20,30,60,10,30,30),
                target.level(),
                target.experience().add(exp),
                target.currentHitPoint()
        );
        System.out.println(target.pokeName() + "は" + exp + "の経験値を獲得!");
        while(bulbasaur.experience().isLevelUp(bulbasaur)) {
            bulbasaur = new Bulbasaur(
                    bulbasaur.gender(),
                    bulbasaur.nature(),
                    bulbasaur.individualValue(),
                    bulbasaur.effortValue(),
                    bulbasaur.level().add(),
                    bulbasaur.experience(),
                    bulbasaur.currentHitPoint()
            );
            System.out.println(target.pokeName() + "はLv." + bulbasaur.level().value() + "にレベルアップした!");
        }
        return bulbasaur;
    }
}
