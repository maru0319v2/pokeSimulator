package field;

public class FieldI implements Field {
    private final Weather weather;
    private final LightScreen lightScreen;
    private final Reflect reflect;

    public Weather weather() {
        return this.weather;
    }

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
        this.weather = Weather.initWeather();
        this.lightScreen = LightScreen.initLightScreen();
        this.reflect = Reflect.initReflect();
    }

    private FieldI(Weather weather, LightScreen lightScreen, Reflect reflect) {
        this.weather = weather;
        this.lightScreen = lightScreen;
        this.reflect = reflect;
    }

    @Override
    public Field withWeather(Weather weather) {
        return new FieldI(weather, this.lightScreen, this.reflect);
    }

    @Override
    public Field withLightScreen(LightScreen lightScreen) {
        return new FieldI(this.weather, lightScreen, this.reflect);
    }

    @Override
    public Field withReflect(Reflect reflect) {
        return new FieldI(this.weather, this.lightScreen, reflect);
    }
}
