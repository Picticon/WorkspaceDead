package workspacedead.network;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import workspacedead.WorkspaceDead;

public final class MyMessages {

    public static SimpleChannel INSTANCE;

    public static void register() {
        INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(WorkspaceDead.MOD_ID, "wd40"), //
                () -> "1.0", //
                s -> true, //
                s -> true);

        INSTANCE.messageBuilder(EntityIntsMessage.class, nextid(), NetworkDirection.PLAY_TO_SERVER) //
                .decoder(EntityIntsMessage::new) //
                .encoder(EntityIntsMessage::toBytes) //
                .consumer(EntityIntsMessage::handleOnServer) //
                .add();
        INSTANCE.messageBuilder(RecipeMessage.class, nextid(), NetworkDirection.PLAY_TO_SERVER) //
                .decoder(RecipeMessage::new) //
                .encoder(RecipeMessage::toBytes) //
                .consumer(RecipeMessage::handleOnServer) //
                .add();
        // INSTANCE.messageBuilder(EntityInts.class, nextid(), NetworkDirection.PLAY_TO_CLIENT) //
        //         .decoder(EntityInts::new) //
        //         .encoder(EntityInts::toBytes) //
        //         .consumer(EntityInts::handleOnClient) //
        //         .add();
    }

    public static <MSG> void sendToServer(MSG message) {
        var minecraft = Minecraft.getInstance();
        if (minecraft.level.isClientSide)
            INSTANCE.sendToServer(message);
    }

    // public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
    //     INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    // }

    private static int packetId = 0;

    private static int nextid() {
        return packetId++;
    }
}
