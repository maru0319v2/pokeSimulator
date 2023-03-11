package field;

public interface Field {
    Weather weather();

    Field withWeather(Weather weather);
}
