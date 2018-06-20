# Simple weather service with caching, using OpenWeatherMap as provider

## Libraries:
- Apache CXF - REST client
- Google Gson - JSON parsing
- Ehcache - caching

### Prerequisites
- Create free account at `https://openweathermap.org/api` and retrieve API key
- Save API key in API_KEY constant inside `src/main/java/co/axelrod/weather/provider/impl/OpenWeatherMap.java`

### Using
- Check provided WeatherServiceTest
- Run `mvn test`