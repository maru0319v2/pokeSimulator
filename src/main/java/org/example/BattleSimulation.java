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
            showPokemonsInfo(myPokemon, enemyPokemon);
            Move selectedMove = selectMove(myPokemon.haveMove());

            if(isPreemptiveMe(myPokemon.realValSpeed(), enemyPokemon.realValSpeed())) {
                // 自分が先行の場合
                int enemyDamage = Main.calcDamage(myPokemon, enemyPokemon, selectedMove);
                Thread.sleep(1000);
                enemyPokemon = damagePoke(enemyPokemon, enemyDamage);
                if(enemyPokemon.currentHitPoint().value() == 0) { break; }

                Thread.sleep(1000);
                int myDamage = Main.calcDamage(enemyPokemon, myPokemon, enemyPokemon.haveMove().get(0));
                Thread.sleep(1000);
                myPokemon = damagePoke(myPokemon, myDamage);
                Thread.sleep(1000);
            } else {
                // 自分が後攻の場合
                int myDamage = Main.calcDamage(enemyPokemon, myPokemon, enemyPokemon.haveMove().get(0));
                Thread.sleep(1000);
                myPokemon = damagePoke(myPokemon, myDamage);
                if(myPokemon.currentHitPoint().value() == 0) { break; }

                Thread.sleep(1000);
                int enemyDamage = Main.calcDamage(myPokemon, enemyPokemon, selectedMove);
                Thread.sleep(1000);
                enemyPokemon = damagePoke(enemyPokemon, enemyDamage);
                Thread.sleep(1000);
            }
        }

        if(myPokemon.currentHitPoint().value() > 0) {
            System.out.println("野生の" + enemyPokemon.pokeName() + "は倒れた!");
            int addExp = calcExp(enemyPokemon);
            myPokemon = addExp(myPokemon, addExp);
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
            int inputCommand = scanner.nextInt();
            System.out.println("");
            for(int j = 1; i > j; j++) {
                if(inputCommand == j) {
                    result = moves.get(j-1);
                    isNoNumberSelected = false;
                }
            }
        }
        return result;
    }

    private void showPokemonsInfo(PokemonInfo myPokemon, PokemonInfo enemyPokemon) {
        System.out.println("-------------------------------------------------");
        System.out.println("");

        System.out.println(enemyPokemon.pokeName() + " " + enemyPokemon.gender().value() + "    Lv." + enemyPokemon.level().value());
        System.out.print("HP");
        showProgressBar(enemyPokemon);
        System.out.println("           ■");
        System.out.print("   " + enemyPokemon.currentHitPoint().value() + " / " + enemyPokemon.realValHitPoint());
        System.out.println("");
        System.out.println("");
        System.out.println("");
        // ↑相手側　↓こっち側
        System.out.print("                        ");
        System.out.println(myPokemon.pokeName() + " " + myPokemon.gender().value() + "    Lv." + myPokemon.level().value());
        System.out.print("            ■           HP");
        showProgressBar(myPokemon);
        System.out.println("");
        System.out.println("                           " + myPokemon.currentHitPoint().value() + " / " + myPokemon.realValHitPoint());
        System.out.println("");
        System.out.println("-------------------------------------------------");
    }
}
