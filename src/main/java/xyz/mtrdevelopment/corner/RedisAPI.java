package xyz.mtrdevelopment.corner;

/*
 *
 * RedisAPI is a property of MTR-Development-Team
 * It was created @ 13/08/2023
 * Coded by the founder of MTR-Development-Team
 * EmpireMTR
 *
 */

import com.google.gson.Gson;
import lombok.Getter;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import xyz.mtrdevelopment.corner.connect.RedisCredentials;
import xyz.mtrdevelopment.corner.listeners.RedisListener;
import xyz.mtrdevelopment.corner.packets.RedisPacket;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Getter
public class RedisAPI {
    private final RedisCredentials redisCredentials;
    private final Gson gson;
    private final Executor executor;
    private final RedisListener redisListener;

    private JedisPool jedisPool;
    private Jedis jedis;

    public RedisAPI(RedisCredentials redisCredentials) {
        this.redisCredentials = redisCredentials;
        this.gson = new Gson();
        this.executor = Executors.newFixedThreadPool(2);

        this.redisListener = new RedisListener(executor, this);
        this.connect(redisCredentials);
    }

    private void connect(RedisCredentials redisCredentials) {
        try {
            this.jedisPool = new JedisPool(new JedisPoolConfig(), redisCredentials.getHostname(), redisCredentials.getPort());
            this.jedis = jedisPool.getResource();
            if (redisCredentials.isAuth()) jedis.auth(redisCredentials.getPassword());
            new Thread(() -> jedis.subscribe(new RedisListener(executor, this), redisCredentials.getChannel())).start();
            jedis.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void send(RedisPacket redisPacket) {
        redisPacket.onSend();
        new Thread(() -> {
            try (Jedis jedis = jedisPool.getResource()) {
                this.jedis = jedis;
                if (redisCredentials.isAuth()) jedis.auth(redisCredentials.getPassword());

                jedis.publish(redisCredentials.getChannel(), gson.toJson(redisPacket) + "///" + redisPacket.getClass().getName());
                jedisPool.returnResource(jedis);
            }
        }).start();
    }

    public String getPing() {
        return jedis.ping();
    }

    public boolean isConnected() {
        return jedis.isConnected();
    }

    public void shutdown() {
        jedisPool.destroy();
        redisListener.unsubscribe();
    }
}
