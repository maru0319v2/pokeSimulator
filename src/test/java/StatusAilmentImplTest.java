import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pokemon.BasePrm;
import pokemon.PokeInfo;
import pokemon.PokeInfoI;
import statusAilment.AilmentE;

import static org.junit.jupiter.api.Assertions.*;
import static statusAilment.AilmentI.changeAilment;

public class StatusAilmentImplTest {
    @Test
    @DisplayName("やけど状態が健康とひんし状態以外に上書きされないこと")
    public void test1() throws InterruptedException {
        PokeInfo poke1 = new PokeInfoI(BasePrm.BULBASAUR);
        PokeInfo poke2 = new PokeInfoI(BasePrm.CHARIZARD);
        poke1 = poke1.withStatusAilment(changeAilment(poke1, AilmentE.BURN));
        poke2 = poke2.withStatusAilment(changeAilment(poke2, AilmentE.BURN));

        poke1 = poke1.withStatusAilment(changeAilment(poke1, AilmentE.POISON));
        assertEquals(AilmentE.BURN, poke1.getStatusAilment().val());

        poke1 = poke1.withStatusAilment(changeAilment(poke1, AilmentE.BAD_POISON));
        assertEquals(AilmentE.BURN, poke1.getStatusAilment().val());

        poke1 = poke1.withStatusAilment(changeAilment(poke1, AilmentE.PARALYSIS));
        assertEquals(AilmentE.BURN, poke1.getStatusAilment().val());

        poke1 = poke1.withStatusAilment(changeAilment(poke1, AilmentE.SLEEP));
        assertEquals(AilmentE.BURN, poke1.getStatusAilment().val());

        poke1 = poke1.withStatusAilment(changeAilment(poke1, AilmentE.FREEZE));
        assertEquals(AilmentE.BURN, poke1.getStatusAilment().val());

        poke1 = poke1.withStatusAilment(changeAilment(poke1, AilmentE.FAINTING));
        assertEquals(AilmentE.FAINTING, poke1.getStatusAilment().val());

        poke2 = poke2.withStatusAilment(changeAilment(poke2, AilmentE.FINE));
        assertEquals(AilmentE.FINE, poke2.getStatusAilment().val());
    }

    @Test
    @DisplayName("こおり状態が健康とひんし状態以外に上書きされないこと")
    public void test2() throws InterruptedException {
        PokeInfo poke1 = new PokeInfoI(BasePrm.BULBASAUR);
        PokeInfo poke2 = new PokeInfoI(BasePrm.CHARIZARD);
        poke1 = poke1.withStatusAilment(changeAilment(poke1, AilmentE.FREEZE));
        poke2 = poke2.withStatusAilment(changeAilment(poke2, AilmentE.FREEZE));

        poke1 = poke1.withStatusAilment(changeAilment(poke1, AilmentE.POISON));
        assertEquals(AilmentE.FREEZE, poke1.getStatusAilment().val());

        poke1 = poke1.withStatusAilment(changeAilment(poke1, AilmentE.BAD_POISON));
        assertEquals(AilmentE.FREEZE, poke1.getStatusAilment().val());

        poke1 = poke1.withStatusAilment(changeAilment(poke1, AilmentE.PARALYSIS));
        assertEquals(AilmentE.FREEZE, poke1.getStatusAilment().val());

        poke1 = poke1.withStatusAilment(changeAilment(poke1, AilmentE.SLEEP));
        assertEquals(AilmentE.FREEZE, poke1.getStatusAilment().val());

        poke1 = poke1.withStatusAilment(changeAilment(poke1, AilmentE.BURN));
        assertEquals(AilmentE.FREEZE, poke1.getStatusAilment().val());

        poke1 = poke1.withStatusAilment(changeAilment(poke1, AilmentE.FAINTING));
        assertEquals(AilmentE.FAINTING, poke1.getStatusAilment().val());

        poke2 = poke2.withStatusAilment(changeAilment(poke2, AilmentE.FINE));
        assertEquals(AilmentE.FINE, poke2.getStatusAilment().val());
    }

