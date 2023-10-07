package workspacedead.block.PoopLantern;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import workspacedead.registry.MyBlocks;

public class PoopBlock extends Block {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public PoopBlock() {
        super(BlockBehaviour.Properties.of(Material.SPONGE).strength(1).sound(SoundType.SLIME_BLOCK));
    }

    @SuppressWarnings("deprecation")
    @Override
    public InteractionResult use(BlockState pBlockState, Level pLevel, BlockPos blockPos, Player pPlayer,
            InteractionHand pHand, BlockHitResult pHitResult) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        if (itemstack.canPerformAction(net.minecraftforge.common.ToolActions.SHEARS_CARVE)) {
            if (!pLevel.isClientSide) {
                Direction direction = pHitResult.getDirection();
                Direction direction1 = direction.getAxis() == Direction.Axis.Y ? pPlayer.getDirection().getOpposite()
                        : direction;
                pLevel.playSound((Player) null, blockPos, SoundEvents.PUMPKIN_CARVE, SoundSource.BLOCKS, 1.0F, 1.0F);
                pLevel.setBlock(blockPos,
                        MyBlocks.CARVED_POOPBLOCK.get().defaultBlockState().setValue(FACING, direction1), 11);
                // ItemEntity itementity = new ItemEntity(p_55290_, (double) p_55291_.getX() +
                // 0.5D + (double) direction1.getStepX() * 0.65D, //
                // (double) p_55291_.getY() + 0.1D, (double) p_55291_.getZ() + 0.5D + (double)
                // direction1.getStepZ() * 0.65D, new ItemStack(Items.PUMPKIN_SEEDS, 4));
                // itementity.setDeltaMovement(0.05D * (double) direction1.getStepX() +
                // p_55290_.random.nextDouble() * 0.02D, 0.05D, 0.05D * (double)
                // direction1.getStepZ() + p_55290_.random.nextDouble() * 0.02D);
                // p_55290_.addFreshEntity(itementity);
                itemstack.hurtAndBreak(1, pPlayer, (p_55287_) -> {
                    p_55287_.broadcastBreakEvent(pHand);
                });
                pLevel.gameEvent(pPlayer, GameEvent.SHEAR, blockPos);
                pPlayer.awardStat(Stats.ITEM_USED.get(Items.SHEARS));
            }
            return InteractionResult.sidedSuccess(pLevel.isClientSide);
        } else {
            return super.use(pBlockState, pLevel, blockPos, pPlayer, pHand, pHitResult);
        }
    }

}
