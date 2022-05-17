package com.datahub.Datahubtestserver.config;

import com.datahub.Datahubtestserver.model.*;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ConfigMapperTests {
    @Test
    public void fromObjectToJSONTest() throws IOException {
        Updates updates = new Updates(true, 600);
        Timestamp timestamp = new Timestamp("2022-04-02T15:33:16+02:00", "now");
        Data data = new Data("Temperature",
                "heater.tempSet", "celsius");
        List<Data> sensors_data = List.of(data);
        Dataset dataset = new Dataset("new_data", DatasetType.CHART,
                "https://datahub.ki.agh.edu.pl/api/endpoints/70/data/",
                List.of(), sensors_data, List.of(), timestamp, List.of(), updates);
        Config config = new Config(List.of(dataset));

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        objectMapper.writeValue(new File("src/main/java/com/datahub/Datahubtestserver/config/config.json"), config);

    }

    @Test
    public void fromJSONToObjectTest() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        Config config = objectMapper.readValue(
                new File("src/main/java/com/datahub/Datahubtestserver/config/new_config.json"),
                Config.class);

        Assert.hasText(config.getDatasets().get(0).getName(), "dataset_1");
        Assert.hasText(config.getDatasets().get(0).getSensors_data().get(0).getName(), "Temperature");

    }
}
