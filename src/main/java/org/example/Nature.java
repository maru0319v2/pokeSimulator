package main.java.org.example;

// 性格を表現するクラス
public interface Nature {
    public String value();

    public double attackRateByNature();

    public double blockRateByNature();

    public double contactRateByNature();

    public double defenceRateByNature();

    public double speedRateByNature();
}
