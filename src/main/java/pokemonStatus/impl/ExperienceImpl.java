package pokemonStatus.impl;

import Enum.ExperienceType;
import pokemon.PokeInfo;
import pokemonStatus.Experience;

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
        return new ExperienceImpl(this.totalExperience + addExperience);
    }

    // 参考: https://wiki.xn--rckteqa2e.com/wiki/%E7%B5%8C%E9%A8%93%E5%80%A4%E3%82%BF%E3%82%A4%E3%83%97


    // TODO 初期から持っている経験値
    public int initialExperience(PokeInfo target, ExperienceType experienceType) {
        return requireExperienceAsType(target);
    }

    // 次のレベルアップに必要な経験値
    public int nextRequireExperience(PokeInfo target) {
        return requireExperienceAsType(target) - target.getExperience().totalExperience();
    }

    public int requireExperience(PokeInfo target) {
        return requireExperienceAsType(target);
    }

    private int requireExperienceAsType(PokeInfo target) {
        ExperienceType experienceType = target.getBasePrm().getExperienceType();
        int level = target.getLevel().value() + 1;
        int result = 10;

        if (Objects.equals(experienceType, ExperienceType.TYPE600000)) {
            if (level >= 2 && level <= 50) {
                result = (int) Math.floor(Math.pow(level, 3) * (100 - level) / 50);
            } else if (level >= 50 && level <= 68) {
                result = (int) Math.floor(Math.pow(level, 3) * (150 - level) / 100);
            } else if (level >= 68 && level <= 98) {
                result = (int) Math.floor(Math.pow(level, 3) * (int) Math.floor(637 - 10 * level / 3) / 500); // TODO　ここの計算式怪しいかも
            } else if (level >= 98 && level <= 100) {
                result = (int) Math.floor(Math.pow(level, 3) * (160 - level) / 100);
            }
        }
        if (Objects.equals(experienceType, ExperienceType.TYPE800000)) {
            result = (int) Math.floor(0.8 * Math.pow(level, 3));
        }
        if (Objects.equals(experienceType, ExperienceType.TYPE1000000)) {
            result = (int) Math.pow(level, 3);
        }
        if (Objects.equals(experienceType, ExperienceType.TYPE1050000)) {
            result = (int) Math.floor(1.2 * Math.pow(level, 3) - 15 * Math.pow(level, 2) + 100 * level - 140);
        }
        if (Objects.equals(experienceType, ExperienceType.TYPE1250000)) {
            result = (int) Math.floor(1.25 * Math.pow(level, 3));
        }
        if (Objects.equals(experienceType, ExperienceType.TYPE1640000)) {
            if (level >= 2 && level <= 15) {
                result = (int) Math.floor(Math.pow(level, 3) * (24 + Math.floor((level + 1) / 3) / 50));
            } else if (level >= 15 && level <= 36) {
                result = (int) Math.floor(Math.pow(level, 3) * (14 + level) / 50);
            } else if (level >= 36 && level <= 100) {
                result = (int) Math.floor(Math.pow(level, 3) * (32 + Math.floor(level / 2) / 50));
            }
        }
        if (level == 2) {
            result = 9;
        }
        return result;
    }

    public boolean isLevelUp(PokeInfo target) {
        int totalExp = this.totalExperience();
        int requireExp = this.requireExperience(target);
        return totalExp >= requireExp;
    }
}
