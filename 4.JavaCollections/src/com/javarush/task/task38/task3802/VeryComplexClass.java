package com.javarush.task.task38.task3802;

/* 
Проверяемые исключения (checked exception)
*/

import java.io.FileInputStream;

public class VeryComplexClass {
    public void veryComplexMethod() throws Exception {
        FileInputStream fis = null;

        fis = new FileInputStream("B:/myfile.txt");
        int k;


        while(( k = fis.read() ) != -1)
        {
            System.out.print((char)k);
        }


        fis.close();
    }

    public static void main(String[] args) {

    }
}
