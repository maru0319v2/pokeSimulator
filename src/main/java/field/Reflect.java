package field;

import Enum.MoveSpecies;
import lombok.AllArgsConstructor;

import static bussinessLogic.ConsoleOutManager.showMessageParChar;

@AllArgsConstructor
public class Reflect {
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

    // 初期化したい場合
    public static Reflect initReflect() {
        return new Reflect();
    }

    private Reflect() {
        this.val = false;
        this.elapsedTurn = 0;
        this.countRecovery = 0;
    }

    // リフレクター状態にする場合
    public static Reflect enableReflect(Reflect currentReflect) throws InterruptedException {
        return new Reflect(currentReflect);
    }

    private Reflect(Reflect currentReflect) throws InterruptedException {
        if (currentReflect.val()) {
            // すでに壁が張られている
            showMessageParChar("リフレクターはすではられている!");
            this.val = currentReflect.val;
            this.elapsedTurn = currentReflect.elapsedTurn;
            this.countRecovery = currentReflect.countRecovery;
        } else {
            // あらたに壁を張る
            showMessageParChar("リフレクターがあらわれた!");
            this.val = true;
            this.elapsedTurn = 0;
            this.countRecovery = 5;
        }
    }

    public double dmgRateByReflect(MoveSpecies species) {
        if (species == MoveSpecies.PHYSICAL) {
            if (this.val) {
                return 0.5;
            }
        }
        return 1.0;
    }

    public Reflect elapsingTurn() throws InterruptedException {
        if (val) {
            if (this.countRecovery <= this.elapsedTurn + 1) {
                showMessageParChar("リフレクターの効果がきれた!");
                return initReflect();
            }
            return new Reflect(this.val, this.elapsedTurn + 1, this.countRecovery);
        } else {
            return new Reflect(this.val, this.elapsedTurn, this.countRecovery);
        }
    }
}
