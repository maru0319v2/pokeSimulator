package main.java.org.example;

import java.util.List;

// ポケモンのタイプを表現するクラス
public enum PokemonType {
    NORMAL("ノーマル"),
    FIGHTING("格闘"),
    FLYING("飛行"),
    POISON("毒"),
    GROUND("地面"),
    ROCK("岩"),
    BUG("虫"),
    GHOST("ゴースト"),
    STEEL("鋼"),
    FIRE("炎"),
    WATER("水"),
    GRASS("草"),
    ELECTRIC("電気"),
    PSYCHIC("エスパー"),
    ICE("氷"),
    DRAGON("ドラゴン"),
    DARK("悪"),
    FAIRY("フェアリー"),
    NONE("-");

    private final String value;

    PokemonType(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }

    public double damageRateByType(PokemonType type1, PokemonType type2, Move affectedMove) {
        return 1.0;
    }

    // 自分のもつタイプがノーマル
    private double damageRateNormal(Move affectedMove) {
        List<PokemonType> superEffectiveList = List.of(FIGHTING);
        List<PokemonType> notEffectiveList = List.of();
        List<PokemonType> doesNotAffectList = List.of(GHOST);

        if(superEffectiveList.contains(affectedMove.moveType())) {
            return 2.0;
        } else if (notEffectiveList.contains(affectedMove.moveType())) {
            return 0.5;
        } else if (doesNotAffectList.contains(affectedMove.moveType())) {
            return 0.0;
        } else {
            return 1.0;
        }
    }
}
