package pokemonStatus;

import pokemon.PokeInfo;

public interface Confusion {
    boolean canMove(String name);

    PokeInfo damageMe(PokeInfo target);

    Confusion elapseTurn(String name);

    boolean val();

    int countRecovery();

    int elapsedTurn();
}
