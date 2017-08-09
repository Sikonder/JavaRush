package com.javarush.task.task36.task3601;

/**
 * Created by sikonder on 09.08.17.
 */
public class View {
    public void fireEventShowData() {
        System.out.println(new Controller().onDataListShow());
    }
}
