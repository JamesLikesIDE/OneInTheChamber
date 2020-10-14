package net.jameslikeside;

import net.jameslikeside.commands.OneInTheChamberCommand;
import net.jameslikeside.data.Gamestate;
import net.jameslikeside.data.HashMapStorage;
import net.jameslikeside.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class OneInTheChamber extends JavaPlugin {

    public static OneInTheChamber instance; // gets the plugin
    FileConfiguration config = getConfig(); // configuration file
    public static String version = "§aVersion: §e0.1";

    @Override
    public void onEnable(){
        OneInTheChamber.instance = this;
        loadConfig();
        loadListeners();
        Gamestate.setGamestate(Gamestate.LOBBY);
        HashMapStorage.map.put("selectedMap", OneInTheChamber.getInstance().getConfig().getString("settings.selectedMap"));
        this.getCommand("oitc").setExecutor(new OneInTheChamberCommand());
    }

    public static OneInTheChamber getInstance(){
        return OneInTheChamber.instance;
    }

    // loads all listeners and events
    public void loadListeners(){
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new JoinListener(), this);
        pluginManager.registerEvents(new LeaveListener(), this);
        pluginManager.registerEvents(new KillListener(), this);
        pluginManager.registerEvents(new OtherListeners(), this);
        pluginManager.registerEvents(new RespawnListener(), this);
    }

    // creates default configuration file and load it if it exists
    public void loadConfig(){
        config.addDefault("messages.useMessages", true);
        config.addDefault("messages.join", "&7[&a+&7] &a{player}");
        config.addDefault("messages.leave", "&7[&c-&7] &c{player}");
        config.addDefault("messages.welcome", "&aWelcome &e{player} &ato One In The Chamber");
        config.addDefault("messages.kill", "&e{player} &awas killed by &e{killer}");
        config.addDefault("messages.death", "&e{player} &adied!");
        config.addDefault("messages.scoreBoard.displayName", "&6&lOITC");
        config.addDefault("messages.scoreBoard.serverName", "&6testserver.com");
        config.addDefault("messages.kickGameStartingStarted", "&cThe game is in &e{gamestate} &cGameState.");
        config.addDefault("messages.gameEnd.winnerMessage", "&aYou have one!");
        config.addDefault("messages.gameEnd.loserMessage", "&cDefeat");
        config.addDefault("messages.gameEnd.broadcastMessage", "&e{killer} &ais the winner!");
        config.addDefault("settings.startUpSequence.maxPlayers", 8);
        config.addDefault("settings.startUpSequence.countdownTime", 5);
        config.addDefault("settings.startUpSequence.countdownMessage", "&aStarting in &e{timeLeft}&a/&e{countdownTime}");
        config.addDefault("settings.maxKills", 25);
        config.addDefault("settings.items.swordName", "&6Iron Sword");
        config.addDefault("settings.items.bowName", "&6Bow");
        config.addDefault("settings.items.arrowName", "&fArrow");
        config.options().copyDefaults(true);
        saveConfig();
    }

}
