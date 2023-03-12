package bussinessLogic;

import field.Field;
import field.OnBattleField;
import field.Weather;
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

        Weather weather = Weather.initWeather();
        Field myField = initField(myPk);
        Field enemyField = initField(enemyPk);

        OnBattleField onBF = new OnBattleField(myField, enemyField, weather);

        while (onBF.isBothFine()) {
            // 技選択
            showPokemonInfo(myField.poke(), enemyField.poke());
            Move selectedMove = selectMove(myField.poke());
            Move enemyMove = enemySelectMove(enemyField, myField, weather);
            showPokemonInfo(myField.poke(), enemyField.poke());

            if (isFirstMe(myField, enemyField, selectedMove, enemyMove)) {
                // 自分が先行の場合
                onBF = doTurn(myField, enemyField, selectedMove, weather);
                myField = onBF.atkField();
                enemyField = onBF.dfcField();
                weather = onBF.weather();

                if (onBF.isDeadEither()) {
                    break;
                }
                Thread.sleep(500);
                showPokemonInfo(myField.poke(), enemyField.poke());

                onBF = doTurn(enemyField, myField, enemyMove, weather);
                myField = onBF.dfcField();
                enemyField = onBF.atkField();
                weather = onBF.weather();

            } else {
                // 自分が後攻の場合
                onBF = doTurn(enemyField, myField, enemyMove, weather);
                myField = onBF.dfcField();
                enemyField = onBF.atkField();
                weather = onBF.weather();

                if (onBF.isDeadEither()) {
                    break;
                }
                Thread.sleep(500);
                showPokemonInfo(myField.poke(), enemyField.poke());

                onBF = doTurn(myField, enemyField, selectedMove, weather);
                myField = onBF.atkField();
                enemyField = onBF.dfcField();
                weather = onBF.weather();
            }

            Thread.sleep(500);
            if (onBF.isDeadEither()) {
                break;
            }
            // ターン終了処理
            onBF = endTurnProcessWeather(myField, enemyField, weather);
            myField = onBF.atkField();
            enemyField = onBF.dfcField();
            weather = onBF.weather();

            if (onBF.isDeadEither()) {
                break;
            }

            onBF = endTurnProcessAilment(myField, enemyField, weather);
            myField = onBF.atkField();
            enemyField = onBF.dfcField();
            weather = onBF.weather();

            // 怯み状態をリセットする
            myField = myField.withPokeInfo(myField.poke().withFlinch(new FlinchI(false)));
            enemyField = enemyField.withPokeInfo(enemyField.poke().withFlinch(new FlinchI(false)));
            // リフレクター、ひかりのかべの経過ターンを+1
            myField = myField.withReflect(myField.reflect().elapsingTurn());
            myField = myField.withLightScreen(myField.lightScreen().elapsingTurn());
            enemyField = enemyField.withReflect(enemyField.reflect().elapsingTurn());
            enemyField = enemyField.withLightScreen(enemyField.lightScreen().elapsingTurn());
        }

        showPokemonInfo(myField.poke(), enemyField.poke());
        if (myField.poke().currentHP().isAlive()) {
            System.out.println(enemyPk.basePrm().pName() + "は倒れた!");
            int addExp = enemyPk.giveExp();
            myField = myField.withPokeInfo(myField.poke().addExp(addExp));
        } else {
            System.out.println(myField.poke().basePrm().pName() + "は倒れた");
        }
        return myField.poke().withResetStatusRank();
    }


    private OnBattleField doTurn(Field atkField, Field dfcField, Move selectedMove, Weather weather) throws InterruptedException {
        String name = atkField.poke().basePrm().pName();
        // 状態異常の場合、経過ターン+1
        atkField = atkField.withPokeInfo(atkField.poke().withAilment(atkField.poke().ailment().comeTurn(name)));
        // 混乱状態の場合、混乱ターン+1
        atkField = atkField.withPokeInfo(atkField.poke().withConfusion(atkField.poke().confusion().elapseTurn(name)));
        // 行動可能な場合、技を使う
        boolean canMoveByConfusion = atkField.poke().confusion().canMove(name);
        boolean canMoveByFlinch = atkField.poke().flinch().canMove(name);
        boolean canMoveByAilment = atkField.poke().ailment().canMove(name);

        if (!canMoveByConfusion) {
            // 自傷ダメージ
            atkField = atkField.withPokeInfo(atkField.poke().confusion().damageMe(atkField.poke()));
            return new OnBattleField(atkField, dfcField, weather);
        }
        if (canMoveByFlinch && canMoveByAilment) {
            return doAction(atkField, dfcField, selectedMove, weather);
        }
        return new OnBattleField(atkField, dfcField, weather);
    }

    private OnBattleField endTurnProcessAilment(Field myField, Field enemyField, Weather weather) throws InterruptedException {
        showPokemonInfo(myField.poke(), enemyField.poke());

        myField = myField.withPokeInfo(myField.poke().ailment().slipDmgByAilment(myField.poke()));
        enemyField = enemyField.withPokeInfo(enemyField.poke().ailment().slipDmgByAilment(enemyField.poke()));
        return new OnBattleField(myField, enemyField, weather);
    }

    private OnBattleField endTurnProcessWeather(Field myField, Field enemyField, Weather weather) throws InterruptedException {
        showPokemonInfo(myField.poke(), enemyField.poke());
        weather = weather.elapsingTurnWeather();

        myField = myField.withPokeInfo(weather.slipDmgByWeather(myField.poke()));
        enemyField = enemyField.withPokeInfo(weather.slipDmgByWeather(enemyField.poke()));
        return new OnBattleField(myField, enemyField, weather);
    }
}
