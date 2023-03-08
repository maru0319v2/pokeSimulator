package move;

import Enum.MoveSpecies;
import Enum.Type;
import bussinessLogic.OnBattleField;
import field.Field;
import field.Weather;
import lombok.AllArgsConstructor;
import pokemon.PokeInfo;
import pokemonStatus.impl.CurrentHPI;
import pokemonStatus.impl.FlinchI;
import statusAilment.AilmentE;

import java.util.Random;

import static bussinessLogic.ConsoleOutManager.showMessageParChar;
import static field.FieldI.changeField;
import static pokemonStatus.impl.ConfusionI.beConfusion;
import static statusAilment.AilmentI.changeAilment;

@AllArgsConstructor
public enum BaseMvPrm {
    /**
     * ここから通常攻撃
     */
    TACKLE("たいあたり", Type.NORMAL, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 40, 95, 35, 0, 0,
            true, false, true, false, false, false
    ) {
        @Override
        public OnBattleField effect(PokeInfo attackPoke, PokeInfo defencePoke, Field field, int recoveryHP) {
            return new OnBattleField(attackPoke, defencePoke, field);
        }
    },
    QUICK_ATTACK("でんこうせっか", Type.NORMAL, MoveSpecies.PHYSICAL, DetailMvSpecies.PRIORITY, 40, 100, 30, 0, 1,
            true, false, true, false, false, false
    ) {
        @Override
        public OnBattleField effect(PokeInfo attackPoke, PokeInfo defencePoke, Field field, int recoveryHP) {
            return new OnBattleField(attackPoke, defencePoke, field);
        }
    },
    AERIAL_ACE("つばめがえし", Type.FLYING, MoveSpecies.PHYSICAL, DetailMvSpecies.HIT, 60, -1, 20, 0, 0,
            true, false, true, false, false, false
    ) {
        @Override
        public OnBattleField effect(PokeInfo attackPoke, PokeInfo defencePoke, Field field, int recoveryHP) {
            return new OnBattleField(attackPoke, defencePoke, field);
        }
    },
    AIR_SLASH("エアスラッシュ", Type.FLYING, MoveSpecies.SPECIAL, DetailMvSpecies.DAMAGE, 75, 95, 15, 0, 0,
            false, false, true, false, false, false
    ) {
        @Override
        public OnBattleField effect(PokeInfo attackPoke, PokeInfo defencePoke, Field field, int recoveryHP) throws InterruptedException {
            if ((new Random().nextInt(10)) <= 2) {
                return new OnBattleField(attackPoke, defencePoke.withFlinch(new FlinchI(true)), field);
            }
            return new OnBattleField(attackPoke, defencePoke, field);
        }
    },
    CLOSE_COMBAT("インファイト", Type.FIGHTING, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 120, 100, 5, 0, 0,
            true, false, true, false, false, false
    ) {
        @Override
        public OnBattleField effect(PokeInfo attackPoke, PokeInfo defencePoke, Field field, int recoveryHP) throws InterruptedException {
            showMessageParChar(attackPoke.basePrm().pName() + "のぼうぎょととくぼうがさがった!");
            return new OnBattleField(attackPoke.withChStatusRank(0, -1, 0, -1, 0, 0, 0), defencePoke, field);
        }
    },
    POWER_UP_PUNCH("グロウパンチ", Type.FIGHTING, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 40, 100, 20, 0, 0,
            true, false, true, false, false, false
    ) {
        @Override
        public OnBattleField effect(PokeInfo attackPoke, PokeInfo defencePoke, Field field, int recoveryHP) throws InterruptedException {
            return new OnBattleField(attackPoke.withChStatusRank(1, 0, 0, 0, 0, 0, 0), defencePoke, field);
        }
    },
    CRESS_CHOP("クロスチョップ", Type.FIGHTING, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 100, 80, 5, 1, 0,
            true, false, true, false, false, false
    ) {
        @Override
        public OnBattleField effect(PokeInfo attackPoke, PokeInfo defencePoke, Field field, int recoveryHP) throws InterruptedException {
            return new OnBattleField(attackPoke, defencePoke, field);
        }
    },
    FLAMETHROWER("かえんほうしゃ", Type.FIRE, MoveSpecies.SPECIAL, DetailMvSpecies.DAMAGE, 90, 100, 15, 0, 0,
            false, false, true, false, false, false
    ) {
        @Override
        public OnBattleField effect(PokeInfo attackPoke, PokeInfo defencePoke, Field field, int recoveryHP) throws InterruptedException {
            if ((new Random().nextInt(10)) == 0) {
                return new OnBattleField(attackPoke, defencePoke.withAilment(changeAilment(defencePoke, AilmentE.BURN)), field);
            }
            return new OnBattleField(attackPoke, defencePoke, field);
        }
    },
    THUNDERBOLT("10まんボルト", Type.ELECTRIC, MoveSpecies.SPECIAL, DetailMvSpecies.DAMAGE, 90, 100, 15, 0, 0,
            false, false, true, false, false, false
    ) {
        @Override
        public OnBattleField effect(PokeInfo attackPoke, PokeInfo defencePoke, Field field, int recoveryHP) throws InterruptedException {
            if ((new Random().nextInt(10)) == 0) {
                return new OnBattleField(attackPoke, defencePoke.withAilment(changeAilment(defencePoke, AilmentE.PARALYSIS)), field);
            }
            return new OnBattleField(attackPoke, defencePoke, field);
        }
    },
    THUNDER("かみなり", Type.ELECTRIC, MoveSpecies.SPECIAL, DetailMvSpecies.DAMAGE, 110, 70, 10, 0, 0,
            false, false, true, false, false, false
    ) {
        @Override
        public OnBattleField effect(PokeInfo attackPoke, PokeInfo defencePoke, Field field, int recoveryHP) throws InterruptedException {
            if ((new Random().nextInt(10)) == 2) {
                return new OnBattleField(attackPoke, defencePoke.withAilment(changeAilment(defencePoke, AilmentE.PARALYSIS)), field);
            }
            return new OnBattleField(attackPoke, defencePoke, field);
        }
    },
    DRAGON_CLAW("ドラゴンクロー", Type.DRAGON, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 80, 100, 15, 0, 0,
            true, false, true, false, false, false
    ) {
        @Override
        public OnBattleField effect(PokeInfo attackPoke, PokeInfo defencePoke, Field field, int recoveryHP) {
            return new OnBattleField(attackPoke, defencePoke, field);
        }
    },
    WATER_PULSE("みずのはどう", Type.WATER, MoveSpecies.SPECIAL, DetailMvSpecies.DAMAGE, 60, 100, 20, 0, 0,
            false, false, true, false, false, false
    ) {
        @Override
        public OnBattleField effect(PokeInfo attackPoke, PokeInfo defencePoke, Field field, int recoveryHP) throws InterruptedException {
            if ((new Random().nextInt(5)) <= 0) {
                return new OnBattleField(attackPoke, defencePoke.withConfusion(beConfusion(defencePoke)), field);
            }
            return new OnBattleField(attackPoke, defencePoke, field);
        }
    },
    ICE_SHARD("こおりのつぶて", Type.ICE, MoveSpecies.PHYSICAL, DetailMvSpecies.PRIORITY, 40, 100, 30, 0, 1,
            false, false, true, false, false, false
    ) {
        @Override
        public OnBattleField effect(PokeInfo attackPoke, PokeInfo defencePoke, Field field, int recoveryHP) throws InterruptedException {
            return new OnBattleField(attackPoke, defencePoke, field);
        }
    },
    VINE_WHIP("つるのムチ", Type.GRASS, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 45, 100, 25, 0, 0,
            true, false, true, false, false, false
    ) {
        @Override
        public OnBattleField effect(PokeInfo attackPoke, PokeInfo defencePoke, Field field, int recoveryHP) {
            return new OnBattleField(attackPoke, defencePoke, field);
        }
    },
    ENERGY_BALL("エナジーボール", Type.GRASS, MoveSpecies.SPECIAL, DetailMvSpecies.DAMAGE, 90, 100, 10, 0, 0,
            false, false, true, false, false, false
    ) {
        @Override
        public OnBattleField effect(PokeInfo attackPoke, PokeInfo defencePoke, Field field, int recoveryHP) throws InterruptedException {
            if ((new Random().nextInt(10)) == 0) {
                return new OnBattleField(attackPoke, defencePoke.withChStatusRank(0, 0, 0, -1, 0, 0, 0), field);
            }
            return new OnBattleField(attackPoke, defencePoke, field);
        }
    },
    GIGA_DRAIN("ギガドレイン", Type.GRASS, MoveSpecies.SPECIAL, DetailMvSpecies.DAMAGE, 75, 100, 10, 0, 0,
            false, false, true, false, false, false
    ) {
        @Override
        public OnBattleField effect(PokeInfo attackPoke, PokeInfo defencePoke, Field field, int recoveryHP) throws InterruptedException {
            int recovery = recoveryHP / 2;
            return new OnBattleField(attackPoke.withCurrentHP(attackPoke.currentHP().recovery(attackPoke, new CurrentHPI(recovery))), defencePoke, field);
        }
    },
    LEECH_LIFE("きゅうけつ", Type.BUG, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 80, 100, 10, 0, 0,
            true, false, true, false, false, false
    ) {
        @Override
        public OnBattleField effect(PokeInfo attackPoke, PokeInfo defencePoke, Field field, int recoveryHP) throws InterruptedException {
            int recovery = recoveryHP / 2;
            return new OnBattleField(attackPoke.withCurrentHP(attackPoke.currentHP().recovery(attackPoke, new CurrentHPI(recovery))), defencePoke, field);
        }
    },
    IRON_TAIL("アイアンテール", Type.STEEL, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 100, 75, 15, 0, 0,
            true, false, true, false, false, false
    ) {
        @Override
        public OnBattleField effect(PokeInfo attackPoke, PokeInfo defencePoke, Field field, int recoveryHP) throws InterruptedException {
            if ((new Random().nextInt(10)) <= 2) {
                return new OnBattleField(attackPoke, defencePoke.withChStatusRank(0, -1, 0, 0, 0, 0, 0), field);
            }
            return new OnBattleField(attackPoke, defencePoke, field);
        }
    },
    ROCK_SLIDE("いわなだれ", Type.ROCK, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 75, 90, 10, 0, 0,
            false, false, true, false, false, false
    ) {
        @Override
        public OnBattleField effect(PokeInfo attackPoke, PokeInfo defencePoke, Field field, int recoveryHP) {
            if ((new Random().nextInt(10)) <= 2) {
                return new OnBattleField(attackPoke, defencePoke.withFlinch(new FlinchI(true)), field);
            }
            return new OnBattleField(attackPoke, defencePoke, field);
        }
    },
    CRUNCH("かみくだく", Type.DARK, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 80, 100, 15, 0, 0,
            true, false, true, false, false, false
    ) {
        @Override
        public OnBattleField effect(PokeInfo attackPoke, PokeInfo defencePoke, Field field, int recoveryHP) {
            if ((new Random().nextInt(10)) <= 1) {
                return new OnBattleField(attackPoke, defencePoke.withChStatusRank(0, -1, 0, 0, 0, 0, 0), field);
            }
            return new OnBattleField(attackPoke, defencePoke, field);
        }
    },
    FOCUS_BLAST("きあいだま", Type.FIGHTING, MoveSpecies.SPECIAL, DetailMvSpecies.DAMAGE, 120, 70, 5, 0, 0,
            false, false, true, false, false, false
    ) {
        @Override
        public OnBattleField effect(PokeInfo attackPoke, PokeInfo defencePoke, Field field, int recoveryHP) {
            if ((new Random().nextInt(10)) <= 1) {
                return new OnBattleField(attackPoke, defencePoke.withChStatusRank(0, 0, 0, -1, 0, 0, 0), field);
            }
            return new OnBattleField(attackPoke, defencePoke, field);
        }
    },
    PSYCHIC("サイコキネシス", Type.PSYCHIC, MoveSpecies.SPECIAL, DetailMvSpecies.DAMAGE, 90, 100, 10, 0, 0,
            false, false, true, false, false, false
    ) {
        @Override
        public OnBattleField effect(PokeInfo attackPoke, PokeInfo defencePoke, Field field, int recoveryHP) {
            if ((new Random().nextInt(10)) <= 1) {
                return new OnBattleField(attackPoke, defencePoke.withChStatusRank(0, 0, 0, -1, 0, 0, 0), field);
            }
            return new OnBattleField(attackPoke, defencePoke, field);
        }
    },
    /**
     * ここからランク変化技
     */
    GROWL("なきごえ", Type.NORMAL, MoveSpecies.CHANGE, DetailMvSpecies.DOWN_A, 0, 100, 20, 0, 0,
            false, true, true, false, true, true
    ) {
        @Override
        public OnBattleField effect(PokeInfo attackPoke, PokeInfo defencePoke, Field field, int recoveryHP) throws InterruptedException {
            showMessageParChar(defencePoke.basePrm().pName() + "の攻撃が下がった!");
            return new OnBattleField(attackPoke, defencePoke.withChStatusRank(-1, 0, 0, 0, 0, 0, 0), field);
        }
    },
    DOUBLE_TEAM("かげぶんしん", Type.NORMAL, MoveSpecies.CHANGE, DetailMvSpecies.UP_AV, 0, -1, 15, 0, 0,
            false, false, false, false, true, false
    ) {
        @Override
        public OnBattleField effect(PokeInfo attackPoke, PokeInfo defencePoke, Field field, int recoveryHP) throws InterruptedException {
            showMessageParChar(attackPoke.basePrm().pName() + "の回避率が上がった!");
            return new OnBattleField(attackPoke.withChStatusRank(0, 0, 0, 0, 0, 0, 1), defencePoke, field);
        }
    },
    GROWTH("せいちょう", Type.NORMAL, MoveSpecies.CHANGE, DetailMvSpecies.UP_C, 0, -1, 20, 0, 0,
            false, false, true, false, true, false) {
        @Override
        public OnBattleField effect(PokeInfo attackPoke, PokeInfo defencePoke, Field field, int recoveryHP) throws InterruptedException {
            showMessageParChar(attackPoke.basePrm().pName() + "の特攻が上がった!");
            return new OnBattleField(attackPoke.withChStatusRank(0, 0, 1, 0, 0, 0, 0), defencePoke, field);
        }
    },
    SWORDS_DANCE("つるぎのまい", Type.NORMAL, MoveSpecies.CHANGE, DetailMvSpecies.UP_A, 0, -1, 20, 0, 0,
            false, false, true, false, true, false) {
        @Override
        public OnBattleField effect(PokeInfo attackPoke, PokeInfo defencePoke, Field field, int recoveryHP) throws InterruptedException {
            showMessageParChar(attackPoke.basePrm().pName() + "の攻撃がぐーんと上がった!");
            return new OnBattleField(attackPoke.withChStatusRank(2, 0, 0, 0, 0, 0, 0), defencePoke, field);
        }
    },
    /**
     * ここから状態異常技
     */
    SLEEP_POWDER("ねむりごな", Type.GRASS, MoveSpecies.CHANGE, DetailMvSpecies.AILMENT, 0, 75, 15, 0, 0,
            false, true, true, false, false, false) {
        @Override
        public OnBattleField effect(PokeInfo attackPoke, PokeInfo defencePoke, Field field, int recoveryHP) throws InterruptedException {
            return new OnBattleField(attackPoke, defencePoke.withAilment(changeAilment(defencePoke, AilmentE.SLEEP)), field);
        }
    },
    SPORE("キノコのほうし", Type.GRASS, MoveSpecies.CHANGE, DetailMvSpecies.AILMENT, 0, 100, 15, 0, 0,
            false, true, true, false, false, false) {
        @Override
        public OnBattleField effect(PokeInfo attackPoke, PokeInfo defencePoke, Field field, int recoveryHP) throws InterruptedException {
            return new OnBattleField(attackPoke, defencePoke.withAilment(changeAilment(defencePoke, AilmentE.SLEEP)), field);
        }
    },
    WILL_O_WISP("おにび", Type.FIRE, MoveSpecies.CHANGE, DetailMvSpecies.AILMENT, 0, 85, 15, 0, 0,
            false, true, true, false, false, false) {
        @Override
        public OnBattleField effect(PokeInfo attackPoke, PokeInfo defencePoke, Field field, int recoveryHP) throws InterruptedException {
            return new OnBattleField(attackPoke, defencePoke.withAilment(changeAilment(defencePoke, AilmentE.BURN)), field);
        }
    },
    CONFUSE_RAY("あやしいひかり", Type.GHOST, MoveSpecies.CHANGE, DetailMvSpecies.CONFUSE, 0, 100, 10, 0, 0,
            false, true, true, false, false, false) {
        @Override
        public OnBattleField effect(PokeInfo attackPoke, PokeInfo defencePoke, Field field, int recoveryHP) throws InterruptedException {
            return new OnBattleField(attackPoke, defencePoke.withConfusion(beConfusion(defencePoke)), field);
        }
    },
    /**
     * ここから天候技
     */
    SUNNY_DAY("にほんばれ", Type.FIRE, MoveSpecies.CHANGE, DetailMvSpecies.WEATHER, 0, -1, 5, 0, 0,
            false, false, false, false, false, false) {
        @Override
        public OnBattleField effect(PokeInfo attackPoke, PokeInfo defencePoke, Field field, int recoveryHP) throws InterruptedException {
            return new OnBattleField(attackPoke, defencePoke, changeField(field, Weather.DROUGHT));
        }
    },
    RAIN_DANCE("あまごい", Type.WATER, MoveSpecies.CHANGE, DetailMvSpecies.WEATHER, 0, -1, 5, 0, 0,
            false, false, false, false, false, false) {
        @Override
        public OnBattleField effect(PokeInfo attackPoke, PokeInfo defencePoke, Field field, int recoveryHP) throws InterruptedException {
            return new OnBattleField(attackPoke, defencePoke, changeField(field, Weather.RAIN));
        }
    },
    SAND_STORM("すなあらし", Type.ROCK, MoveSpecies.CHANGE, DetailMvSpecies.WEATHER, 0, -1, 10, 0, 0,
            false, false, false, false, false, false) {
        @Override
        public OnBattleField effect(PokeInfo attackPoke, PokeInfo defencePoke, Field field, int recoveryHP) throws InterruptedException {
            return new OnBattleField(attackPoke, defencePoke, changeField(field, Weather.SANDSTORM));
        }
    },
    HAIL("あられ", Type.ICE, MoveSpecies.CHANGE, DetailMvSpecies.WEATHER, 0, -1, 10, 0, 0,
            false, false, false, false, false, false) {
        @Override
        public OnBattleField effect(PokeInfo attackPoke, PokeInfo defencePoke, Field field, int recoveryHP) throws InterruptedException {
            return new OnBattleField(attackPoke, defencePoke, changeField(field, Weather.HAIL));
        }
    };

