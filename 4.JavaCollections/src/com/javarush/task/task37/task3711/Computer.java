package com.javarush.task.task37.task3711;

/**
 * Created by Sikonder on 12.08.2017.
 */
public class Computer {
    CPU cpu = new CPU();
    HardDrive hardDrive = new HardDrive();
    Memory memory = new Memory();

    public void run(){
        cpu.calculate();
        memory.allocate();
        hardDrive.storeData();
    }
}
