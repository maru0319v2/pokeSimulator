package bussinessLogic;

import field.Field;
import field.Weather;
import move.Move;
import pokemon.PokemonInfo;
import statusAilment.Ailment;
import Enum.*;

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
            if(myPokemon.getCurrentHitPoint().isDead() || enemyPokemon.getCurrentHitPoint().isDead()) { break; }
            // ターン終了処理
            onBattleField = endTurnProcess(myPokemon, enemyPokemon, field);
            myPokemon = onBattleField.attackPoke;
            enemyPokemon = onBattleField.defencePoke;
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

    private OnBattleField endTurnProcess(PokemonInfo myPokemon, PokemonInfo enemyPokemon, Field field) throws InterruptedException {

        Thread.sleep(100);
        showPokemonInfo(myPokemon, enemyPokemon);
        field = field.elapseTurn();

        // TODO　この辺のテストしたい
        if(field.getWeather() == Weather.SANDSTORM) {
            if(myPokemon.getBasePrm().getType1() != Type.ROCK || myPokemon.getBasePrm().getType2() != Type.ROCK ||
                    myPokemon.getBasePrm().getType1() != Type.GROUND || myPokemon.getBasePrm().getType2() != Type.GROUND ||
                    myPokemon.getBasePrm().getType1() != Type.STEEL || myPokemon.getBasePrm().getType2() != Type.STEEL) {
                myPokemon = field.slipDamageBySandStorm(myPokemon);
            }
            if(enemyPokemon.getBasePrm().getType1() != Type.ROCK || enemyPokemon.getBasePrm().getType2() != Type.ROCK ||
                    enemyPokemon.getBasePrm().getType1() != Type.GROUND || enemyPokemon.getBasePrm().getType2() != Type.GROUND ||
                    enemyPokemon.getBasePrm().getType1() != Type.STEEL || enemyPokemon.getBasePrm().getType2() != Type.STEEL) {
                enemyPokemon = field.slipDamageBySandStorm(enemyPokemon);
            }
        }
        if(field.getWeather() == Weather.HAIL) {
            if(myPokemon.getBasePrm().getType1() != Type.ICE || myPokemon.getBasePrm().getType2() != Type.ICE) {
                myPokemon = field.slipDamageByHail(myPokemon);
            }
            if(enemyPokemon.getBasePrm().getType1() != Type.ICE || enemyPokemon.getBasePrm().getType2() != Type.ICE) {
                enemyPokemon = field.slipDamageByHail(enemyPokemon);
            }
        }
        if(myPokemon.getStatusAilment().getValue() == Ailment.POISON) {
            myPokemon.getStatusAilment().slipDamageByPoison(myPokemon);
        }
        if(myPokemon.getStatusAilment().getValue() == Ailment.BAD_POISON) {
            myPokemon.getStatusAilment().slipDamageByPoison(myPokemon);
        }
        if(myPokemon.getStatusAilment().getValue() == Ailment.BURN) {
            myPokemon.getStatusAilment().slipDamageByPoison(myPokemon);
        }
        if(enemyPokemon.getStatusAilment().getValue() == Ailment.POISON) {
            enemyPokemon.getStatusAilment().slipDamageByPoison(enemyPokemon);
        }
        if(enemyPokemon.getStatusAilment().getValue() == Ailment.BAD_POISON) {
            enemyPokemon.getStatusAilment().slipDamageByPoison(enemyPokemon);
        }
        if(enemyPokemon.getStatusAilment().getValue() == Ailment.BURN) {
            enemyPokemon.getStatusAilment().slipDamageByPoison(enemyPokemon);
        }

        return new OnBattleField(myPokemon, enemyPokemon, field);
    }


}
