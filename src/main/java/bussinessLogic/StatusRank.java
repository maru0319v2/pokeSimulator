package bussinessLogic;

public interface StatusRank {
    int attack();
    int block();
    int contact();
    int defense();
    int speed();
    double attackRateByStatusRank();
    double blockRateByStatusRank();
    double contactRateByStatusRank();
    double defenseRateByStatusRank();
    double speedRateByStatusRank();
    StatusRank add(int attack, int block, int contact, int defense, int speed);
    StatusRank reset();
}
