package Enum;

import move.Move;

import java.util.List;

// タイプを表現するクラス
public enum Type {
    NORMAL("ノーマル"),
    FIGHTING("かくとう"),
    FLYING("ひこう"),
    POISON("どく"),
    GROUND("じめん"),
    ROCK("いわ"),
    BUG("むし"),
    GHOST("ゴースト"),
    STEEL("はがね"),
    FIRE("ほのお"),
    WATER("みず"),
    GRASS("くさ"),
    ELECTRIC("でんき"),
    PSYCHIC("エスパー"),
    ICE("こおり"),
    DRAGON("ドラゴン"),
    DARK("あく"),
    FAIRY("フェアリー"),
    NONE("-");

    public final String value;

    Type(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }

    public static double damageRateByType(Type type1, Type type2, Move affectedMove) {
        double result1;
        switch (type1) {
            case NORMAL -> result1 = damageRateNormal(affectedMove);
            case FIGHTING -> result1 = damageRateFighting(affectedMove);
            case FLYING -> result1 = damageRateFlying(affectedMove);
            case POISON -> result1 = damageRatePoison(affectedMove);
            case GROUND -> result1 = damageRateGround(affectedMove);
            case ROCK -> result1 = damageRateRock(affectedMove);
            case BUG -> result1 = damageRateBug(affectedMove);
            case GHOST -> result1 = damageRateGhost(affectedMove);
            case STEEL -> result1 = damageRateSteel(affectedMove);
            case FIRE -> result1 = damageRateFire(affectedMove);
            case WATER -> result1 = damageRateWater(affectedMove);
            case GRASS -> result1 = damageRateGrass(affectedMove);
            case ELECTRIC -> result1 = damageRateElectric(affectedMove);
            case PSYCHIC -> result1 = damageRatePsychic(affectedMove);
            case ICE -> result1 = damageRateIce(affectedMove);
            case DRAGON -> result1 = damageRateDragon(affectedMove);
            case DARK -> result1 = damageRateDark(affectedMove);
            case FAIRY -> result1 = damageRateFairy(affectedMove);
            default -> result1 = 1.0;
        }

        double result2;
        switch (type2) {
            case NORMAL -> result2 = damageRateNormal(affectedMove);
            case FIGHTING -> result2 = damageRateFighting(affectedMove);
            case FLYING -> result2 = damageRateFlying(affectedMove);
            case POISON -> result2 = damageRatePoison(affectedMove);
            case GROUND -> result2 = damageRateGround(affectedMove);
            case ROCK -> result2 = damageRateRock(affectedMove);
            case BUG -> result2 = damageRateBug(affectedMove);
            case GHOST -> result2 = damageRateGhost(affectedMove);
            case STEEL -> result2 = damageRateSteel(affectedMove);
            case FIRE -> result2 = damageRateFire(affectedMove);
            case WATER -> result2 = damageRateWater(affectedMove);
            case GRASS -> result2 = damageRateGrass(affectedMove);
            case ELECTRIC -> result2 = damageRateElectric(affectedMove);
            case PSYCHIC -> result2 = damageRatePsychic(affectedMove);
            case ICE -> result2 = damageRateIce(affectedMove);
            case DRAGON -> result2 = damageRateDragon(affectedMove);
            case DARK -> result2 = damageRateDark(affectedMove);
            case FAIRY -> result2 = damageRateFairy(affectedMove);
            default -> result2 = 1.0;
        }

        return result1 * result2;
    }

    // 自分のもつタイプがノーマル
    private static double damageRateNormal(Move affectedMove) {
        List<Type> superEffectiveList = List.of(FIGHTING);
        List<Type> doesNotAffectList = List.of(GHOST);

        if (superEffectiveList.contains(affectedMove.getMoveType())) {
            return 2.0;
        } else if (doesNotAffectList.contains(affectedMove.getMoveType())) {
            return 0.0;
        } else {
            return 1.0;
        }
    }

    private static double damageRateFire(Move affectedMove) {
        List<Type> superEffectiveList = List.of(WATER, GROUND, ROCK);
        List<Type> notEffectiveList = List.of(FIRE, GRASS, ICE, BUG, STEEL, FAIRY);

        if (superEffectiveList.contains(affectedMove.getMoveType())) {
            return 2.0;
        } else if (notEffectiveList.contains(affectedMove.getMoveType())) {
            return 0.5;
        } else {
            return 1.0;
        }
    }

