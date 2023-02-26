import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import Enum.*;

import pokemon.BasePrm;
import pokemon.PokemonInfo;
import pokemon.PokemonInfoImpl;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatusAilmentTest {
    @Test
    @DisplayName("やけど状態が維持されていること")
    public void testStatusAilment1() {
        PokemonInfo myPokemon = new PokemonInfoImpl(BasePrm.BULBASAUR);
        myPokemon = myPokemon.withStatusAilment(StatusAilment.BURN);

        assertEquals(myPokemon.getStatusAilment(),StatusAilment.BURN);
    }

    @Test
    @DisplayName("やけど状態によるダメージ倍率が正しいこと")
    public void testStatusAilment2() {
        PokemonInfo myPokemon1 = new PokemonInfoImpl(BasePrm.CHARMANDER);
        PokemonInfo myPokemon2 = new PokemonInfoImpl(BasePrm.SQUIRTLE);
        myPokemon1 = myPokemon1.withStatusAilment(StatusAilment.BURN);

        assertEquals(myPokemon1.getStatusAilment().dameRateByBurn(),0.5);
        assertEquals(myPokemon2.getStatusAilment().dameRateByBurn(),1.0);
    }
}
