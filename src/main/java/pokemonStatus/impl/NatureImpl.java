package pokemonStatus.impl;

import pokemonStatus.Nature;

import java.util.List;
import java.util.Random;

// 性格を表現するクラス
public class NatureImpl implements Nature {
    private final String value;

    public NatureImpl() {
        List<String> natureList = List.of("さみしがり", "いじっぱり", "やんちゃ", "ゆうかん", "ずぶとい", "わんぱく", "のうてんき", "のんき", "ひかえめ", "おっとり", "うっかりや", "れいせい", "おだやか", "おとなしい", "しんちょう", "なまいき", "おくびょう", "せっかち", "ようき", "むじゃき", "てれや", "がんばりや", "すなお", "きまぐれ", "まじめ");
        int index = new Random().nextInt(natureList.size());
        this.value = natureList.get(index);
    }

    public NatureImpl(Nature nature) {
        this.value = nature.value();
    }

    public NatureImpl(String natureValue) {
        this.value = natureValue;
    }

    public String value() {
        return this.value;
    }
    public double attackRateByNature() {
        List<String> upperList = List.of("さみしがり", "いじっぱり", "やんちゃ", "ゆうかん");
        List<String> downerList = List.of("ずぶとい", "ひかえめ", "おだやか", "おくびょう");
        if(upperList.contains(this.value)) {
            return 1.1;
        } else if(downerList.contains(this.value)){
            return 0.9;
        } else {
            return 1.0;
        }
    }

    public double blockRateByNature() {
        List<String> upperList = List.of("ずぶとい", "わんぱく", "のうてんき", "のんき");
        List<String> downerList = List.of("さみしがり","おっとり","おとなしい","せっかち");
        if(upperList.contains(this.value)) {
            return 1.1;
        } else if(downerList.contains(this.value)){
            return 0.9;
        } else {
            return 1.0;
        }
    }

    public double contactRateByNature() {
        List<String> upperList = List.of("ひかえめ", "おっとり", "うっかりや", "れいせい");
        List<String> downerList = List.of("いじっぱり", "わんぱく", "しんちょう", "ようき");
        if(upperList.contains(this.value)) {
            return 1.1;
        } else if(downerList.contains(this.value)){
            return 0.9;
        } else {
            return 1.0;
        }
    }

    public double defenceRateByNature() {
        List<String> upperList = List.of("おだやか", "おとなしい", "しんちょう", "なまいき");
        List<String> downerList = List.of("やんちゃ", "のうてんき", "うっかりや", "むじゃき");
        if(upperList.contains(this.value)) {
            return 1.1;
        } else if(downerList.contains(this.value)){
            return 0.9;
        } else {
            return 1.0;
        }
    }

    public double speedRateByNature() {
        List<String> upperList = List.of("おくびょう", "せっかち", "ようき", "むじゃき");
        List<String> downerList = List.of("ゆうかん", "のんき", "れいせい", "なまいき");
        if(upperList.contains(this.value)) {
            return 1.1;
        } else if(downerList.contains(this.value)){
            return 0.9;
        } else {
            return 1.0;
        }
    }
}
