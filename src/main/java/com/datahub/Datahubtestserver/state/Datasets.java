package com.datahub.Datahubtestserver.state;

import com.datahub.Datahubtestserver.model.Dataset;

import java.util.List;

public class Datasets {
    private static Datasets INSTANCE;
    private List<Dataset> datasets;

    private Datasets(List<Dataset> datasets)
    {
        this.datasets = datasets;
    }

    private Datasets()
    {
        this.datasets = List.of();
    }

    public static Datasets getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new Datasets();
        }
        return INSTANCE;
    }

    public List<Dataset> getDatasets() {
        return datasets;
    }

    public Dataset getDataset(String name)
    {
        for (Dataset dataset: datasets)
        {
            if (dataset.getName() == name) return dataset;
        }
        return null;
    }

    public void setDatasets(List<Dataset> datasets) {
        this.datasets = datasets;
    }
}
