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

        while (myPokemon.getCurrentHitPoint().isAlive() && enemyPokemon.getCurrentHitPoint().isAlive()) {
            Thread.sleep(100);
            showPokemonInfo(myPokemon, enemyPokemon);
            Move selectedMove = BattleLogic.selectMove(myPokemon.getHaveMove(), myPokemon);
            Move enemyMove = BattleLogic.enemySelectMove(enemyPokemon, myPokemon);
            showPokemonInfo(myPokemon, enemyPokemon);

            if(BattleLogic.isPreemptiveMe(myPokemon, enemyPokemon, selectedMove, enemyMove)) {
                // 自分が先行の場合
                myPokemon = myPokemon.withStatusAilment(myPokemon.getStatusAilment().comeTurn());
                if(myPokemon.getStatusAilment().canMove()) {
                    pokemons = doAction(myPokemon, enemyPokemon, selectedMove);
                    myPokemon = pokemons.attackPoke;
                    enemyPokemon = pokemons.defencePoke;
                }

                if(enemyPokemon.getCurrentHitPoint().isDead()) { break; }
                Thread.sleep(100);
                showPokemonInfo(myPokemon, enemyPokemon);

                enemyPokemon = enemyPokemon.withStatusAilment(enemyPokemon.getStatusAilment().comeTurn());
                if(enemyPokemon.getStatusAilment().canMove()) {
                    pokemons = doAction(enemyPokemon, myPokemon, enemyMove);
                    myPokemon = pokemons.defencePoke;
                    enemyPokemon = pokemons.attackPoke;
                }
            } else {
                // 自分が後攻の場合

                enemyPokemon = enemyPokemon.withStatusAilment(enemyPokemon.getStatusAilment().comeTurn());
                if(enemyPokemon.getStatusAilment().canMove()) {
                    pokemons = doAction(enemyPokemon, myPokemon, enemyMove);
                    myPokemon = pokemons.defencePoke;
                    enemyPokemon = pokemons.attackPoke;
                }

                if(myPokemon.getCurrentHitPoint().isDead()) { break; }
                Thread.sleep(100);
                showPokemonInfo(myPokemon, enemyPokemon);

                myPokemon = myPokemon.withStatusAilment(myPokemon.getStatusAilment().comeTurn());
                if(myPokemon.getStatusAilment().canMove()) {
                    pokemons = doAction(myPokemon, enemyPokemon, selectedMove);
                    myPokemon = pokemons.attackPoke;
                    enemyPokemon = pokemons.defencePoke;
                }
            }
        }

        showPokemonInfo(myPokemon, enemyPokemon);
        if(myPokemon.getCurrentHitPoint().isAlive()) {
            System.out.println("野生の" + enemyPokemon.getBasePrm().getName() + "は倒れた!");
            int addExp = enemyPokemon.giveExp();
            myPokemon = myPokemon.addExp(addExp);
        } else {
            System.out.println(myPokemon.getBasePrm().getName() + "は倒れた");
        }
        return myPokemon.withResetStatusRank();
    }
}
