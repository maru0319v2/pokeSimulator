package bussinessLogic;

import field.Field;
import field.OnBattleField;
import move.Move;
import pokemon.PokeInfo;
import pokemonStatus.impl.FlinchI;

import static bussinessLogic.BattleLogic.*;
import static bussinessLogic.ConsoleOutManager.showMessageParChar;
import static bussinessLogic.ConsoleOutManager.showPokemonInfo;
import static bussinessLogic.EnemySelectMove.enemySelectMove;
import static field.FieldI.initField;

public class BattleSimulation {
    public PokeInfo battleSimulation(PokeInfo myPk, PokeInfo enemyPk) throws InterruptedException {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        showMessageParChar("野生の" + enemyPk.basePrm().pName() + "が飛び出してきた!");
        showMessageParChar("ゆけっ!" + myPk.basePrm().pName() + "!");
        Thread.sleep(500);

        Field field = initField();
        OnBattleField onBF = new OnBattleField(myPk, enemyPk, field);

        while (onBF.isBothFine()) {
            // 技選択
            showPokemonInfo(myPk, enemyPk);
            Move selectedMove = selectMove(myPk.haveMove(), myPk);
            Move enemyMove = enemySelectMove(enemyPk, myPk, field);
            showPokemonInfo(myPk, enemyPk);

            if (isFirstMe(myPk, enemyPk, selectedMove, enemyMove)) {
                // 自分が先行の場合
                onBF = doTurn(myPk, enemyPk, selectedMove, field);
                myPk = onBF.getAtkPk();
                enemyPk = onBF.getDfcPk();
                field = onBF.getField();

                if (onBF.isDeadEither()) {
                    break;
                }
                Thread.sleep(500);
                showPokemonInfo(myPk, enemyPk);

                onBF = doTurn(enemyPk, myPk, enemyMove, field);
                myPk = onBF.getDfcPk();
                enemyPk = onBF.getAtkPk();
                field = onBF.getField();

            } else {
                // 自分が後攻の場合
                onBF = doTurn(enemyPk, myPk, enemyMove, field);
                myPk = onBF.getDfcPk();
                enemyPk = onBF.getAtkPk();
                field = onBF.getField();

                if (onBF.isDeadEither()) {
                    break;
                }
                Thread.sleep(500);
                showPokemonInfo(myPk, enemyPk);

                onBF = doTurn(myPk, enemyPk, selectedMove, field);
                myPk = onBF.getAtkPk();
                enemyPk = onBF.getDfcPk();
                field = onBF.getField();
            }

            Thread.sleep(500);
            if (onBF.isDeadEither()) {
                break;
            }
            // ターン終了処理
            onBF = endTurnProcessWeather(myPk, enemyPk, field);
            myPk = onBF.getAtkPk();
            enemyPk = onBF.getDfcPk();
            field = onBF.getField();

            if (onBF.isDeadEither()) {
                break;
            }

            onBF = endTurnProcessAilment(myPk, enemyPk, field);
            myPk = onBF.getAtkPk();
            enemyPk = onBF.getDfcPk();
            field = onBF.getField();

            // 怯み状態をリセットする
            myPk = myPk.withFlinch(new FlinchI(false));
            enemyPk = enemyPk.withFlinch(new FlinchI(false));
        }

        showPokemonInfo(myPk, enemyPk);
        if (myPk.currentHP().isAlive()) {
            System.out.println("野生の" + enemyPk.basePrm().pName() + "は倒れた!");
            int addExp = enemyPk.giveExp();
            myPk = myPk.addExp(addExp);
        } else {
            System.out.println(myPk.basePrm().pName() + "は倒れた");
        }
        return myPk.withResetStatusRank();
    }


    private OnBattleField doTurn(PokeInfo atkPk, PokeInfo dfcPk, Move selectedMove, Field field) throws InterruptedException {
        String name = atkPk.basePrm().pName();
        // 状態異常の場合、経過ターン+1
        atkPk = atkPk.withAilment(atkPk.ailment().comeTurn(name));
        // 混乱状態の場合、混乱ターン+1
        atkPk = atkPk.withConfusion(atkPk.confusion().elapseTurn(name));
        // 行動可能な場合、技を使う
        boolean canMoveByConfusion = atkPk.confusion().canMove(name);
        boolean canMoveByFlinch = atkPk.flinch().canMove(name);
        boolean canMoveByAilment = atkPk.ailment().canMove(name);

        if (!canMoveByConfusion) {
            // 自傷ダメージ
            atkPk = atkPk.confusion().damageMe(atkPk);
            return new OnBattleField(atkPk, dfcPk, field);
        }
        if (canMoveByFlinch && canMoveByAilment) {
            return doAction(atkPk, dfcPk, field, selectedMove);
        }
        return new OnBattleField(atkPk, dfcPk, field);
    }

    private OnBattleField endTurnProcessAilment(PokeInfo myPoke, PokeInfo enemyPoke, Field field) throws InterruptedException {
        showPokemonInfo(myPoke, enemyPoke);

        myPoke = myPoke.ailment().slipDmgByAilment(myPoke);
        enemyPoke = enemyPoke.ailment().slipDmgByAilment(enemyPoke);
        return new OnBattleField(myPoke, enemyPoke, field);
    }

    private OnBattleField endTurnProcessWeather(PokeInfo myPoke, PokeInfo enemyPoke, Field field) throws InterruptedException {
        showPokemonInfo(myPoke, enemyPoke);
        field = field.withWeather(field.weather().elapsingTurnWeather());

        myPoke = field.weather().slipDmgByWeather(myPoke);
        enemyPoke = field.weather().slipDmgByWeather(enemyPoke);
        return new OnBattleField(myPoke, enemyPoke, field);
    }
}
