package workspacedead.block.FullMetalAlchemiser;

import javax.annotation.Nullable;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;

public class FullMetalAlchemiserBlock extends BaseEntityBlock {

    // This sets up the 6 possible direction states
    public static final DirectionProperty FACING = DirectionalBlock.FACING;

    public static final String FULLMETALALCHEMISER_SCREEN = "fullmetalalchemiser.screen";
    public static final String MESSAGE_POWERGEN = "message.powergen";

    public FullMetalAlchemiserBlock() {
        super(BlockBehaviour.Properties.of(Material.STONE).strength(1f).sound(SoundType.STONE).noOcclusion());
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(FACING);
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getNearestLookingDirection().getOpposite());
    }

    /*
     * @Override public void appendHoverText(ItemStack stack, @Nullable BlockGetter
     * reader, List<Component> list, TooltipFlag flags) { list.add(new
     * TranslatableComponent(MESSAGE_POWERGEN,
     * Integer.toString(YellowGeneratorBlockEntity.POWERGEN_GENERATE)).withStyle(
     * ChatFormatting.BLUE)); }
     */

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new FullMetalAlchemiserBlockEntity(blockPos, blockState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state,
            BlockEntityType<T> type) {
        if (level.isClientSide()) {
            return null;
        }
        return (lvl, pos, blockState, t) -> {
            if (t instanceof FullMetalAlchemiserBlockEntity tile) {
                tile.tickServer(this, lvl, pos, blockState);
            }
        };
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand,
            BlockHitResult trace) {
        if (level.isClientSide) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof FullMetalAlchemiserBlockEntity) {
                Minecraft.getInstance().setScreen(new FullMetalAlchemiserOptionsScreen((FullMetalAlchemiserBlockEntity)be));
            }
        }
        return InteractionResult.SUCCESS;
    }
}
