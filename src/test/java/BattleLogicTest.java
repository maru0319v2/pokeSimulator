import Enum.Gender;
import Enum.Nature;
import bussinessLogic.BattleLogic;
import move.BaseMPrm;
import move.MoveImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pokemon.BasePrm;
import pokemon.PokeInfo;
import pokemon.PokeInfoImpl;
import pokemonStatus.impl.EffortValueImpl;
import pokemonStatus.impl.IndividualValueImpl;
import pokemonStatus.impl.LevelImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BattleLogicTest {
    @Test
    @DisplayName("技の優先度によって先攻後攻が決まること")
    public void testBattleLogic1() {
        PokeInfo myPoke = new PokeInfoImpl(
                BasePrm.CHARIZARD,
                Gender.MALE,
                Nature.MODEST,
                new IndividualValueImpl(),
                new EffortValueImpl(),
                new LevelImpl(5),
                List.of(new MoveImpl(BaseMPrm.QUICK_ATTACK))
        );
        PokeInfo enemyPoke = new PokeInfoImpl(
                BasePrm.BLASTOISE,
                Gender.MALE,
                Nature.MODEST,
                new IndividualValueImpl(),
                new EffortValueImpl(),
                new LevelImpl(100),
                List.of(new MoveImpl(BaseMPrm.TACKLE))
        );

        assertTrue(BattleLogic.isFirstMe(myPoke, enemyPoke, new MoveImpl(BaseMPrm.QUICK_ATTACK), new MoveImpl(BaseMPrm.TACKLE)));
        assertFalse(BattleLogic.isFirstMe(myPoke, enemyPoke, new MoveImpl(BaseMPrm.TACKLE), new MoveImpl(BaseMPrm.QUICK_ATTACK)));
    }


}
