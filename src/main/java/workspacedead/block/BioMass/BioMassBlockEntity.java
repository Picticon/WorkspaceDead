package workspacedead.block.BioMass;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
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
import workspacedead.block.ModBlockEntities;

public class BioMassBlockEntity extends BlockEntity implements IAnimatable {
    //    private AnimationFactory factory = GeckoLibUtil.createFactory(this);
    private AnimationFactory factory =GeckoLibUtil.createFactory(this);

    public BioMassBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BIOMASS_BLOCK_ENTITY.get(), pos, state);
        //idleanim = new AnimationBuilder().addAnimation("idle", EDefaultLoopTypes.LOOP);
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<BioMassBlockEntity>
                (this, "controller", 0, this::predicate));
    }

    private <E extends BlockEntity & IAnimatable> PlayState predicate(AnimationEvent<E> event) {
//        var controller = event.getController();
//        controller.transitionLengthTicks = 0;
//        controller.setAnimation(idleanim);
        event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", ILoopType.EDefaultLoopTypes.LOOP));

        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}