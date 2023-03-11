package bussinessLogic;

import Enum.Gender;
import Enum.Nature;
import move.BaseMvPrm;
import pokemon.BasePrm;
import pokemon.PokeInfo;
import pokemon.PokeInfoI;
import pokemonStatus.impl.EffortValueI;
import pokemonStatus.impl.IndividualValueI;
import pokemonStatus.impl.LevelI;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static move.MoveI.initMv;
import static pokemon.PokeInfoI.initialize;

// TODO やることリスト
// 初期経験値固定値問題
// 覚える技リスト
// 経験値を得るタイミングを努力値を得る
// レベルアップ時ステータス上昇幅表示
// 6匹まで自分のポケモンをもてるようにする
// 覚えられる技は4つまで

// バトルファクトリーに必要なもの
// もちもの
// PPが0のときはわるあがきする
// 草タイプはねむりごな等無効
// とくせい
// バインド、のろい、ちょうはつ、こだわり
// リフレクター、ひかりのかべ
// すでにやけどになっているのメッセージ表示
// BattleSimulationのロジック最適化
// テストを充実させる
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        PokeInfo myPokemon = initialize(BasePrm.CHARIZARD);

        String inputCommand = "";
        while (!inputCommand.equals("q")) {
            System.out.println("-------------------------------------------------");
            System.out.print("i:ステータス表示");
            System.out.print("　　m:技表示  ");
            System.out.println("　　　       e:経験値を与える");
            System.out.print("r:体力回復");
            System.out.print("          b:野生ポケモンと戦闘");
            System.out.print(" 　f:バトルファクトリー");
            System.out.println(" 　q:終了");
            System.out.print("コマンドを入力してください > ");
            inputCommand = scanner.nextLine();

            switch (inputCommand) {
                case "i" -> ConsoleOutManager.showAllParameters(myPokemon);
                case "m" -> ConsoleOutManager.showMoveDetail(myPokemon.haveMove());
                case "e" -> myPokemon = myPokemon.addExp(200);
                case "r" -> myPokemon = myPokemon.recoveryAll();
                case "b" -> myPokemon = new BattleSimulation().battleSimulation(myPokemon, initialize(BasePrm.BLASTOISE));
                case "f" -> myPokemon = new BattleSimulation().battleSimulation(
                        new PokeInfoI(
                                BasePrm.CHARIZARD,
                                Gender.MALE,
                                Nature.MODEST,
                                new IndividualValueI(10, 10, 10, 10, 10, 10),
                                new EffortValueI(252, 0, 0, 0, 0, 252),
                                new LevelI(50),
                                List.of(initMv(BaseMvPrm.AIR_SLASH), initMv(BaseMvPrm.SUNNY_DAY), initMv(BaseMvPrm.RAIN_DANCE), initMv(BaseMvPrm.THUNDER))
                        ),
                        new PokeInfoI(
                                randomPoke(),
                                Gender.MALE,
                                Nature.MODEST,
                                new IndividualValueI(10, 10, 10, 10, 10, 10),
                                new EffortValueI(252, 0, 0, 252, 0, 6),
                                new LevelI(50),
                                List.of(initMv(randomMv()), initMv(randomMv()), initMv(randomMv()), initMv(randomMv()))
                        ));
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
