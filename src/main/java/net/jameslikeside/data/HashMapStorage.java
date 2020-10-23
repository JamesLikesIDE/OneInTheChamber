package net.jameslikeside.data;

import net.jameslikeside.OneInTheChamber;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class HashMapStorage {

    // This class stores all the HashMap data

    public static<Key> void increment(Map<Key, Integer> map, Key key){
        map.putIfAbsent(key, 0);
        map.put(key, map.get(key) + 1);
    }

    public static<Key> void decrement(Map<Key, Integer> map, Key key){
        map.putIfAbsent(key, 0);
        map.put(key, map.get(key) - 1);
    }
    
    public static Map<String, Integer> kills = new HashMap<>();

    public static Map<String, Integer> deaths = new HashMap<>();

    public static Map<String, String> map = new HashMap<>();

    public static Map<String, Integer> offence = new HashMap<>();

    public static<Key> void incrementOffence(Map<Key, Integer> map, Key key){
        map.putIfAbsent(key, 1);
        map.put(key, map.get(key) + 1);
    }

    public static void addOffence(Player player){
        if(offence.get(player.getName()) >= 3){
            offence.put(player.getName(), 0);
            player.kickPlayer(" ENTER KICK MESSAGE HERE ");
        } else {
            incrementOffence(offence, player.getName());
        }
    }

}
