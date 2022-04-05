package com.datahub.Datahubtestserver.state;

import com.datahub.Datahubtestserver.model.Dataset;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

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
            if (Objects.equals(dataset.getName(), name)) return dataset;
        }
        return null;
    }

    public void setDatasets(List<Dataset> datasets) {
        this.datasets = datasets;
    }

    public List<String> getDatasetsNames()
    {
        return this.datasets.stream().map((Dataset::getName)).toList();
    }
}
