package workspacedead.entity.mob;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
//import net.minecraft.world.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
//import net.minecraft.world.entity.ai.goal.SitWhenOrderedToGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;
import workspacedead.entity.ModEntityTypes;
import workspacedead.particle.ModParticles;
import workspacedead.registry.MySounds;

public class GrassyPotato extends TamableAnimal implements IAnimatable {
    public static final EntityDataAccessor<Byte> CHANNELINGMODE = SynchedEntityData.defineId(GrassyPotato.class,
            EntityDataSerializers.BYTE);
    public static final byte CHANNELING_NONE = 0;
    public static final byte CHANNELING_PURIFYING = 1;
    public static final byte CHANNELING_BONEMEALING = 2;
    public static final byte CHANNELING_COOLDOWN = 3;
    private static final EntityDataAccessor<Float> SCALESIZE = SynchedEntityData.defineId(GrassyPotato.class,
            EntityDataSerializers.FLOAT);
    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);
    private static final float GROWSIZE = .05f;
    private static final float SHRINKSIZE = -.01f;
    private static final float MINSIZE = .7f;
    private static final float MAXSIZE = 1.75f;

    public GrassyPotato(EntityType<? extends TamableAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        // debugscale = .7f;
    }
    // private float debugscale;
    // private boolean isChanneling;

    public static AttributeSupplier setAttributes() {
        return Animal.createMobAttributes().add(Attributes.MAX_HEALTH, 20.0D).add(Attributes.ATTACK_DAMAGE, 1.0f)//
                .add(Attributes.ATTACK_SPEED, 2.0f).add(Attributes.MOVEMENT_SPEED, 0.2f).build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        //this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(3, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.3D, false));
        this.goalSelector.addGoal(5, new MoveToDirtBlockGoal(this));
        this.goalSelector.addGoal(6, new MoveToPlantBlockGoal(this));
        this.goalSelector.addGoal(7, new MoveToBiomassBlockGoal(this));
        // this.goalSelector.addGoal(7, new FollowOwnerGoal(this, .5D, 10.0F, 2.0F, false));
        this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(11, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, KillerDonut.class, true));
    }

    // no babies
    public void setBaby(boolean pBaby) {
        this.setAge(0);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SCALESIZE, MINSIZE);
        this.entityData.define(CHANNELINGMODE, (byte) 0);
    }

    public boolean isAtMinimumSize() {
        return getScale() <= MINSIZE;
    }

    public boolean isAtOptimalSize() {
        return getScale() >= ((MAXSIZE - MINSIZE) * .3f) + MINSIZE;
    }

    public boolean isAtMaximumSize() {
        return getScale() >= MAXSIZE;
    }

    public byte getChanneling() {
        return this.entityData.get(CHANNELINGMODE);
    }

    public void setChanneling(byte channeling) {
        this.entityData.set(CHANNELINGMODE, channeling);
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

    public EntityDimensions getDimensions(Pose pPose) {
        return new EntityDimensions(super.getDimensions(pPose).width * .65f,
                super.getDimensions(pPose).height * this.getScale(), false);
        //return super.getDimensions(pPose).scale(this.getScale());
    }

    protected void setScale(float pSize, boolean pResetHealth) {
        // WDServer.send("Setting size to " + pSize);
        float f = Mth.clamp(pSize, MINSIZE, MAXSIZE);
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
        //var a = 5;
        //var b = a + 2;
        //Chatter.sendToAllPlayers("b=" + b);
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
        if (level.isClientSide
                && (getChanneling() == CHANNELING_PURIFYING || getChanneling() == CHANNELING_BONEMEALING)) {
            // BlockState blockstate = level.getBlockState(this.getOnPos());
            //if (blockstate == null || !blockstate.is(Blocks.DIRT))
            //    return;
            if (this.tickCount % 8 == 0)
                Particleify(level, this.getOnPos(), .9f);
        }
    }

    public void Particleify(Level level, BlockPos pos, double yoffset) {
        if (level.isClientSide()) {
            // create particles... client only, sad. not sure how to do it server side
            // (yet?)
            for (var i = random.nextInt(90); i < 360; i += 90) {
                var x = pos.getX() + 0.5d + Math.cos(i) * .9;
                var y = pos.getY() + .5 + yoffset;
                var z = pos.getZ() + 0.5d + Math.sin(i) * .9;
                if (getChanneling() == CHANNELING_PURIFYING)
                    level.addParticle(ModParticles.PURIFY_PARTICLES.get(), true, x, y, z, Math.cos(i) * -0.05d, -0.05d,
                            Math.sin(i) * -0.05d);
                else
                    level.addParticle(ParticleTypes.COMPOSTER.getType(), true, x, y, z, Math.cos(i) * -0.05d, -0.05d,
                            Math.sin(i) * -0.05d);
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

        if (this.isInSittingPose()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.grassypotato.sit",
                    ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }

        if (this.getChanneling() == CHANNELING_BONEMEALING || this.getChanneling() == CHANNELING_PURIFYING) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.grassypotato.channel",
                    ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.grassypotato.walk",
                    ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }

        event.getController().setAnimation(
                new AnimationBuilder().addAnimation("animation.grassypotato.idle", ILoopType.EDefaultLoopTypes.LOOP));
        return PlayState.CONTINUE;
    }

    // public void refreshDimensions() {
    // double d0 = this.getX();
    // double d1 = this.getY();
    // double d2 = this.getZ();
    // super.refreshDimensions();
    // this.setPos(d0, d1, d2);
    // }

    public InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        if (this.level.isClientSide) {
            // not sure what this all does... I think it shows the animation for some states
            // java logical operation order is fucky... this.isTame() && !this.isTame() //
            // WTF
            boolean flag = this.isOwnedBy(pPlayer) || this.isTame() || itemstack.is(Items.BONE_MEAL) && !this.isTame();
            return flag ? InteractionResult.CONSUME : InteractionResult.PASS;
        } else {
            // server things
            if (this.isTame()) {
                if (itemstack.is(Items.BONE_MEAL) && !this.isAtMaximumSize()) {
                    addBonemeal();
                    if (!pPlayer.getAbilities().instabuild) {
                        itemstack.shrink(1);
                    }
                    return InteractionResult.SUCCESS;
                } else {
                    if (this.isFood(itemstack) && this.getHealth() < this.getMaxHealth()) {
                        this.heal((float) itemstack.getFoodProperties(this).getNutrition());
                        if (!pPlayer.getAbilities().instabuild) {
                            itemstack.shrink(1);
                        }
                        this.gameEvent(GameEvent.MOB_INTERACT, this.eyeBlockPosition());
                        return InteractionResult.SUCCESS;
                    }
                    InteractionResult interactionresult = super.mobInteract(pPlayer, pHand);
                    if ((!interactionresult.consumesAction() || this.isBaby()) && this.isOwnedBy(pPlayer)) {
                        //this.setOrderedToSit(!this.isOrderedToSit());
                        this.setOrderedToSit(false);
                        this.jumping = false;
                        this.navigation.stop();
                        this.setTarget((LivingEntity) null);
                        return InteractionResult.SUCCESS;
                    }
                    return interactionresult;
                }
            } else if (itemstack.is(Items.BONE_MEAL)) {
                if (!pPlayer.getAbilities().instabuild) {
                    itemstack.shrink(1);
                }
                if (this.random.nextInt(1) == 0
                        && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, pPlayer)) {
                    this.tame(pPlayer);
                    this.navigation.stop();
                    this.setTarget((LivingEntity) null);
                    //this.setOrderedToSit(true);
                    this.setOrderedToSit(false);
                    this.level.broadcastEntityEvent(this, (byte) 7);
                } else {
                    this.level.broadcastEntityEvent(this, (byte) 6);
                }

                return InteractionResult.SUCCESS;
            } else if (itemstack.is(Items.ROTTEN_FLESH)) {
                if (!pPlayer.getAbilities().instabuild) {
                    itemstack.shrink(1);
                }
                var newent = ModEntityTypes.ROTTENPOTATO.get();
                if (newent != null) {
                    var heyhey = newent.create(pPlayer.level);
                    if (heyhey != null) {
                        heyhey.moveTo(this.blockPosition(), this.getYRot(), this.getXRot());
                        if (heyhey instanceof LivingEntity) {
                            ((LivingEntity) heyhey).addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 120));
                            ((LivingEntity) heyhey).addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 120));
                            ((LivingEntity) heyhey).setSpeed(0);
                        }
                        pPlayer.level.addFreshEntity(heyhey);
                        heyhey.setXRot(this.getXRot());
                        heyhey.setYRot(this.getYRot());
                        this.discard();
                    }
                }
                return InteractionResult.SUCCESS;
            }
            return super.mobInteract(pPlayer, pHand);
        }
    }

    @Override
    public boolean isFood(ItemStack pStack) {
        Item item = pStack.getItem();
        return item instanceof BoneMealItem;
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

    @Override
    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return null;
    }

    public void useBonemeal() {
        this.setScale(this.getScale() + SHRINKSIZE, false);
    }

    public void addBonemeal() {
        if (!this.isAtMaximumSize())
            this.setScale(this.getScale() + GROWSIZE, true);
    }

}
