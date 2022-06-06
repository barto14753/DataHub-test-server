package com.datahub.Datahubtestserver.download;

import com.datahub.Datahubtestserver.model.Record;
import com.datahub.Datahubtestserver.model.TimePeriodSelection;
import com.datahub.Datahubtestserver.model.Timestamp;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    public static Observable<JSONObject> download(String url_, Timestamp timestamp) throws JSONException, IOException, InterruptedException {
        String url = url_ + "?limit=100"; // max limit (less calls for api data)

        return Observable.create(emitter -> {
            try
            {
                JSONObject data = Downloader.download(url);

                boolean finished = false;
                TimePeriodSelection selection = TimePeriodSelection.HOUR;
                int iter = 0;

                while (!finished && data != null)
                {
                    JSONArray array = data.getJSONArray("results");
                    for (int i=0; i<array.length(); i++)
                    {
                        JSONObject obj = array.getJSONObject(i);
                        String datetime = obj.getString("timestamp");
                        try {
                            if (timestamp.isInRange(datetime))
                            {
                                iter = (iter + 1) % selection.getSelectionRating();
                                TimePeriodSelection actualSelection = timestamp.getTimePeriodSelection(datetime);
                                if (actualSelection != selection)
                                {
                                    selection = actualSelection;
                                    iter = 0;
                                }

                                if (iter == 0)
                                {
                                    emitter.onNext(obj);
                                }
                            }
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

                    if (data.has("next")) data = Downloader.download(data.getString("next"));
                    else finished = true;
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        });


    }


}
