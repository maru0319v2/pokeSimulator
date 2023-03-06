package statusAilment;

import pokemon.PokeInfo;

public interface Ailment {

    AilmentE val();

    int elapsedTurn();

    int countRecoverySleep();

    Ailment comeTurn(String pokeName) throws InterruptedException;

    boolean canMove(String pokeName) throws InterruptedException;

    boolean isFine();

    boolean isSick();

    double damageRateByBurn();

    double speedRateByParalysis();

    PokeInfo slipDamageByAilment(PokeInfo target) throws InterruptedException;

}
