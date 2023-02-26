package pokemon;

import Enum.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import move.*;

import java.util.List;

@Getter
@AllArgsConstructor
public enum BasePrm {
    // 各ポケモンの固有の値を表現する
    BULBASAUR(
            "001", "フシギダネ",
            Type.GRASS, Type.POISON,
            45, 49, 49, 65, 65, 45,
            ExperienceType.TYPE1050000, 64,
            List.of(new MoveImpl(BaseMPrm.TACKLE), new MoveImpl(BaseMPrm.VINE_WHIP), new MoveImpl(BaseMPrm.GROWL), new MoveImpl(BaseMPrm.SWORDS_DANCE))
    ),
    CHARMANDER(
            "004", "ヒトカゲ",
            Type.FIRE, Type.NONE,
            39, 52, 43, 60, 50, 65,
            ExperienceType.TYPE1050000, 62,
            List.of(new MoveImpl(BaseMPrm.TACKLE), new MoveImpl(BaseMPrm.GROWL))
    ),
    SQUIRTLE(
            "007", "ゼニガメ",
            Type.WATER, Type.NONE,
            44, 48, 65, 50, 64, 43,
            ExperienceType.TYPE1050000, 63,
            List.of(new MoveImpl(BaseMPrm.TACKLE), new MoveImpl(BaseMPrm.GROWL))
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
    private final ExperienceType experienceType;
    private final int basicExperience;
    private final List<Move> initialMove;
}