    // わざめい
    private final String mvName;
    // わざタイプ
    private final Type moveType;
    // 分類
    private final MoveSpecies moveSpecies;
    // 詳細な分類 CPUの技選択時に使用
    private final DetailMvSpecies detailedSpecies;
    // 威力
    private final int damage;
    // 命中率
    private final int hitRate;
    // PP
    private final int pp;
    // 急所ランク
    private final int criticalRank;
    // 優先度
    private final int priority;
    // 直接攻撃
    private final boolean direct;
    // マジックコートで返せる
    private final boolean isReflect;
    // オウム返しできる
    private final boolean isParrot;
    // まもるを貫通できる
    private final boolean isPenetrationDefence;
    // よこどりできる
    private final boolean isSeizure;
    // みがわりを貫通できる
    private final boolean isPenetrationScapegoat;

    public String mvName() {
        return this.mvName;
    }

    public Type moveType() {
        return this.moveType;
    }

    public MoveSpecies moveSpecies() {
        return this.moveSpecies;
    }

    public DetailMvSpecies detailedSpecies() {
        return this.detailedSpecies;
    }

    public int damage() {
        return this.damage;
    }

    public int hitRate() {
        return this.hitRate;
    }

    public int pp() {
        return this.pp;
    }

    public int criticalRank() {
        return this.criticalRank;
    }

    public int priority() {
        return this.priority;
    }

    public boolean direct() {
        return this.direct;
    }

    public boolean isReflect() {
        return this.isReflect;
    }

    public boolean isParrot() {
        return this.isParrot;
    }

    public boolean isPenetrationDefence() {
        return this.isPenetrationDefence;
    }

    public boolean isSeizure() {
        return this.isSeizure;
    }

    public boolean isPenetrationScapegoat() {
        return this.isPenetrationScapegoat;
    }

    // 効果
    public abstract OnBattleField effect(PokeInfo attackPoke, PokeInfo defencePoke, Field field, int recoveryHP) throws InterruptedException;
}
