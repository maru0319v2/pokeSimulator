package pokemonStatus.impl;

import pokemonStatus.Flinch;

import static bussinessLogic.ConsoleOutManager.showMessageParChar;

public class FlinchI implements Flinch {
    // 怯んでいるならtrue,怯んでいないならfalse
    private final boolean val;

    @Override
    public boolean val() {
        return this.val;
    }

    // 初期化のときに使う
    public static Flinch init() {
        return new FlinchI();
    }

    private FlinchI() {
        this.val = false;
    }

    public FlinchI(boolean value) {
        this.val = value;
    }

    public boolean canMove(String pokeName) throws InterruptedException {
        if (this.val) {
            showMessageParChar(pokeName + "はひるんでうごけない!");
            return false;
        }
        return true;
    }
}
