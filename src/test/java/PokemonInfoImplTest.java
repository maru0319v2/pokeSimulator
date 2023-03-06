import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pokemon.BasePrm;
import pokemon.PokeInfo;
import statusAilment.AilmentE;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pokemon.PokeInfoI.initialize;

public class PokemonInfoImplTest {
    @Test
    @DisplayName("HP0でひんし状態になり、回復すると状態なしになること")
    public void testBattleLogic1() throws InterruptedException {
        PokeInfo myPoke = initialize(BasePrm.CHARMANDER);
        myPoke = myPoke.damage(200);
        assertEquals(AilmentE.FAINTING, myPoke.ailment().val());

        myPoke = myPoke.recoveryHP(50);
        assertEquals(AilmentE.FINE, myPoke.ailment().val());
    }
}
