package statusAilment;

import pokemon.PokemonInfo;

public interface StatusAilmentInterface {
    Ailment getValue();
    StatusAilmentImpl comeTurn();
    boolean canMove() throws InterruptedException;
    public double damageRateByBurn();
    double speedRateByParalysis();
    PokemonInfo slipDamageByBurn(PokemonInfo target) throws InterruptedException;
    PokemonInfo slipDamageByPoison(PokemonInfo target) throws InterruptedException;
    PokemonInfo slipDamageByBadPoison(PokemonInfo target) throws InterruptedException;

}
