package field;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Weather {
    DROUGHT("ひでり"),
    RAIN("あめ"),
    SANDSTORM("すなあらし"),
    HAIL("あられ"),
    NONE("-");

    private final String val;

    public String val() {
        return this.val;
    }
}
