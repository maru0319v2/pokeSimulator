package pokemonStatus;

public interface StatusRank {
    int getAttack();
    int getBlock();
    int getContact();
    int getDefense();
    int getSpeed();
    double attackRateByStatusRank();
    double blockRateByStatusRank();
    double contactRateByStatusRank();
    double defenseRateByStatusRank();
    double speedRateByStatusRank();
    StatusRank add(int attack, int block, int contact, int defense, int speed);
    StatusRank reset();
}
