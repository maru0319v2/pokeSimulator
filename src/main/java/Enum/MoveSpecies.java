package Enum;

// 技の分類を表現するクラス

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum MoveSpecies {
    PHYSICAL("物理"),
    SPECIAL("特殊"),
    CHANGE("変化");

    private final String val;

    public String val() {
        return this.val;
    }
}
