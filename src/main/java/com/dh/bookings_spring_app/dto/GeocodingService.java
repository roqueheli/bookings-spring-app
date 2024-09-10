package com.dh.bookings_spring_app.dto;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class GeocodingService {
    private static final String GOOGLE_GEOCODE_API_URL = "https://maps.googleapis.com/maps/api/geocode/json";    
    @Value("${google.api-key}")
    private String apiKey;
    
    public String getLocationFromAddress(String address) {
        URI uri = URI.create(GOOGLE_GEOCODE_API_URL);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUri(uri)
                .queryParam("address", address)
                .queryParam("key", apiKey);

        RestTemplate restTemplate = new RestTemplate();
        GoogleGeocodeResponse response = restTemplate.getForObject(uriBuilder.toUriString(),
                GoogleGeocodeResponse.class);

        if (response != null && response.getResults() != null && response.getResults().length > 0) {
            GoogleGeocodeResponse.Result result = response.getResults()[0];
            return result.getGeometry().getLocation().getLat() + ", " + result.getGeometry().getLocation().getLng();
        } else {
            return null;
        }
    }
}
