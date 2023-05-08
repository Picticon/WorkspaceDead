package workspacedead.util;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.player.Player;

public class Chatter {
    public static MinecraftServer staticServer;

    public static void sendToAllPlayers(String string) {
        if (staticServer == null)
            return;
        var msg = new TextComponent(string);
        for (var player : staticServer.getPlayerList().getPlayers()) {
            player.sendMessage(msg, player.getUUID());
        }
    }

    public static void chat(String message) {
        try {
            var mc = Minecraft.getInstance();
            mc.player.sendMessage(new TextComponent(message), null);
        } catch (Exception ex) {
            sendToAllPlayers("Server: " + message);
        }
    }

    public static void sendToPlayer(Player player, String string) {
        player.sendMessage(new TextComponent(string), player.getUUID());
    }
}
