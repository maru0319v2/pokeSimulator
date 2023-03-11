package statusAilment;

import pokemon.PokeInfo;

public interface Ailment {

    AilmentEnum val();

    int elapsedTurn();

    int countRecoverySleep();

    Ailment comeTurn(String pokeName) throws InterruptedException;

    boolean canMove(String pokeName) throws InterruptedException;

    boolean isFine();

    boolean isSick();

    double dmgRateByBurn();

    double spdRateByParalysis();

    PokeInfo slipDmgByAilment(PokeInfo target) throws InterruptedException;

}
