package statusAilment;

import lombok.AllArgsConstructor;

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

    public final String val;

    public String val() {
        return this.val;
    }
}