import Enum.Gender;
import Enum.Nature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pokemon.BasePrm;
import pokemon.PokeInfo;
import pokemon.PokeInfoI;
import pokemonStatus.impl.EffortValueI;
import pokemonStatus.impl.IndividualValueI;
import pokemonStatus.impl.LevelI;
import statusAilment.AilmentEnum;

import java.util.List;

import static Enum.Item.NONE;
import static org.junit.jupiter.api.Assertions.*;
import static pokemon.BasePrm.CHARIZARD;
import static pokemon.PokeInfoI.init;
import static statusAilment.AilmentI.changeAilment;

public class AilmentTest {
    @Test
    @DisplayName("やけど状態が健康とひんし状態と自己ねむり以外に上書きされないこと")
    public void test1() {
        PokeInfo poke1 = init(BasePrm.BLASTOISE);
        PokeInfo poke2 = init(CHARIZARD);
        PokeInfo poke3 = init(BasePrm.AKAKAZAM);
        poke1 = poke1.updateAilment(changeAilment(poke1, AilmentEnum.BURN));
        poke2 = poke2.updateAilment(changeAilment(poke2, AilmentEnum.BURN));
        poke3 = poke3.updateAilment(changeAilment(poke3, AilmentEnum.BURN));

        poke1 = poke1.updateAilment(changeAilment(poke1, AilmentEnum.POISON));
        assertEquals(AilmentEnum.BURN, poke1.ailment().val());

        poke1 = poke1.updateAilment(changeAilment(poke1, AilmentEnum.BAD_POISON));
        assertEquals(AilmentEnum.BURN, poke1.ailment().val());

        poke1 = poke1.updateAilment(changeAilment(poke1, AilmentEnum.PARALYSIS));
        assertEquals(AilmentEnum.BURN, poke1.ailment().val());

        poke1 = poke1.updateAilment(changeAilment(poke1, AilmentEnum.SLEEP));
        assertEquals(AilmentEnum.BURN, poke1.ailment().val());

        poke1 = poke1.updateAilment(changeAilment(poke1, AilmentEnum.FREEZE));
        assertEquals(AilmentEnum.BURN, poke1.ailment().val());

        poke1 = poke1.updateAilment(changeAilment(poke1, AilmentEnum.FAINTING));
        assertEquals(AilmentEnum.FAINTING, poke1.ailment().val());

        poke2 = poke2.updateAilment(changeAilment(poke2, AilmentEnum.FINE));
        assertEquals(AilmentEnum.FINE, poke2.ailment().val());

        poke3 = poke3.updateAilment(changeAilment(poke3, AilmentEnum.SELF_SLEEP));
        assertEquals(AilmentEnum.SELF_SLEEP, poke3.ailment().val());
    }

    @Test
    @DisplayName("こおり状態が健康とひんし状態と自己ねむり以外に上書きされないこと")
    public void test2() {
        PokeInfo poke1 = init(BasePrm.BLASTOISE);
        PokeInfo poke2 = init(CHARIZARD);
        PokeInfo poke3 = init(BasePrm.AKAKAZAM);
        poke1 = poke1.updateAilment(changeAilment(poke1, AilmentEnum.FREEZE));
        poke2 = poke2.updateAilment(changeAilment(poke2, AilmentEnum.FREEZE));
        poke3 = poke3.updateAilment(changeAilment(poke3, AilmentEnum.FREEZE));

        poke1 = poke1.updateAilment(changeAilment(poke1, AilmentEnum.POISON));
        assertEquals(AilmentEnum.FREEZE, poke1.ailment().val());

        poke1 = poke1.updateAilment(changeAilment(poke1, AilmentEnum.BAD_POISON));
        assertEquals(AilmentEnum.FREEZE, poke1.ailment().val());

        poke1 = poke1.updateAilment(changeAilment(poke1, AilmentEnum.PARALYSIS));
        assertEquals(AilmentEnum.FREEZE, poke1.ailment().val());

        poke1 = poke1.updateAilment(changeAilment(poke1, AilmentEnum.SLEEP));
        assertEquals(AilmentEnum.FREEZE, poke1.ailment().val());

        poke1 = poke1.updateAilment(changeAilment(poke1, AilmentEnum.BURN));
        assertEquals(AilmentEnum.FREEZE, poke1.ailment().val());

        poke1 = poke1.updateAilment(changeAilment(poke1, AilmentEnum.FAINTING));
        assertEquals(AilmentEnum.FAINTING, poke1.ailment().val());

        poke2 = poke2.updateAilment(changeAilment(poke2, AilmentEnum.FINE));
        assertEquals(AilmentEnum.FINE, poke2.ailment().val());

        poke3 = poke3.updateAilment(changeAilment(poke3, AilmentEnum.SELF_SLEEP));
        assertEquals(AilmentEnum.SELF_SLEEP, poke3.ailment().val());
    }

