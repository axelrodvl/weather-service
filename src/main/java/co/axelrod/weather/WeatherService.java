package co.axelrod.weather;

import co.axelrod.weather.model.CityWeather;
import co.axelrod.weather.provider.WeatherProviderException;
import co.axelrod.weather.provider.WeatherProvider;
import co.axelrod.weather.provider.impl.OpenWeatherMap;
import lombok.extern.slf4j.Slf4j;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

import java.time.Duration;

/**
 * Created by Vadim Axelrod (vadim@axelrod.co) on 19.06.2018.
 */
@Slf4j
public class WeatherService {
    private static final String CACHE_NAME = "weatherCache";
    private static final Integer CACHE_SIZE = 1000;

    WeatherProvider provider;

    CacheManager cacheManager;
    Cache<String, CityWeather> cache;

    public WeatherService() {
        this(new OpenWeatherMap());
    }

    public WeatherService(WeatherProvider provider) {
        this.provider = provider;
        initCache();
    }

    private void initCache() {
        this.cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                .withCache(CACHE_NAME,
                        CacheConfigurationBuilder
                                .newCacheConfigurationBuilder(String.class, CityWeather.class,
                                        ResourcePoolsBuilder.heap(CACHE_SIZE).build()
                                )
                                .withExpiry(ExpiryPolicyBuilder.timeToIdleExpiration(Duration.ofSeconds(120)))
                                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(1000)))
                )
                .build(true);

        cache = cacheManager.getCache(CACHE_NAME, String.class, CityWeather.class);
    }

    public CityWeather getWeather(String city) {
        if (cache.containsKey(city)) {
            log.debug("Cache hit for entry: " + city);
            return cache.get(city);
        } else {
            try {
                CityWeather weather = provider.getWeather(city);
                cache.put(city, weather);
                log.debug("Cache miss for entry: " + city + ", adding value to cache.");
                return weather;
            } catch (WeatherProviderException ex) {
                log.error("Unable to get weather for city: " + city);
                return null;
            }
        }
    }

    public void dispose() {
        cacheManager.close();
    }
}
