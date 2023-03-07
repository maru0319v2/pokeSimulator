package pokemon;

import Enum.ExpType;
import Enum.Type;
import lombok.AllArgsConstructor;
import move.BaseMvPrm;
import move.Move;

import java.util.List;

import static move.MoveI.initMv;

@SuppressWarnings("ALL")
@AllArgsConstructor
public enum BasePrm {
    // 各ポケモンの固有の値を表現する
    BULBASAUR(
            "001", "フシギダネ",
            Type.GRASS, Type.POISON,
            45, 49, 49, 65, 65, 45,
            ExpType.TYPE1050000, 64,
            0, 0, 0, 1, 0, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.VINE_WHIP), initMv(BaseMvPrm.GROWL), initMv(BaseMvPrm.SWORDS_DANCE))
    ),
    IVYSAUR(
            "002", "フシギソウ",
            Type.GRASS, Type.POISON,
            60, 62, 63, 80, 80, 60,
            ExpType.TYPE1050000, 142,
            0, 0, 0, 1, 1, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.VINE_WHIP), initMv(BaseMvPrm.GROWL), initMv(BaseMvPrm.SWORDS_DANCE))
    ),
    VENUSAUR(
            "003", "フシギバナ",
            Type.GRASS, Type.POISON,
            80, 82, 83, 100, 100, 80,
            ExpType.TYPE1050000, 263,
            0, 0, 0, 2, 1, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.VINE_WHIP), initMv(BaseMvPrm.GROWL), initMv(BaseMvPrm.SWORDS_DANCE))
    ),
    CHARMANDER(
            "004", "ヒトカゲ",
            Type.FIRE, Type.NONE,
            39, 52, 43, 60, 50, 65,
            ExpType.TYPE1050000, 62,
            0, 0, 0, 0, 0, 1,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    CHARMELEON(
            "005", "リザード",
            Type.FIRE, Type.NONE,
            58, 64, 58, 80, 65, 80,
            ExpType.TYPE1050000, 142,
            0, 0, 0, 1, 0, 1,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    CHARIZARD(
            "006", "リザードン",
            Type.FIRE, Type.FLYING,
            78, 84, 78, 109, 85, 100,
            ExpType.TYPE1050000, 267,
            0, 0, 0, 3, 0, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    SQUIRTLE(
            "007", "ゼニガメ",
            Type.WATER, Type.NONE,
            44, 48, 65, 50, 64, 43,
            ExpType.TYPE1050000, 63,
            0, 0, 1, 0, 0, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    WARTORTLE(
            "008", "カメール",
            Type.WATER, Type.NONE,
            59, 63, 80, 65, 80, 58,
            ExpType.TYPE1050000, 142,
            0, 0, 1, 0, 1, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    BLASTOISE(
            "009", "カメックス",
            Type.WATER, Type.NONE,
            79, 83, 100, 85, 105, 78,
            ExpType.TYPE1050000, 265,
            0, 0, 0, 0, 3, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    BUTTERFREE(
            "012", "バタフリー",
            Type.BUG, Type.FLYING,
            60, 45, 50, 80, 80, 70,
            ExpType.TYPE1000000, 198,
            0, 0, 0, 2, 1, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    BEEDRILL(
            "015", "スピアー",
            Type.BUG, Type.POISON,
            65, 90, 40, 45, 80, 75,
            ExpType.TYPE1000000, 178,
            0, 2, 0, 0, 1, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    PIDGEOT(
            "018", "ピジョット",
            Type.NORMAL, Type.FLYING,
            83, 80, 75, 70, 70, 101,
            ExpType.TYPE1050000, 240,
            0, 0, 0, 0, 0, 3,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    RATICATE(
            "020", "ラッタ",
            Type.NORMAL, Type.NONE,
            55, 81, 60, 50, 70, 97,
            ExpType.TYPE1000000, 145,
            0, 0, 0, 0, 0, 2,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    FEAROW(
            "022", "オニドリル",
            Type.NORMAL, Type.FLYING,
            65, 90, 65, 61, 61, 100,
            ExpType.TYPE1000000, 155,
            0, 0, 0, 0, 0, 2,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    ARBOK(
            "024", "アーボック",
            Type.POISON, Type.NONE,
            60, 95, 69, 65, 79, 80,
            ExpType.TYPE1000000, 157,
            0, 2, 0, 0, 0, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    RAICHU(
            "026", "ライチュウ",
            Type.ELECTRIC, Type.NONE,
            60, 90, 55, 90, 80, 110,
            ExpType.TYPE1000000, 243,
            0, 0, 0, 0, 0, 3,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    SANDSLASH(
            "028", "サンドパン",
            Type.GROUND, Type.NONE,
            75, 100, 110, 45, 55, 65,
            ExpType.TYPE1000000, 158,
            0, 0, 2, 0, 0, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    NIDOQUEEN(
            "031", "ニドクイン",
            Type.POISON, Type.GROUND,
            90, 92, 87, 75, 85, 76,
            ExpType.TYPE1050000, 253,
            3, 0, 0, 0, 0, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    NIDOKING(
            "034", "ニドキング",
            Type.POISON, Type.GROUND,
            81, 102, 77, 85, 75, 85,
            ExpType.TYPE1050000, 253,
            0, 3, 0, 0, 0, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    DUGTRIO(
            "051", "ダグトリオ",
            Type.GROUND, Type.NONE,
            35, 100, 50, 50, 70, 120,
            ExpType.TYPE1000000, 149,
            0, 0, 0, 0, 0, 2,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    RHYDON(
            "112", "サイドン",
            Type.GROUND, Type.ROCK,
            105, 130, 120, 45, 45, 40,
            ExpType.TYPE1250000, 170,
            0, 2, 0, 0, 0, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    LAPRAS(
            "131", "ラプラス",
            Type.WATER, Type.ICE,
            130, 85, 80, 85, 95, 60,
            ExpType.TYPE1250000, 187,
            2, 0, 0, 0, 0, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    SCIZOR(
            "212", "ハッサム",
            Type.BUG, Type.STEEL,
            70, 130, 100, 55, 80, 65,
            ExpType.TYPE1000000, 175,
            0, 2, 0, 0, 0, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    );

    private final String pokeDexNo;
    private final String pName;
    private final Type type1;
    private final Type type2;
    private final int hp;
    private final int attack;
    private final int block;
    private final int contact;
    private final int defence;
    private final int speed;
    private final ExpType expType;
    private final int basicExp;
    private final int givenEffortH;
    private final int givenEffortA;
    private final int givenEffortB;
    private final int givenEffortC;
    private final int givenEffortD;
    private final int givenEffortS;
    private final List<Move> initialMove;

    public String pokeDexNo() {
        return this.pokeDexNo;
    }

    public String pName() {
        return this.pName;
    }

    public Type type1() {
        return this.type1;
    }

    public Type type2() {
        return this.type2;
    }

    public int hp() {
        return this.hp;
    }

    public int attack() {
        return this.attack;
    }

    public int block() {
        return this.block;
    }

    public int contact() {
        return this.contact;
    }

    public int defence() {
        return this.defence;
    }

    public int speed() {
        return this.speed;
    }

    public ExpType expType() {
        return this.expType;
    }

    public int basicExp() {
        return this.basicExp;
    }

    public int givenEffortH() {
        return this.givenEffortH;
    }

    public int givenEffortA() {
        return this.givenEffortA;
    }

    public int givenEffortB() {
        return this.givenEffortB;
    }

    public int givenEffortC() {
        return this.givenEffortC;
    }

    public int givenEffortD() {
        return this.givenEffortD;
    }

    public int givenEffortS() {
        return this.givenEffortS;
    }

    public List<Move> initialMove() {
        return this.initialMove;
    }
}
