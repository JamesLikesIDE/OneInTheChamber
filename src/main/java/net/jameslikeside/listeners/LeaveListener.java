package net.jameslikeside.listeners;

import net.jameslikeside.OneInTheChamber;
import net.jameslikeside.data.HashMapStorage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LeaveListener implements Listener {

    @EventHandler
    public void onLeave(PlayerQuitEvent event){
        Player player = event.getPlayer();
        HashMapStorage.kills.put(player.getName(), 0);
        HashMapStorage.deaths.put(player.getName(), 0);
        if(OneInTheChamber.getInstance().getConfig().getBoolean("messages.useMessages", true)){
            event.setQuitMessage(OneInTheChamber.getInstance().getConfig().getString("messages.leave").replace("&", "§").replace("{player}", player.getName()));
        }
        else {
            System.out.println("[One In The Chamber] Plugin messages are disabled");
        }
    }

}
