package main.java.org.example.impl;

import main.java.org.example.Nature;

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

    public String value() {
        return this.value;
    }
}
