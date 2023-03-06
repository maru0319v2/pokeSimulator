import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pokemon.BasePrm;
import pokemon.PokeInfo;
import pokemon.PokeInfoImpl;
import pokemonStatus.StatusRank;
import pokemonStatus.impl.StatusRankImpl;

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
        int h = sr.getHitRate();
        int av = sr.getAvoidRate();
        assertEquals(0, a);
        assertEquals(0, b);
        assertEquals(0, c);
        assertEquals(0, d);
        assertEquals(0, s);
        assertEquals(0, h);
        assertEquals(0, av);
    }

    @Test
    @DisplayName("コンストラクタを呼んだときに引数で渡した通りのステータスランクが取得できること")
    public void testStatusRank2() {
        StatusRank sr = new StatusRankImpl(1, 2, 3, 4, 5, 6, -4);
        int a = sr.getAttack();
        int b = sr.getBlock();
        int c = sr.getContact();
        int d = sr.getDefense();
        int s = sr.getSpeed();
        int h = sr.getHitRate();
        int av = sr.getAvoidRate();
        assertEquals(1, a);
        assertEquals(2, b);
        assertEquals(3, c);
        assertEquals(4, d);
        assertEquals(5, s);
        assertEquals(6, h);
        assertEquals(-4, av);
    }

    @Test
    @DisplayName("ステータスランクが正しく変化していること")
    public void testStatusRank3() {
        StatusRank sr = new StatusRankImpl();
        sr = sr.add(0, -2, -9, 2, 9, -3, 3);
        int a = sr.getAttack();
        int b = sr.getBlock();
        int c = sr.getContact();
        int d = sr.getDefense();
        int s = sr.getSpeed();
        int h = sr.getHitRate();
        int av = sr.getAvoidRate();

        assertEquals(0, a);
        assertEquals(-2, b);
        assertEquals(-6, c);
        assertEquals(2, d);
        assertEquals(6, s);
        assertEquals(-3, h);
        assertEquals(3, av);
    }

    @Test
    @DisplayName("ステータスランクが正しくリセットされること")
    public void testStatusRank4() {
        StatusRank sr = new StatusRankImpl(1, 2, 3, 4, 5, 6, -6);
        sr = sr.reset();
        int a = sr.getAttack();
        int b = sr.getBlock();
        int c = sr.getContact();
        int d = sr.getDefense();
        int s = sr.getSpeed();
        int h = sr.getHitRate();
        int av = sr.getAvoidRate();
        assertEquals(0, a);
        assertEquals(0, b);
        assertEquals(0, c);
        assertEquals(0, d);
        assertEquals(0, s);
        assertEquals(0, h);
        assertEquals(0, av);
    }

    @Test
    @DisplayName("適切なランク補正されたステータスが返ること")
    public void testStatusRank5() {
        // https://wiki.xn--rckteqa2e.com/wiki/%E3%83%A9%E3%83%B3%E3%82%AF%E8%A3%9C%E6%AD%A3
        StatusRank sr1 = new StatusRankImpl(-7, -6, -5, -4, -3, 0, 0);
        StatusRank sr2 = new StatusRankImpl(-2, -1, 0, 1, 2, 0, 0);
        StatusRank sr3 = new StatusRankImpl(3, 4, 5, 6, 7, 0, 0);

        // ランク補正計算後、小数点は切り捨てられる
        assertEquals(25, (int) (100 * sr1.attackRateByStatusRank()));
        assertEquals(25, (int) (100 * sr1.blockRateByStatusRank()));
        assertEquals(28, (int) (100 * sr1.contactRateByStatusRank()));
        assertEquals(33, (int) (100 * sr1.defenseRateByStatusRank()));
        assertEquals(40, (int) (100 * sr1.speedRateByStatusRank()));
        assertEquals(50, (int) (100 * sr2.attackRateByStatusRank()));
        assertEquals(66, (int) (100 * sr2.blockRateByStatusRank()));
        assertEquals(100, (int) (100 * sr2.contactRateByStatusRank()));
        assertEquals(150, (int) (100 * sr2.defenseRateByStatusRank()));
        assertEquals(200, (int) (100 * sr2.speedRateByStatusRank()));
        assertEquals(250, (int) (100 * sr3.attackRateByStatusRank()));
        assertEquals(300, (int) (100 * sr3.blockRateByStatusRank()));
        assertEquals(350, (int) (100 * sr3.contactRateByStatusRank()));
        assertEquals(400, (int) (100 * sr3.defenseRateByStatusRank()));
        assertEquals(400, (int) (100 * sr3.speedRateByStatusRank()));
    }

    @Test
    @DisplayName("適切な命中率補正が返ること")
    // https://wiki.xn--rckteqa2e.com/wiki/%E5%91%BD%E4%B8%AD
    public void testStatusRank6() {
        StatusRank mySr1 = new StatusRankImpl(0, 0, 0, 0, 0, 0, 0);
        StatusRank mySr2 = new StatusRankImpl(0, 0, 0, 0, 0, 3, 0);
        PokeInfo enemyPoke1 = new PokeInfoImpl(BasePrm.CHARMANDER);
        PokeInfo enemyPoke2 = new PokeInfoImpl(BasePrm.CHARMANDER);
        enemyPoke2 = enemyPoke2.withAddedStatusRank(0, 0, 0, 0, 0, 0, 5);

        assertEquals(1.0, mySr1.hitRateByStatusRank(enemyPoke1));
        assertEquals(2.0, mySr2.hitRateByStatusRank(enemyPoke1));
        assertEquals(0.6, mySr2.hitRateByStatusRank(enemyPoke2));
    }
}
