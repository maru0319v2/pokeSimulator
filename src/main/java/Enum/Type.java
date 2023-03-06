package Enum;

import lombok.AllArgsConstructor;
import move.Move;

// タイプを表現するクラス
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

    private final String val;
    private final double dmgRateNormal;
    private final double dmgRateFire;
    private final double dmgRateWater;
    private final double dmgRateElectric;
    private final double dmgRateGrass;
    private final double dmgRateIce;
    private final double dmgRateFighting;
    private final double dmgRatePoison;
    private final double dmgRateGround;
    private final double dmgRateFlying;
    private final double dmgRatePsychic;
    private final double dmgRateBug;
    private final double dmgRateRock;
    private final double dmgRateGhost;
    private final double dmgRateDragon;
    private final double dmgRateDark;
    private final double dmgRateSteel;
    private final double dmgRateFairy;

    public String val() {
        return this.val;
    }

    public static double damageRateByType(Type type1, Type type2, Move affectedMove) {
        double result = 0;
        switch (affectedMove.baseMPrm().moveType()) {
            case NORMAL -> result = type1.dmgRateNormal * type2.dmgRateNormal;
            case FIGHTING -> result = type1.dmgRateFighting * type2.dmgRateFighting;
            case FLYING -> result = type1.dmgRateFlying * type2.dmgRateFlying;
            case POISON -> result = type1.dmgRatePoison * type2.dmgRatePoison;
            case GROUND -> result = type1.dmgRateGround * type2.dmgRateGround;
            case ROCK -> result = type1.dmgRateRock * type2.dmgRateRock;
            case BUG -> result = type1.dmgRateBug * type2.dmgRateBug;
            case GHOST -> result = type1.dmgRateGhost * type2.dmgRateGhost;
            case STEEL -> result = type1.dmgRateSteel * type2.dmgRateSteel;
            case FIRE -> result = type1.dmgRateFire * type2.dmgRateFire;
            case WATER -> result = type1.dmgRateWater * type2.dmgRateWater;
            case GRASS -> result = type1.dmgRateGrass * type2.dmgRateGrass;
            case ELECTRIC -> result = type1.dmgRateElectric * type2.dmgRateElectric;
            case PSYCHIC -> result = type1.dmgRatePsychic * type2.dmgRatePsychic;
            case ICE -> result = type1.dmgRateIce * type2.dmgRateIce;
            case DRAGON -> result = type1.dmgRateDragon * type2.dmgRateDragon;
            case DARK -> result = type1.dmgRateDark * type2.dmgRateDark;
            case FAIRY -> result = type1.dmgRateFairy * type2.dmgRateFairy;
        }
        return result;
    }
}
