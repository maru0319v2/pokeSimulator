package pokemon;

import Enum.ExpType;
import Enum.Type;
import lombok.AllArgsConstructor;
import move.BaseMvPrm;
import move.Move;

import java.util.List;

import static move.MoveI.initMv;

@SuppressWarnings("ALL")
@AllArgsConstructor
public enum BasePrm {
    // 各ポケモンの固有の値を表現する
    VENUSAUR(
            "003", "フシギバナ",
            Type.GRASS, Type.POISON,
            80, 82, 83, 100, 100, 80,
            ExpType.TYPE1050000, 263,
            0, 0, 0, 2, 1, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.VINE_WHIP), initMv(BaseMvPrm.GROWL), initMv(BaseMvPrm.SWORDS_DANCE))
    ),
    CHARIZARD(
            "006", "リザードン",
            Type.FIRE, Type.FLYING,
            78, 84, 78, 109, 85, 100,
            ExpType.TYPE1050000, 267,
            0, 0, 0, 3, 0, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    BLASTOISE(
            "009", "カメックス",
            Type.WATER, Type.NONE,
            79, 83, 100, 85, 105, 78,
            ExpType.TYPE1050000, 265,
            0, 0, 0, 0, 3, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    BUTTERFREE(
            "012", "バタフリー",
            Type.BUG, Type.FLYING,
            60, 45, 50, 80, 80, 70,
            ExpType.TYPE1000000, 198,
            0, 0, 0, 2, 1, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    BEEDRILL(
            "015", "スピアー",
            Type.BUG, Type.POISON,
            65, 90, 40, 45, 80, 75,
            ExpType.TYPE1000000, 178,
            0, 2, 0, 0, 1, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    PIDGEOT(
            "018", "ピジョット",
            Type.NORMAL, Type.FLYING,
            83, 80, 75, 70, 70, 101,
            ExpType.TYPE1050000, 240,
            0, 0, 0, 0, 0, 3,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    RATICATE(
            "020", "ラッタ",
            Type.NORMAL, Type.NONE,
            55, 81, 60, 50, 70, 97,
            ExpType.TYPE1000000, 145,
            0, 0, 0, 0, 0, 2,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    FEAROW(
            "022", "オニドリル",
            Type.NORMAL, Type.FLYING,
            65, 90, 65, 61, 61, 100,
            ExpType.TYPE1000000, 155,
            0, 0, 0, 0, 0, 2,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    ARBOK(
            "024", "アーボック",
            Type.POISON, Type.NONE,
            60, 95, 69, 65, 79, 80,
            ExpType.TYPE1000000, 157,
            0, 2, 0, 0, 0, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    RAICHU(
            "026", "ライチュウ",
            Type.ELECTRIC, Type.NONE,
            60, 90, 55, 90, 80, 110,
            ExpType.TYPE1000000, 243,
            0, 0, 0, 0, 0, 3,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    SANDSLASH(
            "028", "サンドパン",
            Type.GROUND, Type.NONE,
            75, 100, 110, 45, 55, 65,
            ExpType.TYPE1000000, 158,
            0, 0, 2, 0, 0, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    NIDOQUEEN(
            "031", "ニドクイン",
            Type.POISON, Type.GROUND,
            90, 92, 87, 75, 85, 76,
            ExpType.TYPE1050000, 253,
            3, 0, 0, 0, 0, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    NIDOKING(
            "034", "ニドキング",
            Type.POISON, Type.GROUND,
            81, 102, 77, 85, 75, 85,
            ExpType.TYPE1050000, 253,
            0, 3, 0, 0, 0, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    CLEFABLE(
            "035", "ピクシー",
            Type.FAIRY, Type.NONE,
            95, 70, 73, 95, 90, 60,
            ExpType.TYPE1050000, 253,
            3, 0, 0, 0, 0, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    NINETALES(
            "038", "キュウコン",
            Type.FIRE, Type.NONE,
            73, 76, 75, 81, 100, 100,
            ExpType.TYPE1000000, 177,
            0, 0, 0, 0, 1, 1,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    WIGGLYTUFF(
            "040", "プクリン",
            Type.NORMAL, Type.FAIRY,
            140, 70, 45, 85, 50, 45,
            ExpType.TYPE800000, 218,
            3, 0, 0, 0, 0, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    GOLBAT(
            "042", "ゴルバット",
            Type.POISON, Type.FLYING,
            75, 80, 70, 65, 75, 90,
            ExpType.TYPE1000000, 159,
            0, 0, 0, 0, 0, 2,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    VILEPLUME(
            "045", "ラフレシア",
            Type.GRASS, Type.POISON,
            75, 80, 85, 110, 90, 50,
            ExpType.TYPE1050000, 245,
            0, 0, 0, 3, 0, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    PARASECT(
            "047", "パラセクト",
            Type.BUG, Type.GRASS,
            60, 95, 80, 60, 80, 30,
            ExpType.TYPE1050000, 245,
            0, 2, 1, 0, 0, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    VENOMATH(
            "049", "モルフォン",
            Type.BUG, Type.POISON,
            70, 65, 60, 90, 75, 90,
            ExpType.TYPE1000000, 158,
            0, 0, 0, 1, 0, 1,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    DUGTRIO(
            "051", "ダグトリオ",
            Type.GROUND, Type.NONE,
            35, 100, 50, 50, 70, 120,
            ExpType.TYPE1000000, 149,
            0, 0, 0, 0, 0, 2,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    PERSIAN(
            "053", "ペルシアン",
            Type.NORMAL, Type.NONE,
            65, 70, 60, 65, 65, 115,
            ExpType.TYPE1000000, 154,
            0, 0, 0, 0, 0, 2,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    GOLDUCK(
            "055", "ゴルダック",
            Type.WATER, Type.NONE,
            80, 82, 78, 95, 80, 85,
            ExpType.TYPE1000000, 175,
            0, 0, 0, 2, 0, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    PRIMEAPE(
            "057", "オコリザル",
            Type.FIGHTING, Type.NONE,
            65, 105, 60, 60, 70, 95,
            ExpType.TYPE1000000, 159,
            0, 2, 0, 0, 0, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    ARCANINE(
            "059", "ウインディ",
            Type.FIRE, Type.NONE,
            90, 110, 80, 100, 80, 95,
            ExpType.TYPE1250000, 194,
            0, 2, 0, 0, 0, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    POLIWRATH(
            "062", "ニョロボン",
            Type.WATER, Type.FIGHTING,
            90, 95, 95, 70, 90, 70,
            ExpType.TYPE1050000, 255,
            0, 0, 3, 0, 0, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    AKAKAZAM(
            "065", "フーディン",
            Type.PSYCHIC, Type.NONE,
            55, 50, 45, 135, 95, 120,
            ExpType.TYPE1050000, 250,
            0, 0, 0, 3, 0, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    MACHAMP(
            "068", "カイリキー",
            Type.FIGHTING, Type.NONE,
            90, 130, 80, 65, 85, 55,
            ExpType.TYPE1050000, 253,
            0, 3, 0, 0, 0, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    VICTREEBEL(
            "071", "ウツボット",
            Type.GRASS, Type.POISON,
            80, 105, 65, 100, 70, 70,
            ExpType.TYPE1050000, 245,
            0, 3, 0, 0, 0, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    TENTACRUEL(
            "073", "ドククラゲ",
            Type.WATER, Type.POISON,
            80, 70, 65, 80, 120, 100,
            ExpType.TYPE1250000, 180,
            0, 0, 0, 0, 2, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    GOLEM(
            "076", "ゴローニャ",
            Type.ROCK, Type.GROUND,
            80, 120, 130, 55, 65, 45,
            ExpType.TYPE1050000, 248,
            0, 0, 3, 0, 0, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    RAPIDASH(
            "078", "ギャロップ",
            Type.FIRE, Type.NONE,
            65, 100, 70, 80, 80, 105,
            ExpType.TYPE1000000, 175,
            0, 0, 0, 0, 0, 2,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    SLOWBRO(
            "080", "ヤドラン",
            Type.WATER, Type.PSYCHIC,
            95, 75, 110, 100, 80, 30,
            ExpType.TYPE1000000, 172,
            0, 0, 2, 0, 0, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    MAGNRTON(
            "082", "レアコイル",
            Type.ELECTRIC, Type.STEEL,
            50, 60, 95, 120, 70, 70,
            ExpType.TYPE1000000, 163,
            0, 0, 0, 2, 0, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    FARFETCHD(
            "083", "カモネギ",
            Type.NORMAL, Type.FIGHTING,
            52, 90, 55, 58, 62, 60,
            ExpType.TYPE1000000, 132,
            0, 1, 0, 0, 0, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    DODRIO(
            "085", "ドードリオ",
            Type.NORMAL, Type.FIGHTING,
            60, 110, 70, 60, 60, 110,
            ExpType.TYPE1000000, 165,
            0, 2, 0, 0, 0, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    DEWGONG(
            "087", "ジュゴン",
            Type.WATER, Type.ICE,
            90, 70, 80, 70, 95, 70,
            ExpType.TYPE1000000, 166,
            0, 0, 0, 0, 2, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    MUK(
            "089", "ベトベトン",
            Type.POISON, Type.NONE,
            105, 105, 75, 65, 100, 50,
            ExpType.TYPE1000000, 175,
            1, 1, 0, 0, 0, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    CLOYSTER(
            "091", "パルシェン",
            Type.WATER, Type.ICE,
            50, 95, 180, 85, 45, 70,
            ExpType.TYPE1250000, 184,
            0, 0, 2, 0, 0, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    GENGAR(
            "094", "ゲンガー",
            Type.GHOST, Type.POISON,
            60, 65, 60, 130, 75, 110,
            ExpType.TYPE1050000, 250,
            0, 0, 0, 3, 0, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    ONIX(
            "095", "イワーク",
            Type.ROCK, Type.GROUND,
            35, 45, 160, 30, 45, 70,
            ExpType.TYPE1000000, 77,
            0, 0, 1, 0, 0, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    HYPNO(
            "097", "スリーパー",
            Type.PSYCHIC, Type.NONE,
            85, 73, 70, 73, 115, 67,
            ExpType.TYPE1000000, 169,
            0, 0, 0, 0, 2, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    KINGLER(
            "099", "キングラー",
            Type.WATER, Type.NONE,
            55, 130, 115, 50, 50, 75,
            ExpType.TYPE1000000, 166,
            0, 2, 0, 0, 0, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    ELECTRODE(
            "101", "マルマイン",
            Type.ELECTRIC, Type.NONE,
            60, 50, 70, 80, 80, 150,
            ExpType.TYPE1000000, 172,
            0, 0, 0, 0, 0, 2,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    EXEGGUTOR(
            "103", "ナッシー",
            Type.GRASS, Type.PSYCHIC,
            95, 95, 85, 125, 75, 55,
            ExpType.TYPE1250000, 186,
            0, 0, 0, 2, 0, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    MARAWAK(
            "105", "ガラガラ",
            Type.GROUND, Type.NONE,
            60, 80, 110, 50, 80, 45,
            ExpType.TYPE1000000, 149,
            0, 0, 2, 0, 0, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    HITMONLEE(
            "106", "サワムラー",
            Type.FIGHTING, Type.NONE,
            50, 120, 53, 35, 110, 87,
            ExpType.TYPE1000000, 159,
            0, 2, 0, 0, 0, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    HITMONCHAN(
            "107", "エビワラー",
            Type.FIGHTING, Type.NONE,
            50, 105, 79, 35, 110, 76,
            ExpType.TYPE1000000, 159,
            0, 0, 0, 0, 2, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    LICKITUNG(
            "108", "ベロリンガ",
            Type.NORMAL, Type.NONE,
            90, 55, 75, 60, 75, 30,
            ExpType.TYPE1000000, 77,
            2, 0, 0, 0, 0, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    WEEZING(
            "110", "マタドガス",
            Type.POISON, Type.NONE,
            65, 90, 120, 85, 70, 60,
            ExpType.TYPE1000000, 172,
            0, 0, 2, 0, 0, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    RHYDON(
            "112", "サイドン",
            Type.GROUND, Type.ROCK,
            105, 130, 120, 45, 45, 40,
            ExpType.TYPE1250000, 170,
            0, 2, 0, 0, 0, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    CHANSEY(
            "113", "ラッキー",
            Type.NORMAL, Type.NONE,
            250, 5, 5, 35, 105, 50,
            ExpType.TYPE800000, 395,
            2, 0, 0, 0, 0, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    TANGELA(
            "114", "モンジャラ",
            Type.GRASS, Type.NONE,
            65, 55, 115, 100, 40, 60,
            ExpType.TYPE1000000, 87,
            0, 0, 1, 0, 0, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    KANGASKHAN(
            "115", "ガルーラ",
            Type.NORMAL, Type.NONE,
            105, 95, 80, 40, 80, 90,
            ExpType.TYPE1000000, 172,
            2, 0, 0, 0, 0, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    SRADRA(
            "117", "シードラ",
            Type.WATER, Type.NONE,
            55, 65, 95, 95, 45, 85,
            ExpType.TYPE1000000, 154,
            0, 0, 1, 1, 0, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    SEAKING(
            "119", "アズマオウ",
            Type.WATER, Type.NONE,
            80, 92, 65, 65, 80, 68,
            ExpType.TYPE1000000, 158,
            0, 2, 0, 0, 0, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    STARMIE(
            "121", "スターミー",
            Type.WATER, Type.PSYCHIC,
            60, 75, 85, 100, 85, 115,
            ExpType.TYPE1250000, 182,
            0, 0, 0, 0, 0, 2,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    MRMIME(
            "122", "バリヤード",
            Type.PSYCHIC, Type.FAIRY,
            40, 45, 65, 100, 120, 90,
            ExpType.TYPE1000000, 161,
            0, 0, 0, 0, 2, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    SCYTHER(
            "123", "ストライク",
            Type.BUG, Type.FLYING,
            70, 110, 80, 55, 80, 105,
            ExpType.TYPE1000000, 100,
            0, 1, 0, 0, 0, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    JYNX(
            "124", "ルージュラ",
            Type.ICE, Type.PSYCHIC,
            65, 50, 35, 115, 95, 95,
            ExpType.TYPE1000000, 159,
            0, 0, 0, 2, 0, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    ELECTABUZZ(
            "125", "エレブー",
            Type.ELECTRIC, Type.NONE,
            65, 83, 57, 95, 85, 105,
            ExpType.TYPE1000000, 172,
            0, 0, 0, 0, 0, 2,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    MAGMAR(
            "126", "ブーバー",
            Type.FIRE, Type.NONE,
            65, 95, 57, 100, 85, 93,
            ExpType.TYPE1000000, 172,
            0, 0, 0, 2, 0, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    PINSIR(
            "127", "カイロス",
            Type.BUG, Type.NONE,
            65, 125, 100, 55, 70, 85,
            ExpType.TYPE1250000, 175,
            0, 2, 0, 0, 0, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    TAUROS(
            "128", "ケンタロス",
            Type.NORMAL, Type.NONE,
            75, 100, 95, 40, 70, 110,
            ExpType.TYPE1250000, 172,
            0, 1, 0, 0, 0, 1,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    GYARADOS(
            "130", "ギャラドス",
            Type.WATER, Type.FLYING,
            95, 125, 79, 60, 100, 81,
            ExpType.TYPE1250000, 189,
            0, 2, 0, 0, 0, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    LAPRAS(
            "131", "ラプラス",
            Type.WATER, Type.ICE,
            130, 85, 80, 85, 95, 60,
            ExpType.TYPE1250000, 187,
            2, 0, 0, 0, 0, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    DITTO(
            "132", "メタモン",
            Type.NORMAL, Type.NONE,
            48, 48, 48, 48, 48, 48,
            ExpType.TYPE1000000, 101,
            1, 0, 0, 0, 0, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    VAPAREON(
            "134", "シャワーズ",
            Type.WATER, Type.NONE,
            130, 65, 60, 110, 95, 65,
            ExpType.TYPE1000000, 184,
            2, 0, 0, 0, 0, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    JOLTEON(
            "135", "サンダース",
            Type.ELECTRIC, Type.NONE,
            65, 65, 60, 110, 95, 130,
            ExpType.TYPE1000000, 184,
            0, 0, 0, 0, 0, 2,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    FLAREON(
            "136", "ブースター",
            Type.FIRE, Type.NONE,
            65, 130, 60, 95, 110, 65,
            ExpType.TYPE1000000, 184,
            0, 2, 0, 0, 0, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    PORYGON(
            "137", "ポリゴン",
            Type.NORMAL, Type.NONE,
            65, 60, 70, 85, 75, 40,
            ExpType.TYPE1000000, 184,
            0, 0, 0, 1, 0, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    OMASTAR(
            "139", "オムスター",
            Type.ROCK, Type.WATER,
            70, 60, 125, 115, 70, 55,
            ExpType.TYPE1000000, 173,
            0, 0, 2, 0, 0, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    KABUTOPS(
            "141", "カブトプス",
            Type.ROCK, Type.WATER,
            60, 115, 105, 65, 70, 80,
            ExpType.TYPE1000000, 173,
            0, 2, 0, 0, 0, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    AERODACTYL(
            "142", "プテラ",
            Type.ROCK, Type.FLYING,
            80, 105, 65, 60, 75, 130,
            ExpType.TYPE1250000, 180,
            0, 0, 0, 0, 0, 2,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    SNORLAX(
            "143", "カビゴン",
            Type.NORMAL, Type.NONE,
            160, 110, 65, 65, 110, 30,
            ExpType.TYPE1250000, 189,
            2, 0, 0, 0, 0, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    ARTICUNO(
            "144", "フリーザー",
            Type.ICE, Type.FLYING,
            90, 85, 100, 95, 125, 85,
            ExpType.TYPE1250000, 290,
            0, 0, 0, 0, 3, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    ZAPDOS(
            "145", "サンダー",
            Type.ELECTRIC, Type.FLYING,
            90, 90, 85, 125, 90, 100,
            ExpType.TYPE1250000, 290,
            0, 0, 0, 3, 0, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    MOLTRES(
            "146", "ファイヤー",
            Type.FIRE, Type.FLYING,
            90, 100, 90, 125, 85, 90,
            ExpType.TYPE1250000, 290,
            0, 0, 0, 3, 0, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    DRAGONITE(
            "149", "カイリュー",
            Type.DRAGON, Type.FLYING,
            91, 134, 95, 100, 100, 80,
            ExpType.TYPE1250000, 300,
            0, 3, 0, 0, 0, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    ),
    SCIZOR(
            "212", "ハッサム",
            Type.BUG, Type.STEEL,
            70, 130, 100, 55, 80, 65,
            ExpType.TYPE1000000, 1,
            0, 0, 0, 0, 0, 0,
            List.of(initMv(BaseMvPrm.TACKLE), initMv(BaseMvPrm.GROWL))
    );

    private final String pokeDexNo;
    private final String pName;
    private final Type type1;
    private final Type type2;
    private final int hp;
    private final int attack;
    private final int block;
    private final int contact;
    private final int defence;
    private final int speed;
    private final ExpType expType;
    private final int basicExp;
    private final int givenEffortH;
    private final int givenEffortA;
    private final int givenEffortB;
    private final int givenEffortC;
    private final int givenEffortD;
    private final int givenEffortS;
    private final List<Move> initialMove;

    public String pokeDexNo() {
        return this.pokeDexNo;
    }

    public String pName() {
        return this.pName;
    }

    public Type type1() {
        return this.type1;
    }

    public Type type2() {
        return this.type2;
    }

    public int hp() {
        return this.hp;
    }

    public int attack() {
        return this.attack;
    }

    public int block() {
        return this.block;
    }

    public int contact() {
        return this.contact;
    }

    public int defence() {
        return this.defence;
    }

    public int speed() {
        return this.speed;
    }

    public ExpType expType() {
        return this.expType;
    }

    public int basicExp() {
        return this.basicExp;
    }

    public int givenEffortH() {
        return this.givenEffortH;
    }

    public int givenEffortA() {
        return this.givenEffortA;
    }

    public int givenEffortB() {
        return this.givenEffortB;
    }

    public int givenEffortC() {
        return this.givenEffortC;
    }

    public int givenEffortD() {
        return this.givenEffortD;
    }

    public int givenEffortS() {
        return this.givenEffortS;
    }

    public List<Move> initialMove() {
        return this.initialMove;
    }
}
