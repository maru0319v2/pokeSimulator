package impl;

import bussinessLogic.Gender;

import java.util.Random;

public class GenderImpl implements Gender {
    private final String value;

    public GenderImpl() {
        Random rand = new Random();
        if(rand.nextInt(10) % 2 == 0) {
            this.value = "♂";
        } else {
            this.value = "♀";
        }
    }

    public GenderImpl(Gender gender) {
        this.value = gender.value();
    }

    public String value() {
        return this.value;
    }
}
