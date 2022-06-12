package com.datahub.Datahubtestserver.model;

public enum TimePeriodSelection {
    HOUR, DAY, WEEK, MONTH, MORE;

    public int getSelectionRating()
    {
        switch (this)
        {
            case HOUR -> {
                return 1;
            }
            case DAY -> {
                return 5;
            }
            case WEEK -> {
                return 10;
            }
            case MONTH -> {
                return 20;
            }
            default -> {
                return 40;
            }
        }
    }
}
