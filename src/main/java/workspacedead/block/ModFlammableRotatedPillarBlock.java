package workspacedead.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;

public class ModFlammableRotatedPillarBlock extends RotatedPillarBlock {
    public ModFlammableRotatedPillarBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
        return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
        return 5;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
        return 5;
    }

    // @Nullable
    // @Override
    // public BlockState getToolModifiedState(BlockState state, Level world,
    // BlockPos pos, Player player,
    // ItemStack stack, ToolAction toolAction) {
    // if(stack.getItem() instanceof AxeItem) {
    // // if(state.is(ModBlocks.DEADLOG.get())) {
    // // return
    // ModBlocks.STRIPPED_EBONY_LOG.get().defaultBlockState().setValue(AXIS,
    // state.getValue(AXIS));
    // // }
    // // if(state.is(ModBlocks.EBONY_WOOD.get())) {
    // // return
    // ModBlocks.STRIPPED_EBONY_WOOD.get().defaultBlockState().setValue(AXIS,
    // state.getValue(AXIS));
    // // }
    // }

    // return super.getToolModifiedState(state,new UseOnContext(player,
    // InteractionHand.MAIN_HAND, result), toolAction, true)
    // return super.getToolModifiedState(state, world, pos, player, stack,
    // toolAction);
    // }
}