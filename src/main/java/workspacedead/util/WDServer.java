package workspacedead.util;

import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.MinecraftServer;

public class WDServer {
    public static MinecraftServer staticServer;

    public static void send(String string) {
        if (staticServer == null)
            return;
        var msg = new TextComponent(string);
        for (var player : staticServer.getPlayerList().getPlayers()) {
            player.sendMessage(msg, player.getUUID());
        }
    }
}
