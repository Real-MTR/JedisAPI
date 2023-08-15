package xyz.mtrdevelopment.corner.packets.defaults;

/*
 *
 * RedisAPI is a property of MTR-Development-Team
 * It was created @ 13/08/2023
 * Coded by the founder of MTR-Development-Team
 * EmpireMTR
 *
 */

import xyz.mtrdevelopment.corner.packets.RedisPacket;
import xyz.mtrdevelopment.corner.utils.ChatUtils;

public class BroadcastPacket implements RedisPacket {

    private final String message, permission;

    public BroadcastPacket(String message) {
        this.message = message;
        this.permission = null;
    }

    public BroadcastPacket(String message, String permission) {
        this.message = message;
        this.permission = permission;
    }

    @Override
    public void onReceived() {
        if(permission == null) {
            ChatUtils.broadcastMessage(message);
            return;
        }
        ChatUtils.broadcastMessage(message, permission);
    }
}
