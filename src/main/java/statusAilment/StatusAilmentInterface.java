package statusAilment;

import pokemon.PokemonInfo;
import Enum.*;

public interface StatusAilmentInterface {
    Ailment getValue();
    StatusAilmentImpl comeTurn();
    boolean canMove();
    public double damageRateByBurn();
    PokemonInfo slipDamageByBurn(PokemonInfo target) throws InterruptedException;
    PokemonInfo slipDamageByPoison(PokemonInfo target) throws InterruptedException;

}
