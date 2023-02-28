import Enum.*;
import move.BaseMPrm;
import move.MoveImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import bussinessLogic.BattleLogic;
import pokemon.BasePrm;
import pokemon.PokemonInfo;
import pokemon.PokemonInfoImpl;
import pokemonStatus.impl.EffortValueImpl;
import pokemonStatus.impl.IndividualValueImpl;
import pokemonStatus.impl.LevelImpl;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BattleLogicTest {
    @Test
    @DisplayName("技の優先度によって先攻後攻が決まること")
    public void testBattleLogic1() {
        PokemonInfo myPoke = new PokemonInfoImpl(
                BasePrm.CHARIZARD,
                Gender.MALE,
                Nature.MODEST,
                new IndividualValueImpl(),
                new EffortValueImpl(),
                new LevelImpl(5),
                List.of(new MoveImpl(BaseMPrm.QUICK_ATTACK))
        );
        PokemonInfo enemyPoke = new PokemonInfoImpl(
                BasePrm.BLASTOISE,
                Gender.MALE,
                Nature.MODEST,
                new IndividualValueImpl(),
                new EffortValueImpl(),
                new LevelImpl(100),
                List.of(new MoveImpl(BaseMPrm.TACKLE))
        );

        boolean result1 = BattleLogic.isPreemptiveMe(myPoke, enemyPoke, new MoveImpl(BaseMPrm.QUICK_ATTACK), new MoveImpl(BaseMPrm.TACKLE));
        assertEquals(result1, true);
        boolean result2 = BattleLogic.isPreemptiveMe(myPoke, enemyPoke, new MoveImpl(BaseMPrm.TACKLE), new MoveImpl(BaseMPrm.QUICK_ATTACK));
        assertEquals(result2, false);
    }


}
