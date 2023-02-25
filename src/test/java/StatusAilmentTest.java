import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import Enum.*;
import pokemon.Bulbasaur;
import pokemon.Charmander;
import pokemon.PokemonInfo;
import pokemon.Squirtle;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatusAilmentTest {
    @Test
    @DisplayName("やけど状態が維持されていること")
    public void testStatusAilment1() {
        PokemonInfo myPokemon = new Bulbasaur();
        myPokemon = myPokemon.withStatusAilment(StatusAilment.BURN);

        assertEquals(myPokemon.statusAilment(),StatusAilment.BURN);
    }

    @Test
    @DisplayName("やけど状態によるダメージ倍率が正しいこと")
    public void testStatusAilment2() {
        PokemonInfo myPokemon1 = new Charmander();
        PokemonInfo myPokemon2 = new Squirtle();
        myPokemon1 = myPokemon1.withStatusAilment(StatusAilment.BURN);

        assertEquals(myPokemon1.statusAilment().dameRateByBurn(),0.5);
        assertEquals(myPokemon2.statusAilment().dameRateByBurn(),1.0);
    }
}
