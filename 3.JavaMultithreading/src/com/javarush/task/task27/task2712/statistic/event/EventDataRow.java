package com.javarush.task.task27.task2712.statistic.event;

import java.util.Date;

/**
 * Created by Sikonder on 24.07.2017.
 */
public interface EventDataRow {
    EventType getType();
    Date getDate();
    int getTime();
}
