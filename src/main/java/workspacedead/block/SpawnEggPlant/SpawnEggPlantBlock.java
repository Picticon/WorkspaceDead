package workspacedead.block.SpawnEggPlant;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import workspacedead.item.custom.SpawnEggSeedsItem;
import workspacedead.registry.MyBlockEntities;
import workspacedead.registry.MyItems;

public class SpawnEggPlantBlock extends CropBlock implements EntityBlock {
    // public static final IntegerProperty AGE = BlockStateProperties.AGE_7;
    // public static final StringProperty AGE = BlockStateProperties.AGE_7;

    public SpawnEggPlantBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack pStack, BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
        //pTooltip.add(new TextComponent("aaa"));
    }

    @Override
    public int getMaxAge() {
        return 7;
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return MyItems.SPAWNEGG_SEEDS.get();
    }

    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        return true;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        Level world = pContext.getLevel();
        BlockPos pos = pContext.getClickedPos();
        ItemStack stack = pContext.getItemInHand();
        BlockState state = this.defaultBlockState();
        if (!canSurvive(state, world, pos)) {
            return null;
        }
        if (stack.getItem() instanceof SpawnEggSeedsItem seed) {
            var entityid = seed.getMobID(stack);
            if (entityid != null) {
            }
        }

        return super.getStateForPlacement(pContext);
    }

    @Override
    public void growCrops(Level pLevel, BlockPos pPos, BlockState pState) {
        super.growCrops(pLevel, pPos, pState);
        if (getAge(pState) == getMaxAge()) {

        }
    }

    @Override
    public void spawnAfterBreak(BlockState blockState, ServerLevel level, BlockPos pos, ItemStack itemStack) {
        if (level.isClientSide())
            return;
        ItemStack stack = new ItemStack(MyItems.SPAWNEGG_SEEDS.get(), 1);
        stack.getOrCreateTag().putString(SpawnEggSeedsItem.EssenceTagID, "");

    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        var newentity = MyBlockEntities.SPAWNEGG_BLOCK_ENTITY.get().create(pPos, pState);
        newentity.setEntityID("none");
        return newentity;
    }
}