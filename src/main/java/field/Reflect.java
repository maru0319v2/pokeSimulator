package field;

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
    public static Reflect enableReflect(Field field) throws InterruptedException {
        return new Reflect(field);
    }

    private Reflect(Field field) throws InterruptedException {
        if (field.reflect().val()) {
            // すでに壁が張られている
            showMessageParChar("リフレクターはすではられている!");
            this.val = field.reflect().val;
            this.elapsedTurn = field.reflect().elapsedTurn;
            this.countRecovery = field.reflect().countRecovery;
        } else {
            // あらたに壁を張る
            showMessageParChar("リフレクターがあらわれた!");
            this.val = true;
            this.elapsedTurn = 0;
            this.countRecovery = 5;
        }
    }
}
