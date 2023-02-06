package com.cargo.util;

import com.jayway.jsonpath.JsonPath;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.ResourceBundle;

/**
 * The PriceMaker class calculate the price of cargo transportation based on the distance traveled and the weight of the cargo.
 * The class also provides a method for fetching the distance between two origins using the Google Maps API.
 */
public class PriceMaker {
    private static final Logger LOGGER = Logger.getLogger(PriceMaker.class);

    ResourceBundle google = ResourceBundle.getBundle("application");

    /**
     * A string representing the API key for accessing the Google Maps API.
     */
    String key = google.getString("token");

    /**
     * An integer representing the distance between the two origins in kilometers.
     */
    private int kilometers = 0;

    /**
     * Calculates the price of cargo transportation based on the distance traveled and the weight of the cargo.
     * The volume weight is calculated as (width * length * height) / 4000.
     * If the volumetric weight is greater than the actual, we calculate the volumetric weight.
     *
     * @param distance the distance traveled in kilometers
     * @param weight   the weight of the cargo in kilograms
     * @param length   the length of the cargo in centimeters
     * @param height   the height of the cargo in centimeters
     * @param width    the width of the cargo in centimeters
     * @return the price of the cargo transportation
     */
    public int getPrice(int distance, int weight, int length, int height, int width) {
        int price;

        int volumeWeight = ((width * length * height) / 4000);
        if (volumeWeight > weight) {
            price = distance / 100 * volumeWeight * 2;
        } else {
            price = distance / 100 * weight * 2;
        }
        if (price < 60) {
            price = 60;
        }
        return price;
    }

    /**
     * Fetches the distance between two origins using the Google Maps API.
     * If the origins and destination is the same return 0 (we don`t transport delivery).
     *
     * @param origins      the origins branch
     * @param destinations the destinations branch
     * @return the distance between the two origins in kilometers
     */
    public int getDistance(String origins, String destinations) {
        try {
            if(origins.equals(destinations)){
                kilometers = 0;
                return kilometers;
            }

            String googleUrl = "https://maps.googleapis.com/maps/api/distancematrix/json?destinations=";
            String url = googleUrl + destinations + "&origins=" + origins + "&key=" + key;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .GET()
                    .build();
            HttpClient client = HttpClient.newBuilder()
                    .version(HttpClient.Version.HTTP_1_1)
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String a = response.body();

            List<Integer> distances = JsonPath.read(a, "$.rows[*].elements[*].distance.value");
            for (int distance : distances) {
                kilometers = distance / 1000;
            }

        } catch (IOException | URISyntaxException | InterruptedException e) {
            LOGGER.error("Cant get distance from google maps api");
        }
        return kilometers;
    }
}