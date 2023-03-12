import Enum.Gender;
import Enum.Item;
import Enum.Nature;
import field.Field;
import field.Weather;
import move.BaseMvPrm;
import move.Move;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pokemon.BasePrm;
import pokemon.PokeInfo;
import pokemon.PokeInfoI;
import pokemonStatus.impl.EffortValueI;
import pokemonStatus.impl.IndividualValueI;
import pokemonStatus.impl.LevelI;

import java.util.List;

import static bussinessLogic.EnemySelectMove.enemySelectMove;
import static field.FieldI.initField;
import static move.MoveI.initMv;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static pokemon.PokeInfoI.initialize;

public class EnemySelectMoveTest {
    @Test
    @DisplayName("Exceptionが発生しないこと")
    public void test1() {
        PokeInfo enemyPk = new PokeInfoI(
                BasePrm.CHARIZARD,
                Gender.MALE,
                Nature.MODEST,
                new IndividualValueI(10, 10, 10, 10, 10, 10),
                new EffortValueI(6, 0, 0, 252, 0, 252),
                new LevelI(50),
                List.of(initMv(BaseMvPrm.WILL_O_WISP), initMv(BaseMvPrm.DOUBLE_TEAM)),
                Item.NONE
        );
        PokeInfo myPk = initialize(BasePrm.BLASTOISE);
        Weather weather = Weather.initWeather();
        Field myField = initField(myPk);
        Field enemyField = initField(enemyPk);

        Move result = enemySelectMove(enemyField, myField, weather);

        assertTrue(result.baseMPrm().priority() >= 0);

    }
}
