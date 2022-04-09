package com.datahub.Datahubtestserver.model.filters;

import com.datahub.Datahubtestserver.model.Record;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeInfo(use = NAME, include = PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value= SortFilter.class, name = "SortFilter"),
})
public interface Filter {

    public List<Record> apply(List<Record> records);
}
