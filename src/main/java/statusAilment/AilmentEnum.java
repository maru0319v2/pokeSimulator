package statusAilment;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum AilmentEnum {
    BURN("やけど"),
    FREEZE("こおり"),
    PARALYSIS("まひ"),
    POISON("どく"),
    BAD_POISON("もうどく"),
    SLEEP("ねむり"),
    SELF_SLEEP("ねむり"),
    FAINTING("ひんし"),
    FINE("-");

    public final String val;

    public String val() {
        return this.val;
    }
}
