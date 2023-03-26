package pokemon;

import Enum.Type;
import lombok.AllArgsConstructor;

import static Enum.Type.*;

@SuppressWarnings("ALL")
@AllArgsConstructor
public enum BasePrm {
    // 各ポケモンの固有の値を表現する
    VENUSAUR("フシギバナ", GRASS, POISON, 80, 82, 83, 100, 100, 80),
    CHARIZARD("リザードン", FIRE, FLYING, 78, 84, 78, 109, 85, 100),
    BLASTOISE("カメックス", WATER, NONE, 79, 83, 100, 85, 105, 78),
    BUTTERFREE("バタフリー", BUG, FLYING, 60, 45, 50, 80, 80, 70),
    BEEDRILL("スピアー", BUG, POISON, 65, 90, 40, 45, 80, 75),
    PIDGEOT("ピジョット", NORMAL, FLYING, 83, 80, 75, 70, 70, 101),
    RATICATE("ラッタ", NORMAL, NONE, 55, 81, 60, 50, 70, 97),
    FEAROW("オニドリル", NORMAL, FLYING, 65, 90, 65, 61, 61, 100),
    ARBOK("アーボック", POISON, NONE, 60, 95, 69, 65, 79, 80),
    RAICHU("ライチュウ", ELECTRIC, NONE, 60, 90, 55, 90, 80, 110),
    SANDSLASH("サンドパン", GROUND, NONE, 75, 100, 110, 45, 55, 65),
    NIDOQUEEN("ニドクイン", POISON, GROUND, 90, 92, 87, 75, 85, 76),
    NIDOKING("ニドキング", POISON, GROUND, 81, 102, 77, 85, 75, 85),
    CLEFABLE("ピクシー", FAIRY, NONE, 95, 70, 73, 95, 90, 60),
    NINETALES("キュウコン", FIRE, NONE, 73, 76, 75, 81, 100, 100),
    WIGGLYTUFF("プクリン", NORMAL, FAIRY, 140, 70, 45, 85, 50, 45),
    GOLBAT("ゴルバット", POISON, FLYING, 75, 80, 70, 65, 75, 90),
    VILEPLUME("ラフレシア", GRASS, POISON, 75, 80, 85, 110, 90, 50),
    PARASECT("パラセクト", BUG, GRASS, 60, 95, 80, 60, 80, 30),
    VENOMATH("モルフォン", BUG, POISON, 70, 65, 60, 90, 75, 90),
    DUGTRIO("ダグトリオ", GROUND, NONE, 35, 100, 50, 50, 70, 120),
    PERSIAN("ペルシアン", NORMAL, NONE, 65, 70, 60, 65, 65, 115),
    GOLDUCK("ゴルダック", WATER, NONE, 80, 82, 78, 95, 80, 85),
    PRIMEAPE("オコリザル", FIGHTING, NONE, 65, 105, 60, 60, 70, 95),
    ARCANINE("ウインディ", FIRE, NONE, 90, 110, 80, 100, 80, 95),
    POLIWRATH("ニョロボン", WATER, FIGHTING, 90, 95, 95, 70, 90, 70),
    AKAKAZAM("フーディン", PSYCHIC, NONE, 55, 50, 45, 135, 95, 120),
    MACHAMP("カイリキー", FIGHTING, NONE, 90, 130, 80, 65, 85, 55),
    VICTREEBEL("ウツボット", GRASS, POISON, 80, 105, 65, 100, 70, 70),
    TENTACRUEL("ドククラゲ", WATER, POISON, 80, 70, 65, 80, 120, 100),
    GOLEM("ゴローニャ", ROCK, GROUND, 80, 120, 130, 55, 65, 45),
    RAPIDASH("ギャロップ", FIRE, NONE, 65, 100, 70, 80, 80, 105),
    SLOWBRO("ヤドラン", WATER, PSYCHIC, 95, 75, 110, 100, 80, 30),
    MAGNRTON("レアコイル", ELECTRIC, STEEL, 50, 60, 95, 120, 70, 70),
    FARFETCHD("カモネギ", NORMAL, FLYING, 52, 90, 55, 58, 62, 60),
    DODRIO("ドードリオ", NORMAL, FLYING, 60, 110, 70, 60, 60, 110),
    DEWGONG("ジュゴン", WATER, ICE, 90, 70, 80, 70, 95, 70),
    MUK("ベトベトン", POISON, NONE, 105, 105, 75, 65, 100, 50),
    CLOYSTER("パルシェン", WATER, ICE, 50, 95, 180, 85, 45, 70),
    GENGAR("ゲンガー", GHOST, POISON, 60, 65, 60, 130, 75, 110),
    ONIX("イワーク", ROCK, GROUND, 35, 45, 160, 30, 45, 70),
    HYPNO("スリーパー", PSYCHIC, NONE, 85, 73, 70, 73, 115, 67),
    KINGLER("キングラー", WATER, NONE, 55, 130, 115, 50, 50, 75),
    ELECTRODE("マルマイン", ELECTRIC, NONE, 60, 50, 70, 80, 80, 150),
    EXEGGUTOR("ナッシー", GRASS, PSYCHIC, 95, 95, 85, 125, 75, 55),
    MARAWAK("ガラガラ", GROUND, NONE, 60, 80, 110, 50, 80, 45),
    HITMONLEE("サワムラー", FIGHTING, NONE, 50, 120, 53, 35, 110, 87),
    HITMONCHAN("エビワラー", FIGHTING, NONE, 50, 105, 79, 35, 110, 76),
    LICKITUNG("ベロリンガ", NORMAL, NONE, 90, 55, 75, 60, 75, 30),
    WEEZING("マタドガス", POISON, NONE, 65, 90, 120, 85, 70, 60),
    RHYDON("サイドン", GROUND, ROCK, 105, 130, 120, 45, 45, 40),
    CHANSEY("ラッキー", NORMAL, NONE, 250, 5, 5, 35, 105, 50),
    TANGELA("モンジャラ", GRASS, NONE, 65, 55, 115, 100, 40, 60),
    KANGASKHAN("ガルーラ", NORMAL, NONE, 105, 95, 80, 40, 80, 90),
    SRADRA("シードラ", WATER, NONE, 55, 65, 95, 95, 45, 85),
    SEAKING("アズマオウ", WATER, NONE, 80, 92, 65, 65, 80, 68),
    STARMIE("スターミー", WATER, PSYCHIC, 60, 75, 85, 100, 85, 115),
    MRMIME("バリヤード", PSYCHIC, FAIRY, 40, 45, 65, 100, 120, 90),
    SCYTHER("ストライク", BUG, FLYING, 70, 110, 80, 55, 80, 105),
    JYNX("ルージュラ", ICE, PSYCHIC, 65, 50, 35, 115, 95, 95),
    ELECTABUZZ("エレブー", ELECTRIC, NONE, 65, 83, 57, 95, 85, 105),
    MAGMAR("ブーバー", FIRE, NONE, 65, 95, 57, 100, 85, 93),
    PINSIR("カイロス", BUG, NONE, 65, 125, 100, 55, 70, 85),
    TAUROS("ケンタロス", NORMAL, NONE, 75, 100, 95, 40, 70, 110),
    GYARADOS("ギャラドス", WATER, FLYING, 95, 125, 79, 60, 100, 81),
    LAPRAS("ラプラス", WATER, ICE, 130, 85, 80, 85, 95, 60),
    DITTO("メタモン", NORMAL, NONE, 48, 48, 48, 48, 48, 48),
    VAPAREON("シャワーズ", WATER, NONE, 130, 65, 60, 110, 95, 65),
    JOLTEON("サンダース", ELECTRIC, NONE, 65, 65, 60, 110, 95, 130),
    FLAREON("ブースター", FIRE, NONE, 65, 130, 60, 95, 110, 65),
    PORYGON("ポリゴン", NORMAL, NONE, 65, 60, 70, 85, 75, 40),
    OMASTAR("オムスター", ROCK, WATER, 70, 60, 125, 115, 70, 55),
    KABUTOPS("カブトプス", ROCK, WATER, 60, 115, 105, 65, 70, 80),
    AERODACTYL("プテラ", ROCK, FLYING, 80, 105, 65, 60, 75, 130),
    SNORLAX("カビゴン", NORMAL, NONE, 160, 110, 65, 65, 110, 30),
    ARTICUNO("フリーザー", ICE, FLYING, 90, 85, 100, 95, 125, 85),
    ZAPDOS("サンダー", ELECTRIC, FLYING, 90, 90, 85, 125, 90, 100),
    MOLTRES("ファイヤー", FIRE, FLYING, 90, 100, 90, 125, 85, 90),
    DRAGONITE("カイリュー", DRAGON, FLYING, 91, 134, 95, 100, 100, 80),
    SCIZOR("ハッサム", BUG, STEEL, 70, 130, 100, 55, 80, 65);

    private final String pName;
    private final Type type1;
    private final Type type2;
    private final int hp;
    private final int attack;
    private final int block;
    private final int contact;
    private final int defence;
    private final int speed;

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
}
