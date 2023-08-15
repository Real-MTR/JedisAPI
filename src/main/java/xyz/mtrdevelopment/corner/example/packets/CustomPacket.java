package xyz.mtrdevelopment.corner.example.packets;

/*
 *
 * RedisAPI is a property of MTR-Development-Team
 * It was created @ 14/08/2023
 * Coded by the founder of MTR-Development-Team
 * EmpireMTR
 *
 */

import org.bukkit.entity.Player;
import xyz.mtrdevelopment.corner.packets.RedisPacket;

public class CustomPacket implements RedisPacket {

    private final Player player;

    public CustomPacket(Player player) {
        this.player = player;
    }

    @Override
    public void onReceived() {
        player.sendMessage("From = Hello.");
    }

    @Override
    public void onSend() {
        player.sendMessage("To = Hello.");
    }
}
