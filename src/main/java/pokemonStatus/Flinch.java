package pokemonStatus;

public interface Flinch {
    boolean canMove(String pokeName) throws InterruptedException;

    boolean val();
}
