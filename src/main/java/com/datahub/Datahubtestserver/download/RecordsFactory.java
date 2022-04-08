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

    public static List<Record> jsonsToRecord(JSONArray array, String source) throws JSONException, ParseException {
        List<Record> records = new ArrayList<>();
        Record record = jsonToRecord(array.getJSONObject(0), source);
        records.add(record);
        return records;
    }

    public static List<Record> jsonsToRecords(JSONArray array, String source) throws JSONException, ParseException {
        List<Record> records = new ArrayList<>();
        for (int i=0; i< array.length(); i++)
        {
            Record record = jsonToRecord(array.getJSONObject(i), source);
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