    private static double damageRateWater(Move affectedMove) {
        List<Type> superEffectiveList = List.of(ELECTRIC, GRASS);
        List<Type> notEffectiveList = List.of(FIRE, WATER, ICE, STEEL);

        if (superEffectiveList.contains(affectedMove.getMoveType())) {
            return 2.0;
        } else if (notEffectiveList.contains(affectedMove.getMoveType())) {
            return 0.5;
        } else {
            return 1.0;
        }
    }

    private static double damageRateElectric(Move affectedMove) {
        List<Type> superEffectiveList = List.of(GROUND);
        List<Type> notEffectiveList = List.of(ELECTRIC, FLYING, STEEL);

        if (superEffectiveList.contains(affectedMove.getMoveType())) {
            return 2.0;
        } else if (notEffectiveList.contains(affectedMove.getMoveType())) {
            return 0.5;
        } else {
            return 1.0;
        }
    }

    private static double damageRateGrass(Move affectedMove) {
        List<Type> superEffectiveList = List.of(FIRE, ICE, POISON, FLYING, BUG);
        List<Type> notEffectiveList = List.of(WATER, ELECTRIC, GRASS, GROUND);

        if (superEffectiveList.contains(affectedMove.getMoveType())) {
            return 2.0;
        } else if (notEffectiveList.contains(affectedMove.getMoveType())) {
            return 0.5;
        } else {
            return 1.0;
        }
    }

    private static double damageRateIce(Move affectedMove) {
        List<Type> superEffectiveList = List.of(FIRE, FIGHTING, ROCK, STEEL);
        List<Type> notEffectiveList = List.of(ICE);

        if (superEffectiveList.contains(affectedMove.getMoveType())) {
            return 2.0;
        } else if (notEffectiveList.contains(affectedMove.getMoveType())) {
            return 0.5;
        } else {
            return 1.0;
        }
    }

    private static double damageRateFighting(Move affectedMove) {
        List<Type> superEffectiveList = List.of(FLYING, PSYCHIC, FAIRY);
        List<Type> notEffectiveList = List.of(BUG, ROCK, DARK);

        if (superEffectiveList.contains(affectedMove.getMoveType())) {
            return 2.0;
        } else if (notEffectiveList.contains(affectedMove.getMoveType())) {
            return 0.5;
        } else {
            return 1.0;
        }
    }

    private static double damageRatePoison(Move affectedMove) {
        List<Type> superEffectiveList = List.of(GROUND, PSYCHIC);
        List<Type> notEffectiveList = List.of(GRASS, FIGHTING, POISON, BUG, FAIRY);

        if (superEffectiveList.contains(affectedMove.getMoveType())) {
            return 2.0;
        } else if (notEffectiveList.contains(affectedMove.getMoveType())) {
            return 0.5;
        } else {
            return 1.0;
        }
    }

    private static double damageRateGround(Move affectedMove) {
        List<Type> superEffectiveList = List.of(WATER, GRASS, ICE);
        List<Type> notEffectiveList = List.of(POISON, ROCK);
        List<Type> doesNotAffectList = List.of(ELECTRIC);

        if (superEffectiveList.contains(affectedMove.getMoveType())) {
            return 2.0;
        } else if (notEffectiveList.contains(affectedMove.getMoveType())) {
            return 0.5;
        } else if (doesNotAffectList.contains(affectedMove.getMoveType())) {
            return 0.0;
        } else {
            return 1.0;
        }
    }

    private static double damageRateFlying(Move affectedMove) {
        List<Type> superEffectiveList = List.of(ELECTRIC, ICE, ROCK);
        List<Type> notEffectiveList = List.of(GRASS, FIGHTING, BUG);
        List<Type> doesNotAffectList = List.of(GROUND);

        if (superEffectiveList.contains(affectedMove.getMoveType())) {
            return 2.0;
        } else if (notEffectiveList.contains(affectedMove.getMoveType())) {
            return 0.5;
        } else if (doesNotAffectList.contains(affectedMove.getMoveType())) {
            return 0.0;
        } else {
            return 1.0;
        }
    }

