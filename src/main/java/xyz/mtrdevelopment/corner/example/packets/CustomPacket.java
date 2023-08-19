package xyz.mtrdevelopment.corner.example.packets;

/*
 *
 * RedisAPI is a property of MTR-Development-Team
 * It was created @ 14/08/2023
 * Coded by the founder of MTR-Development-Team
 * EmpireMTR
 *
 */

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.entity.Player;
import xyz.mtrdevelopment.corner.packets.RedisPacket;

@Getter
@AllArgsConstructor
public class CustomPacket implements RedisPacket {

    private final Player player;
    private final Player target;
    private final String message;

    @Override
    public void onReceived() {
        target.sendMessage("(From " + player.getName() + ") " + message);
    }

    @Override
    public void onSend() {
        player.sendMessage("(To " + target.getName() + ") " + message);
    }
}
