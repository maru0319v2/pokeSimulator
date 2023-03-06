package statusAilment;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AilmentE {
    BURN("やけど"),
    FREEZE("こおり"),
    PARALYSIS("まひ"),
    POISON("どく"),
    BAD_POISON("もうどく"),
    SLEEP("ねむり"),
    FAINTING("ひんし"),
    FINE("-");

    public final String value;
}
