package main.java.org.example;

import main.java.org.example.impl.CurrentHitPointImpl;
import main.java.org.example.move.Tackle;
import main.java.org.example.pokemon.Bulbasaur;
import main.java.org.example.pokemon.Charmander;
import main.java.org.example.pokemon.Squirtle;

import java.util.Objects;
import java.util.Random;
import java.util.Scanner;


// TODO やることリスト
// 状態異常クラス
// 技が外れた場合
// ランク上昇 下降
// 最初のポケモンを選ぶ
// 初期経験値固定値問題
// 覚える技リスト
// 経験値を得るタイミングを努力値を得る
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("1:フシギダネ");
        System.out.println("2:ヒトカゲ");
        System.out.println("3:ゼニガメ");

        PokemonInfo myPokemon = null;
        while (myPokemon == null) {
            System.out.print("あなたのポケモンを選択してください > ");
            String selectPokeCommand = scanner.nextLine();
            switch (selectPokeCommand) {
                case "1" -> myPokemon = new Bulbasaur();
                case "2" -> myPokemon = new Charmander();
                case "3" -> myPokemon = new Squirtle();
            }
        }

        String inputCommand = "";
        while (!inputCommand.equals("q")) {
            System.out.println("--------------------------------------");
            System.out.print("i:ステータス表示  ");
            System.out.print("m:技表示  ");
            System.out.print("e:経験値を与える  ");
            System.out.print("d:ダメージを与える  ");
            System.out.print("r:体力回復  ");
            System.out.print("c:ダメージ計算  ");
            System.out.print("b:バトルシミュレーション  ");
            System.out.print("ce:経験値計算  ");
            System.out.println("q:終了");
            System.out.print("コマンドを入力してください > ");
            inputCommand = scanner.nextLine();

            switch (inputCommand) {
                case "i" -> showAllParameters(myPokemon);
                case "m" -> showMoveDetail(new Tackle());
                case "e" -> myPokemon = addExp(myPokemon, 100); // TODO フシギダネが帰ってくる damagePoke()とかを真似して直す
                case "d" -> myPokemon = damagePoke(myPokemon, 10);
                case "r" -> myPokemon = recoveryPoke(myPokemon, 20);
                case "c" -> calcDamage(myPokemon, new Charmander(), new Tackle());
                case "b" -> myPokemon = new BattleSimulation().battleSimulation(myPokemon, new Charmander());
                case "ce" -> calcExp(new Charmander());
            }
        }
        scanner.close();
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
        while(isLevelUp(bulbasaur)) {
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

    public static boolean isLevelUp(PokemonInfo target) {
        int totalExp = target.experience().totalExperience();
        int requireExp = target.experience().requireExperience(target);
        return totalExp >= requireExp;
    }

    public static void showAllParameters(PokemonInfo target) {
        System.out.println("--------------------------------------");
        System.out.print("図鑑No:" + target.pokeDexNo() + " ");
        System.out.print("名前:" + target.pokeName() + " ");
        System.out.println("分類:" + target.species());
        System.out.println("タイプ1: " + target.pokemonType1().value() + " タイプ2: " + target.pokemonType2().value());
        System.out.print("レベル: " + target.level().value());
        System.out.print("  次のレベルまで: " + target.experience().nextRequireExperience(target) + " exp.");
        System.out.println("  総経験値: " + target.experience().totalExperience() + " exp.");

      //  System.out.println("  経験値タイプ: " + target.experienceType());
        System.out.println("性別: " + target.gender().value());
        System.out.println("性格: " + target.nature().value());
        System.out.print("覚えている技: ");
        for (Move move : target.haveMove()) {
            System.out.print(move.name() + "  ");
        }
        System.out.println("");
//        System.out.print("種族値:");
//        System.out.print(" 体力 " + target.baseStats().hitPoint());
//        System.out.print(" 攻撃 " + target.baseStats().attack());
//        System.out.print(" 防御 " + target.baseStats().block());
//        System.out.print(" 特攻 " + target.baseStats().contact());
//        System.out.print(" 特防 " + target.baseStats().defense());
//        System.out.print(" 素早 " + target.baseStats().speed());
//        System.out.println(" 合計 " + (target.baseStats().hitPoint() +
//                                    target.baseStats().attack() +
//                                    target.baseStats().block() +
//                                    target.baseStats().contact() +
//                                    target.baseStats().defense() +
//                                    target.baseStats().speed()));
//        System.out.print("個体値:");
//        System.out.print(" 体力 " + target.individualValue().hitPoint());
//        System.out.print(" 攻撃 " + target.individualValue().attack());
//        System.out.print(" 防御 " + target.individualValue().block());
//        System.out.print(" 特攻 " + target.individualValue().contact());
//        System.out.print(" 特防 " + target.individualValue().defense());
//        System.out.println(" 素早 " + target.individualValue().speed());
//        System.out.print("努力値:");
//        System.out.print(" 体力 " + target.effortValue().hitPoint());
//        System.out.print(" 攻撃 " + target.effortValue().attack());
//        System.out.print(" 防御 " + target.effortValue().block());
//        System.out.print(" 特攻 " + target.effortValue().contact());
//        System.out.print(" 特防 " + target.effortValue().defense());
//        System.out.println(" 素早 " + target.effortValue().speed());
        System.out.print("HP: " + target.currentHitPoint().value() + "/" + target.realValHitPoint() + " ");
        showProgressBar(target);
        System.out.println("");
        System.out.println("攻撃 " + target.realValAttack());
        System.out.println("防御 " + target.realValBlock());
        System.out.println("特攻 " + target.realValContact());
        System.out.println("特防 " + target.realValDefense());
        System.out.println("素早 " + target.realValSpeed());
    }

    public static void showParametersInBattle(PokemonInfo target) {
        System.out.print("名前:" + target.pokeName() + " ");
        System.out.print("レベル: " + target.level().value());
        System.out.println("HP: " + target.currentHitPoint().value() + "/" + target.realValHitPoint());

    }

    public static void showMoveDetail(Move target) {
        System.out.println("--------------------------------------");
        System.out.println("【 技詳細表示 】");
        System.out.println("技　名: " + target.name());
        System.out.println("タイプ: " + target.moveType().value());
        System.out.println("分　類: " + target.moveSpecies().value());
        System.out.println("威　力: " + target.damage());
        System.out.println("命中率: " + target.hitRate());
    }

    public static void showProgressBar(PokemonInfo target) {
        // 残りHPのパーセントを出す
        double i = ((double)target.currentHitPoint().value()) / ((double)target.realValHitPoint()) * 20;
        System.out.print("[");
        int j;
        for(j = 0; i > j; j++) {
            System.out.print("=");
        }
        while (j < 20) {
            System.out.print(" ");
            j++;
        }
        System.out.print("]");
    }
}