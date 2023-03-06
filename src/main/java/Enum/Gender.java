package Enum;

import lombok.AllArgsConstructor;

import java.util.Random;

@AllArgsConstructor
public enum Gender {
    MALE("♂"),
    FEMALE("♀"),
    UNKNOWN("");

    private final String val;

    public static Gender initGender() {
        int index = new Random().nextInt(Gender.values().length);
        return Gender.values()[index];
    }

    public String val() {
        return this.val;
    }
}
