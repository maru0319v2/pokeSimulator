import field.Field;
import field.Weather;
import move.BaseMvPrm;
import move.MoveI;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pokemon.BasePrm;
import pokemon.PokeInfo;

import static field.FieldI.changeField;
import static field.FieldI.initializeField;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static pokemon.PokeInfoI.initialize;

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

    @Test
    @DisplayName("晴れのとき炎技が1.5倍、水技が0.5倍になること")
    public void test4() throws InterruptedException {
        Field field = initializeField();
        field = changeField(field, Weather.DROUGHT);

        double result1 = field.damageRateByWeather(new MoveI(BaseMvPrm.FLAMETHROWER));
        double result2 = field.damageRateByWeather(new MoveI(BaseMvPrm.WATER_PULSE));

        assertEquals(1.5, result1);
        assertEquals(0.5, result2);
    }

    @Test
    @DisplayName("雨のとき炎技が0.5倍、水技が1.5倍になること")
    public void test5() throws InterruptedException {
        Field field = initializeField();
        field = changeField(field, Weather.RAIN);

        double result1 = field.damageRateByWeather(new MoveI(BaseMvPrm.FLAMETHROWER));
        double result2 = field.damageRateByWeather(new MoveI(BaseMvPrm.WATER_PULSE));

        assertEquals(0.5, result1);
        assertEquals(1.5, result2);
    }

    @Test
    @DisplayName("砂嵐のときに岩タイプの特防が1.5倍になること")
    public void test6() throws InterruptedException {
        PokeInfo myPoke1 = initialize(BasePrm.RHYDON);
        PokeInfo myPoke2 = initialize(BasePrm.CHARIZARD);

        Field field = initializeField();
        double result1 = field.defenceRateBySandStorm(myPoke1);
        double result2 = field.defenceRateBySandStorm(myPoke2);
        assertEquals(1.0, result1);
        assertEquals(1.0, result2);

        field = changeField(field, Weather.SANDSTORM);
        double result3 = field.defenceRateBySandStorm(myPoke1);
        double result4 = field.defenceRateBySandStorm(myPoke2);
        assertEquals(1.5, result3);
        assertEquals(1.0, result4);
    }
}
