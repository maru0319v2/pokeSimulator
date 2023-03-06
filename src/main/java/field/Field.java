package field;

import move.Move;
import pokemon.PokeInfo;

public interface Field {
    Weather getWeather();

    int getElapsedTurn();

    int getCountForRecovery();

    Field elapseTurn() throws InterruptedException;

    PokeInfo slipDamageByWeather(PokeInfo target) throws InterruptedException;

    double damageRateByWeather(Move move);

    double defenceRateBySandStorm(PokeInfo defencePoke);
}
