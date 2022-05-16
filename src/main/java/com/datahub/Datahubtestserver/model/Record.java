package com.datahub.Datahubtestserver.model;

import com.datahub.Datahubtestserver.model.filters.TagType;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Record<T> {
    @JsonProperty("data") private T data;
    @JsonProperty("timestamp") private String timestamp;
    @JsonProperty("tag") private TagType tag;

    @JsonCreator
    public Record(T data, String timestamp)
    {
        this.data = data;
        this.timestamp = timestamp;
        this.tag = TagType.NORMAL;
    }

    public T getData() {
        return data;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public TagType getTag() {
        return tag;
    }

    public void setTag(TagType tag) {
        this.tag = tag;
    }
}
