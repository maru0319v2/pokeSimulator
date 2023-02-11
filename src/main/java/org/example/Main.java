package main.java.org.example;

import main.java.org.example.pokemon.Bulbasaur;

public class Main {
    public static void main(String[] args) {

        Bulbasaur bulbasaur = new Bulbasaur();

        System.out.println(bulbasaur.pokeName);

        System.out.println("種族値");
        System.out.println("体力 " + bulbasaur.baseStats.hitPoint);
        System.out.println("攻撃 " + bulbasaur.baseStats.attack);
        System.out.println("防御 " + bulbasaur.baseStats.block);
        System.out.println("特攻 " + bulbasaur.baseStats.contact);
        System.out.println("特防 " + bulbasaur.baseStats.defense);
        System.out.println("素早 " + bulbasaur.baseStats.speed);
        System.out.println("合計 " + (bulbasaur.baseStats.hitPoint + bulbasaur.baseStats.attack + bulbasaur.baseStats.block + bulbasaur.baseStats.contact + bulbasaur.baseStats.defense + bulbasaur.baseStats.speed));

        System.out.println();

        System.out.println("個体値");
        System.out.println("体力 " + bulbasaur.individualValue.hitPoint);
        System.out.println("攻撃 " + bulbasaur.individualValue.attack);
        System.out.println("防御 " + bulbasaur.individualValue.block);
        System.out.println("特攻 " + bulbasaur.individualValue.contact);
        System.out.println("特防 " + bulbasaur.individualValue.defense);
        System.out.println("素早 " + bulbasaur.individualValue.speed);

        System.out.println();

        System.out.println("努力値");
        System.out.println("体力 " + bulbasaur.effortValue.hitPoint);
        System.out.println("攻撃 " + bulbasaur.effortValue.attack);
        System.out.println("防御 " + bulbasaur.effortValue.block);
        System.out.println("特攻 " + bulbasaur.effortValue.contact);
        System.out.println("特防 " + bulbasaur.effortValue.defense);
        System.out.println("素早 " + bulbasaur.effortValue.speed);

        System.out.println("努力値加算");
        System.out.println();
        Bulbasaur bulbasaur1 = new Bulbasaur(bulbasaur.individualValue, bulbasaur.effortValue.add(2,3,6,1,3,3));

        System.out.println("種族値");
        System.out.println("体力 " + bulbasaur1.baseStats.hitPoint);
        System.out.println("攻撃 " + bulbasaur1.baseStats.attack);
        System.out.println("防御 " + bulbasaur1.baseStats.block);
        System.out.println("特攻 " + bulbasaur1.baseStats.contact);
        System.out.println("特防 " + bulbasaur1.baseStats.defense);
        System.out.println("素早 " + bulbasaur1.baseStats.speed);
        System.out.println("合計 " + (bulbasaur1.baseStats.hitPoint + bulbasaur1.baseStats.attack + bulbasaur1.baseStats.block + bulbasaur1.baseStats.contact + bulbasaur1.baseStats.defense + bulbasaur1.baseStats.speed));

        System.out.println();

        System.out.println("個体値");
        System.out.println("体力 " + bulbasaur1.individualValue.hitPoint);
        System.out.println("攻撃 " + bulbasaur1.individualValue.attack);
        System.out.println("防御 " + bulbasaur1.individualValue.block);
        System.out.println("特攻 " + bulbasaur1.individualValue.contact);
        System.out.println("特防 " + bulbasaur1.individualValue.defense);
        System.out.println("素早 " + bulbasaur1.individualValue.speed);

        System.out.println();

        System.out.println("努力値");
        System.out.println("体力 " + bulbasaur1.effortValue.hitPoint);
        System.out.println("攻撃 " + bulbasaur1.effortValue.attack);
        System.out.println("防御 " + bulbasaur1.effortValue.block);
        System.out.println("特攻 " + bulbasaur1.effortValue.contact);
        System.out.println("特防 " + bulbasaur1.effortValue.defense);
        System.out.println("素早 " + bulbasaur1.effortValue.speed);

    }
}