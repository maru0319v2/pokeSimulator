package bussinessLogic;

import move.Move;
import pokemon.PokemonInfo;

import static bussinessLogic.BattleLogic.doAction;
import static bussinessLogic.ConsoleOutManager.showMessageParChar;

public class BattleSimulation {
    public PokemonInfo battleSimulation(PokemonInfo myPokemon, PokemonInfo enemyPokemon) throws InterruptedException {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        showMessageParChar("野生の" + enemyPokemon.getPokeName() + "が飛び出してきた!");
        showMessageParChar("ゆけっ!" + myPokemon.getPokeName() + "!");

        InBattlePokemons pokemons;

        while (myPokemon.getCurrentHitPoint().value() > 0 && enemyPokemon.getCurrentHitPoint().value() > 0) {
            Thread.sleep(100);
            ConsoleOutManager.showPokemonInfoWithClear(myPokemon, enemyPokemon);
            Move selectedMove = BattleLogic.selectMove(myPokemon.getHaveMove(), myPokemon);
            ConsoleOutManager.showPokemonInfoWithClear(myPokemon, enemyPokemon);

            if(BattleLogic.isPreemptiveMe(myPokemon, enemyPokemon)) {
                // 自分が先行の場合
                pokemons = doAction(myPokemon, enemyPokemon, selectedMove);
                myPokemon = pokemons.attackPoke;
                enemyPokemon = pokemons.defensePoke;

                if(enemyPokemon.getCurrentHitPoint().value() == 0) { break; }
                Thread.sleep(100);
                ConsoleOutManager.showPokemonInfoWithClear(myPokemon, enemyPokemon);

                pokemons = doAction(enemyPokemon, myPokemon, enemyPokemon.getHaveMove().get(0));
                myPokemon = pokemons.defensePoke;
                enemyPokemon = pokemons.attackPoke;
            } else {
                // 自分が後攻の場合
                pokemons = doAction(enemyPokemon, myPokemon, enemyPokemon.getHaveMove().get(0));
                myPokemon = pokemons.defensePoke;
                enemyPokemon = pokemons.attackPoke;

                if(myPokemon.getCurrentHitPoint().value() == 0) { break; }
                Thread.sleep(100);
                ConsoleOutManager.showPokemonInfoWithClear(myPokemon, enemyPokemon);

                pokemons = doAction(myPokemon, enemyPokemon, selectedMove);
                myPokemon = pokemons.attackPoke;
                enemyPokemon = pokemons.defensePoke;
            }
        }

        ConsoleOutManager.showPokemonInfoWithClear(myPokemon, enemyPokemon);
        if(myPokemon.getCurrentHitPoint().value() > 0) {
            System.out.println("野生の" + enemyPokemon.getPokeName() + "は倒れた!");
            int addExp = BattleLogic.calcExp(enemyPokemon);
            myPokemon = BattleLogic.addExp(myPokemon, addExp);
        } else {
            System.out.println(myPokemon.getPokeName() + "は倒れた");
        }
        return myPokemon.withResetStatusRank();
    }
}
