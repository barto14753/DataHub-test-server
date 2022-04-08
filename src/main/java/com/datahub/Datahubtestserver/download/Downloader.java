package com.datahub.Datahubtestserver.download;

import com.datahub.Datahubtestserver.model.Record;
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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Downloader {
    public static JSONObject download(String url) throws IOException, InterruptedException, JSONException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return new JSONObject(response.body());

    }

    public static JSONArray download(String url, Timestamp timestamp) throws JSONException, IOException, InterruptedException {
        JSONArray result = new JSONArray();
        JSONObject data = Downloader.download(url);

        boolean finished = false;
        while (!finished)
        {
            JSONArray array = data.getJSONArray("results");
            for (int i=0; i<array.length(); i++)
            {
                JSONObject obj = array.getJSONObject(i);
                String datetime = obj.getString("timestamp");
                try {
                    if (timestamp.isInRange(datetime)) result.put(obj);
                    else if (timestamp.isLate(datetime))
                    {
                        finished = true;
                        return result;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            if (data.has("next")) data = Downloader.download(data.getString("next"));
            else finished = true;
        }
        return result;

    }


}
