package field;

import Enum.MoveSpecies;
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
    public static LightScreen enableLightScreen(LightScreen currentLightScreen) {
        return new LightScreen(currentLightScreen);
    }

    private LightScreen(LightScreen currentLightScreen) {
        if (currentLightScreen.val()) {
            //すでに壁が張られている
            showMessageParChar("ひかりのかべはすではられている!");
            this.val = currentLightScreen.val;
            this.elapsedTurn = currentLightScreen.elapsedTurn;
            this.countRecovery = currentLightScreen.countRecovery;
        } else {
            //あらたに壁を張る
            showMessageParChar("ひかりのかべがはられた!");
            this.val = true;
            this.elapsedTurn = 0;
            this.countRecovery = 5;
        }
    }

    public double dmgRateByLightScreen(MoveSpecies species) {
        if (species == MoveSpecies.SPECIAL) {
            if (this.val) {
                return 0.5;
            }
        }
        return 1.0;
    }

    public LightScreen elapsingTurn() {
        if (val) {
            if (this.countRecovery <= this.elapsedTurn + 1) {
                showMessageParChar("ひかりのかべのこうかがきれた!");
                return initLightScreen();
            }
            return new LightScreen(this.val, this.elapsedTurn + 1, this.countRecovery);
        } else {
            return new LightScreen(this.val, this.elapsedTurn, this.countRecovery);
        }
    }
}
