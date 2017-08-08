package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.Waiter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Sikonder on 21.07.2017.
 */
public class Restaurant {
    private static final int ORDER_CREATING_INTERVAL = 100; //final сука забыл указать!!! Сразу бы прошло
    private static final LinkedBlockingQueue<Order> QUEUE = new LinkedBlockingQueue<>();
    private static final LinkedBlockingQueue<Order> deliveryQueue = new LinkedBlockingQueue<>();;

    public static void main(String[] args) throws InterruptedException{
        Locale.setDefault(Locale.ENGLISH);
        Cook cookAmigo = new Cook("Amigo");
        cookAmigo.setQueue(QUEUE);
        cookAmigo.setDeliveryQueue(deliveryQueue);
        Cook cookDiego = new Cook("Deigo");
        cookDiego.setQueue(QUEUE);
        cookDiego.setDeliveryQueue(deliveryQueue);
        Waiter waitor = new Waiter();
        waitor.setDeliveryQueue(deliveryQueue);

        List<Tablet> tablets = new ArrayList<>();
        for(int i = 0;i < 5; i++){
            Tablet tablet = new Tablet(i + 1);
            tablet.setQueue(QUEUE);
            tablets.add(tablet);
        }



        Thread randomOrderGeneratorTaskThread = new Thread(new RandomOrderGeneratorTask(tablets, ORDER_CREATING_INTERVAL));
        randomOrderGeneratorTaskThread.start();
        try
        {
            Thread.sleep(1000);

        }
        catch (InterruptedException e)
        {

        }

        randomOrderGeneratorTaskThread.interrupt();

        while (!QUEUE.isEmpty()){
            Thread.sleep(1);
        }

        while ((cookAmigo.isBusy())||(cookDiego.isBusy())) { Thread.sleep(1);}


        while (!deliveryQueue.isEmpty()){
            Thread.sleep(1);
        }


        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();
    }
}
