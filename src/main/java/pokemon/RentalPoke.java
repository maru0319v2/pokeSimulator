package pokemon;

import Enum.Gender;
import Enum.Item;
import Enum.Nature;
import lombok.AllArgsConstructor;
import move.BaseMvPrm;
import pokemonStatus.impl.EffortValueI;
import pokemonStatus.impl.IndividualValueI;
import pokemonStatus.impl.LevelI;

import java.util.*;

import static Enum.Gender.*;
import static Enum.Item.*;
import static Enum.Nature.*;
import static move.BaseMvPrm.*;
import static move.MoveI.init;
import static pokemon.BasePrm.*;

@SuppressWarnings("ALL")
@AllArgsConstructor
public enum RentalPoke {
    // レンタルポケモンのパラメータを表現する
    フシギバナ_1(
            VENUSAUR, MALE, MODEST, OBON_FRUIT,
            GIGA_DRAIN, SYNTHESIS, SLUDGE_BOMB, SLEEP_POWDER,
            252, 0, 0, 252, 0, 0
    ),
    リザードン_1(
            CHARIZARD, MALE, MODEST, OBON_FRUIT,
            FLARE_BLITZ, AERIAL_ACE, OVERHEAT, BELLY_DRUM,
            6, 252, 0, 0, 0, 252
    ),
    カメックス_1(
            BLASTOISE, MALE, MODEST, KAMURA_FRUIT,
            SURF, RAIN_DANCE, ICE_BEAM, AQUA_JET,
            0, 0, 0, 252, 0, 252
    ),
    バタフリー_1(
            BUTTERFREE, FEMALE, TIMID, LAPEL_OF_SPIRIT,
            BUG_BUZZ, QUIVER_DANCE, SLEEP_POWDER, PSYCHIC,
            0, 0, 0, 252, 0, 252
    ),
    ライチュウ_1(
            RAICHU, MALE, NAIVE, LAPEL_OF_SPIRIT,
            THUNDERBOLT, NASTY_PLOT, SURF, THUNDER_WAVE,
            6, 0, 0, 252, 0, 252
    ),
    ペルシアン_1(
            PERSIAN, FEMALE, JOLLY, PINTO_LENS,
            SLASH, HYPNOSIS, DOUBLE_TEAM, IRON_TAIL,
            6, 252, 0, 0, 0, 252
    ),
    ラフレシア_1(
            VILEPLUME, FEMALE, MODEST, LIGHT_POWDER,
            SLUDGE_BOMB, SLEEP_POWDER, GIGA_DRAIN, DOUBLE_TEAM,
            252, 0, 0, 252, 0, 6
    ),
    ウインディ_1(
            ARCANINE, MALE, NAIVE, RAMU_FRUIT,
            HONE_CLAWS, FLARE_BLITZ, EXTREME_SPEED, WILD_CHARGE,
            0, 252, 0, 6, 0, 252
    ),
    フーディン_1(
            AKAKAZAM, MALE, TIMID, LAPEL_OF_SPIRIT,
            PSYCHIC, SHADOW_BALL, RECOVER, CALM_MIND,
            6, 0, 0, 252, 0, 252
    ),
    カイリキー_1(
            MACHAMP, MALE, ADAMANT, MARK_OF_KINGS,
            CRESS_CHOP, STONE_EDGE, BULK_UP, BULLET_PUNCH,
            252, 252, 0, 0, 0, 6
    ),
    ヤドラン_1(
            SLOWBRO, FEMALE, RELAXED, RAMU_FRUIT,
            SCALD, PSYCHIC, SLACK_OFF, CALM_MIND,
            252, 0, 0, 200, 156, 0
    ),
    レアコイル_1(
            MAGNRTON, UNKNOWN, MODEST, LAPEL_OF_SPIRIT,
            THUNDERBOLT, FLASH_CANNON, THUNDER_WAVE, STEEL_BEAM,
            152, 0, 0, 252, 0, 100
    ),
    パルシェン_1(
            CLOYSTER, MALE, MODEST, PEAL_OF_LIFE,
            SURF, ICE_BEAM, SHELL_SMASH, EXPLOSION,
            0, 0, 0, 252, 0, 252
    ),
    ゲンガー_1(
            GENGAR, FEMALE, TIMID, LAPEL_OF_SPIRIT,
            SHADOW_BALL, CONFUSE_RAY, THUNDERBOLT, HYPNOSIS,
            0, 0, 0, 252, 0, 252
    ),
    ナッシー_1(
            EXEGGUTOR, MALE, MODEST, OBON_FRUIT,
            GIGA_DRAIN, PSYCHIC, HYPNOSIS, SYNTHESIS,
            252, 0, 0, 252, 0, 6
    ),
    マタドガス_1(
            WEEZING, FEMALE, RELAXED, RAMU_FRUIT,
            SLUDGE_BOMB, WILL_O_WISP, EXPLOSION, FLAMETHROWER,
            252, 100, 0, 152, 0, 6
    ),
    サイドン_1(
            RHYDON, MALE, RELAXED, OBON_FRUIT,
            EARTHQUAKE, STONE_EDGE, HAMMER_ARM, MEGA_HORN,
            252, 100, 156, 0, 0, 0
    ),
    ラッキー_1(
            CHANSEY, FEMALE, BOLD, LIGHT_POWDER,
            FLAMETHROWER, ICE_BEAM, THUNDERBOLT, SOFT_BOILED,
            252, 0, 6, 252, 0, 0
    ),
    モンジャラ_1(
            TANGELA, MALE, CALM, LOSTOVER_FOOD,
            GIGA_DRAIN, GROWTH, SYNTHESIS, SLEEP_POWDER,
            252, 0, 120, 0, 132, 0
    ),
    ガルーラ_1(
            KANGASKHAN, FEMALE, ADAMANT, LOSTOVER_FOOD,
            DOUBLE_EDGE, POWER_UP_PUNCH, EARTHQUAKE, HAMMER_ARM,
            252, 252, 6, 0, 0, 0
    ),
    シードラ_1(
            SRADRA, MALE, TIMID, KAMURA_FRUIT,
            ICE_BEAM, SURF, WATER_PULSE, HYDRO_PUMP,
            0, 0, 0, 252, 0, 252
    ),
    アズマオウ_1(
            SEAKING, MALE, TIMID, KAMURA_FRUIT,
            ICE_BEAM, SURF, WATER_PULSE, HYDRO_PUMP,
            0, 0, 0, 252, 0, 252
    ),
    スターミー_1(
            STARMIE, UNKNOWN, TIMID, KAMURA_FRUIT,
            ICE_BEAM, PSYCHIC, THUNDERBOLT, HYDRO_PUMP,
            0, 0, 0, 252, 0, 252
    ),
    バリヤード_1(
            MRMIME, MALE, CALM, OBON_FRUIT,
            PSYCHIC, THUNDERBOLT, LIGHT_SCREEN, REFLECT,
            6, 0, 0, 252, 0, 252
    ),
    ストライク_1(
            SCYTHER, MALE, JOLLY, LAPEL_OF_SPIRIT,
            AERIAL_ACE, X_SCISSOR, DOUBLE_TEAM, SWORDS_DANCE,
            6, 252, 0, 0, 0, 252
    ),
    ルージュラ_1(
            JYNX, FEMALE, HASTY, RAMU_FRUIT,
            ICE_BEAM, LOVELY_KISS, PSYCHIC, FOCUS_BLAST,
            6, 0, 0, 252, 0, 252
    ),
    エレブー_1(
            ELECTABUZZ, FEMALE, HASTY, LIGHT_POWDER,
            THUNDERBOLT, MACH_PUNCH, PSYCHIC, HIDDEN_POWER_ICE,
            6, 0, 0, 252, 0, 252
    ),
    ブーバー_1(
            MAGMAR, FEMALE, HASTY, LIGHT_POWDER,
            FIRE_BLAST, MACH_PUNCH, PSYCHIC, DOUBLE_EDGE,
            0, 100, 0, 156, 0, 252
    ),
    カイロス_1(
            PINSIR, MALE, JOLLY, PEAL_OF_LIFE,
            X_SCISSOR, SWORDS_DANCE, ROCK_SLIDE, CLOSE_COMBAT,
            6, 252, 0, 0, 0, 252
    ),
    ケンタロス_1(
            TAUROS, MALE, JOLLY, PEAL_OF_LIFE,
            DOUBLE_EDGE, BULK_UP, EARTHQUAKE, WILD_CHARGE,
            0, 0, 0, 252, 0, 252
    ),
    ギャラドス_1(
            GYARADOS, FEMALE, ADAMANT, LOSTOVER_FOOD,
            WATERFALL, EARTHQUAKE, DRAGON_DANCE, STONE_EDGE,
            252, 152, 0, 0, 0, 100
    ),
    ラプラス_1(
            LAPRAS, FEMALE, CALM, LOSTOVER_FOOD,
            SCALD, THUNDERBOLT, ICE_BEAM, CONFUSE_RAY,
            252, 0, 0, 100, 156, 0
    ),
    シャワーズ_1(
            VAPAREON, MALE, CALM, RAMU_FRUIT,
            SCALD, TOXIC, REST, ICE_BEAM,
            252, 0, 0, 252, 6, 0
    ),
    サンダース_1(
            JOLTEON, MALE, TIMID, LAPEL_OF_SPIRIT,
            THUNDERBOLT, SHADOW_BALL, SWAGGER, HIDDEN_POWER_ICE,
            6, 0, 0, 252, 0, 252
    ),
    ブースター_1(
            FLAREON, MALE, JOLLY, PEAL_OF_LIFE,
            FLARE_BLITZ, FLAME_CHARGE, QUICK_ATTACK, SUPERPOWER,
            6, 252, 0, 0, 0, 252
    ),
    オムスター_1(
            OMASTAR, MALE, JOLLY, LAPEL_OF_SPIRIT,
            HYDRO_PUMP, ICE_BEAM, AQUA_JET, SHELL_SMASH,
            0, 0, 0, 252, 6, 252
    ),
    カブトプス_1(
            KABUTOPS, MALE, JOLLY, KAMURA_FRUIT,
            STONE_EDGE, LIQUIDATION, AQUA_JET, SWORDS_DANCE,
            0, 252, 0, 0, 6, 252
    ),
    プテラ_1(
            AERODACTYL, MALE, JOLLY, MARK_OF_KINGS,
            STONE_EDGE, EARTHQUAKE, AERIAL_ACE, SWAGGER,
            0, 252, 0, 0, 6, 252
    ),
    カビゴン_1(
            SNORLAX, MALE, RELAXED, RAMU_FRUIT,
            BODY_SLAM, EARTHQUAKE, REST, SELF_DESTRUCT,
            252, 252, 0, 0, 6, 0
    ),
    フリーザー_1(
            ARTICUNO, UNKNOWN, TIMID, LIGHT_POWDER,
            BLIZZARD, HURRICANE, ICE_SHARD, HAIL,
            252, 0, 0, 252, 0, 6
    ),
    サンダー_1(
            ZAPDOS, UNKNOWN, TIMID, KAMURA_FRUIT,
            THUNDER, HURRICANE, RAIN_DANCE, THUNDER_WAVE,
            0, 0, 0, 252, 0, 252
    ),
    ファイヤー_1(
            MOLTRES, UNKNOWN, TIMID, RAMU_FRUIT,
            FIRE_BLAST, AIR_SLASH, WILL_O_WISP, HIDDEN_POWER_GRASS,
            0, 0, 0, 252, 0, 252
    ),
    カイリュー_1(
            DRAGONITE, MALE, ADAMANT, LOSTOVER_FOOD,
            DRAGON_CLAW, DRAGON_DANCE, EARTHQUAKE, EXTREME_SPEED,
            0, 0, 0, 252, 0, 252
    );

