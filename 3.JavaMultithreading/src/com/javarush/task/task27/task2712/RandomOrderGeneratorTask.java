package com.javarush.task.task27.task2712;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sikonder on 30.07.2017.
 */
public class RandomOrderGeneratorTask implements Runnable
{
    private List<Tablet> tablets;
    private int interval;

    public RandomOrderGeneratorTask(List<Tablet> tablets, int interval)
    {
        this.tablets = tablets;
        this.interval = interval;
    }

    @Override
    public void run()
    {
        while (!Thread.currentThread().isInterrupted())
        {
            int number = (int) (Math.random() * tablets.size());
            tablets.get(number).createTestOrder();
            try
            {
                Thread.sleep(interval);
            }
            catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
            }
        }
    }
}
