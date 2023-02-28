import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import Enum.*;

import pokemon.BasePrm;
import pokemon.PokemonInfo;
import pokemon.PokemonInfoImpl;
import statusAilment.StatusAilmentImpl;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class AilmentTest {
    @Test
    @DisplayName("やけど状態が維持されていること")
    public void testStatusAilment1() {
        PokemonInfo myPokemon = new PokemonInfoImpl(BasePrm.BULBASAUR);
        myPokemon = myPokemon.withStatusAilment(new StatusAilmentImpl(Ailment.BURN));

        assertEquals(myPokemon.getStatusAilment().getValue(), Ailment.BURN);
    }

    @Test
    @DisplayName("やけど状態によるダメージ倍率が正しいこと")
    public void testStatusAilment2() {
        PokemonInfo myPokemon1 = new PokemonInfoImpl(BasePrm.CHARMANDER);
        PokemonInfo myPokemon2 = new PokemonInfoImpl(BasePrm.SQUIRTLE);
        myPokemon1 = myPokemon1.withStatusAilment(new StatusAilmentImpl(Ailment.BURN));

        assertEquals(myPokemon1.getStatusAilment().damageRateByBurn(),0.5);
        assertEquals(myPokemon2.getStatusAilment().damageRateByBurn(),1.0);
    }
}
