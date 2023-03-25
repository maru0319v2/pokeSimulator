package move;

import Enum.MoveSpecies;
import Enum.Type;
import field.*;
import lombok.AllArgsConstructor;
import pokemonStatus.impl.ConfusionI;
import pokemonStatus.impl.FlinchI;
import statusAilment.AilmentEnum;
import statusAilment.AilmentI;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static field.WeatherEnum.*;

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
    EXTREME_SPEED("しんそく", Type.NORMAL, MoveSpecies.PHYSICAL, DetailMvSpecies.PRIORITY, 80, 100, 5, 0, 2,
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
    SLASH("きりさく", Type.NORMAL, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 70, 100, 20, 1, 0,
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
    SELF_DESTRUCT("じばく", Type.NORMAL, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 200, 100, 5, 0, 0,
            false, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int damaged, Weather weather) throws InterruptedException {
            return recoilDmgHP100Per(atkField, dfcField, weather);
        }
    },
    EXPLOSION("だいばくはつ", Type.NORMAL, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 250, 100, 5, 0, 0,
            false, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int damaged, Weather weather) throws InterruptedException {
            return recoilDmgHP100Per(atkField, dfcField, weather);
        }
    },
    AERIAL_ACE("つばめがえし", Type.FLYING, MoveSpecies.PHYSICAL, DetailMvSpecies.HIT, 60, -1, 20, 0, 0,
            true, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) {
            return doNothing(atkField, dfcField, weather);
        }
    },
    DRILL_PECK("ドリルくちばし", Type.FLYING, MoveSpecies.PHYSICAL, DetailMvSpecies.HIT, 80, 100, 20, 0, 0,
            true, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) {
            return doNothing(atkField, dfcField, weather);
        }
    },
    BRAVE_BIRD("ブレイブバード", Type.FLYING, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 120, 100, 15, 0, 0,
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
    HURRICANE("ぼうふう", Type.FLYING, MoveSpecies.SPECIAL, DetailMvSpecies.DAMAGE, 110, 70, 10, 0, 0,
            true, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return beConfusion(30, atkField, dfcField, weather);
        }
    },
    MACH_PUNCH("マッハパンチ", Type.FIGHTING, MoveSpecies.PHYSICAL, DetailMvSpecies.PRIORITY, 40, 100, 30, 0, 0,
            true, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return doNothing(atkField, dfcField, weather);
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
    HAMMER_ARM("アームハンマー", Type.FIGHTING, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 100, 90, 10, 0, 0,
            true, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return myStatusRankCh(100, atkField, dfcField, weather, 0, 0, 0, 0, -1, 0, 0);
        }
    },
    DYNAMIC_PUNCH("ばくれつパンチ", Type.FIGHTING, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 100, 50, 5, 0, 0,
            true, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return beConfusion(100, atkField, dfcField, weather);
        }
    },
    AURA_SPHERE("はどうだん", Type.FIGHTING, MoveSpecies.SPECIAL, DetailMvSpecies.HIT, 80, -1, 20, 0, 0,
            false, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return doNothing(atkField, dfcField, weather);
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
    DRAIN_PUNCH("ドレインパンチ", Type.FIGHTING, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 75, 100, 10, 0, 0,
            true, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return recoveryDmg50Per(atkField, dfcField, weather, recoveryHP);
        }
    },
    CRESS_CHOP("クロスチョップ", Type.FIGHTING, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 100, 80, 5, 1, 0,
            true, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) {
            return doNothing(atkField, dfcField, weather);
        }
    },
    BULLDOZE("じならし", Type.GROUND, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 60, 100, 10, 0, 0,
            false, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return enemyStatusRankCh(100, atkField, dfcField, weather, 0, 0, 0, 0, -1, 0, 0);
        }
    },
    EARTH_POWER("だいちのちから", Type.GROUND, MoveSpecies.SPECIAL, DetailMvSpecies.DAMAGE, 90, 100, 10, 0, 0,
            false, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return enemyStatusRankCh(10, atkField, dfcField, weather, 0, 0, 0, -1, 0, 0, 0);
        }
    },
    EARTHQUAKE("じしん", Type.GROUND, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 100, 100, 10, 0, 0,
            false, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) {
            return doNothing(atkField, dfcField, weather);
        }
    },
    SLUDGE_BOMB("ヘドロばくだん", Type.POISON, MoveSpecies.SPECIAL, DetailMvSpecies.DAMAGE, 90, 100, 10, 0, 0,
            false, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return bePoison(10, atkField, dfcField, weather);
        }
    },
    FLAME_CHARGE("ニトロチャージ", Type.FIRE, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 50, 100, 20, 0, 0,
            true, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return myStatusRankCh(100, atkField, dfcField, weather, 0, 0, 0, 0, 1, 0, 0);
        }
    },
    FLAMETHROWER("かえんほうしゃ", Type.FIRE, MoveSpecies.SPECIAL, DetailMvSpecies.DAMAGE, 90, 100, 15, 0, 0,
            false, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return beBurn(10, atkField, dfcField, weather);
        }
    },
    FIRE_BLAST("だいもんじ", Type.FIRE, MoveSpecies.SPECIAL, DetailMvSpecies.DAMAGE, 110, 85, 5, 0, 0,
            false, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return beBurn(10, atkField, dfcField, weather);
        }
    },
    FLARE_BLITZ("フレアドライブ", Type.FIRE, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 120, 100, 15, 0, 0,
            true, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int damaged, Weather weather) throws InterruptedException {
            OnBattleField onBf = beBurn(10, atkField, dfcField, weather);
            return recoilDmg33Per(onBf.atkField(), onBf.dfcField(), onBf.weather(), damaged);
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
    WILD_CHARGE("ワイルドボルト", Type.ELECTRIC, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 90, 100, 15, 0, 0,
            true, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int damaged, Weather weather) throws InterruptedException {
            return recoilDmg25Per(atkField, dfcField, weather, damaged);
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
    DRACO_METEOR("りゅうせいぐん", Type.DRAGON, MoveSpecies.SPECIAL, DetailMvSpecies.DAMAGE, 130, 90, 5, 0, 0,
            false, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return myStatusRankCh(100, atkField, dfcField, weather, 0, 0, -2, 0, 0, 0, 0);
        }
    },
    AQUA_JET("アクアジェット", Type.WATER, MoveSpecies.PHYSICAL, DetailMvSpecies.PRIORITY, 40, 100, 20, 0, 1,
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
    WATERFALL("たきのぼり", Type.WATER, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 80, 100, 15, 0, 0,
            true, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) {
            return beFlinch(20, atkField, dfcField, weather);
        }
    },
    SCALD("ねっとう", Type.WATER, MoveSpecies.SPECIAL, DetailMvSpecies.DAMAGE, 80, 100, 15, 0, 0,
            false, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return beBurn(30, atkField, dfcField, weather);
        }
    },
    SURF("なみのり", Type.WATER, MoveSpecies.SPECIAL, DetailMvSpecies.DAMAGE, 90, 100, 15, 0, 0,
            false, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) {
            return doNothing(atkField, dfcField, weather);
        }
    },
    HYDRO_PUMP("ハイドロポンプ", Type.WATER, MoveSpecies.SPECIAL, DetailMvSpecies.DAMAGE, 110, 80, 5, 0, 0,
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
    ICE_BEAM("れいとうビーム", Type.ICE, MoveSpecies.SPECIAL, DetailMvSpecies.DAMAGE, 90, 100, 10, 0, 0,
            false, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return beFreeze(10, atkField, dfcField, weather);
        }
    },
    BLIZZARD("ふぶき", Type.ICE, MoveSpecies.SPECIAL, DetailMvSpecies.DAMAGE, 110, 70, 5, 0, 0,
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
    SEED_BOMB("タネばくだん", Type.GRASS, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 80, 100, 15, 0, 0,
            false, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return doNothing(atkField, dfcField, weather);
        }
    },
    GIGA_DRAIN("ギガドレイン", Type.GRASS, MoveSpecies.SPECIAL, DetailMvSpecies.DAMAGE, 75, 100, 10, 0, 0,
            false, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return recoveryDmg50Per(atkField, dfcField, weather, recoveryHP);
        }
    },
    WOOD_HAMMER("ウッドハンマー", Type.GRASS, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 120, 100, 15, 0, 0,
            true, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int damaged, Weather weather) throws InterruptedException {
            return recoilDmg33Per(atkField, dfcField, weather, damaged);
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
    BUG_BUZZ("むしのさざめき", Type.BUG, MoveSpecies.SPECIAL, DetailMvSpecies.DAMAGE, 90, 100, 10, 0, 0,
            false, false, true, false, false, true) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return enemyStatusRankCh(10, atkField, dfcField, weather, 0, 0, 0, -1, 0, 0, 0);
        }
    },
    MEGA_HORN("メガホーン", Type.BUG, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 120, 85, 10, 0, 0,
            true, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) {
            return doNothing(atkField, dfcField, weather);
        }
    },
    BULLET_PUNCH("バレットパンチ", Type.STEEL, MoveSpecies.PHYSICAL, DetailMvSpecies.PRIORITY, 40, 100, 30, 0, 1,
            true, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return doNothing(atkField, dfcField, weather);
        }
    },
    FLASH_CANNON("ラスターカノン", Type.STEEL, MoveSpecies.SPECIAL, DetailMvSpecies.DAMAGE, 80, 100, 10, 0, 0,
            false, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return enemyStatusRankCh(10, atkField, dfcField, weather, 0, 0, 0, -1, 0, 0, 0);
        }
    },
    IRON_TAIL("アイアンテール", Type.STEEL, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 100, 75, 15, 0, 0,
            true, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return enemyStatusRankCh(30, atkField, dfcField, weather, 0, -1, 0, 0, 0, 0, 0);
        }
    },
    METEOR_MASH("コメットパンチ", Type.STEEL, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 90, 90, 10, 0, 0,
            true, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return myStatusRankCh(20, atkField, dfcField, weather, 1, 0, 0, 0, 0, 0, 0);
        }
    },
    STEEL_BEAM("てっていこうせん", Type.STEEL, MoveSpecies.SPECIAL, DetailMvSpecies.DAMAGE, 140, 95, 5, 0, 0,
            false, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int damaged, Weather weather) throws InterruptedException {
            return recoilDmgHP50Per(atkField, dfcField, weather);
        }
    },
    ROCK_TOMB("がんせきふうじ", Type.ROCK, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 60, 95, 15, 0, 0,
            false, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return enemyStatusRankCh(100, atkField, dfcField, weather, 0, 0, 0, 0, -1, 0, 0);
        }
    },
    ROCK_SLIDE("いわなだれ", Type.ROCK, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 75, 90, 10, 0, 0,
            false, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) {
            return beFlinch(30, atkField, dfcField, weather);
        }
    },
    STONE_EDGE("ストーンエッジ", Type.ROCK, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 100, 80, 5, 1, 0,
            false, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) {
            return doNothing(atkField, dfcField, weather);
        }
    },
    NIGHT_SLASH("つじぎり", Type.DARK, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 70, 100, 15, 1, 0,
            true, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return doNothing(atkField, dfcField, weather);
        }
    },
    CRUNCH("かみくだく", Type.DARK, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 80, 100, 15, 0, 0,
            true, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return enemyStatusRankCh(20, atkField, dfcField, weather, 0, -1, 0, 0, 0, 0, 0);
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
    SHADOW_BALL("シャドーボール", Type.GHOST, MoveSpecies.SPECIAL, DetailMvSpecies.DAMAGE, 80, 100, 15, 0, 0,
            false, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return enemyStatusRankCh(20, atkField, dfcField, weather, 0, 0, 0, -1, 0, 0, 0);
        }
    },
    PLAY_ROUGH("じゃれつく", Type.FAIRY, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 90, 90, 10, 0, 0,
            true, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return enemyStatusRankCh(10, atkField, dfcField, weather, -1, 0, 0, 0, 0, 0, 0);
        }
    },
    DAZZLING_GLEAM("マジカルシャイン", Type.FAIRY, MoveSpecies.SPECIAL, DetailMvSpecies.DAMAGE, 80, 100, 10, 0, 0,
            false, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return doNothing(atkField, dfcField, weather);
        }
    },
    MOONBLAST("ムーンフォース", Type.FAIRY, MoveSpecies.SPECIAL, DetailMvSpecies.DAMAGE, 95, 100, 15, 0, 0,
            false, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return enemyStatusRankCh(30, atkField, dfcField, weather, 0, 0, -1, 0, 0, 0, 0);
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
    SHELL_SMASH("からをやぶる", Type.NORMAL, MoveSpecies.CHANGE, DetailMvSpecies.UP_A, 0, 100, 15, 0, 0,
            false, true, true, false, true, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return myStatusRankCh(100, atkField, dfcField, weather, 2, -1, 2, -1, 2, 0, 0);
        }
    },
    MINIMIZE("ちいさくなる", Type.NORMAL, MoveSpecies.CHANGE, DetailMvSpecies.UP_AV, 0, 100, 10, 0, 0,
            false, true, true, false, true, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return myStatusRankCh(100, atkField, dfcField, weather, 0, 0, 0, 0, 0, 0, 2);
        }
    },
    BELLY_DRUM("はらだいこ", Type.NORMAL, MoveSpecies.CHANGE, DetailMvSpecies.UP_AV, 0, -1, 10, 0, 0,
            false, true, true, false, true, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            OnBattleField onBf = recoilDmgHP50Per(atkField, dfcField, weather);
            return myStatusRankCh(100, onBf.atkField(), onBf.dfcField(), onBf.weather(), 12, 0, 0, 0, 0, 0, 0);
        }
    },
    CHARM("あまえる", Type.PSYCHIC, MoveSpecies.CHANGE, DetailMvSpecies.DOWN_A, 0, 100, 20, 0, 0,
            false, true, true, false, true, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return enemyStatusRankCh(100, atkField, dfcField, weather, -2, 0, 0, 0, 0, 0, 0);
        }
    },
    CALM_MIND("めいそう", Type.PSYCHIC, MoveSpecies.CHANGE, DetailMvSpecies.UP_C, 0, 100, 20, 0, 0,
            false, false, false, false, true, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return myStatusRankCh(100, atkField, dfcField, weather, 0, 0, 1, 1, 0, 0, 0);
        }
    },
    NASTY_PLOT("わるだくみ", Type.DARK, MoveSpecies.CHANGE, DetailMvSpecies.UP_C, 0, 100, 20, 0, 0,
            false, true, true, false, true, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return myStatusRankCh(100, atkField, dfcField, weather, 0, 0, 2, 0, 0, 0, 0);
        }
    },
    MEMENTO("おきみやげ", Type.DARK, MoveSpecies.CHANGE, DetailMvSpecies.DOWN_A, 0, 100, 10, 0, 0,
            false, false, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            OnBattleField onBf = enemyStatusRankCh(100, atkField, dfcField, weather, -2, 0, -2, 0, 0, 0, 0);
            return recoilDmgHP100Per(onBf.atkField(), onBf.dfcField(), onBf.weather());
        }
    },
    HONE_CLAWS("つめとぎ", Type.DARK, MoveSpecies.CHANGE, DetailMvSpecies.UP_A, 0, 100, 15, 0, 0,
            false, false, true, false, true, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return myStatusRankCh(100, atkField, dfcField, weather, 1, 0, 0, 0, 0, 1, 0);
        }
    },
    DRAGON_DANCE("りゅうのまい", Type.DRAGON, MoveSpecies.CHANGE, DetailMvSpecies.UP_A, 0, 100, 20, 0, 0,
            false, true, true, false, true, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return myStatusRankCh(100, atkField, dfcField, weather, 1, 0, 0, 0, 1, 0, 0);
        }
    },
    QUIVER_DANCE("ちょうのまい", Type.BUG, MoveSpecies.CHANGE, DetailMvSpecies.UP_C, 0, 100, 20, 0, 0,
            false, true, true, false, true, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return myStatusRankCh(100, atkField, dfcField, weather, 0, 0, 1, 1, 1, 0, 0);
        }
    },
    BULK_UP("ビルドアップ", Type.FIGHTING, MoveSpecies.CHANGE, DetailMvSpecies.UP_A, 0, 100, 20, 0, 0,
            false, true, true, false, true, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return myStatusRankCh(100, atkField, dfcField, weather, 1, 1, 0, 0, 0, 0, 0);
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
    TOXIC("どくどく", Type.POISON, MoveSpecies.CHANGE, DetailMvSpecies.AILMENT, 0, 90, 10, 0, 0,
            false, true, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return BaseMvPrm.beBadPoison(100, atkField, dfcField, weather);
        }
    },
    THUNDER_WAVE("でんじは", Type.ELECTRIC, MoveSpecies.CHANGE, DetailMvSpecies.AILMENT, 0, 90, 20, 0, 0,
            false, true, true, false, false, false) {
        // TODO 多分地面タイプにも効く
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return BaseMvPrm.beParalysis(100, atkField, dfcField, weather);
        }
    },
    SWAGGER("いばる", Type.NORMAL, MoveSpecies.CHANGE, DetailMvSpecies.CONFUSE, 0, 85, 15, 0, 0,
            false, true, true, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            OnBattleField onBf = enemyStatusRankCh(100, atkField, dfcField, weather, 2, 0, 0, 0, 0, 0, 0);
            return beConfusion(100, onBf.atkField(), onBf.dfcField(), onBf.weather());
        }
    },
    /**
     * ここからその他変化技
     */
    RECOVER("じこさいせい", Type.NORMAL, MoveSpecies.CHANGE, DetailMvSpecies.RECOVERY, 0, -1, 5, 0, 0,
            false, false, false, false, true, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return recoveryHP50Per(atkField, dfcField, weather);
        }
    },
    SLACK_OFF("なまける", Type.NORMAL, MoveSpecies.CHANGE, DetailMvSpecies.RECOVERY, 0, -1, 5, 0, 0,
            false, false, false, false, true, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return recoveryHP50Per(atkField, dfcField, weather);
        }
    },
    SOFT_BOILED("タマゴうみ", Type.NORMAL, MoveSpecies.CHANGE, DetailMvSpecies.RECOVERY, 0, -1, 5, 0, 0,
            false, false, false, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return recoveryHP50Per(atkField, dfcField, weather);
        }
    },
    MOONLIGHT("つきのひかり", Type.PSYCHIC, MoveSpecies.CHANGE, DetailMvSpecies.RECOVERY, 0, -1, 5, 0, 0,
            false, false, false, false, true, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return recoveryByWeather(atkField, dfcField, weather);
        }
    },
    SYNTHESIS("こうごうせい", Type.GRASS, MoveSpecies.CHANGE, DetailMvSpecies.RECOVERY, 0, -1, 5, 0, 0,
            false, false, false, false, true, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return recoveryByWeather(atkField, dfcField, weather);
        }
    },
    LIGHT_SCREEN("ひかりのかべ", Type.PSYCHIC, MoveSpecies.CHANGE, DetailMvSpecies.WALL, 0, -1, 30, 0, 0,
            false, false, false, false, true, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return new OnBattleField(atkField.updateLightScreen(LightScreen.enableLightScreen(atkField.lightScreen())), dfcField, weather);
        }
    },
    REFLECT("リフレクター", Type.PSYCHIC, MoveSpecies.CHANGE, DetailMvSpecies.WALL, 0, -1, 20, 0, 0,
            false, false, false, false, true, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return new OnBattleField(atkField.updateReflect(Reflect.enableReflect(atkField.reflect())), dfcField, weather);
        }
    },
    /**
     * ここから天候技
     */
    SUNNY_DAY("にほんばれ", Type.FIRE, MoveSpecies.CHANGE, DetailMvSpecies.WEATHER, 0, -1, 5, 0, 0,
            false, false, false, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return new OnBattleField(atkField, dfcField, Weather.changeWeather(weather, DROUGHT));
        }
    },
    RAIN_DANCE("あまごい", Type.WATER, MoveSpecies.CHANGE, DetailMvSpecies.WEATHER, 0, -1, 5, 0, 0,
            false, false, false, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return new OnBattleField(atkField, dfcField, Weather.changeWeather(weather, RAIN));
        }
    },
    SAND_STORM("すなあらし", Type.ROCK, MoveSpecies.CHANGE, DetailMvSpecies.WEATHER, 0, -1, 10, 0, 0,
            false, false, false, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return new OnBattleField(atkField, dfcField, Weather.changeWeather(weather, SANDSTORM));
        }
    },
    HAIL("あられ", Type.ICE, MoveSpecies.CHANGE, DetailMvSpecies.WEATHER, 0, -1, 10, 0, 0,
            false, false, false, false, false, false) {
        public OnBattleField effect(Field atkField, Field dfcField, int recoveryHP, Weather weather) throws InterruptedException {
            return new OnBattleField(atkField, dfcField, Weather.changeWeather(weather, WeatherEnum.HAIL));
        }
    };

    // 粉技、草タイプには無効
    public static final Set<BaseMvPrm> powderMove = new HashSet<>(Arrays.asList(BaseMvPrm.SLEEP_POWDER, BaseMvPrm.SPORE));

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

    private static OnBattleField recoilDmg25Per(Field atkField, Field dfcField, Weather weather, int damaged) throws InterruptedException {
        return new OnBattleField(atkField.updatePokeInfo(atkField.poke().damage(damaged / 4)), dfcField, weather);
    }

    private static OnBattleField recoilDmg33Per(Field atkField, Field dfcField, Weather weather, int damaged) throws InterruptedException {
        return new OnBattleField(atkField.updatePokeInfo(atkField.poke().damage(damaged / 3)), dfcField, weather);
    }

    private static OnBattleField recoilDmgHP50Per(Field atkField, Field dfcField, Weather weather) throws InterruptedException {
        int dmg = atkField.poke().realHP() / 2;
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
