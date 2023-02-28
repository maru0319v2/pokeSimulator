
import bussinessLogic.BattleLogic;
import move.BaseMPrm;
import move.MoveImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pokemon.BasePrm;
import pokemon.PokemonInfo;
import pokemon.PokemonInfoImpl;
import Enum.*;



import static org.junit.jupiter.api.Assertions.*;

public class PokemonInfoImplTest {
    @Test
    @DisplayName("HP0でひんし状態になり、回復すると状態なしになること")
    public void testBattleLogic1() throws InterruptedException {
        PokemonInfo myPoke = new PokemonInfoImpl(BasePrm.CHARMANDER);
        myPoke = myPoke.damagePoke(200);
        assertEquals(myPoke.getStatusAilment(), StatusAilment.FAINTING);

        myPoke = myPoke.recoveryHitPoint(50);
        assertEquals(myPoke.getStatusAilment(), StatusAilment.NONE);
    }
}
