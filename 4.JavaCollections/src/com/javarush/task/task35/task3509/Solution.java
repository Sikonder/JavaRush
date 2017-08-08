package com.javarush.task.task35.task3509;

import java.security.Key;
import java.util.*;




/* 
Collections & Generics
*/
public class Solution {

    public static void main(String[] args) {
    }

    public  static <T> ArrayList<T> newArrayList(T... elements) {
        ArrayList<T> list = new ArrayList<T>();
        for(T o : elements){
            list.add(o);
        }
        return list;
    }

    public static <T> HashSet<T> newHashSet(T... elements) {
        HashSet<T> set = new HashSet<>();
        for(T o: elements){
            set.add(o);
        }
        return set;
    }



    public static <K,V> HashMap<K,V> newHashMap(List<? extends K> keys, List<? extends V> values) {
        if(keys.size()!=values.size()){
            throw new IllegalArgumentException();
        }
        HashMap<K,V> map = new HashMap<K, V>();
        for(int i = 0;i<keys.size();i++){
            map.put(keys.get(i),values.get(i));
        }
        return map;
    }
}
