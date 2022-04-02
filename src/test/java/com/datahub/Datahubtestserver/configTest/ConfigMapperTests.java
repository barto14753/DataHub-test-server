package com.datahub.Datahubtestserver.configTest;

import com.datahub.Datahubtestserver.model.*;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertEquals;

public class ConfigMapperTests {
    @Test
    public void fromObjectToJSONTest() throws IOException {
        Updates updates = new Updates(true, 600);
        Timestamp timestamp = new Timestamp("2022-04-02T15:33:16+02:00", "now");
        Data data = new Data("Temperature", "https://datahub.ki.agh.edu.pl/api/endpoints/70/data/",
                "heater.tempSet", timestamp, "celsius", updates);
        List<Data> sensors_data = List.of(data);
        Dataset dataset = new Dataset("new_data", DatasetType.CHART, null, sensors_data);
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
                new File("src/main/java/com/datahub/Datahubtestserver/config/config.json"),
                Config.class);

        Assert.hasText(config.getDatasets().get(0).getName(), "new_data");
        Assert.isNull(config.getDatasets().get(0).getStatic_data());
        Assert.hasText(config.getDatasets().get(0).getSensors_data().get(0).getName(), "Temperature");

    }
}
