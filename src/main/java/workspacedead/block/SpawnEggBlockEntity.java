package workspacedead.block;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class SpawnEggBlockEntity extends BlockEntity {

    private String entityid;

    public SpawnEggBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.SPAWNEGG_BLOCK_ENTITY.get(), pos, state);
    }

    public String getEntityID() {
        return entityid;
    }

    public void setEntityID(String entityID) {
        this.entityid = entityID;
    }

    public static String entityidkey = "entityid";

    public void load(CompoundTag pTag) {
        super.load(pTag);
        this.entityid = pTag.getString(entityidkey);
    }

    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.putString(entityidkey, this.entityid);
    }

}
