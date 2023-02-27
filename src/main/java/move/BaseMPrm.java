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
    TACKLE("たいあたり", Type.NORMAL, MoveSpecies.PHYSICAL, 40, 95, 35) {
        @Override
        public InBattlePokemons effect(PokemonInfo attackPoke, PokemonInfo defensePoke) {
            return new InBattlePokemons(attackPoke, defensePoke);
        }
    },
    FLAMETHROWER("かえんほうしゃ", Type.FIRE, MoveSpecies.SPECIAL, 95, 100, 15) {
        @Override
        public InBattlePokemons effect(PokemonInfo attackPoke, PokemonInfo defensePoke) {
            return new InBattlePokemons(attackPoke, defensePoke);
        }
    },
    DRAGON_CLAW("ドラゴンクロー", Type.DRAGON, MoveSpecies.PHYSICAL, 80, 100, 15) {
        @Override
        public InBattlePokemons effect(PokemonInfo attackPoke, PokemonInfo defensePoke) {
            return new InBattlePokemons(attackPoke, defensePoke);
        }
    },
    VINE_WHIP("つるのムチ", Type.GRASS, MoveSpecies.PHYSICAL, 45, 100, 25) {
        @Override
        public InBattlePokemons effect(PokemonInfo attackPoke, PokemonInfo defensePoke) {
            return new InBattlePokemons(attackPoke, defensePoke);
        }
    },
    GROWL("なきごえ", Type.NORMAL, MoveSpecies.CHANGE, 0, 100, 20) {
        @Override
        public InBattlePokemons effect(PokemonInfo attackPoke, PokemonInfo defensePoke) throws InterruptedException {
            showMessageParChar(defensePoke.getBasePrm().getName() + "の攻撃が下がった!");
            return new InBattlePokemons(attackPoke, defensePoke.withAddedStatusRank(-1,0, 0, 0, 0));
        }
    },
    GROWTH("せいちょう", Type.NORMAL, MoveSpecies.CHANGE, 0, 100, 20) {
        @Override
        public InBattlePokemons effect(PokemonInfo attackPoke, PokemonInfo defensePoke) throws InterruptedException {
            showMessageParChar(attackPoke.getBasePrm().getName() + "の特攻が上がった!");
            return new InBattlePokemons(attackPoke.withAddedStatusRank(0,0, 1, 0, 0), defensePoke);
        }
    },
    SWORDS_DANCE("つるぎのまい", Type.NORMAL, MoveSpecies.CHANGE, 0, 100, 20) {
        @Override
        public InBattlePokemons effect(PokemonInfo attackPoke, PokemonInfo defensePoke) throws InterruptedException {
            showMessageParChar(attackPoke.getBasePrm().getName() + "の攻撃がぐーんと上がった!");
            return new InBattlePokemons(attackPoke.withAddedStatusRank(2,0, 0, 0, 0), defensePoke);
        }
    };

    private final String name;
    private final Type moveType;
    private final MoveSpecies moveSpecies;
    private final int damage;
    private final int hitRate;
    private final int powerPoint;
    public abstract InBattlePokemons effect(PokemonInfo attackPoke, PokemonInfo defensePoke) throws InterruptedException;
}
