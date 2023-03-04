package statusAilment;

import pokemon.PokemonInfo;

public interface StatusAilment {
    Ailment getValue();
    int getElapsedTurn();
    int getCountRecoverySleep();
    StatusAilment comeTurn(String pokeName) throws InterruptedException;
    boolean canMove(String pokeName) throws InterruptedException;
    boolean isFine();
    boolean isSick();
    double damageRateByBurn();
    double speedRateByParalysis();
    PokemonInfo slipDamageByBurn(PokemonInfo target) throws InterruptedException;
    PokemonInfo slipDamageByPoison(PokemonInfo target) throws InterruptedException;
    PokemonInfo slipDamageByBadPoison(PokemonInfo target) throws InterruptedException;

}
