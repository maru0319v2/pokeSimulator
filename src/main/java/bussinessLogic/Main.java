package bussinessLogic;

import move.BaseMvPrm;
import pokemon.BasePrm;
import pokemon.PokeInfo;
import pokemon.PokeInfoI;
import pokemon.RentalPoke;

import java.util.Random;
import java.util.Scanner;

import static pokemon.RentalPoke.VENUSAUR_1;


// TODO やることリスト
// 初期経験値固定値問題
// 覚える技リスト
// 経験値を得るタイミングを努力値を得る
// レベルアップ時ステータス上昇幅表示
// 6匹まで自分のポケモンをもてるようにする
// 覚えられる技は4つまで

// バトルファクトリーに必要なもの
// PPが0のときはわるあがきする
// 草タイプはねむりごな等無効
// とくせい
// バインド、のろい、ちょうはつ、こだわり
// すでにやけどになっているのメッセージ表示
// BattleSimulationのロジック最適化
// テストを充実させる
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        PokeInfo myPokemon = PokeInfoI.init(BasePrm.CHARIZARD);

        String inputCommand = "";
        while (!inputCommand.equals("q")) {
            System.out.println("-------------------------------------------------");
            System.out.print("i:ステータス表示");
            System.out.print("　　m:技表示  ");
            System.out.print("r:体力回復");
            System.out.print("          b:野生ポケモンと戦闘");
            System.out.print(" 　f:バトルファクトリー");
            System.out.println(" 　q:終了");
            System.out.print("コマンドを入力してください > ");
            inputCommand = scanner.nextLine();

            switch (inputCommand) {
                case "i" -> ConsoleOutManager.showAllParameters(myPokemon);
                case "m" -> ConsoleOutManager.showMoveDetail(myPokemon.haveMove());
                case "r" -> myPokemon = myPokemon.recoveryAll();
                case "b" -> myPokemon = new BattleSimulation().battleSimulation(myPokemon, PokeInfoI.init(BasePrm.BLASTOISE));
                case "f" -> myPokemon = new BattleSimulation().battleSimulation(
                        //RentalPoke.randomRental(),
                        RentalPoke.rent(VENUSAUR_1),
                        RentalPoke.randomRental());
            }
        }
        scanner.close();
    }

    // ランダムにBasePrmの中から1体選択する
    private static BasePrm randomPoke() {
        int pick = new Random().nextInt(BasePrm.values().length);
        return BasePrm.values()[pick];
    }

    // ランダムにBaseMvPrmの中から1体選択する
    private static BaseMvPrm randomMv() {
        int pick = new Random().nextInt(BaseMvPrm.values().length);
        return BaseMvPrm.values()[pick];
    }
}
