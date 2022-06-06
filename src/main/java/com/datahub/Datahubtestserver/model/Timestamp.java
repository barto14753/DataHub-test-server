package com.datahub.Datahubtestserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Timestamp {
    private String from;// non datetime format options: always
    private String to; // non datetime format options: now
    private static final SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");

    private static final long MILLIS_IN_A_HOUR = 1000 * 60 * 60;
    private static final long MILLIS_IN_A_DAY = 1000 * 60 * 60 * 24;
    private static final long MILLIS_IN_A_WEEK = 1000 * 60 * 60 * 24 * 7;

    public Long FROM_MILLIS = null;
    public Long TO_MILLIS = null;
    public boolean fromNow = false;
    public Timestamp(
            @JsonProperty("from") String from,
            @JsonProperty("to") String to)
    {
        try {
            this.from = getFrom(from,to);
            if(!from.equals("always")){
                this.FROM_MILLIS = Timestamp.dateTimeFormat.parse(this.from).getTime();
            }
            this.to = to;
            if(!to.equals("now")){
                this.TO_MILLIS = Timestamp.dateTimeFormat.parse(this.to).getTime();
            }
            else{
                this.fromNow = true;
                this.TO_MILLIS = new Date().getTime();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private String getFrom(String from, String to) throws ParseException {
        long millis= new Date().getTime();
        if(!to.equals("now")){
            millis = Timestamp.dateTimeFormat.parse(to).getTime();
            System.out.println(Timestamp.dateTimeFormat.parse(to).toString());
        }
        return switch (from) {
            case "hour" -> Timestamp.dateTimeFormat.format(millis - MILLIS_IN_A_HOUR);
            case "day" -> Timestamp.dateTimeFormat.format(millis - MILLIS_IN_A_DAY);
            case "week" -> Timestamp.dateTimeFormat.format(millis - MILLIS_IN_A_WEEK);
            case "month" -> Timestamp.dateTimeFormat.format(millis - 4 * MILLIS_IN_A_WEEK);
            default -> from;
        };
    }

    public static Date getDate(String datetime) throws ParseException {
        return dateTimeFormat.parse(datetime);
    }

    public static String getDate(Date datetime) {
        return dateTimeFormat.format(datetime);
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

    public TimePeriodSelection getTimePeriodSelection(String recordTimestamp) throws ParseException {
        long millisRecord = Timestamp.dateTimeFormat.parse(recordTimestamp).getTime();
        long diff = TO_MILLIS - millisRecord;

        if (diff < MILLIS_IN_A_HOUR) return TimePeriodSelection.HOUR;
        else if (diff < MILLIS_IN_A_DAY) return TimePeriodSelection.DAY;
        else if (diff < MILLIS_IN_A_WEEK) return TimePeriodSelection.WEEK;
        else if (diff < MILLIS_IN_A_WEEK * 4) return TimePeriodSelection.MONTH;
        else return TimePeriodSelection.MORE;
    }

}