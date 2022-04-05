package com.datahub.Datahubtestserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Config {
    private List<Dataset> datasets;

    public Config(@JsonProperty("datasets") List<Dataset> datasets)
    {
        this.datasets = datasets;
    }

    public List<Dataset> getDatasets()
    {
        return this.datasets;
    }

    public void setDatasets(List<Dataset> datasets) {
        this.datasets = datasets;
    }
}
