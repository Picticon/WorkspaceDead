package workspacedead.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import workspacedead.entity.ModEntityTypes;
import workspacedead.registry.MyItems;

public class GrassyPotatoPlantBlock extends CropBlock {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_7;

    public GrassyPotatoPlantBlock(Properties properties) {
        super(properties);
    }

    @Override
    public int getMaxAge() {
        return 7;
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return MyItems.GRASSYPOTATO_SEEDS.get();
    }

    private void spawnInfestation(ServerLevel level, BlockPos pos) {
        var potatoman = ModEntityTypes.GRASSYPOTATO.get().create(level);
        potatoman.moveTo((double) pos.getX() + 0.5D, (double) pos.getY(), (double) pos.getZ() + 0.5D, 0.0F, 0.0F);
        level.addFreshEntity(potatoman);
        potatoman.spawnAnim();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void spawnAfterBreak(BlockState blockState, ServerLevel level, BlockPos pos, ItemStack itemStack) {
        if (this.getAge(blockState) >= getMaxAge())
            this.spawnInfestation(level, pos);
        else
            super.spawnAfterBreak(blockState, level, pos, itemStack); // TO DO how do I get around this?

        // if (p_54189_.getGameRules().getBoolean(GameRules.RULE_DOBLOCKDROPS)) {
        // }
        // super.blockState.spawnAfterBreak(level, pos, itemStack);
    }

    // @Override
    // public void wasExploded(Level level, BlockPos pos, Explosion explosion) {
    // if (level instanceof ServerLevel) {
    // this.spawnInfestation((ServerLevel) level, pos);
    // }
    // }
}