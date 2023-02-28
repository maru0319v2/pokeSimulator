package Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Ailment {
    BURN("やけど"),
    FREEZE("こおり"),
    PARALYSIS("まひ"),
    POISON("どく"),
    BAD_POISON("もうどく"),
    SLEEP("ねむり"),
    FAINTING("ひんし"),
    NONE("-");

    public final String value;
}
