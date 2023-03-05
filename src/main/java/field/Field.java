package field;

import move.Move;
import pokemon.PokemonInfo;

public interface Field {
    Weather getWeather();
    int getElapsedTurn();
    int getCountForRecovery();
    Field elapseTurn() throws InterruptedException;
    PokemonInfo slipDamageByWeather(PokemonInfo target) throws InterruptedException;
    double damageRateByWeather(Move move);
    double defenceRateBySandStorm(PokemonInfo defencePoke);
}
