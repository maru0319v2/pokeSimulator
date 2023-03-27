package bussinessLogic;

import pokemon.PokeInfo;
import pokemon.RentalPoke;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


// TODO やることリスト
// PPが0のときはわるあがきする
// とくせい
// バインド、のろい、ちょうはつ、こだわり
// すでにやけどになっているのメッセージ表示
// BattleSimulationのロジック最適化
// ポケモン交代
// テストを充実させる
public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        Scanner scanner = new Scanner(System.in);
        List<PokeInfo> selectedRentalPoke = selectRentalPoke();

        String inputCommand = "";
        int wins = 0;
        while (!inputCommand.equals("q")) {
            System.out.println("-------------------------------------------------");
            System.out.println("現在 " + wins + "連勝");
            System.out.println("1: " + selectedRentalPoke.get(0).basePrm().pName());
            System.out.println("2: " + selectedRentalPoke.get(1).basePrm().pName());
            System.out.println("3: " + selectedRentalPoke.get(2).basePrm().pName());
            System.out.println("");
            System.out.println("r:レンタルポケモン選択");
            System.out.println("b:バトルファクトリーへ");
            System.out.print("コマンドを入力してください > ");
            inputCommand = scanner.nextLine();

            if ("b".equals(inputCommand)) {
                if (new BattleSimulation().initBattle(selectedRentalPoke, RentalPoke.randomCPURental(selectedRentalPoke))) {
                    wins++;
                } else {
                    wins = 0;
                }
            }
            if ("r".equals(inputCommand)) {
                selectedRentalPoke = selectRentalPoke();
            }
        }
        scanner.close();
    }

    private static List<PokeInfo> selectRentalPoke() {
        Scanner scanner = new Scanner(System.in);

        List<PokeInfo> selectedRentalPoke = new ArrayList<>(3);
        List<PokeInfo> randomRentalPoke = RentalPoke.randomRental(6);
        int i = 0;
        for (PokeInfo p : randomRentalPoke) {
            System.out.print("[" + i + "]");
            ConsoleOutManager.showAllParameters(p);
            System.out.println("");
            i++;
        }
        System.out.println("-------------------------------------------------");
        boolean disableNumSelected1 = true;
        int pk1Index = 0;
        while (disableNumSelected1) {
            System.out.print("1匹目のポケモンを選択してください > ");
            pk1Index = Integer.parseInt(scanner.nextLine());
            if (pk1Index < 0 || pk1Index > 5) {
                System.out.println("有効な数字ではありません。");
            } else {
                selectedRentalPoke.add(randomRentalPoke.get(pk1Index));
                disableNumSelected1 = false;
            }
        }

        boolean disableNumSelected2 = true;
        int pk2Index = 0;
        while (disableNumSelected2) {
            System.out.print("2匹目のポケモンを選択してください > ");
            pk2Index = Integer.parseInt(scanner.nextLine());
            if (pk2Index == pk1Index) {
                System.out.println("同じポケモンが選択されています。");
            } else if (pk2Index < 0 || pk2Index > 5) {
                System.out.println("有効な数字ではありません。");
            } else {
                selectedRentalPoke.add(randomRentalPoke.get(pk2Index));
                disableNumSelected2 = false;
            }
        }

        boolean disableNumSelected3 = true;
        int pk3Index = 0;
        while (disableNumSelected3) {
            System.out.print("3匹目のポケモンを選択してください > ");
            pk3Index = Integer.parseInt(scanner.nextLine());
            if (pk3Index == pk1Index || pk3Index == pk2Index) {
                System.out.println("同じポケモンが選択されています。");
            } else if (pk3Index < 0 || pk3Index > 5) {
                System.out.println("有効な数字ではありません。");
            } else {
                selectedRentalPoke.add(randomRentalPoke.get(pk3Index));
                disableNumSelected3 = false;
            }
        }
        return selectedRentalPoke;
    }
}
