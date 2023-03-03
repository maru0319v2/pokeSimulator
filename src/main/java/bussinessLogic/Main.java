package bussinessLogic;

import move.BaseMPrm;
import move.MoveImpl;
import pokemon.PokemonInfo;
import pokemon.*;
import pokemonStatus.impl.*;
import Enum.*;

import java.util.List;
import java.util.Scanner;

import static bussinessLogic.ConsoleOutManager.showMessageParChar;

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
// 命中ランク、回避ランク
// とくせい
// ひるみ判定
// こんらん、バインド、のろい、ちょうはつ、こだわり
// ひでりのときに炎技1.5倍、水技半減、雨のときに雨技1.5倍、炎技半減、砂嵐のときに岩特防1.5倍
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
                case "1" -> myPokemon = new PokemonInfoImpl(BasePrm.BULBASAUR);
                case "2" -> myPokemon = new PokemonInfoImpl(BasePrm.CHARMANDER);
                case "3" -> myPokemon = new PokemonInfoImpl(BasePrm.SQUIRTLE);
            }
        }

        System.out.print("\033[H\033[2J");
        System.out.flush();
        showMessageParChar(myPokemon.getBasePrm().getName() + "を仲間にした!");

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
                case "m" -> ConsoleOutManager.showMoveDetail(myPokemon.getHaveMove());
                case "e" -> myPokemon = myPokemon.addExp(200);
                case "r" -> myPokemon = myPokemon.recoveryAll();
                case "b" -> myPokemon = new BattleSimulation().battleSimulation(myPokemon, new PokemonInfoImpl(BasePrm.CHARMANDER));
                case "f" -> myPokemon = new BattleSimulation().battleSimulation(
                        new PokemonInfoImpl(
                                BasePrm.CHARIZARD,
                                Gender.MALE,
                                Nature.MODEST,
                                new IndividualValueImpl(10, 10, 10, 10, 10 , 10),
                                new EffortValueImpl(6, 0, 0, 252, 0, 252),
                                new LevelImpl(50),
                                List.of(new MoveImpl(BaseMPrm.FLAMETHROWER), new MoveImpl(BaseMPrm.SUNNY_DAY), new MoveImpl(BaseMPrm.WILL_O_WISP), new MoveImpl(BaseMPrm.SAND_STORM))
                        ),
                        new PokemonInfoImpl(
                                BasePrm.VENUSAUR,
                                Gender.MALE,
                                Nature.MODEST,
                                new IndividualValueImpl(10, 10, 10, 10, 10 , 10),
                                new EffortValueImpl(252, 0, 0, 252, 0 , 6),
                                new LevelImpl(50),
                                List.of(new MoveImpl(BaseMPrm.SLEEP_POWDER), new MoveImpl(BaseMPrm.GIGA_DRAIN), new MoveImpl(BaseMPrm.QUICK_ATTACK), new MoveImpl(BaseMPrm.RAIN_DANCE))
                        ));
            }
        }
        scanner.close();
    }
}
