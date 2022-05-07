package com.datahub.Datahubtestserver.state;

import com.datahub.Datahubtestserver.model.Data;
import com.datahub.Datahubtestserver.model.Dataset;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Datasets {
    private static Datasets INSTANCE;
    private List<Dataset> datasets;
    private ExecutorService threadpool = Executors.newCachedThreadPool();

    private Datasets(List<Dataset> datasets)
    {
        this.datasets = datasets;
    }

    private Datasets()
    {
        this.datasets = new ArrayList<>();
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
        threadpool.shutdownNow();
        threadpool = Executors.newCachedThreadPool();
        this.datasets = datasets;

        for (Dataset d: datasets)
        {
            threadpool.submit(() -> {
                System.out.println("Download: " + d.getName());
                d.downloadRecords();
                d.applyFilters();
                System.out.println("Done download: " + d.getName());
            });
        }
    }

    public List<String> getDatasetsNames()
    {
        return this.datasets.stream().map((Dataset::getName)).toList();
    }
}
