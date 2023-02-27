package Enum;

import java.util.Random;

public enum Gender {
    MALE("♂"),
    FEMALE("♀"),
    UNKNOWN("");

    public final String value;

    public static Gender decide() {
        Random rand = new Random();
        if(rand.nextInt(10) % 2 == 0) {
            return MALE;
        } else {
            return FEMALE;
        }
    }

    Gender(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
