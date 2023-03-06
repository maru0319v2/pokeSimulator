import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pokemon.BasePrm;
import pokemon.PokeInfo;
import pokemon.PokeInfoI;
import statusAilment.AilmentE;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PokemonInfoImplTest {
    @Test
    @DisplayName("HP0でひんし状態になり、回復すると状態なしになること")
    public void testBattleLogic1() throws InterruptedException {
        PokeInfo myPoke = new PokeInfoI(BasePrm.CHARMANDER);
        myPoke = myPoke.damagePoke(200);
        assertEquals(AilmentE.FAINTING, myPoke.getStatusAilment().val());

        myPoke = myPoke.recoveryHitPoint(50);
        assertEquals(AilmentE.FINE, myPoke.getStatusAilment().val());
    }
}
