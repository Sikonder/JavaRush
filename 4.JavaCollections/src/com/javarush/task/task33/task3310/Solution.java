package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.*;


import java.util.*;

/**
 * Created by Sikonder on 10.08.2017.
 */
public class Solution {
    public static void main(String args[]){

        testStrategy(new HashMapStorageStrategy(),10000);
        testStrategy(new OurHashMapStorageStrategy(),10000);
        testStrategy(new FileStorageStrategy(),10);
        testStrategy(new OurHashBiMapStorageStrategy(),10000);
        testStrategy(new HashBiMapStorageStrategy(),10000);
        testStrategy(new DualHashBidiMapStorageStrategy(),10000);
    }
    public static Set<Long> getIds(Shortener shortener, Set<String> strings){
        Set<Long> set = new HashSet<>();
        for(String x : strings){
            set.add(shortener.getId(x));
        }
        return set;
    }
    public static Set<String> getStrings(Shortener shortener, Set<Long> keys){
        Set<String> set = new HashSet<>();
        for(Long x:keys){
            set.add(shortener.getString(x));
        }
        return set;
    }
    public static void testStrategy(StorageStrategy strategy, long elementsNumber){
        Helper.printMessage(strategy.getClass().getSimpleName());
        Set<String> list = new HashSet<>();
        for(int i = 0;i<elementsNumber;i++){
            list.add(Helper.generateRandomString());
        }

        Shortener shortener = new Shortener(strategy);
        long start = new Date().getTime();
        Set<Long> ids = getIds(shortener,list);
        long finish = new Date().getTime();
        long allForIDs = finish-start;
        Helper.printMessage(Long.toString(allForIDs));

        long start1 = new Date().getTime();
        Set<String> strings = getStrings(shortener,ids);
        long finish1 = new Date().getTime();
        long allForStrings = finish1-start1;
        Helper.printMessage(Long.toString(allForStrings));


        if(list.equals(strings)){
            Helper.printMessage("Тест пройден.");
        }else {
            Helper.printMessage("Тест не пройден.");
        }

    }
}
