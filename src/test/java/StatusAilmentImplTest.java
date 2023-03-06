import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pokemon.BasePrm;
import pokemon.PokeInfo;
import statusAilment.AilmentE;

import static org.junit.jupiter.api.Assertions.*;
import static pokemon.PokeInfoI.initialize;
import static statusAilment.AilmentI.changeAilment;

public class StatusAilmentImplTest {
    @Test
    @DisplayName("やけど状態が健康とひんし状態以外に上書きされないこと")
    public void test1() throws InterruptedException {
        PokeInfo poke1 = initialize(BasePrm.BULBASAUR);
        PokeInfo poke2 = initialize(BasePrm.CHARIZARD);
        poke1 = poke1.withAilment(changeAilment(poke1, AilmentE.BURN));
        poke2 = poke2.withAilment(changeAilment(poke2, AilmentE.BURN));

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentE.POISON));
        assertEquals(AilmentE.BURN, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentE.BAD_POISON));
        assertEquals(AilmentE.BURN, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentE.PARALYSIS));
        assertEquals(AilmentE.BURN, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentE.SLEEP));
        assertEquals(AilmentE.BURN, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentE.FREEZE));
        assertEquals(AilmentE.BURN, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentE.FAINTING));
        assertEquals(AilmentE.FAINTING, poke1.ailment().val());

        poke2 = poke2.withAilment(changeAilment(poke2, AilmentE.FINE));
        assertEquals(AilmentE.FINE, poke2.ailment().val());
    }

    @Test
    @DisplayName("こおり状態が健康とひんし状態以外に上書きされないこと")
    public void test2() throws InterruptedException {
        PokeInfo poke1 = initialize(BasePrm.BULBASAUR);
        PokeInfo poke2 = initialize(BasePrm.CHARIZARD);
        poke1 = poke1.withAilment(changeAilment(poke1, AilmentE.FREEZE));
        poke2 = poke2.withAilment(changeAilment(poke2, AilmentE.FREEZE));

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentE.POISON));
        assertEquals(AilmentE.FREEZE, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentE.BAD_POISON));
        assertEquals(AilmentE.FREEZE, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentE.PARALYSIS));
        assertEquals(AilmentE.FREEZE, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentE.SLEEP));
        assertEquals(AilmentE.FREEZE, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentE.BURN));
        assertEquals(AilmentE.FREEZE, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentE.FAINTING));
        assertEquals(AilmentE.FAINTING, poke1.ailment().val());

        poke2 = poke2.withAilment(changeAilment(poke2, AilmentE.FINE));
        assertEquals(AilmentE.FINE, poke2.ailment().val());
    }

    @Test
    @DisplayName("まひ状態が健康とひんし状態以外に上書きされないこと")
    public void test3() throws InterruptedException {
        PokeInfo poke1 = initialize(BasePrm.BULBASAUR);
        PokeInfo poke2 = initialize(BasePrm.CHARIZARD);
        poke1 = poke1.withAilment(changeAilment(poke1, AilmentE.PARALYSIS));
        poke2 = poke2.withAilment(changeAilment(poke2, AilmentE.PARALYSIS));

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentE.POISON));
        assertEquals(AilmentE.PARALYSIS, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentE.BAD_POISON));
        assertEquals(AilmentE.PARALYSIS, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentE.FREEZE));
        assertEquals(AilmentE.PARALYSIS, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentE.SLEEP));
        assertEquals(AilmentE.PARALYSIS, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentE.BURN));
        assertEquals(AilmentE.PARALYSIS, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentE.FAINTING));
        assertEquals(AilmentE.FAINTING, poke1.ailment().val());

        poke2 = poke2.withAilment(changeAilment(poke2, AilmentE.FINE));
        assertEquals(AilmentE.FINE, poke2.ailment().val());
    }

    @Test
    @DisplayName("どく状態が健康とひんし状態以外に上書きされないこと")
    public void test4() throws InterruptedException {
        PokeInfo poke1 = initialize(BasePrm.BULBASAUR);
        PokeInfo poke2 = initialize(BasePrm.CHARIZARD);
        poke1 = poke1.withAilment(changeAilment(poke1, AilmentE.POISON));
        poke2 = poke2.withAilment(changeAilment(poke2, AilmentE.POISON));

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentE.PARALYSIS));
        assertEquals(AilmentE.POISON, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentE.BAD_POISON));
        assertEquals(AilmentE.POISON, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentE.FREEZE));
        assertEquals(AilmentE.POISON, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentE.SLEEP));
        assertEquals(AilmentE.POISON, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentE.BURN));
        assertEquals(AilmentE.POISON, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentE.FAINTING));
        assertEquals(AilmentE.FAINTING, poke1.ailment().val());

        poke2 = poke2.withAilment(changeAilment(poke2, AilmentE.FINE));
        assertEquals(AilmentE.FINE, poke2.ailment().val());
    }

    @Test
    @DisplayName("もうどく状態が健康とひんし状態以外に上書きされないこと")
    public void test5() throws InterruptedException {
        PokeInfo poke1 = initialize(BasePrm.BULBASAUR);
        PokeInfo poke2 = initialize(BasePrm.CHARIZARD);
        poke1 = poke1.withAilment(changeAilment(poke1, AilmentE.BAD_POISON));
        poke2 = poke2.withAilment(changeAilment(poke2, AilmentE.BAD_POISON));

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentE.PARALYSIS));
        assertEquals(AilmentE.BAD_POISON, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentE.POISON));
        assertEquals(AilmentE.BAD_POISON, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentE.FREEZE));
        assertEquals(AilmentE.BAD_POISON, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentE.SLEEP));
        assertEquals(AilmentE.BAD_POISON, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentE.BURN));
        assertEquals(AilmentE.BAD_POISON, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentE.FAINTING));
        assertEquals(AilmentE.FAINTING, poke1.ailment().val());

        poke2 = poke2.withAilment(changeAilment(poke2, AilmentE.FINE));
        assertEquals(AilmentE.FINE, poke2.ailment().val());
    }

    @Test
    @DisplayName("ねむり状態が健康とひんし状態以外に上書きされないこと")
    public void test6() throws InterruptedException {
        PokeInfo poke1 = initialize(BasePrm.BULBASAUR);
        PokeInfo poke2 = initialize(BasePrm.CHARIZARD);
        poke1 = poke1.withAilment(changeAilment(poke1, AilmentE.SLEEP));
        poke2 = poke2.withAilment(changeAilment(poke2, AilmentE.SLEEP));

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentE.PARALYSIS));
        assertEquals(AilmentE.SLEEP, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentE.POISON));
        assertEquals(AilmentE.SLEEP, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentE.FREEZE));
        assertEquals(AilmentE.SLEEP, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentE.BAD_POISON));
        assertEquals(AilmentE.SLEEP, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentE.BURN));
        assertEquals(AilmentE.SLEEP, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentE.FAINTING));
        assertEquals(AilmentE.FAINTING, poke1.ailment().val());

        poke2 = poke2.withAilment(changeAilment(poke2, AilmentE.FINE));
        assertEquals(AilmentE.FINE, poke2.ailment().val());
    }

    @Test
    @DisplayName("ひんし状態が健康と状態以外に上書きされないこと")
    public void test7() throws InterruptedException {
        PokeInfo poke1 = initialize(BasePrm.BULBASAUR);
        poke1 = poke1.withAilment(changeAilment(poke1, AilmentE.FAINTING));

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentE.PARALYSIS));
        assertEquals(AilmentE.FAINTING, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentE.POISON));
        assertEquals(AilmentE.FAINTING, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentE.FREEZE));
        assertEquals(AilmentE.FAINTING, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentE.BAD_POISON));
        assertEquals(AilmentE.FAINTING, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentE.BURN));
        assertEquals(AilmentE.FAINTING, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentE.FINE));
        assertEquals(AilmentE.FINE, poke1.ailment().val());
    }

    @Test
    @DisplayName("健康状態がすべての状態に上書きできること")
    public void test8() throws InterruptedException {
        PokeInfo poke1 = initialize(BasePrm.BULBASAUR);
        PokeInfo poke2 = initialize(BasePrm.BULBASAUR);
        PokeInfo poke3 = initialize(BasePrm.BULBASAUR);
        PokeInfo poke4 = initialize(BasePrm.BULBASAUR);
        PokeInfo poke5 = initialize(BasePrm.BULBASAUR);
        PokeInfo poke6 = initialize(BasePrm.BULBASAUR);

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentE.PARALYSIS));
        assertEquals(AilmentE.PARALYSIS, poke1.ailment().val());

        poke2 = poke2.withAilment(changeAilment(poke2, AilmentE.POISON));
        assertEquals(AilmentE.POISON, poke2.ailment().val());

        poke3 = poke3.withAilment(changeAilment(poke3, AilmentE.FREEZE));
        assertEquals(AilmentE.FREEZE, poke3.ailment().val());

        poke4 = poke4.withAilment(changeAilment(poke4, AilmentE.BAD_POISON));
        assertEquals(AilmentE.BAD_POISON, poke4.ailment().val());

        poke5 = poke5.withAilment(changeAilment(poke5, AilmentE.BURN));
        assertEquals(AilmentE.BURN, poke5.ailment().val());

        poke6 = poke6.withAilment(changeAilment(poke6, AilmentE.FAINTING));
        assertEquals(AilmentE.FAINTING, poke6.ailment().val());
    }

    @Test
    @DisplayName("ねむり状態が1ターン経過後は継続され行動できず、5ターン経過後は解除され行動できること")
    public void test9() throws InterruptedException {
        PokeInfo myPokemon = initialize(BasePrm.BULBASAUR);
        myPokemon = myPokemon.withAilment(changeAilment(myPokemon, AilmentE.SLEEP));
        myPokemon = myPokemon.withAilment(myPokemon.ailment().comeTurn(myPokemon.basePrm().pName()));
        // 1ターン経過後
        assertEquals(AilmentE.SLEEP, myPokemon.ailment().val());
        assertFalse(myPokemon.ailment().canMove(myPokemon.basePrm().pName()));
        myPokemon = myPokemon.withAilment(myPokemon.ailment().comeTurn(myPokemon.basePrm().pName()));
        myPokemon = myPokemon.withAilment(myPokemon.ailment().comeTurn(myPokemon.basePrm().pName()));
        myPokemon = myPokemon.withAilment(myPokemon.ailment().comeTurn(myPokemon.basePrm().pName()));
        myPokemon = myPokemon.withAilment(myPokemon.ailment().comeTurn(myPokemon.basePrm().pName()));
        // 5ターン経過後
        assertEquals(AilmentE.FINE, myPokemon.ailment().val());
        assertTrue(myPokemon.ailment().canMove(myPokemon.basePrm().pName()));
    }

    @Test
    @DisplayName("ねむり状態から0ターン経過後、新たにねむり状態になってもねむり解除ターンがリセットされないこと")
    public void test10() throws InterruptedException {
        PokeInfo poke = initialize(BasePrm.BULBASAUR);
        poke = poke.withAilment(changeAilment(poke, AilmentE.SLEEP));
        int beforeCount = poke.ailment().countRecoverySleep();
        poke = poke.withAilment(changeAilment(poke, AilmentE.SLEEP));
        int afterCount = poke.ailment().countRecoverySleep();

        assertEquals(beforeCount, afterCount);
    }

    @Test
    @DisplayName("ねむり状態から1ターン経過後、新たにねむり状態になっても経過ターンがリセットされないこと")
    public void test11() throws InterruptedException {
        PokeInfo poke = initialize(BasePrm.BULBASAUR);
        poke = poke.withAilment(changeAilment(poke, AilmentE.SLEEP));
        poke = poke.withAilment(poke.ailment().comeTurn(poke.basePrm().pName()));
        poke = poke.withAilment(changeAilment(poke, AilmentE.SLEEP));

        assertEquals(1, poke.ailment().elapsedTurn());
    }

    @Test
    @DisplayName("やけど状態によるダメージ倍率が正しいこと")
    public void test12() throws InterruptedException {
        PokeInfo myPokemon1 = initialize(BasePrm.CHARMANDER);
        PokeInfo myPokemon2 = initialize(BasePrm.SQUIRTLE);
        myPokemon1 = myPokemon1.withAilment(changeAilment(myPokemon1, AilmentE.BURN));

        assertEquals(0.5, myPokemon1.ailment().damageRateByBurn());
        assertEquals(1.0, myPokemon2.ailment().damageRateByBurn());
    }
}
