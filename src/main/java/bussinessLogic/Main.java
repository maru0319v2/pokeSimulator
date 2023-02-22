package bussinessLogic;

import move.Tackle;
import pokemon.Bulbasaur;
import pokemon.Charmander;
import pokemon.Squirtle;

import java.io.IOException;
import java.util.Scanner;


// TODO やることリスト
// 状態異常クラス
// 技が外れた場合
// ランク上昇 下降
// 初期経験値固定値問題
// 覚える技リスト
// 経験値を得るタイミングを努力値を得る
// レベルアップ時ステータス上昇幅表示
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
                case "i" -> ConsoleOutManager.showAllParameters(myPokemon);
                case "m" -> ConsoleOutManager.showMoveDetail(new Tackle());
                case "e" -> myPokemon = BattleLogic.addExp(myPokemon, 200);
                case "d" -> myPokemon = BattleLogic.damagePoke(myPokemon, 10);
                case "r" -> myPokemon = BattleLogic.recoveryPoke(myPokemon, 20);
                case "c" -> BattleLogic.calcDamage(myPokemon, new Charmander(), new Tackle());
                case "b" -> myPokemon = new BattleSimulation().battleSimulation(myPokemon, new Charmander());
                case "ce" -> BattleLogic.calcExp(new Charmander());
            }
        }
        scanner.close();
    }
}
