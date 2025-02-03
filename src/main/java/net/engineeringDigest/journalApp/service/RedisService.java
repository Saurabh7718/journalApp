package net.engineeringDigest.journalApp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.engineeringDigest.journalApp.apiResponse.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;


    public <T> T get(String key, Class<T> weatherResponseClass) throws JsonProcessingException {
       try{
           Object o = redisTemplate.opsForValue().get(key);
           if (o == null) {
               return null;  // Or handle this case if the value is not found in Redis
           }
           // here we are getting reponse in object but we want to share reponse in reposetype for weather response
           //means we want to share repsonse in the form of that class means POJO for weather api
           //we can convert by using ObjectMapper

           ObjectMapper mapper = new ObjectMapper();

           //mapper first take string and second object take class in which want to convert
           return mapper.readValue(o.toString(), weatherResponseClass);
       }
       catch(Exception e){
           log.error("Exception "+e);
           return null;
       }
    }


    public void set(String key, Object o, Long ttl) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(o);
            redisTemplate.opsForValue().set(key, json, ttl, TimeUnit.SECONDS);


        } catch (Exception e) {
            log.error(String.valueOf(e));
         }
    }


}
