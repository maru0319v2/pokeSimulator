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
// もちもの
// PPが0のときはわるあがきする
// 草タイプはねむりごな等無効、炎タイプはやけどにならない、氷タイプは氷状態にならない、鋼タイプはどくにならない、毒タイプはどくにならない
// とくせい
// バインド、のろい、ちょうはつ、こだわり
// テストを充実させる
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("1:フシギダネ");
        System.out.println("2:ヒトカゲ");
        System.out.println("3:ゼニガメ");

        PokeInfo myPokemon = null;
//        while (myPokemon == null) {
//            System.out.print("あなたのポケモンを選択してください > ");
//            String selectPokeCommand = scanner.nextLine();
//            switch (selectPokeCommand) {
//                case "1" -> myPokemon = new PokemonInfoImpl(BasePrm.BULBASAUR);
//                case "2" -> myPokemon = new PokemonInfoImpl(BasePrm.CHARMANDER);
//                case "3" -> myPokemon = new PokemonInfoImpl(BasePrm.SQUIRTLE);
//            }
//        }
        myPokemon = initialize(BasePrm.BULBASAUR);

        System.out.print("\033[H\033[2J");
        System.out.flush();
        //showMessageParChar(myPokemon.getBasePrm().getName() + "を仲間にした!");

        String inputCommand = "";
        while (!inputCommand.equals("q")) {
            System.out.println("-------------------------------------------------");
            System.out.print("i:ステータス表示");
            System.out.print("　　m:技表示  ");
            System.out.println("　　　       e:経験値を与える");
            System.out.print("r:体力回復");
            System.out.print("          b:野生ポケモンと戦闘");
            System.out.print(" 　f:バトルファクトリーbeta");
            System.out.println(" 　q:終了");
            System.out.print("コマンドを入力してください > ");
            inputCommand = scanner.nextLine();

            switch (inputCommand) {
                case "i" -> ConsoleOutManager.showAllParameters(myPokemon);
                case "m" -> ConsoleOutManager.showMoveDetail(myPokemon.haveMove());
                case "e" -> myPokemon = myPokemon.addExp(200);
                case "r" -> myPokemon = myPokemon.recoveryAll();
                case "b" ->
                        myPokemon = new BattleSimulation().battleSimulation(myPokemon, initialize(BasePrm.CHARMANDER));
                case "f" -> myPokemon = new BattleSimulation().battleSimulation(
                        new PokeInfoI(
                                BasePrm.CHARIZARD,
                                Gender.MALE,
                                Nature.MODEST,
                                new IndividualValueI(10, 10, 10, 10, 10, 10),
                                new EffortValueI(6, 0, 0, 252, 0, 252),
                                new LevelI(50),
                                List.of(initMv(BaseMvPrm.AIR_SLASH), initMv(BaseMvPrm.WATER_PULSE), initMv(BaseMvPrm.WILL_O_WISP), initMv(BaseMvPrm.DOUBLE_TEAM))
                        ),
                        new PokeInfoI(
                                BasePrm.BLASTOISE,
                                Gender.MALE,
                                Nature.MODEST,
                                new IndividualValueI(10, 10, 10, 10, 10, 10),
                                new EffortValueI(252, 0, 0, 252, 0, 6),
                                new LevelI(50),
                                List.of(initMv(BaseMvPrm.SLEEP_POWDER), initMv(BaseMvPrm.GIGA_DRAIN), initMv(BaseMvPrm.QUICK_ATTACK), initMv(BaseMvPrm.AERIAL_ACE))
                        ));
            }
        }
        scanner.close();
    }
}
