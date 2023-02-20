package bussinessLogic;

public interface EffortValue {


    // 努力値を加算する。
    EffortValue add(final int hitPoint, final int attack, final int block, final int contact, final int defense, final int speed);

    // 努力値をリセットする。
    EffortValue reset();

    int hitPoint();
    int attack();
    int block();
    int contact();
    int defense();
    int speed();
}
