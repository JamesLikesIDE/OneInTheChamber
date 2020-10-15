package net.jameslikeside.commands;

import net.jameslikeside.OneInTheChamber;
import net.jameslikeside.data.Countdown;
import net.jameslikeside.data.Gamestate;
import net.jameslikeside.data.HashMapStorage;
import net.jameslikeside.data.Item;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class OneInTheChamberCommand implements CommandExecutor {

    // This class holds the command /oitc information
    // I can add any // in this if it is too complicated

    @Override
    public boolean onCommand(CommandSender s, Command c, String string, String[] args) {
        if (s instanceof Player) {
            Player player = ((Player) s).getPlayer();
            double xLevel = player.getLocation().getX();
            double yLevel = player.getLocation().getY();
            double zLevel = player.getLocation().getZ();
            double pitch = player.getLocation().getPitch();
            double yaw = player.getLocation().getYaw();
            String playerWorld = player.getWorld().getName();
            if (player.hasPermission("oitc.admin")) {
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("start")) {
                        startCountdown();
                        Gamestate.setGamestate(Gamestate.STARTING);
                    }
                    else if (args[0].equalsIgnoreCase("getGameState")) {
                        player.sendMessage(Gamestate.getCurrentGamestate().toString());
                    }
                    else if (args[0].equalsIgnoreCase("getMap")) {
                        HashMapStorage.map.putIfAbsent("selectedMap", "none");
                        player.sendMessage(HashMapStorage.map.get("selectedMap"));
                    }
                }
                else if (args.length == 2) {
                    if (args[0].equalsIgnoreCase("setMap")) {
                        String mapName = args[1];
                        OneInTheChamber.getInstance().getConfig().set("settings.selectedMap", mapName);
                        OneInTheChamber.getInstance().getConfig().set("settings." + mapName + ".world", playerWorld);
                        OneInTheChamber.getInstance().getConfig().set("settings." + mapName + ".worldName", mapName);
                        OneInTheChamber.getInstance().getConfig().set("settings." + mapName + ".x", xLevel);
                        OneInTheChamber.getInstance().getConfig().set("settings." + mapName + ".y", yLevel);
                        OneInTheChamber.getInstance().getConfig().set("settings." + mapName + ".z", zLevel);
                        OneInTheChamber.getInstance().getConfig().set("settings." + mapName + ".pitch", pitch);
                        OneInTheChamber.getInstance().getConfig().set("settings." + mapName + ".yaw", yaw);
                        OneInTheChamber.getInstance().saveConfig();

                        HashMapStorage.map.put("selectedMap", mapName);
                        player.sendMessage("§aGame location successfully set!");
                    } else if (args[0].equalsIgnoreCase("selectMap")) {
                        String mapName = args[1];
                        HashMapStorage.map.put("selectedMap", mapName);
                        player.sendMessage("§aYou have selected map §e" + mapName);
                        OneInTheChamber.getInstance().getConfig().set("settings.selectedMap", mapName);
                        OneInTheChamber.getInstance().saveConfig();
                    }
                    else if(args[0].equalsIgnoreCase("setGameState")){
                        String gameState = args[1];
                        if(gameState.equalsIgnoreCase("lobby")){
                            Gamestate.setGamestate(Gamestate.LOBBY);
                        }
                        else if(gameState.equalsIgnoreCase("starting")){
                            Gamestate.setGamestate(Gamestate.STARTING);
                        }
                        else if(gameState.equalsIgnoreCase("ingame")){
                            Gamestate.setGamestate(Gamestate.INGAME);
                        }
                        else if(gameState.equalsIgnoreCase("ended")){
                            Gamestate.setGamestate(Gamestate.ENDED);
                        }
                        else {
                            player.sendMessage("§cUnknown GameState. §eAvailable GameStates: §aLobby, Starting, ingame, ended");
                        }
                    }
                }
                else {
                    player.sendMessage("§cUnknown command!");
                    player.sendMessage("§e/oitc help §a(Shows all available commands");
                    player.sendMessage("§e/oitc setMap <mapName> §a(Adds a new map to the config");
                    player.sendMessage("§e/oitc selectMap <mapName> §a(Selects a map to start with when the game starts (chosen from config))");
                    player.sendMessage("§e/oitc start §a(Starts the game)");
                    player.sendMessage("§e/oitc getGameState §a(Gets the current GameState of the server)");
                    player.sendMessage("§e/oitc setGameState §a(Set the GameState of the server)");
                    player.sendMessage("§e/oitc getMap §a(Gets the current selected map)");
                    player.sendMessage("§e/oitc leave §a(Returns you back to the lobby (if BungeeCord is the proxy connected))");
                    player.sendMessage("§e/oitc version §a(Displays the current version)");
                }
            }
            if(args.length == 1){
                if(args[0].equalsIgnoreCase("leave")){
                    player.kickPlayer("§cYou have left the game!");
                }
                else if(args[0].equalsIgnoreCase("version")){
                    player.sendMessage(OneInTheChamber.version);
                }
                else if(args[0].equalsIgnoreCase("help")){
                    player.sendMessage("§e/oitc leave §a(Returns you back to the lobby (if BungeeCord is the proxy connected))");
                    player.sendMessage("§e/oitc version §a(Displays the current version)");
                }
            }
        }
        return false;
    }

    private void startCountdown(){

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
