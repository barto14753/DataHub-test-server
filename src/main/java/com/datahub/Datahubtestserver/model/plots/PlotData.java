package com.datahub.Datahubtestserver.model.plots;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class PlotData {
    private final String data_name;
    private final String plot_color;

    public PlotData(
            @JsonProperty("data_name") String data_name,
            @JsonProperty("plot_color") String plotColor)
    {
        this.data_name = data_name;
        this.plot_color = plotColor;
    }

    public String getData_name() {
        return data_name;
    }

    public String getPlot_color() {
        return plot_color;
    }
}
