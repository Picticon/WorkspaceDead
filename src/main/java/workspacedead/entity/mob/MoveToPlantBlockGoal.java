package workspacedead.entity.mob;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.MushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.IPlantable;
import java.util.Random;

public class MoveToPlantBlockGoal extends MoveToBlockGoal {

    private final GrassyPotato grassyPotato;
    // private Object wantsToRaid;
    private int timechanneling;

    private static Random RANDOM = new Random();

    public MoveToPlantBlockGoal(GrassyPotato grassypotato) {
        super(grassypotato, 1, 12, 3);
        this.grassyPotato = grassypotato;
        this.nextStartTick = 120;
    }

    @Override
    public double acceptedDistance() {
        return 1.75D; // would like less... but...
    }

    @Override
    protected boolean isValidTarget(LevelReader pLevel, BlockPos pPos) {
        //var t = 4;
        if (this.grassyPotato.isInSittingPose() || grassyPotato.getChanneling() == GrassyPotato.CHANNELING_COOLDOWN)
            return false;
        //if (RANDOM.nextInt(t) < 1) 
        {
            BlockState blockstate = pLevel.getBlockState(pPos);
            //Chatter.sendToAllPlayers(
            //        "X:" + pPos.getX() + " Y:" + pPos.getY() + " Z:" + pPos.getZ() + " state:" + blockstate.toString());
            // if (blockstate.is(Blocks.WHEAT)) {
            //     Chatter.sendToAllPlayers("Wheat!");
            // }
            if (blockstate.getBlock() instanceof IPlantable) {// || blockstate.getBlock() instanceof BonemealableBlock) {
                if (blockstate.getBlock() instanceof CropBlock) {
                    if (!((CropBlock) blockstate.getBlock()).isMaxAge(blockstate)) {
                        var bs2 = pLevel.getBlockState(pPos.above());
                        return bs2.is(Blocks.AIR);
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean canContinueToUse() {
        if (this.grassyPotato.isLeashed())
            return false;
        if (this.grassyPotato.isInSittingPose())
            return false;
        return super.canContinueToUse();
    }

    @Override
    public boolean canUse() {
        if (this.grassyPotato.isLeashed())
            return false;
        if (this.grassyPotato.isInSittingPose())
            return false;
        return super.canUse();
    }

    public void tick() {
        super.tick();
        // this.grassyPotato.getLookControl().setLookAt((double) this.blockPos.getX() +
        // 0.5D, (double) (this.blockPos.getY() + 1), (double) this.blockPos.getZ() +
        // 0.5D, 10.0F, (float) this.grassyPotato.getMaxHeadXRot());
        if (this.isReachedTarget() && this.canUse()) {
            this.grassyPotato.setPos((this.grassyPotato.getX() + this.blockPos.getX() + .5f) / 2.0f,
                    this.grassyPotato.getY(), (this.grassyPotato.getZ() + this.blockPos.getZ() + .5f) / 2.0f);
            grassyPotato.setChanneling(GrassyPotato.CHANNELING_BONEMEALING);
            timechanneling++;
            var level = this.grassyPotato.level;
            //Chatter.sendToAllPlayers("" + timechanneling + " " + this.nextStartTick);
            if (timechanneling > 4) {
                if (!level.isClientSide && level instanceof ServerLevel) {
                    BlockState blockstate = level.getBlockState(this.blockPos);
                    BlockPos.betweenClosed(this.blockPos.offset(-1, -1, -1), blockPos.offset(3, 1, 3))
                            .forEach(iteratedPos -> {
                                // if (!this.grassyPotato.level.hasChunkAt(cropPos)) {
                                //     return;
                                // }
                                BlockState cropState = level.getBlockState(iteratedPos);
                                Block cropBlock = cropState.getBlock();

                                if ((cropBlock instanceof IPlantable)) {//|| cropBlock instanceof BonemealableBlock)) {
                                    this.tickCropBlock(((ServerLevel) level), iteratedPos, cropState, cropBlock);
                                }
                            });
                    level.scheduleTick(this.blockPos, blockstate.getBlock(), 20);
                    level.playSound(null, blockPos.getX(), blockPos.getY(), blockPos.getZ(),
                            SoundEvents.BONE_MEAL_USE, SoundSource.BLOCKS, 1, 1);
                }
                grassyPotato.setChanneling(GrassyPotato.CHANNELING_COOLDOWN);
                stop();
                //this.nextStartTick = 120;
            }
        } else {
            // if (timechanneling % 80 == 0)
            // WDServer.send("POTATO target " + this.grassyPotato.blockPosition().getX() +
            // "," + this.grassyPotato.blockPosition().getZ() + " -> " +
            // this.getMoveToTarget().getX() + "," + this.getMoveToTarget().getZ());

            // grassyPotato.setChanneling(false);
            // timechanneling = 0;
        }
    }

    private void tickCropBlock(ServerLevel level, BlockPos cropPos, BlockState cropState, Block cropBlock) {
        level.scheduleTick(cropPos, cropBlock, (int) (20F));
        cropState.randomTick(level, cropPos, RANDOM);
        level.levelEvent(2005, cropPos, 1);
    }

    @Override
    public void stop() {
        // WDServer.send("POTATO stopping");
        super.stop();
        grassyPotato.setChanneling(GrassyPotato.CHANNELING_NONE);
        timechanneling = 0;
    }

}