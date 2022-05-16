package com.datahub.Datahubtestserver.download;

import com.datahub.Datahubtestserver.model.Timestamp;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import rx.Observable;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.ParseException;

import static com.datahub.Datahubtestserver.model.Timestamp.getMillis;


public class Downloader {
    public static JSONObject download(String url) throws IOException, JSONException {
        try
        {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return new JSONObject(response.body());
        }
        catch (InterruptedException e)
        {
            System.out.println("Exception: download was interrupted");
        }

        return null;

    }

    public static Observable<JSONObject> download(String url, Timestamp timestamp) throws JSONException, IOException, InterruptedException {
        return Observable.create(emitter -> {
            //JSONArray result = new JSONArray();

            try
            {
                JSONObject data = Downloader.download(url);

                boolean finished = false;
                while (!finished && data != null)
                {
                    JSONArray array = data.getJSONArray("results");
                    boolean skip = false;
                    if(!timestamp.fromNow && getMillis(array.getJSONObject(array.length()-1)) > timestamp.TO_MILLIS){
                        skip = true;
                    }
                    if(!skip){
                        for (int i=0; i<array.length(); i++)
                        {
                            JSONObject obj = array.getJSONObject(i);
                            String datetime = obj.getString("timestamp");
                            try {
                                if (timestamp.isInRange(datetime)) emitter.onNext(obj);
                                else if (timestamp.isLate(datetime))
                                {
                                    finished = true;
                                    return;
                                }
                            }
                            catch (NumberFormatException e)
                            {
                                System.out.println("Exception: Empty timestamp");
                            }
                            catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    if (data.has("next")) data = Downloader.download(data.getString("next"));
                    else finished = true;
                }
                System.out.println("still going strong");
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        });
    }




}
