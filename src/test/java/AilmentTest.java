import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pokemon.BasePrm;
import pokemon.PokeInfo;
import statusAilment.AilmentEnum;

import static org.junit.jupiter.api.Assertions.*;
import static pokemon.PokeInfoI.initialize;
import static statusAilment.AilmentI.changeAilment;

public class AilmentTest {
    @Test
    @DisplayName("やけど状態が健康とひんし状態以外に上書きされないこと")
    public void test1() throws InterruptedException {
        PokeInfo poke1 = initialize(BasePrm.BLASTOISE);
        PokeInfo poke2 = initialize(BasePrm.CHARIZARD);
        poke1 = poke1.withAilment(changeAilment(poke1, AilmentEnum.BURN));
        poke2 = poke2.withAilment(changeAilment(poke2, AilmentEnum.BURN));

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentEnum.POISON));
        assertEquals(AilmentEnum.BURN, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentEnum.BAD_POISON));
        assertEquals(AilmentEnum.BURN, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentEnum.PARALYSIS));
        assertEquals(AilmentEnum.BURN, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentEnum.SLEEP));
        assertEquals(AilmentEnum.BURN, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentEnum.FREEZE));
        assertEquals(AilmentEnum.BURN, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentEnum.FAINTING));
        assertEquals(AilmentEnum.FAINTING, poke1.ailment().val());

        poke2 = poke2.withAilment(changeAilment(poke2, AilmentEnum.FINE));
        assertEquals(AilmentEnum.FINE, poke2.ailment().val());
    }

    @Test
    @DisplayName("こおり状態が健康とひんし状態以外に上書きされないこと")
    public void test2() throws InterruptedException {
        PokeInfo poke1 = initialize(BasePrm.BLASTOISE);
        PokeInfo poke2 = initialize(BasePrm.CHARIZARD);
        poke1 = poke1.withAilment(changeAilment(poke1, AilmentEnum.FREEZE));
        poke2 = poke2.withAilment(changeAilment(poke2, AilmentEnum.FREEZE));

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentEnum.POISON));
        assertEquals(AilmentEnum.FREEZE, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentEnum.BAD_POISON));
        assertEquals(AilmentEnum.FREEZE, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentEnum.PARALYSIS));
        assertEquals(AilmentEnum.FREEZE, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentEnum.SLEEP));
        assertEquals(AilmentEnum.FREEZE, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentEnum.BURN));
        assertEquals(AilmentEnum.FREEZE, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentEnum.FAINTING));
        assertEquals(AilmentEnum.FAINTING, poke1.ailment().val());

        poke2 = poke2.withAilment(changeAilment(poke2, AilmentEnum.FINE));
        assertEquals(AilmentEnum.FINE, poke2.ailment().val());
    }

    @Test
    @DisplayName("まひ状態が健康とひんし状態以外に上書きされないこと")
    public void test3() throws InterruptedException {
        PokeInfo poke1 = initialize(BasePrm.BLASTOISE);
        PokeInfo poke2 = initialize(BasePrm.CHARIZARD);
        poke1 = poke1.withAilment(changeAilment(poke1, AilmentEnum.PARALYSIS));
        poke2 = poke2.withAilment(changeAilment(poke2, AilmentEnum.PARALYSIS));

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentEnum.POISON));
        assertEquals(AilmentEnum.PARALYSIS, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentEnum.BAD_POISON));
        assertEquals(AilmentEnum.PARALYSIS, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentEnum.FREEZE));
        assertEquals(AilmentEnum.PARALYSIS, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentEnum.SLEEP));
        assertEquals(AilmentEnum.PARALYSIS, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentEnum.BURN));
        assertEquals(AilmentEnum.PARALYSIS, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentEnum.FAINTING));
        assertEquals(AilmentEnum.FAINTING, poke1.ailment().val());

        poke2 = poke2.withAilment(changeAilment(poke2, AilmentEnum.FINE));
        assertEquals(AilmentEnum.FINE, poke2.ailment().val());
    }

    @Test
    @DisplayName("どく状態が健康とひんし状態以外に上書きされないこと")
    public void test4() throws InterruptedException {
        PokeInfo poke1 = initialize(BasePrm.BLASTOISE);
        PokeInfo poke2 = initialize(BasePrm.CHARIZARD);
        poke1 = poke1.withAilment(changeAilment(poke1, AilmentEnum.POISON));
        poke2 = poke2.withAilment(changeAilment(poke2, AilmentEnum.POISON));

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentEnum.PARALYSIS));
        assertEquals(AilmentEnum.POISON, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentEnum.BAD_POISON));
        assertEquals(AilmentEnum.POISON, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentEnum.FREEZE));
        assertEquals(AilmentEnum.POISON, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentEnum.SLEEP));
        assertEquals(AilmentEnum.POISON, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentEnum.BURN));
        assertEquals(AilmentEnum.POISON, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentEnum.FAINTING));
        assertEquals(AilmentEnum.FAINTING, poke1.ailment().val());

        poke2 = poke2.withAilment(changeAilment(poke2, AilmentEnum.FINE));
        assertEquals(AilmentEnum.FINE, poke2.ailment().val());
    }

    @Test
    @DisplayName("もうどく状態が健康とひんし状態以外に上書きされないこと")
    public void test5() throws InterruptedException {
        PokeInfo poke1 = initialize(BasePrm.BLASTOISE);
        PokeInfo poke2 = initialize(BasePrm.CHARIZARD);
        poke1 = poke1.withAilment(changeAilment(poke1, AilmentEnum.BAD_POISON));
        poke2 = poke2.withAilment(changeAilment(poke2, AilmentEnum.BAD_POISON));

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentEnum.PARALYSIS));
        assertEquals(AilmentEnum.BAD_POISON, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentEnum.POISON));
        assertEquals(AilmentEnum.BAD_POISON, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentEnum.FREEZE));
        assertEquals(AilmentEnum.BAD_POISON, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentEnum.SLEEP));
        assertEquals(AilmentEnum.BAD_POISON, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentEnum.BURN));
        assertEquals(AilmentEnum.BAD_POISON, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentEnum.FAINTING));
        assertEquals(AilmentEnum.FAINTING, poke1.ailment().val());

        poke2 = poke2.withAilment(changeAilment(poke2, AilmentEnum.FINE));
        assertEquals(AilmentEnum.FINE, poke2.ailment().val());
    }

    @Test
    @DisplayName("ねむり状態が健康とひんし状態以外に上書きされないこと")
    public void test6() throws InterruptedException {
        PokeInfo poke1 = initialize(BasePrm.BLASTOISE);
        PokeInfo poke2 = initialize(BasePrm.CHARIZARD);
        poke1 = poke1.withAilment(changeAilment(poke1, AilmentEnum.SLEEP));
        poke2 = poke2.withAilment(changeAilment(poke2, AilmentEnum.SLEEP));

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentEnum.PARALYSIS));
        assertEquals(AilmentEnum.SLEEP, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentEnum.POISON));
        assertEquals(AilmentEnum.SLEEP, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentEnum.FREEZE));
        assertEquals(AilmentEnum.SLEEP, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentEnum.BAD_POISON));
        assertEquals(AilmentEnum.SLEEP, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentEnum.BURN));
        assertEquals(AilmentEnum.SLEEP, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentEnum.FAINTING));
        assertEquals(AilmentEnum.FAINTING, poke1.ailment().val());

        poke2 = poke2.withAilment(changeAilment(poke2, AilmentEnum.FINE));
        assertEquals(AilmentEnum.FINE, poke2.ailment().val());
    }

    @Test
    @DisplayName("ひんし状態が健康と状態以外に上書きされないこと")
    public void test7() throws InterruptedException {
        PokeInfo poke1 = initialize(BasePrm.BLASTOISE);
        poke1 = poke1.withAilment(changeAilment(poke1, AilmentEnum.FAINTING));

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentEnum.PARALYSIS));
        assertEquals(AilmentEnum.FAINTING, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentEnum.POISON));
        assertEquals(AilmentEnum.FAINTING, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentEnum.FREEZE));
        assertEquals(AilmentEnum.FAINTING, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentEnum.BAD_POISON));
        assertEquals(AilmentEnum.FAINTING, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentEnum.BURN));
        assertEquals(AilmentEnum.FAINTING, poke1.ailment().val());

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentEnum.FINE));
        assertEquals(AilmentEnum.FINE, poke1.ailment().val());
    }

    @Test
    @DisplayName("健康状態がすべての状態に上書きできること")
    public void test8() throws InterruptedException {
        PokeInfo poke1 = initialize(BasePrm.BLASTOISE);
        PokeInfo poke2 = initialize(BasePrm.BLASTOISE);
        PokeInfo poke3 = initialize(BasePrm.BLASTOISE);
        PokeInfo poke4 = initialize(BasePrm.BLASTOISE);
        PokeInfo poke5 = initialize(BasePrm.BLASTOISE);
        PokeInfo poke6 = initialize(BasePrm.BLASTOISE);

        poke1 = poke1.withAilment(changeAilment(poke1, AilmentEnum.PARALYSIS));
        assertEquals(AilmentEnum.PARALYSIS, poke1.ailment().val());

        poke2 = poke2.withAilment(changeAilment(poke2, AilmentEnum.POISON));
        assertEquals(AilmentEnum.POISON, poke2.ailment().val());

        poke3 = poke3.withAilment(changeAilment(poke3, AilmentEnum.FREEZE));
        assertEquals(AilmentEnum.FREEZE, poke3.ailment().val());

        poke4 = poke4.withAilment(changeAilment(poke4, AilmentEnum.BAD_POISON));
        assertEquals(AilmentEnum.BAD_POISON, poke4.ailment().val());

        poke5 = poke5.withAilment(changeAilment(poke5, AilmentEnum.BURN));
        assertEquals(AilmentEnum.BURN, poke5.ailment().val());

        poke6 = poke6.withAilment(changeAilment(poke6, AilmentEnum.FAINTING));
        assertEquals(AilmentEnum.FAINTING, poke6.ailment().val());
    }

    @Test
    @DisplayName("ねむり状態が1ターン経過後は継続され行動できず、5ターン経過後は解除され行動できること")
    public void test9() throws InterruptedException {
        PokeInfo myPokemon = initialize(BasePrm.BLASTOISE);
        myPokemon = myPokemon.withAilment(changeAilment(myPokemon, AilmentEnum.SLEEP));
        myPokemon = myPokemon.withAilment(myPokemon.ailment().comeTurn(myPokemon.basePrm().pName()));
        // 1ターン経過後
        assertEquals(AilmentEnum.SLEEP, myPokemon.ailment().val());
        assertFalse(myPokemon.ailment().canMove(myPokemon.basePrm().pName()));
        myPokemon = myPokemon.withAilment(myPokemon.ailment().comeTurn(myPokemon.basePrm().pName()));
        myPokemon = myPokemon.withAilment(myPokemon.ailment().comeTurn(myPokemon.basePrm().pName()));
        myPokemon = myPokemon.withAilment(myPokemon.ailment().comeTurn(myPokemon.basePrm().pName()));
        myPokemon = myPokemon.withAilment(myPokemon.ailment().comeTurn(myPokemon.basePrm().pName()));
        // 5ターン経過後
        assertEquals(AilmentEnum.FINE, myPokemon.ailment().val());
        assertTrue(myPokemon.ailment().canMove(myPokemon.basePrm().pName()));
    }

    @Test
    @DisplayName("ねむり状態から0ターン経過後、新たにねむり状態になってもねむり解除ターンがリセットされないこと")
    public void test10() throws InterruptedException {
        PokeInfo poke = initialize(BasePrm.BLASTOISE);
        poke = poke.withAilment(changeAilment(poke, AilmentEnum.SLEEP));
        int beforeCount = poke.ailment().countRecoverySleep();
        poke = poke.withAilment(changeAilment(poke, AilmentEnum.SLEEP));
        int afterCount = poke.ailment().countRecoverySleep();

        assertEquals(beforeCount, afterCount);
    }

    @Test
    @DisplayName("ねむり状態から1ターン経過後、新たにねむり状態になっても経過ターンがリセットされないこと")
    public void test11() throws InterruptedException {
        PokeInfo poke = initialize(BasePrm.BLASTOISE);
        poke = poke.withAilment(changeAilment(poke, AilmentEnum.SLEEP));
        poke = poke.withAilment(poke.ailment().comeTurn(poke.basePrm().pName()));
        poke = poke.withAilment(changeAilment(poke, AilmentEnum.SLEEP));

        assertEquals(1, poke.ailment().elapsedTurn());
    }

    @Test
    @DisplayName("やけど状態によるダメージ倍率が正しいこと")
    public void test12() throws InterruptedException {
        PokeInfo myPokemon1 = initialize(BasePrm.BLASTOISE);
        PokeInfo myPokemon2 = initialize(BasePrm.CHANSEY);
        myPokemon1 = myPokemon1.withAilment(changeAilment(myPokemon1, AilmentEnum.BURN));

        assertEquals(0.5, myPokemon1.ailment().dmgRateByBurn());
        assertEquals(1.0, myPokemon2.ailment().dmgRateByBurn());
    }

    @Test
    @DisplayName("こおりタイプはこおり状態にならないこと")
    public void test13() throws InterruptedException {
        PokeInfo icePk = initialize(BasePrm.ARTICUNO);
        PokeInfo elePk = initialize(BasePrm.ZAPDOS);
        icePk = icePk.withAilment(changeAilment(icePk, AilmentEnum.FREEZE));
        elePk = elePk.withAilment(changeAilment(elePk, AilmentEnum.FREEZE));

        assertEquals(AilmentEnum.FINE, icePk.ailment().val());
        assertEquals(AilmentEnum.FREEZE, elePk.ailment().val());
    }

    @Test
    @DisplayName("ほのおタイプはやけど状態にならないこと")
    public void test14() throws InterruptedException {
        PokeInfo firePk = initialize(BasePrm.CHARIZARD);
        PokeInfo elePk = initialize(BasePrm.ZAPDOS);
        firePk = firePk.withAilment(changeAilment(firePk, AilmentEnum.BURN));
        elePk = elePk.withAilment(changeAilment(elePk, AilmentEnum.BURN));

        assertEquals(AilmentEnum.FINE, firePk.ailment().val());
        assertEquals(AilmentEnum.BURN, elePk.ailment().val());
    }

    @Test
    @DisplayName("はがね、どくタイプはどく状態にならないこと")
    public void test15() throws InterruptedException {
        PokeInfo firePk = initialize(BasePrm.CHARIZARD);
        PokeInfo posPk = initialize(BasePrm.MUK);
        PokeInfo stlPk = initialize(BasePrm.MAGNRTON);
        firePk = firePk.withAilment(changeAilment(firePk, AilmentEnum.POISON));
        posPk = posPk.withAilment(changeAilment(posPk, AilmentEnum.POISON));
        stlPk = stlPk.withAilment(changeAilment(stlPk, AilmentEnum.POISON));

        assertEquals(AilmentEnum.POISON, firePk.ailment().val());
        assertEquals(AilmentEnum.FINE, posPk.ailment().val());
        assertEquals(AilmentEnum.FINE, stlPk.ailment().val());
    }

    @Test
    @DisplayName("はがね、どくタイプはもうどく状態にならないこと")
    public void test16() throws InterruptedException {
        PokeInfo firePk = initialize(BasePrm.CHARIZARD);
        PokeInfo posPk = initialize(BasePrm.MUK);
        PokeInfo stlPk = initialize(BasePrm.MAGNRTON);
        firePk = firePk.withAilment(changeAilment(firePk, AilmentEnum.BAD_POISON));
        posPk = posPk.withAilment(changeAilment(posPk, AilmentEnum.BAD_POISON));
        stlPk = stlPk.withAilment(changeAilment(stlPk, AilmentEnum.BAD_POISON));

        assertEquals(AilmentEnum.BAD_POISON, firePk.ailment().val());
        assertEquals(AilmentEnum.FINE, posPk.ailment().val());
        assertEquals(AilmentEnum.FINE, stlPk.ailment().val());
    }
}
