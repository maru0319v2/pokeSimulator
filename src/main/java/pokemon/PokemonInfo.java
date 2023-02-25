package pokemon;

import Enum.*;
import pokemonStatus.*;
import move.Move;
import Enum.ExperienceType;

import java.util.List;

// ポケモンの情報を表現するクラス
public interface PokemonInfo {

    // 名前
    String getPokeName();
    // 図鑑No.
    String getPokeDexNo();
    // 分類
    String getSpecies();
    // タイプ1
    Type getType1();
    // タイプ2
    Type getType2();
    // 種族値
    BaseStats getBaseStats();
    // 個体値
    IndividualValue getIndividualValue();
    // 努力値
    EffortValue getEffortValue();
    // 基礎経験値
    int getBasicExperience();
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
    // 経験値タイプ
    ExperienceType getExperienceType();
    // 総獲得経験値
    Experience getExperience();
    // ステータスランク
    StatusRank getStatusRank();
    // 状態異常1
    StatusAilment getStatusAilment();

    // TODO テスト用コード
    PokemonInfo withCurrentHitPoint(CurrentHitPoint currentHitPoint);
    PokemonInfo withExperience(int addingExperience);
    PokemonInfo withLevel(int addLevel);
    PokemonInfo withAddedStatusRank(int attack, int block, int contact, int defense, int speed);
    PokemonInfo withResetStatusRank();
    PokemonInfo withMove(Move move);
    PokemonInfo withStatusAilment(StatusAilment statusAilment);

}
