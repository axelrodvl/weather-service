package co.axelrod.weather;

import co.axelrod.weather.model.CityWeather;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Vadim Axelrod (vadim@axelrod.co) on 20.06.2018.
 */
public class WeatherServiceTest {
    private static final String DEFAULT_CITY = "Moscow";

    private WeatherService weatherService;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        weatherService = new WeatherService();
    }

    @After
    public void tearDown() {
        weatherService.dispose();
    }

    @Test
    public void allCapitals() {
        long startTime;

        startTime = System.currentTimeMillis();
        System.out.println("Heating cache: ");
        for (String city : capitalsList()) {
            System.out.println(weatherService.getWeather(city));
        }
        System.out.println("Elapsed time to heat cache: " + (System.currentTimeMillis() - startTime));

        startTime = System.currentTimeMillis();
        System.out.println("Cache is ready: ");
        for (String city : capitalsList()) {
            System.out.println(weatherService.getWeather(city));
        }
        System.out.println("Elapsed time to get data from cache: " + (System.currentTimeMillis() - startTime));
    }

    @Test
    public void weather() {
        CityWeather cityWeather = weatherService.getWeather(DEFAULT_CITY);

        assertNotNull(cityWeather);

        String weatherAsString = cityWeather.toString();

        assertEquals("Moscow, RU ", weatherAsString.substring(0, weatherAsString.indexOf("-")));
        assertEquals("°C", weatherAsString.substring(weatherAsString.length() - 2, weatherAsString.length()));
    }

    @Test
    public void invalidCity() {
        assertNull(weatherService.getWeather("INVALID_CITY"));
    }

    @Test
    public void getDataFromCache() {
        assertFalse(weatherService.cache.containsKey(DEFAULT_CITY));
        assertNotNull(weatherService.getWeather("Moscow"));

        assertTrue(weatherService.cache.containsKey(DEFAULT_CITY));
        assertNotNull(weatherService.getWeather("Moscow"));
    }

    private List<String> capitalsList() {
        return Arrays.asList(
                "Abu Dhabi",
                "Abuja",
                "Accra",
                "Adamstown",
                "Addis Ababa",
                "Algiers",
                "Alofi",
                "Amman",
                "Amsterdam",
                "Andorra la Vella",
                "Ankara",
                "Antananarivo",
                "Apia",
                "Ashgabat",
                "Asmara",
                "Astana",
                "Asunción",
                "Athens",
                "Avarua",
                "Baghdad",
                "Baku",
                "Bamako",
                "Bandar Seri Begawan",
                "Bangkok",
                "Bangui",
                "Banjul",
                "Basseterre",
                "Beijing",
                "Beirut",
                "Belgrade",
                "Belmopan",
                "Berlin",
                "Bern",
                "Bishkek",
                "Bissau",
                "Bogotá",
                "Brades",
                "Brasília",
                "Bratislava",
                "Brazzaville",
                "Bridgetown",
                "Brussels",
                "Bucharest",
                "Budapest",
                "Buenos Aires",
                "Bujumbura",
                "Cairo",
                "Canberra",
                "Cape Town",
                "Caracas",
                "Castries",
                "Cayenne",
                "Cebu City",
                "Charlotte Amalie",
                "Chişinău",
                "Cockburn Town",
                "Conakry",
                "Copenhagen",
                "Dakar",
                "Damascus",
                "Dhaka",
                "Dili",
                "Djibouti",
                "Dodoma",
                "Doha",
                "Douglas",
                "Dublin",
                "Dushanbe",
                "East Jerusalem",
                "Episkopi Cantonment",
                "Flying Fish Cove",
                "Freetown",
                "Funafuti",
                "Gaborone",
                "George Town",
                "Georgetown",
                "Gibraltar",
                "Guatemala City",
                "Gustavia",
                "Hagåtña",
                "Hamilton",
                "Hanoi",
                "Harare",
                "Hargeisa",
                "Havana",
                "Helsinki",
                "Honiara",
                "Islamabad",
                "Jakarta",
                "Jamestown",
                "Jerusalem",
                "Juba",
                "Kabul",
                "Kampala",
                "Kathmandu",
                "Khartoum",
                "Kiev",
                "Kigali",
                "King Edward Point",
                "Kingston",
                "Kingstown",
                "Kinshasa",
                "Kuala Lumpur",
                "Kuwait City",
                "Laayoune",
                "Libreville",
                "Lilongwe",
                "Lima",
                "Lisbon",
                "Ljubljana",
                "Lomé",
                "London",
                "Longyearbyen",
                "Luanda",
                "Lusaka",
                "Luxembourg City",
                "Madrid",
                "Majuro",
                "Malabo",
                "Malé",
                "Mamoudzou",
                "Managua",
                "Manama",
                "Manila",
                "Maputo",
                "Mariehamn",
                "Marigot",
                "Maseru",
                "Mata-Utu",
                "Mbabane",
                "Mexico City",
                "Minsk",
                "Mogadishu",
                "Monaco",
                "Monrovia",
                "Montevideo",
                "Moroni",
                "Moscow",
                "Muscat",
                "N'Djamena",
                "Nairobi",
                "Nassau",
                "Nay Pyi Taw",
                "New Delhi",
                "Ngerulmud",
                "Niamey",
                "Nicosia",
                "None",
                "North Nicosia",
                "Nouakchott",
                "Nouméa",
                "Nukuʻalofa",
                "Nuuk",
                "Oranjestad",
                "Oslo",
                "Ottawa",
                "Ouagadougou",
                "Pago Pago",
                "Palikir",
                "Panama City",
                "Papeete",
                "Paramaribo",
                "Paris",
                "Philipsburg",
                "Phnom Penh",
                "Podgorica",
                "Port Louis",
                "Port Moresby",
                "Port of Spain",
                "Port Sudan",
                "Port Vila",
                "Port-au-Prince",
                "Porto-Novo",
                "Prague",
                "Praia",
                "Pretoria",
                "Pristina",
                "Pyongyang",
                "Quito",
                "Rabat",
                "Reykjavík",
                "Riga",
                "Riyadh",
                "Road Town",
                "Rome",
                "Roseau",
                "Saint Helier",
                "Saint Peter Port",
                "Saint-Pierre",
                "San Antonio",
                "San José",
                "San Juan",
                "San Marino",
                "San Salvador",
                "Sana'a",
                "Santiago",
                "Santo Domingo",
                "São Tomé",
                "Sarajevo",
                "Seoul",
                "Singapore",
                "Skopje",
                "Sofia",
                "Sri Jayawardenepura Kotte",
                "St. George's",
                "St. John's",
                "Stanley",
                "Stepanakert",
                "Stockholm",
                "Sucre, La Paz",
                "Sukhumi",
                "Suva",
                "Taipei",
                "Tallinn",
                "Tarawa",
                "Tashkent",
                "Tbilisi",
                "Tegucigalpa",
                "Tehran",
                "The Valley",
                "Thimphu",
                "Tirana",
                "Tiraspol",
                "Tokyo",
                "Tórshavn",
                "Tripoli",
                "Tskhinvali",
                "Tunis",
                "Ulaanbaatar",
                "Vaduz",
                "Valletta",
                "Vatican City",
                "Victoria",
                "Vienna",
                "Vientiane",
                "Vilnius",
                "Warsaw",
                "Washington, D.C.",
                "Wellington",
                "West Island",
                "Willemstad",
                "Windhoek",
                "Yamoussoukro",
                "Yaoundé",
                "Yaren",
                "Yerevan",
                "Zagreb");
    }
}