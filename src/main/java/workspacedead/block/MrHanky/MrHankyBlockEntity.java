package workspacedead.block.MrHanky;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;
import workspacedead.effect.ModEffects;
import workspacedead.registry.MyBlockEntities;

public class MrHankyBlockEntity extends BlockEntity implements IAnimatable {
    // private AnimationFactory factory = GeckoLibUtil.createFactory(this);
    private AnimationFactory factory = GeckoLibUtil.createFactory(this);

    public MrHankyBlockEntity(BlockPos pos, BlockState state) {
        super(MyBlockEntities.MRHANKY_BLOCK_ENTITY.get(), pos, state);
        // idleanim = new AnimationBuilder().addAnimation("idle",
        // EDefaultLoopTypes.LOOP);
    }

    private int cooldownTime = -1;

    public static void tick(Level level, BlockPos pos, BlockState state, MrHankyBlockEntity pBlockEntity) {
        --pBlockEntity.cooldownTime;

        if (!pBlockEntity.isOnCooldown()) {
            pBlockEntity.setCooldown(20);
            if (!level.isClientSide()) {
                pBlockEntity.applyEffect(level, pos, state);
            }
        }
    }

    private void applyEffect(Level level, BlockPos pos, BlockState state) {
        if (state.getValue(MrHankyBlock.ENABLED)) {
            var list = level.getEntitiesOfClass(Mob.class, AABB.ofSize(Vec3.atCenterOf(pos), 5, 5, 5));
            for (Mob mob : list) {
                mob.addEffect(new MobEffectInstance(ModEffects.DEADINSIDE.get(), 60));
            }
        }
    }

    private static String cooldownkey = "cooldown";

    public void load(CompoundTag pTag) {
        super.load(pTag);
        this.cooldownTime = pTag.getInt(cooldownkey);
    }

    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.putInt(cooldownkey, this.cooldownTime);
    }

    public void setCooldown(int pCooldownTime) {
        this.cooldownTime = pCooldownTime;
    }

    private boolean isOnCooldown() {
        return this.cooldownTime > 0;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(
                new AnimationController<MrHankyBlockEntity>(this, "controller", 0, this::predicate));
    }

    int _currentstate = -1;

    private <E extends BlockEntity & IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        var state = event.getAnimatable().getBlockState().getValue(MrHankyBlock.ENABLED);
        if (_currentstate != 1 && state) {
            event.getController().setAnimation(
                    new AnimationBuilder().addAnimation("animation.mrhanky.active", ILoopType.EDefaultLoopTypes.LOOP));
            _currentstate = 1;
        }
        if (_currentstate != 0 && !state) {
            event.getController().setAnimation(
                    new AnimationBuilder().addAnimation("animation.mrhanky.idle", ILoopType.EDefaultLoopTypes.LOOP));
            _currentstate = 0;
        }
        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}