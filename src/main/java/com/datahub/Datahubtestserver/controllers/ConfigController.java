package com.datahub.Datahubtestserver.controllers;

import com.datahub.Datahubtestserver.model.Config;
import com.datahub.Datahubtestserver.state.Datasets;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/config")
public class ConfigController {
    private Config config = null;
    private Datasets datasets = Datasets.getInstance();

    @GetMapping("/get")
    @ResponseBody
    public Config getConfig()
    {
        return config;
    }

    @PostMapping("/load")
    @ResponseBody
    public Config postConfig(@RequestBody Config config)
    {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        this.config = config;
        datasets.setDatasets(config.getDatasets());

        return config;
    }

}
