package com.datahub.Datahubtestserver.model;

import com.datahub.Datahubtestserver.download.Downloader;
import com.datahub.Datahubtestserver.model.filters.Filter;
import com.datahub.Datahubtestserver.model.plots.Plot;
import com.datahub.Datahubtestserver.model.plots.PlotData;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

public class Dataset {
    private String name;
    private DatasetType type;
    private Map<String,String> urls;
    private List<Data> static_data;
    private List<Data> sensors_data;
    private List<Plot> plots;
    private Timestamp timestamp;
    private List<Filter> filters;
    private Updates updates;

    public Dataset(
            @JsonProperty("name") String name,
            @JsonProperty("urls") Map<String,String> urls,
            @JsonProperty("static_data") List<Data> static_data,
            @JsonProperty("sensors_data") List<Data> sensors_data,
            @JsonProperty("plots") List<Plot> plots,
            @JsonProperty("timestamp") Timestamp timestamp,
            @JsonProperty("filters") List<Filter> filters,
            @JsonProperty("updates") Updates updates)
    {
        this.name = name;
        this.urls = urls;
        this.static_data = static_data;
        this.sensors_data = sensors_data;
        this.plots = plots;
        this.timestamp = timestamp;
        this.filters = filters;
        this.updates = updates;
        this.updates.setUpdate(this);



    }

    public void downloadRecords() {
        try {
            for(String url: urls.keySet()){
                Downloader.download(urls.get(url), timestamp).subscribe(jsonData -> {
                    for (Data data: static_data) {
                        if(data.getUrl().equals(url)){
                            data.uploadStaticRecords(jsonData);
                        }
                    }
                    for (Data data: sensors_data) {
                        if(data.getUrl().equals(url)){
                            data.uploadRecords(jsonData);
                        }
                    }
                });
            }

        } catch (JSONException e) {
            System.out.println("Cannot download | Remember to run VPN");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void updateRecords()
    {
        if (
                timestamp == null ||
                timestamp.getFrom().equals("") ||
                timestamp.getTo().equals("")
        ) {
            return;
        }
        try {
            for(String url: urls.keySet()){
                JSONObject jsonObject = Downloader.downloadLatestRecord(urls.get(url), timestamp);
                System.out.println("Update download obj: " + jsonObject);
                for (Data data: sensors_data) {
                    if(data.getUrl().equals(url)){
                        data.uploadUpdatedRecords(jsonObject);
                    }
                }
            }

        } catch (JSONException e) {
            System.out.println("Cannot download | Remember to run VPN");
        } catch (IOException | ParseException e) {
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

    public Map<String,String> getUrls() {
        return urls;
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
