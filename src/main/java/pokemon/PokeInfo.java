package pokemon;

import Enum.Gender;
import Enum.Nature;
import move.Move;
import pokemonStatus.*;
import statusAilment.Ailment;

import java.util.List;

// ポケモンの情報を表現するクラス
public interface PokeInfo {
    // ポケモン固有の値
    BasePrm basePrm();

    // 個体値
    IndividualValue individualValue();

    // 努力値
    EffortValue effortValue();

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
    int realAttack();

    // 防御実数値
    int realBlock();

    // 特攻実数値
    int realContact();

    // 特防実数値
    int realDefence();

    // 素早実数値
    int realSpeed();

    // 総獲得経験値
    Experience experience();

    // ステータスランク
    StatusRank statusRank();

    // 怯み判定
    Flinch flinch();

    // 状態異常1
    Ailment ailment();

    // 与える経験値
    int giveExp();

    // 経験値を得る
    PokeInfo addExp(int exp) throws InterruptedException;

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

    PokeInfo withExp(int addingExperience);

    PokeInfo withLevel(int addLevel);

    PokeInfo withChStatusRank(int attack, int block, int contact, int defence, int speed, int hitRate, int avoidRate);

    PokeInfo withResetStatusRank();

    PokeInfo withMove(Move move);

    PokeInfo withAilment(Ailment statusAilment);

    PokeInfo withFlinch(Flinch flinch);
}
