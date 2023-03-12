import Enum.Gender;
import Enum.Item;
import Enum.Nature;
import bussinessLogic.BattleLogic;
import field.Field;
import move.BaseMvPrm;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pokemon.BasePrm;
import pokemon.PokeInfo;
import pokemon.PokeInfoI;
import pokemonStatus.impl.LevelI;

import java.util.List;

import static field.FieldI.initField;
import static move.MoveI.initMv;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static pokemonStatus.impl.EffortValueI.initEffortValue;
import static pokemonStatus.impl.IndividualValueI.initIndividualValue;

public class BattleLogicTest {
    @Test
    @DisplayName("技の優先度によって先攻後攻が決まること")
    public void testBattleLogic1() {
        PokeInfo myPoke = new PokeInfoI(
                BasePrm.CHARIZARD,
                Gender.MALE,
                Nature.MODEST,
                initIndividualValue(),
                initEffortValue(),
                new LevelI(5),
                List.of(initMv(BaseMvPrm.QUICK_ATTACK)),
                Item.NONE
        );
        PokeInfo enemyPoke = new PokeInfoI(
                BasePrm.BLASTOISE,
                Gender.MALE,
                Nature.MODEST,
                initIndividualValue(),
                initEffortValue(),
                new LevelI(100),
                List.of(initMv(BaseMvPrm.TACKLE)),
                Item.NONE
        );
        Field myField = initField(myPoke);
        Field enemyField = initField(enemyPoke);

        assertTrue(BattleLogic.isFirstMe(myField, enemyField, initMv(BaseMvPrm.QUICK_ATTACK), initMv(BaseMvPrm.TACKLE)));
        assertFalse(BattleLogic.isFirstMe(myField, enemyField, initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.QUICK_ATTACK)));
    }


}
