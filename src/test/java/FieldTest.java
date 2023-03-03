import field.Field;
import field.Weather;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pokemonStatus.StatusRank;
import pokemonStatus.impl.StatusRankImpl;

import static field.FieldImpl.changeField;
import static field.FieldImpl.initializeField;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FieldTest {
    @Test
    @DisplayName("天候が上書きできること")
    public void test1() throws InterruptedException {
        // 初期化 天候なし
        Field field = initializeField();
        assertEquals(Weather.NONE, field.getWeather());

        // 天候なし -> ひでり
        field = changeField(field, Weather.DROUGHT);
        assertEquals(Weather.DROUGHT, field.getWeather());

        // ひでり -> あめ
        field = changeField(field, Weather.RAIN);
        assertEquals(Weather.RAIN, field.getWeather());
    }

    @Test
    @DisplayName("変更する天候がもとの天候と同じ場合上書きできないこと")
    public void test2() throws InterruptedException {
        // 初期化 天候なし
        Field field = initializeField();
        assertEquals(Weather.NONE, field.getWeather());

        // 天候なし -> ひでり
        field = changeField(field, Weather.DROUGHT);
        assertEquals(Weather.DROUGHT, field.getWeather());

        // 1ターン経過される
        field = field.elapseTurn();

        // ひでり -> ひでり
        field = changeField(field, Weather.DROUGHT);
        assertEquals(1, field.getElapsedTurn());
    }

    @Test
    @DisplayName("5ターン経過後にもとの天候にもどること")
    public void test3() throws InterruptedException {
        // 初期化 天候なし
        Field field = initializeField();
        assertEquals(Weather.NONE, field.getWeather());

        // 天候なし -> ひでり
        field = changeField(field, Weather.DROUGHT);
        assertEquals(Weather.DROUGHT, field.getWeather());

        // 1ターン経過される
        field = field.elapseTurn();
        field = field.elapseTurn();
        field = field.elapseTurn();
        field = field.elapseTurn();
        field = field.elapseTurn();

        // ひでり -> 天候なし
        assertEquals(Weather.NONE, field.getWeather());
    }
}
