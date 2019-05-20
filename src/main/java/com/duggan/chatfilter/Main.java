package com.duggan.chatfilter;

import com.duggan.chatfilter.Listeners.PlayerChatListener;
import me.lucko.luckperms.api.LuckPermsApi;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class Main extends JavaPlugin {

    public static LuckPermsApi luckPerms;

    private static Main instance;

    public void onEnable() {
        System.out.println(ChatColor.AQUA + "ChatFilter is enabled");
        getCommand("chatfilter").setExecutor(new StartFilter(new Filters()));
        getServer().getPluginManager().registerEvents(new PlayerChatListener(), this);
        loadConfig();
        luckPerms = getServer().getServicesManager().getRegistration(LuckPermsApi.class).getProvider();
        instance = this;
    }

    public static Main getInstance() {
        return instance;
    }

    public File wordsFile;

    public FileConfiguration words;

    public void loadConfig() {
        // Custom Config
        wordsFile = new File(getDataFolder(), "words.yml");
        if (!wordsFile.exists()) {
            wordsFile.getParentFile().mkdirs();
            saveResource("words.yml", false);
        }

        words = new YamlConfiguration();
        try {
            words.load(wordsFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getWordsConfig() {
        return words;
    }

    public File getWordFile() {
        return wordsFile;
    }

}