    @Test
    @DisplayName("まひ状態が健康とひんし状態と自己ねむり以外に上書きされないこと")
    public void test3() {
        PokeInfo poke1 = init(BasePrm.BLASTOISE);
        PokeInfo poke2 = init(CHARIZARD);
        PokeInfo poke3 = init(BasePrm.AKAKAZAM);
        poke1 = poke1.updateAilment(changeAilment(poke1, AilmentEnum.PARALYSIS));
        poke2 = poke2.updateAilment(changeAilment(poke2, AilmentEnum.PARALYSIS));
        poke3 = poke3.updateAilment(changeAilment(poke3, AilmentEnum.PARALYSIS));

        poke1 = poke1.updateAilment(changeAilment(poke1, AilmentEnum.POISON));
        assertEquals(AilmentEnum.PARALYSIS, poke1.ailment().val());

        poke1 = poke1.updateAilment(changeAilment(poke1, AilmentEnum.BAD_POISON));
        assertEquals(AilmentEnum.PARALYSIS, poke1.ailment().val());

        poke1 = poke1.updateAilment(changeAilment(poke1, AilmentEnum.FREEZE));
        assertEquals(AilmentEnum.PARALYSIS, poke1.ailment().val());

        poke1 = poke1.updateAilment(changeAilment(poke1, AilmentEnum.SLEEP));
        assertEquals(AilmentEnum.PARALYSIS, poke1.ailment().val());

        poke1 = poke1.updateAilment(changeAilment(poke1, AilmentEnum.BURN));
        assertEquals(AilmentEnum.PARALYSIS, poke1.ailment().val());

        poke1 = poke1.updateAilment(changeAilment(poke1, AilmentEnum.FAINTING));
        assertEquals(AilmentEnum.FAINTING, poke1.ailment().val());

        poke2 = poke2.updateAilment(changeAilment(poke2, AilmentEnum.FINE));
        assertEquals(AilmentEnum.FINE, poke2.ailment().val());

        poke3 = poke3.updateAilment(changeAilment(poke3, AilmentEnum.SELF_SLEEP));
        assertEquals(AilmentEnum.SELF_SLEEP, poke3.ailment().val());
    }

    @Test
    @DisplayName("どく状態が健康とひんし状態と自己ねむり以外に上書きされないこと")
    public void test4() {
        PokeInfo poke1 = init(BasePrm.BLASTOISE);
        PokeInfo poke2 = init(CHARIZARD);
        PokeInfo poke3 = init(BasePrm.AKAKAZAM);
        poke1 = poke1.updateAilment(changeAilment(poke1, AilmentEnum.POISON));
        poke2 = poke2.updateAilment(changeAilment(poke2, AilmentEnum.POISON));
        poke3 = poke3.updateAilment(changeAilment(poke3, AilmentEnum.POISON));

        poke1 = poke1.updateAilment(changeAilment(poke1, AilmentEnum.PARALYSIS));
        assertEquals(AilmentEnum.POISON, poke1.ailment().val());

        poke1 = poke1.updateAilment(changeAilment(poke1, AilmentEnum.BAD_POISON));
        assertEquals(AilmentEnum.POISON, poke1.ailment().val());

        poke1 = poke1.updateAilment(changeAilment(poke1, AilmentEnum.FREEZE));
        assertEquals(AilmentEnum.POISON, poke1.ailment().val());

        poke1 = poke1.updateAilment(changeAilment(poke1, AilmentEnum.SLEEP));
        assertEquals(AilmentEnum.POISON, poke1.ailment().val());

        poke1 = poke1.updateAilment(changeAilment(poke1, AilmentEnum.BURN));
        assertEquals(AilmentEnum.POISON, poke1.ailment().val());

        poke1 = poke1.updateAilment(changeAilment(poke1, AilmentEnum.FAINTING));
        assertEquals(AilmentEnum.FAINTING, poke1.ailment().val());

        poke2 = poke2.updateAilment(changeAilment(poke2, AilmentEnum.FINE));
        assertEquals(AilmentEnum.FINE, poke2.ailment().val());

        poke3 = poke3.updateAilment(changeAilment(poke3, AilmentEnum.SELF_SLEEP));
        assertEquals(AilmentEnum.SELF_SLEEP, poke3.ailment().val());
    }

