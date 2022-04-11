package com.datahub.Datahubtestserver.controllers;

import com.datahub.Datahubtestserver.model.Data;
import com.datahub.Datahubtestserver.model.Dataset;
import com.datahub.Datahubtestserver.model.Record;
import com.datahub.Datahubtestserver.state.Datasets;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/datasets")
public class DatasetsController {
    private Datasets datasets = Datasets.getInstance();

    @GetMapping("/{dataset}")
    @ResponseBody
    public Dataset getDataset(@PathVariable String dataset)
    {
        return datasets.getDataset(dataset);
    }

    @GetMapping("/{dataset}/{data}")
    @ResponseBody
    public Data getDataset(@PathVariable String dataset, @PathVariable String data)
    {
        return datasets.getDataset(dataset).getData(data);
    }

    @GetMapping("/{dataset}/{data}/records")
    @ResponseBody
    public List<Record> getData(@PathVariable String dataset, @PathVariable String data)
    {
        return datasets.getDataset(dataset).getData(data).getRecords();
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
