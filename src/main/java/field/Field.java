package field;

import move.Move;
import pokemon.PokeInfo;

public interface Field {
    WeatherEnum weather();

    int elapsedTurnWeather();

    int countRecoveryWeather();

    Field elapsingTurnWeather() throws InterruptedException;

    PokeInfo slipDmgByWeather(PokeInfo target) throws InterruptedException;

    double dmgRateByWeather(Move move);

    double dfcRateBySandStorm(PokeInfo defencePoke);
}