    @Test
    @DisplayName("もうどく状態が健康とひんし状態と自己ねむり以外に上書きされないこと")
    public void test5() {
        PokeInfo poke1 = init(BasePrm.BLASTOISE);
        PokeInfo poke2 = init(CHARIZARD);
        PokeInfo poke3 = init(BasePrm.AKAKAZAM);
        poke1 = poke1.updateAilment(changeAilment(poke1, AilmentEnum.BAD_POISON));
        poke2 = poke2.updateAilment(changeAilment(poke2, AilmentEnum.BAD_POISON));
        poke3 = poke3.updateAilment(changeAilment(poke3, AilmentEnum.BAD_POISON));

        poke1 = poke1.updateAilment(changeAilment(poke1, AilmentEnum.PARALYSIS));
        assertEquals(AilmentEnum.BAD_POISON, poke1.ailment().val());

        poke1 = poke1.updateAilment(changeAilment(poke1, AilmentEnum.POISON));
        assertEquals(AilmentEnum.BAD_POISON, poke1.ailment().val());

        poke1 = poke1.updateAilment(changeAilment(poke1, AilmentEnum.FREEZE));
        assertEquals(AilmentEnum.BAD_POISON, poke1.ailment().val());

        poke1 = poke1.updateAilment(changeAilment(poke1, AilmentEnum.SLEEP));
        assertEquals(AilmentEnum.BAD_POISON, poke1.ailment().val());

        poke1 = poke1.updateAilment(changeAilment(poke1, AilmentEnum.BURN));
        assertEquals(AilmentEnum.BAD_POISON, poke1.ailment().val());

        poke1 = poke1.updateAilment(changeAilment(poke1, AilmentEnum.FAINTING));
        assertEquals(AilmentEnum.FAINTING, poke1.ailment().val());

        poke2 = poke2.updateAilment(changeAilment(poke2, AilmentEnum.FINE));
        assertEquals(AilmentEnum.FINE, poke2.ailment().val());

        poke3 = poke3.updateAilment(changeAilment(poke3, AilmentEnum.SELF_SLEEP));
        assertEquals(AilmentEnum.SELF_SLEEP, poke3.ailment().val());
    }

    @Test
    @DisplayName("ねむり状態が健康とひんし状態以外に上書きされないこと")
    public void test6() {
        PokeInfo poke1 = init(BasePrm.BLASTOISE);
        PokeInfo poke2 = init(CHARIZARD);
        poke1 = poke1.updateAilment(changeAilment(poke1, AilmentEnum.SLEEP));
        poke2 = poke2.updateAilment(changeAilment(poke2, AilmentEnum.SLEEP));

        poke1 = poke1.updateAilment(changeAilment(poke1, AilmentEnum.PARALYSIS));
        assertEquals(AilmentEnum.SLEEP, poke1.ailment().val());

        poke1 = poke1.updateAilment(changeAilment(poke1, AilmentEnum.POISON));
        assertEquals(AilmentEnum.SLEEP, poke1.ailment().val());

        poke1 = poke1.updateAilment(changeAilment(poke1, AilmentEnum.FREEZE));
        assertEquals(AilmentEnum.SLEEP, poke1.ailment().val());

        poke1 = poke1.updateAilment(changeAilment(poke1, AilmentEnum.BAD_POISON));
        assertEquals(AilmentEnum.SLEEP, poke1.ailment().val());

        poke1 = poke1.updateAilment(changeAilment(poke1, AilmentEnum.BURN));
        assertEquals(AilmentEnum.SLEEP, poke1.ailment().val());

        poke1 = poke1.updateAilment(changeAilment(poke1, AilmentEnum.SELF_SLEEP));
        assertEquals(AilmentEnum.SLEEP, poke1.ailment().val());

        poke1 = poke1.updateAilment(changeAilment(poke1, AilmentEnum.FAINTING));
        assertEquals(AilmentEnum.FAINTING, poke1.ailment().val());

        poke2 = poke2.updateAilment(changeAilment(poke2, AilmentEnum.FINE));
        assertEquals(AilmentEnum.FINE, poke2.ailment().val());
    }

