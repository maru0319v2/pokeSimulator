package pokemon;

import Enum.Gender;
import Enum.Item;
import Enum.Nature;
import move.BaseMvPrm;
import move.Move;
import move.MoveI;
import pokemonStatus.*;
import pokemonStatus.impl.*;
import statusAilment.Ailment;
import statusAilment.AilmentEnum;
import statusAilment.AilmentI;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static bussinessLogic.ConsoleOutManager.showMessageParChar;

public class PokeInfoI implements PokeInfo {
    private final BasePrm basePrm;
    private final IndividualValue individualValue;
    private final EffortValue effortValue;
    private final Level level;
    private final Gender gender;
    private final Nature nature;
    private final List<Move> haveMove;
    private final CurrentHP currentHP;
    private final StatusRank statusRank;
    private final Ailment ailment;
    private final Flinch flinch;
    private final Confusion confusion;
    private final Item item;

    public BasePrm basePrm() {
        return this.basePrm;
    }

    public Level level() {
        return this.level;
    }

    public Gender gender() {
        return this.gender;
    }

    public Nature nature() {
        return this.nature;
    }

    public List<Move> haveMove() {
        return this.haveMove;
    }

    public CurrentHP currentHP() {
        return this.currentHP;
    }

    public StatusRank statusRank() {
        return this.statusRank;
    }

    public Ailment ailment() {
        return this.ailment;
    }

    public Flinch flinch() {
        return this.flinch;
    }

    public Confusion confusion() {
        return this.confusion;
    }

    public Item item() {
        return this.item;
    }

    public int realHP() {
        return ((this.basePrm.hp() * 2 + this.individualValue.hp() + (this.effortValue.hp() / 4)) * this.level.val() / 100) + 10 + this.level.val();
    }

    public int realAtk() {
        return (int) ((((this.basePrm.attack() * 2 + this.individualValue.attack() + (this.effortValue.attack() / 4)) * this.level.val() / 100) + 5) * this.nature.attackRate());
    }

    public int realBlk() {
        return (int) ((((this.basePrm.block() * 2 + this.individualValue.block() + (this.effortValue.block() / 4)) * this.level.val() / 100) + 5) * this.nature.blockRate());
    }

    public int realCnt() {
        return (int) ((((this.basePrm.contact() * 2 + this.individualValue.contact() + (this.effortValue.contact() / 4)) * this.level.val() / 100) + 5) * this.nature.contactRate());
    }

    public int realDfc() {
        return (int) ((((this.basePrm.defence() * 2 + this.individualValue.defence() + (this.effortValue.defence() / 4)) * this.level.val() / 100) + 5) * this.nature.defenceRate());
    }

    public int realSpd() {
        return (int) ((((this.basePrm.speed() * 2 + this.individualValue.speed() + (this.effortValue.speed() / 4)) * this.level.val() / 100) + 5) * this.nature.speedRate());
    }

    @Override
    public PokeInfo recoveryHP(int value) throws InterruptedException {
        PokeInfo result = this.withCurrentHP(this.currentHP().recovery(this, new CurrentHPI(value)));
        if (result.ailment().val() == AilmentEnum.FAINTING) {
            return result.withAilment(AilmentI.changeAilment(result, AilmentEnum.FINE));
        }
        return result;
    }

    @Override
    public PokeInfo damage(int value) throws InterruptedException {
        PokeInfo result = this.withCurrentHP(this.currentHP().damage(new CurrentHPI(value)));
        showMessageParChar(result.basePrm().pName() + "は" + value + "のダメージ!");
        if (result.currentHP().isDead()) {
            return result.withAilment(AilmentI.changeAilment(result, AilmentEnum.FAINTING));
        }
        return Item.afterDamaged(result);
    }

    @Override
    public PokeInfo recoveryPP(Move move, int value) {
        Move targetMoves = null;
        List<Move> haveMoves = this.haveMove();
        for (Move haveMove : haveMoves) {
            if (move.getClass() == haveMove.getClass()) {
                targetMoves = haveMove;
            }
        }
        CurrentPP recoveredPP = targetMoves.currentPP().recovery(targetMoves, new CurrentPPI(value));
        Move recoveredPPMove = targetMoves.withCurrentPP(targetMoves, recoveredPP);
        return this.withMove(recoveredPPMove);
    }

    @Override
    public PokeInfo recoveryAll() throws InterruptedException {
        PokeInfo result = this.recoveryHP(999);

        for (Move move : result.haveMove()) {
            result = result.recoveryPP(move, 99);
        }
        return result;
    }

    @Override
    public PokeInfo decrementPP(Move usedMove) {
        CurrentPP decrementedPowerPoint = usedMove.currentPP().decrement(new CurrentPPI(1));
        Move decrementedPPMove = usedMove.withCurrentPP(usedMove, decrementedPowerPoint);
        return this.withMove(decrementedPPMove);
    }

    public static PokeInfo init(BasePrm basePrm) {
        return new PokeInfoI(basePrm);
    }

    private PokeInfoI(BasePrm basePrm) {
        this.basePrm = basePrm;
        this.gender = Gender.init();
        this.nature = Nature.init();
        this.individualValue = IndividualValueI.init();
        this.effortValue = EffortValueI.init();
        this.level = new LevelI(5);
        this.haveMove = List.of(MoveI.init(BaseMvPrm.TACKLE));
        this.currentHP = new CurrentHPI(realHP());
        this.statusRank = StatusRankI.init();
        this.ailment = AilmentI.init();
        this.flinch = FlinchI.init();
        this.confusion = ConfusionI.init();
        this.item = Item.NONE;
    }

