package workspacedead.entity.mob;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;
import workspacedead.item.ModArmorMaterials;
import workspacedead.particle.ModParticles;
import workspacedead.registry.MySounds;

public class RottenPotato extends Monster implements IAnimatable {
    private static final EntityDataAccessor<Float> SCALESIZE = SynchedEntityData.defineId(RottenPotato.class,
            EntityDataSerializers.FLOAT);
    public static final EntityDataAccessor<Boolean> CHANNELING = SynchedEntityData.defineId(RottenPotato.class,
            EntityDataSerializers.BOOLEAN);

    public RottenPotato(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        // debugscale = .7f;
    }

    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);
    // private float debugscale;
    // private boolean isChanneling;

    public static AttributeSupplier setAttributes() {
        return Animal.createMobAttributes().add(Attributes.MAX_HEALTH, 20.0D).add(Attributes.ATTACK_DAMAGE, 3.0f)//
                .add(Attributes.ATTACK_SPEED, 2.0f).add(Attributes.MOVEMENT_SPEED, 0.2f).build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(3, new MoveToFarmlandBlockGoal(this));
        this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(4,
                new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, this::PlayerMissingArmor));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setAlertOthers());
    }

    boolean PlayerMissingArmor(LivingEntity pTarget) {
        if (!this.canAttack(pTarget))
            return false;
        if (pTarget.getType() != EntityType.PLAYER)
            return false;
        var player = (Player) pTarget;

        if (player.getInventory().getArmor(0).isEmpty())
            return true;
        if (player.getInventory().getArmor(1).isEmpty())
            return true;
        if (player.getInventory().getArmor(2).isEmpty())
            return true;
        if (player.getInventory().getArmor(3).isEmpty())
            return true;

        ArmorItem boots = ((ArmorItem) player.getInventory().getArmor(0).getItem());
        ArmorItem leggings = ((ArmorItem) player.getInventory().getArmor(1).getItem());
        ArmorItem breastplate = ((ArmorItem) player.getInventory().getArmor(2).getItem());
        ArmorItem helmet = ((ArmorItem) player.getInventory().getArmor(3).getItem());

        if (boots.getMaterial() == ModArmorMaterials.POTATO && //
                leggings.getMaterial() == ModArmorMaterials.POTATO && //
                breastplate.getMaterial() == ModArmorMaterials.POTATO && //
                helmet.getMaterial() == ModArmorMaterials.POTATO)
            return false;
        return true;
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SCALESIZE, .7f);
        this.entityData.define(CHANNELING, false);
    }

    public boolean isChanneling() {
        return this.entityData.get(CHANNELING);
    }

    public void setChanneling(boolean channeling) {
        // if (isChanneling() != channeling)
        // if (channeling)
        // WDServer.send("POTATO START channeling");
        // else
        // WDServer.send("POTATO STOP channeling");
        this.entityData.set(CHANNELING, channeling);
        // isChanneling = channeling;
    }

    // public float getSize() {
    // return this.debugscale;
    // }

    @Override
    public float getScale() {
        return this.entityData.get(SCALESIZE);
    }

    public float getRenderScale() {
        var s = getScale();
        if (s < 1)
            return 1 + ((s - 1) * 1.5f);
        else
            return 1 + ((s - 1) * 2.5f);
    }

    protected void setScale(float pSize, boolean pResetHealth) {
        // WDServer.send("Setting size to " + pSize);
        float f = Mth.clamp(pSize, .7f, 2f);
        this.entityData.set(SCALESIZE, f);
        // this.debugscale = f;
        this.refreshDimensions();
        this.reapplyPosition();
        if (pResetHealth) {
            this.setHealth(this.getMaxHealth());
        }
    }

    public void refreshDimensions() {
        double d0 = this.getX();
        double d1 = this.getY();
        double d2 = this.getZ();
        super.refreshDimensions();
        this.setPos(d0, d1, d2);
    }

    public void onSyncedDataUpdated(EntityDataAccessor<?> pKey) {
        if (SCALESIZE.equals(pKey)) {
            // this.debugscale = this.entityData.get(SCALESIZE);
            this.refreshDimensions();
            this.setYRot(this.yHeadRot);
            this.yBodyRot = this.yHeadRot;
            if (this.isInWater() && this.random.nextInt(20) == 0) {
                this.doWaterSplashEffect();
            }
        }
        super.onSyncedDataUpdated(pKey);
    }

    public EntityDimensions getDimensions(Pose pPose) {
        return super.getDimensions(pPose).scale(this.getScale());
    }

    public int getMaxHeadXRot() {
        return 0;
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putFloat("Scale", this.getScale());
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        this.setScale(pCompound.getFloat("Scale"), false);
        super.readAdditionalSaveData(pCompound);
    }

    @Override
    public void tick() {
        // if (this.tickCount % 40 == 0)
        // WDServer.send("POTATO " + this.goalSelector.getRunningGoals().count() + "
        // goals are running.");
        // if (this.tickCount % 400 == 0) {
        // var r = random.nextInt(12);
        // if (r < 4)
        // this.debugscale = .5f;
        // else if (r < 8)
        // this.debugscale = 1f;
        // else
        // this.debugscale = 1.5f;
        // this.debugscale = .7f + (r / 10f);
        // this.setSize(1f, true);
        // }
        super.tick();
        if (level.isClientSide && isChanneling()) {
            BlockState blockstate = level.getBlockState(this.getOnPos());
            if (blockstate == null || !blockstate.is(Blocks.DIRT))
                return;
            if (this.tickCount % 5 == 0)
                Particleify(level, this.getOnPos(), .9f);
        }
    }

    private void Particleify(Level level, BlockPos pos, double yoffset) {
        if (level.isClientSide()) {
            // create particles... client only, sad. not sure how to do it server side
            // (yet?)
            for (var i = random.nextInt(90); i < 360; i += 90) {
                var x = pos.getX() + 0.5d + Math.cos(i) * .9;
                var y = pos.getY() + .5 + yoffset;
                var z = pos.getZ() + 0.5d + Math.sin(i) * .9;
                level.addParticle(ModParticles.PURIFY_PARTICLES.get(), true, x, y, z, Math.cos(i) * -0.11d, -0.05d,
                        Math.sin(i) * -0.11d);
            }
        }
    }

    // public void onSyncedDataUpdated(EntityDataAccessor<?> pKey) {
    // if (ID_SIZE.equals(pKey)) {
    // this.refreshDimensions();
    // this.setYRot(this.yHeadRot);
    // this.yBodyRot = this.yHeadRot;
    // if (this.isInWater() && this.random.nextInt(20) == 0) {
    // this.doWaterSplashEffect();
    // }
    // }
    // super.onSyncedDataUpdated(pKey);
    // }

    // public EntityDimensions getDimensions(Pose pPose) {
    // WDServer.send("getdim " + super.getDimensions(pPose).scale(scalesize()));
    // return super.getDimensions(pPose).scale(scalesize());
    // }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (this.isChanneling()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.rottenpotato.channel",
                    ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.rottenpotato.walk",
                    ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }

        event.getController().setAnimation(
                new AnimationBuilder().addAnimation("animation.rottenpotato.idle", ILoopType.EDefaultLoopTypes.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        // this.playSound(ModSoundEvents., 0.15F, 1.0F);
    }

    protected SoundEvent getAmbientSound() {
        return MySounds.GRASSYPOTATO_AMBIENT.get();
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return MySounds.GRASSYPOTATO_HURT.get();
    }

    protected SoundEvent getDeathSound() {
        return MySounds.GRASSYPOTATO_DEATH.get();
    }

    protected float getSoundVolume() {
        return 0.2F;
    }

}