    @Test
    @DisplayName("ひんし状態が健康と状態以外に上書きされないこと")
    public void test7() {
        PokeInfo poke1 = init(BasePrm.BLASTOISE);
        poke1 = poke1.updateAilment(changeAilment(poke1, AilmentEnum.FAINTING));

        poke1 = poke1.updateAilment(changeAilment(poke1, AilmentEnum.PARALYSIS));
        assertEquals(AilmentEnum.FAINTING, poke1.ailment().val());

        poke1 = poke1.updateAilment(changeAilment(poke1, AilmentEnum.POISON));
        assertEquals(AilmentEnum.FAINTING, poke1.ailment().val());

        poke1 = poke1.updateAilment(changeAilment(poke1, AilmentEnum.FREEZE));
        assertEquals(AilmentEnum.FAINTING, poke1.ailment().val());

        poke1 = poke1.updateAilment(changeAilment(poke1, AilmentEnum.BAD_POISON));
        assertEquals(AilmentEnum.FAINTING, poke1.ailment().val());

        poke1 = poke1.updateAilment(changeAilment(poke1, AilmentEnum.SELF_SLEEP));
        assertEquals(AilmentEnum.FAINTING, poke1.ailment().val());

        poke1 = poke1.updateAilment(changeAilment(poke1, AilmentEnum.BURN));
        assertEquals(AilmentEnum.FAINTING, poke1.ailment().val());

        poke1 = poke1.updateAilment(changeAilment(poke1, AilmentEnum.FINE));
        assertEquals(AilmentEnum.FINE, poke1.ailment().val());
    }

    @Test
    @DisplayName("健康状態がすべての状態に上書きできること")
    public void test8() {
        PokeInfo poke1 = init(BasePrm.BLASTOISE);
        PokeInfo poke2 = init(BasePrm.BLASTOISE);
        PokeInfo poke3 = init(BasePrm.BLASTOISE);
        PokeInfo poke4 = init(BasePrm.BLASTOISE);
        PokeInfo poke5 = init(BasePrm.BLASTOISE);
        PokeInfo poke6 = init(BasePrm.BLASTOISE);
        PokeInfo poke7 = init(BasePrm.BLASTOISE);

        poke1 = poke1.updateAilment(changeAilment(poke1, AilmentEnum.PARALYSIS));
        assertEquals(AilmentEnum.PARALYSIS, poke1.ailment().val());

        poke2 = poke2.updateAilment(changeAilment(poke2, AilmentEnum.POISON));
        assertEquals(AilmentEnum.POISON, poke2.ailment().val());

        poke3 = poke3.updateAilment(changeAilment(poke3, AilmentEnum.FREEZE));
        assertEquals(AilmentEnum.FREEZE, poke3.ailment().val());

        poke4 = poke4.updateAilment(changeAilment(poke4, AilmentEnum.BAD_POISON));
        assertEquals(AilmentEnum.BAD_POISON, poke4.ailment().val());

        poke5 = poke5.updateAilment(changeAilment(poke5, AilmentEnum.BURN));
        assertEquals(AilmentEnum.BURN, poke5.ailment().val());

        poke6 = poke6.updateAilment(changeAilment(poke6, AilmentEnum.FAINTING));
        assertEquals(AilmentEnum.FAINTING, poke6.ailment().val());

        poke7 = poke7.updateAilment(changeAilment(poke7, AilmentEnum.SELF_SLEEP));
        assertEquals(AilmentEnum.SELF_SLEEP, poke7.ailment().val());
    }

