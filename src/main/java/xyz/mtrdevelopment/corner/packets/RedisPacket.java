package xyz.mtrdevelopment.corner.packets;

/*
 *
 * RedisAPI is a property of MTR-Development-Team
 * It was created @ 13/08/2023
 * Coded by the founder of MTR-Development-Team
 * EmpireMTR
 *
 */

public interface RedisPacket {
    void onReceived();
    default void onSend() {}
    default boolean async() {return false;}
}
