package pokemon;

import Enum.Gender;
import Enum.Nature;
import move.Move;
import pokemonStatus.*;
import statusAilment.StatusAilment;

import java.util.List;

// ポケモンの情報を表現するクラス
public interface PokeInfo {
    // ポケモン固有の値
    BasePrm getBasePrm();

    // 個体値
    IndividualValue getIndividualValue();

    // 努力値
    EffortValue getEffortValue();

    // レベル
    Level getLevel();

    // 性別
    Gender getGender();

    // 性格
    Nature getNature();

    // 覚えている技
    List<Move> getHaveMove();

    // 現在の残りHP
    CurrentHitPoint getCurrentHitPoint();

    // HP実数値
    int getRealValHitPoint();

    // 攻撃実数値
    int getRealValAttack();

    // 防御実数値
    int getRealValBlock();

    // 特攻実数値
    int getRealValContact();

    // 特防実数値
    int getRealValDefense();

    // 素早実数値
    int getRealValSpeed();

    // 総獲得経験値
    Experience getExperience();

    // ステータスランク
    StatusRank getStatusRank();

    // 怯み判定
    Flinch getFlinch();

    // 状態異常1
    StatusAilment getStatusAilment();

    // 与える経験値
    int giveExp();

    // 経験値を得る
    PokeInfo addExp(int exp) throws InterruptedException;

    // 体力を回復
    PokeInfo recoveryHitPoint(int value) throws InterruptedException;

    // ダメージを受ける
    PokeInfo damagePoke(int value) throws InterruptedException;

    // 指定PPを回復
    PokeInfo recoveryPowerPoint(Move move, int value);

    // 体力、PP全回復
    PokeInfo recoveryAll() throws InterruptedException;

    // PPを使う
    PokeInfo decrementPowerPoint(Move usedMove);

    PokeInfo withCurrentHitPoint(CurrentHitPoint currentHitPoint);

    PokeInfo withExperience(int addingExperience);

    PokeInfo withLevel(int addLevel);

    PokeInfo withAddedStatusRank(int attack, int block, int contact, int defense, int speed, int hitRate, int avoidRate);

    PokeInfo withResetStatusRank();

    PokeInfo withMove(Move move);

    PokeInfo withStatusAilment(StatusAilment statusAilment);

    PokeInfo withFlinch(Flinch flinch);
}
