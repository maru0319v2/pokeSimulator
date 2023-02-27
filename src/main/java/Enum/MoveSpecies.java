package Enum;

// 技の分類を表現するクラス

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MoveSpecies {
    PHYSICAL("物理"),
    SPECIAL("特殊"),
    CHANGE("変化");

    private final String value;
}
