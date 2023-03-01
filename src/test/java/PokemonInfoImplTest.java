
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pokemon.BasePrm;
import pokemon.PokemonInfo;
import pokemon.PokemonInfoImpl;
import statusAilment.Ailment;


import static org.junit.jupiter.api.Assertions.*;

public class PokemonInfoImplTest {
    @Test
    @DisplayName("HP0でひんし状態になり、回復すると状態なしになること")
    public void testBattleLogic1() throws InterruptedException {
        PokemonInfo myPoke = new PokemonInfoImpl(BasePrm.CHARMANDER);
        myPoke = myPoke.damagePoke(200);
        assertEquals(Ailment.FAINTING, myPoke.getStatusAilment().getValue());

        myPoke = myPoke.recoveryHitPoint(50);
        assertEquals(Ailment.NONE, myPoke.getStatusAilment().getValue());
    }
}
