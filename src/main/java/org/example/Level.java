package main.java.org.example;

// レベルを表現するクラス
public interface Level {
    public Level add();

    public int value();

    public Level add(int value);
}
