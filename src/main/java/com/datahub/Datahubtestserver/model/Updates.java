package com.datahub.Datahubtestserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;

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

    public boolean isUpdate() {
        return update;
    }

    public int getUpdate_interval_sec() {
        return update_interval_sec;
    }
}
