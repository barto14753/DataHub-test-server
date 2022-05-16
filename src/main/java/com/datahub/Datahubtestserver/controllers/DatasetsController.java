package com.datahub.Datahubtestserver.controllers;

import com.datahub.Datahubtestserver.model.Data;
import com.datahub.Datahubtestserver.model.Dataset;
import com.datahub.Datahubtestserver.model.Record;
import com.datahub.Datahubtestserver.state.Datasets;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/datasets")
public class DatasetsController {
    private Datasets datasets = Datasets.getInstance();

    @GetMapping("/{dataset}")
    @ResponseBody
    public Dataset getDataset(@PathVariable String dataset)
    {
        return Optional.ofNullable(datasets)
                .map(d -> d.getDataset(dataset))
                .orElse(null);
    }

    @GetMapping("/{dataset}/{data}")
    @ResponseBody
    public Data getDataset(@PathVariable String dataset, @PathVariable String data)
    {
        return Optional.ofNullable(datasets)
                .map(d -> d.getDataset(dataset))
                .map(d -> d.getData(data))
                .orElse(null);
    }

    @GetMapping("/{dataset}/{data}/records")
    @ResponseBody
    public List<Record> getData(@PathVariable String dataset, @PathVariable String data)
    {
        return Optional.ofNullable(datasets)
                .map(d -> d.getDataset(dataset))
                .map(d -> d.getData(data))
                .map(Data::getRecords)
                .orElse(null);
    }

    @GetMapping("/all")
    @ResponseBody
    public List<Dataset> getAllDatasets()
    {
        return Optional.ofNullable(datasets)
                .map(Datasets::getDatasets)
                .orElse(null);
    }

    @GetMapping("/allNames")
    @ResponseBody
    public List<String> getAllDatasetsNames()
    {
        return datasets.getDatasetsNames();
    }



}