    @Test
    @DisplayName("まひ状態が健康とひんし状態以外に上書きされないこと")
    public void test3() throws InterruptedException {
        PokeInfo poke1 = new PokeInfoI(BasePrm.BULBASAUR);
        PokeInfo poke2 = new PokeInfoI(BasePrm.CHARIZARD);
        poke1 = poke1.withStatusAilment(changeAilment(poke1, AilmentE.PARALYSIS));
        poke2 = poke2.withStatusAilment(changeAilment(poke2, AilmentE.PARALYSIS));

        poke1 = poke1.withStatusAilment(changeAilment(poke1, AilmentE.POISON));
        assertEquals(AilmentE.PARALYSIS, poke1.getStatusAilment().val());

        poke1 = poke1.withStatusAilment(changeAilment(poke1, AilmentE.BAD_POISON));
        assertEquals(AilmentE.PARALYSIS, poke1.getStatusAilment().val());

        poke1 = poke1.withStatusAilment(changeAilment(poke1, AilmentE.FREEZE));
        assertEquals(AilmentE.PARALYSIS, poke1.getStatusAilment().val());

        poke1 = poke1.withStatusAilment(changeAilment(poke1, AilmentE.SLEEP));
        assertEquals(AilmentE.PARALYSIS, poke1.getStatusAilment().val());

        poke1 = poke1.withStatusAilment(changeAilment(poke1, AilmentE.BURN));
        assertEquals(AilmentE.PARALYSIS, poke1.getStatusAilment().val());

        poke1 = poke1.withStatusAilment(changeAilment(poke1, AilmentE.FAINTING));
        assertEquals(AilmentE.FAINTING, poke1.getStatusAilment().val());

        poke2 = poke2.withStatusAilment(changeAilment(poke2, AilmentE.FINE));
        assertEquals(AilmentE.FINE, poke2.getStatusAilment().val());
    }

    @Test
    @DisplayName("どく状態が健康とひんし状態以外に上書きされないこと")
    public void test4() throws InterruptedException {
        PokeInfo poke1 = new PokeInfoI(BasePrm.BULBASAUR);
        PokeInfo poke2 = new PokeInfoI(BasePrm.CHARIZARD);
        poke1 = poke1.withStatusAilment(changeAilment(poke1, AilmentE.POISON));
        poke2 = poke2.withStatusAilment(changeAilment(poke2, AilmentE.POISON));

        poke1 = poke1.withStatusAilment(changeAilment(poke1, AilmentE.PARALYSIS));
        assertEquals(AilmentE.POISON, poke1.getStatusAilment().val());

        poke1 = poke1.withStatusAilment(changeAilment(poke1, AilmentE.BAD_POISON));
        assertEquals(AilmentE.POISON, poke1.getStatusAilment().val());

        poke1 = poke1.withStatusAilment(changeAilment(poke1, AilmentE.FREEZE));
        assertEquals(AilmentE.POISON, poke1.getStatusAilment().val());

        poke1 = poke1.withStatusAilment(changeAilment(poke1, AilmentE.SLEEP));
        assertEquals(AilmentE.POISON, poke1.getStatusAilment().val());

        poke1 = poke1.withStatusAilment(changeAilment(poke1, AilmentE.BURN));
        assertEquals(AilmentE.POISON, poke1.getStatusAilment().val());

        poke1 = poke1.withStatusAilment(changeAilment(poke1, AilmentE.FAINTING));
        assertEquals(AilmentE.FAINTING, poke1.getStatusAilment().val());

        poke2 = poke2.withStatusAilment(changeAilment(poke2, AilmentE.FINE));
        assertEquals(AilmentE.FINE, poke2.getStatusAilment().val());
    }

    @Test
    @DisplayName("もうどく状態が健康とひんし状態以外に上書きされないこと")
    public void test5() throws InterruptedException {
        PokeInfo poke1 = new PokeInfoI(BasePrm.BULBASAUR);
        PokeInfo poke2 = new PokeInfoI(BasePrm.CHARIZARD);
        poke1 = poke1.withStatusAilment(changeAilment(poke1, AilmentE.BAD_POISON));
        poke2 = poke2.withStatusAilment(changeAilment(poke2, AilmentE.BAD_POISON));

        poke1 = poke1.withStatusAilment(changeAilment(poke1, AilmentE.PARALYSIS));
        assertEquals(AilmentE.BAD_POISON, poke1.getStatusAilment().val());

        poke1 = poke1.withStatusAilment(changeAilment(poke1, AilmentE.POISON));
        assertEquals(AilmentE.BAD_POISON, poke1.getStatusAilment().val());

        poke1 = poke1.withStatusAilment(changeAilment(poke1, AilmentE.FREEZE));
        assertEquals(AilmentE.BAD_POISON, poke1.getStatusAilment().val());

        poke1 = poke1.withStatusAilment(changeAilment(poke1, AilmentE.SLEEP));
        assertEquals(AilmentE.BAD_POISON, poke1.getStatusAilment().val());

        poke1 = poke1.withStatusAilment(changeAilment(poke1, AilmentE.BURN));
        assertEquals(AilmentE.BAD_POISON, poke1.getStatusAilment().val());

        poke1 = poke1.withStatusAilment(changeAilment(poke1, AilmentE.FAINTING));
        assertEquals(AilmentE.FAINTING, poke1.getStatusAilment().val());

        poke2 = poke2.withStatusAilment(changeAilment(poke2, AilmentE.FINE));
        assertEquals(AilmentE.FINE, poke2.getStatusAilment().val());
    }

