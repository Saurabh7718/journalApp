    package net.engineeringDigest.journalApp.apiResponse;


    import com.fasterxml.jackson.annotation.JsonProperty;
    import lombok.Data;

    import java.util.List;

    @Data
    public class WeatherResponse {

            private Current current;

            @Data
            public class Current {


                private int temperature;


                @JsonProperty("weather_descriptions")
                private List<String> weatherDescriptions;


                private int feelslike;

            }


    }

    // import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
    // import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
    /* ObjectMapper om = new ObjectMapper();
    Root root = om.readValue(myJsonString, Root.class); */
