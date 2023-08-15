package xyz.mtrdevelopment.corner.utils;

/*
 *
 * RedisAPI is a property of MTR-Development-Team
 * It was created @ 14/08/2023
 * Coded by the founder of MTR-Development-Team
 * EmpireMTR
 *
 */

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class ChatUtils {

    public static String translate(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static void sendMessage(CommandSender sender, String message) {
        sender.sendMessage(translate(message));
    }

    public static void broadcastMessage(String message) {
        Bukkit.getOnlinePlayers().forEach(player -> sendMessage(player, message));
    }

    public static void broadcastMessage(String message, String permission) {
        Bukkit.getOnlinePlayers().forEach(player -> {
            if(player.hasPermission(permission)) {
                sendMessage(player, message);
            }
        });
    }
}