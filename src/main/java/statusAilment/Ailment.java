package statusAilment;

import pokemon.PokeInfo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public interface Ailment {
    Set<AilmentE> SLIP_DAMAGE_AILMENT = new HashSet<>(Arrays.asList(AilmentE.POISON, AilmentE.BAD_POISON, AilmentE.BURN));

    AilmentE getValue();

    int getElapsedTurn();

    int getCountRecoverySleep();

    Ailment comeTurn(String pokeName) throws InterruptedException;

    boolean canMove(String pokeName) throws InterruptedException;

    boolean isFine();

    boolean isSick();

    double damageRateByBurn();

    double speedRateByParalysis();

    PokeInfo slipDamageByAilment(PokeInfo target) throws InterruptedException;

}
