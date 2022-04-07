package com.datahub.Datahubtestserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.servlet.tags.EditorAwareTag;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Timestamp {
    private String from;// non datetime format options: always
    private String to; // non datetime format options: now
    private static final SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");

    public Timestamp(
            @JsonProperty("from") String from,
            @JsonProperty("to") String to)
    {
        this.from = from;
        this.to = to;
    }

    public static Date getDate(String datetime) throws ParseException {
        return dateTimeFormat.parse(datetime);
    }

    public static String getDate(Date datetime) throws ParseException {
        return dateTimeFormat.format(datetime);
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public boolean isInRange(String datetime) throws ParseException {
        Date date = getDate(datetime);
        if (from.equals("always"))
        {
            if (to.equals("now")) return true;
            else
            {
                Date _to = getDate(to);
                return _to.after(date);
            }
        }
        else
        {
            Date from_ = getDate(from);
            if (to.equals("now")) return from_.before(date);
            else
            {
                Date _to = getDate(to);
                return _to.after(date);
            }
        }

    }
}
