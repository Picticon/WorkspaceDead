package workspacedead.entity.mob;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class MoveToDirtBlockGoal extends MoveToBlockGoal {

    private final GrassyPotato grassyPotato;
    // private Object wantsToRaid;
    private int timechanneling;

    public MoveToDirtBlockGoal(GrassyPotato grassypotato) {
        super(grassypotato, 1, 12, 2);
        this.grassyPotato = grassypotato;
        this.nextStartTick = 120;
    }

    @Override
    public double acceptedDistance() {
        return 1.75D; // would like less... but...
    }

    @Override
    protected boolean isValidTarget(LevelReader pLevel, BlockPos pPos) {
        if (this.grassyPotato.isInSittingPose())
            return false;
        BlockState blockstate = pLevel.getBlockState(pPos);
        if (blockstate.is(Blocks.DIRT)) {
            var bs2 = pLevel.getBlockState(pPos.above());
            return bs2.is(Blocks.AIR);
        }
        return false;
    }


    @Override
    public boolean canContinueToUse() {
        if (this.grassyPotato.isLeashed()) return false;
        if (this.grassyPotato.isInSittingPose()) return false;
        return super.canContinueToUse();
    }

    @Override
    public boolean canUse() {
        if (this.grassyPotato.isLeashed()) return false;
        if (this.grassyPotato.isInSittingPose()) return false;
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
            grassyPotato.setChanneling(GrassyPotato.CHANNELING_PURIFYING);
            timechanneling++;
            if (timechanneling > 80) {
                Level level = this.grassyPotato.level;
                BlockState blockstate = level.getBlockState(this.blockPos);
                if (blockstate.is(Blocks.DIRT)) {
                    if (!level.isClientSide())
                        level.setBlock(this.blockPos, Blocks.GRASS_BLOCK.defaultBlockState(), 1 | 2);
                }
                this.nextStartTick = 60;
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
        grassyPotato.setChanneling(GrassyPotato.CHANNELING_NONE);
        timechanneling = 0;
    }

}