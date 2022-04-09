package com.datahub.Datahubtestserver.model.filters;

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
    public List<Record> apply(List<Record> records) {
        // TODO : Method should sort all datas same way (now they are sorted independent-ally)
        return records.stream().sorted().toList();
    }

}
