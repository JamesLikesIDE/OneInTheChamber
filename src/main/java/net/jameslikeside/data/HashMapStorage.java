package net.jameslikeside.data;

import java.util.HashMap;
import java.util.Map;

public class HashMapStorage {

    // This class stores all the HashMap data

    public static<Key> void increment(Map<Key, Integer> map, Key key){
        map.putIfAbsent(key, 0);
        map.put(key, map.get(key) + 1);
    }

    public static Map<String, Integer> kills = new HashMap<>();

    public static Map<String, Integer> deaths = new HashMap<>();

    public static Map<String, String> map = new HashMap<>();

}
