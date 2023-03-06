package field;

import Enum.Type;
import move.Move;
import pokemon.PokeInfo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static bussinessLogic.ConsoleOutManager.*;

public class FieldI implements Field {
    private final Weather weather;
    private final int elapsedTurn;
    private final int countForRecovery;

    public Weather weather() {
        return this.weather;
    }

    public int elapsedTurn() {
        return this.elapsedTurn;
    }

    public int countForRecovery() {
        return this.countForRecovery;
    }

    // フィールドを継続する場合(経過ターン+1)
    private static Field keepField(Weather weather, int elapsedTurn, int countRecovery) {
        return new FieldI(weather, elapsedTurn, countRecovery);
    }

    private FieldI(Weather weather, int elapsedTurn, int countForRecovery) {
        this.weather = weather;
        this.elapsedTurn = elapsedTurn;
        this.countForRecovery = countForRecovery;
    }

    // 初期化したい場合
    public static Field initField() {
        return new FieldI();
    }

    private FieldI() {
        this.weather = Weather.NONE;
        this.elapsedTurn = 0;
        this.countForRecovery = 0;
    }

    // 天候を変化させる場合
    public static Field changeField(Field field, Weather weather) throws InterruptedException {
        return new FieldI(field, weather);
    }

    private FieldI(Field field, Weather weather) throws InterruptedException {

        if (field.weather() != weather) {
            // 引数の天候がもとの天候以外なら上書きする
            this.weather = weather;
            this.elapsedTurn = 0;
            this.countForRecovery = 5;
            showChangeWeather(weather);
        } else {
            this.weather = field.weather();
            this.elapsedTurn = field.elapsedTurn();
            this.countForRecovery = field.countForRecovery();
        }
    }

    public Field elapseTurn() throws InterruptedException {
        if (this.countForRecovery <= this.elapsedTurn + 1) {
            showUndoWeather(this.weather);
            return initField();
        }
        showKeepWeather(this.weather);
        return keepField(this.weather, this.elapsedTurn + 1, this.countForRecovery);
    }

    @Override
    public PokeInfo slipDamageByWeather(PokeInfo target) throws InterruptedException {
        List<Type> types = List.of(target.basePrm().type1(), target.basePrm().type2());
        Set<Type> roGrSt = new HashSet<>(Arrays.asList(Type.ROCK, Type.GROUND, Type.STEEL));
        int damage;

        if (this.weather() == Weather.SANDSTORM && roGrSt.stream().noneMatch(types::contains)) {
            showMessageParChar(target.basePrm().pName() + "はすなあらしでダメージをうけた！");
            damage = target.realHP() / 16;
        } else if (this.weather() == Weather.HAIL && !types.contains(Type.ICE)) {
            showMessageParChar(target.basePrm().pName() + "はあられでダメージをうけた！");
            damage = target.realHP() / 16;
        } else {
            return target;
        }
        Thread.sleep(500);
        return target.damage(damage);
    }

    public double damageRateByWeather(Move move) {
        Type useType = move.baseMPrm().moveType();
        if (useType == Type.FIRE) {
            if (this.weather == Weather.DROUGHT) {
                return 1.5;
            }
            if (this.weather == Weather.RAIN) {
                return 0.5;
            }
        }
        if (useType == Type.WATER) {
            if (this.weather == Weather.DROUGHT) {
                return 0.5;
            }
            if (this.weather == Weather.RAIN) {
                return 1.5;
            }
        }
        return 1.0;
    }

    public double defenceRateBySandStorm(PokeInfo defencePoke) {
        Type type1 = defencePoke.basePrm().type1();
        Type type2 = defencePoke.basePrm().type2();
        if (this.weather == Weather.SANDSTORM) {
            return type1 == Type.ROCK || type2 == Type.ROCK ? 1.5 : 1.0;
        }
        return 1.0;
    }
}
