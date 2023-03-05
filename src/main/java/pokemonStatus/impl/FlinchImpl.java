package pokemonStatus.impl;

import pokemonStatus.Flinch;

import static bussinessLogic.ConsoleOutManager.showMessageParChar;

public class FlinchImpl implements Flinch {
    // 怯んでいるならtrue,怯んでいないならfalse
    private boolean value;

    // 初期化のときに使う
    public static Flinch initializeFlinch() {
        return new FlinchImpl();
    }

    private FlinchImpl() {
        this.value = false;
    }

    public FlinchImpl(boolean value) {
        this.value = value;
    }


    public boolean canMove(String pokeName) throws InterruptedException {
        if (this.value) {
            showMessageParChar(pokeName + "はひるんでうごけない!");
            return false;
        }
        return true;
    }
}
