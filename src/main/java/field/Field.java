package field;

public interface Field {
    Weather getWeather();
    int getElapsedTurn();
    int getCountForRecovery();
    Field elapseTurn() throws InterruptedException;
}
