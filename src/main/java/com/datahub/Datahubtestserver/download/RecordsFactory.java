package com.datahub.Datahubtestserver.download;

import com.datahub.Datahubtestserver.model.Record;
import com.datahub.Datahubtestserver.model.Timestamp;
import javassist.compiler.ast.Pair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class RecordsFactory {
    public static List<Record> downloadRecords(String url, Timestamp timestamp, String source)
            throws JSONException, IOException, InterruptedException, ParseException {

        List<Record> records = new ArrayList<>();
        JSONObject data = Downloader.download(url);
        boolean finished = false;
        while (!finished)
        {
            JSONArray results = data.getJSONArray("results");
            records.addAll(jsonsToRecords(results, timestamp, source));
            if (records.size() == results.length() && data.has("next"))
            {
                data = Downloader.download(data.getString("next"));
            }
            else
            {
                finished = true;
            }
        }

        return records;
    }

    private static boolean isValid(Timestamp timestamp, String recordTimestamp) throws ParseException {
        if (timestamp == null) return true;
        return timestamp.isInRange(recordTimestamp);
    }

    public static List<Record> jsonsToRecords(JSONArray array, Timestamp timestamp, String source) throws JSONException, ParseException {
        List<Record> records = new ArrayList<>();
        Boolean end = false;
        for (int i=0; i< array.length(); i++)
        {
            Record record = jsonToRecord(array.getJSONObject(i), source);
            if (!isValid(timestamp, record.getTimestamp()))
            {
                break;
            }
            records.add(record);
        }
        return records;
    }

    public static Record jsonToRecord(JSONObject object, String source) throws JSONException {
        List<String> path = getPath(source);
        String timestamp = object.getString("timestamp");
        object = object.getJSONObject("data");
        for (int i=0; i<path.size()-1; i++)
        {
            object = object.getJSONObject(path.get(i));
        }

        return new Record(object.get(path.get(path.size()-1)), timestamp);

    }

    public static List<String> getPath(String source)
    {
        return List.of(source.split("\\."));
    }


}
