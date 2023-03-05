package move;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DetailedMoveSpecies {
    // CPUの技選択時に使用する技の詳細な分類
    DAMAGE("通常ダメージ"),
    HIT("必中技"),
    PRIORITY("先制技"),
    RECOVERY("回復"),
    UP_A("ランク上昇A"),
    UP_B("ランク上昇B"),
    UP_C("ランク上昇C"),
    UP_D("ランク上昇D"),
    UP_S("ランク上昇S"),
    UP_HI("ランク上昇命中"),
    UP_AV("ランク上昇回避"),
    DOWN_A("ランク下降A"),
    DOWN_B("ランク下降B"),
    DOWN_C("ランク下降C"),
    DOWN_D("ランク下降D"),
    DOWN_S("ランク下降S"),
    DOWN_HI("ランク下降命中"),
    DOWN_AV("ランク下降回避"),
    AILMENT("状態異常"),
    WEATHER("天候");

    private final String value;
}
