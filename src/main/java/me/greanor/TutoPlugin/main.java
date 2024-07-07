package me.greanor.TutoPlugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin {
    @Override
    public void onEnable() {
        // Code à exécuter lors de l'activation du plugin
        getLogger().info("MonPlugin est activé !");

        // Enregistrer l'écouteur d'événements
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
    }

    @Override
    public void onDisable() {
        // Code à exécuter lors de la désactivation du plugin
        getLogger().info("MonPlugin est désactivé !");
    }

    public void sendCustomMessage(Player player, String message) {
        String customMessage = ChatColor.AQUA + "[Enkidiev] " + ChatColor.RESET + message;
        Bukkit.broadcastMessage(customMessage);
    }
}