    @Test
    @DisplayName("ねむり状態が1ターン経過後は継続され行動できず、5ターン経過後は解除され行動できること")
    public void test9() {
        PokeInfo myPokemon = init(BasePrm.BLASTOISE);
        myPokemon = myPokemon.updateAilment(changeAilment(myPokemon, AilmentEnum.SLEEP));
        myPokemon = myPokemon.updateAilment(myPokemon.ailment().comeTurn(myPokemon.basePrm().pName()));
        // 1ターン経過後
        assertEquals(AilmentEnum.SLEEP, myPokemon.ailment().val());
        assertFalse(myPokemon.ailment().canMove(myPokemon.basePrm().pName()));
        myPokemon = myPokemon.updateAilment(myPokemon.ailment().comeTurn(myPokemon.basePrm().pName()));
        myPokemon = myPokemon.updateAilment(myPokemon.ailment().comeTurn(myPokemon.basePrm().pName()));
        myPokemon = myPokemon.updateAilment(myPokemon.ailment().comeTurn(myPokemon.basePrm().pName()));
        myPokemon = myPokemon.updateAilment(myPokemon.ailment().comeTurn(myPokemon.basePrm().pName()));
        // 5ターン経過後
        assertEquals(AilmentEnum.FINE, myPokemon.ailment().val());
        assertTrue(myPokemon.ailment().canMove(myPokemon.basePrm().pName()));
    }

    @Test
    @DisplayName("自己ねむり状態で1ターン経過後は継続され行動できず、3ターン経過後は解除され行動できること")
    public void test10() {
        PokeInfo myPokemon = init(BasePrm.BLASTOISE);
        myPokemon = myPokemon.updateAilment(changeAilment(myPokemon, AilmentEnum.SELF_SLEEP));
        myPokemon = myPokemon.updateAilment(myPokemon.ailment().comeTurn(myPokemon.basePrm().pName()));
        // 1ターン経過後
        assertEquals(AilmentEnum.SELF_SLEEP, myPokemon.ailment().val());
        assertFalse(myPokemon.ailment().canMove(myPokemon.basePrm().pName()));
        myPokemon = myPokemon.updateAilment(myPokemon.ailment().comeTurn(myPokemon.basePrm().pName()));
        myPokemon = myPokemon.updateAilment(myPokemon.ailment().comeTurn(myPokemon.basePrm().pName()));
        // 2ターン経過後
        assertEquals(AilmentEnum.FINE, myPokemon.ailment().val());
        assertTrue(myPokemon.ailment().canMove(myPokemon.basePrm().pName()));
    }

    @Test
    @DisplayName("ねむり状態から0ターン経過後、新たにねむり状態になってもねむり解除ターンがリセットされないこと")
    public void test11() {
        PokeInfo poke = init(BasePrm.BLASTOISE);
        poke = poke.updateAilment(changeAilment(poke, AilmentEnum.SLEEP));
        int beforeCount = poke.ailment().countRecoverySleep();
        poke = poke.updateAilment(changeAilment(poke, AilmentEnum.SLEEP));
        int afterCount = poke.ailment().countRecoverySleep();

        assertEquals(beforeCount, afterCount);
    }

    @Test
    @DisplayName("自己ねむり状態から0ターン経過後、新たにねむり状態になってもねむり解除ターンがリセットされないこと")
    public void test12() {
        PokeInfo poke = init(BasePrm.BLASTOISE);
        poke = poke.updateAilment(changeAilment(poke, AilmentEnum.SELF_SLEEP));
        int beforeCount = poke.ailment().countRecoverySleep();
        poke = poke.updateAilment(changeAilment(poke, AilmentEnum.SLEEP));
        int afterCount = poke.ailment().countRecoverySleep();

        assertEquals(beforeCount, afterCount);
    }

    @Test
    @DisplayName("ねむり状態から1ターン経過後、新たにねむり状態になっても経過ターンがリセットされないこと")
    public void test13() {
        PokeInfo poke = init(BasePrm.BLASTOISE);
        poke = poke.updateAilment(changeAilment(poke, AilmentEnum.SLEEP));
        poke = poke.updateAilment(poke.ailment().comeTurn(poke.basePrm().pName()));
        poke = poke.updateAilment(changeAilment(poke, AilmentEnum.SLEEP));

        assertEquals(1, poke.ailment().elapsedTurn());
    }

    @Test
    @DisplayName("自己ねむり状態から1ターン経過後、新たにねむり状態になっても経過ターンがリセットされないこと")
    public void test14() {
        PokeInfo poke = init(BasePrm.BLASTOISE);
        poke = poke.updateAilment(changeAilment(poke, AilmentEnum.SELF_SLEEP));
        poke = poke.updateAilment(poke.ailment().comeTurn(poke.basePrm().pName()));
        poke = poke.updateAilment(changeAilment(poke, AilmentEnum.SLEEP));

        assertEquals(1, poke.ailment().elapsedTurn());
    }

