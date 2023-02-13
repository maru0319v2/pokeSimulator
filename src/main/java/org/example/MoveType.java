package main.java.org.example;

// 技のタイプを表現するクラス
public enum MoveType {
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
    FAIRY("フェアリー");

    private final String value;

    MoveType(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
