package com.datahub.Datahubtestserver.model;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.List;

public class DatasetTest {
    @Test
    public void testGetData()
    {
        Data data1 = new Data("A", null, null, null, null, null);
        Data data2 = new Data("B", null, null, null, null, null);
        Dataset dataset = new Dataset("dataset", DatasetType.DATA, List.of(data1), List.of(data2));

        Assert.isTrue(data1.equals(dataset.getData("A")));
        Assert.isTrue(data2.equals(dataset.getData("B")));
        Assert.isTrue(!data1.equals(dataset.getData("B")));
    }
}
