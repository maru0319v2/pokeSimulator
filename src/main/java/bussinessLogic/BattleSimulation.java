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
import static field.FieldI.initializeField;

public class BattleSimulation {
    public PokeInfo battleSimulation(PokeInfo myPoke, PokeInfo enemyPoke) throws InterruptedException {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        showMessageParChar("野生の" + enemyPoke.getBasePrm().pName() + "が飛び出してきた!");
        showMessageParChar("ゆけっ!" + myPoke.getBasePrm().pName() + "!");
        Thread.sleep(500);

        Field field = initializeField();
        OnBattleField onBF = new OnBattleField(myPoke, enemyPoke, field);

        while (onBF.isBothFine()) {
            // 技選択
            showPokemonInfo(myPoke, enemyPoke);
            Move selectedMove = BattleLogic.selectMove(myPoke.getHaveMove(), myPoke);
            Move enemyMove = enemySelectMove(enemyPoke, myPoke, field);
            showPokemonInfo(myPoke, enemyPoke);
            String myName = myPoke.getBasePrm().pName();
            String enemyName = enemyPoke.getBasePrm().pName();

            if (isFirstMe(myPoke, enemyPoke, selectedMove, enemyMove)) {
                // 自分が先行の場合

                // 状態異常の場合、経過ターン+1
                myPoke = myPoke.withStatusAilment(myPoke.getStatusAilment().comeTurn(myName));
                // 行動可能な場合、技を使う
                if (canMove(myPoke)) {
                    onBF = doAction(myPoke, enemyPoke, field, selectedMove);
                    myPoke = onBF.getAttackPoke();
                    enemyPoke = onBF.getDefencePoke();
                    field = onBF.getField();
                }

                if (onBF.isDeadEither()) {
                    break;
                }
                Thread.sleep(500);
                showPokemonInfo(myPoke, enemyPoke);

                // 状態異常の場合、経過ターン+1
                enemyPoke = enemyPoke.withStatusAilment(enemyPoke.getStatusAilment().comeTurn(enemyName));
                // 行動可能な場合、技を使う
                if (canMove(enemyPoke)) {
                    onBF = doAction(enemyPoke, myPoke, field, enemyMove);
                    myPoke = onBF.getDefencePoke();
                    enemyPoke = onBF.getAttackPoke();
                    field = onBF.getField();
                }
            } else {
                // 自分が後攻の場合

                // 状態異常の場合、経過ターン+1
                enemyPoke = enemyPoke.withStatusAilment(enemyPoke.getStatusAilment().comeTurn(enemyName));
                // 行動可能な場合、技を使う
                if (canMove(enemyPoke)) {
                    onBF = doAction(enemyPoke, myPoke, field, enemyMove);
                    myPoke = onBF.getDefencePoke();
                    enemyPoke = onBF.getAttackPoke();
                    field = onBF.getField();
                }

                if (onBF.isDeadEither()) {
                    break;
                }
                Thread.sleep(500);
                showPokemonInfo(myPoke, enemyPoke);

                // 状態異常の場合、経過ターン+1
                myPoke = myPoke.withStatusAilment(myPoke.getStatusAilment().comeTurn(myName));
                // 行動可能な場合、技を使う
                if (canMove(myPoke)) {
                    onBF = doAction(myPoke, enemyPoke, field, selectedMove);
                    myPoke = onBF.getAttackPoke();
                    enemyPoke = onBF.getDefencePoke();
                    field = onBF.getField();
                }
            }
            Thread.sleep(500);
            if (onBF.isDeadEither()) {
                break;
            }
            // ターン終了処理
            onBF = endTurnProcessWeather(myPoke, enemyPoke, field);
            myPoke = onBF.getAttackPoke();
            enemyPoke = onBF.getDefencePoke();

            if (onBF.isDeadEither()) {
                break;
            }

            onBF = endTurnProcessAilment(myPoke, enemyPoke, field);
            myPoke = onBF.getAttackPoke();
            enemyPoke = onBF.getDefencePoke();

            // 怯み状態をリセットする
            myPoke = myPoke.withFlinch(new FlinchI(false));
            enemyPoke = enemyPoke.withFlinch(new FlinchI(false));
        }

        showPokemonInfo(myPoke, enemyPoke);
        if (myPoke.getCurrentHitPoint().isAlive()) {
            System.out.println("野生の" + enemyPoke.getBasePrm().pName() + "は倒れた!");
            int addExp = enemyPoke.giveExp();
            myPoke = myPoke.addExp(addExp);
        } else {
            System.out.println(myPoke.getBasePrm().pName() + "は倒れた");
        }
        return myPoke.withResetStatusRank();
    }

    private boolean canMove(PokeInfo target) throws InterruptedException {
        String name = target.getBasePrm().pName();
        boolean flinchResult = target.getFlinch().canMove(name);
        boolean ailmentResult = target.getStatusAilment().canMove(name);
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

        myPoke = myPoke.getStatusAilment().slipDamageByAilment(myPoke);
        enemyPoke = enemyPoke.getStatusAilment().slipDamageByAilment(enemyPoke);
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
