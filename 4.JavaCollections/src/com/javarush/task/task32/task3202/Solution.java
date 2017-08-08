package com.javarush.task.task32.task3202;


import java.io.*;
import java.util.Scanner;

/* 
Читаем из потока
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        StringWriter writer = getAllDataFromInputStream(new FileInputStream("testFile.log"));
        System.out.println(writer.toString());
    }

    public static StringWriter getAllDataFromInputStream(InputStream is) throws IOException {
        StringWriter writer = new StringWriter();
        try{
            while (is.available()>0){
                byte[] buffer = new byte[1024];
                int len = is.read(buffer);
                String s = new String(buffer,0,len);
                writer.append(s);
            }
        }catch (Exception e){
            return new StringWriter();
        }
        return writer;

    }
}