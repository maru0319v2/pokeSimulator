package pokemonStatus;

import pokemon.PokeInfo;

public interface Confusion {
    boolean canMove(String name) throws InterruptedException;

    PokeInfo damageMe(PokeInfo target) throws InterruptedException;

    Confusion elapseTurn(String name) throws InterruptedException;

    boolean val();

    int countRecovery();

    int elapsedTurn();
}
