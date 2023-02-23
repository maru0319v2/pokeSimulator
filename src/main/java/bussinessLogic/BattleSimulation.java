package bussinessLogic;

import static bussinessLogic.BattleLogic.doAction;
import static bussinessLogic.ConsoleOutManager.showMessageParChar;

public class BattleSimulation {
    public PokemonInfo battleSimulation(PokemonInfo myPokemon, PokemonInfo enemyPokemon) throws InterruptedException {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        showMessageParChar("野生の" + enemyPokemon.pokeName() + "が飛び出してきた!");
        showMessageParChar("ゆけっ!" + myPokemon.pokeName() + "!");

        while (myPokemon.currentHitPoint().value() > 0 && enemyPokemon.currentHitPoint().value() > 0) {
            Thread.sleep(1000);
            ConsoleOutManager.showPokemonInfoWithClear(myPokemon, enemyPokemon);
            Move selectedMove = BattleLogic.selectMove(myPokemon.haveMove());

            if(BattleLogic.isPreemptiveMe(myPokemon.realValSpeed(), enemyPokemon.realValSpeed())) {
                // 自分が先行の場合
                enemyPokemon = doAction(myPokemon, enemyPokemon, selectedMove);

                if(enemyPokemon.currentHitPoint().value() == 0) { break; }
                Thread.sleep(1000);
                ConsoleOutManager.showPokemonInfoWithClear(myPokemon, enemyPokemon);

                myPokemon = doAction(enemyPokemon, myPokemon, enemyPokemon.haveMove().get(0));
            } else {
                // 自分が後攻の場合
                myPokemon = doAction(enemyPokemon, myPokemon, enemyPokemon.haveMove().get(0));

                if(myPokemon.currentHitPoint().value() == 0) { break; }
                Thread.sleep(1000);
                ConsoleOutManager.showPokemonInfoWithClear(myPokemon, enemyPokemon);

                enemyPokemon = doAction(myPokemon, enemyPokemon, selectedMove);
            }
        }

        ConsoleOutManager.showPokemonInfoWithClear(myPokemon, enemyPokemon);
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
