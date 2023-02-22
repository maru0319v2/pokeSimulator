package bussinessLogic;

public class ConsoleOutManager {
    // コンソール出力を管理するクラス

    public static void showAllParameters(PokemonInfo target) {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("--------------------------------------");
        System.out.print("図鑑No:" + target.pokeDexNo() + " ");
        System.out.print("名前:" + target.pokeName() + " ");
        System.out.println("分類:" + target.species());
        System.out.println("タイプ1: " + target.pokemonType1().value() + " タイプ2: " + target.pokemonType2().value());
        System.out.print("レベル: " + target.level().value());
        System.out.print("  次のレベルまで: " + target.experience().nextRequireExperience(target) + " exp.");
        System.out.println("  総経験値: " + target.experience().totalExperience() + " exp.");

        //  System.out.println("  経験値タイプ: " + target.experienceType());
        System.out.println("性別: " + target.gender().value());
        System.out.println("性格: " + target.nature().value());
        System.out.print("覚えている技: ");
        for (Move move : target.haveMove()) {
            System.out.print(move.name() + "  ");
        }
        System.out.println("");
//        System.out.print("種族値:");
//        System.out.print(" 体力 " + target.baseStats().hitPoint());
//        System.out.print(" 攻撃 " + target.baseStats().attack());
//        System.out.print(" 防御 " + target.baseStats().block());
//        System.out.print(" 特攻 " + target.baseStats().contact());
//        System.out.print(" 特防 " + target.baseStats().defense());
//        System.out.print(" 素早 " + target.baseStats().speed());
//        System.out.println(" 合計 " + (target.baseStats().hitPoint() +
//                                    target.baseStats().attack() +
//                                    target.baseStats().block() +
//                                    target.baseStats().contact() +
//                                    target.baseStats().defense() +
//                                    target.baseStats().speed()));
//        System.out.print("個体値:");
//        System.out.print(" 体力 " + target.individualValue().hitPoint());
//        System.out.print(" 攻撃 " + target.individualValue().attack());
//        System.out.print(" 防御 " + target.individualValue().block());
//        System.out.print(" 特攻 " + target.individualValue().contact());
//        System.out.print(" 特防 " + target.individualValue().defense());
//        System.out.println(" 素早 " + target.individualValue().speed());
//        System.out.print("努力値:");
//        System.out.print(" 体力 " + target.effortValue().hitPoint());
//        System.out.print(" 攻撃 " + target.effortValue().attack());
//        System.out.print(" 防御 " + target.effortValue().block());
//        System.out.print(" 特攻 " + target.effortValue().contact());
//        System.out.print(" 特防 " + target.effortValue().defense());
//        System.out.println(" 素早 " + target.effortValue().speed());
        System.out.print("HP: " + target.currentHitPoint().value() + "/" + target.realValHitPoint() + " ");
        showProgressBar(target);
        System.out.println("");
        System.out.println("攻撃 " + target.realValAttack());
        System.out.println("防御 " + target.realValBlock());
        System.out.println("特攻 " + target.realValContact());
        System.out.println("特防 " + target.realValDefense());
        System.out.println("素早 " + target.realValSpeed());
    }

    public static void showMoveDetail(Move target) {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("--------------------------------------");
        System.out.println("【 技詳細表示 】");
        System.out.println("技　名: " + target.name());
        System.out.println("タイプ: " + target.moveType().value());
        System.out.println("分　類: " + target.moveSpecies().value());
        System.out.println("威　力: " + target.damage());
        System.out.println("命中率: " + target.hitRate());
    }

    public static void showProgressBar(PokemonInfo target) {
        // 残りHPのパーセントを出す
        double i = ((double)target.currentHitPoint().value()) / ((double)target.realValHitPoint()) * 20;
        System.out.print("[");
        int j;
        for(j = 0; i > j; j++) {
            System.out.print("=");
        }
        while (j < 20) {
            System.out.print(" ");
            j++;
        }
        System.out.print("]");
    }

    // バトル中のステータス表示を行う
    public static void showPokemonInfo(PokemonInfo myPokemon, PokemonInfo enemyPokemon) {

        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("-------------------------------------------------");
        System.out.println("");

        System.out.println(enemyPokemon.pokeName() + " " + enemyPokemon.gender().value() + "    Lv." + enemyPokemon.level().value());
        System.out.print("HP");
        ConsoleOutManager.showProgressBar(enemyPokemon);
        System.out.println("           ■");
        System.out.print("   " + enemyPokemon.currentHitPoint().value() + " / " + enemyPokemon.realValHitPoint());
        System.out.println("");
        System.out.println("");
        System.out.println("");
        // ↑相手側　↓こっち側
        System.out.print("                        ");
        System.out.println(myPokemon.pokeName() + " " + myPokemon.gender().value() + "    Lv." + myPokemon.level().value());
        System.out.print("            ■           HP");
        ConsoleOutManager.showProgressBar(myPokemon);
        System.out.println("");
        System.out.println("                           " + myPokemon.currentHitPoint().value() + " / " + myPokemon.realValHitPoint());
        System.out.println("");
        System.out.println("-------------------------------------------------");
    }
}
