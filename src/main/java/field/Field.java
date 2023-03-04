package field;

import move.Move;
import pokemon.PokemonInfo;

public interface Field {
    Weather getWeather();
    int getElapsedTurn();
    int getCountForRecovery();
    Field elapseTurn() throws InterruptedException;
    PokemonInfo slipDamageBySandStorm(PokemonInfo target) throws InterruptedException;
    PokemonInfo slipDamageByHail(PokemonInfo target) throws InterruptedException;
    double damageRateByWeather(Move move);
}
