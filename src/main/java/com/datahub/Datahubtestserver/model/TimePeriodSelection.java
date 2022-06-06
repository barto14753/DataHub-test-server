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
                return 2;
            }
            case WEEK -> {
                return 8;
            }
            case MONTH -> {
                return 15;
            }
            default -> {
                return 25;
            }
        }
    }
}
