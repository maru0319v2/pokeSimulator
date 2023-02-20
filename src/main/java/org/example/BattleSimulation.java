package main.java.org.example;


import main.java.org.example.move.Tackle;
import main.java.org.example.pokemon.Bulbasaur;

import java.util.List;
import java.util.Random;
import java.util.Scanner;


import static main.java.org.example.Main.*;

public class BattleSimulation {
    public PokemonInfo battleSimulation(PokemonInfo myPokemon, PokemonInfo enemyPokemon) throws InterruptedException {
        System.out.println("");
        System.out.println("野生の" + enemyPokemon.pokeName() + "が飛び出してきた!");
        System.out.println("ゆけっ!" + myPokemon.pokeName() + "!");

        while (myPokemon.currentHitPoint().value() > 0 && enemyPokemon.currentHitPoint().value() > 0) {
            ConsoleOutManager.showPokemonInfo(myPokemon, enemyPokemon);
            Move selectedMove = selectMove(myPokemon.haveMove());

            if(isPreemptiveMe(myPokemon.realValSpeed(), enemyPokemon.realValSpeed())) {
                // 自分が先行の場合
                int enemyDamage = BattleLogic.calcDamage(myPokemon, enemyPokemon, selectedMove);
                Thread.sleep(500);
                enemyPokemon = BattleLogic.damagePoke(enemyPokemon, enemyDamage);
                if(enemyPokemon.currentHitPoint().value() == 0) { break; }

                Thread.sleep(500);
                int myDamage = BattleLogic.calcDamage(enemyPokemon, myPokemon, enemyPokemon.haveMove().get(0));
                Thread.sleep(500);
                myPokemon = BattleLogic.damagePoke(myPokemon, myDamage);
                Thread.sleep(500);
            } else {
                // 自分が後攻の場合
                int myDamage = BattleLogic.calcDamage(enemyPokemon, myPokemon, enemyPokemon.haveMove().get(0));
                Thread.sleep(500);
                myPokemon = BattleLogic.damagePoke(myPokemon, myDamage);
                if(myPokemon.currentHitPoint().value() == 0) { break; }

                Thread.sleep(500);
                int enemyDamage = BattleLogic.calcDamage(myPokemon, enemyPokemon, selectedMove);
                Thread.sleep(500);
                enemyPokemon = BattleLogic.damagePoke(enemyPokemon, enemyDamage);
                Thread.sleep(500);
            }
        }

        if(myPokemon.currentHitPoint().value() > 0) {
            System.out.println("野生の" + enemyPokemon.pokeName() + "は倒れた!");
            int addExp = BattleLogic.calcExp(enemyPokemon);
            myPokemon = BattleLogic.addExp(myPokemon, addExp);
        } else {
            System.out.println(myPokemon.pokeName() + "は倒れた");
        }

        return myPokemon;
    }

    // 先行後攻を決める
    private boolean isPreemptiveMe(int mySpeed, int enemySpeed) {
        if(mySpeed == enemySpeed) {
            // 同速の場合は1~10をランダムで生成して偶数なら先行
            return (new Random().nextInt(10) + 1) % 2 == 0;
        }
        return mySpeed > enemySpeed;
    }

    // 技を選択する
    private Move selectMove(List<Move> moves) {
        Scanner scanner = new Scanner(System.in);
        int i = 1;
        for (Move move : moves) {
            System.out.println(i + ": " + move.name());
            i++;
        }

        Move result = null;
        boolean isNoNumberSelected = true;
        while (isNoNumberSelected) {
            System.out.print("技を選択してください > ");
            String inputCommand = scanner.nextLine();
            System.out.println("");
            for(int j = 1; i > j; j++) {
                if(Integer.parseInt(inputCommand) == j) {
                    result = moves.get(j-1);
                    isNoNumberSelected = false;
                }
            }
        }
        return result;
    }
}
