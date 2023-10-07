package workspacedead.entity.mob;

import java.util.Random;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import workspacedead.registry.MySounds;

public class SkeletonSlime extends Slime {

    public SkeletonSlime(EntityType<? extends Slime> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static AttributeSupplier setAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 20).add(Attributes.ATTACK_DAMAGE, 2).add(Attributes.ATTACK_SPEED, 4).add(Attributes.MOVEMENT_SPEED, .2f).add(Attributes.SPAWN_REINFORCEMENTS_CHANCE).add(Attributes.FOLLOW_RANGE, 15.0D).build();
    }

    public static boolean checkSlimeSpawnRules2(EntityType<SkeletonSlime> pSlime, ServerLevelAccessor pLevel, MobSpawnType pSpawnType, BlockPos pPos, Random pRandom) {
        return Slime.checkSlimeSpawnRules(EntityType.SLIME, pLevel, pSpawnType, pPos, pRandom);
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.SKELETON_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.SKELETON_DEATH;
    }

    @Override
    protected SoundEvent getSquishSound() {
        return SoundEvents.SKELETON_STEP;
    }

    @Override
    protected SoundEvent getJumpSound() {
        return SoundEvents.SKELETON_STEP;
    }

    @Override
    protected ParticleOptions getParticleType() {
        // return ParticleTypes.DAMAGE_INDICATOR;
        int i = Mth.floor(this.getX());
        int j = Mth.floor(this.getY() - (double) 0.2F);
        int k = Mth.floor(this.getZ());
        BlockPos blockpos = new BlockPos(i, j, k);
        BlockState blockstate = this.level.getBlockState(blockpos);
        if (!blockstate.addRunningEffects(level, blockpos, this))
            if (blockstate.getRenderShape() != RenderShape.INVISIBLE) {
                return new BlockParticleOption(ParticleTypes.BLOCK, blockstate);
            }
        return ParticleTypes.CRIT;
    }

    @Override
    protected void dealDamage(LivingEntity pLivingEntity) {
        if (this.isAlive()) {
            int i = this.getSize();
            if (this.distanceToSqr(pLivingEntity) < 0.6D * (double) i * 0.6D * (double) i && this.hasLineOfSight(pLivingEntity) && pLivingEntity.hurt(DamageSource.mobAttack(this), this.getAttackDamage())) {
                this.playSound(MySounds.SKELETONSLIME_ATTACKS.get(), 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
                this.doEnchantDamageEffects(this, pLivingEntity);
            }
        }

    }

}
