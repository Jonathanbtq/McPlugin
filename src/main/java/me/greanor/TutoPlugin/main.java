package me.greanor.TutoPlugin;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;
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

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        /**
         * Command permettant l'envoi d'une épée aux joueurs
         */
        if (command.getName().equalsIgnoreCase("getsword")) {
            if (sender instanceof Player) {
                if (sender.hasPermission("mycustomplugin.givexp")) {
                    Player target = Bukkit.getPlayer(args[0]);
                    Player player = (Player) sender;

                    ItemStack ironSword = new ItemStack(Material.IRON_SWORD, 1);

                    if (target != null) {
                        target.getInventory().addItem(ironSword);
                        player.sendMessage("Vous avez reçu une épée en fer !");
                    } else {
                        player.sendMessage(args[0] + " N'existe pas");
                    }
                }

            } else {
                sender.sendMessage("Cette commande ne peut être utilisée que par un joueur.");
            }
            return true;
        }

        /**
         * Commande permettant l'envoi d'xp a un joueurs precis
         */
        if (command.getName().equalsIgnoreCase("givexp")) {
            if (sender.hasPermission("mycustomplugin.givexp")) {
                if (args.length > 0) {
                    if (args.length > 1) {

                        int amount = Integer.parseInt(args[1]);
                        Player target = Bukkit.getPlayer(args[0]);

                        if (target != null && target.isOnline()) {
                            try {
                                if (amount > 0) {
                                    target.giveExpLevels(amount);
                                    // Message pour le receveur
                                    String senderMsd = ChatColor.RED + "Vous avez envoyé " + amount + " Enkiv à " + target;
                                    String targetMsg = ChatColor.GREEN + "---> " + sender + " vous a envoyé " + amount + " Enkiv";
                                    sender.sendMessage(senderMsd);
                                    target.sendMessage(targetMsg);
                                } else {
                                    sender.sendMessage("Le montant doit être positif");
                                }
                            } catch (NumberFormatException e) {
                                sender.sendMessage("Veuillez entrer un nombre valide");
                            }
                        } else {
                            sender.sendMessage(target + " n'est pas connecté");
                        }

                        if (target == null) {
                            Player player = (Player) sender;
                            player.giveExpLevels(amount);
                            sender.sendMessage("Cette commande ne peut être utilisée que par un joueur.");
                        }
                    }
                }
            }
            return true;
        }

        /**
         * Give un gamemode en utilisant un chiffre
         */
        if (command.getName().equalsIgnoreCase("gm")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;

                if (args.length > 0) {
                    String[] gmTab = {"0", "1", "2"};
                    String number = args[0];

                    for (String gm : gmTab) {
                        if (gm.equals(number)) {
                            GameMode gameMode;
                            switch (number) {
                                case "1":
                                    gameMode = GameMode.CREATIVE;
                                    break;
                                case "0":
                                    gameMode = GameMode.SURVIVAL;
                                    break;
                                case "2":
                                    gameMode = GameMode.SPECTATOR;
                                    break;
                                default:
                                    gameMode = GameMode.SURVIVAL;
                                    break;
                            }
                            player.setGameMode(gameMode);
                            player.sendMessage("Mode de jeu changé en + " + gameMode.toString().toLowerCase());
                            return true;
                        }
                    }
                    player.sendMessage("Mode de jeu invalide. Utilisez 1 (creative), 0 (survival) ou 2 (spectator).");
                } else {
                    player.sendMessage("Usage: /gm <0|1|2>");
                }
            } else {
                sender.sendMessage("Cette commande ne peut être utilisée que par un joueur.");
            }
            return true;
        }
        return false;
    }
}
