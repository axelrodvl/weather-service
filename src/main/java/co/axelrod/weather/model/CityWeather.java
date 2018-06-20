package co.axelrod.weather.model;

import java.io.Serializable;

/**
 * Created by Vadim Axelrod (vadim@axelrod.co) on 20.06.2018.
 */
public class CityWeather implements Serializable {
    private String country;
    private String city;
    private Long temperature;

    public CityWeather(String country, String city, Long temperature) {
        this.country = country;
        this.city = city;
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return city + ", " + country + " - " + temperature + "°C";
    }
}
