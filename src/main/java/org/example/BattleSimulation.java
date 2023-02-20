package main.java.org.example;

public class BattleSimulation {
    public PokemonInfo battleSimulation(PokemonInfo myPokemon, PokemonInfo enemyPokemon) throws InterruptedException {
        System.out.println("");
        System.out.println("野生の" + enemyPokemon.pokeName() + "が飛び出してきた!");
        System.out.println("ゆけっ!" + myPokemon.pokeName() + "!");

        while (myPokemon.currentHitPoint().value() > 0 && enemyPokemon.currentHitPoint().value() > 0) {
            ConsoleOutManager.showPokemonInfo(myPokemon, enemyPokemon);
            Move selectedMove = BattleLogic.selectMove(myPokemon.haveMove());

            if(BattleLogic.isPreemptiveMe(myPokemon.realValSpeed(), enemyPokemon.realValSpeed())) {
                // 自分が先行の場合
                int enemyDamage = BattleLogic.calcDamage(myPokemon, enemyPokemon, selectedMove);
                enemyPokemon = BattleLogic.damagePoke(enemyPokemon, enemyDamage);
                if(enemyPokemon.currentHitPoint().value() == 0) { break; }

                Thread.sleep(500);
                int myDamage = BattleLogic.calcDamage(enemyPokemon, myPokemon, enemyPokemon.haveMove().get(0));
                myPokemon = BattleLogic.damagePoke(myPokemon, myDamage);
                Thread.sleep(500);
            } else {
                // 自分が後攻の場合
                int myDamage = BattleLogic.calcDamage(enemyPokemon, myPokemon, enemyPokemon.haveMove().get(0));
                myPokemon = BattleLogic.damagePoke(myPokemon, myDamage);
                if(myPokemon.currentHitPoint().value() == 0) { break; }

                Thread.sleep(500);
                int enemyDamage = BattleLogic.calcDamage(myPokemon, enemyPokemon, selectedMove);
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
}
