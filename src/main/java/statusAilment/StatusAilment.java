package statusAilment;

import pokemon.PokemonInfo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public interface StatusAilment {
    Set<Ailment> SLIP_DAMAGE_AILMENT = new HashSet<>(Arrays.asList(Ailment.POISON, Ailment.BAD_POISON, Ailment.BURN));
    Ailment getValue();
    int getElapsedTurn();
    int getCountRecoverySleep();
    StatusAilment comeTurn(String pokeName) throws InterruptedException;
    boolean canMove(String pokeName) throws InterruptedException;
    boolean isFine();
    boolean isSick();
    double damageRateByBurn();
    double speedRateByParalysis();
    PokemonInfo slipDamageByAilment(PokemonInfo target) throws InterruptedException;

}
