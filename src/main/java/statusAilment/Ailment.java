package statusAilment;

import pokemon.PokeInfo;

public interface Ailment {

    AilmentEnum val();

    int elapsedTurn();

    int countRecoverySleep();

    Ailment comeTurn(String pokeName);

    boolean canMove(String pokeName);

    boolean isFine();

    boolean isSick();

    double dmgRateByBurn();

    double spdRateByParalysis();

    PokeInfo slipDmgByAilment(PokeInfo target);

}
