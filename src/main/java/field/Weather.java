package field;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Weather {
    DROUGHT("ひでり"),
    RAIN("あめ"),
    SANDSTORM("すなあらし"),
    HAIL("あられ"),
    NONE("-");

    public final String value;
}
