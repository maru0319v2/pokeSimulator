import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import pokemon.BasePrm;
import pokemon.PokemonInfo;
import pokemon.PokemonInfoImpl;
import statusAilment.Ailment;
import statusAilment.StatusAilmentImpl;

import static org.junit.jupiter.api.Assertions.*;

public class StatusAilmentImplTest {
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

    @Test
    @DisplayName("ねむり状態が1ターン経過後は継続され行動できず、5ターン経過後は解除され行動できること")
    public void testStatusAilment3() throws InterruptedException {
        PokemonInfo myPokemon = new PokemonInfoImpl(BasePrm.BULBASAUR);
        myPokemon = myPokemon.withStatusAilment(new StatusAilmentImpl(Ailment.SLEEP));
        myPokemon = myPokemon.withStatusAilment(myPokemon.getStatusAilment().comeTurn());
        // 1ターン経過後
        assertEquals(myPokemon.getStatusAilment().getValue(), Ailment.SLEEP);
        assertFalse(myPokemon.getStatusAilment().canMove());
        myPokemon = myPokemon.withStatusAilment(myPokemon.getStatusAilment().comeTurn());
        myPokemon = myPokemon.withStatusAilment(myPokemon.getStatusAilment().comeTurn());
        myPokemon = myPokemon.withStatusAilment(myPokemon.getStatusAilment().comeTurn());
        myPokemon = myPokemon.withStatusAilment(myPokemon.getStatusAilment().comeTurn());
        // 5ターン経過後
        assertEquals(myPokemon.getStatusAilment().getValue(), Ailment.NONE);
        assertTrue(myPokemon.getStatusAilment().canMove());

    }
}
