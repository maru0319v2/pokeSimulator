package field;

import move.Move;
import pokemon.PokeInfo;

public interface Field {
    Weather weather();

    int elapsedTurn();

    int countForRecovery();

    Field elapseTurn() throws InterruptedException;

    PokeInfo slipDmgByWeather(PokeInfo target) throws InterruptedException;

    double dmgRateByWeather(Move move);

    double dfcRateBySandStorm(PokeInfo defencePoke);
}
