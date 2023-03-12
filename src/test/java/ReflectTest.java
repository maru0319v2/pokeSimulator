import Enum.MoveSpecies;
import field.Reflect;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReflectTest {
    @Test
    @DisplayName("初期化後メンバ変数が期待通りであること")
    public void test1() {
        Reflect reflect = Reflect.initReflect();
        assertFalse(reflect.val());
        assertEquals(0, reflect.elapsedTurn());
        assertEquals(0, reflect.countRecovery());
    }

    @Test
    @DisplayName("リフレクターが有効になること")
    public void test2() throws InterruptedException {
        Reflect reflect = Reflect.initReflect();
        reflect = Reflect.enableReflect(reflect);
        assertTrue(reflect.val());
        assertEquals(0, reflect.elapsedTurn());
        assertEquals(5, reflect.countRecovery());
    }

    @Test
    @DisplayName("5ターン後にリフレクターが無効になること")
    public void test3() throws InterruptedException {
        Reflect reflect = Reflect.initReflect();
        reflect = Reflect.enableReflect(reflect);

        reflect = reflect.elapsingTurn();
        reflect = reflect.elapsingTurn();
        reflect = reflect.elapsingTurn();
        reflect = reflect.elapsingTurn();
        reflect = reflect.elapsingTurn();

        assertFalse(reflect.val());
    }

    @Test
    @DisplayName("物理、特殊攻撃を受けたときにダメージ倍率が正しいこと")
    public void test4() throws InterruptedException {
        Reflect reflect = Reflect.initReflect();

        // リフレクターがない状態の場合
        assertEquals(1.0, reflect.dmgRateByReflect(MoveSpecies.PHYSICAL));
        assertEquals(1.0, reflect.dmgRateByReflect(MoveSpecies.SPECIAL));

        reflect = Reflect.enableReflect(reflect);

        // リフレクターがある状態の場合
        assertEquals(0.5, reflect.dmgRateByReflect(MoveSpecies.PHYSICAL));
        assertEquals(1.0, reflect.dmgRateByReflect(MoveSpecies.SPECIAL));
    }
}
