package bussinessLogic;

import move.Move;
import pokemon.PokemonInfo;

import static bussinessLogic.BattleLogic.*;
import static bussinessLogic.ConsoleOutManager.showMessageParChar;
import static bussinessLogic.ConsoleOutManager.showPokemonInfo;

public class BattleSimulation {
    public PokemonInfo battleSimulation(PokemonInfo myPokemon, PokemonInfo enemyPokemon) throws InterruptedException {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        showMessageParChar("野生の" + enemyPokemon.getBasePrm().getName() + "が飛び出してきた!");
        showMessageParChar("ゆけっ!" + myPokemon.getBasePrm().getName() + "!");

        InBattlePokemons pokemons;

        while (myPokemon.getCurrentHitPoint().value() > 0 && enemyPokemon.getCurrentHitPoint().value() > 0) {
            Thread.sleep(100);
            showPokemonInfo(myPokemon, enemyPokemon);
            Move selectedMove = BattleLogic.selectMove(myPokemon.getHaveMove(), myPokemon);
            Move enemyMove = BattleLogic.enemySelectMove(enemyPokemon, myPokemon);
            showPokemonInfo(myPokemon, enemyPokemon);

            if(BattleLogic.isPreemptiveMe(myPokemon, enemyPokemon)) {
                // 自分が先行の場合
                pokemons = doAction(myPokemon, enemyPokemon, selectedMove);
                myPokemon = pokemons.attackPoke;
                enemyPokemon = pokemons.defencePoke;

                if(enemyPokemon.getCurrentHitPoint().value() == 0) { break; }
                Thread.sleep(100);
                showPokemonInfo(myPokemon, enemyPokemon);

                pokemons = doAction(enemyPokemon, myPokemon, enemyMove);
                myPokemon = pokemons.defencePoke;
                enemyPokemon = pokemons.attackPoke;
            } else {
                // 自分が後攻の場合
                pokemons = doAction(enemyPokemon, myPokemon, enemyMove);
                myPokemon = pokemons.defencePoke;
                enemyPokemon = pokemons.attackPoke;

                if(myPokemon.getCurrentHitPoint().value() == 0) { break; }
                Thread.sleep(100);
                showPokemonInfo(myPokemon, enemyPokemon);

                pokemons = doAction(myPokemon, enemyPokemon, selectedMove);
                myPokemon = pokemons.attackPoke;
                enemyPokemon = pokemons.defencePoke;
            }
        }

        showPokemonInfo(myPokemon, enemyPokemon);
        if(myPokemon.getCurrentHitPoint().value() > 0) {
            System.out.println("野生の" + enemyPokemon.getBasePrm().getName() + "は倒れた!");
            int addExp = BattleLogic.calcExp(enemyPokemon);
            myPokemon = BattleLogic.addExp(myPokemon, addExp);
        } else {
            System.out.println(myPokemon.getBasePrm().getName() + "は倒れた");
        }
        return myPokemon.withResetStatusRank();
    }
}