    // 指定パラメータポケモンインスタンスを作成する。
    public PokeInfoI(
            BasePrm basePrm,
            Gender gender,
            Nature nature,
            IndividualValue iv,
            EffortValue ev,
            Level level,
            List<Move> haveMove,
            Item item
    ) {
        this.basePrm = basePrm;
        this.gender = gender;
        this.nature = nature;
        this.individualValue = new IndividualValueI(iv.hp(), iv.attack(), iv.block(), iv.contact(), iv.defence(), iv.speed());
        this.effortValue = new EffortValueI(ev.hp(), ev.attack(), ev.block(), ev.contact(), ev.defence(), ev.speed());
        this.level = new LevelI(level.val());
        this.haveMove = haveMove;
        this.currentHP = new CurrentHPI(realHP());
        this.statusRank = StatusRankI.init();
        this.ailment = AilmentI.init();
        this.flinch = FlinchI.init();
        this.confusion = ConfusionI.init();
        this.item = item;
        // TODO　Builderパターンで作る
    }

    private PokeInfoI(
            BasePrm basePrm,
            Gender gender,
            Nature nature,
            IndividualValue iv,
            EffortValue ev,
            Level level,
            List<Move> haveMove,
            CurrentHP currentHP,
            StatusRank sr,
            Ailment ailment,
            Flinch flinch,
            Confusion confusion,
            Item item
    ) {
        this.basePrm = basePrm;
        this.gender = gender;
        this.nature = nature;
        this.individualValue = new IndividualValueI(iv.hp(), iv.attack(), iv.block(), iv.contact(), iv.defence(), iv.speed());
        this.effortValue = new EffortValueI(ev.hp(), ev.attack(), ev.block(), ev.contact(), ev.defence(), ev.speed());
        this.level = new LevelI(level.val());
        this.haveMove = haveMove;
        this.currentHP = new CurrentHPI(currentHP.val());
        this.statusRank = new StatusRankI(sr.attack(), sr.block(), sr.contact(), sr.defence(), sr.speed(), sr.hitRate(), sr.avoidRate());
        this.ailment = ailment;
        this.flinch = flinch;
        this.confusion = confusion;
        this.item = item;
    }

    @Override
    public PokeInfo withCurrentHP(CurrentHP currentHP) {
        return new PokeInfoI(this.basePrm, this.gender, this.nature, this.individualValue, this.effortValue, this.level, this.haveMove, currentHP, this.statusRank, this.ailment, this.flinch, this.confusion, this.item);
    }

    @Override
    public PokeInfo withChStatusRank(int attack, int block, int contact, int defence, int speed, int hitRate, int avoidRate) throws InterruptedException {
        return new PokeInfoI(this.basePrm, this.gender, this.nature, this.individualValue, this.effortValue, this.level, this.haveMove, this.currentHP, this.statusRank.change(this.basePrm.pName(), attack, block, contact, defence, speed, hitRate, avoidRate), this.ailment, this.flinch, this.confusion, this.item);
    }

    @Override
    public PokeInfo withResetStatusRank() {
        return new PokeInfoI(this.basePrm, this.gender, this.nature, this.individualValue, this.effortValue, this.level, this.haveMove, this.currentHP, this.statusRank.reset(), this.ailment, this.flinch, this.confusion, this.item);
    }

    @Override
    public PokeInfo withMove(Move move) {
        List<Move> newMoves = new ArrayList<>(4);
        for (Move haveMove : this.haveMove) {
            if (Objects.equals(move.baseMPrm().mvName(), haveMove.baseMPrm().mvName())) {
                newMoves.add(move);
            } else {
                newMoves.add(haveMove);
            }
        }
        return new PokeInfoI(this.basePrm, this.gender, this.nature, this.individualValue, this.effortValue, this.level, newMoves, this.currentHP, this.statusRank, this.ailment, this.flinch, this.confusion, this.item);
    }

    @Override
    public PokeInfo withAilment(Ailment statusAilment) {
        return new PokeInfoI(this.basePrm, this.gender, this.nature, this.individualValue, this.effortValue, this.level, this.haveMove, this.currentHP, this.statusRank, statusAilment, this.flinch, this.confusion, this.item);
    }

    @Override
    public PokeInfo withFlinch(Flinch flinch) {
        return new PokeInfoI(this.basePrm, this.gender, this.nature, this.individualValue, this.effortValue, this.level, this.haveMove, this.currentHP, this.statusRank, this.ailment, flinch, this.confusion, this.item);
    }

    @Override
    public PokeInfo withConfusion(Confusion confusion) {
        return new PokeInfoI(this.basePrm, this.gender, this.nature, this.individualValue, this.effortValue, this.level, this.haveMove, this.currentHP, this.statusRank, this.ailment, this.flinch, confusion, this.item);
    }

    @Override
    public PokeInfo withItem(Item item) {
        return new PokeInfoI(this.basePrm, this.gender, this.nature, this.individualValue, this.effortValue, this.level, this.haveMove, this.currentHP, this.statusRank, this.ailment, this.flinch, confusion, item);
    }
}
