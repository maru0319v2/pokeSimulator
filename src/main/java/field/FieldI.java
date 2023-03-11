package field;

import pokemon.PokeInfo;

public class FieldI implements Field {
    private final PokeInfo poke;
    private final LightScreen lightScreen;
    private final Reflect reflect;

    public PokeInfo poke() {
        return this.poke;
    }

    public LightScreen lightScreen() {
        return this.lightScreen;
    }

    public Reflect reflect() {
        return this.reflect;
    }

    // 初期化したい場合
    public static Field initField(PokeInfo poke) {
        return new FieldI(poke);
    }

    private FieldI(PokeInfo poke) {
        this.poke = poke;
        this.lightScreen = LightScreen.initLightScreen();
        this.reflect = Reflect.initReflect();
    }

    private FieldI(PokeInfo poke, LightScreen lightScreen, Reflect reflect) {
        this.poke = poke;
        this.lightScreen = lightScreen;
        this.reflect = reflect;
    }

    @Override
    public Field withPokeInfo(PokeInfo poke) {
        return new FieldI(poke, this.lightScreen, this.reflect);
    }

    @Override
    public Field withLightScreen(LightScreen lightScreen) {
        return new FieldI(this.poke, lightScreen, this.reflect);
    }

    @Override
    public Field withReflect(Reflect reflect) {
        return new FieldI(this.poke, this.lightScreen, reflect);
    }
}