    @Test
    @DisplayName("ねむり状態が健康とひんし状態以外に上書きされないこと")
    public void test6() throws InterruptedException {
        PokeInfo poke1 = new PokeInfoI(BasePrm.BULBASAUR);
        PokeInfo poke2 = new PokeInfoI(BasePrm.CHARIZARD);
        poke1 = poke1.withStatusAilment(changeAilment(poke1, AilmentE.SLEEP));
        poke2 = poke2.withStatusAilment(changeAilment(poke2, AilmentE.SLEEP));

        poke1 = poke1.withStatusAilment(changeAilment(poke1, AilmentE.PARALYSIS));
        assertEquals(AilmentE.SLEEP, poke1.getStatusAilment().val());

        poke1 = poke1.withStatusAilment(changeAilment(poke1, AilmentE.POISON));
        assertEquals(AilmentE.SLEEP, poke1.getStatusAilment().val());

        poke1 = poke1.withStatusAilment(changeAilment(poke1, AilmentE.FREEZE));
        assertEquals(AilmentE.SLEEP, poke1.getStatusAilment().val());

        poke1 = poke1.withStatusAilment(changeAilment(poke1, AilmentE.BAD_POISON));
        assertEquals(AilmentE.SLEEP, poke1.getStatusAilment().val());

        poke1 = poke1.withStatusAilment(changeAilment(poke1, AilmentE.BURN));
        assertEquals(AilmentE.SLEEP, poke1.getStatusAilment().val());

        poke1 = poke1.withStatusAilment(changeAilment(poke1, AilmentE.FAINTING));
        assertEquals(AilmentE.FAINTING, poke1.getStatusAilment().val());

        poke2 = poke2.withStatusAilment(changeAilment(poke2, AilmentE.FINE));
        assertEquals(AilmentE.FINE, poke2.getStatusAilment().val());
    }

    @Test
    @DisplayName("ひんし状態が健康と状態以外に上書きされないこと")
    public void test7() throws InterruptedException {
        PokeInfo poke1 = new PokeInfoI(BasePrm.BULBASAUR);
        poke1 = poke1.withStatusAilment(changeAilment(poke1, AilmentE.FAINTING));

        poke1 = poke1.withStatusAilment(changeAilment(poke1, AilmentE.PARALYSIS));
        assertEquals(AilmentE.FAINTING, poke1.getStatusAilment().val());

        poke1 = poke1.withStatusAilment(changeAilment(poke1, AilmentE.POISON));
        assertEquals(AilmentE.FAINTING, poke1.getStatusAilment().val());

        poke1 = poke1.withStatusAilment(changeAilment(poke1, AilmentE.FREEZE));
        assertEquals(AilmentE.FAINTING, poke1.getStatusAilment().val());

        poke1 = poke1.withStatusAilment(changeAilment(poke1, AilmentE.BAD_POISON));
        assertEquals(AilmentE.FAINTING, poke1.getStatusAilment().val());

        poke1 = poke1.withStatusAilment(changeAilment(poke1, AilmentE.BURN));
        assertEquals(AilmentE.FAINTING, poke1.getStatusAilment().val());

        poke1 = poke1.withStatusAilment(changeAilment(poke1, AilmentE.FINE));
        assertEquals(AilmentE.FINE, poke1.getStatusAilment().val());
    }

