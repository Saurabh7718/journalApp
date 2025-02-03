package net.engineeringDigest.journalApp.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Data;
import net.engineeringDigest.journalApp.apiResponse.WeatherResponse;
import net.engineeringDigest.journalApp.cache.AppCache;
import net.engineeringDigest.journalApp.constants.Placeholders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class WeatherService {
//https://api.weatherstack.com/current?access_key=efd935bbc5352372561c61053750ec08&query=Mumbai

    @Value("${weather.api.key}")
    private String apiKey;

    @Autowired
    private AppCache appCache;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisService redisService;

    public WeatherResponse getWeather(String city) throws JsonProcessingException {
        WeatherResponse weatherResponse = redisService.get("weather", WeatherResponse.class);
        if (weatherResponse != null) {
            return weatherResponse;
        } else {
            String finalAPI = appCache.APP_CACHE.get(AppCache.Keys.weather_api.toString()).replace(Placeholders.CITY, city).replace(Placeholders.API_KEY, apiKey);
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
            WeatherResponse body = response.getBody();
            if (body != null) {
                redisService.set("weather" , body, 300l);
            }

            return body;
        }


    }


//
//
//    HttpEntity httpEntity = new HttpEntity<>(body);
//
//    public WeatherResponse createWeather(String city) {
//        String finalAPI = API.replace("CITY", city).replace("API_KEY", apiKey);
//
//        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.POST, httpEntity, WeatherResponse.class);
//        WeatherResponse body = response.getBody();
//        return body;
//
//
//    }


}
