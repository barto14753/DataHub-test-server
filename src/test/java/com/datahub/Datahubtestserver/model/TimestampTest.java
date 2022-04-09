package com.datahub.Datahubtestserver.model;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class TimestampTest {
    @Test
    public void testDataTimeFormat() throws ParseException {
        Date now = new Date();
        String date = Timestamp.getDate(now);
        Date date_ = Timestamp.getDate(date);

        Assert.isTrue(date_.toString().equals(now.toString()));

    }
}
