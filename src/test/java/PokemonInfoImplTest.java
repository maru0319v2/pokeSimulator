import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pokemon.BasePrm;
import pokemon.PokeInfo;
import statusAilment.AilmentEnum;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pokemon.PokeInfoI.init;

public class PokemonInfoImplTest {
    @Test
    @DisplayName("HP0でひんし状態になり、回復すると状態なしになること")
    public void testBattleLogic1() throws InterruptedException {
        PokeInfo myPoke = init(BasePrm.CHARIZARD);
        myPoke = myPoke.damage(200);
        assertEquals(AilmentEnum.FAINTING, myPoke.ailment().val());

        myPoke = myPoke.recoveryHP(50);
        assertEquals(AilmentEnum.FINE, myPoke.ailment().val());
    }
}
