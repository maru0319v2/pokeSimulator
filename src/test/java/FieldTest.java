import field.Field;
import field.WeatherEnum;
import move.BaseMvPrm;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pokemon.BasePrm;
import pokemon.PokeInfo;

import static field.FieldI.changeWeather;
import static field.FieldI.initField;
import static move.MoveI.initMv;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static pokemon.PokeInfoI.initialize;

public class FieldTest {
    @Test
    @DisplayName("天候が上書きできること")
    public void test1() throws InterruptedException {
        // 初期化 天候なし
        Field field = initField();
        assertEquals(WeatherEnum.NONE, field.weather());

        // 天候なし -> ひでり
        field = changeWeather(field, WeatherEnum.DROUGHT);
        assertEquals(WeatherEnum.DROUGHT, field.weather());

        // ひでり -> あめ
        field = changeWeather(field, WeatherEnum.RAIN);
        assertEquals(WeatherEnum.RAIN, field.weather());
    }

    @Test
    @DisplayName("変更する天候がもとの天候と同じ場合上書きできないこと")
    public void test2() throws InterruptedException {
        // 初期化 天候なし
        Field field = initField();
        assertEquals(WeatherEnum.NONE, field.weather());

        // 天候なし -> ひでり
        field = changeWeather(field, WeatherEnum.DROUGHT);
        assertEquals(WeatherEnum.DROUGHT, field.weather());

        // 1ターン経過される
        field = field.elapsingTurnWeather();

        // ひでり -> ひでり
        field = changeWeather(field, WeatherEnum.DROUGHT);
        assertEquals(1, field.elapsedTurnWeather());
    }

    @Test
    @DisplayName("5ターン経過後にもとの天候にもどること")
    public void test3() throws InterruptedException {
        // 初期化 天候なし
        Field field = initField();
        assertEquals(WeatherEnum.NONE, field.weather());

        // 天候なし -> ひでり
        field = changeWeather(field, WeatherEnum.DROUGHT);
        assertEquals(WeatherEnum.DROUGHT, field.weather());

        // 1ターン経過される
        field = field.elapsingTurnWeather();
        field = field.elapsingTurnWeather();
        field = field.elapsingTurnWeather();
        field = field.elapsingTurnWeather();
        field = field.elapsingTurnWeather();

        // ひでり -> 天候なし
        assertEquals(WeatherEnum.NONE, field.weather());
    }

    @Test
    @DisplayName("晴れのとき炎技が1.5倍、水技が0.5倍になること")
    public void test4() throws InterruptedException {
        Field field = initField();
        field = changeWeather(field, WeatherEnum.DROUGHT);

        double result1 = field.dmgRateByWeather(initMv(BaseMvPrm.FLAMETHROWER));
        double result2 = field.dmgRateByWeather(initMv(BaseMvPrm.WATER_PULSE));

        assertEquals(1.5, result1);
        assertEquals(0.5, result2);
    }

    @Test
    @DisplayName("雨のとき炎技が0.5倍、水技が1.5倍になること")
    public void test5() throws InterruptedException {
        Field field = initField();
        field = changeWeather(field, WeatherEnum.RAIN);

        double result1 = field.dmgRateByWeather(initMv(BaseMvPrm.FLAMETHROWER));
        double result2 = field.dmgRateByWeather(initMv(BaseMvPrm.WATER_PULSE));

        assertEquals(0.5, result1);
        assertEquals(1.5, result2);
    }

    @Test
    @DisplayName("砂嵐のときに岩タイプの特防が1.5倍になること")
    public void test6() throws InterruptedException {
        PokeInfo myPoke1 = initialize(BasePrm.RHYDON);
        PokeInfo myPoke2 = initialize(BasePrm.CHARIZARD);

        Field field = initField();
        double result1 = field.dfcRateBySandStorm(myPoke1);
        double result2 = field.dfcRateBySandStorm(myPoke2);
        assertEquals(1.0, result1);
        assertEquals(1.0, result2);

        field = changeWeather(field, WeatherEnum.SANDSTORM);
        double result3 = field.dfcRateBySandStorm(myPoke1);
        double result4 = field.dfcRateBySandStorm(myPoke2);
        assertEquals(1.5, result3);
        assertEquals(1.0, result4);
    }

    @Test
    @DisplayName("晴れになったときにcountForRecoveryが5、elapsedTurnが0であること")
    public void test7() throws InterruptedException {
        Field field = initField();
        field = changeWeather(field, WeatherEnum.DROUGHT);

        assertEquals(0, field.elapsedTurnWeather());
        assertEquals(5, field.countRecoveryWeather());
    }

    @Test
    @DisplayName("岩、地面、鋼タイプはすなあらしダメージを受けないこと")
    public void test8() throws InterruptedException {
        Field field = initField();
        field = changeWeather(field, WeatherEnum.SANDSTORM);

        PokeInfo rockPk = initialize(BasePrm.RHYDON);
        PokeInfo groundPk = initialize(BasePrm.DUGTRIO);
        PokeInfo steelPk = initialize(BasePrm.SCIZOR);
        PokeInfo firePk = initialize(BasePrm.CHARIZARD);

        rockPk = field.slipDmgByWeather(rockPk);
        groundPk = field.slipDmgByWeather(groundPk);
        steelPk = field.slipDmgByWeather(steelPk);
        firePk = field.slipDmgByWeather(firePk);

        assertEquals(1, (rockPk.currentHP().val() / rockPk.realHP()));
        assertEquals(1, (groundPk.currentHP().val() / groundPk.realHP()));
        assertEquals(1, (steelPk.currentHP().val() / steelPk.realHP()));
        assertNotEquals(1, (firePk.currentHP().val() / firePk.realHP()));
    }

    @Test
    @DisplayName("氷タイプはあられダメージを受けないこと")
    public void test9() throws InterruptedException {
        Field field = initField();
        field = changeWeather(field, WeatherEnum.HAIL);

        PokeInfo icePk = initialize(BasePrm.LAPRAS);
        PokeInfo firePk = initialize(BasePrm.CHARIZARD);

        icePk = field.slipDmgByWeather(icePk);
        firePk = field.slipDmgByWeather(firePk);

        assertEquals(1, (icePk.currentHP().val() / icePk.realHP()));
        assertNotEquals(1, (firePk.currentHP().val() / firePk.realHP()));
    }
}
