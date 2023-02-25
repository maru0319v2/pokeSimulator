package move;

import bussinessLogic.*;
import impl.CurrentPowerPointImpl;
import Enum.*;

import static bussinessLogic.ConsoleOutManager.showMessageParChar;

public class Growl implements Move {
    private String name = "なきごえ";
    private final Type moveType = Type.NORMAL;
    private final MoveSpecies moveSpecies = MoveSpecies.CHANGE;
    private int damage = 0;
    private int hitRate = 100;
    private int powerPoint = 20;
    private final CurrentPowerPoint currentPowerPoint;

    public Growl() {
        this.currentPowerPoint = new CurrentPowerPointImpl(powerPoint);
    }

    public Growl(CurrentPowerPoint currentHitPoint) {
        this.currentPowerPoint = currentHitPoint;
    }

    public String name() {
        return this.name;
    }

    public Type moveType() {
        return this.moveType;
    }

    public MoveSpecies moveSpecies() {
        return this.moveSpecies;
    }

    public int damage() {
        return this.damage;
    }

    public int hitRate() {
        return this.hitRate;
    }

    public InBattlePokemons effect(PokemonInfo attackPoke, PokemonInfo defensePoke) throws InterruptedException {
        showMessageParChar(defensePoke.pokeName() + "の攻撃が下がった!");
        return new InBattlePokemons(attackPoke, defensePoke.withAddedStatusRank(-1,0, 0, 0, 0));
    }
    public int powerPoint() {
        return this.powerPoint;
    }

    public CurrentPowerPoint currentPowerPoint() {
        return this.currentPowerPoint;
    }
    @Override
    public Move withCurrentPowerPoint(CurrentPowerPoint decrementedPowerPoint) {
        return new Growl(decrementedPowerPoint);
    }
}
