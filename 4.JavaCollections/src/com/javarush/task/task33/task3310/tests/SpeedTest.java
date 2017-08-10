package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * Created by Sikonder on 10.08.2017.
 */
public class SpeedTest {
    public long getTimeForGettingIds(Shortener shortener, Set<String> strings, Set<Long> ids){
        long start1 = new Date().getTime();
        for(String x : strings){
            ids.add(shortener.getId(x));
        }
        long finish1 = new Date().getTime();
        return finish1-start1;
    }

    public long getTimeForGettingStrings(Shortener shortener, Set<Long> ids, Set<String> strings){
        long start1 = new Date().getTime();
        for(Long x : ids){
            strings.add(shortener.getString(x));
        }
        long finish1 = new Date().getTime();
        return finish1-start1;
    }
    @Test
    public void testHashMapStorage(){
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());
        Set<String> origStrings = new HashSet<>();
        for(int i = 0;i<10000;i++){
            origStrings.add(Helper.generateRandomString());
        }
        Set<Long> ids1 = new HashSet<>();
        Set<Long> ids2 = new HashSet<>();
        long time1 = getTimeForGettingIds(shortener1, origStrings, ids1);
        long time2 = getTimeForGettingIds(shortener2, origStrings, ids2);
        Assert.assertTrue(time1 > time2);

        long time3 = getTimeForGettingStrings(shortener1, ids1, new HashSet<String>());
        long time4 = getTimeForGettingStrings(shortener2, ids2, new HashSet<String>());
        Assert.assertEquals(time3, time4, 30);
    }
}
