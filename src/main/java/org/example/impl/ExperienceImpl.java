package main.java.org.example.impl;

import main.java.org.example.Experience;
import main.java.org.example.Level;
import main.java.org.example.PokemonInfo;

import java.util.Objects;

public class ExperienceImpl implements Experience {
    private final int totalExperience;

    public ExperienceImpl(int totalExperience) {
        this.totalExperience = totalExperience;
    }

    public int totalExperience() {
        return this.totalExperience;
    }


    public ExperienceImpl add(int addExperience) {
        return new ExperienceImpl(this.totalExperience + addExperience) ;
//        int require = nextRequireExperience(target);
//        if(addExperience.value() >= require) {
//            return target.level().add();
//        }
//        return target.level();
    }

    // 参考: https://wiki.xn--rckteqa2e.com/wiki/%E7%B5%8C%E9%A8%93%E5%80%A4%E3%82%BF%E3%82%A4%E3%83%97

    // 次のレベルアップに必要な経験値
    public int nextRequireExperience(PokemonInfo target) {
        return requireExperienceAsType(target) - target.experience().totalExperience();
    }

    public int requireExperience(PokemonInfo target) {
        return requireExperienceAsType(target);
    }

    private int requireExperienceAsType(PokemonInfo target) {
        String experienceType = target.experienceType();
        int level = target.level().value() + 1;
        int result = 10;

        if(Objects.equals(experienceType, "60万タイプ")) {
            if(level >= 2 && level <= 50) {
                result = (int)Math.floor(Math.pow(level, 3) * (100 - level) / 50);
            } else if(level >= 50 && level <= 68) {
                result = (int)Math.floor(Math.pow(level, 3) * (150 - level) / 100);
            } else if(level >= 68 && level <= 98) {
                result = (int)Math.floor(Math.pow(level, 3) * (int)Math.floor(637 - 10 * level / 3) / 500); // TODO　ここの計算式怪しいかも
            } else if(level >= 98 && level <= 100) {
                result = (int)Math.floor(Math.pow(level, 3) * (160 - level) / 100);
            }
        }
        if(Objects.equals(experienceType, "80万タイプ")) {
            result = (int)Math.floor(0.8 * Math.pow(level, 3));
        }
        if(Objects.equals(experienceType, "100万タイプ")) {
            result = (int)Math.pow(level, 3);
        }
        if(Objects.equals(experienceType, "105万タイプ")) {
            result = (int)Math.floor(1.2 * Math.pow(level, 3) - 15 * Math.pow(level, 2) + 100 * level - 140);
        }
        if(Objects.equals(experienceType, "125万タイプ")) {
            result = (int)Math.floor(1.25 * Math.pow(level, 3));
        }
        if(Objects.equals(experienceType, "164万タイプ")) {
            if(level >= 2 && level <= 15) {
                result = (int)Math.floor(Math.pow(level, 3) * (24 + Math.floor((level + 1) / 3) / 50));
            } else if (level >= 15 && level <= 36) {
                result = (int)Math.floor(Math.pow(level, 3) * (14 + level) / 50);
            } else if (level >= 36 && level <= 100) {
                result = (int)Math.floor(Math.pow(level, 3) * (32 + Math.floor(level / 2) / 50));
            }
        }
        if(level == 2) { result = 9; }
        return result;
    }
}
