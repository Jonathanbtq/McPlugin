package me.greanor.TutoPlugin;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.entity.Player;

import java.util.Random;

public class PlayerJoinListener implements Listener {
    private final main plugin;

    public PlayerJoinListener(main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        String[] messages;
        messages = new String[]{"Bienvenue", "Heho de retour", "Le grand retour", "Hey salut!"};

        Random random = new Random();
        int randomText = random.nextInt(messages.length);
        String finalmsg = messages[randomText];

        if (!player.hasPlayedBefore()) {
            // Utiliser la méthode pour envoyer un message personnalisé
            plugin.sendCustomMessage(player, "Bienvenue pour la première fois, " + player.getName() + "!");
        } else {
            plugin.sendCustomMessage(player, finalmsg + " " + player.getName() + "!");
        }
    }
}