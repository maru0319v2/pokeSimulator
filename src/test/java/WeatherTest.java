import field.Weather;
import field.WeatherEnum;
import move.BaseMvPrm;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pokemon.BasePrm;
import pokemon.PokeInfo;
import pokemon.PokeInfoI;

import static field.Weather.changeWeather;
import static field.Weather.initWeather;
import static move.MoveI.init;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class WeatherTest {
    @Test
    @DisplayName("天候が上書きできること")
    public void test1() throws InterruptedException {
        // 初期化 天候なし
        Weather weather = initWeather();
        assertEquals(WeatherEnum.NONE, weather.val());

        // 天候なし -> ひでり
        weather = changeWeather(weather, WeatherEnum.DROUGHT);
        assertEquals(WeatherEnum.DROUGHT, weather.val());

        // ひでり -> あめ
        weather = changeWeather(weather, WeatherEnum.RAIN);
        assertEquals(WeatherEnum.RAIN, weather.val());
    }

    @Test
    @DisplayName("変更する天候がもとの天候と同じ場合上書きできないこと")
    public void test2() throws InterruptedException {
        // 初期化 天候なし
        Weather weather = initWeather();
        assertEquals(WeatherEnum.NONE, weather.val());

        // 天候なし -> ひでり
        weather = changeWeather(weather, WeatherEnum.DROUGHT);
        assertEquals(WeatherEnum.DROUGHT, weather.val());

        // 1ターン経過される
        weather = weather.elapsingTurnWeather();

        // ひでり -> ひでり
        weather = changeWeather(weather, WeatherEnum.DROUGHT);
        assertEquals(1, weather.elapsedTurn());
    }

    @Test
    @DisplayName("5ターン経過後にもとの天候にもどること")
    public void test3() throws InterruptedException {
        // 初期化 天候なし
        Weather weather = initWeather();
        assertEquals(WeatherEnum.NONE, weather.val());

        // 天候なし -> ひでり
        weather = changeWeather(weather, WeatherEnum.DROUGHT);
        assertEquals(WeatherEnum.DROUGHT, weather.val());

        // 1ターン経過される
        weather = weather.elapsingTurnWeather();
        weather = weather.elapsingTurnWeather();
        weather = weather.elapsingTurnWeather();
        weather = weather.elapsingTurnWeather();
        weather = weather.elapsingTurnWeather();

        // ひでり -> 天候なし
        assertEquals(WeatherEnum.NONE, weather.val());
    }

    @Test
    @DisplayName("晴れのとき炎技が1.5倍、水技が0.5倍になること")
    public void test4() throws InterruptedException {
        Weather weather = initWeather();
        weather = changeWeather(weather, WeatherEnum.DROUGHT);

        double result1 = weather.dmgRateByWeather(init(BaseMvPrm.FLAMETHROWER));
        double result2 = weather.dmgRateByWeather(init(BaseMvPrm.WATER_PULSE));

        assertEquals(1.5, result1);
        assertEquals(0.5, result2);
    }

    @Test
    @DisplayName("雨のとき炎技が0.5倍、水技が1.5倍になること")
    public void test5() throws InterruptedException {
        Weather weather = initWeather();
        weather = changeWeather(weather, WeatherEnum.RAIN);

        double result1 = weather.dmgRateByWeather(init(BaseMvPrm.FLAMETHROWER));
        double result2 = weather.dmgRateByWeather(init(BaseMvPrm.WATER_PULSE));

        assertEquals(0.5, result1);
        assertEquals(1.5, result2);
    }

    @Test
    @DisplayName("砂嵐のときに岩タイプの特防が1.5倍になること")
    public void test6() throws InterruptedException {
        PokeInfo myPoke1 = PokeInfoI.init(BasePrm.RHYDON);
        PokeInfo myPoke2 = PokeInfoI.init(BasePrm.CHARIZARD);

        Weather weather = initWeather();
        double result1 = weather.dfcRateBySandStorm(myPoke1);
        double result2 = weather.dfcRateBySandStorm(myPoke2);
        assertEquals(1.0, result1);
        assertEquals(1.0, result2);

        weather = changeWeather(weather, WeatherEnum.SANDSTORM);
        double result3 = weather.dfcRateBySandStorm(myPoke1);
        double result4 = weather.dfcRateBySandStorm(myPoke2);
        assertEquals(1.5, result3);
        assertEquals(1.0, result4);
    }

    @Test
    @DisplayName("晴れになったときにcountForRecoveryが5、elapsedTurnが0であること")
    public void test7() throws InterruptedException {
        Weather weather = initWeather();
        weather = changeWeather(weather, WeatherEnum.DROUGHT);

        assertEquals(0, weather.elapsedTurn());
        assertEquals(5, weather.countRecovery());
    }

    @Test
    @DisplayName("岩、地面、鋼タイプはすなあらしダメージを受けないこと")
    public void test8() throws InterruptedException {
        Weather weather = initWeather();
        weather = changeWeather(weather, WeatherEnum.SANDSTORM);

        PokeInfo rockPk = PokeInfoI.init(BasePrm.RHYDON);
        PokeInfo groundPk = PokeInfoI.init(BasePrm.DUGTRIO);
        PokeInfo steelPk = PokeInfoI.init(BasePrm.SCIZOR);
        PokeInfo firePk = PokeInfoI.init(BasePrm.CHARIZARD);

        rockPk = weather.slipDmgByWeather(rockPk);
        groundPk = weather.slipDmgByWeather(groundPk);
        steelPk = weather.slipDmgByWeather(steelPk);
        firePk = weather.slipDmgByWeather(firePk);

        assertEquals(1, (rockPk.currentHP().val() / rockPk.realHP()));
        assertEquals(1, (groundPk.currentHP().val() / groundPk.realHP()));
        assertEquals(1, (steelPk.currentHP().val() / steelPk.realHP()));
        assertNotEquals(1, (firePk.currentHP().val() / firePk.realHP()));
    }

    @Test
    @DisplayName("氷タイプはあられダメージを受けないこと")
    public void test9() throws InterruptedException {
        Weather weather = initWeather();
        weather = changeWeather(weather, WeatherEnum.HAIL);

        PokeInfo icePk = PokeInfoI.init(BasePrm.LAPRAS);
        PokeInfo firePk = PokeInfoI.init(BasePrm.CHARIZARD);

        icePk = weather.slipDmgByWeather(icePk);
        firePk = weather.slipDmgByWeather(firePk);

        assertEquals(1, (icePk.currentHP().val() / icePk.realHP()));
        assertNotEquals(1, (firePk.currentHP().val() / firePk.realHP()));
    }
}