    private static double damageRatePsychic(Move affectedMove) {
        List<Type> superEffectiveList = List.of(BUG, GHOST, DARK);
        List<Type> notEffectiveList = List.of(FIGHTING, PSYCHIC);

        if (superEffectiveList.contains(affectedMove.getMoveType())) {
            return 2.0;
        } else if (notEffectiveList.contains(affectedMove.getMoveType())) {
            return 0.5;
        } else {
            return 1.0;
        }
    }

    private static double damageRateBug(Move affectedMove) {
        List<Type> superEffectiveList = List.of(FIRE, FLYING, ROCK);
        List<Type> notEffectiveList = List.of(GRASS, FIGHTING, GROUND);

        if (superEffectiveList.contains(affectedMove.getMoveType())) {
            return 2.0;
        } else if (notEffectiveList.contains(affectedMove.getMoveType())) {
            return 0.5;
        } else {
            return 1.0;
        }
    }

    private static double damageRateRock(Move affectedMove) {
        List<Type> superEffectiveList = List.of(WATER, GRASS, FIGHTING, GROUND, STEEL);
        List<Type> notEffectiveList = List.of(NORMAL, FIRE, POISON, FLYING);

        if (superEffectiveList.contains(affectedMove.getMoveType())) {
            return 2.0;
        } else if (notEffectiveList.contains(affectedMove.getMoveType())) {
            return 0.5;
        } else {
            return 1.0;
        }
    }

    private static double damageRateGhost(Move affectedMove) {
        List<Type> superEffectiveList = List.of(GHOST, DARK);
        List<Type> notEffectiveList = List.of(POISON, BUG);
        List<Type> doesNotAffectList = List.of(NORMAL, FIGHTING);

        if (superEffectiveList.contains(affectedMove.getMoveType())) {
            return 2.0;
        } else if (notEffectiveList.contains(affectedMove.getMoveType())) {
            return 0.5;
        } else if (doesNotAffectList.contains(affectedMove.getMoveType())) {
            return 0.0;
        } else {
            return 1.0;
        }
    }

    private static double damageRateDragon(Move affectedMove) {
        List<Type> superEffectiveList = List.of(ICE, DRAGON, FAIRY);
        List<Type> notEffectiveList = List.of(FIRE, WATER, ELECTRIC, GRASS);

        if (superEffectiveList.contains(affectedMove.getMoveType())) {
            return 2.0;
        } else if (notEffectiveList.contains(affectedMove.getMoveType())) {
            return 0.5;
        } else {
            return 1.0;
        }
    }

    private static double damageRateDark(Move affectedMove) {
        List<Type> superEffectiveList = List.of(FIGHTING, BUG, FAIRY);
        List<Type> notEffectiveList = List.of(GHOST, DARK);
        List<Type> doesNotAffectList = List.of(PSYCHIC);

        if (superEffectiveList.contains(affectedMove.getMoveType())) {
            return 2.0;
        } else if (notEffectiveList.contains(affectedMove.getMoveType())) {
            return 0.5;
        } else if (doesNotAffectList.contains(affectedMove.getMoveType())) {
            return 0.0;
        } else {
            return 1.0;
        }
    }

    private static double damageRateSteel(Move affectedMove) {
        List<Type> superEffectiveList = List.of(FIRE, FIGHTING, GROUND);
        List<Type> notEffectiveList = List.of(NORMAL, GRASS, ICE, FLYING, PSYCHIC, BUG, ROCK, DRAGON, STEEL, FAIRY);
        List<Type> doesNotAffectList = List.of(POISON);

        if (superEffectiveList.contains(affectedMove.getMoveType())) {
            return 2.0;
        } else if (notEffectiveList.contains(affectedMove.getMoveType())) {
            return 0.5;
        } else if (doesNotAffectList.contains(affectedMove.getMoveType())) {
            return 0.0;
        } else {
            return 1.0;
        }
    }

    private static double damageRateFairy(Move affectedMove) {
        List<Type> superEffectiveList = List.of(POISON, STEEL);
        List<Type> notEffectiveList = List.of(FIGHTING, BUG, DARK);
        List<Type> doesNotAffectList = List.of(DRAGON);

        if (superEffectiveList.contains(affectedMove.getMoveType())) {
            return 2.0;
        } else if (notEffectiveList.contains(affectedMove.getMoveType())) {
            return 0.5;
        } else if (doesNotAffectList.contains(affectedMove.getMoveType())) {
            return 0.0;
        } else {
            return 1.0;
        }
    }
}
