package move;

import Enum.Item;
import Enum.MoveSpecies;
import Enum.Type;
import field.*;
import lombok.AllArgsConstructor;
import pokemon.PokeInfo;
import pokemonStatus.impl.ConfusionI;
import pokemonStatus.impl.FlinchI;
import statusAilment.AilmentEnum;
import statusAilment.AilmentI;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static Enum.MoveSpecies.*;
import static Enum.Type.*;
import static bussinessLogic.ConsoleOutManager.showMessageParChar;
import static field.WeatherEnum.*;
import static move.DetailMvSpecies.*;

@AllArgsConstructor
public enum BaseMvPrm {
    /**
     * ここから通常攻撃
     */
    QUICK_ATTACK("でんこうせっか", NORMAL, PHYSICAL, PRIORITY, 40, 100, 30, 0, 1, true) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) {
            return doNothing(atkField, dfcField, weather);
        }
    },
    BODY_SLAM("のしかかり", NORMAL, PHYSICAL, DAMAGE, 85, 100, 15, 0, 0, true) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return BaseMvPrm.beParalysis(30, atkField, dfcField, weather);
        }
    },
    EXTREME_SPEED("しんそく", NORMAL, PHYSICAL, PRIORITY, 80, 100, 5, 0, 2, true) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) {
            return doNothing(atkField, dfcField, weather);
        }
    },
    HYPER_VOICE("ハイパーボイス", NORMAL, SPECIAL, DAMAGE, 90, 100, 10, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) {
            return doNothing(atkField, dfcField, weather);
        }
    },
    FLAIL("じたばた", NORMAL, PHYSICAL, DAMAGE, 20, 100, 15, 0, 0, true) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) {
            return doNothing(atkField, dfcField, weather);
        }
    },
    SLASH("きりさく", NORMAL, PHYSICAL, DAMAGE, 70, 100, 20, 1, 0, true) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) {
            return doNothing(atkField, dfcField, weather);
        }
    },
    FACADE("からげんき", NORMAL, PHYSICAL, DAMAGE, 70, 100, 15, 0, 0, true) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) {
            return doNothing(atkField, dfcField, weather);
        }
    },
    DOUBLE_EDGE("すてみタックル", NORMAL, PHYSICAL, DAMAGE, 120, 100, 15, 0, 0, true) {
        public OnBattleField effect(Field atkField, Field dfcField, int damaged, Weather weather) throws InterruptedException {
            return recoilDmg33Per(atkField, dfcField, weather, damaged);
        }
    },
    SELF_DESTRUCT("じばく", NORMAL, PHYSICAL, DAMAGE, 200, 100, 5, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int damaged, Weather weather) throws InterruptedException {
            return recoilDmgHP100Per(atkField, dfcField, weather);
        }
    },
    EXPLOSION("だいばくはつ", NORMAL, PHYSICAL, DAMAGE, 250, 100, 5, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int damaged, Weather weather) throws InterruptedException {
            return recoilDmgHP100Per(atkField, dfcField, weather);
        }
    },
    ENDEAVOR("がむしゃら", NORMAL, PHYSICAL, DAMAGE, 1, 100, 5, 0, 0, true) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) {
            return doNothing(atkField, dfcField, weather);
        }
    },
    AERIAL_ACE("つばめがえし", FLYING, PHYSICAL, HIT, 60, -1, 20, 0, 0, true) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) {
            return doNothing(atkField, dfcField, weather);
        }
    },
    DRILL_PECK("ドリルくちばし", FLYING, PHYSICAL, HIT, 80, 100, 20, 0, 0, true) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) {
            return doNothing(atkField, dfcField, weather);
        }
    },
    BRAVE_BIRD("ブレイブバード", FLYING, PHYSICAL, DAMAGE, 120, 100, 15, 0, 0, true) {
        public OnBattleField effect(Field atkField, Field dfcField, int damaged, Weather weather) throws InterruptedException {
            return recoilDmg33Per(atkField, dfcField, weather, damaged);
        }
    },
    AIR_SLASH("エアスラッシュ", FLYING, SPECIAL, DAMAGE, 75, 95, 15, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) {
            return beFlinch(30, atkField, dfcField, weather);
        }
    },
    HURRICANE("ぼうふう", FLYING, SPECIAL, DAMAGE, 110, 70, 10, 0, 0, true) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return beConfusion(30, atkField, dfcField, weather);
        }
    },
    MACH_PUNCH("マッハパンチ", FIGHTING, PHYSICAL, PRIORITY, 40, 100, 30, 0, 1, true) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) {
            return doNothing(atkField, dfcField, weather);
        }
    },
    SEISMIC_TOSS("ちきゅうなげ", FIGHTING, SPECIAL, DAMAGE, 1, 100, 20, 0, 0, true) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) {
            return doNothing(atkField, dfcField, weather);
        }
    },
    FOCUS_BLAST("きあいだま", FIGHTING, SPECIAL, DAMAGE, 120, 70, 5, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return enemyStatusRankCh(10, atkField, dfcField, weather, 0, 0, 0, -1, 0, 0, 0);
        }
    },
    REVERSAL("きしかいせい", FIGHTING, PHYSICAL, DAMAGE, 20, 100, 15, 0, 0, true) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) {
            return doNothing(atkField, dfcField, weather);
        }
    },
    HAMMER_ARM("アームハンマー", FIGHTING, PHYSICAL, DAMAGE, 100, 90, 10, 0, 0, true) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return myStatusRankCh(100, atkField, dfcField, weather, 0, 0, 0, 0, -1, 0, 0);
        }
    },
    DYNAMIC_PUNCH("ばくれつパンチ", FIGHTING, PHYSICAL, DAMAGE, 100, 50, 5, 0, 0, true) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return beConfusion(100, atkField, dfcField, weather);
        }
    },
    AURA_SPHERE("はどうだん", FIGHTING, SPECIAL, HIT, 80, -1, 20, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) {
            return doNothing(atkField, dfcField, weather);
        }
    },
    CLOSE_COMBAT("インファイト", FIGHTING, PHYSICAL, DAMAGE, 120, 100, 5, 0, 0, true) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return myStatusRankCh(100, atkField, dfcField, weather, 0, -1, 0, -1, 0, 0, 0);
        }
    },
    SUPERPOWER("ばかぢから", FIGHTING, PHYSICAL, DAMAGE, 120, 100, 5, 0, 0, true) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return myStatusRankCh(100, atkField, dfcField, weather, -1, -1, 0, 0, 0, 0, 0);
        }
    },
    POWER_UP_PUNCH("グロウパンチ", FIGHTING, PHYSICAL, DAMAGE, 40, 100, 20, 0, 0, true) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return myStatusRankCh(50, atkField, dfcField, weather, 1, 0, 0, 0, 0, 0, 0);
        }
    },
    DRAIN_PUNCH("ドレインパンチ", FIGHTING, PHYSICAL, DAMAGE, 75, 100, 10, 0, 0, true) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return recoveryDmg50Per(atkField, dfcField, weather, recoveryHP);
        }
    },
    CRESS_CHOP("クロスチョップ", FIGHTING, PHYSICAL, DAMAGE, 100, 80, 5, 1, 0, true) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) {
            return doNothing(atkField, dfcField, weather);
        }
    },
    BULLDOZE("じならし", GROUND, PHYSICAL, DAMAGE, 60, 100, 10, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return enemyStatusRankCh(100, atkField, dfcField, weather, 0, 0, 0, 0, -1, 0, 0);
        }
    },
    EARTH_POWER("だいちのちから", GROUND, SPECIAL, DAMAGE, 90, 100, 10, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return enemyStatusRankCh(10, atkField, dfcField, weather, 0, 0, 0, -1, 0, 0, 0);
        }
    },
    EARTHQUAKE("じしん", GROUND, PHYSICAL, DAMAGE, 100, 100, 10, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) {
            return doNothing(atkField, dfcField, weather);
        }
    },
    // TODO ひこうタイプにもあたる
    FISSURE("じわれ", GROUND, PHYSICAL, DAMAGE, 1, 30, 5, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) {
            return doNothing(atkField, dfcField, weather);
        }
    },
    VENOSHOCK("ベノムショック", POISON, SPECIAL, DAMAGE, 65, 100, 10, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) {
            return doNothing(atkField, dfcField, weather);
        }
    },
    SLUDGE_BOMB("ヘドロばくだん", POISON, SPECIAL, DAMAGE, 90, 100, 10, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return bePoison(10, atkField, dfcField, weather);
        }
    },
    Sludge_Wave("ヘドロウェーブ", POISON, SPECIAL, DAMAGE, 95, 100, 10, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return bePoison(10, atkField, dfcField, weather);
        }
    },
    FLAME_CHARGE("ニトロチャージ", FIRE, PHYSICAL, DAMAGE, 50, 100, 20, 0, 0, true) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return myStatusRankCh(100, atkField, dfcField, weather, 0, 0, 0, 0, 1, 0, 0);
        }
    },
    FIRE_PUNCH("ほのおのパンチ", FIRE, PHYSICAL, DAMAGE, 75, 100, 15, 0, 0, true) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return beBurn(10, atkField, dfcField, weather);
        }
    },
    MYSTICAL_FIRE("マジカルフレイム", FIRE, SPECIAL, DAMAGE, 75, 100, 10, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return enemyStatusRankCh(100, atkField, dfcField, weather, 0, 0, -1, 0, 0, 0, 0);
        }
    },
    FLAMETHROWER("かえんほうしゃ", FIRE, SPECIAL, DAMAGE, 90, 100, 15, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return beBurn(10, atkField, dfcField, weather);
        }
    },
    HEAT_WAVE("ねっぷう", FIRE, SPECIAL, DAMAGE, 95, 90, 10, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return beBurn(10, atkField, dfcField, weather);
        }
    },
    FIRE_BLAST("だいもんじ", FIRE, SPECIAL, DAMAGE, 110, 85, 5, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return beBurn(10, atkField, dfcField, weather);
        }
    },
    FLARE_BLITZ("フレアドライブ", FIRE, PHYSICAL, DAMAGE, 120, 100, 15, 0, 0, true) {
        public OnBattleField effect(Field atkField, Field dfcField, int damaged, Weather weather) throws InterruptedException {
            OnBattleField onBf = beBurn(10, atkField, dfcField, weather);
            return recoilDmg33Per(onBf.atkField(), onBf.dfcField(), onBf.weather(), damaged);
        }
    },
    OVERHEAT("オーバーヒート", FIRE, SPECIAL, DAMAGE, 130, 90, 5, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return myStatusRankCh(100, atkField, dfcField, weather, 0, 0, -2, 0, 0, 0, 0);
        }
    },
    ERUPTION("ふんか", FIRE, SPECIAL, DAMAGE, 150, 100, 5, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) {
            return doNothing(atkField, dfcField, weather);
        }
    },
    THUNDER_PUNCH("かみなりパンチ", ELECTRIC, PHYSICAL, DAMAGE, 75, 100, 15, 0, 0, true) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return beParalysis(10, atkField, dfcField, weather);
        }
    },
    WILD_CHARGE("ワイルドボルト", ELECTRIC, PHYSICAL, DAMAGE, 90, 100, 15, 0, 0, true) {
        public OnBattleField effect(Field atkField, Field dfcField, int damaged, Weather weather) throws InterruptedException {
            return recoilDmg25Per(atkField, dfcField, weather, damaged);
        }
    },
    THUNDERBOLT("10まんボルト", ELECTRIC, SPECIAL, DAMAGE, 90, 100, 15, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return beParalysis(10, atkField, dfcField, weather);
        }
    },
    THUNDER("かみなり", ELECTRIC, SPECIAL, DAMAGE, 110, 70, 10, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return beParalysis(30, atkField, dfcField, weather);
        }
    },
    DRAGON_CLAW("ドラゴンクロー", DRAGON, PHYSICAL, DAMAGE, 80, 100, 15, 0, 0, true) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) {
            return doNothing(atkField, dfcField, weather);
        }
    },
    DRACO_METEOR("りゅうせいぐん", DRAGON, SPECIAL, DAMAGE, 130, 90, 5, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return myStatusRankCh(100, atkField, dfcField, weather, 0, 0, -2, 0, 0, 0, 0);
        }
    },
    AQUA_JET("アクアジェット", WATER, PHYSICAL, PRIORITY, 40, 100, 20, 0, 1, true) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) {
            return doNothing(atkField, dfcField, weather);
        }
    },
    WATER_PULSE("みずのはどう", WATER, SPECIAL, DAMAGE, 60, 100, 20, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return beConfusion(20, atkField, dfcField, weather);
        }
    },
    BRINE("しおみず", WATER, SPECIAL, DAMAGE, 65, 100, 10, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) {
            return doNothing(atkField, dfcField, weather);
        }
    },
    WATERFALL("たきのぼり", WATER, PHYSICAL, DAMAGE, 80, 100, 15, 0, 0, true) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) {
            return beFlinch(20, atkField, dfcField, weather);
        }
    },
    SCALD("ねっとう", WATER, SPECIAL, DAMAGE, 80, 100, 15, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return beBurn(30, atkField, dfcField, weather);
        }
    },
    LIQUIDATION("アクアブレイク", WATER, PHYSICAL, DAMAGE, 85, 100, 10, 0, 0, true) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return enemyStatusRankCh(20, atkField, dfcField, weather, 0, -1, 0, 0, 0, 0, 0);
        }
    },
    SURF("なみのり", WATER, SPECIAL, DAMAGE, 90, 100, 15, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) {
            return doNothing(atkField, dfcField, weather);
        }
    },
    HYDRO_PUMP("ハイドロポンプ", WATER, SPECIAL, DAMAGE, 110, 80, 5, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) {
            return doNothing(atkField, dfcField, weather);
        }
    },
    WATER_SPOUT("しおふき", WATER, SPECIAL, DAMAGE, 150, 100, 5, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) {
            return doNothing(atkField, dfcField, weather);
        }
    },
    ICE_SHARD("こおりのつぶて", ICE, PHYSICAL, PRIORITY, 40, 100, 30, 0, 1, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) {
            return doNothing(atkField, dfcField, weather);
        }
    },
    ICE_PUNCH("れいとうパンチ", ICE, PHYSICAL, DAMAGE, 75, 100, 15, 0, 0, true) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return beFreeze(10, atkField, dfcField, weather);
        }
    },
    ICE_BEAM("れいとうビーム", ICE, SPECIAL, DAMAGE, 90, 100, 10, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return beFreeze(10, atkField, dfcField, weather);
        }
    },
    BLIZZARD("ふぶき", ICE, SPECIAL, DAMAGE, 110, 70, 5, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return beFreeze(10, atkField, dfcField, weather);
        }
    },
    SHEER_COLD("ぜったいれいど", ICE, SPECIAL, DAMAGE, 1, 30, 5, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) {
            return doNothing(atkField, dfcField, weather);
        }
    },
    ENERGY_BALL("エナジーボール", GRASS, SPECIAL, DAMAGE, 90, 100, 10, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return enemyStatusRankCh(10, atkField, dfcField, weather, 0, 0, 0, -1, 0, 0, 0);
        }
    },
    SEED_BOMB("タネばくだん", GRASS, PHYSICAL, DAMAGE, 80, 100, 15, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) {
            return doNothing(atkField, dfcField, weather);
        }
    },
    GIGA_DRAIN("ギガドレイン", GRASS, SPECIAL, DAMAGE, 75, 100, 10, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return recoveryDmg50Per(atkField, dfcField, weather, recoveryHP);
        }
    },
    WOOD_HAMMER("ウッドハンマー", GRASS, PHYSICAL, DAMAGE, 120, 100, 15, 0, 0, true) {
        public OnBattleField effect(Field atkField, Field dfcField, int damaged, Weather weather) throws InterruptedException {
            return recoilDmg33Per(atkField, dfcField, weather, damaged);
        }
    },
    LEECH_LIFE("きゅうけつ", BUG, PHYSICAL, DAMAGE, 80, 100, 10, 0, 0, true) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return recoveryDmg50Per(atkField, dfcField, weather, recoveryHP);
        }
    },
    X_SCISSOR("シザークロス", BUG, PHYSICAL, DAMAGE, 80, 100, 15, 0, 0, true) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) {
            return doNothing(atkField, dfcField, weather);
        }
    },
    BUG_BUZZ("むしのさざめき", BUG, SPECIAL, DAMAGE, 90, 100, 10, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return enemyStatusRankCh(10, atkField, dfcField, weather, 0, 0, 0, -1, 0, 0, 0);
        }
    },
    MEGA_HORN("メガホーン", BUG, PHYSICAL, DAMAGE, 120, 85, 10, 0, 0, true) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) {
            return doNothing(atkField, dfcField, weather);
        }
    },
    BULLET_PUNCH("バレットパンチ", STEEL, PHYSICAL, PRIORITY, 40, 100, 30, 0, 1, true) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) {
            return doNothing(atkField, dfcField, weather);
        }
    },
    FLASH_CANNON("ラスターカノン", STEEL, SPECIAL, DAMAGE, 80, 100, 10, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return enemyStatusRankCh(10, atkField, dfcField, weather, 0, 0, 0, -1, 0, 0, 0);
        }
    },
    IRON_TAIL("アイアンテール", STEEL, PHYSICAL, DAMAGE, 100, 75, 15, 0, 0, true) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return enemyStatusRankCh(30, atkField, dfcField, weather, 0, -1, 0, 0, 0, 0, 0);
        }
    },
    METEOR_MASH("コメットパンチ", STEEL, PHYSICAL, DAMAGE, 90, 90, 10, 0, 0, true) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return myStatusRankCh(20, atkField, dfcField, weather, 1, 0, 0, 0, 0, 0, 0);
        }
    },
    STEEL_BEAM("てっていこうせん", STEEL, SPECIAL, DAMAGE, 140, 95, 5, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int damaged, Weather weather) throws InterruptedException {
            return recoilDmgHP50Per(atkField, dfcField, weather);
        }
    },
    ROCK_TOMB("がんせきふうじ", ROCK, PHYSICAL, DAMAGE, 60, 95, 15, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return enemyStatusRankCh(100, atkField, dfcField, weather, 0, 0, 0, 0, -1, 0, 0);
        }
    },
    ROCK_SLIDE("いわなだれ", ROCK, PHYSICAL, DAMAGE, 75, 90, 10, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) {
            return beFlinch(30, atkField, dfcField, weather);
        }
    },
    STONE_EDGE("ストーンエッジ", ROCK, PHYSICAL, DAMAGE, 100, 80, 5, 1, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) {
            return doNothing(atkField, dfcField, weather);
        }
    },
    HEAD_SMASH("もろはのずつき", ROCK, PHYSICAL, DAMAGE, 150, 80, 5, 0, 0, true) {
        public OnBattleField effect(Field atkField, Field dfcField, int damaged, Weather weather) throws InterruptedException {
            return recoilDmg50Per(atkField, dfcField, weather, damaged);
        }
    },
    FEINT_ATTACK("だましうち", DARK, PHYSICAL, HIT, 60, -1, 15, 0, 0, true) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) {
            return doNothing(atkField, dfcField, weather);
        }
    },
    KNOCK_OFF("はたきおとす", DARK, PHYSICAL, DAMAGE, 65, 100, 20, 0, 0, true) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            showMessageParChar(dfcField.poke().basePrm().pName() + "の" + dfcField.poke().item().val() + "をはたきおとした!");
            return lostItem(atkField, dfcField, weather);
        }
    },
    NIGHT_SLASH("つじぎり", DARK, PHYSICAL, DAMAGE, 70, 100, 15, 1, 0, true) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) {
            return doNothing(atkField, dfcField, weather);
        }
    },
    CRUNCH("かみくだく", DARK, PHYSICAL, DAMAGE, 80, 100, 15, 0, 0, true) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return enemyStatusRankCh(20, atkField, dfcField, weather, 0, -1, 0, 0, 0, 0, 0);
        }
    },
    PSYCHICS("サイコキネシス", PSYCHIC, SPECIAL, DAMAGE, 90, 100, 10, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return enemyStatusRankCh(10, atkField, dfcField, weather, 0, 0, 0, -1, 0, 0, 0);
        }
    },
    ZEN_HEADBUTT("しねんのずつき", PSYCHIC, PHYSICAL, DAMAGE, 80, 90, 15, 0, 0, true) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) {
            return beFlinch(20, atkField, dfcField, weather);
        }
    },
    SHADOW_BALL("シャドーボール", GHOST, SPECIAL, DAMAGE, 80, 100, 15, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return enemyStatusRankCh(20, atkField, dfcField, weather, 0, 0, 0, -1, 0, 0, 0);
        }
    },
    PLAY_ROUGH("じゃれつく", FAIRY, PHYSICAL, DAMAGE, 90, 90, 10, 0, 0, true) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return enemyStatusRankCh(10, atkField, dfcField, weather, -1, 0, 0, 0, 0, 0, 0);
        }
    },
    DAZZLING_GLEAM("マジカルシャイン", FAIRY, SPECIAL, DAMAGE, 80, 100, 10, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) {
            return doNothing(atkField, dfcField, weather);
        }
    },
    MOONBLAST("ムーンフォース", FAIRY, SPECIAL, DAMAGE, 95, 100, 15, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return enemyStatusRankCh(30, atkField, dfcField, weather, 0, 0, -1, 0, 0, 0, 0);
        }
    },
    HIDDEN_POWER_FIRE("めざめるパワー", FIRE, SPECIAL, DAMAGE, 70, 100, 15, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) {
            return doNothing(atkField, dfcField, weather);
        }
    },
    HIDDEN_POWER_ICE("めざめるパワー", ICE, SPECIAL, DAMAGE, 70, 100, 15, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) {
            return doNothing(atkField, dfcField, weather);
        }
    },
    HIDDEN_POWER_GRASS("めざめるパワー", GRASS, SPECIAL, DAMAGE, 70, 100, 15, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) {
            return doNothing(atkField, dfcField, weather);
        }
    },

    /**
     * ここからランク変化技
     */
    DOUBLE_TEAM("かげぶんしん", NORMAL, CHANGE, UP_AV, null, -1, 15, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return myStatusRankCh(100, atkField, dfcField, weather, 0, 0, 0, 0, 0, 0, 1);
        }
    },
    GROWTH("せいちょう", NORMAL, CHANGE, UP_C, null, -1, 20, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            if (weather.val() == DROUGHT) {
                return myStatusRankCh(100, atkField, dfcField, weather, 2, 0, 2, 0, 0, 0, 0);
            } else {
                return myStatusRankCh(100, atkField, dfcField, weather, 1, 0, 1, 0, 0, 0, 0);
            }
        }
    },
    SWORDS_DANCE("つるぎのまい", NORMAL, CHANGE, UP_A, null, -1, 20, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return myStatusRankCh(100, atkField, dfcField, weather, 2, 0, 0, 0, 0, 0, 0);
        }
    },
    SHELL_SMASH("からをやぶる", NORMAL, CHANGE, UP_A, null, -1, 15, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            OnBattleField onBf = myStatusRankCh(100, atkField, dfcField, weather, 0, -1, 0, -1, 0, 0, 0);
            return myStatusRankCh(100, onBf.atkField(), dfcField, weather, 2, 0, 2, 0, 2, 0, 0);
        }
    },
    MINIMIZE("ちいさくなる", NORMAL, CHANGE, UP_AV, null, -1, 10, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return myStatusRankCh(100, atkField, dfcField, weather, 0, 0, 0, 0, 0, 0, 2);
        }
    },
    BELLY_DRUM("はらだいこ", NORMAL, CHANGE, UP_AV, null, -1, 10, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            OnBattleField onBf = recoilDmgHP50Per(atkField, dfcField, weather);
            return myStatusRankCh(100, onBf.atkField(), onBf.dfcField(), onBf.weather(), 12, 0, 0, 0, 0, 0, 0);
        }
    },
    CHARM("あまえる", PSYCHIC, CHANGE, DOWN_A, null, 100, 20, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return enemyStatusRankCh(100, atkField, dfcField, weather, -2, 0, 0, 0, 0, 0, 0);
        }
    },
    CALM_MIND("めいそう", PSYCHIC, CHANGE, UP_C, null, -1, 20, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return myStatusRankCh(100, atkField, dfcField, weather, 0, 0, 1, 1, 0, 0, 0);
        }
    },
    NASTY_PLOT("わるだくみ", DARK, CHANGE, UP_C, null, -1, 20, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return myStatusRankCh(100, atkField, dfcField, weather, 0, 0, 2, 0, 0, 0, 0);
        }
    },
    MEMENTO("おきみやげ", DARK, CHANGE, DOWN_A, null, -1, 10, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            OnBattleField onBf = enemyStatusRankCh(100, atkField, dfcField, weather, -2, 0, -2, 0, 0, 0, 0);
            return recoilDmgHP100Per(onBf.atkField(), onBf.dfcField(), onBf.weather());
        }
    },
    HONE_CLAWS("つめとぎ", DARK, CHANGE, UP_A, null, -1, 15, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return myStatusRankCh(100, atkField, dfcField, weather, 1, 0, 0, 0, 0, 1, 0);
        }
    },
    DRAGON_DANCE("りゅうのまい", DRAGON, CHANGE, UP_A, null, -1, 20, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return myStatusRankCh(100, atkField, dfcField, weather, 1, 0, 0, 0, 1, 0, 0);
        }
    },
    QUIVER_DANCE("ちょうのまい", BUG, CHANGE, UP_C, null, -1, 20, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return myStatusRankCh(100, atkField, dfcField, weather, 0, 0, 1, 1, 1, 0, 0);
        }
    },
    BULK_UP("ビルドアップ", FIGHTING, CHANGE, UP_A, null, -1, 20, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return myStatusRankCh(100, atkField, dfcField, weather, 1, 1, 0, 0, 0, 0, 0);
        }
    },
    /**
     * ここから状態異常技
     */
    SLEEP_POWDER("ねむりごな", GRASS, CHANGE, AILMENT, null, 75, 15, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return beSleep(100, atkField, dfcField, weather);
        }
    },
    SPORE("キノコのほうし", GRASS, CHANGE, AILMENT, null, 100, 15, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return beSleep(100, atkField, dfcField, weather);
        }
    },
    LOVELY_KISS("あくまのキッス", NORMAL, CHANGE, AILMENT, null, 75, 10, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return beSleep(100, atkField, dfcField, weather);
        }
    },
    WILL_O_WISP("おにび", FIRE, CHANGE, AILMENT, null, 85, 15, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return beBurn(100, atkField, dfcField, weather);
        }
    },
    CONFUSE_RAY("あやしいひかり", GHOST, CHANGE, CONFUSE, null, 100, 10, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return beConfusion(100, atkField, dfcField, weather);
        }
    },
    HYPNOSIS("さいみんじゅつ", PSYCHIC, CHANGE, AILMENT, null, 60, 20, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return beSleep(100, atkField, dfcField, weather);
        }
    },
    TOXIC("どくどく", POISON, CHANGE, AILMENT, null, 90, 10, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return BaseMvPrm.beBadPoison(100, atkField, dfcField, weather);
        }
    },
    THUNDER_WAVE("でんじは", ELECTRIC, CHANGE, AILMENT, null, 90, 20, 0, 0, false) {
        // TODO 多分地面タイプにも効く
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return BaseMvPrm.beParalysis(100, atkField, dfcField, weather);
        }
    },
    SWAGGER("いばる", NORMAL, CHANGE, CONFUSE, null, 85, 15, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            OnBattleField onBf = enemyStatusRankCh(100, atkField, dfcField, weather, 2, 0, 0, 0, 0, 0, 0);
            return beConfusion(100, onBf.atkField(), onBf.dfcField(), onBf.weather());
        }
    },
    SWEET_KISS("てんしのキッス", FAIRY, CHANGE, CONFUSE, null, 75, 10, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return beConfusion(100, atkField, dfcField, weather);
        }
    },
    /**
     * ここからその他変化技
     */
    RECOVER("じこさいせい", NORMAL, CHANGE, RECOVERY, null, -1, 5, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return recoveryHP50Per(atkField, dfcField, weather);
        }
    },
    SLACK_OFF("なまける", NORMAL, CHANGE, RECOVERY, null, -1, 5, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return recoveryHP50Per(atkField, dfcField, weather);
        }
    },
    SOFT_BOILED("タマゴうみ", NORMAL, CHANGE, RECOVERY, null, -1, 5, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return recoveryHP50Per(atkField, dfcField, weather);
        }
    },
    MOONLIGHT("つきのひかり", PSYCHIC, CHANGE, RECOVERY, null, -1, 5, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return recoveryByWeather(atkField, dfcField, weather);
        }
    },
    SYNTHESIS("こうごうせい", GRASS, CHANGE, RECOVERY, null, -1, 5, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return recoveryByWeather(atkField, dfcField, weather);
        }
    },
    REST("ねむる", PSYCHIC, CHANGE, RECOVERY, null, -1, 10, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            if (atkField.poke().realHP() > atkField.poke().currentHP().val()) {
                OnBattleField onBf = recoveryHP100Per(atkField, dfcField, weather);
                return beSelfSleep(100, onBf.atkField(), onBf.dfcField(), onBf.weather());
            } else {
                return recoveryHP100Per(atkField, dfcField, weather);
            }
        }
    },
    LIGHT_SCREEN("ひかりのかべ", PSYCHIC, CHANGE, WALL, null, -1, 30, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return new OnBattleField(atkField.updateLightScreen(LightScreen.enableLightScreen(atkField.lightScreen())), dfcField, weather);
        }
    },
    REFLECT("リフレクター", PSYCHIC, CHANGE, WALL, null, -1, 20, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return new OnBattleField(atkField.updateReflect(Reflect.enableReflect(atkField.reflect())), dfcField, weather);
        }
    },
    /**
     * ここから天候技
     */
    SUNNY_DAY("にほんばれ", FIRE, CHANGE, WEATHER, null, -1, 5, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return new OnBattleField(atkField, dfcField, Weather.changeWeather(weather, DROUGHT));
        }
    },
    RAIN_DANCE("あまごい", WATER, CHANGE, WEATHER, null, -1, 5, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return new OnBattleField(atkField, dfcField, Weather.changeWeather(weather, RAIN));
        }
    },
    SAND_STORM("すなあらし", ROCK, CHANGE, WEATHER, null, -1, 10, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return new OnBattleField(atkField, dfcField, Weather.changeWeather(weather, SANDSTORM));
        }
    },
    HAIL("あられ", ICE, CHANGE, WEATHER, null, -1, 10, 0, 0, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return new OnBattleField(atkField, dfcField, Weather.changeWeather(weather, WeatherEnum.HAIL));
        }
    };

    // 粉技、草タイプには無効
    public static final Set<BaseMvPrm> powderMove = new HashSet<>(Arrays.asList(SLEEP_POWDER, SPORE));

    // 固定ダメージ、一撃技リスト
    public static final Set<BaseMvPrm> constDmgMove = new HashSet<>(Arrays.asList(SEISMIC_TOSS, SHEER_COLD, FISSURE, ENDEAVOR));

    // わざめい
    private final String mvName;
    // わざタイプ
    private final Type moveType;
    // 分類
    private final MoveSpecies moveSpecies;
    // 詳細な分類 CPUの技選択時に使用
    private final DetailMvSpecies detailedSpecies;
    // 威力
    private final Integer damage;
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

    public Integer damage() {
        return this.damage;
    }

    public int damage(Field atkField, Field dfcField) {
        PokeInfo atkPk = atkField.poke();
        PokeInfo dfcPk = dfcField.poke();
        int result;
        switch (this) {
            case ERUPTION, WATER_SPOUT -> result = 150 * atkPk.currentHP().val() / atkPk.realHP();
            case REVERSAL, FLAIL -> result = calcReversalDmg(atkField);
            case BRINE -> result = (double) (dfcPk.currentHP().val()) / (double) (dfcPk.realHP()) <= 0.5 ? this.damage * 2 : this.damage;
            case FACADE -> result = atkPk.ailment().isSick() ? this.damage * 2 : this.damage;
            case KNOCK_OFF -> result = dfcPk.item() != Item.NONE ? 98 : this.damage;
            case VENOSHOCK -> {
                if (dfcPk.ailment().val() == AilmentEnum.POISON || dfcPk.ailment().val() == AilmentEnum.BAD_POISON) {
                    result = this.damage * 2;
                } else {
                    result = this.damage;
                }
            }
            default -> result = this.damage;
        }
        return result;
    }

    // 固定ダメージ、一撃技のダメージ計算
    public int constDmgCalc(Field atkField, Field dfcField) {
        int result;
        switch (this) {
            case SEISMIC_TOSS -> result = atkField.poke().level().val();
            case SHEER_COLD, FISSURE -> result = dfcField.poke().currentHP().val();
            case ENDEAVOR -> result = Math.max(0, dfcField.poke().currentHP().val() - atkField.poke().currentHP().val());
            default -> result = this.damage;
        }
        return result;
    }

    public int hitRate() {
        return this.hitRate;
    }

    public int hitRate(Weather weather) {
        switch (this) {
            case THUNDER, HURRICANE -> {
                if (weather.val() == RAIN) {
                    return -1;
                } else if (weather.val() == DROUGHT) {
                    return this.hitRate / 2;
                } else {
                    return this.hitRate;
                }
            }
            case BLIZZARD -> {
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

    // 効果
    public abstract OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException;

    private static OnBattleField doNothing(Field atkField, Field dfcField, Weather weather) {
        return new OnBattleField(atkField, dfcField, weather);
    }

    private static OnBattleField recoilDmg25Per(Field atkField, Field dfcField, Weather weather, int damaged) throws InterruptedException {
        return new OnBattleField(atkField.updatePokeInfo(atkField.poke().damage(damaged / 4)), dfcField, weather);
    }

    private static OnBattleField recoilDmg33Per(Field atkField, Field dfcField, Weather weather, int damaged) throws InterruptedException {
        return new OnBattleField(atkField.updatePokeInfo(atkField.poke().damage(damaged / 3)), dfcField, weather);
    }

    private static OnBattleField recoilDmg50Per(Field atkField, Field dfcField, Weather weather, int damaged) throws InterruptedException {
        return new OnBattleField(atkField.updatePokeInfo(atkField.poke().damage(damaged / 2)), dfcField, weather);
    }

    private static OnBattleField recoilDmgHP50Per(Field atkField, Field dfcField, Weather weather) throws InterruptedException {
        int dmg = (atkField.poke().realHP() / 2) + 1;
        return new OnBattleField(atkField.updatePokeInfo(atkField.poke().damage(dmg)), dfcField, weather);
    }

    private static OnBattleField recoilDmgHP100Per(Field atkField, Field dfcField, Weather weather) throws InterruptedException {
        int dmg = atkField.poke().realHP();
        return new OnBattleField(atkField.updatePokeInfo(atkField.poke().damage(dmg)), dfcField, weather);
    }

    private static OnBattleField recoveryDmg50Per(Field atkField, Field dfcField, Weather weather, int damaged) throws InterruptedException {
        return new OnBattleField(atkField.updatePokeInfo(atkField.poke().recoveryHP(damaged / 2)), dfcField, weather);
    }

    private static OnBattleField recoveryHP50Per(Field atkField, Field dfcField, Weather weather) throws InterruptedException {
        int recovery = atkField.poke().realHP() / 2;
        return new OnBattleField(atkField.updatePokeInfo(atkField.poke().recoveryHP(recovery)), dfcField, weather);
    }

    private static OnBattleField recoveryHP100Per(Field atkField, Field dfcField, Weather weather) throws InterruptedException {
        int recovery = atkField.poke().realHP();
        return new OnBattleField(atkField.updatePokeInfo(atkField.poke().recoveryHP(recovery)), dfcField, weather);
    }

    private static OnBattleField recoveryByWeather(Field atkField, Field dfcField, Weather weather) throws InterruptedException {
        int recovery;
        switch (weather.val()) {
            case DROUGHT -> recovery = (int) ((double) atkField.poke().realHP() / 1.5d);
            case RAIN, SANDSTORM, HAIL -> recovery = atkField.poke().realHP() / 4;
            default -> recovery = atkField.poke().realHP() / 2;
        }
        return new OnBattleField(atkField.updatePokeInfo(atkField.poke().recoveryHP(recovery)), dfcField, weather);
    }

    private static OnBattleField myStatusRankCh(int percent, Field atkField, Field dfcField, Weather weather, int A, int B, int C, int D, int S, int HT, int AV) throws InterruptedException {
        if ((new Random().nextInt(10)) <= percent / 10 - 1) {
            return new OnBattleField(atkField.updatePokeInfo(atkField.poke().changeStatusRank(A, B, C, D, S, HT, AV)), dfcField, weather);
        } else {
            return doNothing(atkField, dfcField, weather);
        }
    }

    private static OnBattleField enemyStatusRankCh(int percent, Field atkField, Field dfcField, Weather weather, int A, int B, int C, int D, int S, int HT, int AV) throws InterruptedException {
        if ((new Random().nextInt(10)) <= percent / 10 - 1) {
            return new OnBattleField(atkField, dfcField.updatePokeInfo(dfcField.poke().changeStatusRank(A, B, C, D, S, HT, AV)), weather);
        } else {
            return doNothing(atkField, dfcField, weather);
        }
    }

    private static OnBattleField lostItem(Field atkField, Field dfcField, Weather weather) {
        return new OnBattleField(atkField, dfcField.updatePokeInfo(dfcField.poke().updateItem(Item.NONE)), weather);
    }

    private static OnBattleField beFlinch(int percent, Field atkField, Field dfcField, Weather weather) {
        if ((new Random().nextInt(10)) <= percent / 10 - 1) {
            return new OnBattleField(atkField, dfcField.updatePokeInfo(dfcField.poke().updateFlinch(new FlinchI(true))), weather);
        } else {
            return doNothing(atkField, dfcField, weather);
        }
    }

    private static OnBattleField beConfusion(int percent, Field atkField, Field dfcField, Weather weather) throws InterruptedException {
        if ((new Random().nextInt(10)) <= percent / 10 - 1) {
            return new OnBattleField(atkField, dfcField.updatePokeInfo(dfcField.poke().updateConfusion(ConfusionI.beConfusion(dfcField.poke()))), weather);
        } else {
            return doNothing(atkField, dfcField, weather);
        }
    }

    private static OnBattleField beBurn(int percent, Field atkField, Field dfcField, Weather weather) throws InterruptedException {
        if ((new Random().nextInt(10)) <= percent / 10 - 1) {
            return new OnBattleField(atkField, dfcField.updatePokeInfo(dfcField.poke().updateAilment(AilmentI.changeAilment(dfcField.poke(), AilmentEnum.BURN))), weather);
        } else {
            return doNothing(atkField, dfcField, weather);
        }
    }

    private static OnBattleField beParalysis(int percent, Field atkField, Field dfcField, Weather weather) throws InterruptedException {
        if ((new Random().nextInt(10)) <= percent / 10 - 1) {
            return new OnBattleField(atkField, dfcField.updatePokeInfo(dfcField.poke().updateAilment(AilmentI.changeAilment(dfcField.poke(), AilmentEnum.PARALYSIS))), weather);
        } else {
            return doNothing(atkField, dfcField, weather);
        }
    }

    private static OnBattleField beFreeze(int percent, Field atkField, Field dfcField, Weather weather) throws InterruptedException {
        if ((new Random().nextInt(10)) <= percent / 10 - 1) {
            return new OnBattleField(atkField, dfcField.updatePokeInfo(dfcField.poke().updateAilment(AilmentI.changeAilment(dfcField.poke(), AilmentEnum.FREEZE))), weather);
        } else {
            return doNothing(atkField, dfcField, weather);
        }
    }

    private static OnBattleField beSleep(int percent, Field atkField, Field dfcField, Weather weather) throws InterruptedException {
        if ((new Random().nextInt(10)) <= percent / 10 - 1) {
            return new OnBattleField(atkField, dfcField.updatePokeInfo(dfcField.poke().updateAilment(AilmentI.changeAilment(dfcField.poke(), AilmentEnum.SLEEP))), weather);
        } else {
            return doNothing(atkField, dfcField, weather);
        }
    }

    private static OnBattleField beSelfSleep(int percent, Field atkField, Field dfcField, Weather weather) throws InterruptedException {
        if ((new Random().nextInt(10)) <= percent / 10 - 1) {
            return new OnBattleField(atkField.updatePokeInfo(atkField.poke().updateAilment(AilmentI.changeAilment(atkField.poke(), AilmentEnum.SELF_SLEEP))), dfcField, weather);
        } else {
            return doNothing(atkField, dfcField, weather);
        }
    }

    private static OnBattleField bePoison(int percent, Field atkField, Field dfcField, Weather weather) throws InterruptedException {
        if ((new Random().nextInt(10)) <= percent / 10 - 1) {
            return new OnBattleField(atkField, dfcField.updatePokeInfo(dfcField.poke().updateAilment(AilmentI.changeAilment(dfcField.poke(), AilmentEnum.POISON))), weather);
        } else {
            return doNothing(atkField, dfcField, weather);
        }
    }

    private static OnBattleField beBadPoison(int percent, Field atkField, Field dfcField, Weather weather) throws InterruptedException {
        if ((new Random().nextInt(10)) <= percent / 10 - 1) {
            return new OnBattleField(atkField, dfcField.updatePokeInfo(dfcField.poke().updateAilment(AilmentI.changeAilment(dfcField.poke(), AilmentEnum.BAD_POISON))), weather);
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
