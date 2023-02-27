package Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
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

    public double dameRateByBurn() {
         return Objects.equals(this.value, "やけど") ? 0.5 : 1.0;
    }
}
