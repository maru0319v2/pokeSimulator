import Enum.Gender;
import Enum.Nature;
import bussinessLogic.BattleLogic;
import move.BaseMvPrm;
import move.MoveI;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pokemon.BasePrm;
import pokemon.PokeInfo;
import pokemon.PokeInfoI;
import pokemonStatus.impl.EffortValueI;
import pokemonStatus.impl.IndividualValueI;
import pokemonStatus.impl.LevelI;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BattleLogicTest {
    @Test
    @DisplayName("技の優先度によって先攻後攻が決まること")
    public void testBattleLogic1() {
        PokeInfo myPoke = new PokeInfoI(
                BasePrm.CHARIZARD,
                Gender.MALE,
                Nature.MODEST,
                new IndividualValueI(),
                new EffortValueI(),
                new LevelI(5),
                List.of(new MoveI(BaseMvPrm.QUICK_ATTACK))
        );
        PokeInfo enemyPoke = new PokeInfoI(
                BasePrm.BLASTOISE,
                Gender.MALE,
                Nature.MODEST,
                new IndividualValueI(),
                new EffortValueI(),
                new LevelI(100),
                List.of(new MoveI(BaseMvPrm.TACKLE))
        );

        assertTrue(BattleLogic.isFirstMe(myPoke, enemyPoke, new MoveI(BaseMvPrm.QUICK_ATTACK), new MoveI(BaseMvPrm.TACKLE)));
        assertFalse(BattleLogic.isFirstMe(myPoke, enemyPoke, new MoveI(BaseMvPrm.TACKLE), new MoveI(BaseMvPrm.QUICK_ATTACK)));
    }


}
