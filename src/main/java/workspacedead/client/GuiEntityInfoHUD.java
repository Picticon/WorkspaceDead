package workspacedead.client;

import net.minecraft.client.Minecraft;
import net.minecraft.world.level.GameType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.client.gui.IIngameOverlay;

/**
 * 
 */

public class GuiEntityInfoHUD {

    public static final IIngameOverlay OVERLAY = (gui, poseStack, partialTicks, width, height) -> {
        Minecraft mc = Minecraft.getInstance();
        if (mc.options.hideGui || mc.gameMode.getPlayerMode() == GameType.SPECTATOR)
            return;
        HitResult objectMouseOver = mc.hitResult;
        if (objectMouseOver instanceof BlockHitResult hitResult) {
            var hovering = hitResult.getBlockPos();
            if (mc.level.getBlockEntity(hovering) instanceof IPowerHUD iHud) {
                //int x = ManaConfig.MANA_HUD_X.get();
                //int y = ManaConfig.MANA_HUD_Y.get();
                // int x = 10;
                // int y = 10;
                var toDisplay = iHud.getMessage();
                var w = gui.getFont().width(toDisplay);
                var ww = mc.getWindow().getGuiScaledWidth();
                var wh = mc.getWindow().getGuiScaledHeight();
                gui.getFont().drawShadow(poseStack, toDisplay, (ww / 2) - w - 20, (wh / 2), 0xffffff);
            }
        }
    };
}
