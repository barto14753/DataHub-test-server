package com.datahub.Datahubtestserver.state;

import com.datahub.Datahubtestserver.model.Dataset;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Cache {
    public static final String CACHE_FILE_PATH = "src/main/java/com/datahub/Datahubtestserver/config/cache.json";

    public static List<Dataset> loadCachedDatasets()
    {
        ObjectMapper objectMapper = new ObjectMapper();

        try
        {
            List<Dataset> cachedDatasets = objectMapper.readValue(
                    new File(CACHE_FILE_PATH),
                    new TypeReference<List<Dataset>>(){}
            );

            return cachedDatasets;

        }
        catch(IOException e)
        {
            System.out.println("Cannot read cache file");
            e.printStackTrace();
            return null;
        }
    }

    public static void saveDatasetsToCache(List<Dataset> datasetsToCache)
    {
        ObjectMapper objectMapper = new ObjectMapper();

        try
        {
            objectMapper.writeValue(
                    new File(CACHE_FILE_PATH),
                    datasetsToCache
            );


        }
        catch(IOException e)
        {
            System.out.println("Cannot write to cache file");
        }
    }
}
