package bussinessLogic;

import field.Field;
import field.FieldImpl;
import field.Weather;
import move.Move;
import pokemon.PokemonInfo;

import static bussinessLogic.BattleLogic.*;
import static bussinessLogic.ConsoleOutManager.showMessageParChar;
import static bussinessLogic.ConsoleOutManager.showPokemonInfo;
import static field.FieldImpl.initializeField;

public class BattleSimulation {
    public PokemonInfo battleSimulation(PokemonInfo myPokemon, PokemonInfo enemyPokemon) throws InterruptedException {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        showMessageParChar("野生の" + enemyPokemon.getBasePrm().getName() + "が飛び出してきた!");
        showMessageParChar("ゆけっ!" + myPokemon.getBasePrm().getName() + "!");

        OnBattleField onBattleField;
        Field field = initializeField();

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
                    onBattleField = doAction(myPokemon, enemyPokemon, field, selectedMove);
                    myPokemon = onBattleField.attackPoke;
                    enemyPokemon = onBattleField.defencePoke;
                    field = onBattleField.field;
                }

                if(enemyPokemon.getCurrentHitPoint().isDead()) { break; }
                Thread.sleep(100);
                showPokemonInfo(myPokemon, enemyPokemon);

                enemyPokemon = enemyPokemon.withStatusAilment(enemyPokemon.getStatusAilment().comeTurn());
                if(enemyPokemon.getStatusAilment().canMove()) {
                    onBattleField = doAction(enemyPokemon, myPokemon, field, enemyMove);
                    myPokemon = onBattleField.defencePoke;
                    enemyPokemon = onBattleField.attackPoke;
                    field = onBattleField.field;
                }
            } else {
                // 自分が後攻の場合
                enemyPokemon = enemyPokemon.withStatusAilment(enemyPokemon.getStatusAilment().comeTurn());
                if(enemyPokemon.getStatusAilment().canMove()) {
                    onBattleField = doAction(enemyPokemon, myPokemon, field,  enemyMove);
                    myPokemon = onBattleField.defencePoke;
                    enemyPokemon = onBattleField.attackPoke;
                    field = onBattleField.field;
                }

                if(myPokemon.getCurrentHitPoint().isDead()) { break; }
                Thread.sleep(100);
                showPokemonInfo(myPokemon, enemyPokemon);

                myPokemon = myPokemon.withStatusAilment(myPokemon.getStatusAilment().comeTurn());
                if(myPokemon.getStatusAilment().canMove()) {
                    onBattleField = doAction(myPokemon, enemyPokemon, field,  selectedMove);
                    myPokemon = onBattleField.attackPoke;
                    enemyPokemon = onBattleField.defencePoke;
                    field = onBattleField.field;
                }
            }
            // ターン終了処理
            field = field.elapseTurn();
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
