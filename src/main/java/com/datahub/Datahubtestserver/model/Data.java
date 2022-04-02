package com.datahub.Datahubtestserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Data {
    private final String name;
    private final String url;
    private final Timestamp timestamps;
    private final String source;
    private final String unit;
    private final Updates updates;

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
}

