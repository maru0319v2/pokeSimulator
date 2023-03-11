package field;

import pokemon.PokeInfo;

public interface Field {
    PokeInfo poke();

    LightScreen lightScreen();

    Reflect reflect();

    Field withPokeInfo(PokeInfo poke);

    Field withLightScreen(LightScreen lightScreen);

    Field withReflect(Reflect reflect);
}
