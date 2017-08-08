package com.javarush.task.task37.task3702;

import com.javarush.task.task37.task3702.female.FemaleFactory;
import com.javarush.task.task37.task3702.male.MaleFactory;

/**
 * Created by Sikonder on 08.08.2017.
 */
public class FactoryProducer {
    public static enum HumanFactoryType{
        MALE, FEMALE;
    }
    public static AbstractFactory getFactory(HumanFactoryType type){
        AbstractFactory abstractFactory = null;
        if(type==HumanFactoryType.MALE){
            abstractFactory = new MaleFactory();
        }else if(type==HumanFactoryType.FEMALE){
            abstractFactory = new FemaleFactory();
        }
        return abstractFactory;
    }
}
