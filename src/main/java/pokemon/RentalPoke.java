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
    サンドパン_1(
            SANDSLASH, FEMALE, JOLLY, PINTO_LENS,
            EARTHQUAKE, STONE_EDGE, HONE_CLAWS, DOUBLE_TEAM,
            0, 0, 0, 252, 0, 252
    ),
    ニドクイン_1(
            NIDOQUEEN, FEMALE, TIMID, MARK_OF_KINGS,
            SLUDGE_BOMB, EARTH_POWER, ICE_BEAM, THUNDERBOLT,
            0, 0, 0, 252, 0, 252
    ),
    ニドキング_1(
            NIDOKING, MALE, TIMID, MARK_OF_KINGS,
            SLUDGE_BOMB, EARTH_POWER, ICE_BEAM, THUNDERBOLT,
            0, 0, 0, 252, 0, 252
    ),
    ピクシー_1(
            CLEFABLE, FEMALE, MODEST, LIGHT_POWDER,
            DOUBLE_EDGE, THUNDER_WAVE, PLAY_ROUGH, DOUBLE_TEAM,
            252, 0, 0, 0, 0, 252
    ),
    キュウコン_1(
            NINETALES, MALE, MODEST, RAMU_FRUIT,
            FLAMETHROWER, WILL_O_WISP, HIDDEN_POWER_ICE, DOUBLE_TEAM,
            0, 0, 0, 252, 0, 252
    ),
    プクリン_1(
            WIGGLYTUFF, FEMALE, MODEST, LIGHT_POWDER,
            DOUBLE_EDGE, THUNDER_WAVE, PLAY_ROUGH, DOUBLE_TEAM,
            252, 0, 0, 0, 0, 252
    ),
    ゴルバット_1(
            GOLBAT, FEMALE, MODEST, LIGHT_POWDER,
            SLUDGE_BOMB, CONFUSE_RAY, AIR_SLASH, DOUBLE_TEAM,
            252, 0, 0, 0, 0, 252
    ),
    ラフレシア_1(
            VILEPLUME, FEMALE, MODEST, LIGHT_POWDER,
            SLUDGE_BOMB, SLEEP_POWDER, GIGA_DRAIN, DOUBLE_TEAM,
            252, 0, 0, 252, 0, 6
    ),
    パラセクト_1(
            PARASECT, MALE, JOLLY, LAPEL_OF_SPIRIT,
            LEECH_LIFE, SPORE, SYNTHESIS, SWORDS_DANCE,
            6, 0, 0, 152, 100, 252
    ),
    モルフォン_1(
            VENOMATH, MALE, JOLLY, MARK_OF_KINGS,
            BUG_BUZZ, PSYCHIC, SLUDGE_BOMB, QUIVER_DANCE,
            6, 0, 0, 152, 100, 252
    ),
    ダグトリオ_1(
            DUGTRIO, MALE, JOLLY, MARK_OF_KINGS,
            SLASH, EARTHQUAKE, STONE_EDGE, HONE_CLAWS,
            6, 252, 0, 0, 0, 252
    ),
    ペルシアン_1(
            PERSIAN, FEMALE, JOLLY, PINTO_LENS,
            SLASH, HYPNOSIS, DOUBLE_TEAM, IRON_TAIL,
            6, 252, 0, 0, 0, 252
    ),
    ゴルダック_1(
            GOLDUCK, MALE, TIMID, RAMU_FRUIT,
            HYDRO_PUMP, ICE_BEAM, PSYCHIC, CALM_MIND,
            0, 0, 0, 252, 6, 252
    ),
    オコリザル_1(
            PRIMEAPE, MALE, ADAMANT, KAMURA_FRUIT,
            CLOSE_COMBAT, DRAIN_PUNCH, BULK_UP, SWAGGER,
            158, 252, 0, 0, 0, 100
    ),
    ウインディ_1(
            ARCANINE, MALE, NAIVE, RAMU_FRUIT,
            HONE_CLAWS, FLARE_BLITZ, EXTREME_SPEED, WILD_CHARGE,
            0, 252, 0, 6, 0, 252
    ),
    ニョロボン_1(
            POLIWRATH, MALE, ADAMANT, KAMURA_FRUIT,
            WATERFALL, DRAIN_PUNCH, HYPNOSIS, POWER_UP_PUNCH,
            6, 252, 0, 0, 152, 100
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
    ウツボット_1(
            VICTREEBEL, MALE, TIMID, RAMU_FRUIT,
            SLUDGE_BOMB, GIGA_DRAIN, TOXIC, SLEEP_POWDER,
            0, 0, 0, 252, 6, 252
    ),
    ドククラゲ_1(
            TENTACRUEL, MALE, CALM, RAMU_FRUIT,
            SLUDGE_BOMB, HYDRO_PUMP, TOXIC, CONFUSE_RAY,
            252, 0, 0, 0, 252, 0
    ),
    ゴローニャ_1(
            GOLEM, MALE, ADAMANT, LIGHT_POWDER,
            EARTHQUAKE, STONE_EDGE, EXPLOSION, HAMMER_ARM,
            52, 200, 252, 0, 0, 0
    ),
    ギャロップ_1(
            RAPIDASH, MALE, NAIVE, MARK_OF_KINGS,
            FLARE_BLITZ, DOUBLE_EDGE, WILD_CHARGE, DOUBLE_TEAM,
            6, 252, 0, 0, 0, 252
    ),
    ヤドラン_1(
            SLOWBRO, FEMALE, RELAXED, RAMU_FRUIT,
            SCALD, PSYCHIC, SLACK_OFF, CALM_MIND,
            252, 0, 0, 200, 56, 0
    ),
    レアコイル_1(
            MAGNRTON, UNKNOWN, MODEST, LAPEL_OF_SPIRIT,
            THUNDERBOLT, FLASH_CANNON, THUNDER_WAVE, STEEL_BEAM,
            152, 0, 0, 252, 0, 100
    ),
    カモネギ_1(
            FARFETCHD, MALE, JOLLY, MARK_OF_KINGS,
            BRAVE_BIRD, DOUBLE_EDGE, SWORDS_DANCE, SWAGGER,
            6, 252, 0, 0, 0, 252
    ),
    ドードリオ_1(
            DODRIO, MALE, JOLLY, MARK_OF_KINGS,
            BRAVE_BIRD, DOUBLE_EDGE, DOUBLE_TEAM, SWAGGER,
            6, 252, 0, 0, 0, 252
    ),
    ジュゴン_1(
            DEWGONG, MALE, CALM, RAMU_FRUIT,
            ICE_BEAM, REST, SCALD, ICE_SHARD,
            252, 0, 0, 200, 48, 0
    ),
    ベトベトン_1(
            MUK, MALE, BOLD, RAMU_FRUIT,
            SLUDGE_BOMB, TOXIC, REST, MEMENTO,
            252, 0, 48, 152, 48, 0
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
    イワーク_1(
            ONIX, FEMALE, JOLLY, RAMU_FRUIT,
            SAND_STORM, EARTHQUAKE, SELF_DESTRUCT, STONE_EDGE,
            0, 0, 252, 0, 0, 252
    ),
    スリーパー_1(
            HYPNO, MALE, MODEST, RAMU_FRUIT,
            PSYCHIC, SHADOW_BALL, DAZZLING_GLEAM, HYPNOSIS,
            50, 0, 0, 252, 202, 6
    ),
    キングラー_1(
            KINGLER, MALE, ADAMANT, PEAL_OF_LIFE,
            LIQUIDATION, BODY_SLAM, ROCK_TOMB, HAMMER_ARM,
            252, 252, 0, 0, 0, 6
    ),
    マルマイン_1(
            ELECTRODE, UNKNOWN, MILD, MARK_OF_KINGS,
            THUNDERBOLT, EXPLOSION, LIGHT_SCREEN, REFLECT,
            0, 100, 0, 152, 0, 252
    ),
    ナッシー_1(
            EXEGGUTOR, MALE, MODEST, OBON_FRUIT,
            GIGA_DRAIN, PSYCHIC, HYPNOSIS, SYNTHESIS,
            252, 0, 0, 252, 0, 6
    ),
    ガラガラ_1(
            MARAWAK, MALE, ADAMANT, OBON_FRUIT,
            EARTHQUAKE, HAMMER_ARM, SWORDS_DANCE, STONE_EDGE,
            252, 252, 0, 0, 0, 6
    ),
    サワムラー_1(
            HITMONLEE, MALE, ADAMANT, OBON_FRUIT,
            CLOSE_COMBAT, MACH_PUNCH, BULK_UP, ROCK_SLIDE,
            6, 252, 0, 0, 0, 252
    ),
    エビワラー_1(
            HITMONCHAN, MALE, ADAMANT, OBON_FRUIT,
            DRAIN_PUNCH, MACH_PUNCH, BULK_UP, ROCK_SLIDE,
            6, 252, 0, 0, 0, 252
    ),
    ベロリンガ_1(
            LICKITUNG, MALE, RELAXED, RAMU_FRUIT,
            BODY_SLAM, FLAMETHROWER, BULLDOZE, PLAY_ROUGH,
            252, 252, 0, 0, 0, 6
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
            SNORLAX, MALE, SERIOUS, RAMU_FRUIT,
            BODY_SLAM, EARTHQUAKE, REST, SELF_DESTRUCT,
            252, 200, 52, 0, 6, 0
    ),
    フリーザー_1(
            ARTICUNO, UNKNOWN, HARDY, LIGHT_POWDER,
            BLIZZARD, HURRICANE, ICE_SHARD, HAIL,
            52, 0, 0, 200, 252, 6
    ),
    サンダー_1(
            ZAPDOS, UNKNOWN, SERIOUS, KAMURA_FRUIT,
            THUNDER, HURRICANE, RAIN_DANCE, THUNDER_WAVE,
            50, 0, 50, 202, 50, 152
    ),
    ファイヤー_1(
            MOLTRES, UNKNOWN, QUIRKY, RAMU_FRUIT,
            FIRE_BLAST, AIR_SLASH, WILL_O_WISP, HIDDEN_POWER_GRASS,
            0, 0, 0, 252, 0, 252
    ),
    カイリュー_1(
            DRAGONITE, MALE, NAUGHTY, LOSTOVER_FOOD,
            DRAGON_CLAW, DRAGON_DANCE, EARTHQUAKE, EXTREME_SPEED,
            52, 200, 52, 0, 52, 148
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
