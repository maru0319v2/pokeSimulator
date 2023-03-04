package field;

import lombok.Getter;
import move.Move;
import pokemon.PokemonInfo;
import Enum.*;

import java.util.Objects;

import static bussinessLogic.ConsoleOutManager.*;

@Getter
public class FieldImpl implements Field {
    private final Weather weather;
    private final int elapsedTurn;
    private final int countForRecovery;

    // フィールドを継続する場合(経過ターン+1)
    private static Field keepField(Weather weather, int elapsedTurn, int countRecovery) {
        return new FieldImpl(weather, elapsedTurn, countRecovery);
    }
    private FieldImpl(Weather weather, int elapsedTurn, int countForRecovery) {
        this.weather = weather;
        this.elapsedTurn = elapsedTurn;
        this.countForRecovery = countForRecovery;
    }

    // 初期化したい場合
    public static Field initializeField() {
        return new FieldImpl();
    }
    private FieldImpl() {
        this.weather = Weather.NONE;
        this.elapsedTurn = 0;
        this.countForRecovery = 0;
    }

    // 天候を変化させる場合
    public static Field changeField(Field field, Weather weather) throws InterruptedException {
        return new FieldImpl(field, weather);
    }
    private FieldImpl(Field field, Weather weather) throws InterruptedException {

        if(field.getWeather() != weather){
            // 引数の天候がもとの天候以外なら上書きする
            this.weather = weather;
            this.elapsedTurn = 0;
            this.countForRecovery = 5;
            showChangeWeather(weather);
        } else {
            this.weather = field.getWeather();
            this.elapsedTurn = field.getElapsedTurn();
            this.countForRecovery = field.getCountForRecovery();
        }
    }

    public Field elapseTurn() throws InterruptedException {
        if(this.countForRecovery <= this.elapsedTurn + 1) {
            showUndoWeather(this.weather);
            return initializeField();
        }
        showKeepWeather(this.weather);
        return keepField(this.weather, this.elapsedTurn + 1, this.countForRecovery);
    }

    public PokemonInfo slipDamageBySandStorm(PokemonInfo target) throws InterruptedException {
        int damage = target.getRealValHitPoint() / 16;
        return target.damagePoke(damage);
    }

    public PokemonInfo slipDamageByHail(PokemonInfo target) throws InterruptedException {
        int damage = target.getRealValHitPoint() / 16;
        return target.damagePoke(damage);
    }

    public double damageRateByWeather(Move move) {
        Type useType = move.baseMPrm().getMoveType();
        if(useType == Type.FIRE) {
            if(this.weather == Weather.DROUGHT) { return 1.5;}
            if(this.weather == Weather.RAIN) { return 0.5;}
        }
        if(useType == Type.WATER) {
            if(this.weather == Weather.DROUGHT) { return 0.5;}
            if(this.weather == Weather.RAIN) { return 1.5;}
        }
        return 1.0;
    }
}
