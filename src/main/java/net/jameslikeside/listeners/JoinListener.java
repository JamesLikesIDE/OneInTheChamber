package net.jameslikeside.listeners;

import net.jameslikeside.OneInTheChamber;
import net.jameslikeside.data.*;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        ScoreboardManager.setScoreboard(player);
        if(Gamestate.getCurrentGamestate() == Gamestate.STARTING || Gamestate.getCurrentGamestate() == Gamestate.INGAME){
            player.kickPlayer(OneInTheChamber.getInstance().getConfig().getString("messages.kickGameStartingStarted").replace("&", "§").replace("{gamestate}", Gamestate.getCurrentGamestate().toString()));
        }

        if(OneInTheChamber.getInstance().getConfig().getBoolean("messages.useMessages", true)){
            event.setJoinMessage(OneInTheChamber.getInstance().getConfig().getString("messages.join").replace("&", "§").replace("{player}", player.getName()) + " §e" + Bukkit.getServer().getOnlinePlayers().size() + "§a/" + OneInTheChamber.getInstance().getConfig().getInt("settings.startUpSequence.maxPlayers"));
            player.sendMessage(OneInTheChamber.getInstance().getConfig().getString("messages.welcome").replace("&", "§").replace("{player}", player.getName()));
        }
        else {
            System.out.println("[One In The Chamber] Plugin messages are disabled");
        }

        if(Bukkit.getServer().getOnlinePlayers().size() >= OneInTheChamber.getInstance().getConfig().getInt("settings.startUpSequence.maxPlayers")){
            player.kickPlayer("§cGame is full");
        }

        if(Bukkit.getServer().getOnlinePlayers().size() >= OneInTheChamber.getInstance().getConfig().getInt("settings.startUpSequence.maxPlayers") - 1){

            String mapName = HashMapStorage.map.get("selectedMap");

            Location spawnLocation = new Location(Bukkit.getWorld(OneInTheChamber.getInstance().getConfig().getString("settings." + mapName + ".world")),
                    OneInTheChamber.getInstance().getConfig().getDouble("settings." + mapName + ".x"),
                    OneInTheChamber.getInstance().getConfig().getDouble("settings." + mapName + ".y"),
                    OneInTheChamber.getInstance().getConfig().getDouble("settings" + mapName + ".z"));
            spawnLocation.setPitch((float) OneInTheChamber.getInstance().getConfig().getDouble("settings." + mapName + ".pitch"));
            spawnLocation.setYaw((float) OneInTheChamber.getInstance().getConfig().getDouble("settings." + mapName + ".yaw"));

            new Countdown(OneInTheChamber.getInstance().getConfig().getInt("settings.startUpSequence.countdownTime"), OneInTheChamber.getInstance()){
                @Override
                public void count(int current) {
                    Bukkit.getServer().broadcastMessage(OneInTheChamber.getInstance().getConfig().getString("settings.startUpSequence.countdownMessage").replace("&", "§").replace("{timeLeft}", current + "").replace("{countdownTime}", OneInTheChamber.getInstance().getConfig().getInt("settings.startUpSequence.countdownTime") + ""));
                    if(current <= 0){

                        ItemStack s = new Item(Material.IRON_SWORD, 1).setDisplayName(OneInTheChamber.getInstance().getConfig().getString(
                                "settings.items.swordName").replace("&", "§")).setUnbreakable(true).build();
                        ItemStack b = new Item(Material.BOW, 1).setDisplayName(OneInTheChamber.getInstance().getConfig().getString(
                                "settings.items.bowName").replace("&", "§")).setUnbreakable(true).build();
                        ItemStack a = new Item(Material.ARROW, 1).setDisplayName(OneInTheChamber.getInstance().getConfig().getString(
                                "settings.items.arrowName").replace("&", "§")).build();

                        for(Player player : OneInTheChamber.getInstance().getServer().getOnlinePlayers()){
                            player.getInventory().clear();
                            player.getInventory().setItem(0, s);
                            player.getInventory().setItem(1, b);
                            player.getInventory().setItem(17, a);
                            player.setGameMode(GameMode.ADVENTURE);
                            player.teleport(spawnLocation);
                            player.setMaxHealth(2);
                            player.setHealth(2);
                            Gamestate.setGamestate(Gamestate.INGAME);
                        }
                    }
                }
            }.start();
        }

    }

}