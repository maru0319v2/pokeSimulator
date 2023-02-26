package bussinessLogic;

import pokemon.PokemonInfo;
import pokemon.*;
import java.util.Scanner;

import static bussinessLogic.ConsoleOutManager.showMessageParChar;

// TODO やることリスト
// 初期経験値固定値問題
// 覚える技リスト
// 経験値を得るタイミングを努力値を得る
// レベルアップ時ステータス上昇幅表示
// 相手のステータスランク表示
// 6匹まで自分のポケモンをもてるようにする
// 覚えられる技は4つまで
// もちもの
// PPが0のときはわるあがきする
// 技の優先度
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
            System.out.println(" 　q:終了");
            System.out.print("コマンドを入力してください > ");
            inputCommand = scanner.nextLine();

            switch (inputCommand) {
                case "i" -> ConsoleOutManager.showAllParameters(myPokemon);
                case "m" -> ConsoleOutManager.showMoveDetail(myPokemon.getHaveMove());
                case "e" -> myPokemon = BattleLogic.addExp(myPokemon, 200);
                case "r" -> myPokemon = BattleLogic.recoveryAll(myPokemon);
                case "b" -> myPokemon = new BattleSimulation().battleSimulation(myPokemon, new PokemonInfoImpl(BasePrm.CHARMANDER));
            }
        }
        scanner.close();
    }
}
