import Enum.Gender;
import Enum.Item;
import Enum.Nature;
import bussinessLogic.BattleLogic;
import field.Field;
import field.FieldI;
import move.BaseMvPrm;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pokemon.BasePrm;
import pokemon.PokeInfo;
import pokemon.PokeInfoI;
import pokemonStatus.impl.EffortValueI;
import pokemonStatus.impl.IndividualValueI;
import pokemonStatus.impl.LevelI;

import java.util.List;

import static move.MoveI.init;
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
                IndividualValueI.init(),
                EffortValueI.init(),
                new LevelI(5),
                List.of(init(BaseMvPrm.QUICK_ATTACK)),
                Item.NONE
        );
        PokeInfo enemyPoke = new PokeInfoI(
                BasePrm.BLASTOISE,
                Gender.MALE,
                Nature.MODEST,
                IndividualValueI.init(),
                EffortValueI.init(),
                new LevelI(100),
                List.of(init(BaseMvPrm.BODY_SLAM)),
                Item.NONE
        );
        Field myField = FieldI.init(myPoke);
        Field enemyField = FieldI.init(enemyPoke);

        assertTrue(BattleLogic.isFirstMe(myField, enemyField, init(BaseMvPrm.QUICK_ATTACK), init(BaseMvPrm.BODY_SLAM)));
        assertFalse(BattleLogic.isFirstMe(myField, enemyField, init(BaseMvPrm.BODY_SLAM), init(BaseMvPrm.QUICK_ATTACK)));
    }


}
