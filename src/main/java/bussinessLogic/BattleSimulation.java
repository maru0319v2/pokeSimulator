package bussinessLogic;

import field.Field;
import move.Move;
import pokemon.PokeInfo;
import pokemonStatus.impl.FlinchI;

import static bussinessLogic.BattleLogic.doAction;
import static bussinessLogic.BattleLogic.isFirstMe;
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
            Move selectedMove = BattleLogic.selectMove(myPk.haveMove(), myPk);
            Move enemyMove = enemySelectMove(enemyPk, myPk, field);
            showPokemonInfo(myPk, enemyPk);
            String myName = myPk.basePrm().pName();
            String enemyName = enemyPk.basePrm().pName();

            if (isFirstMe(myPk, enemyPk, selectedMove, enemyMove)) {
                // 自分が先行の場合

                // 状態異常の場合、経過ターン+1
                myPk = myPk.withAilment(myPk.ailment().comeTurn(myName));
                // 行動可能な場合、技を使う
                if (canMove(myPk)) {
                    onBF = doAction(myPk, enemyPk, field, selectedMove);
                    myPk = onBF.getAtkPk();
                    enemyPk = onBF.getDfcPk();
                    field = onBF.getField();
                }

                if (onBF.isDeadEither()) {
                    break;
                }
                Thread.sleep(500);
                showPokemonInfo(myPk, enemyPk);

                // 状態異常の場合、経過ターン+1
                enemyPk = enemyPk.withAilment(enemyPk.ailment().comeTurn(enemyName));
                // 行動可能な場合、技を使う
                if (canMove(enemyPk)) {
                    onBF = doAction(enemyPk, myPk, field, enemyMove);
                    myPk = onBF.getDfcPk();
                    enemyPk = onBF.getAtkPk();
                    field = onBF.getField();
                }
            } else {
                // 自分が後攻の場合

                // 状態異常の場合、経過ターン+1
                enemyPk = enemyPk.withAilment(enemyPk.ailment().comeTurn(enemyName));
                // 行動可能な場合、技を使う
                if (canMove(enemyPk)) {
                    onBF = doAction(enemyPk, myPk, field, enemyMove);
                    myPk = onBF.getDfcPk();
                    enemyPk = onBF.getAtkPk();
                    field = onBF.getField();
                }

                if (onBF.isDeadEither()) {
                    break;
                }
                Thread.sleep(500);
                showPokemonInfo(myPk, enemyPk);

                // 状態異常の場合、経過ターン+1
                myPk = myPk.withAilment(myPk.ailment().comeTurn(myName));
                // 行動可能な場合、技を使う
                if (canMove(myPk)) {
                    onBF = doAction(myPk, enemyPk, field, selectedMove);
                    myPk = onBF.getAtkPk();
                    enemyPk = onBF.getDfcPk();
                    field = onBF.getField();
                }
            }
            Thread.sleep(500);
            if (onBF.isDeadEither()) {
                break;
            }
            // ターン終了処理
            onBF = endTurnProcessWeather(myPk, enemyPk, field);
            myPk = onBF.getAtkPk();
            enemyPk = onBF.getDfcPk();

            if (onBF.isDeadEither()) {
                break;
            }

            onBF = endTurnProcessAilment(myPk, enemyPk, field);
            myPk = onBF.getAtkPk();
            enemyPk = onBF.getDfcPk();

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

    private boolean canMove(PokeInfo target) throws InterruptedException {
        String name = target.basePrm().pName();
        boolean flinchResult = target.flinch().canMove(name);
        boolean ailmentResult = target.ailment().canMove(name);
        if (!flinchResult) {
            return false;
        }
        if (!ailmentResult) {
            return false;
        }
        return true;
    }

    private OnBattleField endTurnProcessAilment(PokeInfo myPoke, PokeInfo enemyPoke, Field field) throws InterruptedException {
        showPokemonInfo(myPoke, enemyPoke);

        myPoke = myPoke.ailment().slipDamageByAilment(myPoke);
        enemyPoke = enemyPoke.ailment().slipDamageByAilment(enemyPoke);
        return new OnBattleField(myPoke, enemyPoke, field);
    }

    private OnBattleField endTurnProcessWeather(PokeInfo myPoke, PokeInfo enemyPoke, Field field) throws InterruptedException {
        showPokemonInfo(myPoke, enemyPoke);
        field = field.elapseTurn();

        myPoke = field.slipDamageByWeather(myPoke);
        enemyPoke = field.slipDamageByWeather(enemyPoke);
        return new OnBattleField(myPoke, enemyPoke, field);
    }
}
