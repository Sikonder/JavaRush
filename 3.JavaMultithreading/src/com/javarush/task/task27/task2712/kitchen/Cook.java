package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Sikonder on 21.07.2017.
 */
public class Cook extends Observable implements Observer {
    private String name;
    private LinkedBlockingQueue<Order> queue;
    private volatile boolean busy;
    private LinkedBlockingQueue<Order> deliveryQueue;

    public void setQueue(LinkedBlockingQueue<Order> queue)
    {
        this.queue = queue;
    }


    public Cook(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable observable, Object arg) {
        Order order = (Order) arg;
        Tablet tablet = order.getTablet();
        StatisticManager.getInstance().register(new CookedOrderEventDataRow(tablet.toString(),this.toString(),order.getTotalCookingTime()*60,order.getDishes()));
        ConsoleHelper.writeMessage("Start cooking - " + order + ", cooking time " + order.getTotalCookingTime() + "min");
        setChanged();
        notifyObservers(arg);

    }

    @Override
    public String toString() {
        return name;
    }


    public boolean isBusy() {
        return busy;
    }

    public void setDeliveryQueue(LinkedBlockingQueue<Order> deliveryQueue) {
        this.deliveryQueue = deliveryQueue;
    }
}
