package field;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LightScreen {
    private final String val;

    public String val() {
        return this.val;
    }
}