    @Test
    @DisplayName("やけど状態によるダメージ倍率が正しいこと")
    public void test15() {
        PokeInfo myPokemon1 = init(BasePrm.BLASTOISE);
        PokeInfo myPokemon2 = init(BasePrm.CHANSEY);
        myPokemon1 = myPokemon1.updateAilment(changeAilment(myPokemon1, AilmentEnum.BURN));

        assertEquals(0.5, myPokemon1.ailment().dmgRateByBurn());
        assertEquals(1.0, myPokemon2.ailment().dmgRateByBurn());
    }

    @Test
    @DisplayName("こおりタイプはこおり状態にならないこと")
    public void test16() {
        PokeInfo icePk = init(BasePrm.ARTICUNO);
        PokeInfo elePk = init(BasePrm.ZAPDOS);
        icePk = icePk.updateAilment(changeAilment(icePk, AilmentEnum.FREEZE));
        elePk = elePk.updateAilment(changeAilment(elePk, AilmentEnum.FREEZE));

        assertEquals(AilmentEnum.FINE, icePk.ailment().val());
        assertEquals(AilmentEnum.FREEZE, elePk.ailment().val());
    }

    @Test
    @DisplayName("ほのおタイプはやけど状態にならないこと")
    public void test17() {
        PokeInfo firePk = init(CHARIZARD);
        PokeInfo elePk = init(BasePrm.ZAPDOS);
        firePk = firePk.updateAilment(changeAilment(firePk, AilmentEnum.BURN));
        elePk = elePk.updateAilment(changeAilment(elePk, AilmentEnum.BURN));

        assertEquals(AilmentEnum.FINE, firePk.ailment().val());
        assertEquals(AilmentEnum.BURN, elePk.ailment().val());
    }

    @Test
    @DisplayName("はがね、どくタイプはどく状態にならないこと")
    public void test18() {
        PokeInfo firePk = init(CHARIZARD);
        PokeInfo posPk = init(BasePrm.MUK);
        PokeInfo stlPk = init(BasePrm.MAGNRTON);
        firePk = firePk.updateAilment(changeAilment(firePk, AilmentEnum.POISON));
        posPk = posPk.updateAilment(changeAilment(posPk, AilmentEnum.POISON));
        stlPk = stlPk.updateAilment(changeAilment(stlPk, AilmentEnum.POISON));

        assertEquals(AilmentEnum.POISON, firePk.ailment().val());
        assertEquals(AilmentEnum.FINE, posPk.ailment().val());
        assertEquals(AilmentEnum.FINE, stlPk.ailment().val());
    }

    @Test
    @DisplayName("はがね、どくタイプはもうどく状態にならないこと")
    public void test19() {
        PokeInfo firePk = init(CHARIZARD);
        PokeInfo posPk = init(BasePrm.MUK);
        PokeInfo stlPk = init(BasePrm.MAGNRTON);
        firePk = firePk.updateAilment(changeAilment(firePk, AilmentEnum.BAD_POISON));
        posPk = posPk.updateAilment(changeAilment(posPk, AilmentEnum.BAD_POISON));
        stlPk = stlPk.updateAilment(changeAilment(stlPk, AilmentEnum.BAD_POISON));

        assertEquals(AilmentEnum.BAD_POISON, firePk.ailment().val());
        assertEquals(AilmentEnum.FINE, posPk.ailment().val());
        assertEquals(AilmentEnum.FINE, stlPk.ailment().val());
    }

    @Test
    @DisplayName("もうどくによるダメージがターン経過ごとに増加すること")
    public void test20() {
        PokeInfo poke = new PokeInfoI(
                CHARIZARD,
                Gender.init(),
                Nature.init(),
                new IndividualValueI(0, 0, 0, 0, 0, 0),
                EffortValueI.init(),
                new LevelI(100),
                List.of(),
                NONE
        );
        poke = poke.updateAilment(changeAilment(poke, AilmentEnum.BAD_POISON));

        // 現在HP満タンの確認
        assertEquals(266, poke.currentHP().val());
        // 現在HPが1/16減少している確認
        poke = poke.ailment().slipDmgByAilment(poke);
        assertEquals(250, poke.currentHP().val());
        // 1ターン経過する
        poke = poke.updateAilment(poke.ailment().comeTurn("リザードン"));
        // 現在HPが2/16減少している確認
        poke = poke.ailment().slipDmgByAilment(poke);
        assertEquals(218, poke.currentHP().val());
    }
}
