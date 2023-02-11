package main.java.org.example;

// タイプを表現するクラス
public enum PokemonType {
    NORMAL("normal"),
    FIGHTING("fighting"),
    FLYING("flying"),
    POISON("poison"),
    GROUND("ground"),
    ROCK("rock"),
    BUG("bug"),
    GHOST("ghost"),
    STEEL("steel"),
    FIRE("fire"),
    WATER("water"),
    GRASS("grass"),
    ELECTRIC("electric"),
    PSYCHIC("psychic"),
    ICE("ice"),
    DRAGON("dragon"),
    DARK("dark"),
    FAIRY("fairy");

    private String value;

    private PokemonType(String value) {
        this.value = value;
    }
}
