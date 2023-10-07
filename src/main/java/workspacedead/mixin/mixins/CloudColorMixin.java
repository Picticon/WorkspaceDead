package workspacedead.mixin.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.phys.Vec3;
import workspacedead.config.CommonConfig;

@Mixin(ClientLevel.class)
public abstract class CloudColorMixin {
    private CloudColorMixin() {
    }

    @Inject(at = @At("HEAD"), method = "getCloudColor", cancellable = true)
    public void getCloudColor(float pPartialTick, CallbackInfoReturnable<Vec3> cir) {
        if (CommonConfig.cloud_color_cache != null)
            cir.setReturnValue(CommonConfig.cloud_color_cache);
    }
}
