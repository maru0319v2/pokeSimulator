package main.java.org.example;


import java.util.Random;


import static main.java.org.example.Main.*;

public class BattleSimulation {
    public PokemonInfo battleSimulation(PokemonInfo myPokemon, PokemonInfo enemyPokemon) throws InterruptedException {

        if(isPreemptiveMe(myPokemon.realValSpeed(), enemyPokemon.realValSpeed())) {
            int enemyDamage = Main.calcDamage(myPokemon, enemyPokemon, myPokemon.haveMove());
            enemyPokemon = damagePoke(enemyPokemon, enemyDamage);
            Thread.sleep(500);
        }
        while (myPokemon.currentHitPoint().value() > 0 && enemyPokemon.currentHitPoint().value() > 0) {
            // ダメージ計算(敵 -> 自分)
            int myDamage = Main.calcDamage(enemyPokemon, myPokemon, enemyPokemon.haveMove());
            // 自分にダメージを与える
            myPokemon = damagePoke(myPokemon, myDamage);
            if(myPokemon.currentHitPoint().value() == 0) {
                break;
            }
            Thread.sleep(500);
            // ダメージ計算(自分 ->　敵)
            int enemyDamage = Main.calcDamage(myPokemon, enemyPokemon, myPokemon.haveMove());
            // 相手にダメージを与える
            enemyPokemon = damagePoke(enemyPokemon, enemyDamage);
            Thread.sleep(500);
        }

        if(myPokemon.currentHitPoint().value() > 0) {
            System.out.println(enemyPokemon.pokeName() + "を倒した!");
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
}
