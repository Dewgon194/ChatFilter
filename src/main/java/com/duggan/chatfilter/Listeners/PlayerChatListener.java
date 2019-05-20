package com.duggan.chatfilter.Listeners;

import com.duggan.chatfilter.Prefix;
import com.duggan.chatfilter.Main;
import com.duggan.chatfilter.ReplacementWords;
import me.lucko.luckperms.LuckPerms;
import me.lucko.luckperms.api.Contexts;
import me.lucko.luckperms.api.LuckPermsApi;
import me.lucko.luckperms.api.caching.UserData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerChatListener implements Listener {

    private Main main;
    private Prefix prefix;
    private ReplacementWords replacements;
    public PlayerChatListener(ReplacementWords replacements) {this.replacements = replacements;}
    public PlayerChatListener(Main main) {
        this.main = main;
    }

    public String getPrefix(Player player) {
        UserData userData = Main.luckPerms.getUser(player.getUniqueId()).getCachedData();
        Contexts contexts = Main.luckPerms.getContextManager().getApplicableContexts(player);

        return ChatColor.translateAlternateColorCodes('&', userData.getMetaData(contexts).getPrefix());
    }

    Plugin plugin = Main.getPlugin(Main.class);
    LuckPermsApi api = LuckPerms.getApi();

    public PlayerChatListener() {

    }

    @EventHandler
    public void chatFilter(AsyncPlayerChatEvent e) {

        String message = e.getMessage();
        String format = e.getFormat();
        e.setCancelled(true);
        Player player = e.getPlayer();
        String prefix = getPrefix(player);
        List<String> words = main.getWordsConfig().getStringList("words");
        for (Player on : Bukkit.getOnlinePlayers()) {
            UUID playerUUID = on.getUniqueId();
            String filterValue = String.valueOf(plugin.getConfig().getString("Filters." + playerUUID));
            if (filterValue.equals("on")) {
                player.sendMessage(String.valueOf(words));
                for (int i = 0; i < words.size(); i++) {
                    if (message.contains(main.getWordsConfig().getStringList("words").get(i))) {
                        String ns = message.replace(main.getWordsConfig().getStringList("words").get(i), "Cabbage");
                        on.sendMessage(prefix + player.getName() + ns);
                    }
                }
            }else if(filterValue.equals("off")){
                on.sendMessage( prefix + player.getName() + ": " + message);

            }else{
                on.sendMessage( prefix + player.getName() + ": " + message);

            }
        }
    }
}

