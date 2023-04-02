package bussinessLogic;

import Enum.Item;
import field.Field;
import field.FieldI;
import field.OnBattleField;
import field.Weather;
import move.Move;
import pokemon.PokeInfo;
import pokemonStatus.impl.FlinchI;

import java.util.List;
import java.util.stream.Collectors;

import static bussinessLogic.BattleLogic.*;
import static bussinessLogic.ConsoleOutManager.showMessageParChar;
import static bussinessLogic.ConsoleOutManager.showPokemonInfo;
import static bussinessLogic.EnemySelectMove.enemySelectMove;

public class BattleSimulation {

    public boolean initBattle(List<PokeInfo> myPokeList, List<PokeInfo> enemyPokeList) throws InterruptedException {
        // フィールド、天気を初期化
        Field myField = FieldI.init(myPokeList.get(0));
        Field enemyField = FieldI.init(enemyPokeList.get(0));
        Weather weather = Weather.init();

        System.out.print("\033[H\033[2J");
        System.out.flush();
        showMessageParChar("相手のポケモントレーナーは" + enemyField.poke().basePrm().pName() + "をくりだした!");
        showMessageParChar("ゆけっ! " + myField.poke().basePrm().pName() + "!");
        Thread.sleep(500);

        OnBattleField onBF;

        boolean continueFlg = true;
        while (continueFlg) {
            // 互いの先頭ポケモンをバトルに出す
            onBF = battleSimulation(myField, enemyField, weather);
            myField = onBF.atkField();
            enemyField = onBF.dfcField();
            weather = onBF.weather();
            // 互いのListを更新する
            myPokeList = updatePokeList(myPokeList, myField.poke());
            enemyPokeList = updatePokeList(enemyPokeList, enemyField.poke());

            if (myField.poke().currentHP().isDead()) {
                if (myPokeList.get(1).currentHP().isAlive()) {
                    myField = myField.updatePokeInfo(myPokeList.get(1));
                    showMessageParChar("ゆけっ! " + myField.poke().basePrm().pName() + "!");
                    Thread.sleep(500);
                } else if (myPokeList.get(2).currentHP().isAlive()) {
                    myField = myField.updatePokeInfo(myPokeList.get(2));
                    showMessageParChar("ゆけっ! " + myField.poke().basePrm().pName() + "!");
                    Thread.sleep(500);
                } else {
                    // 自分の負け
                    showMessageParChar("相手のポケモントレーナーとのバトルに負けた。。。");
                    continueFlg = false;
                    return false;
                }
            }

            if (enemyField.poke().currentHP().isDead()) {
                if (enemyPokeList.get(1).currentHP().isAlive()) {
                    enemyField = enemyField.updatePokeInfo(enemyPokeList.get(1));
                    showMessageParChar("相手のポケモントレーナーは" + enemyField.poke().basePrm().pName() + "をくりだした!");
                    Thread.sleep(500);
                } else if (enemyPokeList.get(2).currentHP().isAlive()) {
                    enemyField = enemyField.updatePokeInfo(enemyPokeList.get(2));
                    showMessageParChar("相手のポケモントレーナーは" + enemyField.poke().basePrm().pName() + "をくりだした!");
                    Thread.sleep(500);
                } else {
                    // 相手トレーナの負け
                    showMessageParChar("相手のポケモントレーナーとのバトルに勝った!");
                    continueFlg = false;
                    return true;
                }
            }
        }
        return true;
    }

    public OnBattleField battleSimulation(Field myField, Field enemyField, Weather weather) throws InterruptedException {

        OnBattleField onBF = new OnBattleField(myField, enemyField, weather);

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
        return new OnBattleField(myField, enemyField, onBF.weather());
    }


    private OnBattleField doTurn(OnBattleField onBF, Move selectedMove) {
        Field atkField = onBF.atkField();
        Field dfcField = onBF.dfcField();
        Weather weather = onBF.weather();
        String name = atkField.poke().basePrm().pName();
        // 状態異常の場合、経過ターン+1
        atkField = atkField.updatePokeInfo(atkField.poke().updateAilment(atkField.poke().ailment().comeTurn(name)));
        // 混乱状態の場合、混乱ターン+1
        atkField = atkField.updatePokeInfo(atkField.poke().updateConfusion(atkField.poke().confusion().elapseTurn(name)));
        // 行動可能な場合、技を使う
        boolean canMoveByConfusion = atkField.poke().confusion().canMove(name);
        boolean canMoveByFlinch = atkField.poke().flinch().canMove(name);
        boolean canMoveByAilment = atkField.poke().ailment().canMove(name);

        if (!canMoveByConfusion) {
            // 自傷ダメージ
            atkField = atkField.updatePokeInfo(atkField.poke().confusion().damageMe(atkField.poke()));
            return new OnBattleField(atkField, dfcField, weather);
        }
        if (canMoveByFlinch && canMoveByAilment) {
            return doAction(atkField, dfcField, selectedMove, weather);
        }
        return new OnBattleField(atkField, dfcField, weather);
    }

    private OnBattleField endTurnProcess(Field myField, Field enemyField, Weather weather) {
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
        myField = myField.updatePokeInfo(myField.poke().updateFlinch(new FlinchI(false)));
        enemyField = enemyField.updatePokeInfo(enemyField.poke().updateFlinch(new FlinchI(false)));

        // リフレクター、ひかりのかべの経過ターンを+1
        myField = myField.updateReflect(myField.reflect().elapsingTurn());
        myField = myField.updateLightScreen(myField.lightScreen().elapsingTurn());
        enemyField = enemyField.updateReflect(enemyField.reflect().elapsingTurn());
        enemyField = enemyField.updateLightScreen(enemyField.lightScreen().elapsingTurn());

        return new OnBattleField(myField, enemyField, weather);
    }

    private OnBattleField endTurnProcessAilment(Field myField, Field enemyField, Weather weather) {
        showPokemonInfo(myField.poke(), enemyField.poke());

        myField = myField.updatePokeInfo(myField.poke().ailment().slipDmgByAilment(myField.poke()));
        enemyField = enemyField.updatePokeInfo(enemyField.poke().ailment().slipDmgByAilment(enemyField.poke()));
        return new OnBattleField(myField, enemyField, weather);
    }

    private OnBattleField endTurnProcessWeather(Field myField, Field enemyField, Weather weather) {
        showPokemonInfo(myField.poke(), enemyField.poke());
        weather = weather.elapsingTurnWeather();

        myField = myField.updatePokeInfo(weather.slipDmgByWeather(myField.poke()));
        enemyField = enemyField.updatePokeInfo(weather.slipDmgByWeather(enemyField.poke()));
        return new OnBattleField(myField, enemyField, weather);
    }

    private List<PokeInfo> updatePokeList(List<PokeInfo> targetList, PokeInfo updatePoke) {
        return targetList.stream()
                .map(pokeInfo -> pokeInfo.basePrm().equals(updatePoke.basePrm()) ? updatePoke : pokeInfo)
                .collect(Collectors.toList());
    }
}
