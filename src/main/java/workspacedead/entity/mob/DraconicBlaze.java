package workspacedead.entity.mob;

import java.util.EnumSet;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MoveTowardsRestrictionGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class DraconicBlaze extends Monster {
    // private static final EntityDataAccessor<Byte> DATA_FLAGS_ID =
    // SynchedEntityData.defineId(Blaze.class, EntityDataSerializers.BYTE);
    private float allowedHeightOffset = 0.5F;
    private int nextHeightOffsetChangeTick;

    public DraconicBlaze(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(4, new DraconicBlaze.DraconicBlazeAttackGoal(this));
        this.goalSelector.addGoal(5, new MoveTowardsRestrictionGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D, 0.0F));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.ATTACK_DAMAGE, 6.0D)//
                .add(Attributes.MOVEMENT_SPEED, (double) 0.23F).add(Attributes.FOLLOW_RANGE, 48.0D);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENDER_DRAGON_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.ENDER_DRAGON_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.BLAZE_DEATH;
    }

    @Override
    public float getBrightness() {
        return .25F;
    }

    @Override
    public boolean isSensitiveToWater() {
        return false;
    }

    @Override
    public void aiStep() {
        if (!this.onGround && this.getDeltaMovement().y < 0.0D) {
            this.setDeltaMovement(this.getDeltaMovement().multiply(1.0D, 0.6D, 1.0D));
        }

        if (this.level.isClientSide) {
            // if (this.random.nextInt(24) == 0 && !this.isSilent()) {
            // this.level.playLocalSound(this.getX() + 0.5D, this.getY() + 0.5D, this.getZ()
            // + 0.5D, SoundEvents.BLAZE_BURN, this.getSoundSource(), 1.0F +
            // this.random.nextFloat(), this.random.nextFloat() * 0.7F + 0.3F, false);
            // }
            // for (int i = 0; i < 2; ++i) {
            if (this.getRandom().nextFloat() > .75f)
                this.level.addParticle(ParticleTypes.DRAGON_BREATH, this.getRandomX(0.5D), this.getRandomY(), this.getRandomZ(0.5D), 0.0D, 0.0D, 0.0D);
            // }

        }

        super.aiStep();
    }

    @Override
    protected void customServerAiStep() {
        --this.nextHeightOffsetChangeTick;
        if (this.nextHeightOffsetChangeTick <= 0) {
            this.nextHeightOffsetChangeTick = 100;
            this.allowedHeightOffset = 0.5F + (float) this.random.nextGaussian() * 3.0F;
        }

        LivingEntity livingentity = this.getTarget();
        if (livingentity != null && livingentity.getEyeY() > this.getEyeY() + (double) this.allowedHeightOffset && this.canAttack(livingentity)) {
            Vec3 vec3 = this.getDeltaMovement();
            this.setDeltaMovement(this.getDeltaMovement().add(0.0D, ((double) 0.3F - vec3.y) * (double) 0.3F, 0.0D));
            this.hasImpulse = true;
        }

        super.customServerAiStep();
    }

    public boolean causeFallDamage(float pFallDistance, float pMultiplier, DamageSource pSource) {
        return false;
    }

    // public boolean isOnFire() {
    // return super.isOnFire();
    // }

    // private boolean isCharged2() {
    // return (this.entityData.get(DATA_FLAGS_ID) & 1) != 0;
    // }

    // void setCharged(boolean pCharged) {
    // super.setCharged(pCharged);
    // // byte b0 = this.entityData.get(DATA_FLAGS_ID);
    // // if (pCharged) {
    // // b0 = (byte) (b0 | 1);
    // // } else {
    // // b0 = (byte) (b0 & -2);
    // // }

    // // this.entityData.set(DATA_FLAGS_ID, b0);
    // }

    static class DraconicBlazeAttackGoal extends Goal {
        private final DraconicBlaze blaze;
        private int attackStep;
        private int attackTime;
        private int lastSeen;

        public DraconicBlazeAttackGoal(DraconicBlaze pBlaze) {
            this.blaze = pBlaze;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state
         * necessary for execution in this method as well.
         */
        public boolean canUse() {
            LivingEntity livingentity = this.blaze.getTarget();
            return livingentity != null && livingentity.isAlive() && this.blaze.canAttack(livingentity);
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void start() {
            this.attackStep = 0;
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by
         * another one
         */
        public void stop() {
            // this.blaze.setCharged(false);
            this.lastSeen = 0;
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            --this.attackTime;
            LivingEntity targetentity = this.blaze.getTarget();
            if (targetentity != null) {
                boolean canseetarget = this.blaze.getSensing().hasLineOfSight(targetentity);
                if (canseetarget) {
                    this.lastSeen = 0;
                } else {
                    ++this.lastSeen;
                }

                double sqrdistance = this.blaze.distanceToSqr(targetentity);
                if (sqrdistance < 4.0D) {
                    if (!canseetarget) {
                        return;
                    }

                    if (this.attackTime <= 0) {
                        this.attackTime = 20;
                        this.blaze.doHurtTarget(targetentity);
                    }

                    this.blaze.getMoveControl().setWantedPosition(targetentity.getX(), targetentity.getY(), targetentity.getZ(), 1.0D);
                } else if (sqrdistance < this.getFollowDistance() * this.getFollowDistance() && canseetarget) {
                    double d1 = targetentity.getX() - this.blaze.getX();
                    double d2 = targetentity.getY() - this.blaze.getY(0.5D); // shoot at feet
                    double d3 = targetentity.getZ() - this.blaze.getZ();
                    if (this.attackTime <= 0) {
                        ++this.attackStep;
                        if (this.attackStep == 1) {
                            // charging balls
                            this.attackTime = 60;
                            // this.blaze.setCharged2(true);
                        } else if (this.attackStep <= 2) {
                            // firing (one ball please)
                            this.attackTime = 6;
                        } else {
                            // delay
                            this.attackTime = 200;
                            this.attackStep = 0;
                            // this.blaze.setCharged2(false);
                        }

                        if (this.attackStep > 1) {
                            // shoot ball
                            // double d4 = Math.sqrt(Math.sqrt(sqrdistance)) * 0.5D;
                            if (!this.blaze.isSilent()) {
                                this.blaze.level.levelEvent((Player) null, 1018, this.blaze.blockPosition(), 0);
                            }

                            // for (int i = 0; i < 1; ++i) {
                            var smallfireball = new DraconicBlazeBall(this.blaze.level, this.blaze, //
                                    d1, // + this.blaze.getRandom().nextGaussian() * d4, //
                                    d2, //
                                    d3 // + this.blaze.getRandom().nextGaussian() * d4
                            );
                            smallfireball.setPos(smallfireball.getX(), this.blaze.getY(0.5D) + 0.5D, smallfireball.getZ());
                            this.blaze.level.addFreshEntity(smallfireball);
                            // }
                        }
                    }

                    this.blaze.getLookControl().setLookAt(targetentity, 10.0F, 10.0F);
                } else if (this.lastSeen < 5) {
                    this.blaze.getMoveControl().setWantedPosition(targetentity.getX(), targetentity.getY(), targetentity.getZ(), 1.0D);
                }

                super.tick();
            }
        }

        private double getFollowDistance() {
            return this.blaze.getAttributeValue(Attributes.FOLLOW_RANGE);
        }
    }
}
