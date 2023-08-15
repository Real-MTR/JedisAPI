package xyz.mtrdevelopment.corner.packets.defaults;

/*
 *
 * RedisAPI is a property of MTR-Development-Team
 * It was created @ 13/08/2023
 * Coded by the founder of MTR-Development-Team
 * EmpireMTR
 *
 */

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import xyz.mtrdevelopment.corner.packets.RedisPacket;
import xyz.mtrdevelopment.corner.utils.ChatUtils;

public class MessagePacket implements RedisPacket {

    private final Player player;
    private final String message;

    public MessagePacket(Player player, String message) {
        this.player = player;
        this.message = message;
    }

    @Override
    public void onReceived() {
        Bukkit.getOnlinePlayers().forEach(player1 -> {
            if(player1.getUniqueId() == player.getUniqueId()) {
                ChatUtils.sendMessage(player1, message);
            }
        });
    }
}
