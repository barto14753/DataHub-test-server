package com.datahub.Datahubtestserver.model.filters;

import com.datahub.Datahubtestserver.dataValidation.StandardDeviation;
import com.datahub.Datahubtestserver.model.Data;
import com.datahub.Datahubtestserver.model.Record;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Set;

public class OutlierFilter implements Filter{

    private List<String> data_names;

    public OutlierFilter(@JsonProperty("data_names") List<String> data_names)
    {
        this.data_names = data_names;
    }

    private List<Double> castRecordsToDoubles(Data data)
//    Records in data must be Doubles
    {
        return data.getRecords().stream()
                .map(Record::getData)
                .map(record -> (Double) record)
                .toList();
    }

    private void markOutliers(Data data, List<Integer> outlierIndexes)
    {
        List<Record> records = data.getRecords();
        for (Integer index: outlierIndexes)
        {
            records.get(index).setTag(TagType.OUTLIER);
        }
        return;
    }

    private void applyOnData(Data data)
    {

        List<Double> recordsData = castRecordsToDoubles(data);
        List<Integer> outlierIndexes = StandardDeviation.getBadDataIndexes(recordsData);
        markOutliers(data, outlierIndexes);
    }

    @Override
    public List<Data> apply(List<Data> data) {
        for (Data d: data)
        {
            if (data_names.contains(d.getName()))
            {
                applyOnData(d);
            }
        }
        return data;

    }
}
