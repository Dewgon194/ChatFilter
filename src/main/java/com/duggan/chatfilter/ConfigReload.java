package com.duggan.chatfilter;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class ConfigReload implements CommandExecutor {

    private Main main = Main.getInstance();
    Plugin plugin = Main.getPlugin(Main.class);

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            if (sender.hasPermission("indigo.command.chatfilter")) {
                if (args.length == 1 && args[0].equals("reload")) {
                    Main.getInstance().loadConfig();
                    plugin.reloadConfig();
                }
            } else {
                sender.sendMessage(ChatColor.RED + "You do not have permission!");
            }
        } else {
            if (args.length == 1 && args[0].equals("reload")) {
                Main.getInstance().loadConfig();
            }
        }
        return true;
    }
}