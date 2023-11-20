package workspacedead.network;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;

public abstract class BlockEntityMessage {
    protected final Holder<DimensionType> dimensionType;
    protected final ResourceKey<Level> dimension;
    protected BlockPos blockpos;

    public BlockEntityMessage(Level level, BlockPos blockEntitypos) {
        this.dimensionType = level.dimensionTypeRegistration();
        this.dimension = level.dimension();
        this.blockpos = blockEntitypos;
    }

    public BlockEntityMessage(FriendlyByteBuf buf) {
        this.dimensionType = buf.readWithCodec(DimensionType.CODEC);
        this.dimension = ResourceKey.create(Registry.DIMENSION_REGISTRY, buf.readResourceLocation());
        this.blockpos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeWithCodec(DimensionType.CODEC, this.dimensionType);
        buf.writeResourceLocation(this.dimension.location());
        buf.writeInt(this.blockpos.getX());
        buf.writeInt(this.blockpos.getY());
        buf.writeInt(this.blockpos.getZ());
    }

}
