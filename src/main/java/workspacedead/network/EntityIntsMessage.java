package workspacedead.network;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class EntityIntsMessage extends BlockEntityMessage {
    public static final int TRIGGER_UPDATEBLOCK = -98765;

    // private ResourceLocation _dimension;
    private int _variableId;
    private int _value;

    public EntityIntsMessage(Level level, BlockPos blockEntitypos, int variableId, int value) {
        super(level, blockEntitypos);
        _variableId = variableId;
        _value = value;
    }

    public EntityIntsMessage(FriendlyByteBuf buf) {
        super(buf);
        _variableId = buf.readInt();
        _value = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        super.toBytes(buf);
        buf.writeInt(_variableId);
        buf.writeInt(_value);
    }

    public boolean handleOnServer(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context ctx = supplier.get();
        ctx.enqueueWork(() -> {
            var level = supplier.get().getSender().getLevel();
            if (level.dimension() != dimension)
                return; // player isn't in same dimension... ignore
            var entity = level.getBlockEntity(blockpos);
            if (entity != null && entity instanceof IHandleClientInt) {
                ((IHandleClientInt) entity).handleClientInt(_variableId, _value);
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
