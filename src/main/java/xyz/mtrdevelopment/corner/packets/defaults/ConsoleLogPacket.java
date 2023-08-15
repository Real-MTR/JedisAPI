package xyz.mtrdevelopment.corner.packets.defaults;

/*
 *
 * RedisAPI is a property of MTR-Development-Team
 * It was created @ 13/08/2023
 * Coded by the founder of MTR-Development-Team
 * EmpireMTR
 *
 */

import lombok.Getter;
import org.bukkit.Bukkit;
import xyz.mtrdevelopment.corner.packets.RedisPacket;
import xyz.mtrdevelopment.corner.utils.ChatUtils;

@Getter
public class ConsoleLogPacket implements RedisPacket {

    private final String message;

    public ConsoleLogPacket(String message) {
        this.message = message;
    }

    @Override
    public void onReceived() {
        ChatUtils.sendMessage(Bukkit.getConsoleSender(), message);
    }
}