package com.datahub.Datahubtestserver.state;

import com.datahub.Datahubtestserver.model.Timestamp;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.text.ParseException;
import java.util.Date;

public class CacheTest {
    @Test
    public void testReadCacheFile() {
        Cache.loadCachedDatasets();
    }
}
