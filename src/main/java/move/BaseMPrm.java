package move;

import Enum.MoveSpecies;
import Enum.Type;
import bussinessLogic.OnBattleField;
import field.Field;
import field.Weather;
import lombok.AllArgsConstructor;
import lombok.Getter;
import pokemon.PokemonInfo;
import pokemonStatus.impl.CurrentHitPointImpl;
import statusAilment.Ailment;

import java.util.Random;

import static bussinessLogic.ConsoleOutManager.showMessageParChar;
import static field.FieldImpl.changeField;
import static statusAilment.StatusAilmentImpl.changeAilment;

@Getter
@AllArgsConstructor
public enum BaseMPrm {
    TACKLE("たいあたり", Type.NORMAL, MoveSpecies.PHYSICAL, DetailedMoveSpecies.DAMAGE, 40, 95, 35, 0, 0,
            true, false, true, false, false, false
    ) {
        @Override
        public OnBattleField effect(PokemonInfo attackPoke, PokemonInfo defensePoke, Field field, int recoveryHP) {
            return new OnBattleField(attackPoke, defensePoke, field);
        }
    },
    AERIAL_ACE("つばめがえし", Type.FLYING, MoveSpecies.PHYSICAL, DetailedMoveSpecies.HIT, 60, -1, 20, 0, 0,
            true, false, true, false, false, false
    ) {
        @Override
        public OnBattleField effect(PokemonInfo attackPoke, PokemonInfo defensePoke, Field field, int recoveryHP) {
            return new OnBattleField(attackPoke, defensePoke, field);
        }
    },
    QUICK_ATTACK("でんこうせっか", Type.NORMAL, MoveSpecies.PHYSICAL, DetailedMoveSpecies.PRIORITY, 40, 100, 30, 0, 1,
            true, false, true, false, false, false
    ) {
        @Override
        public OnBattleField effect(PokemonInfo attackPoke, PokemonInfo defensePoke, Field field, int recoveryHP) {
            return new OnBattleField(attackPoke, defensePoke, field);
        }
    },
    FLAMETHROWER("かえんほうしゃ", Type.FIRE, MoveSpecies.SPECIAL, DetailedMoveSpecies.DAMAGE, 90, 100, 15, 0, 0,
            false, false, true, false, false, false
    ) {
        @Override
        public OnBattleField effect(PokemonInfo attackPoke, PokemonInfo defensePoke, Field field, int recoveryHP) throws InterruptedException {
            if ((new Random().nextInt(10)) == 0) {
                return new OnBattleField(attackPoke, defensePoke.withStatusAilment(changeAilment(defensePoke, Ailment.BURN)), field);
            }
            return new OnBattleField(attackPoke, defensePoke, field);
        }
    },
    THUNDERBOLT("10まんボルト", Type.ELECTRIC, MoveSpecies.SPECIAL, DetailedMoveSpecies.DAMAGE, 90, 100, 15, 0, 0,
            false, false, true, false, false, false
    ) {
        @Override
        public OnBattleField effect(PokemonInfo attackPoke, PokemonInfo defensePoke, Field field, int recoveryHP) throws InterruptedException {
            if ((new Random().nextInt(10)) == 0) {
                return new OnBattleField(attackPoke, defensePoke.withStatusAilment(changeAilment(defensePoke, Ailment.PARALYSIS)), field);
            }
            return new OnBattleField(attackPoke, defensePoke, field);
        }
    },
    DRAGON_CLAW("ドラゴンクロー", Type.DRAGON, MoveSpecies.PHYSICAL, DetailedMoveSpecies.DAMAGE, 80, 100, 15, 0, 0,
            true, false, true, false, false, false
    ) {
        @Override
        public OnBattleField effect(PokemonInfo attackPoke, PokemonInfo defensePoke, Field field, int recoveryHP) {
            return new OnBattleField(attackPoke, defensePoke, field);
        }
    },
    WATER_PULSE("みずのはどう", Type.WATER, MoveSpecies.SPECIAL, DetailedMoveSpecies.DAMAGE, 60, 100, 20, 0, 0,
            false, false, true, false, false, false
    ) {
        @Override
        public OnBattleField effect(PokemonInfo attackPoke, PokemonInfo defensePoke, Field field, int recoveryHP) {
            // TODO 20%の確率で相手を1~4ターン混乱する
            return new OnBattleField(attackPoke, defensePoke, field);
        }
    },
    VINE_WHIP("つるのムチ", Type.GRASS, MoveSpecies.PHYSICAL, DetailedMoveSpecies.DAMAGE, 45, 100, 25, 0, 0,
            true, false, true, false, false, false
    ) {
        @Override
        public OnBattleField effect(PokemonInfo attackPoke, PokemonInfo defensePoke, Field field, int recoveryHP) {
            return new OnBattleField(attackPoke, defensePoke, field);
        }
    },
    IRON_TAIL("アイアンテール", Type.STEEL, MoveSpecies.PHYSICAL, DetailedMoveSpecies.DAMAGE, 100, 75, 15, 0, 0,
            true, false, true, false, false, false
    ) {
        @Override
        public OnBattleField effect(PokemonInfo attackPoke, PokemonInfo defensePoke, Field field, int recoveryHP) throws InterruptedException {
            if ((new Random().nextInt(10)) <= 2) {
                return new OnBattleField(attackPoke, defensePoke.withAddedStatusRank(0, -1, 0, 0, 0, 0, 0), field);
            }
            return new OnBattleField(attackPoke, defensePoke, field);
        }
    },
    GIGA_DRAIN("ギガドレイン", Type.GRASS, MoveSpecies.SPECIAL, DetailedMoveSpecies.DAMAGE, 75, 100, 10, 0, 0,
            false, false, true, false, false, false
    ) {
        @Override
        public OnBattleField effect(PokemonInfo attackPoke, PokemonInfo defensePoke, Field field, int recoveryHP) throws InterruptedException {
            int recovery = recoveryHP / 2;
            return new OnBattleField(attackPoke.withCurrentHitPoint(attackPoke.getCurrentHitPoint().recovery(attackPoke, new CurrentHitPointImpl(recovery))), defensePoke, field);
        }
    },
    GROWL("なきごえ", Type.NORMAL, MoveSpecies.CHANGE, DetailedMoveSpecies.DOWN_A, 0, 100, 20, 0, 0,
            false, true, true, false, true, true
    ) {
        @Override
        public OnBattleField effect(PokemonInfo attackPoke, PokemonInfo defensePoke, Field field, int recoveryHP) throws InterruptedException {
            showMessageParChar(defensePoke.getBasePrm().getName() + "の攻撃が下がった!");
            return new OnBattleField(attackPoke, defensePoke.withAddedStatusRank(-1, 0, 0, 0, 0, 0, 0), field);
        }
    },
    DOUBLE_TEAM("かげぶんしん", Type.NORMAL, MoveSpecies.CHANGE, DetailedMoveSpecies.UP_AV, 0, -1, 15, 0, 0,
            false, false, false, false, true, false
    ) {
        @Override
        public OnBattleField effect(PokemonInfo attackPoke, PokemonInfo defensePoke, Field field, int recoveryHP) throws InterruptedException {
            showMessageParChar(attackPoke.getBasePrm().getName() + "の回避率が上がった!");
            return new OnBattleField(attackPoke.withAddedStatusRank(0, 0, 0, 0, 0, 0, 1), defensePoke, field);
        }
    },
    GROWTH("せいちょう", Type.NORMAL, MoveSpecies.CHANGE, DetailedMoveSpecies.UP_C, 0, -1, 20, 0, 0,
            false, false, true, false, true, false) {
        @Override
        public OnBattleField effect(PokemonInfo attackPoke, PokemonInfo defensePoke, Field field, int recoveryHP) throws InterruptedException {
            showMessageParChar(attackPoke.getBasePrm().getName() + "の特攻が上がった!");
            return new OnBattleField(attackPoke.withAddedStatusRank(0, 0, 1, 0, 0, 0, 0), defensePoke, field);
        }
    },
    SWORDS_DANCE("つるぎのまい", Type.NORMAL, MoveSpecies.CHANGE, DetailedMoveSpecies.UP_A, 0, -1, 20, 0, 0,
            false, false, true, false, true, false) {
        @Override
        public OnBattleField effect(PokemonInfo attackPoke, PokemonInfo defensePoke, Field field, int recoveryHP) throws InterruptedException {
            showMessageParChar(attackPoke.getBasePrm().getName() + "の攻撃がぐーんと上がった!");
            return new OnBattleField(attackPoke.withAddedStatusRank(2, 0, 0, 0, 0, 0, 0), defensePoke, field);
        }
    },
    SLEEP_POWDER("ねむりごな", Type.GRASS, MoveSpecies.CHANGE, DetailedMoveSpecies.AILMENT, 0, 75, 15, 0, 0,
            false, true, true, false, false, false) {
        @Override
        public OnBattleField effect(PokemonInfo attackPoke, PokemonInfo defensePoke, Field field, int recoveryHP) throws InterruptedException {
            return new OnBattleField(attackPoke, defensePoke.withStatusAilment(changeAilment(defensePoke, Ailment.SLEEP)), field);
        }
    },
    WILL_O_WISP("おにび", Type.FIRE, MoveSpecies.CHANGE, DetailedMoveSpecies.AILMENT, 0, 85, 15, 0, 0,
            false, true, true, false, false, false) {
        @Override
        public OnBattleField effect(PokemonInfo attackPoke, PokemonInfo defensePoke, Field field, int recoveryHP) throws InterruptedException {
            return new OnBattleField(attackPoke, defensePoke.withStatusAilment(changeAilment(defensePoke, Ailment.BURN)), field);
        }
    },
    SUNNY_DAY("にほんばれ", Type.FIRE, MoveSpecies.CHANGE, DetailedMoveSpecies.WEATHER, 0, -1, 5, 0, 0,
            false, false, false, false, false, false) {
        @Override
        public OnBattleField effect(PokemonInfo attackPoke, PokemonInfo defensePoke, Field field, int recoveryHP) throws InterruptedException {
            return new OnBattleField(attackPoke, defensePoke, changeField(field, Weather.DROUGHT));
        }
    },
    RAIN_DANCE("あまごい", Type.WATER, MoveSpecies.CHANGE, DetailedMoveSpecies.WEATHER, 0, -1, 5, 0, 0,
            false, false, false, false, false, false) {
        @Override
        public OnBattleField effect(PokemonInfo attackPoke, PokemonInfo defensePoke, Field field, int recoveryHP) throws InterruptedException {
            return new OnBattleField(attackPoke, defensePoke, changeField(field, Weather.RAIN));
        }
    },
    SAND_STORM("すなあらし", Type.ROCK, MoveSpecies.CHANGE, DetailedMoveSpecies.WEATHER, 0, -1, 10, 0, 0,
            false, false, false, false, false, false) {
        @Override
        public OnBattleField effect(PokemonInfo attackPoke, PokemonInfo defensePoke, Field field, int recoveryHP) throws InterruptedException {
            return new OnBattleField(attackPoke, defensePoke, changeField(field, Weather.SANDSTORM));
        }
    },
    HAIL("あられ", Type.ICE, MoveSpecies.CHANGE, DetailedMoveSpecies.WEATHER, 0, -1, 10, 0, 0,
            false, false, false, false, false, false) {
        @Override
        public OnBattleField effect(PokemonInfo attackPoke, PokemonInfo defensePoke, Field field, int recoveryHP) throws InterruptedException {
            return new OnBattleField(attackPoke, defensePoke, changeField(field, Weather.HAIL));
        }
    };

    // わざめい
    private final String name;
    // わざタイプ
    private final Type moveType;
    // 分類
    private final MoveSpecies moveSpecies;
    // 詳細な分類 CPUの技選択時に使用
    private final DetailedMoveSpecies detailedSpecies;
    // 威力
    private final int damage;
    // 命中率
    private final int hitRate;
    // PP
    private final int powerPoint;
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

    // 効果
    public abstract OnBattleField effect(PokemonInfo attackPoke, PokemonInfo defensePoke, Field field, int recoveryHP) throws InterruptedException;
}
