
import pokemonStatus.StatusRank;
import pokemonStatus.impl.StatusRankImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatusRankTest {
    @Test
    @DisplayName("引数なしコンストラクタを呼んだときにすべてのステータスランクが0になること")
    public void testStatusRank1() {
        StatusRank sr = new StatusRankImpl();
        int a = sr.getAttack();
        int b = sr.getBlock();
        int c = sr.getContact();
        int d = sr.getDefense();
        int s = sr.getSpeed();
        assertEquals(0, a);
        assertEquals(0, b);
        assertEquals(0, c);
        assertEquals(0, d);
        assertEquals(0, s);
    }

    @Test
    @DisplayName("コンストラクタを呼んだときに引数で渡した通りのステータスランクが取得できること")
    public void testStatusRank2() {
        StatusRank sr = new StatusRankImpl(1, 2, 3, 4, 5);
        int a = sr.getAttack();
        int b = sr.getBlock();
        int c = sr.getContact();
        int d = sr.getDefense();
        int s = sr.getSpeed();
        assertEquals(1, a);
        assertEquals(2, b);
        assertEquals(3, c);
        assertEquals(4, d);
        assertEquals(5, s);
    }

    @Test
    @DisplayName("ステータスランクが正しく変化していること")
    public void testStatusRank3() {
        StatusRank sr = new StatusRankImpl();
        sr = sr.add(0, -2, -9, 2, 9);
        int a = sr.getAttack();
        int b = sr.getBlock();
        int c = sr.getContact();
        int d = sr.getDefense();
        int s = sr.getSpeed();
        assertEquals(0, a);
        assertEquals(-2, b);
        assertEquals(-6, c);
        assertEquals(2, d);
        assertEquals(6, s);
    }

    @Test
    @DisplayName("ステータスランクが正しくリセットされること")
    public void testStatusRank4() {
        StatusRank sr = new StatusRankImpl(1, 2, 3, 4, 5);
        sr = sr.reset();
        int a = sr.getAttack();
        int b = sr.getBlock();
        int c = sr.getContact();
        int d = sr.getDefense();
        int s = sr.getSpeed();
        assertEquals(0, a);
        assertEquals(0, b);
        assertEquals(0, c);
        assertEquals(0, d);
        assertEquals(0, s);
    }

    @Test
    @DisplayName("適切なランク補正されたステータスが返ること")
    public void testStatusRank5() {
        // https://wiki.xn--rckteqa2e.com/wiki/%E3%83%A9%E3%83%B3%E3%82%AF%E8%A3%9C%E6%AD%A3
        StatusRank sr1 = new StatusRankImpl(-7, -6, -5, -4, -3);
        StatusRank sr2 = new StatusRankImpl(-2, -1, 0, 1, 2);
        StatusRank sr3 = new StatusRankImpl(3, 4, 5, 6, 7);

        // ランク補正計算後、小数点は切り捨てられる
        assertEquals(25, (int)(100 * sr1.attackRateByStatusRank()));
        assertEquals(25, (int)(100 * sr1.blockRateByStatusRank()));
        assertEquals(28, (int)(100 * sr1.contactRateByStatusRank()));
        assertEquals(33, (int)(100 * sr1.defenseRateByStatusRank()));
        assertEquals(40, (int)(100 * sr1.speedRateByStatusRank()));
        assertEquals(50, (int)(100 * sr2.attackRateByStatusRank()));
        assertEquals(66, (int)(100 * sr2.blockRateByStatusRank()));
        assertEquals(100, (int)(100 * sr2.contactRateByStatusRank()));
        assertEquals(150, (int)(100 * sr2.defenseRateByStatusRank()));
        assertEquals(200, (int)(100 * sr2.speedRateByStatusRank()));
        assertEquals(250, (int)(100 * sr3.attackRateByStatusRank()));
        assertEquals(300, (int)(100 * sr3.blockRateByStatusRank()));
        assertEquals(350, (int)(100 * sr3.contactRateByStatusRank()));
        assertEquals(400, (int)(100 * sr3.defenseRateByStatusRank()));
        assertEquals(400, (int)(100 * sr3.speedRateByStatusRank()));
    }
}
