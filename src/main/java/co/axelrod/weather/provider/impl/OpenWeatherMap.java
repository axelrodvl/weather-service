package co.axelrod.weather.provider.impl;

import co.axelrod.weather.model.CityWeather;
import co.axelrod.weather.provider.WeatherProviderException;
import co.axelrod.weather.provider.WeatherProvider;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

/**
 * Created by Vadim Axelrod (vadim@axelrod.co) on 19.06.2018.
 */
public class OpenWeatherMap implements WeatherProvider {
    private static final String API_KEY = PUT_API_KEY_FROM_OPENWEATHERMAP_HERE;
    private static final String WEATHER_API_URL = "http://api.openweathermap.org/data/2.5";

    public CityWeather getWeather(String city) throws WeatherProviderException {
        JsonObject serviceResponse = callService(city);
        return parseResponse(serviceResponse);
    }

    private JsonObject callService(String city) throws WeatherProviderException {
        WebTarget target = ClientBuilder.newBuilder().newClient()
                .target(WEATHER_API_URL)
                .path("weather")
                .queryParam("q", city)
                .queryParam("units", "metric")
                .queryParam("appid", API_KEY);

        Invocation.Builder builder = target.request();
        Response response = builder.get();

        if(response.getStatus() != 200) {
            throw new WeatherProviderException("Unable to get weather from provider "
                    + this.getClass().getSimpleName()
                    + " for city: " + city);
        }

        String json = builder.get().readEntity(String.class);
        return (new JsonParser()).parse(json).getAsJsonObject();
    }

    private CityWeather parseResponse(JsonObject json) throws WeatherProviderException {
        try {
            String country = json.get("sys").getAsJsonObject().get("country").getAsString();
            String city = json.get("name").getAsString();
            Long temperature = json.get("main").getAsJsonObject().get("temp").getAsLong();

            return new CityWeather(country, city, temperature);
        } catch (Exception ex) {
            throw new WeatherProviderException("Unable to parse response of provider "
                    + this.getClass().getSimpleName());
        }
    }
}
