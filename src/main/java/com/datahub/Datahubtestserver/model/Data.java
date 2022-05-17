package com.datahub.Datahubtestserver.model;

import com.datahub.Datahubtestserver.download.RecordsFactory;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class Data {
    private final String name;
    private final String source;
    private final String unit;
    private List<Record> records;

    public Data(
            @JsonProperty("name") String name,
            @JsonProperty("source") String source,
            @JsonProperty("unit") String unit)
    {
        this.name = name;
        this.source = source;
        this.unit = unit;
        this.records = new ArrayList<>();

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

    public String getName() {
        return name;
    }

    public String getSource() {
        return source;
    }

    public String getUnit() {
        return unit;
    }

    public List<Record> getRecords() { return records; }
}

