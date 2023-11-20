package workspacedead.block;

import java.util.Random;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;
import workspacedead.recipe.CropMutationRecipe;

public class MutatingFarmland extends FarmBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public MutatingFarmland(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        // super.onPlace(state, level, pos, oldState, isMoving);
        level.scheduleTick(pos, this, Mth.nextInt(RANDOM, 20, 60)); // initial tick is smaller than the ongoing tick
    }

    @Override
    public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction direction,
            IPlantable plantable) {
        var type = plantable.getPlantType(world, pos.relative(direction));
        return type == PlantType.CROP || type == PlantType.PLAINS;
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, Random rand) {
        level.scheduleTick(pos, this, 20 * 5); // 15 - 30 second delay
        var air = level.getBlockState(pos.above());
        if (air.isAir()) {
            var recipes = level.getRecipeManager().getAllRecipesFor(CropMutationRecipe.Type.INSTANCE);

            var desats = new BlockState[6];
            desats[0] = level.getBlockState(pos.north(1).above(1));
            desats[1] = level.getBlockState(pos.south(1).above(1));
            desats[2] = level.getBlockState(pos.east(1).above(1));
            desats[3] = level.getBlockState(pos.west(1).above(1));
            var catalyst = level.getBlockState(pos.below(1));
            for (CropMutationRecipe recipe : recipes) {
                if (recipe.getCatalyst() != null && !recipe.getCatalyst().test(catalyst))
                    continue;
                for (var i = 0; i < 4; i += 2) {
                    if ((recipe.getInput1Block().test(desats[i]) && recipe.getInput2Block().test(desats[i + 1]))
                            ||
                            (recipe.getInput1Block().test(desats[i + 1]) && recipe.getInput2Block().test(desats[i]))) {
                        var fail = false;
                        for (var z = 0; z < 2; z++) {
                            if (desats[i + z].getBlock() instanceof CropBlock) {
                                var cb = (CropBlock) desats[i + z].getBlock();
                                if (!cb.isMaxAge(desats[i + z])) {
                                    fail = true;
                                    break;
                                }
                            }
                        }
                        if (fail)
                            continue;
                        boolean yea = false;
                        //                        for (var r = 0; r < 5; r++) {
                        if (rand.nextFloat() < recipe.getChance())
                            yea = true;
                        //                      }
                        if (yea) {
                            //level.sendParticles(new ItemParticleOption
                            level.setBlock(pos.above(1), recipe.getOutputBlock().pick(rand),
                                    UPDATE_ALL_IMMEDIATE | UPDATE_ALL);
                            level.playSound((Player) null, pos, SoundEvents.CHORUS_FLOWER_GROW, SoundSource.BLOCKS,
                                    1.0F, 1.0F);
                            for (var z = 0; z < 2; z++) {
                                var block = desats[i + z].getBlock();
                                Age age = block instanceof CropBlock crop
                                        ? new Age(crop.getAgeProperty(), crop.getMaxAge())
                                        : null;
                                if (age != null) {
                                    if (desats[i + z].getBlock() instanceof CropBlock) {
                                        BlockPos pos2;
                                        if (i + z == 0)
                                            pos2 = pos.north(1).above(1);
                                        else if (i + z == 1)
                                            pos2 = pos.south(1).above(1);
                                        else if (i + z == 2)
                                            pos2 = pos.east(1).above(1);
                                        else
                                            pos2 = pos.west(1).above(1);
                                        level.setBlock(pos2, desats[i + z].setValue(age.property, 0),
                                                Block.UPDATE_ALL);
                                    }
                                }
                            }
                        } else {
                            // level.playSound((Player) null, pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS,
                            //         1.0F, 1.0F);
                            ((ServerLevel) level).sendParticles(ParticleTypes.SMOKE, pos.getX() + .5f,
                                    pos.getY() + .5f, pos.getZ() + .5f, 20, .1f, .1f, .1f, .1f);
                        }
                        return;
                    }
                }
            }
        }
    }

    private record Age(IntegerProperty property, int maxAge) {
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, Level level, BlockPos pos, Random rand) {
        for (int i = 0; i < 1; ++i) {
            double d0 = (double) ((float) pos.getX() + rand.nextFloat());
            double d1 = (double) ((float) pos.getY() + rand.nextFloat());
            double d2 = (double) ((float) pos.getZ() + rand.nextFloat());
            double d3 = ((double) rand.nextFloat() - 0.5D) * 0.1D;
            double d4 = ((double) rand.nextFloat() - 0.5D) * 0.1D;
            double d5 = ((double) rand.nextFloat() - 0.5D) * 0.1D;
            level.addParticle(ParticleTypes.ELECTRIC_SPARK, d0, d1, d2, d3, d4, d5);
        }
    }

}
