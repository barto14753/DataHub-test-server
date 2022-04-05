package com.datahub.Datahubtestserver.controllers;

import com.datahub.Datahubtestserver.model.Data;
import com.datahub.Datahubtestserver.model.Dataset;
import com.datahub.Datahubtestserver.state.Datasets;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/datasets")
public class DatasetsController {
    private Datasets datasets = Datasets.getInstance();

    @GetMapping("/{name}")
    @ResponseBody
    public Dataset getDataset(@PathVariable String name)
    {
        return datasets.getDataset(name);
    }

    @GetMapping("/all")
    @ResponseBody
    public List<Dataset> getAllDatasets()
    {
        return datasets.getDatasets();
    }

    @GetMapping("/allNames")
    @ResponseBody
    public List<String> getAllDatasetsNames()
    {
        return datasets.getDatasetsNames();
    }



}
