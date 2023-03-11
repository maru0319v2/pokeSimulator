package field;

public interface Field {
    Weather weather();

    LightScreen lightScreen();

    Reflect reflect();

    Field withWeather(Weather weather);

    Field withLightScreen(LightScreen lightScreen);

    Field withReflect(Reflect reflect);
}
