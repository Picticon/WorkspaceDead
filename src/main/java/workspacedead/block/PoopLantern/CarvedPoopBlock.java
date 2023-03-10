package workspacedead.block.PoopLantern;

import java.util.function.Predicate;

import javax.annotation.Nullable;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Wearable;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import net.minecraft.world.level.block.state.pattern.BlockPatternBuilder;
import net.minecraft.world.level.block.state.predicate.BlockMaterialPredicate;
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.Material;
import workspacedead.block.ModBlocks;
import workspacedead.entity.ModEntityTypes;

public class CarvedPoopBlock extends HorizontalDirectionalBlock implements Wearable {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    private static final Predicate<BlockState> PUMPKINS_PREDICATE = (predator) -> {
        return predator != null && (predator.is(ModBlocks.POOP_O_LANTERN.get()) || predator.is(ModBlocks.CARVED_POOPBLOCK.get()));
    };
    @Nullable
    private BlockPattern boneGolemBase;
    @Nullable
    private BlockPattern boneGolemFull;

    public CarvedPoopBlock(BlockBehaviour.Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving) {
        if (!pOldState.is(pState.getBlock())) {
            this.trySpawnGolem(pLevel, pPos);
        }
    }

    public boolean canSpawnGolem(LevelReader pLevel, BlockPos pPos) {
        return this.getOrCreateBoneGolemBase().find(pLevel, pPos) != null;
    }

    private void trySpawnGolem(Level pLevel, BlockPos pPos) {
        BlockPattern.BlockPatternMatch blockpattern$blockpatternmatch = this.getOrCreateBoneGolemFull().find(pLevel, pPos);
        if (blockpattern$blockpatternmatch != null) {
            for (int j = 0; j < this.getOrCreateBoneGolemFull().getWidth(); ++j) {
                for (int k = 0; k < this.getOrCreateBoneGolemFull().getHeight(); ++k) {
                    BlockInWorld blockinworld2 = blockpattern$blockpatternmatch.getBlock(j, k, 0);
                    pLevel.setBlock(blockinworld2.getPos(), Blocks.AIR.defaultBlockState(), 2);
                    pLevel.levelEvent(2001, blockinworld2.getPos(), Block.getId(blockinworld2.getState()));
                }
            }

            BlockPos blockpos = blockpattern$blockpatternmatch.getBlock(1, 2, 0).getPos();
            var bonegolem = ModEntityTypes.BONE_GOLEM.get().create(pLevel);
            bonegolem.setPlayerCreated(true);
            bonegolem.moveTo((double) blockpos.getX() + 0.5D, (double) blockpos.getY() + 0.05D, (double) blockpos.getZ() + 0.5D, 0.0F, 0.0F);
            pLevel.addFreshEntity(bonegolem);

            for (ServerPlayer serverplayer1 : pLevel.getEntitiesOfClass(ServerPlayer.class, bonegolem.getBoundingBox().inflate(5.0D))) {
                CriteriaTriggers.SUMMONED_ENTITY.trigger(serverplayer1, bonegolem);
            }

            for (int i1 = 0; i1 < this.getOrCreateBoneGolemFull().getWidth(); ++i1) {
                for (int j1 = 0; j1 < this.getOrCreateBoneGolemFull().getHeight(); ++j1) {
                    BlockInWorld blockinworld1 = blockpattern$blockpatternmatch.getBlock(i1, j1, 0);
                    pLevel.blockUpdated(blockinworld1.getPos(), Blocks.AIR);
                }
            }
        }
    }

    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
    }

    private BlockPattern getOrCreateBoneGolemBase() {
        if (this.boneGolemBase == null) {
            this.boneGolemBase = BlockPatternBuilder.start().aisle("~ ~", "###", "~#~")
                    .where('#', BlockInWorld.hasState(BlockStatePredicate.forBlock(Blocks.BONE_BLOCK)))
                    .where('~', BlockInWorld.hasState(BlockMaterialPredicate.forMaterial(Material.AIR))).build();
        }

        return this.boneGolemBase;
    }

    private BlockPattern getOrCreateBoneGolemFull() {
        if (this.boneGolemFull == null) {
            this.boneGolemFull = BlockPatternBuilder.start().aisle("~^~", "###", "~#~")
                    .where('^', BlockInWorld.hasState(PUMPKINS_PREDICATE))
                    .where('#', BlockInWorld.hasState(BlockStatePredicate.forBlock(Blocks.BONE_BLOCK)))
                    .where('~', BlockInWorld.hasState(BlockMaterialPredicate.forMaterial(Material.AIR))).build();
        }

        return this.boneGolemFull;
    }

}
