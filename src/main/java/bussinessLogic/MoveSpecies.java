package bussinessLogic;

// 技の分類を表現するクラス
public enum MoveSpecies {
    PHYSICAL("物理"),
    SPECIAL("特殊");

    private final String value;

    MoveSpecies(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
