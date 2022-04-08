package com.datahub.Datahubtestserver.model;

import com.datahub.Datahubtestserver.download.RecordsFactory;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.text.ParseException;
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

    }

    public void uploadRecords(JSONArray data)
    {
        try {
            this.records = RecordsFactory.jsonsToRecords(data, this.source);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void uploadStaticRecords(JSONArray data)
    {
        try {
            this.records = RecordsFactory.jsonsToRecord(data, this.source);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
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

