package Enum;

import lombok.AllArgsConstructor;

import java.util.Random;

// 性格を表現するクラス
@AllArgsConstructor
public enum Nature {
    LONELY("さみしがり", 1.1, 0.9, 1, 1, 1),
    ADAMANT("いじっぱり", 1.1, 1, 0.9, 1, 1),
    NAUGHTY("やんちゃ", 1.1, 1, 1, 0.9, 1),
    BRAVE("ゆうかん", 1.1, 1, 1, 1, 0.9),
    BOLD("ずぶとい", 0.9, 1.1, 1, 1, 1),
    IMPISH("わんぱく", 1, 1.1, 0.9, 1, 1),
    LAX("のうてんき", 1, 1.1, 1, 0.9, 1),
    RELAXED("のんき", 1, 1.1, 1, 1, 0.9),
    MODEST("ひかえめ", 0.9, 1, 1.1, 1, 1),
    MILD("おっとり", 1, 0.9, 1.1, 1, 1),
    RASH("うっかりや", 1, 1, 1.1, 0.9, 1),
    QUIET("れいせい", 1, 1, 1.1, 1, 0.9),
    CALM("おだやか", 0.9, 1, 1, 1.1, 1),
    GENTLE("おとなしい", 1, 0.9, 1, 1.1, 1),
    CAREFUL("しんちょう", 1, 1, 0.9, 1.1, 1),
    SASSY("なまいき", 1, 1, 1, 1.1, 0.9),
    TIMID("おくびょう", 0.9, 1, 1, 1, 1.1),
    HASTY("せっかち", 1, 0.9, 1, 1, 1.1),
    JOLLY("ようき", 1, 1, 0.9, 1, 1.1),
    NAIVE("むじゃき", 1, 1, 1, 0.9, 1.1),
    HARDY("がんばりや", 1, 1, 1, 1, 1),
    DOCILE("すなお", 1, 1, 1, 1, 1),
    SERIOUS("まじめ", 1, 1, 1, 1, 1),
    BASHFUL("てれや", 1, 1, 1, 1, 1),
    QUIRKY("きまぐれ", 1, 1, 1, 1, 1);

    private final String val;
    private final double attackRate;
    private final double blockRate;
    private final double contactRate;
    private final double defenceRate;
    private final double speedRate;

    public static Nature initNature() {
        int index = new Random().nextInt(Nature.values().length);
        return Nature.values()[index];
    }

    public String val() {
        return this.val;
    }

    public double attackRate() {
        return this.attackRate;
    }

    public double blockRate() {
        return this.blockRate;
    }

    public double contactRate() {
        return this.contactRate;
    }

    public double defenceRate() {
        return this.defenceRate;
    }

    public double speedRate() {
        return this.speedRate;
    }
}
