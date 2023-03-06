package field;

import move.Move;
import pokemon.PokeInfo;

public interface Field {
    Weather weather();

    int elapsedTurn();

    int countForRecovery();

    Field elapseTurn() throws InterruptedException;

    PokeInfo slipDamageByWeather(PokeInfo target) throws InterruptedException;

    double damageRateByWeather(Move move);

    double defenceRateBySandStorm(PokeInfo defencePoke);
}
