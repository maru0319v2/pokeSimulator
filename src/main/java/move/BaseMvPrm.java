package move;

import Enum.MoveSpecies;
import Enum.Type;
import field.*;
import lombok.AllArgsConstructor;
import pokemonStatus.impl.ConfusionI;
import pokemonStatus.impl.CurrentHPI;
import pokemonStatus.impl.FlinchI;
import statusAilment.AilmentEnum;
import statusAilment.AilmentI;

import java.util.Random;

@AllArgsConstructor
public enum BaseMvPrm {
    /**
     * ここから通常攻撃
     */
    TACKLE("たいあたり", Type.NORMAL, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 40, 95, 35, 0, 0,
            true, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) {
            return doNothing(atkField, dfcField, weather);
        }
    },
    QUICK_ATTACK("でんこうせっか", Type.NORMAL, MoveSpecies.PHYSICAL, DetailMvSpecies.PRIORITY, 40, 100, 30, 0, 1,
            true, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) {
            return doNothing(atkField, dfcField, weather);
        }
    },
    FLAIL("じたばた", Type.NORMAL, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 20, 100, 15, 0, 0,
            true, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) {
            return doNothing(atkField, dfcField, weather);
        }
    },
    DOUBLE_EDGE("すてみタックル", Type.NORMAL, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 120, 100, 15, 0, 0,
            true, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int damaged, Weather weather) throws InterruptedException {
            return recoilDmg33Per(atkField, dfcField, weather, damaged);
        }
    },
    AERIAL_ACE("つばめがえし", Type.FLYING, MoveSpecies.PHYSICAL, DetailMvSpecies.HIT, 60, -1, 20, 0, 0,
            true, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) {
            return doNothing(atkField, dfcField, weather);
        }
    },
    BRAVE_BIRD("ブレイブバード", Type.FIGHTING, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 120, 100, 15, 0, 0,
            true, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int damaged, Weather weather) throws InterruptedException {
            return recoilDmg33Per(atkField, dfcField, weather, damaged);
        }
    },
    AIR_SLASH("エアスラッシュ", Type.FLYING, MoveSpecies.SPECIAL, DetailMvSpecies.DAMAGE, 75, 95, 15, 0, 0,
            false, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) {
            return beFlinch(30, atkField, dfcField, weather);
        }
    },
    CLOSE_COMBAT("インファイト", Type.FIGHTING, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 120, 100, 5, 0, 0,
            true, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return myStatusRankCh(100, atkField, dfcField, weather, 0, -1, 0, -1, 0, 0, 0);
        }
    },
    SUPERPOWER("ばかぢから", Type.FIGHTING, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 120, 100, 5, 0, 0,
            true, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return myStatusRankCh(100, atkField, dfcField, weather, -1, -1, 0, 0, 0, 0, 0);
        }
    },
    POWER_UP_PUNCH("グロウパンチ", Type.FIGHTING, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 40, 100, 20, 0, 0,
            true, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return myStatusRankCh(50, atkField, dfcField, weather, 1, 0, 0, 0, 0, 0, 0);
        }
    },
    CRESS_CHOP("クロスチョップ", Type.FIGHTING, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 100, 80, 5, 1, 0,
            true, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) {
            return doNothing(atkField, dfcField, weather);
        }
    },
    EARTHQUAKE("じしん", Type.GROUND, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 100, 100, 10, 0, 0,
            false, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) {
            return doNothing(atkField, dfcField, weather);
        }
    },
    FLAMETHROWER("かえんほうしゃ", Type.FIRE, MoveSpecies.SPECIAL, DetailMvSpecies.DAMAGE, 90, 100, 15, 0, 0,
            false, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return beBurn(10, atkField, dfcField, weather);
        }
    },
    OVERHEAT("オーバーヒート", Type.FIRE, MoveSpecies.SPECIAL, DetailMvSpecies.DAMAGE, 130, 90, 5, 0, 0,
            false, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return myStatusRankCh(100, atkField, dfcField, weather, 0, 0, -2, 0, 0, 0, 0);
        }
    },
    ERUPTION("ふんか", Type.FIRE, MoveSpecies.SPECIAL, DetailMvSpecies.DAMAGE, 150, 100, 5, 0, 0,
            false, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return doNothing(atkField, dfcField, weather);
        }
    },
    THUNDERBOLT("10まんボルト", Type.ELECTRIC, MoveSpecies.SPECIAL, DetailMvSpecies.DAMAGE, 90, 100, 15, 0, 0,
            false, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return beParalysis(10, atkField, dfcField, weather);
        }
    },
    THUNDER("かみなり", Type.ELECTRIC, MoveSpecies.SPECIAL, DetailMvSpecies.DAMAGE, 110, 70, 10, 0, 0,
            false, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return beParalysis(30, atkField, dfcField, weather);
        }
    },
    DRAGON_CLAW("ドラゴンクロー", Type.DRAGON, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 80, 100, 15, 0, 0,
            true, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) {
            return doNothing(atkField, dfcField, weather);
        }
    },
    WATER_PULSE("みずのはどう", Type.WATER, MoveSpecies.SPECIAL, DetailMvSpecies.DAMAGE, 60, 100, 20, 0, 0,
            false, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return beConfusion(20, atkField, dfcField, weather);
        }
    },
    BRINE("しおみず", Type.WATER, MoveSpecies.SPECIAL, DetailMvSpecies.DAMAGE, 65, 100, 10, 0, 0,
            false, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) {
            return doNothing(atkField, dfcField, weather);
        }
    },
    WATER_SPOUT("しおふき", Type.WATER, MoveSpecies.SPECIAL, DetailMvSpecies.DAMAGE, 150, 100, 5, 0, 0,
            false, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) {
            return doNothing(atkField, dfcField, weather);
        }
    },
    ICE_SHARD("こおりのつぶて", Type.ICE, MoveSpecies.PHYSICAL, DetailMvSpecies.PRIORITY, 40, 100, 30, 0, 1,
            false, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) {
            return doNothing(atkField, dfcField, weather);
        }
    },
    Blizzard("ふぶき", Type.ICE, MoveSpecies.SPECIAL, DetailMvSpecies.DAMAGE, 110, 70, 5, 0, 0,
            false, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return beFreeze(10, atkField, dfcField, weather);
        }
    },
    VINE_WHIP("つるのムチ", Type.GRASS, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 45, 100, 25, 0, 0,
            true, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) {
            return doNothing(atkField, dfcField, weather);
        }
    },
    ENERGY_BALL("エナジーボール", Type.GRASS, MoveSpecies.SPECIAL, DetailMvSpecies.DAMAGE, 90, 100, 10, 0, 0,
            false, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return enemyStatusRankCh(10, atkField, dfcField, weather, 0, 0, 0, -1, 0, 0, 0);
        }
    },
    GIGA_DRAIN("ギガドレイン", Type.GRASS, MoveSpecies.SPECIAL, DetailMvSpecies.DAMAGE, 75, 100, 10, 0, 0,
            false, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return recoveryDmg50Per(atkField, dfcField, weather, recoveryHP);
        }
    },
    LEECH_LIFE("きゅうけつ", Type.BUG, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 80, 100, 10, 0, 0,
            true, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return recoveryDmg50Per(atkField, dfcField, weather, recoveryHP);
        }
    },
    X_SCISSOR("シザークロス", Type.BUG, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 80, 100, 15, 0, 0,
            true, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) {
            return doNothing(atkField, dfcField, weather);
        }
    },
    IRON_TAIL("アイアンテール", Type.STEEL, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 100, 75, 15, 0, 0,
            true, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return enemyStatusRankCh(30, atkField, dfcField, weather, 0, -1, 0, 0, 0, 0, 0);
        }
    },
    ROCK_SLIDE("いわなだれ", Type.ROCK, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 75, 90, 10, 0, 0,
            false, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) {
            return beFlinch(30, atkField, dfcField, weather);
        }
    },
    CRUNCH("かみくだく", Type.DARK, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 80, 100, 15, 0, 0,
            true, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return enemyStatusRankCh(20, atkField, dfcField, weather, 0, -1, 0, 0, 0, 0, 0);
        }
    },
    FOCUS_BLAST("きあいだま", Type.FIGHTING, MoveSpecies.SPECIAL, DetailMvSpecies.DAMAGE, 120, 70, 5, 0, 0,
            false, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return enemyStatusRankCh(10, atkField, dfcField, weather, 0, 0, 0, -1, 0, 0, 0);
        }
    },
    REVERSAL("きしかいせい", Type.FIGHTING, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 20, 100, 15, 0, 0,
            true, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) {
            return doNothing(atkField, dfcField, weather);
        }
    },
    PSYCHIC("サイコキネシス", Type.PSYCHIC, MoveSpecies.SPECIAL, DetailMvSpecies.DAMAGE, 90, 100, 10, 0, 0,
            false, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return enemyStatusRankCh(10, atkField, dfcField, weather, 0, 0, 0, -1, 0, 0, 0);
        }
    },
    ZEN_HEADBUTT("しねんのずつき", Type.PSYCHIC, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 80, 90, 15, 0, 0,
            true, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) {
            return beFlinch(20, atkField, dfcField, weather);
        }
    },
    /**
     * ここからランク変化技
     */
    GROWL("なきごえ", Type.NORMAL, MoveSpecies.CHANGE, DetailMvSpecies.DOWN_A, 0, 100, 20, 0, 0,
            false, true, true, false, true, true) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return enemyStatusRankCh(100, atkField, dfcField, weather, -1, 0, 0, 0, 0, 0, 0);
        }
    },
    DOUBLE_TEAM("かげぶんしん", Type.NORMAL, MoveSpecies.CHANGE, DetailMvSpecies.UP_AV, 0, -1, 15, 0, 0,
            false, false, false, false, true, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return myStatusRankCh(100, atkField, dfcField, weather, 0, 0, 0, 0, 0, 0, 1);
        }
    },
    GROWTH("せいちょう", Type.NORMAL, MoveSpecies.CHANGE, DetailMvSpecies.UP_C, 0, -1, 20, 0, 0,
            false, false, true, false, true, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return myStatusRankCh(100, atkField, dfcField, weather, 0, 0, 1, 0, 0, 0, 0);
        }
    },
    SWORDS_DANCE("つるぎのまい", Type.NORMAL, MoveSpecies.CHANGE, DetailMvSpecies.UP_A, 0, -1, 20, 0, 0,
            false, false, true, false, true, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return myStatusRankCh(100, atkField, dfcField, weather, 2, 0, 0, 0, 0, 0, 0);
        }
    },
    CHARM("あまえる", Type.PSYCHIC, MoveSpecies.CHANGE, DetailMvSpecies.DOWN_A, 0, 100, 20, 0, 0,
            false, true, true, false, true, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return enemyStatusRankCh(100, atkField, dfcField, weather, -2, 0, 0, 0, 0, 0, 0);
        }
    },
    /**
     * ここから状態異常技
     */
    SLEEP_POWDER("ねむりごな", Type.GRASS, MoveSpecies.CHANGE, DetailMvSpecies.AILMENT, 0, 75, 15, 0, 0,
            false, true, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return beSleep(100, atkField, dfcField, weather);
        }
    },
    SPORE("キノコのほうし", Type.GRASS, MoveSpecies.CHANGE, DetailMvSpecies.AILMENT, 0, 100, 15, 0, 0,
            false, true, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return beSleep(100, atkField, dfcField, weather);
        }
    },
    WILL_O_WISP("おにび", Type.FIRE, MoveSpecies.CHANGE, DetailMvSpecies.AILMENT, 0, 85, 15, 0, 0,
            false, true, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return beBurn(100, atkField, dfcField, weather);
        }
    },
    CONFUSE_RAY("あやしいひかり", Type.GHOST, MoveSpecies.CHANGE, DetailMvSpecies.CONFUSE, 0, 100, 10, 0, 0,
            false, true, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return beConfusion(100, atkField, dfcField, weather);
        }
    },
    HYPNOSIS("さいみんじゅつ", Type.PSYCHIC, MoveSpecies.CHANGE, DetailMvSpecies.AILMENT, 0, 60, 20, 0, 0,
            false, true, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return beSleep(100, atkField, dfcField, weather);
        }
    },
    /**
     * ここからその他変化技
     */
    RECOVER("じこさいせい", Type.NORMAL, MoveSpecies.CHANGE, DetailMvSpecies.RECOVERY, 0, -1, 5, 0, 0,
            false, false, false, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return recoveryHP50Per(atkField, dfcField, weather);
        }
    },
    LIGHT_SCREEN("ひかりのかべ", Type.PSYCHIC, MoveSpecies.CHANGE, DetailMvSpecies.WALL, 0, -1, 30, 0, 0,
            false, false, false, false, true, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return new OnBattleField(atkField.withLightScreen(LightScreen.enableLightScreen(atkField.lightScreen())), dfcField, weather);
        }
    },
    REFLECT("リフレクター", Type.PSYCHIC, MoveSpecies.CHANGE, DetailMvSpecies.WALL, 0, -1, 20, 0, 0,
            false, false, false, false, true, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return new OnBattleField(atkField.withReflect(Reflect.enableReflect(atkField.reflect())), dfcField, weather);
        }
    },
    /**
     * ここから天候技
     */
    SUNNY_DAY("にほんばれ", Type.FIRE, MoveSpecies.CHANGE, DetailMvSpecies.WEATHER, 0, -1, 5, 0, 0,
            false, false, false, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return new OnBattleField(atkField, dfcField, Weather.changeWeather(weather, WeatherEnum.DROUGHT));
        }
    },
    RAIN_DANCE("あまごい", Type.WATER, MoveSpecies.CHANGE, DetailMvSpecies.WEATHER, 0, -1, 5, 0, 0,
            false, false, false, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return new OnBattleField(atkField, dfcField, Weather.changeWeather(weather, WeatherEnum.RAIN));
        }
    },
    SAND_STORM("すなあらし", Type.ROCK, MoveSpecies.CHANGE, DetailMvSpecies.WEATHER, 0, -1, 10, 0, 0,
            false, false, false, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return new OnBattleField(atkField, dfcField, Weather.changeWeather(weather, WeatherEnum.SANDSTORM));
        }
    },
    HAIL("あられ", Type.ICE, MoveSpecies.CHANGE, DetailMvSpecies.WEATHER, 0, -1, 10, 0, 0,
            false, false, false, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return new OnBattleField(atkField, dfcField, Weather.changeWeather(weather, WeatherEnum.HAIL));
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

    public int damage(Field atkField, Field dfcField) {
        int result;
        switch (this) {
            case ERUPTION, WATER_SPOUT -> result = 150 * atkField.poke().currentHP().val() / atkField.poke().realHP();
            case REVERSAL, FLAIL -> result = calcReversalDmg(atkField);
            case BRINE -> result = (double) (dfcField.poke().currentHP().val()) / (double) (dfcField.poke().realHP()) <= 0.5 ? this.damage * 2 : this.damage;
            default -> result = this.damage;
        }
        return result;
    }

    public int hitRate() {
        return this.hitRate;
    }

    public int hitRate(Weather weather) {
        switch (this) {
            case THUNDER -> {
                if (weather.val() == WeatherEnum.RAIN) {
                    return -1;
                } else if (weather.val() == WeatherEnum.DROUGHT) {
                    return this.hitRate / 2;
                } else {
                    return this.hitRate;
                }
            }
            case Blizzard -> {
                if (weather.val() == WeatherEnum.HAIL) {
                    return -1;
                }
            }
            default -> this.hitRate();
        }
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
    public abstract OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException;

    private static OnBattleField doNothing(Field atkField, Field dfcField, Weather weather) {
        return new OnBattleField(atkField, dfcField, weather);
    }

    private static OnBattleField recoilDmg33Per(Field atkField, Field dfcField, Weather weather, int damaged) throws InterruptedException {
        return new OnBattleField(atkField.withPokeInfo(atkField.poke().damage(damaged / 3)), dfcField, weather);
    }

    private static OnBattleField recoveryDmg50Per(Field atkField, Field dfcField, Weather weather, int damaged) throws InterruptedException {
        return new OnBattleField(atkField.withPokeInfo(atkField.poke().withCurrentHP(atkField.poke().currentHP().recovery(atkField.poke(), new CurrentHPI(damaged)))), dfcField, weather);
    }

    private static OnBattleField recoveryHP50Per(Field atkField, Field dfcField, Weather weather) throws InterruptedException {
        int recovery = atkField.poke().realHP() / 2;
        return new OnBattleField(atkField.withPokeInfo(atkField.poke().withCurrentHP(atkField.poke().currentHP().recovery(atkField.poke(), new CurrentHPI(recovery)))), dfcField, weather);
    }

    private static OnBattleField myStatusRankCh(int percent, Field atkField, Field dfcField, Weather weather, int A, int B, int C, int D, int S, int HT, int AV) throws InterruptedException {
        if ((new Random().nextInt(10)) <= percent / 10 - 1) {
            return new OnBattleField(atkField.withPokeInfo(atkField.poke().withChStatusRank(A, B, C, D, S, HT, AV)), dfcField, weather);
        } else {
            return doNothing(atkField, dfcField, weather);
        }
    }

    private static OnBattleField enemyStatusRankCh(int percent, Field atkField, Field dfcField, Weather weather, int A, int B, int C, int D, int S, int HT, int AV) throws InterruptedException {
        if ((new Random().nextInt(10)) <= percent / 10 - 1) {
            return new OnBattleField(atkField, dfcField.withPokeInfo(dfcField.poke().withChStatusRank(A, B, C, D, S, HT, AV)), weather);
        } else {
            return doNothing(atkField, dfcField, weather);
        }
    }

    private static OnBattleField beFlinch(int percent, Field atkField, Field dfcField, Weather weather) {
        if ((new Random().nextInt(10)) <= percent / 10 - 1) {
            return new OnBattleField(atkField, dfcField.withPokeInfo(dfcField.poke().withFlinch(new FlinchI(true))), weather);
        } else {
            return doNothing(atkField, dfcField, weather);
        }
    }

    private static OnBattleField beConfusion(int percent, Field atkField, Field dfcField, Weather weather) throws InterruptedException {
        if ((new Random().nextInt(10)) <= percent / 10 - 1) {
            return new OnBattleField(atkField, dfcField.withPokeInfo(dfcField.poke().withConfusion(ConfusionI.beConfusion(dfcField.poke()))), weather);
        } else {
            return doNothing(atkField, dfcField, weather);
        }
    }

    private static OnBattleField beBurn(int percent, Field atkField, Field dfcField, Weather weather) throws InterruptedException {
        if ((new Random().nextInt(10)) <= percent / 10 - 1) {
            return new OnBattleField(atkField, dfcField.withPokeInfo(dfcField.poke().withAilment(AilmentI.changeAilment(dfcField.poke(), AilmentEnum.BURN))), weather);
        } else {
            return doNothing(atkField, dfcField, weather);
        }
    }

    private static OnBattleField beParalysis(int percent, Field atkField, Field dfcField, Weather weather) throws InterruptedException {
        if ((new Random().nextInt(10)) <= percent / 10 - 1) {
            return new OnBattleField(atkField, dfcField.withPokeInfo(dfcField.poke().withAilment(AilmentI.changeAilment(dfcField.poke(), AilmentEnum.PARALYSIS))), weather);
        } else {
            return doNothing(atkField, dfcField, weather);
        }
    }

    private static OnBattleField beFreeze(int percent, Field atkField, Field dfcField, Weather weather) throws InterruptedException {
        if ((new Random().nextInt(10)) <= percent / 10 - 1) {
            return new OnBattleField(atkField, dfcField.withPokeInfo(dfcField.poke().withAilment(AilmentI.changeAilment(dfcField.poke(), AilmentEnum.FREEZE))), weather);
        } else {
            return doNothing(atkField, dfcField, weather);
        }
    }

    private static OnBattleField beSleep(int percent, Field atkField, Field dfcField, Weather weather) throws InterruptedException {
        if ((new Random().nextInt(10)) <= percent / 10 - 1) {
            return new OnBattleField(atkField, dfcField.withPokeInfo(dfcField.poke().withAilment(AilmentI.changeAilment(dfcField.poke(), AilmentEnum.SLEEP))), weather);
        } else {
            return doNothing(atkField, dfcField, weather);
        }
    }

    private static boolean random10Per() {
        return (new Random().nextInt(10)) == 0;
    }

    private static boolean random20Per() {
        return (new Random().nextInt(10)) <= 1;
    }

    private static boolean random30Per() {
        return (new Random().nextInt(10)) <= 2;
    }

    // きしかいせい、じたばたのダメージを計算する
    private static int calcReversalDmg(Field atkField) {
        double RemainingHP = (double) (atkField.poke().currentHP().val()) / (double) (atkField.poke().realHP());
        if (RemainingHP < 2d / 48d) {
            return 200;
        }
        if (RemainingHP < 5d / 48d) {
            return 150;
        }
        if (RemainingHP < 10d / 48d) {
            return 100;
        }
        if (RemainingHP < 17d / 48d) {
            return 80;
        }
        if (RemainingHP < 33d / 48d) {
            return 40;
        }
        return 20;
    }
}
