package workspacedead.mixin.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import workspacedead.registry.MyBlocks;

@Mixin(CropBlock.class)
public abstract class CropBlockMixin {
    private CropBlockMixin() {
    }

    @Inject(at = @At("HEAD"), method = "mayPlaceOn", cancellable = true)
    protected void mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos,
            CallbackInfoReturnable<Boolean> cir) {
        if (pState.is(MyBlocks.DEAD_FARMLAND.get())) {
            cir.setReturnValue(true);
        }
    }
}
