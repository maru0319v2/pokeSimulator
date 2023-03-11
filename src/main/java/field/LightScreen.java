package field;

import lombok.AllArgsConstructor;

import static bussinessLogic.ConsoleOutManager.showMessageParChar;

@AllArgsConstructor
public class LightScreen {
    private final boolean val;
    private final int elapsedTurn;
    private final int countRecovery;

    public boolean val() {
        return this.val;
    }

    public int elapsedTurn() {
        return this.elapsedTurn;
    }

    public int countRecovery() {
        return this.countRecovery;
    }

    //初期化したい場合
    public static LightScreen initLightScreen() {
        return new LightScreen();
    }

    private LightScreen() {
        this.val = false;
        this.elapsedTurn = 0;
        this.countRecovery = 0;
    }

    //ひかりのかべ状態にする場合
    public static LightScreen enableLightScreen(Field field) throws InterruptedException {
        return new LightScreen(field);
    }

    private LightScreen(Field field) throws InterruptedException {
        if (field.lightScreen().val()) {
            //すでに壁が張られている
            showMessageParChar("ひかりのかべはすではられている!");
            this.val = field.lightScreen().val;
            this.elapsedTurn = field.lightScreen().elapsedTurn;
            this.countRecovery = field.lightScreen().countRecovery;
        } else {
            //あらたに壁を張る
            showMessageParChar("ひかりのかべがはられた!");
            this.val = true;
            this.elapsedTurn = 0;
            this.countRecovery = 5;
        }
    }
}
