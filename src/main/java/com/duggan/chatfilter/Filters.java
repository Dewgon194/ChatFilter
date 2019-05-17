package com.duggan.chatfilter;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Filters {

    public void saveFilters(String playerUUID) {

        Plugin plugin = Main.getPlugin(Main.class);

        plugin.getConfig().set("Filters." + playerUUID, "on");
        plugin.saveConfig();
    }

    public void stopFilters(String playerUUID) {

        Plugin plugin = Main.getPlugin(Main.class);

        plugin.getConfig().set("Filters." + playerUUID, "off");
        plugin.saveConfig();
    }

    public void filterStart(String playerUUID) {

        Plugin plugin = Main.getPlugin(Main.class);

        String filter = String.valueOf(plugin.getConfig().getString("Filters." + playerUUID));
        if (filter.equals("on")) {
            stopFilters(playerUUID);
            Player player = Bukkit.getPlayer(UUID.fromString(playerUUID));
            player.sendMessage(ChatColor.AQUA + "The Chat Filter is now disabled");
        } else if (filter.equals("off") || plugin.getConfig().getConfigurationSection("Filters." + playerUUID) == null) {
            saveFilters(playerUUID);
            Player player = Bukkit.getPlayer(UUID.fromString(playerUUID));
            player.sendMessage(ChatColor.AQUA + "The Chat Filter is now enabled");

        }
    }
}