    @Test
    @DisplayName("健康状態がすべての状態に上書きできること")
    public void test8() throws InterruptedException {
        PokeInfo poke1 = new PokeInfoI(BasePrm.BULBASAUR);
        PokeInfo poke2 = new PokeInfoI(BasePrm.BULBASAUR);
        PokeInfo poke3 = new PokeInfoI(BasePrm.BULBASAUR);
        PokeInfo poke4 = new PokeInfoI(BasePrm.BULBASAUR);
        PokeInfo poke5 = new PokeInfoI(BasePrm.BULBASAUR);
        PokeInfo poke6 = new PokeInfoI(BasePrm.BULBASAUR);

        poke1 = poke1.withStatusAilment(changeAilment(poke1, AilmentE.PARALYSIS));
        assertEquals(AilmentE.PARALYSIS, poke1.getStatusAilment().val());

        poke2 = poke2.withStatusAilment(changeAilment(poke2, AilmentE.POISON));
        assertEquals(AilmentE.POISON, poke2.getStatusAilment().val());

        poke3 = poke3.withStatusAilment(changeAilment(poke3, AilmentE.FREEZE));
        assertEquals(AilmentE.FREEZE, poke3.getStatusAilment().val());

        poke4 = poke4.withStatusAilment(changeAilment(poke4, AilmentE.BAD_POISON));
        assertEquals(AilmentE.BAD_POISON, poke4.getStatusAilment().val());

        poke5 = poke5.withStatusAilment(changeAilment(poke5, AilmentE.BURN));
        assertEquals(AilmentE.BURN, poke5.getStatusAilment().val());

        poke6 = poke6.withStatusAilment(changeAilment(poke6, AilmentE.FAINTING));
        assertEquals(AilmentE.FAINTING, poke6.getStatusAilment().val());
    }

    @Test
    @DisplayName("ねむり状態が1ターン経過後は継続され行動できず、5ターン経過後は解除され行動できること")
    public void test9() throws InterruptedException {
        PokeInfo myPokemon = new PokeInfoI(BasePrm.BULBASAUR);
        myPokemon = myPokemon.withStatusAilment(changeAilment(myPokemon, AilmentE.SLEEP));
        myPokemon = myPokemon.withStatusAilment(myPokemon.getStatusAilment().comeTurn(myPokemon.getBasePrm().pName()));
        // 1ターン経過後
        assertEquals(AilmentE.SLEEP, myPokemon.getStatusAilment().val());
        assertFalse(myPokemon.getStatusAilment().canMove(myPokemon.getBasePrm().pName()));
        myPokemon = myPokemon.withStatusAilment(myPokemon.getStatusAilment().comeTurn(myPokemon.getBasePrm().pName()));
        myPokemon = myPokemon.withStatusAilment(myPokemon.getStatusAilment().comeTurn(myPokemon.getBasePrm().pName()));
        myPokemon = myPokemon.withStatusAilment(myPokemon.getStatusAilment().comeTurn(myPokemon.getBasePrm().pName()));
        myPokemon = myPokemon.withStatusAilment(myPokemon.getStatusAilment().comeTurn(myPokemon.getBasePrm().pName()));
        // 5ターン経過後
        assertEquals(AilmentE.FINE, myPokemon.getStatusAilment().val());
        assertTrue(myPokemon.getStatusAilment().canMove(myPokemon.getBasePrm().pName()));
    }

    @Test
    @DisplayName("ねむり状態から0ターン経過後、新たにねむり状態になってもねむり解除ターンがリセットされないこと")
    public void test10() throws InterruptedException {
        PokeInfo poke = new PokeInfoI(BasePrm.BULBASAUR);
        poke = poke.withStatusAilment(changeAilment(poke, AilmentE.SLEEP));
        int beforeCount = poke.getStatusAilment().countRecoverySleep();
        poke = poke.withStatusAilment(changeAilment(poke, AilmentE.SLEEP));
        int afterCount = poke.getStatusAilment().countRecoverySleep();

        assertEquals(beforeCount, afterCount);
    }

    @Test
    @DisplayName("ねむり状態から1ターン経過後、新たにねむり状態になっても経過ターンがリセットされないこと")
    public void test11() throws InterruptedException {
        PokeInfo poke = new PokeInfoI(BasePrm.BULBASAUR);
        poke = poke.withStatusAilment(changeAilment(poke, AilmentE.SLEEP));
        poke = poke.withStatusAilment(poke.getStatusAilment().comeTurn(poke.getBasePrm().pName()));
        poke = poke.withStatusAilment(changeAilment(poke, AilmentE.SLEEP));

        assertEquals(1, poke.getStatusAilment().elapsedTurn());
    }

    @Test
    @DisplayName("やけど状態によるダメージ倍率が正しいこと")
    public void test12() throws InterruptedException {
        PokeInfo myPokemon1 = new PokeInfoI(BasePrm.CHARMANDER);
        PokeInfo myPokemon2 = new PokeInfoI(BasePrm.SQUIRTLE);
        myPokemon1 = myPokemon1.withStatusAilment(changeAilment(myPokemon1, AilmentE.BURN));

        assertEquals(0.5, myPokemon1.getStatusAilment().damageRateByBurn());
        assertEquals(1.0, myPokemon2.getStatusAilment().damageRateByBurn());
    }
}
