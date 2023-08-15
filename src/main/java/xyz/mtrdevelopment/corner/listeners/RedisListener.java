package xyz.mtrdevelopment.corner.listeners;

/*
 *
 * RedisAPI is a property of MTR-Development-Team
 * It was created @ 13/08/2023
 * Coded by the founder of MTR-Development-Team
 * EmpireMTR
 *
 */

import redis.clients.jedis.JedisPubSub;
import xyz.mtrdevelopment.corner.RedisAPI;
import xyz.mtrdevelopment.corner.packets.RedisPacket;

import java.util.concurrent.Executor;

public class RedisListener extends JedisPubSub {

    private final Executor executor;
    private final RedisAPI jedisAPI;

    public RedisListener(Executor executor, RedisAPI jedisAPI) {
        this.executor = executor;
        this.jedisAPI = jedisAPI;
    }

    @Override
    public void onMessage(String channel, String message) {
        if (jedisAPI.getRedisCredentials().isAuth()) jedisAPI.getJedisPool().getResource().auth(jedisAPI.getRedisCredentials().getPassword());
        if (!channel.equalsIgnoreCase(jedisAPI.getRedisCredentials().getChannel())) return;

        executor.execute(() -> {
            String[] strings = message.split("///");

            System.out.println(strings[1]);
            System.out.println(strings[0]);

            Object jsonObject = null;
            try {
                jsonObject = jedisAPI.getGson().fromJson(strings[0], Class.forName(strings[1]));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            RedisPacket redisPacket = (RedisPacket) jsonObject;

            if (redisPacket == null) {
                System.out.println("The redis packet received seems to be null!");
                return;
            }

            redisPacket.onReceived();
        });
    }
}