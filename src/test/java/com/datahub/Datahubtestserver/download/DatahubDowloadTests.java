package com.datahub.Datahubtestserver.download;


import com.datahub.Datahubtestserver.model.Record;
import com.datahub.Datahubtestserver.model.Timestamp;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

public class DatahubDowloadTests {
    @Test
    public void testDownload() throws IOException, InterruptedException, JSONException {
        String url = "https://datahub.ki.agh.edu.pl/api/endpoints/70/data/";
        // When
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JSONObject myObject = new JSONObject(response.body());
        System.out.println(myObject); // Your json object

    }

    @Test
    public void testDownloader() throws IOException, InterruptedException, JSONException, ParseException {
        String url = "https://datahub.ki.agh.edu.pl/api/endpoints/70/data/";
        String source = "heater.tempSet";

        List<Record> records = RecordsFactory.downloadRecords(url, new Timestamp("", ""), source);
        System.out.println(records);

    }

    @Test
    public void testGetPath()
    {
        List<String> res = RecordsFactory.getPath("a.b.c");
        Assert.isTrue(res.size() == 3);
    }


}
