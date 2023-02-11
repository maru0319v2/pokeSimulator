package main.java.org.example;

import main.java.org.example.move.Tackle;
import main.java.org.example.pokemon.Bulbasaur;

public class Main {
    public static void main(String[] args) {

        Bulbasaur bulbasaur = new Bulbasaur();
        showAllParameters(bulbasaur);

        System.out.println();
        System.out.println();
        System.out.println("【 努力値,レベル加算 】");
        Bulbasaur bulbasaur1 = new Bulbasaur(
                bulbasaur.gender(),
                bulbasaur.nature(),
                bulbasaur.individualValue(),
                bulbasaur.effortValue().add(20,30,60,10,30,30),
                bulbasaur.level().add(49)
        );
        showAllParameters(bulbasaur1);

        System.out.println();
        System.out.println("【 技詳細表示 】");
        showMoveDetail(new Tackle());
    }

    private static void showAllParameters(PokemonInfo target) {
        System.out.print("図鑑No:" + target.pokeDexNo() + " ");
        System.out.print("名前:" + target.pokeName() + " ");
        System.out.println("分類:" + target.species());
        System.out.println("タイプ1: " + target.pokemonType1() + " タイプ2: " + target.pokemonType2());
        System.out.println("レベル: " + target.level().value());
        System.out.println("性別: " + target.gender().value());
        System.out.println("性格: " + target.nature().value());
        System.out.println("覚えている技: " + target.haveMove().value());
        System.out.print("種族値:");
        System.out.print(" 体力 " + target.baseStats().hitPoint());
        System.out.print(" 攻撃 " + target.baseStats().attack());
        System.out.print(" 防御 " + target.baseStats().block());
        System.out.print(" 特攻 " + target.baseStats().contact());
        System.out.print(" 特防 " + target.baseStats().defense());
        System.out.print(" 素早 " + target.baseStats().speed());
        System.out.println(" 合計 " + (target.baseStats().hitPoint() +
                                    target.baseStats().attack() +
                                    target.baseStats().block() +
                                    target.baseStats().contact() +
                                    target.baseStats().defense() +
                                    target.baseStats().speed()));
        System.out.print("個体値:");
        System.out.print(" 体力 " + target.individualValue().hitPoint());
        System.out.print(" 攻撃 " + target.individualValue().attack());
        System.out.print(" 防御 " + target.individualValue().block());
        System.out.print(" 特攻 " + target.individualValue().contact());
        System.out.print(" 特防 " + target.individualValue().defense());
        System.out.println(" 素早 " + target.individualValue().speed());
        System.out.print("努力値:");
        System.out.print(" 体力 " + target.effortValue().hitPoint());
        System.out.print(" 攻撃 " + target.effortValue().attack());
        System.out.print(" 防御 " + target.effortValue().block());
        System.out.print(" 特攻 " + target.effortValue().contact());
        System.out.print(" 特防 " + target.effortValue().defense());
        System.out.println(" 素早 " + target.effortValue().speed());
        System.out.print("実数値:");
        System.out.print(" 体力 " + target.realValHitPoint());
        System.out.print(" 攻撃 " + target.realValAttack());
        System.out.print(" 防御 " + target.realValBlock());
        System.out.print(" 特攻 " + target.realValContact());
        System.out.print(" 特防 " + target.realValDefense());
        System.out.print(" 素早 " + target.realValSpeed());
    }

    private static void showMoveDetail(Move target) {
        System.out.println("技名:" + target.value());
        System.out.println("タイプ:" + target.moveType());
        System.out.println("分類:" + target.moveSpecies());
        System.out.println("威力:" + target.damage());
        System.out.println("命中率:" + target.hitRate());
    }
}