    private final BasePrm base;
    private final Gender gender;
    private final Nature nature;
    private final Item item;
    private final BaseMvPrm m1;
    private final BaseMvPrm m2;
    private final BaseMvPrm m3;
    private final BaseMvPrm m4;
    private final int H;
    private final int A;
    private final int B;
    private final int C;
    private final int D;
    private final int S;

    // 指定のレンタルポケモンを選択する
    public static PokeInfoI rent(RentalPoke rentalPoke) {
        return new PokeInfoI(
                rentalPoke.base,
                rentalPoke.gender,
                rentalPoke.nature,
                new IndividualValueI(31, 31, 31, 31, 31, 31),
                new EffortValueI(rentalPoke.H, rentalPoke.A, rentalPoke.B, rentalPoke.C, rentalPoke.D, rentalPoke.S),
                new LevelI(100),
                List.of(init(rentalPoke.m1), init(rentalPoke.m2), init(rentalPoke.m3), init(rentalPoke.m4)),
                rentalPoke.item
        );
    }

    // ランダムにレンタルポケモンの中から1体選択する
    public static PokeInfoI randomRental() {
        int pick = new Random().nextInt(RentalPoke.values().length);
        return rent(RentalPoke.values()[pick]);
    }

    // ランダムにレンタルポケモンの中から6体選択する(重複なし)
    public static List<PokeInfo> randomRental(int num) {
        List<RentalPoke> valuesList = new ArrayList<>(EnumSet.allOf(RentalPoke.class));
        Collections.shuffle(valuesList, new Random());
        List<RentalPoke> rentalPokeList = valuesList.subList(0, num);
        List<PokeInfo> resultList = new ArrayList<>(num);
        for (RentalPoke p : rentalPokeList) {
            resultList.add(rent(p));
        }
        return resultList;
    }

    // CPUのポケモンをランダムで決定する。(自分が選んだポケモンは選ばれない)
    public static List<PokeInfo> randomCPURental(List<PokeInfo> mySelectedPoke) {
        List<BasePrm> mySelectedBase = mySelectedPoke.stream().map(e -> e.basePrm()).toList();
        List<RentalPoke> valuesList = Arrays.stream(RentalPoke.values()).filter(e -> !mySelectedBase.contains(e.base)).toList();
        valuesList = new ArrayList<RentalPoke>(valuesList);
        Collections.shuffle(valuesList);
        List<RentalPoke> rentalPokeList = valuesList.subList(0, 3);
        List<PokeInfo> resultList = new ArrayList<>(3);
        for (RentalPoke p : rentalPokeList) {
            resultList.add(rent(p));
        }
        return resultList;
    }
}
