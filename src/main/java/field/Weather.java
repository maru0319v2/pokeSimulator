package field;

import Enum.Type;
import move.Move;
import pokemon.PokeInfo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static bussinessLogic.ConsoleOutManager.*;

public class Weather {
    private final WeatherEnum val;
    private final int elapsedTurnWeather;
    private final int countRecoveryWeather;

    public WeatherEnum val() {
        return this.val;
    }

    public int elapsedTurnWeather() {
        return this.elapsedTurnWeather;
    }

    public int countRecoveryWeather() {
        return this.countRecoveryWeather;
    }

    private Weather(WeatherEnum weather, int elapsedTurnWeather, int countRecoveryWeather) {
        this.val = weather;
        this.elapsedTurnWeather = elapsedTurnWeather;
        this.countRecoveryWeather = countRecoveryWeather;
    }

    // 初期化したい場合
    public static Weather initWeather() {
        return new Weather();
    }

    private Weather() {
        this.val = WeatherEnum.NONE;
        this.elapsedTurnWeather = 0;
        this.countRecoveryWeather = 0;
    }

    // 天候を変化させる場合
    public static Weather changeWeather(Weather currentWeather, WeatherEnum weather) throws InterruptedException {
        return new Weather(currentWeather, weather);
    }

    private Weather(Weather currentWeather, WeatherEnum weather) throws InterruptedException {
        if (currentWeather.val() != weather) {
            // 引数の天候がもとの天候以外なら上書きする
            this.val = weather;
            this.elapsedTurnWeather = 0;
            this.countRecoveryWeather = 5;
            showChangeWeather(weather);
        } else {
            this.val = currentWeather.val();
            this.elapsedTurnWeather = currentWeather.elapsedTurnWeather();
            this.countRecoveryWeather = currentWeather.countRecoveryWeather();
        }
    }

    public Weather elapsingTurnWeather() throws InterruptedException {
        if (this.countRecoveryWeather <= this.elapsedTurnWeather + 1) {
            showUndoWeather(this.val);
            return initWeather();
        }
        showKeepWeather(this.val);
        return new Weather(this.val, this.elapsedTurnWeather + 1, this.countRecoveryWeather);
    }

    public PokeInfo slipDmgByWeather(PokeInfo target) throws InterruptedException {
        List<Type> types = List.of(target.basePrm().type1(), target.basePrm().type2());
        Set<Type> roGrSt = new HashSet<>(Arrays.asList(Type.ROCK, Type.GROUND, Type.STEEL));
        int damage;

        if (this.val() == WeatherEnum.SANDSTORM && roGrSt.stream().noneMatch(types::contains)) {
            showMessageParChar(target.basePrm().pName() + "はすなあらしでダメージをうけた！");
            damage = target.realHP() / 16;
        } else if (this.val() == WeatherEnum.HAIL && !types.contains(Type.ICE)) {
            showMessageParChar(target.basePrm().pName() + "はあられでダメージをうけた！");
            damage = target.realHP() / 16;
        } else {
            return target;
        }
        Thread.sleep(500);
        return target.damage(damage);
    }

    public double dmgRateByWeather(Move move) {
        Type useType = move.baseMPrm().moveType();
        if (useType == Type.FIRE) {
            if (this.val == WeatherEnum.DROUGHT) {
                return 1.5;
            }
            if (this.val == WeatherEnum.RAIN) {
                return 0.5;
            }
        }
        if (useType == Type.WATER) {
            if (this.val == WeatherEnum.DROUGHT) {
                return 0.5;
            }
            if (this.val == WeatherEnum.RAIN) {
                return 1.5;
            }
        }
        return 1.0;
    }

    public double dfcRateBySandStorm(PokeInfo defencePoke) {
        Type type1 = defencePoke.basePrm().type1();
        Type type2 = defencePoke.basePrm().type2();
        if (this.val == WeatherEnum.SANDSTORM) {
            return type1 == Type.ROCK || type2 == Type.ROCK ? 1.5 : 1.0;
        }
        return 1.0;
    }
}
