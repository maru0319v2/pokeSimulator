import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pokemon.BasePrm;
import pokemon.PokeInfo;
import pokemon.PokeInfoImpl;
import statusAilment.Ailment;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PokemonInfoImplTest {
    @Test
    @DisplayName("HP0でひんし状態になり、回復すると状態なしになること")
    public void testBattleLogic1() throws InterruptedException {
        PokeInfo myPoke = new PokeInfoImpl(BasePrm.CHARMANDER);
        myPoke = myPoke.damagePoke(200);
        assertEquals(Ailment.FAINTING, myPoke.getStatusAilment().getValue());

        myPoke = myPoke.recoveryHitPoint(50);
        assertEquals(Ailment.FINE, myPoke.getStatusAilment().getValue());
    }
}
