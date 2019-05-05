package com.duggan.chatfilter;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public void onEnable() {
        System.out.println(ChatColor.AQUA + "ChatFilter is enabled");
        getCommand("chatfilter").setExecutor(new StartFilter(new Filters()));
    }

    private static Main instance;

    public static Main getInstance() {
        return instance;
    }
}
