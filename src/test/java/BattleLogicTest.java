import Enum.Gender;
import Enum.Nature;
import bussinessLogic.BattleLogic;
import move.BaseMvPrm;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pokemon.BasePrm;
import pokemon.PokeInfo;
import pokemon.PokeInfoI;
import pokemonStatus.impl.LevelI;

import java.util.List;

import static move.MoveI.initMv;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static pokemonStatus.impl.EffortValueI.initializeEffortValue;
import static pokemonStatus.impl.IndividualValueI.initializeIndividualValue;

public class BattleLogicTest {
    @Test
    @DisplayName("技の優先度によって先攻後攻が決まること")
    public void testBattleLogic1() {
        PokeInfo myPoke = new PokeInfoI(
                BasePrm.CHARIZARD,
                Gender.MALE,
                Nature.MODEST,
                initializeIndividualValue(),
                initializeEffortValue(),
                new LevelI(5),
                List.of(initMv(BaseMvPrm.QUICK_ATTACK))
        );
        PokeInfo enemyPoke = new PokeInfoI(
                BasePrm.BLASTOISE,
                Gender.MALE,
                Nature.MODEST,
                initializeIndividualValue(),
                initializeEffortValue(),
                new LevelI(100),
                List.of(initMv(BaseMvPrm.TACKLE))
        );

        assertTrue(BattleLogic.isFirstMe(myPoke, enemyPoke, initMv(BaseMvPrm.QUICK_ATTACK), initMv(BaseMvPrm.TACKLE)));
        assertFalse(BattleLogic.isFirstMe(myPoke, enemyPoke, initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.QUICK_ATTACK)));
    }


}
