package Enum;

import java.util.Objects;

public enum StatusAilment {
    BURN("やけど"),
    FREEZE("こおり"),
    PARALYSIS("まひ"),
    POISON("どく"),
    BAD_POISON("もうどく"),
    SLEEP("ねむり"),
    FAINTING("ひんし"),
    NONE("-");

    public final String value;
    StatusAilment(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }

    public double dameRateByBurn() {
         return Objects.equals(this.value, "やけど") ? 0.5 : 1.0;
    }
}
