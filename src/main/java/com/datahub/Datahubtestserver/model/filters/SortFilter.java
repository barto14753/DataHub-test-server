package com.datahub.Datahubtestserver.model.filters;

import com.datahub.Datahubtestserver.model.Data;
import com.datahub.Datahubtestserver.model.Record;
import com.datahub.Datahubtestserver.model.Timestamp;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


import java.text.ParseException;
import java.util.List;

public class SortFilter implements Filter{
    private String data_name;

    public SortFilter(@JsonProperty("data_name") String data_name)
    {
        this.data_name = data_name;
    }

    @Override
    public List<Data> apply(List<Data> data) {
        return null;
    }
}
