package net.jameslikeside.listeners;

import de.dytanic.cloudnet.api.CloudAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Chat implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onChat(final AsyncPlayerChatEvent e) throws SQLException {
        final Player p = e.getPlayer();
        String msg = e.getMessage();
        msg = msg.replaceAll("%", "Prozent");
        msg = msg.replaceAll("&", "§");
        msg = msg.replaceAll("#und#", "&");
        msg = msg.replaceAll("#and#", "&");
        msg = msg.replaceAll("#>>#", "»");
        msg = msg.replaceAll("#<<#", "«");
        msg = msg.replaceAll("#online#", new StringBuilder().append(Bukkit.getServer().getOnlinePlayers().size()).toString());
        msg = msg.replaceAll("#max#", new StringBuilder().append(Bukkit.getServer().getMaxPlayers()).toString());
        msg = msg.replaceAll("#:#", "\u2503");
        msg = msg.replaceAll("#.#", "\u25cf");
        msg = msg.replaceAll("#true#", "\u2714");
        msg = msg.replaceAll("#false#", "\u2718");
        msg = msg.replaceAll("#u#", "\u00fc");
        msg = msg.replaceAll("#o#", "\u00f6\u2018");
        msg = msg.replaceAll("#a#", "\u00e4\u2018");
        msg = msg.replaceAll("#U#", "\u00dc");
        msg = msg.replaceAll("#O#", "\u00d6");
        msg = msg.replaceAll("#A#", "\u00c4");
        msg = msg.replaceAll("ez", "gg");
        String msgk = e.getMessage();
        msgk = msgk.replaceAll("%", "Prozent");
        msgk = msgk.replaceAll("#und#", "&");
        msgk = msgk.replaceAll("#and#", "&");
        msgk = msgk.replaceAll("#>>#", "»");
        msgk = msgk.replaceAll("#<<#", "«");
        msgk = msgk.replaceAll("#online#", new StringBuilder().append(Bukkit.getServer().getOnlinePlayers().size()).toString());
        msgk = msgk.replaceAll("#max#", new StringBuilder().append(Bukkit.getServer().getMaxPlayers()).toString());
        msgk = msgk.replaceAll("#:#", "\u2503");
        msgk = msgk.replaceAll("#.#", "\u25cf");
        msgk = msgk.replaceAll("#true#", "\u2714");
        msgk = msgk.replaceAll("#false#", "\u2718");
        msgk = msgk.replaceAll("#u#", "\u00fc");
        msgk = msgk.replaceAll("#o#", "\u00f6\u2018");
        msgk = msgk.replaceAll("#a#", "\u00e4\u2018");
        msgk = msgk.replaceAll("#U#", "\u00dc");
        msgk = msgk.replaceAll("#O#", "\u00d6");
        msgk = msgk.replaceAll("#A#", "\u00c4");
        msgk = msgk.replaceAll("ez", "gg");
        String msgv = e.getMessage();
        msgv = msgv.replaceAll("&", "§");
        msgv = msgv.replaceAll("%", "Prozent");

        String msgu = e.getMessage();
        msgu = msgu.replaceAll("%", "Prozent");
        final String uuid = p.getUniqueId().toString();
        String message;
        if (p.hasPermission("chat.color")) {
            message = msgv;
        }
        else if (p.hasPermission("chat.symbole")) {
            message = msgk;
        }
        else if (p.hasPermission("chat.symbole") && p.hasPermission("chat.color")) {
            message = msg;
        }
        else {
            message = msgu;
        }
        if (CloudAPI.getInstance().getOnlinePlayer(p.getUniqueId()).getPermissionEntity().isInGroup("Owner")) {
            e.setFormat("§c§lOwner §r§7" + p.getName() + "§8 » §r" + msg);
        }
        else if (CloudAPI.getInstance().getOnlinePlayer(p.getUniqueId()).getPermissionEntity().isInGroup("Manager")) {
            e.setFormat("§cManager §r§7" + p.getName() + "§8 » §r" + msg);
        }
        else if (CloudAPI.getInstance().getOnlinePlayer(p.getUniqueId()).getPermissionEntity().isInGroup("Admin")) {
            e.setFormat("§cAdmin §r§7" + p.getName() + "§8 » §r" + msg);
        }
        else if (CloudAPI.getInstance().getOnlinePlayer(p.getUniqueId()).getPermissionEntity().isInGroup("SrDev")) {
            e.setFormat("§bSrDev §r§7" + p.getName() + "§8 » §r" + msg);
        }
        else if (CloudAPI.getInstance().getOnlinePlayer(p.getUniqueId()).getPermissionEntity().isInGroup("Dev")) {
            e.setFormat("§bDev §r§7" + p.getName() + "§8 » §r" + msg);
        }
        else if (CloudAPI.getInstance().getOnlinePlayer(p.getUniqueId()).getPermissionEntity().isInGroup("T-Dev")) {
            e.setFormat("§bT-Dev §r§7" + p.getName() + "§8 » §r" + msg);
        }
        else if (CloudAPI.getInstance().getOnlinePlayer(p.getUniqueId()).getPermissionEntity().isInGroup("SrMod")) {
            e.setFormat("§5SrMod §r§7" + p.getName() + "§8 » §r" + msg);
        }
        else if (CloudAPI.getInstance().getOnlinePlayer(p.getUniqueId()).getPermissionEntity().isInGroup("Mod")) {
            e.setFormat("§5Mod §r§7" + p.getName() + "§8 » §r" + msg);
        }
        else if (CloudAPI.getInstance().getOnlinePlayer(p.getUniqueId()).getPermissionEntity().isInGroup("T-Mod")) {
            e.setFormat("§5T-Mod §r§7" + p.getName() + "§8 » §r" + msg);
        }
        else if (CloudAPI.getInstance().getOnlinePlayer(p.getUniqueId()).getPermissionEntity().isInGroup("SrBuilder")) {
            e.setFormat("§6SrBuilder §r§7" + p.getName() + "§8 » §r" + msg);
        }
        else if (CloudAPI.getInstance().getOnlinePlayer(p.getUniqueId()).getPermissionEntity().isInGroup("Builder")) {
            e.setFormat("§6Builder §r§7" + p.getName() + "§8 » §r" + msg);
        }
        else if (CloudAPI.getInstance().getOnlinePlayer(p.getUniqueId()).getPermissionEntity().isInGroup("T-Builder")) {
            e.setFormat("§6T-Builder §r§7" + p.getName() + "§8 » §r" + msg);
        }
        else if (CloudAPI.getInstance().getOnlinePlayer(p.getUniqueId()).getPermissionEntity().isInGroup("Helper")) {
            e.setFormat("§9Helper §r§7" + p.getName() + "§8 » §r" + msg);
        }
        else if (CloudAPI.getInstance().getOnlinePlayer(p.getUniqueId()).getPermissionEntity().isInGroup("T-Helper")) {
            e.setFormat("§9T-Helper §r§7" + p.getName() + "§8 » §r" + msg);
        }
        else if (CloudAPI.getInstance().getOnlinePlayer(p.getUniqueId()).getPermissionEntity().isInGroup("YT")) {
            e.setFormat("§5YT §r§7" + p.getName() + "§8 » §r" + msg);
        }
        else if (CloudAPI.getInstance().getOnlinePlayer(p.getUniqueId()).getPermissionEntity().isInGroup("Friend")) {
            e.setFormat("§eFriend §r§7" + p.getName() + "§8 » §r" + message);
        }
        else if (CloudAPI.getInstance().getOnlinePlayer(p.getUniqueId()).getPermissionEntity().isInGroup("Emerald")) {
            e.setFormat("§aEmerald §r§7" + p.getName() + "§8 » §r" + message);
        }
        else if (CloudAPI.getInstance().getOnlinePlayer(p.getUniqueId()).getPermissionEntity().isInGroup("Diamond")) {
            e.setFormat("§3Diamond §r§7" + p.getName() + "§8 » §r" + message);
        }
        else if (CloudAPI.getInstance().getOnlinePlayer(p.getUniqueId()).getPermissionEntity().isInGroup("Gold")) {
            e.setFormat("§6Gold §r§7" + p.getName() + "§8 » §r" + message);
        }
        else if (CloudAPI.getInstance().getOnlinePlayer(p.getUniqueId()).getPermissionEntity().isInGroup("Iron")) {
            e.setFormat("§8Iron §r§7" + p.getName() + "§8 » §r" + message);
        }
        else if (CloudAPI.getInstance().getOnlinePlayer(p.getUniqueId()).getPermissionEntity().isInGroup("Coal")) {
            e.setFormat("§0Coal §r§7" + p.getName() + "§8 » §r" + message);
        }
        else if (CloudAPI.getInstance().getOnlinePlayer(p.getUniqueId()).getPermissionEntity().isInGroup("default")) {
            e.setFormat("§7" + p.getName() + "§8 » §r" + message);
        }
    }
}

