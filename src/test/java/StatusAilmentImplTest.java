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
    @DisplayName("やけど状態が健康とひんし状態以外に上書きされないこと")
    public void test1() {
        PokemonInfo poke1 = new PokemonInfoImpl(BasePrm.BULBASAUR);
        PokemonInfo poke2 = new PokemonInfoImpl(BasePrm.CHARIZARD);
        poke1 = poke1.withStatusAilment(new StatusAilmentImpl(Ailment.BURN));
        poke2 = poke2.withStatusAilment(new StatusAilmentImpl(Ailment.BURN));

        poke1 = poke1.withStatusAilment(new StatusAilmentImpl(Ailment.POISON));
        assertEquals(Ailment.BURN, poke1.getStatusAilment().getValue());

        poke1 = poke1.withStatusAilment(new StatusAilmentImpl(Ailment.BAD_POISON));
        assertEquals(Ailment.BURN, poke1.getStatusAilment().getValue());

        poke1 = poke1.withStatusAilment(new StatusAilmentImpl(Ailment.PARALYSIS));
        assertEquals(Ailment.BURN, poke1.getStatusAilment().getValue());

        poke1 = poke1.withStatusAilment(new StatusAilmentImpl(Ailment.SLEEP));
        assertEquals(Ailment.BURN, poke1.getStatusAilment().getValue());

        poke1 = poke1.withStatusAilment(new StatusAilmentImpl(Ailment.FREEZE));
        assertEquals(Ailment.BURN, poke1.getStatusAilment().getValue());

        poke1 = poke1.withStatusAilment(new StatusAilmentImpl(Ailment.FAINTING));
        assertEquals(Ailment.FAINTING, poke1.getStatusAilment().getValue());

        poke2 = poke2.withStatusAilment(new StatusAilmentImpl(Ailment.NONE));
        assertEquals(Ailment.NONE, poke2.getStatusAilment().getValue());
    }

    @Test
    @DisplayName("こおり状態が健康とひんし状態以外に上書きされないこと")
    public void test2() {
        PokemonInfo poke1 = new PokemonInfoImpl(BasePrm.BULBASAUR);
        PokemonInfo poke2 = new PokemonInfoImpl(BasePrm.CHARIZARD);
        poke1 = poke1.withStatusAilment(new StatusAilmentImpl(Ailment.FREEZE));
        poke2 = poke2.withStatusAilment(new StatusAilmentImpl(Ailment.FREEZE));

        poke1 = poke1.withStatusAilment(new StatusAilmentImpl(Ailment.POISON));
        assertEquals(Ailment.FREEZE, poke1.getStatusAilment().getValue());

        poke1 = poke1.withStatusAilment(new StatusAilmentImpl(Ailment.BAD_POISON));
        assertEquals(Ailment.FREEZE, poke1.getStatusAilment().getValue());

        poke1 = poke1.withStatusAilment(new StatusAilmentImpl(Ailment.PARALYSIS));
        assertEquals(Ailment.FREEZE, poke1.getStatusAilment().getValue());

        poke1 = poke1.withStatusAilment(new StatusAilmentImpl(Ailment.SLEEP));
        assertEquals(Ailment.FREEZE, poke1.getStatusAilment().getValue());

        poke1 = poke1.withStatusAilment(new StatusAilmentImpl(Ailment.BURN));
        assertEquals(Ailment.FREEZE, poke1.getStatusAilment().getValue());

        poke1 = poke1.withStatusAilment(new StatusAilmentImpl(Ailment.FAINTING));
        assertEquals(Ailment.FAINTING, poke1.getStatusAilment().getValue());

        poke2 = poke2.withStatusAilment(new StatusAilmentImpl(Ailment.NONE));
        assertEquals(Ailment.NONE, poke2.getStatusAilment().getValue());
    }

    @Test
    @DisplayName("まひ状態が健康とひんし状態以外に上書きされないこと")
    public void test3() {
        PokemonInfo poke1 = new PokemonInfoImpl(BasePrm.BULBASAUR);
        PokemonInfo poke2 = new PokemonInfoImpl(BasePrm.CHARIZARD);
        poke1 = poke1.withStatusAilment(new StatusAilmentImpl(Ailment.PARALYSIS));
        poke2 = poke2.withStatusAilment(new StatusAilmentImpl(Ailment.PARALYSIS));

        poke1 = poke1.withStatusAilment(new StatusAilmentImpl(Ailment.POISON));
        assertEquals(Ailment.PARALYSIS, poke1.getStatusAilment().getValue());

        poke1 = poke1.withStatusAilment(new StatusAilmentImpl(Ailment.BAD_POISON));
        assertEquals(Ailment.PARALYSIS, poke1.getStatusAilment().getValue());

        poke1 = poke1.withStatusAilment(new StatusAilmentImpl(Ailment.FREEZE));
        assertEquals(Ailment.PARALYSIS, poke1.getStatusAilment().getValue());

        poke1 = poke1.withStatusAilment(new StatusAilmentImpl(Ailment.SLEEP));
        assertEquals(Ailment.PARALYSIS, poke1.getStatusAilment().getValue());

        poke1 = poke1.withStatusAilment(new StatusAilmentImpl(Ailment.BURN));
        assertEquals(Ailment.PARALYSIS, poke1.getStatusAilment().getValue());

        poke1 = poke1.withStatusAilment(new StatusAilmentImpl(Ailment.FAINTING));
        assertEquals(Ailment.FAINTING, poke1.getStatusAilment().getValue());

        poke2 = poke2.withStatusAilment(new StatusAilmentImpl(Ailment.NONE));
        assertEquals(Ailment.NONE, poke2.getStatusAilment().getValue());
    }

    @Test
    @DisplayName("どく状態が健康とひんし状態以外に上書きされないこと")
    public void test4() {
        PokemonInfo poke1 = new PokemonInfoImpl(BasePrm.BULBASAUR);
        PokemonInfo poke2 = new PokemonInfoImpl(BasePrm.CHARIZARD);
        poke1 = poke1.withStatusAilment(new StatusAilmentImpl(Ailment.POISON));
        poke2 = poke2.withStatusAilment(new StatusAilmentImpl(Ailment.POISON));

        poke1 = poke1.withStatusAilment(new StatusAilmentImpl(Ailment.PARALYSIS));
        assertEquals(Ailment.POISON, poke1.getStatusAilment().getValue());

        poke1 = poke1.withStatusAilment(new StatusAilmentImpl(Ailment.BAD_POISON));
        assertEquals(Ailment.POISON, poke1.getStatusAilment().getValue());

        poke1 = poke1.withStatusAilment(new StatusAilmentImpl(Ailment.FREEZE));
        assertEquals(Ailment.POISON, poke1.getStatusAilment().getValue());

        poke1 = poke1.withStatusAilment(new StatusAilmentImpl(Ailment.SLEEP));
        assertEquals(Ailment.POISON, poke1.getStatusAilment().getValue());

        poke1 = poke1.withStatusAilment(new StatusAilmentImpl(Ailment.BURN));
        assertEquals(Ailment.POISON, poke1.getStatusAilment().getValue());

        poke1 = poke1.withStatusAilment(new StatusAilmentImpl(Ailment.FAINTING));
        assertEquals(Ailment.FAINTING, poke1.getStatusAilment().getValue());

        poke2 = poke2.withStatusAilment(new StatusAilmentImpl(Ailment.NONE));
        assertEquals(Ailment.NONE, poke2.getStatusAilment().getValue());
    }

    @Test
    @DisplayName("もうどく状態が健康とひんし状態以外に上書きされないこと")
    public void test5() {
        PokemonInfo poke1 = new PokemonInfoImpl(BasePrm.BULBASAUR);
        PokemonInfo poke2 = new PokemonInfoImpl(BasePrm.CHARIZARD);
        poke1 = poke1.withStatusAilment(new StatusAilmentImpl(Ailment.BAD_POISON));
        poke2 = poke2.withStatusAilment(new StatusAilmentImpl(Ailment.BAD_POISON));

        poke1 = poke1.withStatusAilment(new StatusAilmentImpl(Ailment.PARALYSIS));
        assertEquals(Ailment.BAD_POISON, poke1.getStatusAilment().getValue());

        poke1 = poke1.withStatusAilment(new StatusAilmentImpl(Ailment.POISON));
        assertEquals(Ailment.BAD_POISON, poke1.getStatusAilment().getValue());

        poke1 = poke1.withStatusAilment(new StatusAilmentImpl(Ailment.FREEZE));
        assertEquals(Ailment.BAD_POISON, poke1.getStatusAilment().getValue());

        poke1 = poke1.withStatusAilment(new StatusAilmentImpl(Ailment.SLEEP));
        assertEquals(Ailment.BAD_POISON, poke1.getStatusAilment().getValue());

        poke1 = poke1.withStatusAilment(new StatusAilmentImpl(Ailment.BURN));
        assertEquals(Ailment.BAD_POISON, poke1.getStatusAilment().getValue());

        poke1 = poke1.withStatusAilment(new StatusAilmentImpl(Ailment.FAINTING));
        assertEquals(Ailment.FAINTING, poke1.getStatusAilment().getValue());

        poke2 = poke2.withStatusAilment(new StatusAilmentImpl(Ailment.NONE));
        assertEquals(Ailment.NONE, poke2.getStatusAilment().getValue());
    }

    @Test
    @DisplayName("ねむり状態が健康とひんし状態以外に上書きされないこと")
    public void test6() {
        PokemonInfo poke1 = new PokemonInfoImpl(BasePrm.BULBASAUR);
        PokemonInfo poke2 = new PokemonInfoImpl(BasePrm.CHARIZARD);
        poke1 = poke1.withStatusAilment(new StatusAilmentImpl(Ailment.SLEEP));
        poke2 = poke2.withStatusAilment(new StatusAilmentImpl(Ailment.SLEEP));

        poke1 = poke1.withStatusAilment(new StatusAilmentImpl(Ailment.PARALYSIS));
        assertEquals(Ailment.SLEEP, poke1.getStatusAilment().getValue());

        poke1 = poke1.withStatusAilment(new StatusAilmentImpl(Ailment.POISON));
        assertEquals(Ailment.SLEEP, poke1.getStatusAilment().getValue());

        poke1 = poke1.withStatusAilment(new StatusAilmentImpl(Ailment.FREEZE));
        assertEquals(Ailment.SLEEP, poke1.getStatusAilment().getValue());

        poke1 = poke1.withStatusAilment(new StatusAilmentImpl(Ailment.BAD_POISON));
        assertEquals(Ailment.SLEEP, poke1.getStatusAilment().getValue());

        poke1 = poke1.withStatusAilment(new StatusAilmentImpl(Ailment.BURN));
        assertEquals(Ailment.SLEEP, poke1.getStatusAilment().getValue());

        poke1 = poke1.withStatusAilment(new StatusAilmentImpl(Ailment.FAINTING));
        assertEquals(Ailment.FAINTING, poke1.getStatusAilment().getValue());

        poke2 = poke2.withStatusAilment(new StatusAilmentImpl(Ailment.NONE));
        assertEquals(Ailment.NONE, poke2.getStatusAilment().getValue());
    }

    @Test
    @DisplayName("ひんし状態が健康と状態以外に上書きされないこと")
    public void test7() {
        PokemonInfo poke1 = new PokemonInfoImpl(BasePrm.BULBASAUR);
        poke1 = poke1.withStatusAilment(new StatusAilmentImpl(Ailment.FAINTING));

        poke1 = poke1.withStatusAilment(new StatusAilmentImpl(Ailment.PARALYSIS));
        assertEquals(Ailment.FAINTING, poke1.getStatusAilment().getValue());

        poke1 = poke1.withStatusAilment(new StatusAilmentImpl(Ailment.POISON));
        assertEquals(Ailment.FAINTING, poke1.getStatusAilment().getValue());

        poke1 = poke1.withStatusAilment(new StatusAilmentImpl(Ailment.FREEZE));
        assertEquals(Ailment.FAINTING, poke1.getStatusAilment().getValue());

        poke1 = poke1.withStatusAilment(new StatusAilmentImpl(Ailment.BAD_POISON));
        assertEquals(Ailment.FAINTING, poke1.getStatusAilment().getValue());

        poke1 = poke1.withStatusAilment(new StatusAilmentImpl(Ailment.BURN));
        assertEquals(Ailment.FAINTING, poke1.getStatusAilment().getValue());

        poke1 = poke1.withStatusAilment(new StatusAilmentImpl(Ailment.NONE));
        assertEquals(Ailment.NONE, poke1.getStatusAilment().getValue());
    }

    @Test
    @DisplayName("健康状態がすべての状態に上書きできること")
    public void test8() {
        PokemonInfo poke1 = new PokemonInfoImpl(BasePrm.BULBASAUR);
        PokemonInfo poke2 = new PokemonInfoImpl(BasePrm.BULBASAUR);
        PokemonInfo poke3 = new PokemonInfoImpl(BasePrm.BULBASAUR);
        PokemonInfo poke4 = new PokemonInfoImpl(BasePrm.BULBASAUR);
        PokemonInfo poke5 = new PokemonInfoImpl(BasePrm.BULBASAUR);
        PokemonInfo poke6 = new PokemonInfoImpl(BasePrm.BULBASAUR);

        poke1 = poke1.withStatusAilment(new StatusAilmentImpl(Ailment.PARALYSIS));
        assertEquals(Ailment.PARALYSIS, poke1.getStatusAilment().getValue());

        poke2 = poke2.withStatusAilment(new StatusAilmentImpl(Ailment.POISON));
        assertEquals(Ailment.POISON, poke2.getStatusAilment().getValue());

        poke3 = poke3.withStatusAilment(new StatusAilmentImpl(Ailment.FREEZE));
        assertEquals(Ailment.FREEZE, poke3.getStatusAilment().getValue());

        poke4 = poke4.withStatusAilment(new StatusAilmentImpl(Ailment.BAD_POISON));
        assertEquals(Ailment.BAD_POISON, poke4.getStatusAilment().getValue());

        poke5 = poke5.withStatusAilment(new StatusAilmentImpl(Ailment.BURN));
        assertEquals(Ailment.BURN, poke5.getStatusAilment().getValue());

        poke6 = poke6.withStatusAilment(new StatusAilmentImpl(Ailment.FAINTING));
        assertEquals(Ailment.FAINTING, poke6.getStatusAilment().getValue());
    }

    @Test
    @DisplayName("ねむり状態が1ターン経過後は継続され行動できず、5ターン経過後は解除され行動できること")
    public void test9() throws InterruptedException {
        PokemonInfo myPokemon = new PokemonInfoImpl(BasePrm.BULBASAUR);
        myPokemon = myPokemon.withStatusAilment(new StatusAilmentImpl(Ailment.SLEEP));
        myPokemon = myPokemon.withStatusAilment(myPokemon.getStatusAilment().comeTurn());
        // 1ターン経過後
        assertEquals(Ailment.SLEEP, myPokemon.getStatusAilment().getValue());
        assertFalse(myPokemon.getStatusAilment().canMove());
        myPokemon = myPokemon.withStatusAilment(myPokemon.getStatusAilment().comeTurn());
        myPokemon = myPokemon.withStatusAilment(myPokemon.getStatusAilment().comeTurn());
        myPokemon = myPokemon.withStatusAilment(myPokemon.getStatusAilment().comeTurn());
        myPokemon = myPokemon.withStatusAilment(myPokemon.getStatusAilment().comeTurn());
        // 5ターン経過後
        assertEquals(Ailment.NONE, myPokemon.getStatusAilment().getValue());
        assertTrue(myPokemon.getStatusAilment().canMove());
    }

    @Test
    @DisplayName("ねむり状態から0ターン経過後、新たにねむり状態になってもねむり解除ターンがリセットされないこと")
    public void test10() {
        PokemonInfo poke = new PokemonInfoImpl(BasePrm.BULBASAUR);
        poke = poke.withStatusAilment(new StatusAilmentImpl(Ailment.SLEEP));
        int beforeCount = poke.getStatusAilment().getCountRecoverySleep();
        poke = poke.withStatusAilment(new StatusAilmentImpl(Ailment.SLEEP));
        int afterCount = poke.getStatusAilment().getCountRecoverySleep();

        assertEquals(beforeCount, afterCount);
    }

    @Test
    @DisplayName("ねむり状態から1ターン経過後、新たにねむり状態になっても経過ターンがリセットされないこと")
    public void test11() throws InterruptedException {
        PokemonInfo poke = new PokemonInfoImpl(BasePrm.BULBASAUR);
        poke = poke.withStatusAilment(new StatusAilmentImpl(Ailment.SLEEP));
        poke = poke.withStatusAilment(poke.getStatusAilment().comeTurn());
        poke = poke.withStatusAilment(new StatusAilmentImpl(Ailment.SLEEP));

        assertEquals(1, poke.getStatusAilment().getElapsedTurn());
    }

    @Test
    @DisplayName("やけど状態によるダメージ倍率が正しいこと")
    public void test12() {
        PokemonInfo myPokemon1 = new PokemonInfoImpl(BasePrm.CHARMANDER);
        PokemonInfo myPokemon2 = new PokemonInfoImpl(BasePrm.SQUIRTLE);
        myPokemon1 = myPokemon1.withStatusAilment(new StatusAilmentImpl(Ailment.BURN));

        assertEquals(0.5, myPokemon1.getStatusAilment().damageRateByBurn());
        assertEquals(1.0, myPokemon2.getStatusAilment().damageRateByBurn());
    }
}
