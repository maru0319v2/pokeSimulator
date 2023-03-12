package pokemon;

import Enum.Gender;
import Enum.Item;
import Enum.Nature;
import move.Move;
import pokemonStatus.*;
import statusAilment.Ailment;

import java.util.List;

// ポケモンの情報を表現するクラス
public interface PokeInfo {
    // ポケモン固有の値
    BasePrm basePrm();

    // レベル
    Level level();

    // 性別
    Gender gender();

    // 性格
    Nature nature();

    // 覚えている技
    List<Move> haveMove();

    // 現在の残りHP
    CurrentHP currentHP();

    // HP実数値
    int realHP();

    // 攻撃実数値
    int realAtk();

    // 防御実数値
    int realBlk();

    // 特攻実数値
    int realCnt();

    // 特防実数値
    int realDfc();

    // 素早実数値
    int realSpd();

    // ステータスランク
    StatusRank statusRank();

    // 怯み判定
    Flinch flinch();

    // 混乱判定
    Confusion confusion();

    // 所持アイテム
    Item item();

    // 状態異常1
    Ailment ailment();

    // 体力を回復
    PokeInfo recoveryHP(int value) throws InterruptedException;

    // ダメージを受ける
    PokeInfo damage(int value) throws InterruptedException;

    // 指定PPを回復
    PokeInfo recoveryPP(Move move, int value);

    // 体力、PP全回復
    PokeInfo recoveryAll() throws InterruptedException;

    // PPを使う
    PokeInfo decrementPP(Move usedMove);

    PokeInfo withCurrentHP(CurrentHP currentHitPoint);

    PokeInfo withChStatusRank(int attack, int block, int contact, int defence, int speed, int hitRate, int avoidRate) throws InterruptedException;

    PokeInfo withResetStatusRank();

    PokeInfo withMove(Move move);

    PokeInfo withAilment(Ailment statusAilment);

    PokeInfo withFlinch(Flinch flinch);

    PokeInfo withConfusion(Confusion confusion);

    PokeInfo withItem(Item item);
}
