package main.java.org.example;

import main.java.org.example.move.Tackle;
import main.java.org.example.pokemon.Bulbasaur;

import java.util.Random;
import java.util.Scanner;

import static main.java.org.example.Main.damagePoke;

public class BattleSimulation {
    public PokemonInfo battleSimulation(PokemonInfo myPokemon, PokemonInfo enemyPokemon) {

        if(isPreemptiveMe(myPokemon.realValSpeed(), enemyPokemon.realValSpeed())) {
            while (myPokemon.currentHitPoint().value() != 0 || enemyPokemon.currentHitPoint().value() != 0) {
                // ダメージ計算(自分 ->　敵)
                int enemyDamage = Main.calcDamage(myPokemon, enemyPokemon, myPokemon.haveMove());
                // 相手にダメージを与える
                enemyPokemon = damagePoke(enemyPokemon, enemyDamage);
                // ダメージ計算(敵 -> 自分)
                int myDamage = Main.calcDamage(enemyPokemon, myPokemon, enemyPokemon.haveMove());
                // 自分にダメージを与える
                myPokemon = damagePoke(myPokemon, myDamage);
            }
        } else {
            while (myPokemon.currentHitPoint().value() != 0 || enemyPokemon.currentHitPoint().value() != 0) {
                // ダメージ計算(敵 -> 自分)
                int myDamage = Main.calcDamage(enemyPokemon, myPokemon, enemyPokemon.haveMove());
                // 自分にダメージを与える
                myPokemon = damagePoke(myPokemon, myDamage);
                // ダメージ計算(自分 ->　敵)
                int enemyDamage = Main.calcDamage(myPokemon, enemyPokemon, myPokemon.haveMove());
                // 相手にダメージを与える
                enemyPokemon = damagePoke(enemyPokemon, enemyDamage);
            }
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
}
