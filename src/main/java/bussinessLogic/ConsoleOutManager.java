package bussinessLogic;

import move.Move;
import pokemon.PokemonInfo;

import java.util.List;

public class ConsoleOutManager {
    // コンソール出力を管理するクラス

    public static void showAllParameters(PokemonInfo target) {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.print("図鑑No:" + target.getPokeDexNo() + " ");
        System.out.print("名前:" + target.getPokeName() + " ");
        System.out.println("分類:" + target.getSpecies());
        System.out.println("タイプ1: " + target.getType1().value() + " タイプ2: " + target.getType2().value());
        System.out.print("レベル: " + target.getLevel().value());
        System.out.print("  次のレベルまで: " + target.getExperience().nextRequireExperience(target) + " exp.");
        System.out.println("  総経験値: " + target.getExperience().totalExperience() + " exp.");

        //  System.out.println("  経験値タイプ: " + target.experienceType());
        System.out.println("性別: " + target.getGender().value());
        System.out.println("性格: " + target.getNature().value());
        System.out.print("覚えている技: ");
        for (Move move : target.getHaveMove()) {
            System.out.print(move.getName() + "  ");
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
        System.out.print("HP: " + target.getCurrentHitPoint().value() + "/" + target.getRealValHitPoint() + " ");
        showProgressBar(target);
        System.out.println("");
        System.out.println("攻撃 " + target.getRealValAttack());
        System.out.println("防御 " + target.getRealValBlock());
        System.out.println("特攻 " + target.getRealValContact());
        System.out.println("特防 " + target.getRealValDefense());
        System.out.println("素早 " + target.getRealValSpeed());
    }

    public static void showMoveDetail(List<Move> moves) {
        System.out.println("【 技詳細表示 】");
        for (Move move : moves) {
            System.out.println("技　名: " + move.getName());
            System.out.println("タイプ: " + move.getMoveType().value());
            System.out.println("分　類: " + move.getMoveSpecies().value());
            System.out.println("威　力: " + move.getDamage());
            System.out.println("命中率: " + move.getHitRate());
            System.out.println("");
        }
    }

    public static void showProgressBar(PokemonInfo target) {
        // 残りHPのパーセントを出す
        double i = ((double)target.getCurrentHitPoint().value()) / ((double)target.getRealValHitPoint()) * 20;
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
        System.out.println(enemyPokemon.getPokeName() + " " + enemyPokemon.getGender().value() + "    Lv." + enemyPokemon.getLevel().value());
        System.out.print("HP");
        showProgressBar(enemyPokemon);
        System.out.println("           ■");
        System.out.print("   " + enemyPokemon.getCurrentHitPoint().value() + " / " + enemyPokemon.getRealValHitPoint());
        System.out.println("");
        System.out.println("");
        System.out.println("");
        // ↑相手側　↓こっち側
        System.out.print("                        ");
        System.out.println(myPokemon.getPokeName() + " " + myPokemon.getGender().value() + "    Lv." + myPokemon.getLevel().value());
        System.out.print("            ■           HP");
        showProgressBar(myPokemon);
        System.out.println("");
        System.out.println("                           " + myPokemon.getCurrentHitPoint().value() + " / " + myPokemon.getRealValHitPoint());
        System.out.println("");
        System.out.println("-------------------------------------------------");
    }

    public static void showPokemonInfoWithClear(PokemonInfo myPokemon, PokemonInfo enemyPokemon) {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println(enemyPokemon.getPokeName() + " " + enemyPokemon.getGender().value() + "    Lv." + enemyPokemon.getLevel().value());
        System.out.print("HP");
        showProgressBar(enemyPokemon);
        System.out.println("           ■");
        System.out.print("   " + enemyPokemon.getCurrentHitPoint().value() + " / " + enemyPokemon.getRealValHitPoint());
        System.out.println("");
        System.out.println("");
        System.out.println("");
        // ↑相手側　↓こっち側
        System.out.print("                        ");
        System.out.println(myPokemon.getPokeName() + " " + myPokemon.getGender().value() + "    Lv." + myPokemon.getLevel().value());
        System.out.print("            ■           HP");
        showProgressBar(myPokemon);
        System.out.println("");
        System.out.println("                           " + myPokemon.getCurrentHitPoint().value() + " / " + myPokemon.getRealValHitPoint());
        System.out.println("");
        System.out.println("-------------------------------------------------");
    }

    public static void showParametersInBattle(PokemonInfo target) {
        System.out.println("名前:" + target.getPokeName() + " ");
        System.out.println("タイプ1: " + target.getType1().value() + " タイプ2: " + target.getType2().value());
        System.out.println("レベル: " + target.getLevel().value());
        System.out.print("HP: " + target.getCurrentHitPoint().value() + "/" + target.getRealValHitPoint() + " ");
        showProgressBar(target);
        System.out.println("");
        System.out.println("攻撃 " + target.getRealValAttack());
        System.out.println("防御 " + target.getRealValBlock());
        System.out.println("特攻 " + target.getRealValContact());
        System.out.println("特防 " + target.getRealValDefense());
        System.out.println("素早 " + target.getRealValSpeed());
        System.out.println("");

        System.out.print("攻撃ランク ");
        int i = 0;
        for(;i < Math.abs(target.getStatusRank().attack()); i++) {
            if (target.getStatusRank().attack() > 0) {
                System.out.print("△");
            } else {
                System.out.print("▼");
            }
        }
        while (i < 6) {
            System.out.print(".");
            i++;
        }
        System.out.println("");

        i = 0;
        System.out.print("防御ランク ");
        for(;i < Math.abs(target.getStatusRank().block()); i++) {
            if (target.getStatusRank().block() > 0) {
                System.out.print("△");
            } else {
                System.out.print("▼");
            }
        }
        while (i < 6) {
            System.out.print(".");
            i++;
        }
        System.out.println("");

        i = 0;
        System.out.print("特攻ランク ");
        for(;i < Math.abs(target.getStatusRank().contact()); i++) {
            if (target.getStatusRank().contact() > 0) {
                System.out.print("△");
            } else {
                System.out.print("▼");
            }
        }
        while (i < 6) {
            System.out.print(".");
            i++;
        }
        System.out.println("");

        i = 0;
        System.out.print("特防ランク ");
        for(;i < Math.abs(target.getStatusRank().defense()); i++) {
            if (target.getStatusRank().defense() > 0) {
                System.out.print("△");
            } else {
                System.out.print("▼");
            }
        }
        while (i < 6) {
            System.out.print(".");
            i++;
        }
        System.out.println("");

        i = 0;
        System.out.print("素早ランク ");
        for(;i < Math.abs(target.getStatusRank().speed()); i++) {
            if (target.getStatusRank().speed() > 0) {
                System.out.print("△");
            } else {
                System.out.print("▼");
            }
        }
        while (i < 6) {
            System.out.print(".");
            i++;
        }
        System.out.println("");
        System.out.println("");
    }

    public static void showMessageParChar(String message) throws InterruptedException {
        for(int i = 0; i < message.length(); i++) {
            System.out.print(message.charAt(i));
            Thread.sleep(15);
        }
        System.out.println("");
        Thread.sleep(200);
    }
}
