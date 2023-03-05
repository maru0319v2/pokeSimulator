package bussinessLogic;

import field.Field;
import field.Weather;
import move.Move;
import pokemon.PokemonInfo;
import statusAilment.Ailment;
import Enum.*;

import java.util.*;

import static bussinessLogic.BattleLogic.*;
import static bussinessLogic.ConsoleOutManager.showMessageParChar;
import static bussinessLogic.ConsoleOutManager.showPokemonInfo;
import static field.FieldImpl.initializeField;
import static statusAilment.StatusAilment.SLIP_DAMAGE_AILMENT;

public class BattleSimulation {
    public PokemonInfo battleSimulation(PokemonInfo myPoke, PokemonInfo enemyPoke) throws InterruptedException {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        showMessageParChar("野生の" + enemyPoke.getBasePrm().getName() + "が飛び出してきた!");
        showMessageParChar("ゆけっ!" + myPoke.getBasePrm().getName() + "!");

        Field field = initializeField();
        OnBattleField onBF = new OnBattleField(myPoke, enemyPoke, field);

        while (onBF.isBothFine()) {
            Thread.sleep(500);
            // 技選択
            showPokemonInfo(myPoke, enemyPoke);
            Move selectedMove = BattleLogic.selectMove(myPoke.getHaveMove(), myPoke);
            Move enemyMove = BattleLogic.enemySelectMove(enemyPoke, myPoke);
            showPokemonInfo(myPoke, enemyPoke);
            String myName = myPoke.getBasePrm().getName();
            String enemyName = enemyPoke.getBasePrm().getName();

            if(isFirstMe(myPoke, enemyPoke, selectedMove, enemyMove)) {
                // 自分が先行の場合

                // 状態異常の場合、経過ターン+1
                myPoke = myPoke.withStatusAilment(myPoke.getStatusAilment().comeTurn(myName));
                // 行動可能な場合、技を使う
                if(myPoke.getStatusAilment().canMove(myName)) {
                    onBF = doAction(myPoke, enemyPoke, field, selectedMove);
                    myPoke = onBF.getAttackPoke();
                    enemyPoke = onBF.getDefencePoke();
                    field = onBF.getField();
                }

                if(onBF.isDeadEither()) { break; }
                Thread.sleep(500);
                showPokemonInfo(myPoke, enemyPoke);

                // 状態異常の場合、経過ターン+1
                enemyPoke = enemyPoke.withStatusAilment(enemyPoke.getStatusAilment().comeTurn(enemyName));
                // 行動可能な場合、技を使う
                if(enemyPoke.getStatusAilment().canMove(enemyName)) {
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
                if(enemyPoke.getStatusAilment().canMove(enemyName)) {
                    onBF = doAction(enemyPoke, myPoke, field,  enemyMove);
                    myPoke = onBF.getDefencePoke();
                    enemyPoke = onBF.getAttackPoke();
                    field = onBF.getField();
                }

                if(onBF.isDeadEither()) { break; }
                Thread.sleep(500);
                showPokemonInfo(myPoke, enemyPoke);

                // 状態異常の場合、経過ターン+1
                myPoke = myPoke.withStatusAilment(myPoke.getStatusAilment().comeTurn(myName));
                // 行動可能な場合、技を使う
                if(myPoke.getStatusAilment().canMove(myName)) {
                    onBF = doAction(myPoke, enemyPoke, field,  selectedMove);
                    myPoke = onBF.getAttackPoke();
                    enemyPoke = onBF.getDefencePoke();
                    field = onBF.getField();
                }
            }
            if(onBF.isDeadEither()) { break; }
            // ターン終了処理
            // TODO 天候がなし以外の場合のif文
            onBF = endTurnProcessWeather(myPoke, enemyPoke, field);
            myPoke = onBF.getAttackPoke();
            enemyPoke = onBF.getDefencePoke();

            if(onBF.isDeadEither()) { break; }

            // TODO どちらが状態異常の場合のif文
            onBF = endTurnProcessAilment(myPoke, enemyPoke, field);
            myPoke = onBF.getAttackPoke();
            enemyPoke = onBF.getDefencePoke();
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

    private OnBattleField endTurnProcessAilment(PokemonInfo myPoke, PokemonInfo enemyPoke, Field field) throws InterruptedException {
        Thread.sleep(500);
        showPokemonInfo(myPoke, enemyPoke);

        myPoke = myPoke.getStatusAilment().slipDamageByAilment(myPoke);
        enemyPoke = enemyPoke.getStatusAilment().slipDamageByAilment(enemyPoke);
        return new OnBattleField(myPoke, enemyPoke, field);
    }

    private OnBattleField endTurnProcessWeather(PokemonInfo myPoke, PokemonInfo enemyPoke, Field field) throws InterruptedException {
        Thread.sleep(500);
        showPokemonInfo(myPoke, enemyPoke);
        field = field.elapseTurn();
        List<Type> myTypes = List.of(myPoke.getBasePrm().getType1(), myPoke.getBasePrm().getType2());
        List<Type> eneTypes = List.of(enemyPoke.getBasePrm().getType1(), enemyPoke.getBasePrm().getType2());

        if(field.getWeather() == Weather.SANDSTORM) {
            Set<Type> rockGroundSteel = new HashSet<>(Arrays.asList(Type.ROCK, Type.GROUND, Type.STEEL));
            if(rockGroundSteel.stream().noneMatch(myTypes::contains)) {
                myPoke = field.slipDamageBySandStorm(myPoke);
            }
            if(rockGroundSteel.stream().noneMatch(eneTypes::contains)) {
                enemyPoke = field.slipDamageBySandStorm(enemyPoke);
            }
        }
        if(field.getWeather() == Weather.HAIL) {
            if(!myTypes.contains(Type.ICE)) {
                myPoke = field.slipDamageByHail(myPoke);
            }
            if(!eneTypes.contains(Type.ICE)) {
                enemyPoke = field.slipDamageByHail(enemyPoke);
            }
        }
        return new OnBattleField(myPoke, enemyPoke, field);
    }
}
