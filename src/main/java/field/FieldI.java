package field;

public class FieldI implements Field {
    private final LightScreen lightScreen;
    private final Reflect reflect;

    public LightScreen lightScreen() {
        return this.lightScreen;
    }

    public Reflect reflect() {
        return this.reflect;
    }

    // 初期化したい場合
    public static Field initField() {
        return new FieldI();
    }

    private FieldI() {
        this.lightScreen = LightScreen.initLightScreen();
        this.reflect = Reflect.initReflect();
    }

    private FieldI(LightScreen lightScreen, Reflect reflect) {
        this.lightScreen = lightScreen;
        this.reflect = reflect;
    }

    @Override
    public Field withLightScreen(LightScreen lightScreen) {
        return new FieldI(lightScreen, this.reflect);
    }

    @Override
    public Field withReflect(Reflect reflect) {
        return new FieldI(this.lightScreen, reflect);
    }
}
