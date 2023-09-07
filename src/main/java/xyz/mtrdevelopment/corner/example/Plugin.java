package xyz.mtrdevelopment.corner.example;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.mtrdevelopment.corner.RedisAPI;
import xyz.mtrdevelopment.corner.connect.RedisCredentials;
import xyz.mtrdevelopment.corner.packets.defaults.BroadcastPacket;
import xyz.mtrdevelopment.corner.packets.defaults.ConsoleLogPacket;

@Getter
public final class Plugin extends JavaPlugin implements Listener {

    @Getter private static Plugin instance;
    private RedisAPI jedisAPI;

    @Override
    public void onEnable() {
        instance = this;
        this.jedisAPI = new RedisAPI(this, new RedisCredentials("127.0.0.1", "RedisAPI", 6379));
        this.jedisAPI.send(new ConsoleLogPacket("&aYOOOO it works"));
        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void messagesCheck(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        this.jedisAPI.send(new BroadcastPacket("&a" + player.getName() + " joined (Checking if console still prints logs)"));
    }
}
