package com.datahub.Datahubtestserver.model;

import com.datahub.Datahubtestserver.download.RecordsFactory;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.text.ParseException;
import java.util.*;

public class Data {
    private final String name;
    private final String url;
    private final String source;
    private final String unit;
    private List<Record> records;

    public Data(
            @JsonProperty("name") String name,
            @JsonProperty("url") String url,
            @JsonProperty("source") String source,
            @JsonProperty("unit") String unit)
    {
        this.name = name;
        this.url = url;
        this.source = source;
        this.unit = unit;
        this.records = Collections.synchronizedList(new ArrayList<>());

    }

    public void uploadRecords(JSONObject data)
    {
        try {
            this.records.add(RecordsFactory.jsonToRecord(data, this.source));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void uploadStaticRecords(JSONObject data)
    {
        try {
            if (records.size() == 0)
            {
                this.records.add(RecordsFactory.jsonToRecord(data, this.source));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void uploadUpdatedRecords(JSONObject data)
    {
        try {
            if (data == null) return;

            Record newRecord = RecordsFactory.jsonToRecord(data, this.source);
            if (records.size() == 0) {
                records.add(newRecord);
            }
            Date newRecordDate = Timestamp.stringToDate(newRecord.getTimestamp());
            Date recordDate = Timestamp.stringToDate(records.get(0).getTimestamp());
            if (newRecordDate.after(recordDate))
            {
                System.out.println("Added as updated: " + newRecord);
                records.add(0, newRecord);
            }

        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public String getSource() {
        return source;
    }

    public String getUnit() {
        return unit;
    }

    public String getUrl(){return url;};

    public List<Record> getRecords() {
        return records;
    }
}

