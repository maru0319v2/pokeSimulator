package pokemon;

import Enum.ExpType;
import Enum.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import move.BaseMvPrm;
import move.Move;
import move.MoveI;

import java.util.List;

@SuppressWarnings("ALL")
@Getter
@AllArgsConstructor
public enum BasePrm {
    // 各ポケモンの固有の値を表現する
    BULBASAUR(
            "001", "フシギダネ",
            Type.GRASS, Type.POISON,
            45, 49, 49, 65, 65, 45,
            ExpType.TYPE1050000, 64,
            0, 0, 0, 1, 0, 0,
            List.of(new MoveI(BaseMvPrm.TACKLE), new MoveI(BaseMvPrm.VINE_WHIP), new MoveI(BaseMvPrm.GROWL), new MoveI(BaseMvPrm.SWORDS_DANCE))
    ),
    IVYSAUR(
            "002", "フシギソウ",
            Type.GRASS, Type.POISON,
            60, 62, 63, 80, 80, 60,
            ExpType.TYPE1050000, 142,
            0, 0, 0, 1, 1, 0,
            List.of(new MoveI(BaseMvPrm.TACKLE), new MoveI(BaseMvPrm.VINE_WHIP), new MoveI(BaseMvPrm.GROWL), new MoveI(BaseMvPrm.SWORDS_DANCE))
    ),
    VENUSAUR(
            "003", "フシギバナ",
            Type.GRASS, Type.POISON,
            80, 82, 83, 100, 100, 80,
            ExpType.TYPE1050000, 263,
            0, 0, 0, 2, 1, 0,
            List.of(new MoveI(BaseMvPrm.TACKLE), new MoveI(BaseMvPrm.VINE_WHIP), new MoveI(BaseMvPrm.GROWL), new MoveI(BaseMvPrm.SWORDS_DANCE))
    ),
    CHARMANDER(
            "004", "ヒトカゲ",
            Type.FIRE, Type.NONE,
            39, 52, 43, 60, 50, 65,
            ExpType.TYPE1050000, 62,
            0, 0, 0, 0, 0, 1,
            List.of(new MoveI(BaseMvPrm.TACKLE), new MoveI(BaseMvPrm.GROWL))
    ),
    CHARMELEON(
            "005", "リザード",
            Type.FIRE, Type.NONE,
            58, 64, 58, 80, 65, 80,
            ExpType.TYPE1050000, 142,
            0, 0, 0, 1, 0, 1,
            List.of(new MoveI(BaseMvPrm.TACKLE), new MoveI(BaseMvPrm.GROWL))
    ),
    CHARIZARD(
            "006", "リザードン",
            Type.FIRE, Type.FLYING,
            78, 84, 78, 109, 85, 100,
            ExpType.TYPE1050000, 267,
            0, 0, 0, 3, 0, 0,
            List.of(new MoveI(BaseMvPrm.TACKLE), new MoveI(BaseMvPrm.GROWL))
    ),
    SQUIRTLE(
            "007", "ゼニガメ",
            Type.WATER, Type.NONE,
            44, 48, 65, 50, 64, 43,
            ExpType.TYPE1050000, 63,
            0, 0, 1, 0, 0, 0,
            List.of(new MoveI(BaseMvPrm.TACKLE), new MoveI(BaseMvPrm.GROWL))
    ),
    WARTORTLE(
            "008", "カメール",
            Type.WATER, Type.NONE,
            59, 63, 80, 65, 80, 58,
            ExpType.TYPE1050000, 142,
            0, 0, 1, 0, 1, 0,
            List.of(new MoveI(BaseMvPrm.TACKLE), new MoveI(BaseMvPrm.GROWL))
    ),
    BLASTOISE(
            "009", "カメックス",
            Type.WATER, Type.NONE,
            79, 83, 100, 85, 105, 78,
            ExpType.TYPE1050000, 265,
            0, 0, 0, 0, 3, 0,
            List.of(new MoveI(BaseMvPrm.TACKLE), new MoveI(BaseMvPrm.GROWL))
    ),
    RHYDON(
            "112", "サイドン",
            Type.GROUND, Type.ROCK,
            105, 130, 120, 45, 45, 40,
            ExpType.TYPE1250000, 170,
            0, 2, 0, 0, 0, 0,
            List.of(new MoveI(BaseMvPrm.TACKLE), new MoveI(BaseMvPrm.GROWL))
    );

    private final String pokeDexNo;
    private final String name;
    private final Type type1;
    private final Type type2;
    private final int hitPoint;
    private final int attack;
    private final int block;
    private final int contact;
    private final int defense;
    private final int speed;
    private final ExpType experienceType;
    private final int basicExperience;
    private final int givenEffortH;
    private final int givenEffortA;
    private final int givenEffortB;
    private final int givenEffortC;
    private final int givenEffortD;
    private final int givenEffortS;
    private final List<Move> initialMove;
}
