package com.datahub.Datahubtestserver.download;

import com.datahub.Datahubtestserver.model.Timestamp;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class Downloader {
    public static List<Double> download(String url, Timestamp timestamp, String source) throws IOException, InterruptedException, JSONException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JSONObject myObject = new JSONObject(response.body());
        JSONArray results = myObject.getJSONArray("results");
        List<Double> result = new ArrayList<>();

        for (int i=0; i<results.length(); i++)
        {
            JSONObject object = results.getJSONObject(i);
            JSONObject data = object.getJSONObject("data");
            System.out.println(data);
            result.add(data.getDouble(source));
            System.out.println(object.get("timestamp")); // Your json object
        }
        System.out.println(result); // Your json object
        return result;
    }
}
