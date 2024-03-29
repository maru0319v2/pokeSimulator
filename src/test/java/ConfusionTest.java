import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pokemon.BasePrm;
import pokemon.PokeInfo;

import static org.junit.jupiter.api.Assertions.*;
import static pokemon.PokeInfoI.init;
import static pokemonStatus.impl.ConfusionI.beConfusion;

public class ConfusionTest {
    @Test
    @DisplayName("初期状態は混乱状態ではないこと")
    public void test1() {
        PokeInfo pk = init(BasePrm.CHARIZARD);
        assertFalse(pk.confusion().val());
    }

    @Test
    @DisplayName("混乱状態にしたとき混乱状態が維持されていること")
    public void test2() {
        PokeInfo pk = init(BasePrm.CHARIZARD);
        pk = pk.updateConfusion(beConfusion(pk));
        assertTrue(pk.confusion().val());
    }

    @Test
    @DisplayName("混乱状態にしたときcountRecoveryが2以上、elapsedTurnが0であること")
    public void test3() {
        PokeInfo pk = init(BasePrm.CHARIZARD);
        pk = pk.updateConfusion(beConfusion(pk));
        assertTrue(pk.confusion().countRecovery() >= 2);
        assertEquals(0, pk.confusion().elapsedTurn());
    }

    @Test
    @DisplayName("1ターン経過でelapsedTurnが1増えること")
    public void test4() {
        PokeInfo pk = init(BasePrm.CHARIZARD);
        pk = pk.updateConfusion(beConfusion(pk));
        pk = pk.updateConfusion(pk.confusion().elapseTurn(pk.basePrm().pName()));
        assertEquals(1, pk.confusion().elapsedTurn());
    }

    @Test
    @DisplayName("5ターン経過後に混乱状態が解除されていること")
    public void test5() {
        PokeInfo pk = init(BasePrm.CHARIZARD);
        pk = pk.updateConfusion(beConfusion(pk));

        pk = pk.updateConfusion(pk.confusion().elapseTurn(pk.basePrm().pName()));
        pk = pk.updateConfusion(pk.confusion().elapseTurn(pk.basePrm().pName()));
        pk = pk.updateConfusion(pk.confusion().elapseTurn(pk.basePrm().pName()));
        pk = pk.updateConfusion(pk.confusion().elapseTurn(pk.basePrm().pName()));
        pk = pk.updateConfusion(pk.confusion().elapseTurn(pk.basePrm().pName()));

        assertFalse(pk.confusion().val());
    }

    @Test
    @DisplayName("すでに混乱状態になっている場合は、さらに混乱にならないこと")
    public void test6() {
        PokeInfo pk = init(BasePrm.CHARIZARD);
        pk = pk.updateConfusion(beConfusion(pk));
        pk = pk.updateConfusion(pk.confusion().elapseTurn(pk.basePrm().pName()));
        pk = pk.updateConfusion(beConfusion(pk));

        assertEquals(1, pk.confusion().elapsedTurn());
    }
}
