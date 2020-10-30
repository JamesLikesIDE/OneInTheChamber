package net.jameslikeside.data;

import net.jameslikeside.OneInTheChamber;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

import java.text.DecimalFormat;

public class ScoreboardManagerIngame {

    public static void setScoreboard(Player player) {
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = board.registerNewObjective("dummy", "dummy");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName(OneInTheChamber.getInstance().getConfig().getString("messages.scoreBoard.displayName").replace("&", "§"));
        String playerName = player.getName();

        Score line00 = obj.getScore("§7 ");
        line00.setScore(11);
        Score line0 = obj.getScore("§f§lTime Elapsed:");
        line0.setScore(10);
        Team timeCounter = board.registerNewTeam("timeCounter");
        new BukkitRunnable() {
            @Override
            public void run() {
                int i = 0;
                ++i;
                timeCounter.addEntry("§4");
                timeCounter.setPrefix("§8» ");
                timeCounter.setSuffix("§e" + i);
                obj.getScore("§4").setScore(9);
            }
        }.runTaskTimer(OneInTheChamber.getInstance(), 0L, 20L);

        Score line1 = obj.getScore("§f ");
        line1.setScore(8);
        Score line2 = obj.getScore("§f§lKills:");
        line2.setScore(7);

        Team killCounter = board.registerNewTeam("killCounter");
        killCounter.addEntry(ChatColor.RED + "" + ChatColor.GRAY);
        new BukkitRunnable() {

            @Override
            public void run() {
                if (!player.isOnline()) {
                    cancel();
                }
                int amountOfKills = HashMapStorage.kills.get(playerName);
                DecimalFormat formatterOfKills = new DecimalFormat("#,###");
                killCounter.setPrefix("§8» ");
                killCounter.setSuffix("§a" + formatterOfKills.format(amountOfKills));
                obj.getScore(ChatColor.RED + "" + ChatColor.GRAY).setScore(6);
            }
        }.runTaskTimer(OneInTheChamber.getInstance(), 0L, 5L);

        Score line3 = obj.getScore("§5 ");
        line3.setScore(5);
        Score line4 = obj.getScore("§f§lDeaths:");
        line4.setScore(4);

        Team deathCounter = board.registerNewTeam("deathCounter");
        deathCounter.addEntry(ChatColor.WHITE + "" + ChatColor.YELLOW);
        new BukkitRunnable() {

            @Override
            public void run() {
                if (!player.isOnline()) {
                    cancel();
                }
                int amountOfDeaths = HashMapStorage.deaths.get(playerName);
                DecimalFormat formatterOfDeaths = new DecimalFormat("#,###");
                deathCounter.setPrefix("§8» ");
                deathCounter.setSuffix("§c" + formatterOfDeaths.format(amountOfDeaths));
                obj.getScore(ChatColor.WHITE + "" + ChatColor.YELLOW).setScore(3);
            }
        }.runTaskTimer(OneInTheChamber.getInstance(), 0L, 5L);

        Score line5 = obj.getScore("§7 ");
        line5.setScore(2);
        Score line6 = obj.getScore(OneInTheChamber.getInstance().getConfig().getString("messages.scoreBoard.serverName").replace("&", "§"));
        line6.setScore(1);

        player.setScoreboard(board);

    }
}
