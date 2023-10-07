package workspacedead.entity.mob;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import workspacedead.entity.ModEntityTypes;
import workspacedead.registry.MySounds;

public class SkeletonChicken extends SkeletonAnimal {

    public float flap;
    public float flapSpeed;
    public float oFlapSpeed;
    public float oFlap;
    public float flapping = 1.0F;
    private float nextFlap = 1.0F;

    public SkeletonChicken(EntityType<? extends SkeletonChicken> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setAggressive(true);
    }

    public SkeletonChicken(Level pLevel) {
        this(ModEntityTypes.SKELETONCHICKEN.get(), pLevel);
    }

    public static AttributeSupplier setAttributes() {
        return Animal.createMobAttributes().add(Attributes.MAX_HEALTH, 10).add(Attributes.ATTACK_DAMAGE, .5).add(Attributes.ATTACK_SPEED, 3).add(Attributes.MOVEMENT_SPEED, .4f).add(Attributes.SPAWN_REINFORCEMENTS_CHANCE).add(Attributes.FOLLOW_RANGE, 10.0D).build();
    }

    @Override
    protected void playStepSound(BlockPos pPos, BlockState pBlock) {
        this.playSound(SoundEvents.SKELETON_STEP, 0.15F, 1.0F);
    }

    // @Override
    // public boolean hurt(DamageSource pSource, float pAmount) {
    //     if (pSource.getEntity() instanceof Player) {
    //         var p = ((Player) pSource.getEntity());
    //         if (!p.level.isClientSide) {
    //             if (p.getMainHandItem() != null && p.getMainHandItem().is(Items.POTATO)) {
    //                 if (!p.getAbilities().instabuild) {
    //                     p.getMainHandItem().shrink(1);
    //                 }
    //                 var itemstack = new ItemStack(SpawnEggs.GRASSYPOTATO_SPAWN_EGG.get(), 1);
    //                 this.spawnAtLocation(itemstack);
    //                 this.playSound(SoundEvents.LAVA_POP, 1.0F, 1.0F);
    //                 this.setHealth(0);
    //             }
    //         }
    //     }
    //     return super.hurt(pSource, pAmount);
    // }

    public boolean doHurtTarget(Entity pEntity) {
        var flag = super.doHurtTarget(pEntity);
        if (flag) {
            this.level.broadcastEntityEvent(this, (byte) 4);
            this.playSound(MySounds.SKELETONCHICKEN_ATTACKS.get(), 1.0F, this.getVoicePitch());
        }
        return flag;
    }

    public void handleEntityEvent(byte pId) {
        if (pId == 4) {
            this.playSound(MySounds.SKELETONCHICKEN_ATTACKS.get(), 1.0F, this.getVoicePitch());
        } else {
            super.handleEntityEvent(pId);
        }

    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.SKELETON_HURT;
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.SKELETON_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.SKELETON_DEATH;
    }

    protected SoundEvent getStepSound() {
        return SoundEvents.SKELETON_STEP;
    }

    public MobType getMobType() {
        return MobType.UNDEAD;
    }

    /**
     * Called every tick so the entity can update its state as required. For
     * example, zombies and skeletons use this to react to sunlight and start to
     * burn.
     */
    public void aiStep() {
        super.aiStep();
        this.oFlap = this.flap;
        this.oFlapSpeed = this.flapSpeed;
        this.flapSpeed += (this.onGround ? -1.0F : 4.0F) * 0.3F;
        this.flapSpeed = Mth.clamp(this.flapSpeed, 0.0F, 1.0F);
        if (!this.onGround && this.flapping < 1.0F) {
            this.flapping = 1.0F;
        }

        this.flapping *= 0.9F;
        Vec3 vec3 = this.getDeltaMovement();
        if (!this.onGround && vec3.y < 0.0D) {
            this.setDeltaMovement(vec3.multiply(1.0D, 0.6D, 1.0D));
        }

        this.flap += this.flapping * 2.0F;
    }

    protected boolean isFlapping() {
        return this.flyDist > this.nextFlap;
    }

    protected void onFlap() {
        this.nextFlap = this.flyDist + this.flapSpeed / 2.0F;
    }

