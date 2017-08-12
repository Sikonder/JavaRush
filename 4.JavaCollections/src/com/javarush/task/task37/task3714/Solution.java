package com.javarush.task.task37.task3714;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;

/* 
Древний Рим
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input a roman number to be converted to decimal: ");
        String romanString = bufferedReader.readLine();
        System.out.println("Conversion result equals " + romanToInteger(romanString));
    }

    public static int romanToInteger(String s) {
        Hashtable<Character, Integer> ht = new Hashtable<Character, Integer>();
        ht.put('I',1);
        ht.put('X',10);
        ht.put('C',100);
        ht.put('M',1000);
        ht.put('V',5);
        ht.put('L',50);
        ht.put('D',500);
        int intNum=0;
        int prev = 0;
        for(int i = s.length()-1; i>=0 ; i--)
        {
            int temp = ht.get(s.charAt(i));
            if(temp < prev)
                intNum-=temp;
            else
                intNum+=temp;
            prev = temp;
        }
        return intNum;
    }
}
