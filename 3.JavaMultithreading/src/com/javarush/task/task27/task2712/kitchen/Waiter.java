package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Sikonder on 21.07.2017.
 */
public class Waiter implements Observer {
    private LinkedBlockingQueue<Order> deliveryQueue;
    @Override
    public void update(Observable cook, Object order) {
        ConsoleHelper.writeMessage(order + " was cooked by " + cook);
    }

    public void setDeliveryQueue(LinkedBlockingQueue<Order> deliveryQueue) {
        this.deliveryQueue = deliveryQueue;
    }
}
