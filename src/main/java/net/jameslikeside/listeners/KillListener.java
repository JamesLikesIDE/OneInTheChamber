package net.jameslikeside.listeners;

import net.jameslikeside.OneInTheChamber;
import net.jameslikeside.data.HashMapStorage;
import net.jameslikeside.data.Item;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class KillListener implements Listener {

    // This class holds the death event and what to do when a player is killed by the others

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        final Player player = event.getEntity();
        final Player killer = event.getEntity().getKiller();

        if (event.getEntity().getKiller() instanceof Player) {
            event.setDeathMessage(OneInTheChamber.getInstance().getConfig().getString("messages.kill").replace("&", "§").replace("{player}", player.getName()).replace("{killer}", killer.getName()));

            if(HashMapStorage.kills.get(killer.getName()) >= OneInTheChamber.getInstance().getConfig().getInt("settings.maxKills")){
                for(Player all : Bukkit.getServer().getOnlinePlayers()){
                    all.setGameMode(GameMode.SPECTATOR);
                    all.sendMessage(OneInTheChamber.getInstance().getConfig().getString("messages.gameEnd.loserMessage").replace("&", "§"));
                    Bukkit.getServer().broadcastMessage(OneInTheChamber.getInstance().getConfig().getString("messages.gameEnd.broadcastMessage").replace("&", "§").replace("{killer}", killer.getName()));
                    HashMapStorage.kills.put(all.getName(), 0);
                    HashMapStorage.deaths.put(all.getName(), 0);
                }
                killer.sendMessage(OneInTheChamber.getInstance().getConfig().getString("messages.gameEnd.winnerMessage").replace("&", "§"));

            }
            ItemStack a = new Item(Material.ARROW, 1).setDisplayName(OneInTheChamber.getInstance().getConfig().getString(
                    "settings.items.arrowName").replace("&", "§")).build();
            player.getInventory().addItem(a);
            HashMapStorage.increment(HashMapStorage.kills, killer.getName());
            HashMapStorage.increment(HashMapStorage.deaths, player.getName());
            player.sendMessage(HashMapStorage.deaths.get(player.getName()).toString());
            killer.sendMessage(HashMapStorage.kills.get(killer.getName()).toString());
        } else if (!(event.getEntity().getKiller() instanceof Player)) {
            event.setDeathMessage(OneInTheChamber.getInstance().getConfig().getString("messages.death").replace("&", "§").replace("{player}", player.getName()));
        }
    }
}

