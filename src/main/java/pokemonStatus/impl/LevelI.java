package pokemonStatus.impl;

import pokemonStatus.Level;

public class LevelI implements Level {
    private static final int MIN = 1;
    private static final int MAX = 100;
    private final int val;

    public LevelI(int val) {
        if (val < MIN || val > MAX)
            throw new IllegalArgumentException("レベルは" + MIN + "以上" + MAX + "以下を指定してください。");
        this.val = val;
    }

    public Level add() {
        int incrementedVal = this.val + 1;
        if (incrementedVal > 100) {
            return new LevelI(100);
        } else {
            return new LevelI(incrementedVal);
        }
    }

    public Level add(int value) {
        int incrementedVal = this.val + value;
        if (incrementedVal > 100) {
            return new LevelI(100);
        } else {
            return new LevelI(incrementedVal);
        }
    }

    public int val() {
        return this.val;
    }
}
