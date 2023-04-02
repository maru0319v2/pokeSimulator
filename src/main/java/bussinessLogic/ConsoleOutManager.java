package bussinessLogic;

import field.WeatherEnum;
import move.Move;
import pokemon.PokeInfo;
import statusAilment.AilmentEnum;

import java.util.List;

public class ConsoleOutManager {
    // コンソール出力を管理するクラス

    public static void showAllParameters(PokeInfo target) {
        System.out.println("");
        System.out.print(target.basePrm().pName());
        System.out.print(" " + target.gender().val());
        System.out.print(" Lv:" + target.level().val());
        System.out.print(" " + target.nature().val());
        System.out.println(" " + target.item().val());
        for (Move move : target.haveMove()) {
            System.out.print(move.baseMPrm().mvName() + "  ");
        }
        System.out.println("");
        System.out.print(" H　   P " + target.realHP());
        System.out.println("");
        System.out.println("こうげき " + target.realAtk());
        System.out.println("ぼうぎょ " + target.realBlk());
        System.out.println("とくこう " + target.realCnt());
        System.out.println("とくぼう " + target.realDfc());
        System.out.println("すばやさ " + target.realSpd());
    }

    public static void showMoveDetail(List<Move> moves) {
        System.out.println("【 技詳細表示 】");
        for (Move move : moves) {
            System.out.println("技　名: " + move.baseMPrm().mvName());
            System.out.println("タイプ: " + move.baseMPrm().moveType().val());
            System.out.println("分　類: " + move.baseMPrm().moveSpecies().val());
            if (null == move.baseMPrm().damage()) {
                System.out.println("威　力: -");
            } else {
                System.out.println("威　力: " + move.baseMPrm().damage());
            }
            if (null == move.baseMPrm().hitRate()) {
                System.out.println("命中率: -");
            } else {
                System.out.println("命中率: " + move.baseMPrm().hitRate());
            }
            System.out.println("");
        }
    }

    public static void showProgressBar(PokeInfo target) {
        // 残りHPのパーセントを出す
        double i = ((double) target.currentHP().val()) / ((double) target.realHP()) * 20;
        System.out.print("[");
        int j;
        for (j = 0; i > j; j++) {
            System.out.print("=");
        }
        while (j < 20) {
            System.out.print(" ");
            j++;
        }
        System.out.print("]");
    }

    public static void showPokemonInfo(PokeInfo myPokemon, PokeInfo enemyPokemon) {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.print(enemyPokemon.basePrm().pName() + " " + enemyPokemon.gender().val() + "    Lv." + enemyPokemon.level().val());
        if (enemyPokemon.ailment().val() == AilmentEnum.FINE) {
            System.out.println("");
        } else {
            System.out.println(" " + enemyPokemon.ailment().val().val());
        }
        System.out.print("HP");
        showProgressBar(enemyPokemon);
        System.out.println("           ■");
        System.out.print("   " + enemyPokemon.currentHP().val() + " / " + enemyPokemon.realHP());
        System.out.println("");
        System.out.println("");
        System.out.println("");
        // ↑相手側　↓こっち側
        System.out.print("                        ");
        System.out.print(myPokemon.basePrm().pName() + " " + myPokemon.gender().val() + "    Lv." + myPokemon.level().val());
        if (myPokemon.ailment().val() == AilmentEnum.FINE) {
            System.out.println("");
        } else {
            System.out.println(" " + myPokemon.ailment().val().val());
        }
        System.out.print("            ■           HP");
        showProgressBar(myPokemon);
        System.out.println("");
        System.out.println("                           " + myPokemon.currentHP().val() + " / " + myPokemon.realHP());
        System.out.println("");
        System.out.println("-------------------------------------------------");
    }

