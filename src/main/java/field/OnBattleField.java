package field;

public class OnBattleField {
    private final Field atkField;
    private final Field dfcField;
    private final Weather weather;

    public Field atkField() {
        return this.atkField;
    }

    public Field dfcField() {
        return this.dfcField;
    }

    public OnBattleField(Field atkField, Field dfcField, Weather weather) {
        this.atkField = atkField;
        this.dfcField = dfcField;
        this.weather = weather;
    }

    public boolean isBothFine() {
        return atkField.poke().currentHP().isAlive() && dfcField.poke().currentHP().isAlive();
    }

    public boolean isDeadEither() {
        return atkField.poke().currentHP().isDead() || dfcField.poke().currentHP().isDead();
    }

    public Weather weather() {
        return this.weather;
    }
}
