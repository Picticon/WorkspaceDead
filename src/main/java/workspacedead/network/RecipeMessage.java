package workspacedead.network;

import java.util.function.Supplier;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

public class RecipeMessage extends BlockEntityMessage {
    private ResourceLocation _recipeResource;

    public RecipeMessage(Level level, BlockPos blockEntitypos, ResourceLocation recipeId) {
        super(level, blockEntitypos);
        _recipeResource = recipeId;
    }

    public RecipeMessage(FriendlyByteBuf buf) {
        super(buf);
        var namespace = buf.readUtf();
        var path = buf.readUtf();
        _recipeResource = new ResourceLocation(namespace, path);
    }

    public void toBytes(FriendlyByteBuf buf) {
        super.toBytes(buf);
        buf.writeUtf(_recipeResource.getNamespace());
        buf.writeUtf(_recipeResource.getPath());
    }

    public boolean handleOnServer(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context ctx = supplier.get();
        ctx.enqueueWork(() -> {
            var level = supplier.get().getSender().getLevel();
            if (level.dimension() != dimension)
                return; // player isn't in same dimension... ignore
            var entity = level.getBlockEntity(blockpos);
            if (entity != null && entity instanceof IHandleRecipe) {
                ((IHandleRecipe) entity).handleRecipe(_recipeResource);
            }
        });
        return true;
    }

    public boolean handleOnClient(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context ctx = supplier.get();
        ctx.enqueueWork(() -> {
            // do nothing...?
        });
        return true;
    }

}
