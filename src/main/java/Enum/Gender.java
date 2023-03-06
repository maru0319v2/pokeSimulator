package Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Random;

@Getter
@AllArgsConstructor
public enum Gender {
    MALE("♂"),
    FEMALE("♀"),
    UNKNOWN("");

    public final String value;

    public static Gender initGender() {
        int index = new Random().nextInt(Gender.values().length);
        return Gender.values()[index];
    }
}
