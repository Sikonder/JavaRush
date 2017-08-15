package com.javarush.task.task38.task3804;

/**
 * Created by sikonder on 13.08.17.
 */
public class Factory {
    public static Throwable getException(Enum e){
        if(e==null){
            return new IllegalArgumentException();
        }

        String message = e.name().charAt(0)+e.name().substring(1).toLowerCase().replace("_"," ");

        if(e instanceof ExceptionApplicationMessage){
            return new Exception(message);
        }else if(e instanceof ExceptionDBMessage){
            return new RuntimeException(message);
        }else if(e instanceof  ExceptionUserMessage){
            return new Error(message);
        }else
            return new IllegalArgumentException();
    }
}