    /**
     * Called when the entity is attacked.
     */
    /*
     * public boolean hurt(DamageSource pSource, float pAmount) { if
     * (!super.hurt(pSource, pAmount)) { return false; } else if (!(this.level
     * instanceof ServerLevel)) { return false; } else { ServerLevel serverlevel =
     * (ServerLevel) this.level; LivingEntity livingentity = this.getTarget(); if
     * (livingentity == null && pSource.getEntity() instanceof LivingEntity) {
     * livingentity = (LivingEntity) pSource.getEntity(); }
     * 
     * int i = Mth.floor(this.getX()); int j = Mth.floor(this.getY()); int k =
     * Mth.floor(this.getZ());
     * net.minecraftforge.event.entity.living.ZombieEvent.SummonAidEvent event =
     * net.minecraftforge.event.ForgeEventFactory.fireZombieSummonAid(this, level,
     * i, j, k, livingentity,
     * this.getAttribute(Attributes.SPAWN_REINFORCEMENTS_CHANCE).getValue()); if
     * (event.getResult() == net.minecraftforge.eventbus.api.Event.Result.DENY)
     * return true; if (event.getResult() ==
     * net.minecraftforge.eventbus.api.Event.Result.ALLOW || livingentity != null &&
     * this.level.getDifficulty() == Difficulty.HARD && (double)
     * this.random.nextFloat() <
     * this.getAttribute(Attributes.SPAWN_REINFORCEMENTS_CHANCE).getValue() &&
     * this.level.getGameRules().getBoolean(GameRules.RULE_DOMOBSPAWNING)) { Zombie
     * zombie = event.getCustomSummonedAid() != null && event.getResult() ==
     * net.minecraftforge.eventbus.api.Event.Result.ALLOW ?
     * event.getCustomSummonedAid() : EntityType.ZOMBIE.create(this.level);
     * 
     * for (int l = 0; l < 50; ++l) { int i1 = i + Mth.nextInt(this.random, 7, 40) *
     * Mth.nextInt(this.random, -1, 1); int j1 = j + Mth.nextInt(this.random, 7, 40)
     * * Mth.nextInt(this.random, -1, 1); int k1 = k + Mth.nextInt(this.random, 7,
     * 40) * Mth.nextInt(this.random, -1, 1); BlockPos blockpos = new BlockPos(i1,
     * j1, k1); EntityType<?> entitytype = zombie.getType(); SpawnPlacements.Type
     * spawnplacements$type = SpawnPlacements.getPlacementType(entitytype); if
     * (NaturalSpawner.isSpawnPositionOk(spawnplacements$type, this.level, blockpos,
     * entitytype) && SpawnPlacements.checkSpawnRules(entitytype, serverlevel,
     * MobSpawnType.REINFORCEMENT, blockpos, this.level.random)) {
     * zombie.setPos((double) i1, (double) j1, (double) k1); if
     * (!this.level.hasNearbyAlivePlayer((double) i1, (double) j1, (double) k1,
     * 7.0D) && this.level.isUnobstructed(zombie) && this.level.noCollision(zombie)
     * && !this.level.containsAnyLiquid(zombie.getBoundingBox())) { if (livingentity
     * != null) zombie.setTarget(livingentity); zombie.finalizeSpawn(serverlevel,
     * this.level.getCurrentDifficultyAt(zombie.blockPosition()),
     * MobSpawnType.REINFORCEMENT, (SpawnGroupData) null, (CompoundTag) null);
     * serverlevel.addFreshEntityWithPassengers(zombie);
     * this.getAttribute(Attributes.SPAWN_REINFORCEMENTS_CHANCE).
     * addPermanentModifier(new
     * AttributeModifier("Zombie reinforcement caller charge", (double) -0.05F,
     * AttributeModifier.Operation.ADDITION));
     * zombie.getAttribute(Attributes.SPAWN_REINFORCEMENTS_CHANCE).
     * addPermanentModifier(new
     * AttributeModifier("Zombie reinforcement callee charge", (double) -0.05F,
     * AttributeModifier.Operation.ADDITION)); break; } } } }
     * 
     * return true; } }
     */

}
