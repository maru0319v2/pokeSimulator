package impl;

import bussinessLogic.Level;

public class LevelImpl implements Level {

    private static final int MIN = 1;
    private static final int MAX = 100;
    private final int value;

    public LevelImpl(int value) {
        if(value < MIN || value > MAX) throw new IllegalArgumentException("レベルは" + MIN + "以上" + MAX + "以下を指定してください。");
        this.value = value;
    }

    public Level add() {
        int incrementedValue = this.value + 1;
        if(incrementedValue > 100) {
            return new LevelImpl(100);
        } else {
            return new LevelImpl(incrementedValue);
        }
    }

    public Level add(int value) {
        int incrementedValue = this.value + value;
        if(incrementedValue > 100) {
            return new LevelImpl(100);
        } else {
            return new LevelImpl(incrementedValue);
        }
    }

    public int value() {
        return this.value;
    }
}
