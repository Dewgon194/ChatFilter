package com.duggan.chatfilter;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StartFilter implements CommandExecutor {

    private Filters filters;

    public StartFilter(Filters filters) {
        this.filters = filters;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            String playerUUID = player.getUniqueId().toString();
            filters.filterStart(playerUUID);
        } else {
            System.out.println(ChatColor.RED + "This command cannot be ran from a console");
        }
        return true;
    }
}
