package workspacedead.entity.mob;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import workspacedead.entity.ModEntityTypes;
import workspacedead.sound.ModSounds;

public class SkeletonSheep extends SkeletonAnimal {

    public SkeletonSheep(EntityType<? extends SkeletonSheep> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setAggressive(true);
    }

    public SkeletonSheep(Level pLevel) {
        this(ModEntityTypes.SKELETONSHEEP.get(), pLevel);
    }

    public static AttributeSupplier setAttributes() {
        return Animal.createMobAttributes().add(Attributes.MAX_HEALTH, 20).add(Attributes.ATTACK_DAMAGE, 2).add(Attributes.ATTACK_SPEED, 4).add(Attributes.MOVEMENT_SPEED, .2f).add(Attributes.SPAWN_REINFORCEMENTS_CHANCE).add(Attributes.FOLLOW_RANGE, 15.0D).build();
    }

    @Override
    protected void playStepSound(BlockPos pPos, BlockState pBlock) {
        this.playSound(SoundEvents.SKELETON_STEP, 0.15F, 1.0F);
    }

    public boolean doHurtTarget(Entity pEntity) {
        var flag = super.doHurtTarget(pEntity);
        if (flag) {
            this.level.broadcastEntityEvent(this, (byte) 4);
            this.playSound(ModSounds.SKELETONSHEEP_ATTACKS.get(), 1.0F, this.getVoicePitch());
        }
        return flag;
    }

    public void handleEntityEvent(byte pId) {
        if (pId == 4) {
            this.playSound(ModSounds.SKELETONSHEEP_ATTACKS.get(), 1.0F, this.getVoicePitch());
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
