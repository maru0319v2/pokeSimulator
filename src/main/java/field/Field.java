package field;

import pokemon.PokeInfo;

public interface Field {
    PokeInfo poke();

    LightScreen lightScreen();

    Reflect reflect();

    Field updatePokeInfo(PokeInfo poke);

    Field updateLightScreen(LightScreen lightScreen);

    Field updateReflect(Reflect reflect);
}
