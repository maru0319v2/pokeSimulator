package Enum;

public enum StatusAilment {
    BURN("やけど"),
    FREEZE("こおり"),
    PARALYSIS("まひ"),
    POISON("どく"),
    BAD_POISON("もうどく"),
    SLEEP("ねむり"),
    FAINTING("ひんし");

    public final String value;

    StatusAilment(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }

    public double dameRateByBurn() {
        return 0.0;
    }
}
