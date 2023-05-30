package workspacedead.entity.mob;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import workspacedead.block.ModBlocks;
import workspacedead.sound.ModSounds;

public class MoveToFarmlandBlockGoal extends MoveToBlockGoal {

    private final RottenPotato grassyPotato;
    // private Object wantsToRaid;
    private int timechanneling;

    public MoveToFarmlandBlockGoal(RottenPotato grassypotato) {
        super(grassypotato, 1, 12, 2);
        this.grassyPotato = grassypotato;
        this.nextStartTick = 120;
    }

    @Override
    public double acceptedDistance() {
        return 1.5D; // would like less... but...
    }

    @Override
    protected boolean isValidTarget(LevelReader pLevel, BlockPos pPos) {
        BlockState blockstate = pLevel.getBlockState(pPos);
        if (blockstate.is(Blocks.FARMLAND)) {
            var bs2 = pLevel.getBlockState(pPos.above());
            return bs2.is(Blocks.AIR) || (bs2.getBlock() instanceof CropBlock);
        }
        return false;
    }

    @Override
    public boolean canContinueToUse() {
        if (this.grassyPotato.isLeashed())
            return false;
        return super.canContinueToUse();
    }

    @Override
    public boolean canUse() {
        if (this.grassyPotato.isLeashed())
            return false;
        return super.canUse();
    }

    public void tick() {
        super.tick();
        // this.grassyPotato.getLookControl().setLookAt((double) this.blockPos.getX() +
        // 0.5D, (double) (this.blockPos.getY() + 1), (double) this.blockPos.getZ() +
        // 0.5D, 10.0F, (float) this.grassyPotato.getMaxHeadXRot());
        if (this.isReachedTarget()) {
            this.grassyPotato.setPos((this.grassyPotato.getX() + this.blockPos.getX() + .5f) / 2.0f,
                    this.grassyPotato.getY(), (this.grassyPotato.getZ() + this.blockPos.getZ() + .5f) / 2.0f);
            grassyPotato.setChanneling(true);
            timechanneling++;
            if (timechanneling > 80) {
                Level level = this.grassyPotato.level;
                BlockState blockstate = level.getBlockState(this.blockPos);
                if (blockstate.is(Blocks.FARMLAND)) {
                    if (!level.isClientSide()) {
                        level.setBlock(this.blockPos, ModBlocks.DEAD_FARMLAND.get().defaultBlockState(), 1 | 2);
                        this.grassyPotato.setPos((this.grassyPotato.getX() + this.blockPos.getX() + .5f) / 2.0f,
                                this.grassyPotato.getY() + .1f,
                                (this.grassyPotato.getZ() + this.blockPos.getZ() + .5f) / 2.0f);
                        level.playSound(null, this.blockPos, ModSounds.POP.get(), SoundSource.AMBIENT, 1F, 1);
                        this.grassyPotato.setYya(nextStartTick);
                    }
                }
                this.nextStartTick = 20;
                stop();
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

    @Override
    public void stop() {
        // WDServer.send("POTATO stopping");
        super.stop();
        grassyPotato.setChanneling(false);
        timechanneling = 0;
    }

}