package pokemon;

import Enum.Gender;
import Enum.Item;
import Enum.Nature;
import move.Move;
import pokemonStatus.*;
import pokemonStatus.impl.*;
import statusAilment.Ailment;
import statusAilment.AilmentEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static bussinessLogic.ConsoleOutManager.showMessageParChar;
import static pokemonStatus.impl.ConfusionI.initConfusion;
import static pokemonStatus.impl.EffortValueI.initEffortValue;
import static pokemonStatus.impl.FlinchI.initFlinch;
import static pokemonStatus.impl.IndividualValueI.initIndividualValue;
import static pokemonStatus.impl.StatusRankI.initStatusRank;
import static statusAilment.AilmentI.changeAilment;
import static statusAilment.AilmentI.initAilment;

public class PokeInfoI implements PokeInfo {
    private final BasePrm basePrm;
    private final IndividualValue individualValue;
    private final EffortValue effortValue;
    private final Level level;
    private final Gender gender;
    private final Nature nature;
    private final List<Move> haveMove;
    private final Experience experience;
    private final CurrentHP currentHP;
    private final StatusRank statusRank;
    private final Ailment ailment;
    private final Flinch flinch;
    private final Confusion confusion;
    private final Item item;

    public BasePrm basePrm() {
        return this.basePrm;
    }

    public IndividualValue individualValue() {
        return this.individualValue;
    }

    public EffortValue effortValue() {
        return this.effortValue;
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

    public Experience experience() {
        return this.experience;
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
    public int giveExp() {
        return this.level().val() * this.basePrm().basicExp() / 7;
    }

    @Override
    public PokeInfo addExp(int exp) throws InterruptedException {
        PokeInfo result = this.withExp(exp);
        showMessageParChar(result.basePrm().pName() + "は" + exp + "の経験値を獲得!");
        while (result.experience().isLevelUp(result)) {
            result = result.withLevel(1);
            showMessageParChar(result.basePrm().pName() + "はLv." + result.level().val() + "にレベルアップした!");
        }
        return result;
    }

    @Override
    public PokeInfo recoveryHP(int value) throws InterruptedException {
        PokeInfo result = this.withCurrentHP(this.currentHP().recovery(this, new CurrentHPI(value)));
        System.out.println(result.basePrm().pName() + "は体力を" + value + "回復!  HP" + result.currentHP().val() + "/" + result.realHP());
        System.out.println();
        return result.withAilment(changeAilment(result, AilmentEnum.FINE));
    }

    @Override
    public PokeInfo damage(int value) throws InterruptedException {
        PokeInfo result = this.withCurrentHP(this.currentHP().damage(new CurrentHPI(value)));
        showMessageParChar(result.basePrm().pName() + "は" + value + "のダメージ!");
        if (result.currentHP().isDead()) {
            return result.withAilment(changeAilment(result, AilmentEnum.FAINTING));
        }
        return result;
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
        PokeInfo result = this.withMove(recoveredPPMove);

        return result;
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

    public static PokeInfo initialize(BasePrm basePrm) {
        return new PokeInfoI(basePrm);
    }

    private PokeInfoI(BasePrm basePrm) {
        this.basePrm = basePrm;
        this.gender = Gender.initGender();
        this.nature = Nature.initNature();
        this.individualValue = initIndividualValue();
        this.effortValue = initEffortValue();
        this.level = new LevelI(5);
        this.haveMove = basePrm.initialMove();
        this.experience = new ExperienceI(135); // TODO 固定化したくない
        this.currentHP = new CurrentHPI(realHP());
        this.statusRank = initStatusRank();
        this.ailment = initAilment();
        this.flinch = initFlinch();
        this.confusion = initConfusion();
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
        this.experience = new ExperienceI(0);
        this.currentHP = new CurrentHPI(realHP());
        this.statusRank = initStatusRank();
        this.ailment = initAilment();
        this.flinch = initFlinch();
        this.confusion = initConfusion();
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
            Experience exp,
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
        this.experience = new ExperienceI(exp.totalExp());
        this.currentHP = new CurrentHPI(currentHP.val());
        this.statusRank = new StatusRankI(sr.attack(), sr.block(), sr.contact(), sr.defence(), sr.speed(), sr.hitRate(), sr.avoidRate());
        this.ailment = ailment;
        this.flinch = flinch;
        this.confusion = confusion;
        this.item = item;
    }

    @Override
    public PokeInfo withCurrentHP(CurrentHP currentHP) {
        return new PokeInfoI(this.basePrm, this.gender, this.nature, this.individualValue, this.effortValue, this.level, this.experience, this.haveMove, currentHP, this.statusRank, this.ailment, this.flinch, this.confusion, this.item);
    }

    @Override
    public PokeInfo withExp(int addingExp) {
        return new PokeInfoI(this.basePrm, this.gender, this.nature, this.individualValue, this.effortValue, this.level, this.experience.add(addingExp), this.haveMove, this.currentHP, this.statusRank, this.ailment, this.flinch, this.confusion, this.item);
    }

    @Override
    public PokeInfo withLevel(int addLevel) {
        return new PokeInfoI(this.basePrm, this.gender, this.nature, this.individualValue, this.effortValue, this.level.add(addLevel), this.experience, this.haveMove, this.currentHP, this.statusRank, this.ailment, this.flinch, this.confusion, this.item);
    }

    @Override
    public PokeInfo withChStatusRank(int attack, int block, int contact, int defence, int speed, int hitRate, int avoidRate) throws InterruptedException {
        return new PokeInfoI(this.basePrm, this.gender, this.nature, this.individualValue, this.effortValue, this.level, this.experience, this.haveMove, this.currentHP, this.statusRank.change(this.basePrm.pName(), attack, block, contact, defence, speed, hitRate, avoidRate), this.ailment, this.flinch, this.confusion, this.item);
    }

    @Override
    public PokeInfo withResetStatusRank() {
        return new PokeInfoI(this.basePrm, this.gender, this.nature, this.individualValue, this.effortValue, this.level, this.experience, this.haveMove, this.currentHP, this.statusRank.reset(), this.ailment, this.flinch, this.confusion, this.item);
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
        return new PokeInfoI(this.basePrm, this.gender, this.nature, this.individualValue, this.effortValue, this.level, this.experience, newMoves, this.currentHP, this.statusRank, this.ailment, this.flinch, this.confusion, this.item);
    }

    @Override
    public PokeInfo withAilment(Ailment statusAilment) {
        return new PokeInfoI(this.basePrm, this.gender, this.nature, this.individualValue, this.effortValue, this.level, this.experience, this.haveMove, this.currentHP, this.statusRank, statusAilment, this.flinch, this.confusion, this.item);
    }

    @Override
    public PokeInfo withFlinch(Flinch flinch) {
        return new PokeInfoI(this.basePrm, this.gender, this.nature, this.individualValue, this.effortValue, this.level, this.experience, this.haveMove, this.currentHP, this.statusRank, this.ailment, flinch, this.confusion, this.item);
    }

    @Override
    public PokeInfo withConfusion(Confusion confusion) {
        return new PokeInfoI(this.basePrm, this.gender, this.nature, this.individualValue, this.effortValue, this.level, this.experience, this.haveMove, this.currentHP, this.statusRank, this.ailment, this.flinch, confusion, this.item);
    }

    @Override
    public PokeInfo withItem(Item item) {
        return new PokeInfoI(this.basePrm, this.gender, this.nature, this.individualValue, this.effortValue, this.level, this.experience, this.haveMove, this.currentHP, this.statusRank, this.ailment, this.flinch, confusion, item);
    }
}
