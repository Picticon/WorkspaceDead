package workspacedead.block;

import java.util.Random;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
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
                        boolean yea = false;
                        for (var r = 0; r < 5; r++) {
                            if (rand.nextFloat() < recipe.getChance())
                                yea = true;
                        }
                        if (yea) {
                            //level.sendParticles(new ItemParticleOption
                            level.setBlock(pos.above(1), recipe.getOutputBlock().pick(rand),
                                    UPDATE_ALL_IMMEDIATE | UPDATE_ALL);
                            level.playSound((Player) null, pos, SoundEvents.CHORUS_FLOWER_GROW, SoundSource.BLOCKS,
                                    1.0F, 1.0F);
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
