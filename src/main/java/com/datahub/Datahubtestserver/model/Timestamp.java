package com.datahub.Datahubtestserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Timestamp {
    private String from;// non datetime format options: always
    private final String to; // non datetime format options: now
    private static final SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");

    private static final long MILLIS_IN_A_HOUR = 1000 * 60 * 60;
    private static final long MILLIS_IN_A_DAY = 1000 * 60 * 60 * 24;
    private static final long MILLIS_IN_A_WEEK = 1000 * 60 * 60 * 24 * 7;
    public Timestamp(
            @JsonProperty("from") String from,
            @JsonProperty("to") String to)
    {
        try {
            this.from = getFrom(from,to);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.to = to;
    }

    private String getFrom(String from,String to) throws ParseException {
        Date toDate = getDate(to);
        return switch (from) {
            case "hour" -> Timestamp.dateTimeFormat.format(toDate.getTime() - MILLIS_IN_A_HOUR);
            case "day" -> Timestamp.dateTimeFormat.format(toDate.getTime() - MILLIS_IN_A_DAY);
            case "week" -> Timestamp.dateTimeFormat.format(toDate.getTime() - MILLIS_IN_A_WEEK);
            case "month" -> Timestamp.dateTimeFormat.format(toDate.getTime() - 4 * MILLIS_IN_A_WEEK);
            default -> from;
        };
    }

    public static Date getDate(String datetime) throws ParseException {
        return dateTimeFormat.parse(datetime);
    }

    public static String getDate(Date datetime) {
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

    public boolean isLate(String datetime) throws ParseException {
        Date date = getDate(datetime);
        if (from.equals("always")) return false;
        else
        {
            Date _from = getDate(from);
            return _from.after(date);
        }

    }
}
