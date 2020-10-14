package net.jameslikeside.listeners;

import net.jameslikeside.OneInTheChamber;
import net.jameslikeside.data.Gamestate;
import net.jameslikeside.data.HashMapStorage;
import net.jameslikeside.data.Item;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

public class RespawnListener implements Listener {

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event){
        Player player = event.getPlayer();
        String mapName = HashMapStorage.map.get("selectedMap");

        if(HashMapStorage.map.get("selectedMap") == null){
            System.out.println("[One In The Chamber] Selected map is null, respawning at default location");
        }
        else {
            Location spawnLocation = new Location(Bukkit.getWorld(OneInTheChamber.getInstance().getConfig().getString("settings." + mapName + ".world")),
                    OneInTheChamber.getInstance().getConfig().getDouble("settings." + mapName + ".x"),
                    OneInTheChamber.getInstance().getConfig().getDouble("settings." + mapName + ".y"),
                    OneInTheChamber.getInstance().getConfig().getDouble("settings" + mapName + ".z"));
            spawnLocation.setPitch((float) OneInTheChamber.getInstance().getConfig().getDouble("settings." + mapName + ".pitch"));
            spawnLocation.setYaw((float) OneInTheChamber.getInstance().getConfig().getDouble("settings." + mapName + ".yaw"));
            event.setRespawnLocation(spawnLocation);
        }

        ItemStack s = new Item(Material.IRON_SWORD, 1).setDisplayName(OneInTheChamber.getInstance().getConfig().getString(
                "settings.items.swordName").replace("&", "§")).setUnbreakable(true).build();
        ItemStack b = new Item(Material.BOW, 1).setDisplayName(OneInTheChamber.getInstance().getConfig().getString(
                "settings.items.bowName").replace("&", "§")).setUnbreakable(true).build();
        ItemStack a = new Item(Material.ARROW, 1).setDisplayName(OneInTheChamber.getInstance().getConfig().getString(
                "settings.items.arrowName").replace("&", "§")).build();

        if(Gamestate.getCurrentGamestate() == Gamestate.INGAME){
            player.getInventory().clear();
            player.getInventory().setItem(0, s);
            player.getInventory().setItem(1, b);
            player.getInventory().setItem(17, a);
        }
        else {
            player.getInventory().clear();
        }
    }

}
