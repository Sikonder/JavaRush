package com.javarush.task.task27.task2712.ad;

/**
 * Created by Sikonder on 21.07.2017.
 */
public class Advertisement {
    private Object content;
    private String name;            //  - имя/название
    private long initialAmount;     //  - начальная сумма, стоимость рекламы в копейках. Используем long, чтобы избежать проблем с округлением
    private int hits;               //  - количество оплаченных показов
    private int duration;           //  - продолжительность в секундах
    private long amountPerOneDisplaying;

    public String getName() {
        return name;
    }

    public long getAmountPerOneDisplaying() {
        return amountPerOneDisplaying;
    }

    public double getAmountPerSecond() {
        return (double)amountPerOneDisplaying / duration;
    }

    public int getDuration() {
        return duration;
    }

    public int getHits() {
        return hits;
    }

    public Advertisement(Object content, String name, long initialAmount, int hits, int duration) {
        this.content = content;
        this.name = name;
        this.initialAmount = initialAmount;
        this.hits = hits;
        this.duration = duration;
        amountPerOneDisplaying = initialAmount / hits;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void revalidate() {
        if (hits <= 0)
            throw new UnsupportedOperationException();
        hits--;
    }
}