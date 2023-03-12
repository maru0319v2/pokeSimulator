import Enum.MoveSpecies;
import field.LightScreen;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LightScreenTest {
    @Test
    @DisplayName("初期化後メンバ変数が期待通りであること")
    public void test1() {
        LightScreen rightScreen = LightScreen.initLightScreen();
        assertFalse(rightScreen.val());
        assertEquals(0, rightScreen.elapsedTurn());
        assertEquals(0, rightScreen.countRecovery());
    }

    @Test
    @DisplayName("ひかりのかべが有効になること")
    public void test2() throws InterruptedException {
        LightScreen rightScreen = LightScreen.initLightScreen();
        rightScreen = LightScreen.enableLightScreen(rightScreen);
        assertTrue(rightScreen.val());
        assertEquals(0, rightScreen.elapsedTurn());
        assertEquals(5, rightScreen.countRecovery());
    }

    @Test
    @DisplayName("5ターン後にひかりのかべが無効になること")
    public void test3() throws InterruptedException {
        LightScreen rightScreen = LightScreen.initLightScreen();
        rightScreen = LightScreen.enableLightScreen(rightScreen);

        rightScreen = rightScreen.elapsingTurn();
        rightScreen = rightScreen.elapsingTurn();
        rightScreen = rightScreen.elapsingTurn();
        rightScreen = rightScreen.elapsingTurn();
        rightScreen = rightScreen.elapsingTurn();

        assertFalse(rightScreen.val());
    }

    @Test
    @DisplayName("物理、特殊攻撃を受けたときにダメージ倍率が正しいこと")
    public void test4() throws InterruptedException {
        LightScreen rightScreen = LightScreen.initLightScreen();

        // ひかりのかべがない状態の場合
        assertEquals(1.0, rightScreen.dmgRateByLightScreen(MoveSpecies.PHYSICAL));
        assertEquals(1.0, rightScreen.dmgRateByLightScreen(MoveSpecies.SPECIAL));

        rightScreen = LightScreen.enableLightScreen(rightScreen);

        // ひかりのかべがある状態の場合
        assertEquals(1.0, rightScreen.dmgRateByLightScreen(MoveSpecies.PHYSICAL));
        assertEquals(0.5, rightScreen.dmgRateByLightScreen(MoveSpecies.SPECIAL));
    }
}
