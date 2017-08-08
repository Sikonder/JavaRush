package com.javarush.task.task27.task2712.statistic.event;

import java.util.Date;

/**
 * Created by Sikonder on 24.07.2017.
 */
public class NoAvailableVideoEventDataRow  implements EventDataRow {
    private Date currentDate;
    private int totalDuration;


    public Date getDate() {
        return currentDate;
    }


    public int getTime() {
        return totalDuration;
    }

    public NoAvailableVideoEventDataRow(int totalDuration) {
        this.totalDuration = totalDuration;
        this.currentDate = new Date();
    }


    public EventType getType() {
        return EventType.NO_AVAILABLE_VIDEO;
    }
}
