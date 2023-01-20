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

public class PriceMaker {
    private static final Logger LOGGER = Logger.getLogger(PriceMaker.class);

    ResourceBundle google = ResourceBundle.getBundle("application");
    String key = google.getString("token");

    private int kilometers = 0;

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

    public int getDistance(String origins, String destinations) {
        try {
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