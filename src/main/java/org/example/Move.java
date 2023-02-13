package main.java.org.example;

// 技を表現するクラス
public interface Move {
    // 技名
    public String value();
    // タイプ
    public MoveType moveType();
    // 分類
    public MoveSpecies moveSpecies();
    // 威力
    public int damage();
    // 命中率
    public int hitRate();


}
