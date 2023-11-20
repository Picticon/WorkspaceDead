package workspacedead.entity.mob;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import workspacedead.block.BioMass.BioMassBlock;

public class MoveToBiomassBlockGoal extends MoveToBlockGoal {
    private final GrassyPotato grassyPotato;

    public MoveToBiomassBlockGoal(GrassyPotato grassypotato) {
        super(grassypotato, 1, 12, 3);
        this.grassyPotato = grassypotato;
        this.nextStartTick = 60;
    }

    @Override
    public double acceptedDistance() {
        return 1.75D; // would like less... but...
    }

    @Override
    protected boolean isValidTarget(LevelReader pLevel, BlockPos pPos) {
        if (this.grassyPotato.isAtOptimalSize())
            return false;
        if (this.grassyPotato.isLeashed())
            return false;
        if (this.grassyPotato.isInSittingPose())
            return false;
        BlockState blockstate = pLevel.getBlockState(pPos);
        return (blockstate.getBlock() instanceof BioMassBlock);
    }

    @Override
    public boolean canContinueToUse() {
        if (this.grassyPotato.isAtOptimalSize())
            return false;
        if (this.grassyPotato.isLeashed())
            return false;
        if (this.grassyPotato.isInSittingPose())
            return false;
        return super.canContinueToUse();
    }

    @Override
    public boolean canUse() {
        if (this.grassyPotato.isAtOptimalSize())
            return false;
        if (this.grassyPotato.isLeashed())
            return false;
        if (this.grassyPotato.isInSittingPose())
            return false;
        return super.canUse();
    }

    @Override
    public void tick() {
        super.tick();
        if (this.isReachedTarget() && this.canUse()) {
            for (var i = 0; i < 10 && !this.grassyPotato.isAtOptimalSize(); i++) {
                this.grassyPotato.addBonemeal();
            }
        }
    }
}