    public static void showParametersInBattle(PokeInfo target) {
        System.out.println("名前:" + target.basePrm().pName() + " ");
        System.out.println("タイプ1: " + target.basePrm().type1().val() + " タイプ2: " + target.basePrm().type2().val());
        System.out.println("レベル: " + target.level().val());
        System.out.print("HP: " + target.currentHP().val() + "/" + target.realHP() + " ");
        showProgressBar(target);
        System.out.println("");
        System.out.println("攻撃 " + target.realAtk());
        System.out.println("防御 " + target.realBlk());
        System.out.println("特攻 " + target.realCnt());
        System.out.println("特防 " + target.realDfc());
        System.out.println("素早 " + target.realSpd());
        System.out.println("");

        System.out.print("攻撃ランク ");
        int i = 0;
        for (; i < Math.abs(target.statusRank().attack()); i++) {
            if (target.statusRank().attack() > 0) {
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
        for (; i < Math.abs(target.statusRank().block()); i++) {
            if (target.statusRank().block() > 0) {
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
        for (; i < Math.abs(target.statusRank().contact()); i++) {
            if (target.statusRank().contact() > 0) {
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
        for (; i < Math.abs(target.statusRank().defence()); i++) {
            if (target.statusRank().defence() > 0) {
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
        for (; i < Math.abs(target.statusRank().speed()); i++) {
            if (target.statusRank().speed() > 0) {
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
        System.out.print("命中ランク ");
        for (; i < Math.abs(target.statusRank().hitRate()); i++) {
            if (target.statusRank().hitRate() > 0) {
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
        System.out.print("回避ランク ");
        for (; i < Math.abs(target.statusRank().avoidRate()); i++) {
            if (target.statusRank().avoidRate() > 0) {
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

    public static void showAlreadyAilmentMessage(PokeInfo target) {
        switch (target.ailment().val()) {
            case PARALYSIS -> showMessageParChar(target.basePrm().pName() + "はすでにしびれている!");
            case POISON -> showMessageParChar(target.basePrm().pName() + "はすでにどくをあびている!");
            case BAD_POISON -> showMessageParChar(target.basePrm().pName() + "はすでにもうどくをあびている!");
            case BURN -> showMessageParChar(target.basePrm().pName() + "はすでにやけどをおっている!");
            case FREEZE -> showMessageParChar(target.basePrm().pName() + "はすでにこおりついている!");
            case SLEEP -> showMessageParChar(target.basePrm().pName() + "はすでにねむってしまっている!");
        }
    }

    public static void showChangeAilmentMessage(PokeInfo target, AilmentEnum value) {
        switch (value) {
            case PARALYSIS -> showMessageParChar(target.basePrm().pName() + "はしびれてしまった!");
            case POISON -> showMessageParChar(target.basePrm().pName() + "はどくをあびた!");
            case BAD_POISON -> showMessageParChar(target.basePrm().pName() + "はもうどくをあびた!");
            case BURN -> showMessageParChar(target.basePrm().pName() + "はやけどをおった!");
            case FREEZE -> showMessageParChar(target.basePrm().pName() + "はこおりついてしまった!");
            case SLEEP -> showMessageParChar(target.basePrm().pName() + "はねむってしまった!");
            case FINE -> showMessageParChar(target.basePrm().pName() + "はけんこうになった!");
            case FAINTING -> showMessageParChar(target.basePrm().pName() + "はたおれてしまった!");
        }
    }

    public static void showChangeWeather(WeatherEnum value) {
        switch (value) {
            case DROUGHT -> showMessageParChar("ひざしがつよくなった!");
            case RAIN -> showMessageParChar("あめがふりはじめた!");
            case SANDSTORM -> showMessageParChar("すなあらしがふきはじめた!");
            case HAIL -> showMessageParChar("あられがふりはじめた!");
        }
    }

    public static void showUndoWeather(WeatherEnum value) {
        switch (value) {
            case DROUGHT -> showMessageParChar("ひざしがよわまった!");
            case RAIN -> showMessageParChar("あめがやんだ!");
            case SANDSTORM -> showMessageParChar("すなあらしがおさまった!");
            case HAIL -> showMessageParChar("あられがやんだ!");
        }
    }

    public static void showKeepWeather(WeatherEnum value) {
        switch (value) {
            case DROUGHT -> showMessageParChar("ひざしがつよい");
            case RAIN -> showMessageParChar("あめがふりつづいている");
            case SANDSTORM -> showMessageParChar("すなあらしがふいている");
            case HAIL -> showMessageParChar("あられがふっている");
        }
    }

    public static void showChangeStatusRank(String name, List<Integer> beforeSRList, List<Integer> afterSRList, List<Integer> changeSRList) {
        List<String> statusMsg = List.of("こうげき", "ぼうぎょ", "とくこう", "とくぼう", "すばやさ", "めいちゅうりつ", "かいひりつ");

        for (int i = 0; i < 7; i++) {
            if (changeSRList.get(i) >= 1) {
                if (afterSRList.get(i) - beforeSRList.get(i) == 0) {
                    showMessageParChar(name + "の" + statusMsg.get(i) + "はもうあがらない!");
                }
            }
            if (changeSRList.get(i) <= -1) {
                if (afterSRList.get(i) - beforeSRList.get(i) == 0) {
                    showMessageParChar(name + "の" + statusMsg.get(i) + "はもうさがらない!");
                }
            }
            switch (afterSRList.get(i) - beforeSRList.get(i)) {
                case 1 -> showMessageParChar(name + "の" + statusMsg.get(i) + "があがった!");
                case 2 -> showMessageParChar(name + "の" + statusMsg.get(i) + "がぐーんとあがった!");
                case 3 -> showMessageParChar(name + "の" + statusMsg.get(i) + "がぐぐーんとあがった!");
                case 4, 5, 6, 7, 8, 9, 10, 11, 12 -> showMessageParChar(name + "の" + statusMsg.get(i) + "がさいだいまであがった!");
                case -1 -> showMessageParChar(name + "の" + statusMsg.get(i) + "がさがった!");
                case -2, -3 -> showMessageParChar(name + "の" + statusMsg.get(i) + "ががくっとさがった!");
                default -> {
                }
            }
        }
    }

    public static void showMessageParChar(String message) {
        try {
            for (int i = 0; i < message.length(); i++) {
                System.out.print(message.charAt(i));
                Thread.sleep(20);
            }
            System.out.println("");
            Thread.sleep(150);
        } catch (InterruptedException ignored) {
        }
    }
}
