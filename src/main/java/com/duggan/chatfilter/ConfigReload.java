package com.duggan.chatfilter;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ConfigReload implements CommandExecutor {

    private Main main = Main.getInstance();

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player && sender.hasPermission("indigo.command.chatfilter")) {
            if (args.length == 1 && args[0].equals("reload")) {
                main.loadConfig();
            }
        }
        return true;
    }
}
