package field;

public class FieldI implements Field {
    private final Weather weather;

    public Weather weather() {
        return this.weather;
    }

    private FieldI(Weather weather) {
        this.weather = weather;
    }

    // 初期化したい場合
    public static Field initField() {
        return new FieldI();
    }

    private FieldI() {
        this.weather = Weather.initWeather();
    }

    @Override
    public Field withWeather(Weather weather) {
        return new FieldI(weather);
    }
}
