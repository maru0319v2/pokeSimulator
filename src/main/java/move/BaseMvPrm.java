package move;

import Enum.MoveSpecies;
import Enum.Type;
import field.Field;
import field.OnBattleField;
import field.Weather;
import field.WeatherEnum;
import lombok.AllArgsConstructor;
import pokemon.PokeInfo;
import pokemonStatus.impl.CurrentHPI;
import pokemonStatus.impl.FlinchI;
import statusAilment.AilmentEnum;

import java.util.Random;

import static field.Weather.changeWeather;
import static pokemonStatus.impl.ConfusionI.beConfusion;
import static statusAilment.AilmentI.changeAilment;

@AllArgsConstructor
public enum BaseMvPrm {
    /**
     * ここから通常攻撃
     */
    TACKLE("たいあたり", Type.NORMAL, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 40, 95, 35, 0, 0,
            true, false, true, false, false, false) {
        public OnBattleField effect(PokeInfo atkPk, PokeInfo dfcPk, Field field, int recoveryHP, Weather weather) {
            return new OnBattleField(atkPk, dfcPk, field, weather);
        }
    },
    QUICK_ATTACK("でんこうせっか", Type.NORMAL, MoveSpecies.PHYSICAL, DetailMvSpecies.PRIORITY, 40, 100, 30, 0, 1,
            true, false, true, false, false, false) {
        public OnBattleField effect(PokeInfo atkPk, PokeInfo dfcPk, Field field, int recoveryHP, Weather weather) {
            return new OnBattleField(atkPk, dfcPk, field, weather);
        }
    },
    FLAIL("じたばた", Type.NORMAL, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 20, 100, 15, 0, 0,
            true, false, true, false, false, false) {
        public OnBattleField effect(PokeInfo atkPk, PokeInfo dfcPk, Field field, int recoveryHP, Weather weather) {
            return new OnBattleField(atkPk, dfcPk, field, weather);
        }
    },
    DOUBLE_EDGE("すてみタックル", Type.NORMAL, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 120, 100, 15, 0, 0,
            true, false, true, false, false, false) {
        public OnBattleField effect(PokeInfo atkPk, PokeInfo dfcPk, Field field, int damaged, Weather weather) throws InterruptedException {
            return new OnBattleField(atkPk.damage(damaged / 3), dfcPk, field, weather);
        }
    },
    AERIAL_ACE("つばめがえし", Type.FLYING, MoveSpecies.PHYSICAL, DetailMvSpecies.HIT, 60, -1, 20, 0, 0,
            true, false, true, false, false, false) {
        public OnBattleField effect(PokeInfo atkPk, PokeInfo dfcPk, Field field, int recoveryHP, Weather weather) {
            return new OnBattleField(atkPk, dfcPk, field, weather);
        }
    },
    BRAVE_BIRD("ブレイブバード", Type.FIGHTING, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 120, 100, 15, 0, 0,
            true, false, true, false, false, false) {
        public OnBattleField effect(PokeInfo atkPk, PokeInfo dfcPk, Field field, int damaged, Weather weather) throws InterruptedException {
            return new OnBattleField(atkPk.damage(damaged / 3), dfcPk, field, weather);
        }
    },
    AIR_SLASH("エアスラッシュ", Type.FLYING, MoveSpecies.SPECIAL, DetailMvSpecies.DAMAGE, 75, 95, 15, 0, 0,
            false, false, true, false, false, false) {
        public OnBattleField effect(PokeInfo atkPk, PokeInfo dfcPk, Field field, int recoveryHP, Weather weather) {
            if (random30Per()) return new OnBattleField(atkPk, dfcPk.withFlinch(new FlinchI(true)), field, weather);
            return new OnBattleField(atkPk, dfcPk, field, weather);
        }
    },
    CLOSE_COMBAT("インファイト", Type.FIGHTING, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 120, 100, 5, 0, 0,
            true, false, true, false, false, false) {
        public OnBattleField effect(PokeInfo atkPk, PokeInfo dfcPk, Field field, int recoveryHP, Weather weather) throws InterruptedException {
            return new OnBattleField(atkPk.withChStatusRank(0, -1, 0, -1, 0, 0, 0), dfcPk, field, weather);
        }
    },
    SUPERPOWER("ばかぢから", Type.FIGHTING, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 120, 100, 5, 0, 0,
            true, false, true, false, false, false) {
        public OnBattleField effect(PokeInfo atkPk, PokeInfo dfcPk, Field field, int recoveryHP, Weather weather) throws InterruptedException {
            return new OnBattleField(atkPk.withChStatusRank(-1, -1, 0, 0, 0, 0, 0), dfcPk, field, weather);
        }
    },
    POWER_UP_PUNCH("グロウパンチ", Type.FIGHTING, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 40, 100, 20, 0, 0,
            true, false, true, false, false, false) {
        public OnBattleField effect(PokeInfo atkPk, PokeInfo dfcPk, Field field, int recoveryHP, Weather weather) throws InterruptedException {
            return new OnBattleField(atkPk.withChStatusRank(1, 0, 0, 0, 0, 0, 0), dfcPk, field, weather);
        }
    },
    CRESS_CHOP("クロスチョップ", Type.FIGHTING, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 100, 80, 5, 1, 0,
            true, false, true, false, false, false) {
        public OnBattleField effect(PokeInfo atkPk, PokeInfo dfcPk, Field field, int recoveryHP, Weather weather) {
            return new OnBattleField(atkPk, dfcPk, field, weather);
        }
    },
    EARTHQUAKE("じしん", Type.GROUND, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 100, 100, 10, 0, 0,
            false, false, true, false, false, false) {
        public OnBattleField effect(PokeInfo atkPk, PokeInfo dfcPk, Field field, int recoveryHP, Weather weather) {
            return new OnBattleField(atkPk, dfcPk, field, weather);
        }
    },
    FLAMETHROWER("かえんほうしゃ", Type.FIRE, MoveSpecies.SPECIAL, DetailMvSpecies.DAMAGE, 90, 100, 15, 0, 0,
            false, false, true, false, false, false) {
        public OnBattleField effect(PokeInfo atkPk, PokeInfo dfcPk, Field field, int recoveryHP, Weather weather) throws InterruptedException {
            if (random10Per()) return new OnBattleField(atkPk, dfcPk.withAilment(changeAilment(dfcPk, AilmentEnum.BURN)), field, weather);
            return new OnBattleField(atkPk, dfcPk, field, weather);
        }
    },
    OVERHEAT("オーバーヒート", Type.FIRE, MoveSpecies.SPECIAL, DetailMvSpecies.DAMAGE, 130, 90, 5, 0, 0,
            false, false, true, false, false, false) {
        public OnBattleField effect(PokeInfo atkPk, PokeInfo dfcPk, Field field, int recoveryHP, Weather weather) throws InterruptedException {
            return new OnBattleField(atkPk.withChStatusRank(0, 0, -2, 0, 0, 0, 0), dfcPk, field, weather);
        }
    },
    ERUPTION("ふんか", Type.FIRE, MoveSpecies.SPECIAL, DetailMvSpecies.DAMAGE, 150, 100, 5, 0, 0,
            false, false, true, false, false, false) {
        public OnBattleField effect(PokeInfo atkPk, PokeInfo dfcPk, Field field, int recoveryHP, Weather weather) throws InterruptedException {
            return new OnBattleField(atkPk, dfcPk, field, weather);
        }
    },
    THUNDERBOLT("10まんボルト", Type.ELECTRIC, MoveSpecies.SPECIAL, DetailMvSpecies.DAMAGE, 90, 100, 15, 0, 0,
            false, false, true, false, false, false) {
        public OnBattleField effect(PokeInfo atkPk, PokeInfo dfcPk, Field field, int recoveryHP, Weather weather) throws InterruptedException {
            if (random10Per()) return new OnBattleField(atkPk, dfcPk.withAilment(changeAilment(dfcPk, AilmentEnum.PARALYSIS)), field, weather);
            return new OnBattleField(atkPk, dfcPk, field, weather);
        }
    },
    THUNDER("かみなり", Type.ELECTRIC, MoveSpecies.SPECIAL, DetailMvSpecies.DAMAGE, 110, 70, 10, 0, 0,
            false, false, true, false, false, false) {
        public OnBattleField effect(PokeInfo atkPk, PokeInfo dfcPk, Field field, int recoveryHP, Weather weather) throws InterruptedException {
            if (random30Per()) return new OnBattleField(atkPk, dfcPk.withAilment(changeAilment(dfcPk, AilmentEnum.PARALYSIS)), field, weather);
            return new OnBattleField(atkPk, dfcPk, field, weather);
        }
    },
    DRAGON_CLAW("ドラゴンクロー", Type.DRAGON, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 80, 100, 15, 0, 0,
            true, false, true, false, false, false) {
        public OnBattleField effect(PokeInfo atkPk, PokeInfo dfcPk, Field field, int recoveryHP, Weather weather) {
            return new OnBattleField(atkPk, dfcPk, field, weather);
        }
    },
    WATER_PULSE("みずのはどう", Type.WATER, MoveSpecies.SPECIAL, DetailMvSpecies.DAMAGE, 60, 100, 20, 0, 0,
            false, false, true, false, false, false) {
        public OnBattleField effect(PokeInfo atkPk, PokeInfo dfcPk, Field field, int recoveryHP, Weather weather) throws InterruptedException {
            if (random20Per()) return new OnBattleField(atkPk, dfcPk.withConfusion(beConfusion(dfcPk)), field, weather);
            return new OnBattleField(atkPk, dfcPk, field, weather);
        }
    },
    BRINE("しおみず", Type.WATER, MoveSpecies.SPECIAL, DetailMvSpecies.DAMAGE, 65, 100, 10, 0, 0,
            false, false, true, false, false, false) {
        public OnBattleField effect(PokeInfo atkPk, PokeInfo dfcPk, Field field, int recoveryHP, Weather weather) {
            return new OnBattleField(atkPk, dfcPk, field, weather);
        }
    },
    WATER_SPOUT("しおふき", Type.WATER, MoveSpecies.SPECIAL, DetailMvSpecies.DAMAGE, 150, 100, 5, 0, 0,
            false, false, true, false, false, false) {
        public OnBattleField effect(PokeInfo atkPk, PokeInfo dfcPk, Field field, int recoveryHP, Weather weather) {
            return new OnBattleField(atkPk, dfcPk, field, weather);
        }
    },
    ICE_SHARD("こおりのつぶて", Type.ICE, MoveSpecies.PHYSICAL, DetailMvSpecies.PRIORITY, 40, 100, 30, 0, 1,
            false, false, true, false, false, false) {
        public OnBattleField effect(PokeInfo atkPk, PokeInfo dfcPk, Field field, int recoveryHP, Weather weather) {
            return new OnBattleField(atkPk, dfcPk, field, weather);
        }
    },
    Blizzard("ふぶき", Type.ICE, MoveSpecies.SPECIAL, DetailMvSpecies.DAMAGE, 110, 70, 5, 0, 0,
            false, false, true, false, false, false) {
        public OnBattleField effect(PokeInfo atkPk, PokeInfo dfcPk, Field field, int recoveryHP, Weather weather) throws InterruptedException {
            if (random10Per()) return new OnBattleField(atkPk, dfcPk.withAilment(changeAilment(dfcPk, AilmentEnum.FREEZE)), field, weather);
            return new OnBattleField(atkPk, dfcPk, field, weather);
        }
    },
    VINE_WHIP("つるのムチ", Type.GRASS, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 45, 100, 25, 0, 0,
            true, false, true, false, false, false) {
        public OnBattleField effect(PokeInfo atkPk, PokeInfo dfcPk, Field field, int recoveryHP, Weather weather) {
            return new OnBattleField(atkPk, dfcPk, field, weather);
        }
    },
    ENERGY_BALL("エナジーボール", Type.GRASS, MoveSpecies.SPECIAL, DetailMvSpecies.DAMAGE, 90, 100, 10, 0, 0,
            false, false, true, false, false, false) {
        public OnBattleField effect(PokeInfo atkPk, PokeInfo dfcPk, Field field, int recoveryHP, Weather weather) throws InterruptedException {
            if (random10Per()) return new OnBattleField(atkPk, dfcPk.withChStatusRank(0, 0, 0, -1, 0, 0, 0), field, weather);
            return new OnBattleField(atkPk, dfcPk, field, weather);
        }
    },
    GIGA_DRAIN("ギガドレイン", Type.GRASS, MoveSpecies.SPECIAL, DetailMvSpecies.DAMAGE, 75, 100, 10, 0, 0,
            false, false, true, false, false, false) {
        public OnBattleField effect(PokeInfo atkPk, PokeInfo dfcPk, Field field, int recoveryHP, Weather weather) throws InterruptedException {
            int recovery = recoveryHP / 2;
            return new OnBattleField(atkPk.withCurrentHP(atkPk.currentHP().recovery(atkPk, new CurrentHPI(recovery))), dfcPk, field, weather);
        }
    },
    LEECH_LIFE("きゅうけつ", Type.BUG, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 80, 100, 10, 0, 0,
            true, false, true, false, false, false) {
        public OnBattleField effect(PokeInfo atkPk, PokeInfo dfcPk, Field field, int recoveryHP, Weather weather) throws InterruptedException {
            int recovery = recoveryHP / 2;
            return new OnBattleField(atkPk.withCurrentHP(atkPk.currentHP().recovery(atkPk, new CurrentHPI(recovery))), dfcPk, field, weather);
        }
    },
    X_SCISSOR("シザークロス", Type.BUG, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 80, 100, 15, 0, 0,
            true, false, true, false, false, false) {
        public OnBattleField effect(PokeInfo atkPk, PokeInfo dfcPk, Field field, int recoveryHP, Weather weather) {
            return new OnBattleField(atkPk, dfcPk, field, weather);
        }
    },
    IRON_TAIL("アイアンテール", Type.STEEL, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 100, 75, 15, 0, 0,
            true, false, true, false, false, false) {
        public OnBattleField effect(PokeInfo atkPk, PokeInfo dfcPk, Field field, int recoveryHP, Weather weather) throws InterruptedException {
            if (random30Per()) return new OnBattleField(atkPk, dfcPk.withChStatusRank(0, -1, 0, 0, 0, 0, 0), field, weather);
            return new OnBattleField(atkPk, dfcPk, field, weather);
        }
    },
    ROCK_SLIDE("いわなだれ", Type.ROCK, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 75, 90, 10, 0, 0,
            false, false, true, false, false, false) {
        public OnBattleField effect(PokeInfo atkPk, PokeInfo dfcPk, Field field, int recoveryHP, Weather weather) {
            if (random30Per()) return new OnBattleField(atkPk, dfcPk.withFlinch(new FlinchI(true)), field, weather);
            return new OnBattleField(atkPk, dfcPk, field, weather);
        }
    },
    CRUNCH("かみくだく", Type.DARK, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 80, 100, 15, 0, 0,
            true, false, true, false, false, false) {
        public OnBattleField effect(PokeInfo atkPk, PokeInfo dfcPk, Field field, int recoveryHP, Weather weather) throws InterruptedException {
            if (random20Per()) return new OnBattleField(atkPk, dfcPk.withChStatusRank(0, -1, 0, 0, 0, 0, 0), field, weather);
            return new OnBattleField(atkPk, dfcPk, field, weather);
        }
    },
    FOCUS_BLAST("きあいだま", Type.FIGHTING, MoveSpecies.SPECIAL, DetailMvSpecies.DAMAGE, 120, 70, 5, 0, 0,
            false, false, true, false, false, false) {
        public OnBattleField effect(PokeInfo atkPk, PokeInfo dfcPk, Field field, int recoveryHP, Weather weather) throws InterruptedException {
            if (random10Per()) return new OnBattleField(atkPk, dfcPk.withChStatusRank(0, 0, 0, -1, 0, 0, 0), field, weather);
            return new OnBattleField(atkPk, dfcPk, field, weather);
        }
    },
    REVERSAL("きしかいせい", Type.FIGHTING, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 20, 100, 15, 0, 0,
            true, false, true, false, false, false) {
        public OnBattleField effect(PokeInfo atkPk, PokeInfo dfcPk, Field field, int recoveryHP, Weather weather) {
            return new OnBattleField(atkPk, dfcPk, field, weather);
        }
    },
    PSYCHIC("サイコキネシス", Type.PSYCHIC, MoveSpecies.SPECIAL, DetailMvSpecies.DAMAGE, 90, 100, 10, 0, 0,
            false, false, true, false, false, false) {
        public OnBattleField effect(PokeInfo atkPk, PokeInfo dfcPk, Field field, int recoveryHP, Weather weather) throws InterruptedException {
            if (random10Per()) return new OnBattleField(atkPk, dfcPk.withChStatusRank(0, 0, 0, -1, 0, 0, 0), field, weather);
            return new OnBattleField(atkPk, dfcPk, field, weather);
        }
    },
    ZEN_HEADBUTT("しねんのずつき", Type.PSYCHIC, MoveSpecies.PHYSICAL, DetailMvSpecies.DAMAGE, 80, 90, 15, 0, 0,
            true, false, true, false, false, false) {
        public OnBattleField effect(PokeInfo atkPk, PokeInfo dfcPk, Field field, int recoveryHP, Weather weather) {
            if (random20Per()) return new OnBattleField(atkPk, dfcPk.withFlinch(new FlinchI(true)), field, weather);
            return new OnBattleField(atkPk, dfcPk, field, weather);
        }
    },
    /**
     * ここからランク変化技
     */
    GROWL("なきごえ", Type.NORMAL, MoveSpecies.CHANGE, DetailMvSpecies.DOWN_A, 0, 100, 20, 0, 0,
            false, true, true, false, true, true) {
        public OnBattleField effect(PokeInfo atkPk, PokeInfo dfcPk, Field field, int recoveryHP, Weather weather) throws InterruptedException {
            return new OnBattleField(atkPk, dfcPk.withChStatusRank(-1, 0, 0, 0, 0, 0, 0), field, weather);
        }
    },
    DOUBLE_TEAM("かげぶんしん", Type.NORMAL, MoveSpecies.CHANGE, DetailMvSpecies.UP_AV, 0, -1, 15, 0, 0,
            false, false, false, false, true, false) {
        public OnBattleField effect(PokeInfo atkPk, PokeInfo dfcPk, Field field, int recoveryHP, Weather weather) throws InterruptedException {
            return new OnBattleField(atkPk.withChStatusRank(0, 0, 0, 0, 0, 0, 1), dfcPk, field, weather);
        }
    },
    GROWTH("せいちょう", Type.NORMAL, MoveSpecies.CHANGE, DetailMvSpecies.UP_C, 0, -1, 20, 0, 0,
            false, false, true, false, true, false) {
        public OnBattleField effect(PokeInfo atkPk, PokeInfo dfcPk, Field field, int recoveryHP, Weather weather) throws InterruptedException {
            return new OnBattleField(atkPk.withChStatusRank(0, 0, 1, 0, 0, 0, 0), dfcPk, field, weather);
        }
    },
    SWORDS_DANCE("つるぎのまい", Type.NORMAL, MoveSpecies.CHANGE, DetailMvSpecies.UP_A, 0, -1, 20, 0, 0,
            false, false, true, false, true, false) {
        public OnBattleField effect(PokeInfo atkPk, PokeInfo dfcPk, Field field, int recoveryHP, Weather weather) throws InterruptedException {
            return new OnBattleField(atkPk.withChStatusRank(2, 0, 0, 0, 0, 0, 0), dfcPk, field, weather);
        }
    },
    CHARM("あまえる", Type.PSYCHIC, MoveSpecies.CHANGE, DetailMvSpecies.DOWN_A, 0, 100, 20, 0, 0,
            false, true, true, false, true, false) {
        public OnBattleField effect(PokeInfo atkPk, PokeInfo dfcPk, Field field, int recoveryHP, Weather weather) throws InterruptedException {
            return new OnBattleField(atkPk, dfcPk.withChStatusRank(-2, 0, 0, 0, 0, 0, 0), field, weather);
        }
    },
    /**
     * ここから状態異常技
     */
    SLEEP_POWDER("ねむりごな", Type.GRASS, MoveSpecies.CHANGE, DetailMvSpecies.AILMENT, 0, 75, 15, 0, 0,
            false, true, true, false, false, false) {
        public OnBattleField effect(PokeInfo atkPk, PokeInfo dfcPk, Field field, int recoveryHP, Weather weather) throws InterruptedException {
            return new OnBattleField(atkPk, dfcPk.withAilment(changeAilment(dfcPk, AilmentEnum.SLEEP)), field, weather);
        }
    },
    SPORE("キノコのほうし", Type.GRASS, MoveSpecies.CHANGE, DetailMvSpecies.AILMENT, 0, 100, 15, 0, 0,
            false, true, true, false, false, false) {
        public OnBattleField effect(PokeInfo atkPk, PokeInfo dfcPk, Field field, int recoveryHP, Weather weather) throws InterruptedException {
            return new OnBattleField(atkPk, dfcPk.withAilment(changeAilment(dfcPk, AilmentEnum.SLEEP)), field, weather);
        }
    },
    WILL_O_WISP("おにび", Type.FIRE, MoveSpecies.CHANGE, DetailMvSpecies.AILMENT, 0, 85, 15, 0, 0,
            false, true, true, false, false, false) {
        public OnBattleField effect(PokeInfo atkPk, PokeInfo dfcPk, Field field, int recoveryHP, Weather weather) throws InterruptedException {
            return new OnBattleField(atkPk, dfcPk.withAilment(changeAilment(dfcPk, AilmentEnum.BURN)), field, weather);
        }
    },
    CONFUSE_RAY("あやしいひかり", Type.GHOST, MoveSpecies.CHANGE, DetailMvSpecies.CONFUSE, 0, 100, 10, 0, 0,
            false, true, true, false, false, false) {
        public OnBattleField effect(PokeInfo atkPk, PokeInfo dfcPk, Field field, int recoveryHP, Weather weather) throws InterruptedException {
            return new OnBattleField(atkPk, dfcPk.withConfusion(beConfusion(dfcPk)), field, weather);
        }
    },
    HYPNOSIS("さいみんじゅつ", Type.PSYCHIC, MoveSpecies.CHANGE, DetailMvSpecies.AILMENT, 0, 60, 20, 0, 0,
            false, true, true, false, false, false) {
        public OnBattleField effect(PokeInfo atkPk, PokeInfo dfcPk, Field field, int recoveryHP, Weather weather) throws InterruptedException {
            return new OnBattleField(atkPk, dfcPk.withAilment(changeAilment(dfcPk, AilmentEnum.SLEEP)), field, weather);
        }
    },
    /**
     * ここからその他変化技
     */
    RECOVER("じこさいせい", Type.NORMAL, MoveSpecies.CHANGE, DetailMvSpecies.RECOVERY, 0, -1, 5, 0, 0,
            false, false, false, false, false, false) {
        public OnBattleField effect(PokeInfo atkPk, PokeInfo dfcPk, Field field, int recoveryHP, Weather weather) throws InterruptedException {
            int recovery = atkPk.realHP() / 2;
            return new OnBattleField(atkPk.withCurrentHP(atkPk.currentHP().recovery(atkPk, new CurrentHPI(recovery))), dfcPk, field, weather);
        }
    },
//    LIGHT_SCREEN("ひかりのかべ", Type.PSYCHIC, MoveSpecies.CHANGE, DetailMvSpecies.WALL, 0, -1, 30, 0, 0,
//            false, false, false, false, true, false) {
//        public OnBattleField effect(PokeInfo atkPk, PokeInfo dfcPk, Field field, int recoveryHP, Weather weather) throws InterruptedException {
//            // TODO てきがつかっても自分のひかりのかべができてしまう
//            return new OnBattleField(atkPk, dfcPk, field.withLightScreen(LightScreen.enableLightScreen(field)));
//        }
//    },
//    REFLECT("リフレクター", Type.PSYCHIC, MoveSpecies.CHANGE, DetailMvSpecies.WALL, 0, -1, 20, 0, 0,
//            false, false, false, false, true, false) {
//        public OnBattleField effect(PokeInfo atkPk, PokeInfo dfcPk, Field field, int recoveryHP, Weather weather) throws InterruptedException {
//            // TODO てきがつかっても自分のリフレクターができてしまう
//            return new OnBattleField(atkPk, dfcPk, field.withReflect(Reflect.enableReflect(field)));
//        }
//    },
    /**
     * ここから天候技
     */
    SUNNY_DAY("にほんばれ", Type.FIRE, MoveSpecies.CHANGE, DetailMvSpecies.WEATHER, 0, -1, 5, 0, 0,
            false, false, false, false, false, false) {
        public OnBattleField effect(PokeInfo atkPk, PokeInfo dfcPk, Field field, int recoveryHP, Weather weather) throws InterruptedException {
            return new OnBattleField(atkPk, dfcPk, field, changeWeather(weather, WeatherEnum.DROUGHT));
        }
    },
    RAIN_DANCE("あまごい", Type.WATER, MoveSpecies.CHANGE, DetailMvSpecies.WEATHER, 0, -1, 5, 0, 0,
            false, false, false, false, false, false) {
        public OnBattleField effect(PokeInfo atkPk, PokeInfo dfcPk, Field field, int recoveryHP, Weather weather) throws InterruptedException {
            return new OnBattleField(atkPk, dfcPk, field, changeWeather(weather, WeatherEnum.RAIN));
        }
    },
    SAND_STORM("すなあらし", Type.ROCK, MoveSpecies.CHANGE, DetailMvSpecies.WEATHER, 0, -1, 10, 0, 0,
            false, false, false, false, false, false) {
        public OnBattleField effect(PokeInfo atkPk, PokeInfo dfcPk, Field field, int recoveryHP, Weather weather) throws InterruptedException {
            return new OnBattleField(atkPk, dfcPk, field, changeWeather(weather, WeatherEnum.SANDSTORM));
        }
    },
    HAIL("あられ", Type.ICE, MoveSpecies.CHANGE, DetailMvSpecies.WEATHER, 0, -1, 10, 0, 0,
            false, false, false, false, false, false) {
        public OnBattleField effect(PokeInfo atkPk, PokeInfo dfcPk, Field field, int recoveryHP, Weather weather) throws InterruptedException {
            return new OnBattleField(atkPk, dfcPk, field, changeWeather(weather, WeatherEnum.HAIL));
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

    public int damage(PokeInfo atkPk, PokeInfo dfcPk, Field field) {
        int result;
        switch (this) {
            case ERUPTION, WATER_SPOUT -> result = 150 * atkPk.currentHP().val() / atkPk.realHP();
            case REVERSAL, FLAIL -> result = calcReversalDmg(atkPk);
            case BRINE -> result = (double) (dfcPk.currentHP().val()) / (double) (dfcPk.realHP()) <= 0.5 ? this.damage * 2 : this.damage;
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
    public abstract OnBattleField effect(PokeInfo atkPk, PokeInfo dfcPk, Field field, int recoveryHP, Weather weather) throws InterruptedException;

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
    private static int calcReversalDmg(PokeInfo atkPk) {
        double RemainingHP = (double) (atkPk.currentHP().val()) / (double) (atkPk.realHP());
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
