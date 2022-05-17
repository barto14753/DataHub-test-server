package com.datahub.Datahubtestserver.model.plots;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedList;
import java.util.List;

public class Plot {
    private final String plot_name;
    private final List<PlotData> plot_data;

    public Plot(
            @JsonProperty("plot_name") String plot_name,
            @JsonProperty("plot_data") List<PlotData> plot_data)
    {
        this.plot_name = plot_name;
        this.plot_data = plot_data;
    }

    public List<PlotData> getPlot_data() {
        return plot_data;
    }

    public String getPlot_name() {
        return plot_name;
    }
}
