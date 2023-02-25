package move;

import Enum.*;
import pokemonStatus.CurrentPowerPoint;
import bussinessLogic.InBattlePokemons;
import pokemon.PokemonInfo;

// 技を表現するクラス
public interface Move {
    // 技名
    String getName();
    // タイプ
    Type getMoveType();
    // 分類
    MoveSpecies getMoveSpecies();
    // 威力
    int getDamage();
    // 命中率
    int getHitRate();
    // デフォルトPP
    int getPowerPoint();
    // 現在のPP
    CurrentPowerPoint getCurrentPowerPoint();
    // 技の効果
    InBattlePokemons effect(PokemonInfo attackPoke, PokemonInfo defensePoke) throws InterruptedException;
    Move withCurrentPowerPoint(CurrentPowerPoint decrementedPowerPoint);
}
