package Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExpType {
    TYPE600000("600,000タイプ"),
    TYPE800000("800,000タイプ"),
    TYPE1000000("1,000,000タイプ"),
    TYPE1050000("1,050,000タイプ"),
    TYPE1250000("1,250,000タイプ"),
    TYPE1640000("1,640,000タイプ");

    private final String value;
}