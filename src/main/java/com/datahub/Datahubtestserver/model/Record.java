package com.datahub.Datahubtestserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Record<T> {
    @JsonProperty("data") private T data;
    @JsonProperty("timestamp") private String timestamp;

    public Record(T data, String timestamp)
    {
        this.data = data;
        this.timestamp = timestamp;
    }

    public T getData() {
        return data;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
