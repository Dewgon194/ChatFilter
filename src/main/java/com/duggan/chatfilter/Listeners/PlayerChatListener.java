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
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class PlayerChatListener implements Listener {


    private Prefix prefix;
    private ReplacementWords replacementWords;
    private Main main = Main.getInstance();

    public String getPrefix(Player player) {
        UserData userData = Main.luckPerms.getUser(player.getUniqueId()).getCachedData();
        Contexts contexts = Main.luckPerms.getContextManager().getApplicableContexts(player);

        return ChatColor.translateAlternateColorCodes('&', userData.getMetaData(contexts).getPrefix());
    }

    public PlayerChatListener(ReplacementWords replacementWords) {
        this.replacementWords = replacementWords;
    }

    Plugin plugin = Main.getPlugin(Main.class);
    LuckPermsApi api = LuckPerms.getApi();

    @EventHandler
    public void chatFilter(AsyncPlayerChatEvent e) {

        String message = e.getMessage().toLowerCase();
        Player player = e.getPlayer();
        String prefix = getPrefix(player);
        List<String> words = new ArrayList<>();
        List<String> replacedWords = new ArrayList<>();
        String ns = e.getMessage().toLowerCase();
        words.addAll(Main.getInstance().getWordsConfig().getStringList("words"));
        for (Player on : Bukkit.getOnlinePlayers()) {
            UUID playerUUID = on.getUniqueId();
            String filterValue = String.valueOf(plugin.getConfig().getString("Filters." + playerUUID));
            if (filterValue.equals("on")) {
                for (int i = 0; i < words.size(); i++) {
                    if (message.contains(words.get(i))) {
                        e.setCancelled(true);
                        replacedWords.add(words.get(i));
                    }
                    if (i == words.size() - 1) {
                        for (int x = 0; x < replacedWords.size(); x++) {
                            ns = ns.replace(replacedWords.get(x), replacementWords.replacementWords());
                            if (x == replacedWords.size() - 1) {
                                on.sendMessage(prefix + player.getName() + ": " + ns);
                            }
                        }
                    }
                }
            }
        }
    }
}

