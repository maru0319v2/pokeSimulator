package main.java.org.example.pokemon;

import main.java.org.example.BaseStats;
import main.java.org.example.EffortValue;
import main.java.org.example.IndividualValue;
import main.java.org.example.PokemonInfo;
import main.java.org.example.impl.*;

public class Bulbasaur implements PokemonInfo {

    public String pokeName = "フシギダネ";
    public BaseStatsImpl baseStats;
    public IndividualValueImpl individualValue;
    public EffortValueImpl effortValue;

    public Bulbasaur() {
        this.baseStats = new BaseStatsImpl(45, 49, 49, 65, 65, 45);
        this.individualValue = new IndividualValueImpl();
        this.effortValue = new EffortValueImpl(0, 0, 0, 0, 0, 0);
    }

    public Bulbasaur(IndividualValueImpl individualValueImpl, EffortValueImpl effortValue) {
        this.baseStats = new BaseStatsImpl(45, 49, 49, 65, 65, 45);
        this.individualValue = new IndividualValueImpl(individualValueImpl.hitPoint, individualValueImpl.attack, individualValueImpl.block, individualValueImpl.contact, individualValueImpl.defense, individualValueImpl.speed);
        this.effortValue = new EffortValueImpl(effortValue.hitPoint, effortValue.attack, effortValue.block, effortValue.contact, effortValue.defense, effortValue.speed);
    }

    @Override
    public BaseStats baseStats() {
        return null;
    }

    @Override
    public IndividualValue individualValue() {
        return null;
    }

    @Override
    public EffortValue effortValue() {
        return null;
    }
}
