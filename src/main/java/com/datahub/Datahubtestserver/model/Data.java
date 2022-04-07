package com.datahub.Datahubtestserver.model;

import com.datahub.Datahubtestserver.download.RecordsFactory;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONException;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class Data {
    private final String name;
    private final String url;
    private final Timestamp timestamps;
    private final String source;
    private final String unit;
    private final Updates updates;
    private List<Record> records;

    public Data(
            @JsonProperty("name") String name,
            @JsonProperty("url") String url,
            @JsonProperty("source") String source,
            @JsonProperty("timestamps") Timestamp timestamps,
            @JsonProperty("unit") String unit,
            @JsonProperty("updates") Updates updates)
    {
        this.name = name;
        this.url = url;
        this.source = source;
        this.timestamps = timestamps;
        this.unit = unit;
        this.updates = updates;
        this.updateRecords();

    }

    public void updateRecords()
    {
        try {
            this.records = RecordsFactory.downloadRecords(this.url, this.timestamps, this.source);
        } catch (JSONException | ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
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

    public String getUrl() {
        return url;
    }

    public Timestamp getTimestamps() {
        return timestamps;
    }

    public Updates getUpdates() {
        return updates;
    }

    public List<Record> getRecords() { return records; }
}

