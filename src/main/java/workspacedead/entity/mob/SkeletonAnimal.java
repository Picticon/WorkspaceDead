package workspacedead.entity.mob;

import java.util.Random;

import net.minecraft.core.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import workspacedead.block.ModBlocks;

public class SkeletonAnimal extends Monster {

    protected SkeletonAnimal(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static boolean checkAnimalSpawnRules(EntityType<? extends Monster> pType, LevelAccessor pLevel,
            MobSpawnType pSpawnType, BlockPos pPos, Random pRandom) {
        return ((pLevel.getBlockState(pPos.below()).is(ModBlocks.DEADDIRT.get()) || //
                pLevel.getBlockState(pPos.below()).is(ModBlocks.DEADSAND.get()) || //
                pLevel.getBlockState(pPos.below()).is(ModBlocks.DEADSTONE.get()) || //
                pLevel.getBlockState(pPos.below()).is(ModBlocks.DEADCLAY.get()))  //
                && pLevel.getDifficulty() != Difficulty.PEACEFUL
                && checkMobSpawnRules(pType, pLevel, pSpawnType, pPos, pRandom));
    }

    @Override
    public float getWalkTargetValue(BlockPos pPos, LevelReader pLevel) {
        return 1; // ignore light
        // return super.getWalkTargetValue(pPos, pLevel);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers(SkeletonCow.class));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

}
