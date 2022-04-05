package com.datahub.Datahubtestserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.stream.Stream;

public class Dataset {
    private String name;
    private DatasetType type;
    private List<Data> static_data;
    private List<Data> sensors_data;

    public Dataset(
            @JsonProperty("name") String name,
            @JsonProperty("type") DatasetType type,
            @JsonProperty("static_data") List<Data> static_data,
            @JsonProperty("sensors_data") List<Data> sensors_data)
    {
        this.name = name;
        this.type = type;
        this.static_data = static_data;
        this.sensors_data = sensors_data;
    }

    public DatasetType getType() {
        return type;
    }

    public List<Data> getSensors_data() {
        return sensors_data;
    }

    public List<Data> getStatic_data() {
        return static_data;
    }

    public Data getData(String name)
    {
        return Stream.concat(static_data.stream(), sensors_data.stream())
                .filter(data -> data.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public String getName() {
        return name;
    }
}
