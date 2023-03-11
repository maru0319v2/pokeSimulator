package field;

public interface Field {
    LightScreen lightScreen();

    Reflect reflect();

    Field withLightScreen(LightScreen lightScreen);

    Field withReflect(Reflect reflect);
}
