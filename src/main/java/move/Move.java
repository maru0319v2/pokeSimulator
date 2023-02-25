package move;

import Enum.*;
import pokemonStatus.CurrentPowerPoint;
import bussinessLogic.InBattlePokemons;
import pokemon.PokemonInfo;

// 技を表現するクラス
public interface Move {
    // 技名
    public String name();
    // タイプ
    public Type moveType();
    // 分類
    public MoveSpecies moveSpecies();
    // 威力
    public int damage();
    // 命中率
    public int hitRate();
    // 技の効果
    public InBattlePokemons effect(PokemonInfo attackPoke, PokemonInfo defensePoke) throws InterruptedException;
    // デフォルトPP
    public int powerPoint();
    // 現在のPP
    public CurrentPowerPoint currentPowerPoint();

    Move withCurrentPowerPoint(CurrentPowerPoint decrementedPowerPoint);
}
