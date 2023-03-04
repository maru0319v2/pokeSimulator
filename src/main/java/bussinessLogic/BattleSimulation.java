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
    public PokemonInfo battleSimulation(PokemonInfo myPoke, PokemonInfo enemyPoke) throws InterruptedException {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        showMessageParChar("野生の" + enemyPoke.getBasePrm().getName() + "が飛び出してきた!");
        showMessageParChar("ゆけっ!" + myPoke.getBasePrm().getName() + "!");

        OnBattleField onBattleField;
        Field field = initializeField();

        while (myPoke.getCurrentHitPoint().isAlive() && enemyPoke.getCurrentHitPoint().isAlive()) {
            Thread.sleep(100);
            showPokemonInfo(myPoke, enemyPoke);
            Move selectedMove = BattleLogic.selectMove(myPoke.getHaveMove(), myPoke);
            Move enemyMove = BattleLogic.enemySelectMove(enemyPoke, myPoke);
            showPokemonInfo(myPoke, enemyPoke);

            if(isFirstMe(myPoke, enemyPoke, selectedMove, enemyMove)) {
                // 自分が先行の場合
                myPoke = myPoke.withStatusAilment(myPoke.getStatusAilment().comeTurn());
                if(myPoke.getStatusAilment().canMove()) {
                    onBattleField = doAction(myPoke, enemyPoke, field, selectedMove);
                    myPoke = onBattleField.getAttackPoke();
                    enemyPoke = onBattleField.getDefencePoke();
                    field = onBattleField.getField();
                }

                if(enemyPoke.getCurrentHitPoint().isDead()) { break; }
                Thread.sleep(100);
                showPokemonInfo(myPoke, enemyPoke);

                enemyPoke = enemyPoke.withStatusAilment(enemyPoke.getStatusAilment().comeTurn());
                if(enemyPoke.getStatusAilment().canMove()) {
                    onBattleField = doAction(enemyPoke, myPoke, field, enemyMove);
                    myPoke = onBattleField.getDefencePoke();
                    enemyPoke = onBattleField.getAttackPoke();
                    field = onBattleField.getField();
                }
            } else {
                // 自分が後攻の場合
                enemyPoke = enemyPoke.withStatusAilment(enemyPoke.getStatusAilment().comeTurn());
                if(enemyPoke.getStatusAilment().canMove()) {
                    onBattleField = doAction(enemyPoke, myPoke, field,  enemyMove);
                    myPoke = onBattleField.getDefencePoke();
                    enemyPoke = onBattleField.getAttackPoke();
                    field = onBattleField.getField();
                }

                if(myPoke.getCurrentHitPoint().isDead()) { break; }
                Thread.sleep(100);
                showPokemonInfo(myPoke, enemyPoke);

                myPoke = myPoke.withStatusAilment(myPoke.getStatusAilment().comeTurn());
                if(myPoke.getStatusAilment().canMove()) {
                    onBattleField = doAction(myPoke, enemyPoke, field,  selectedMove);
                    myPoke = onBattleField.getAttackPoke();
                    enemyPoke = onBattleField.getDefencePoke();
                    field = onBattleField.getField();
                }
            }
            if(myPoke.getCurrentHitPoint().isDead() || enemyPoke.getCurrentHitPoint().isDead()) { break; }
            // ターン終了処理
            onBattleField = endTurnProcess(myPoke, enemyPoke, field);
            myPoke = onBattleField.getAttackPoke();
            enemyPoke = onBattleField.getDefencePoke();
        }

        showPokemonInfo(myPoke, enemyPoke);
        if(myPoke.getCurrentHitPoint().isAlive()) {
            System.out.println("野生の" + enemyPoke.getBasePrm().getName() + "は倒れた!");
            int addExp = enemyPoke.giveExp();
            myPoke = myPoke.addExp(addExp);
        } else {
            System.out.println(myPoke.getBasePrm().getName() + "は倒れた");
        }
        return myPoke.withResetStatusRank();
    }

    private OnBattleField endTurnProcess(PokemonInfo myPoke, PokemonInfo enemyPoke, Field field) throws InterruptedException {

        Thread.sleep(100);
        showPokemonInfo(myPoke, enemyPoke);
        field = field.elapseTurn();

        // TODO　この辺のテストしたい
        if(field.getWeather() == Weather.SANDSTORM) {
            if(myPoke.getBasePrm().getType1() != Type.ROCK || myPoke.getBasePrm().getType2() != Type.ROCK ||
                    myPoke.getBasePrm().getType1() != Type.GROUND || myPoke.getBasePrm().getType2() != Type.GROUND ||
                    myPoke.getBasePrm().getType1() != Type.STEEL || myPoke.getBasePrm().getType2() != Type.STEEL) {
                myPoke = field.slipDamageBySandStorm(myPoke);
            }
            if(enemyPoke.getBasePrm().getType1() != Type.ROCK || enemyPoke.getBasePrm().getType2() != Type.ROCK ||
                    enemyPoke.getBasePrm().getType1() != Type.GROUND || enemyPoke.getBasePrm().getType2() != Type.GROUND ||
                    enemyPoke.getBasePrm().getType1() != Type.STEEL || enemyPoke.getBasePrm().getType2() != Type.STEEL) {
                enemyPoke = field.slipDamageBySandStorm(enemyPoke);
            }
        }
        if(field.getWeather() == Weather.HAIL) {
            if(myPoke.getBasePrm().getType1() != Type.ICE || myPoke.getBasePrm().getType2() != Type.ICE) {
                myPoke = field.slipDamageByHail(myPoke);
            }
            if(enemyPoke.getBasePrm().getType1() != Type.ICE || enemyPoke.getBasePrm().getType2() != Type.ICE) {
                enemyPoke = field.slipDamageByHail(enemyPoke);
            }
        }
        if(myPoke.getStatusAilment().getValue() == Ailment.POISON) {
            myPoke.getStatusAilment().slipDamageByPoison(myPoke);
        }
        if(myPoke.getStatusAilment().getValue() == Ailment.BAD_POISON) {
            myPoke.getStatusAilment().slipDamageByPoison(myPoke);
        }
        if(myPoke.getStatusAilment().getValue() == Ailment.BURN) {
            myPoke.getStatusAilment().slipDamageByPoison(myPoke);
        }
        if(enemyPoke.getStatusAilment().getValue() == Ailment.POISON) {
            enemyPoke.getStatusAilment().slipDamageByPoison(enemyPoke);
        }
        if(enemyPoke.getStatusAilment().getValue() == Ailment.BAD_POISON) {
            enemyPoke.getStatusAilment().slipDamageByPoison(enemyPoke);
        }
        if(enemyPoke.getStatusAilment().getValue() == Ailment.BURN) {
            enemyPoke.getStatusAilment().slipDamageByPoison(enemyPoke);
        }

        return new OnBattleField(myPoke, enemyPoke, field);
    }


}
