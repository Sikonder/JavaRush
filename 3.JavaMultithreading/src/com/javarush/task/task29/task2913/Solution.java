package com.javarush.task.task29.task2913;

import java.util.ArrayList;
import java.util.Random;

/* 
Замена рекурсии
*/

public class Solution {
    private static int numberA;
    private static int numberB;

    public static String getAllNumbersBetween(int a, int b) {
        ArrayList<Integer> list = new ArrayList<>();
        if(a<b){
            for (int i = a+1;i<=b;i++){
                list.add(i);
            }
        }else if(a>b) {
            for (int i = a-1; i >= b; i--) {
                list.add(i);
            }
        }else {
            list.add(a);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(a);
        for (Integer x:list){
            stringBuilder.append(" ").append(x);
        }
        return stringBuilder.toString();

    }

    public static void main(String[] args) {
        Random random = new Random();
        numberA = random.nextInt() % 1_000;
        numberB = random.nextInt() % 10_000;
        System.out.println(getAllNumbersBetween(numberA, numberB));
        System.out.println(getAllNumbersBetween(numberB, numberA));
    }
}