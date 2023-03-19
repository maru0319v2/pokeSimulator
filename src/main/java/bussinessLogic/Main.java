package bussinessLogic;

import move.BaseMvPrm;
import pokemon.BasePrm;
import pokemon.PokeInfo;
import pokemon.RentalPoke;

import java.util.List;
import java.util.Random;
import java.util.Scanner;


// TODO やることリスト
// PPが0のときはわるあがきする
// とくせい
// バインド、のろい、ちょうはつ、こだわり
// すでにやけどになっているのメッセージ表示
// 相手のもつたべのこしが発動していない
// BattleSimulationのロジック最適化
// ポケモン交代
// テストを充実させる
public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        Scanner scanner = new Scanner(System.in);

        List<PokeInfo> randomRentalPoke = RentalPoke.randomRental(3);
        for (PokeInfo p : randomRentalPoke) {
            ConsoleOutManager.showAllParameters(p);
        }

        String inputCommand = "";
        int wins = 0;
        while (!inputCommand.equals("q")) {
            System.out.println("-------------------------------------------------");
            System.out.println("現在 " + wins + "連勝");
            System.out.println("b:バトルファクトリーへ");
            System.out.print("コマンドを入力してください > ");
            inputCommand = scanner.nextLine();

            if ("b".equals(inputCommand)) {
                if (new BattleSimulation().initBattle(randomRentalPoke, RentalPoke.randomCPURental(randomRentalPoke))) {
                    wins++;
                } else {
                    wins = 0;
                }
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
