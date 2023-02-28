package move;

import Enum.*;
import bussinessLogic.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import pokemon.PokemonInfo;

import static bussinessLogic.ConsoleOutManager.showMessageParChar;

@Getter
@AllArgsConstructor
public enum BaseMPrm {
    TACKLE("たいあたり", Type.NORMAL, MoveSpecies.PHYSICAL, 40, 95, 35, 0, 0,
            true, false, true, false, false, false
    ) {
        @Override
        public InBattlePokemons effect(PokemonInfo attackPoke, PokemonInfo defensePoke) {
            return new InBattlePokemons(attackPoke, defensePoke);
        }
    },
    QUICK_ATTACK("でんこうせっか", Type.NORMAL, MoveSpecies.PHYSICAL, 40, 100, 30, 0, 1,
            true, false, true, false, false, false
    ) {
        @Override
        public InBattlePokemons effect(PokemonInfo attackPoke, PokemonInfo defensePoke) {
            return new InBattlePokemons(attackPoke, defensePoke);
        }
    },
    FLAMETHROWER("かえんほうしゃ", Type.FIRE, MoveSpecies.SPECIAL, 90, 100, 15, 0, 0,
            false, false, true, false, false, false
    ) {
        @Override
        public InBattlePokemons effect(PokemonInfo attackPoke, PokemonInfo defensePoke) {
            return new InBattlePokemons(attackPoke, defensePoke);
        }
    },
    DRAGON_CLAW("ドラゴンクロー", Type.DRAGON, MoveSpecies.PHYSICAL, 80, 100, 15, 0, 0,
            true, false, true, false, false, false
    ) {
        @Override
        public InBattlePokemons effect(PokemonInfo attackPoke, PokemonInfo defensePoke) {
            return new InBattlePokemons(attackPoke, defensePoke);
        }
    },
    VINE_WHIP("つるのムチ", Type.GRASS, MoveSpecies.PHYSICAL, 45, 100, 25, 0, 0,
            true, false, true, false, false, false
    ) {
        @Override
        public InBattlePokemons effect(PokemonInfo attackPoke, PokemonInfo defensePoke) {
            return new InBattlePokemons(attackPoke, defensePoke);
        }
    },
    GROWL("なきごえ", Type.NORMAL, MoveSpecies.CHANGE, 0, 100, 20, 0, 0,
            false, true, true, false, true, true
    ) {
        @Override
        public InBattlePokemons effect(PokemonInfo attackPoke, PokemonInfo defensePoke) throws InterruptedException {
            showMessageParChar(defensePoke.getBasePrm().getName() + "の攻撃が下がった!");
            return new InBattlePokemons(attackPoke, defensePoke.withAddedStatusRank(-1,0, 0, 0, 0));
        }
    },
    GROWTH("せいちょう", Type.NORMAL, MoveSpecies.CHANGE, 0, 100, 20, 0, 0,
            false, false, true, false, true, false) {
        @Override
        public InBattlePokemons effect(PokemonInfo attackPoke, PokemonInfo defensePoke) throws InterruptedException {
            showMessageParChar(attackPoke.getBasePrm().getName() + "の特攻が上がった!");
            return new InBattlePokemons(attackPoke.withAddedStatusRank(0,0, 1, 0, 0), defensePoke);
        }
    },
    SWORDS_DANCE("つるぎのまい", Type.NORMAL, MoveSpecies.CHANGE, 0, 100, 20, 0, 0,
            false, false, true, false, true, false) {
        @Override
        public InBattlePokemons effect(PokemonInfo attackPoke, PokemonInfo defensePoke) throws InterruptedException {
            showMessageParChar(attackPoke.getBasePrm().getName() + "の攻撃がぐーんと上がった!");
            return new InBattlePokemons(attackPoke.withAddedStatusRank(2,0, 0, 0, 0), defensePoke);
        }
    };

    // わざめい
    private final String name;
    // わざタイプ
    private final Type moveType;
    // 分類
    private final MoveSpecies moveSpecies;
    // 威力
    private final int damage;
    // 命中率
    private final int hitRate;
    // PP
    private final int powerPoint;
    // 急所ランク
    private final int criticalRank;
    // 優先度
    private final int priority;
    // 直接攻撃
    private final boolean direct;
    // マジックコートで返せる
    private final boolean isReflect;
    // オウム返しできる
    private final boolean isParrot;
    // まもるを貫通できる
    private final boolean isPenetrationDefence;
    // よこどりできる
    private final boolean isSeizure;
    // みがわりを貫通できる
    private final boolean isPenetrationScapegoat;
    // 効果
    public abstract InBattlePokemons effect(PokemonInfo attackPoke, PokemonInfo defensePoke) throws InterruptedException;
}
