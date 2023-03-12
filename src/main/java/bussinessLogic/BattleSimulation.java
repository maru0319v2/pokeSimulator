package bussinessLogic;

import Enum.Item;
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

        Field myField = initField(myPk);
        Field enemyField = initField(enemyPk);
        OnBattleField onBF = new OnBattleField(myField, enemyField, Weather.initWeather());

        while (onBF.isBothFine()) {
            // 技選択
            showPokemonInfo(myField.poke(), enemyField.poke());
            Move selectedMove = selectMove(myField.poke());
            Move enemyMove = enemySelectMove(enemyField, myField, onBF.weather());
            showPokemonInfo(myField.poke(), enemyField.poke());
            // 先攻・後攻決定
            boolean isFirstMe = isFirstMe(myField, enemyField, selectedMove, enemyMove);
            onBF = isFirstMe ? new OnBattleField(myField, enemyField, onBF.weather()) : new OnBattleField(enemyField, myField, onBF.weather());
            Move move = isFirstMe ? selectedMove : enemyMove;
            // 先攻の行動
            onBF = doTurn(onBF, move);
            myField = isFirstMe ? onBF.atkField() : onBF.dfcField();
            enemyField = isFirstMe ? onBF.dfcField() : onBF.atkField();
            // 瀕死判定と攻守交代
            if (onBF.isDeadEither()) {
                break;
            }
            Thread.sleep(500);
            showPokemonInfo(myField.poke(), enemyField.poke());
            onBF = isFirstMe ? new OnBattleField(enemyField, myField, onBF.weather()) : new OnBattleField(myField, enemyField, onBF.weather());
            move = isFirstMe ? enemyMove : selectedMove;
            // 後攻の行動
            onBF = doTurn(onBF, move);
            myField = isFirstMe ? onBF.dfcField() : onBF.atkField();
            enemyField = isFirstMe ? onBF.atkField() : onBF.dfcField();
            // 瀕死判定
            Thread.sleep(500);
            if (onBF.isDeadEither()) {
                break;
            }
            // ターン終了処理
            onBF = endTurnProcess(myField, enemyField, onBF.weather());
            myField = onBF.atkField();
            enemyField = onBF.dfcField();
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


    private OnBattleField doTurn(OnBattleField onBF, Move selectedMove) throws InterruptedException {
        Field atkField = onBF.atkField();
        Field dfcField = onBF.dfcField();
        Weather weather = onBF.weather();
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

    private OnBattleField endTurnProcess(Field myField, Field enemyField, Weather weather) throws InterruptedException {
        // https://wiki.xn--rckteqa2e.com/wiki/%E3%82%BF%E3%83%BC%E3%83%B3
        // 道具処理
        OnBattleField onBF = Item.endTurn(new OnBattleField(myField, enemyField, weather));
        myField = onBF.atkField();
        enemyField = onBF.dfcField();
        weather = onBF.weather();

        // 天候処理
        onBF = endTurnProcessWeather(myField, enemyField, weather);
        myField = onBF.atkField();
        enemyField = onBF.dfcField();
        weather = onBF.weather();

        if (onBF.isDeadEither()) {
            return onBF;
        }

        // 状態異常処理
        onBF = endTurnProcessAilment(myField, enemyField, weather);
        myField = onBF.atkField();
        enemyField = onBF.dfcField();
        weather = onBF.weather();

        if (onBF.isDeadEither()) {
            return onBF;
        }

        // 怯み状態をリセットする
        myField = myField.withPokeInfo(myField.poke().withFlinch(new FlinchI(false)));
        enemyField = enemyField.withPokeInfo(enemyField.poke().withFlinch(new FlinchI(false)));

        // リフレクター、ひかりのかべの経過ターンを+1
        myField = myField.withReflect(myField.reflect().elapsingTurn());
        myField = myField.withLightScreen(myField.lightScreen().elapsingTurn());
        enemyField = enemyField.withReflect(enemyField.reflect().elapsingTurn());
        enemyField = enemyField.withLightScreen(enemyField.lightScreen().elapsingTurn());

        return new OnBattleField(myField, enemyField, weather);
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
