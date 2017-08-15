package com.javarush.task.task20.task2025;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
Алгоритмы-числа
*/
public class Solution
{
    public static int[] getNumbers(int N)
    {
        int[] test = new int[N - 1];
        ArrayList<Integer>tempList=new ArrayList<>();
        for (int i = 0; i < test.length; i++)
        {
            test[i] = i + 1;
        }

        for (int i = 0; i < test.length; i++)
        {
            int M = (int) Math.log10(test[i]) + 1;
            int[]temp=counter(test[i]);
            int tempSum=0;
            for (int j=0; j<temp.length; j++){
                tempSum=tempSum+(int)Math.pow(temp[j],M);
            }
            if (test[i] == tempSum)
            {
                tempList.add(0,test[i]);
            }
        }
        Collections.sort(tempList);
        int[] result = new int[tempList.size()];
        for (int i=0; i<tempList.size(); i++){
            result[i]=tempList.get(i);
        }
        return result;
    }

    public static int[] counter(int x)
    {
        ArrayList<Integer>list=new ArrayList<>();
        int digitCounter;
        for (int i = 0; i < 10; i++)
        {
            int tmp = x;
            digitCounter = 0;
            while (tmp != 0)
            {
                int ost = tmp % 10;
                tmp /= 10;
                if (ost == i)
                {
                    digitCounter++;
                }
            }
            for (int j=digitCounter; j!=0;j--)
            {
                list.add(0, i);
            }
        }
        int array[]=new int[list.size()];
        for (int i=0; i<list.size(); i++){
            array[i]=list.get(i);
        }
        return array;
    }

    public static void main(String[] args)
    {
        int[]result = getNumbers(10000000);

    }
}
