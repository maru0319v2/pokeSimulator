package pokemonStatus;

import pokemon.PokeInfo;

public interface StatusRank {
    int attack();

    int block();

    int contact();

    int defence();

    int speed();

    int hitRate();

    int avoidRate();

    double atkRateByStatusRank();

    double blcRateByStatusRank();

    double cntRateByStatusRank();

    double dfcRateByStatusRank();

    double spdRateByStatusRank();

    double hitRateByStatusRank(PokeInfo defencePoke);

    StatusRank change(String name, int attack, int block, int contact, int defence, int speed, int hitRate, int avoidRate);

    StatusRank reset();
}
