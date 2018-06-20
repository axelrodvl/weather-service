package co.axelrod.weather.provider;

import co.axelrod.weather.model.CityWeather;

/**
 * Created by Vadim Axelrod (vadim@axelrod.co) on 20.06.2018.
 */
public interface WeatherProvider {
    CityWeather getWeather(String city) throws WeatherProviderException;
}
