
import bussinessLogic.StatusRank;
import impl.StatusRankImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatusRankTest {
    @Test
    @DisplayName("引数なしコンストラクタを呼んだときにすべてのステータスランクが0になること")
    public void testStatusRank1() {
        StatusRank sr = new StatusRankImpl();
        int a = sr.attack();
        int b = sr.block();
        int c = sr.contact();
        int d = sr.defense();
        int s = sr.speed();
        assertEquals(a,0);
        assertEquals(b,0);
        assertEquals(c,0);
        assertEquals(d,0);
        assertEquals(s,0);
    }

    @Test
    @DisplayName("コンストラクタを呼んだときに引数で渡した通りのステータスランクが取得できること")
    public void testStatusRank2() {
        StatusRank sr = new StatusRankImpl(1, 2, 3, 4, 5);
        int a = sr.attack();
        int b = sr.block();
        int c = sr.contact();
        int d = sr.defense();
        int s = sr.speed();
        assertEquals(a,1);
        assertEquals(b,2);
        assertEquals(c,3);
        assertEquals(d,4);
        assertEquals(s,5);
    }

    @Test
    @DisplayName("ステータスランクが正しく変化していること")
    public void testStatusRank3() {
        StatusRank sr = new StatusRankImpl();
        sr = sr.add(0, -2, -9, 2, 9);
        int a = sr.attack();
        int b = sr.block();
        int c = sr.contact();
        int d = sr.defense();
        int s = sr.speed();
        assertEquals(a,0);
        assertEquals(b,-2);
        assertEquals(c,-6);
        assertEquals(d,2);
        assertEquals(s,6);
    }

    @Test
    @DisplayName("ステータスランクが正しくリセットされること")
    public void testStatusRank4() {
        StatusRank sr = new StatusRankImpl(1, 2, 3, 4, 5);
        sr = sr.reset();
        int a = sr.attack();
        int b = sr.block();
        int c = sr.contact();
        int d = sr.defense();
        int s = sr.speed();
        assertEquals(a,0);
        assertEquals(b,0);
        assertEquals(c,0);
        assertEquals(d,0);
        assertEquals(s,0);
    }

    @Test
    @DisplayName("適切なランク補正されたステータスが返ること")
    public void testStatusRank5() {
        // https://wiki.xn--rckteqa2e.com/wiki/%E3%83%A9%E3%83%B3%E3%82%AF%E8%A3%9C%E6%AD%A3
        StatusRank sr1 = new StatusRankImpl(-7, -6, -5, -4, -3);
        StatusRank sr2 = new StatusRankImpl(-2, -1, 0, 1, 2);
        StatusRank sr3 = new StatusRankImpl(3, 4, 5, 6, 7);

        // ランク補正計算後、小数点は切り捨てられる
        assertEquals((int)(100 * sr1.attackRateByStatusRank()),25);
        assertEquals((int)(100 * sr1.blockRateByStatusRank()),25);
        assertEquals((int)(100 * sr1.contactRateByStatusRank()),28);
        assertEquals((int)(100 * sr1.defenseRateByStatusRank()),33);
        assertEquals((int)(100 * sr1.speedRateByStatusRank()),40);
        assertEquals((int)(100 * sr2.attackRateByStatusRank()),50);
        assertEquals((int)(100 * sr2.blockRateByStatusRank()),66);
        assertEquals((int)(100 * sr2.contactRateByStatusRank()),100);
        assertEquals((int)(100 * sr2.defenseRateByStatusRank()),150);
        assertEquals((int)(100 * sr2.speedRateByStatusRank()),200);
        assertEquals((int)(100 * sr3.attackRateByStatusRank()),250);
        assertEquals((int)(100 * sr3.blockRateByStatusRank()),300);
        assertEquals((int)(100 * sr3.contactRateByStatusRank()),350);
        assertEquals((int)(100 * sr3.defenseRateByStatusRank()),400);
        assertEquals((int)(100 * sr3.speedRateByStatusRank()),400);
    }
}
