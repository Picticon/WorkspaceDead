package workspacedead.network;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class EntityInts {

    public static final int TRIGGER_UPDATEBLOCK = -98765;
    private final Holder<DimensionType> dimensionType;
    private final ResourceKey<Level> dimension;

    // private ResourceLocation _dimension;
    private BlockPos _blockpos;
    private int _variableId;
    private int _value;

    public EntityInts(Level level, BlockPos blockEntitypos, int variableId, int value) {
        this.dimensionType = level.dimensionTypeRegistration();
        this.dimension = level.dimension();
        // _dimension = level.dimension().getRegistryName();
        _blockpos = blockEntitypos;
        _variableId = variableId;
        _value = value;
    }

    public EntityInts(FriendlyByteBuf buf) {
        this.dimensionType = buf.readWithCodec(DimensionType.CODEC);
        this.dimension = ResourceKey.create(Registry.DIMENSION_REGISTRY, buf.readResourceLocation());
        // _dimension = buf.readResourceLocation();
        _blockpos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
        _variableId = buf.readInt();
        _value = buf.readInt();

    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeWithCodec(DimensionType.CODEC, this.dimensionType);
        buf.writeResourceLocation(this.dimension.location());
        // buf.writeResourceLocation(_dimension);
        buf.writeInt(_blockpos.getX());
        buf.writeInt(_blockpos.getY());
        buf.writeInt(_blockpos.getZ());
        buf.writeInt(_variableId);
        buf.writeInt(_value);
    }

    public boolean handleOnServer(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context ctx = supplier.get();
        ctx.enqueueWork(() -> {
            var level = supplier.get().getSender().getLevel();
            if (level.dimension() != dimension)
                return; // player isn't in same dimension... ignore
            var entity = level.getBlockEntity(_blockpos);
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
