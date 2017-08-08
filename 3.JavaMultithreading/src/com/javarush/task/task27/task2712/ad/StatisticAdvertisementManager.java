package com.javarush.task.task27.task2712.ad;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Sikonder on 30.07.2017.
 */
public class StatisticAdvertisementManager {
    private static StatisticAdvertisementManager ourInstance = new StatisticAdvertisementManager();

    public static StatisticAdvertisementManager getInstance() {
        return ourInstance;
    }

    private StatisticAdvertisementManager() {
    }
    AdvertisementStorage storage = AdvertisementStorage.getInstance();

    public List<Advertisement> getVideoSet(boolean isActive){
        List<Advertisement> advertisements = new ArrayList<>();
        for(Advertisement advertisement : storage.list()){
            if(!isActive&&advertisement.getHits()==0){
                advertisements.add(advertisement);
            }
            if(isActive&&advertisement.getHits()>=1){
                advertisements.add(advertisement);
            }
        }
        Collections.sort(advertisements, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
        return advertisements;
    }
}
