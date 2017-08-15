package com.javarush.task.task38.task3803;

/* 
Runtime исключения (unchecked exception)
*/

public class VeryComplexClass {
    public void methodThrowsClassCastException() throws ClassCastException{
        Object i = Integer.valueOf(42);
        String s = (String)i;
    }

    public void methodThrowsNullPointerException() throws NullPointerException{
        Object obj = null;
        obj.hashCode();
    }

    public static void main(String[] args) {

    }
}
