package com.datahub.Datahubtestserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Timer;
import java.util.TimerTask;

public class Updates {
    private boolean update;
    private int update_interval_sec;

    public Updates(
            @JsonProperty("update") boolean update,
            @JsonProperty("update_interval_sec") int update_interval_sec)
    {
        this.update = update;
        this.update_interval_sec = update_interval_sec;
    }

    public void setUpdate(Dataset dataset)
    {
        System.out.println("Set update: " + dataset.getName() + " | " + this.update_interval_sec + "s");
        new Timer().scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){
                System.out.println("Run update: " + dataset.getName());
                dataset.updateRecords();
            }
        },60 * 1000L,this.update_interval_sec * 1000L);
    }

    public boolean isUpdate() {
        return update;
    }

    public int getUpdate_interval_sec() {
        return update_interval_sec;
    }
}
