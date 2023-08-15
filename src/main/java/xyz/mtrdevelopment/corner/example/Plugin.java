package xyz.mtrdevelopment.corner.example;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.mtrdevelopment.corner.RedisAPI;
import xyz.mtrdevelopment.corner.connect.RedisCredentials;

@Getter
public final class Plugin extends JavaPlugin {

    @Getter private static Plugin instance;
    private RedisAPI jedisAPI;

    @Override
    public void onEnable() {
        instance = this;
        this.jedisAPI = new RedisAPI(new RedisCredentials("127.0.0.1", "RedisAPI", 6379));
        // this.jedisAPI.send(new CustomPacket(player));
        // this is how to send packets.
    }
}
