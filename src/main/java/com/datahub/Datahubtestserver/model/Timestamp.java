package com.datahub.Datahubtestserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Timestamp {
    private String from;
    private String to;

    public Timestamp(
            @JsonProperty("from") String from,
            @JsonProperty("to") String to)
    {
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
}
