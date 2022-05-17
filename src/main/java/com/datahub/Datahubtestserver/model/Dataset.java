package com.datahub.Datahubtestserver.model;

import com.datahub.Datahubtestserver.download.Downloader;
import com.datahub.Datahubtestserver.model.filters.Filter;
import com.datahub.Datahubtestserver.model.plots.Plot;
import com.datahub.Datahubtestserver.model.plots.PlotData;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

public class Dataset {
    private String name;
    private DatasetType type;
    private String url;
    private List<Data> static_data;
    private List<Data> sensors_data;
    private List<Plot> plots;
    private Timestamp timestamp;
    private List<Filter> filters;
    private Updates updates;

    public Dataset(
            @JsonProperty("name") String name,
            @JsonProperty("type") DatasetType type,
            @JsonProperty("url") String url,
            @JsonProperty("static_data") List<Data> static_data,
            @JsonProperty("sensors_data") List<Data> sensors_data,
            @JsonProperty("plots") List<Plot> plots,
            @JsonProperty("timestamp") Timestamp timestamp,
            @JsonProperty("filters") List<Filter> filters,
            @JsonProperty("updates") Updates updates)
    {
        this.name = name;
        this.type = type;
        this.url = url;
        this.static_data = static_data;
        this.sensors_data = sensors_data;
        this.plots = plots;
        this.timestamp = timestamp;
        this.filters = filters;
        this.updates = updates;


    }

    public void downloadRecords()
    {
        try {
            Downloader.download(url, timestamp).subscribe(jsonData -> {
                for (Data data: static_data) data.uploadStaticRecords(jsonData);
                for (Data data: sensors_data) data.uploadRecords(jsonData);
            });


        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void applyFilters()
    {
        for (Filter filter: filters)
        {
            filter.apply(sensors_data);
        }
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

    public String getUrl() {
        return url;
    }

    public Updates getUpdates() {
        return updates;
    }

    public List<Filter> getFilters() {
        return filters;
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

    public List<Plot> getPlots() {
        return plots;
    }
}
