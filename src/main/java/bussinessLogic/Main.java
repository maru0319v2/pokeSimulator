package bussinessLogic;

import move.BaseMvPrm;
import pokemon.BasePrm;
import pokemon.PokeInfo;
import pokemon.PokeInfoI;
import pokemon.RentalPoke;

import java.util.List;
import java.util.Random;
import java.util.Scanner;


// TODO やることリスト
// 初期経験値固定値問題
// 覚える技リスト
// 経験値を得るタイミングを努力値を得る
// レベルアップ時ステータス上昇幅表示
// 6匹まで自分のポケモンをもてるようにする
// 覚えられる技は4つまで

// バトルファクトリーに必要なもの
// PPが0のときはわるあがきする
// とくせい
// バインド、のろい、ちょうはつ、こだわり
// すでにやけどになっているのメッセージ表示
// BattleSimulationのロジック最適化
// テストを充実させる
public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        Scanner scanner = new Scanner(System.in);
        PokeInfo myPokemon = PokeInfoI.init(BasePrm.CHARIZARD);

        List<PokeInfo> randomRentalPoke = RentalPoke.randomRental(3);
        for (PokeInfo p : randomRentalPoke) {
            ConsoleOutManager.showAllParameters(p);
        }

        String inputCommand = "";
        while (!inputCommand.equals("q")) {
            System.out.println("-------------------------------------------------");
            System.out.print("i:ステータス表示");
            System.out.print("　　m:技表示  ");
            System.out.println(" 　f:バトルファクトリー");
            System.out.println("q:終了");
            System.out.print("コマンドを入力してください > ");
            inputCommand = scanner.nextLine();

            switch (inputCommand) {
                case "i" -> ConsoleOutManager.showAllParameters(myPokemon);
                case "m" -> ConsoleOutManager.showMoveDetail(myPokemon.haveMove());
                case "f" -> new BattleSimulation().initBattle(randomRentalPoke);
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
