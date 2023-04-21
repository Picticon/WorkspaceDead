package workspacedead.mixin.mixins;

import javax.annotation.Nullable;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.portal.PortalShape;
import net.minecraftforge.registries.ForgeRegistries;
import workspacedead.config.CommonConfig;

@Mixin(PortalShape.class)
public abstract class NetherPortalMixin {

    private NetherPortalMixin() {
    }

    @Shadow
    private LevelAccessor level;

    @Nullable
    @Shadow
    private BlockPos bottomLeft;

    @Inject(at = @At("HEAD"), method = "createPortalBlocks", cancellable = true)
    private void createPortalBlocks(CallbackInfo info) {
        // info.cancel();
        try {
            var item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(CommonConfig.nether_opener.get()));
            if (item != null && item != Items.AIR) {
                var nearestPlayer = this.level.getNearestPlayer((double) bottomLeft.getX(), (double) bottomLeft.getY(),
                        (double) bottomLeft.getZ(), -1.0, false);
                if (nearestPlayer != null) {
                    if (!nearestPlayer.getInventory().contains(new ItemStack(item))) {
                        level.playSound(null, bottomLeft, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 1.0f, 0.75f);
                        nearestPlayer.displayClientMessage(new TextComponent("You need to have a "
                                + item.getDescription().getString() + " in your inventory to light the portal."), true);
                        info.cancel();
                    }
                }
            }
        } catch (Exception e) {
        }
    }
}
