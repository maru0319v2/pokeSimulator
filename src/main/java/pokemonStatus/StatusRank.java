package pokemonStatus;

import pokemon.PokeInfo;

public interface StatusRank {
    int getAttack();

    int getBlock();

    int getContact();

    int getDefense();

    int getSpeed();

    int getHitRate();

    int getAvoidRate();

    double attackRateByStatusRank();

    double blockRateByStatusRank();

    double contactRateByStatusRank();

    double defenseRateByStatusRank();

    double speedRateByStatusRank();

    double hitRateByStatusRank(PokeInfo defencePoke);

    StatusRank add(int attack, int block, int contact, int defense, int speed, int hitRate, int avoidRate);

    StatusRank reset();
}
