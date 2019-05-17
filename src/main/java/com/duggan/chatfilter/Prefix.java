package com.duggan.chatfilter;

import me.lucko.luckperms.api.Contexts;
import me.lucko.luckperms.api.caching.UserData;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Prefix {

    public String getPrefix(Player player) {
        UserData userData = Main.luckPerms.getUser(player.getUniqueId()).getCachedData();
        Contexts contexts = Main.luckPerms.getContextManager().getApplicableContexts(player);

        return ChatColor.translateAlternateColorCodes('&', userData.getMetaData(contexts).getPrefix());
    }
}
