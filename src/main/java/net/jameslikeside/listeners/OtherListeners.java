package net.jameslikeside.listeners;

import net.jameslikeside.data.Gamestate;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;

public class OtherListeners implements Listener {

    // This class holds any other listeners that are not compulsory but i like to have

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        Player player = event.getPlayer();
        if(player.getGameMode() == GameMode.CREATIVE){
            event.setCancelled(false);
        }
        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event){
        Player player = event.getPlayer();
        if(player.getGameMode() == GameMode.CREATIVE){
            event.setCancelled(false);
        }
        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockGrow(BlockGrowEvent event){
        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockBurn(BlockBurnEvent event){
        event.setCancelled(true);
    }

    @EventHandler
    public void onBedEnter(PlayerBedEnterEvent event){
        event.setCancelled(true);
    }

    @EventHandler
    public void onHit(EntityDamageEvent event){
        if(Gamestate.getCurrentGamestate() == Gamestate.LOBBY || Gamestate.getCurrentGamestate() == Gamestate.STARTING || Gamestate.getCurrentGamestate() == Gamestate.ENDED){
            event.setCancelled(true);
        }
    }

}
