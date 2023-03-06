package Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import move.Move;

// タイプを表現するクラス
@Getter
@AllArgsConstructor
public enum Type {
    // 倍率は定数側が防御、フィールド側が攻撃とする
    NORMAL("ノーマル", 1, 1, 1, 1, 1, 1, 2.0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1),
    FIRE("ほのお", 1, 0.5, 2.0, 1, 0.5, 0.5, 1, 1, 2.0, 1, 1, 0.5, 2.0, 1, 1, 1, 0.5, 0.5),
    WATER("みず", 1, 0.5, 0.5, 2.0, 2.0, 0.5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0.5, 1),
    ELECTRIC("でんき", 1, 1, 1, 0.5, 1, 1, 1, 1, 2.0, 0.5, 1, 1, 1, 1, 1, 1, 0.5, 1),
    GRASS("くさ", 1, 2.0, 0.5, 0.5, 0.5, 2.0, 1, 2.0, 0.5, 2.0, 1, 2.0, 1, 1, 1, 1, 1, 1),
    ICE("こおり", 1, 2.0, 1, 1, 1, 0.5, 2.0, 1, 1, 1, 1, 1, 2.0, 1, 1, 1, 2.0, 1),
    FIGHTING("かくとう", 1, 1, 1, 1, 1, 1, 1, 1, 1, 2.0, 2.0, 0.5, 0.5, 1, 1, 0.5, 1, 2.0),
    POISON("どく", 1, 1, 1, 1, 0.5, 1, 0.5, 0.5, 2.0, 1, 2.0, 0.5, 1, 1, 1, 1, 1, 0.5),
    GROUND("じめん", 1, 1, 2.0, 0, 2.0, 2.0, 1, 0.5, 1, 1, 1, 1, 0.5, 1, 1, 1, 1, 1),
    FLYING("ひこう", 1, 1, 1, 2.0, 0.5, 2.0, 0.5, 1, 0, 1, 1, 0.5, 2.0, 1, 1, 1, 1, 1),
    PSYCHIC("エスパー", 1, 1, 1, 1, 1, 1, 0.5, 1, 1, 1, 0.5, 2.0, 1, 2.0, 1, 2.0, 1, 1),
    BUG("むし", 1, 2.0, 1, 1, 0.5, 1, 0.5, 1, 0.5, 2.0, 1, 1, 2.0, 1, 1, 1, 1, 1),
    ROCK("いわ", 0.5, 0.5, 2.0, 1, 2.0, 1, 2.0, 0.5, 2.0, 0.5, 1, 1, 1, 1, 1, 1, 2.0, 1),
    GHOST("ゴースト", 0, 1, 1, 1, 1, 1, 0, 0.5, 1, 1, 1, 0.5, 1, 2.0, 1, 2.0, 1, 1),
    DRAGON("ドラゴン", 1, 0.5, 0.5, 0.5, 0.5, 2.0, 1, 1, 1, 1, 1, 1, 1, 1, 2.0, 1, 1, 2.0),
    DARK("あく", 1, 1, 1, 1, 1, 1, 2.0, 1, 1, 1, 0, 2.0, 1, 0.5, 1, 0.5, 1, 2.0),
    STEEL("はがね", 0.5, 2.0, 1, 1, 0.5, 0.5, 2.0, 0, 2.0, 0.5, 0.5, 0.5, 0.5, 1, 0.5, 1, 0.5, 0.5),
    FAIRY("フェアリー", 1, 1, 1, 1, 1, 1, 0.5, 2.0, 1, 1, 1, 0.5, 1, 1, 0, 0.5, 2.0, 1),
    NONE("-", 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);

    private final String value;
    private final double damageRateNormal;
    private final double damageRateFire;
    private final double damageRateWater;
    private final double damageRateElectric;
    private final double damageRateGrass;
    private final double damageRateIce;
    private final double damageRateFighting;
    private final double damageRatePoison;
    private final double damageRateGround;
    private final double damageRateFlying;
    private final double damageRatePsychic;
    private final double damageRateBug;
    private final double damageRateRock;
    private final double damageRateGhost;
    private final double damageRateDragon;
    private final double damageRateDark;
    private final double damageRateSteel;
    private final double damageRateFairy;

    public static double damageRateByType(Type type1, Type type2, Move affectedMove) {
        double result = 0;
        switch (affectedMove.baseMPrm().moveType()) {
            case NORMAL -> result = type1.damageRateNormal * type2.damageRateNormal;
            case FIGHTING -> result = type1.damageRateFighting * type2.damageRateFighting;
            case FLYING -> result = type1.damageRateFlying * type2.damageRateFlying;
            case POISON -> result = type1.damageRatePoison * type2.damageRatePoison;
            case GROUND -> result = type1.damageRateGround * type2.damageRateGround;
            case ROCK -> result = type1.damageRateRock * type2.damageRateRock;
            case BUG -> result = type1.damageRateBug * type2.damageRateBug;
            case GHOST -> result = type1.damageRateGhost * type2.damageRateGhost;
            case STEEL -> result = type1.damageRateSteel * type2.damageRateSteel;
            case FIRE -> result = type1.damageRateFire * type2.damageRateFire;
            case WATER -> result = type1.damageRateWater * type2.damageRateWater;
            case GRASS -> result = type1.damageRateGrass * type2.damageRateGrass;
            case ELECTRIC -> result = type1.damageRateElectric * type2.damageRateElectric;
            case PSYCHIC -> result = type1.damageRatePsychic * type2.damageRatePsychic;
            case ICE -> result = type1.damageRateIce * type2.damageRateIce;
            case DRAGON -> result = type1.damageRateDragon * type2.damageRateDragon;
            case DARK -> result = type1.damageRateDark * type2.damageRateDark;
            case FAIRY -> result = type1.damageRateFairy * type2.damageRateFairy;
        }
        return result;
    }
}
