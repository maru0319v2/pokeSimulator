package Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Random;

// 性格を表現するクラス
@Getter
@AllArgsConstructor
public enum Nature {
    LONELY("さみしがり", 1.1, 0.9, 1, 1, 1),
    ADAMANT("いじっぱり", 1.1, 1, 0.9, 1,  1),
    NAUGHTY("やんちゃ", 1.1, 1, 1, 0.9,  1),
    BRAVE("ゆうかん", 1.1, 1, 1, 1,  0.9),
    BOLD("ずぶとい", 0.9, 1.1, 1, 1,  1),
    IMPISH("わんぱく", 1, 1.1, 0.9, 1,  1),
    LAX("のうてんき", 1, 1.1, 1, 0.9,  1),
    RELAXED("のんき", 1, 1.1, 1, 1,  0.9),
    MODEST("ひかえめ", 0.9, 1, 1.1, 1,  1),
    MILD("おっとり", 1, 0.9, 1.1, 1,  1),
    RASH("うっかりや", 1, 1, 1.1, 0.9,  1),
    QUIET("れいせい", 1, 1, 1.1, 1,  0.9),
    CALM("おだやか", 0.9, 1, 1, 1.1,  1),
    GENTLE("おとなしい", 1, 0.9, 1, 1.1,  1),
    CAREFUL("しんちょう", 1, 1, 0.9, 1.1,  1),
    SASSY("なまいき", 1, 1, 1, 1.1,  0.9),
    TIMID("おくびょう", 0.9, 1, 1, 1,  1.1),
    HASTY("せっかち", 1, 0.9, 1, 1,  1.1),
    JOLLY("ようき", 1, 1, 0.9, 1,  1.1),
    NAIVE("むじゃき", 1, 1, 1, 0.9,  1.1),
    HARDY("がんばりや", 1, 1, 1, 1,  1),
    DOCILE("すなお", 1, 1, 1, 1,  1),
    SERIOUS("まじめ", 1, 1, 1, 1,  1),
    BASHFUL("てれや", 1, 1, 1, 1,  1),
    QUIRKY("きまぐれ", 1, 1, 1, 1,  1);

    private final String value;
    private final double attackRate;
    private final double blockRate;
    private final double contactRate;
    private final double defenceRate;
    private final double speedRate;

    public static Nature decide() {
        int index = new Random().nextInt(Nature.values().length);
        return Nature.values()[index];
    }
}
