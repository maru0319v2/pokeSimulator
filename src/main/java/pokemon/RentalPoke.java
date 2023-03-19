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
    VENUSAUR_1(
            VENUSAUR, MALE, MODEST, KAMURA_FRUIT,
            GIGA_DRAIN, LIGHT_SCREEN, SLUDGE_BOMB, SLEEP_POWDER,
            252, 0, 0, 252, 0, 0
    ),
    CHARIZARD_1(
            CHARIZARD, MALE, MODEST, KAMURA_FRUIT,
            FIRE_BLAST, SUNNY_DAY, AIR_SLASH, DRAGON_DANCE,
            0, 0, 0, 252, 0, 252
    ),
    BLASTOISE_1(
            BLASTOISE, MALE, MODEST, KAMURA_FRUIT,
            WATER_SPOUT, RAIN_DANCE, ICE_BEAM, AQUA_JET,
            0, 0, 0, 252, 0, 252
    ),
    BUTTERFREE_1(
            BUTTERFREE, FEMALE, TIMID, LAPEL_OF_SPIRIT,
            BUG_BUZZ, QUIVER_DANCE, SLEEP_POWDER, PSYCHIC,
            0, 0, 0, 252, 0, 252
    ),
    ARCANINE_1(
            ARCANINE, MALE, NAIVE, RAMU_FRUIT,
            OVERHEAT, CLOSE_COMBAT, EXTREME_SPEED, CRUNCH,
            0, 252, 0, 6, 0, 252
    ),
    AKAKAZAM_1(
            AKAKAZAM, MALE, TIMID, LAPEL_OF_SPIRIT,
            PSYCHIC, SHADOW_BALL, ENERGY_BALL, HYPNOSIS,
            6, 0, 0, 252, 0, 252
    ),
    STARMIE_1(
            STARMIE, UNKNOWN, TIMID, KAMURA_FRUIT,
            ICE_BEAM, PSYCHIC, THUNDERBOLT, SURF,
            0, 0, 0, 252, 0, 252
    ),
    GENGAR_1(
            GENGAR, FEMALE, TIMID, LAPEL_OF_SPIRIT,
            SHADOW_BALL, CONFUSE_RAY, THUNDERBOLT, HYPNOSIS,
            0, 0, 0, 252, 0, 252
    ),
    GYARADOS_1(
            GYARADOS, FEMALE, ADAMANT, LOSTOVER_FOOD,
            WATERFALL, EARTHQUAKE, DRAGON_DANCE, STONE_EDGE,
            252, 152, 0, 0, 0, 100
    ),
    ZAPDOS_1(
            ZAPDOS, UNKNOWN, TIMID, KAMURA_FRUIT,
            DRILL_PECK, AIR_SLASH, THUNDERBOLT, REFLECT,
            0, 0, 0, 252, 0, 252
    ),
    DRAGONITE_1(
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
                new LevelI(50),
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
