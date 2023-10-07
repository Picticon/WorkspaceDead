package workspacedead.client;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import workspacedead.block.SpawnEggPlant.SpawnEggBlockEntity;
import workspacedead.item.custom.SpawnEggSeedsItem;
import workspacedead.registry.MyBlocks;
import workspacedead.registry.MyItems;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientProxy {

    // controls custom coloring for items
    // includes the spawnegg seed
    @SubscribeEvent
    static void registerItemColors(final ColorHandlerEvent.Item event) {
        event.getItemColors().register((stack, tintIndex) -> {
            if (tintIndex == 1 || tintIndex == 2) {
                SpawnEggItem info = null;
                if (stack.hasTag() && stack.getTag().contains(SpawnEggSeedsItem.EssenceTagID)) {
                    ResourceLocation id = new ResourceLocation(
                            stack.getTag().getString(SpawnEggSeedsItem.EssenceTagID));
                    info = ForgeSpawnEggItem.fromEntityType(ForgeRegistries.ENTITIES.getValue(id));
                }
                if (info != null) {
                    var r = (SpawnEggSeedsItem) MyItems.SPAWNEGG_SEEDS.get();
                    if (r.canBeExtracted(info.getType(new CompoundTag()))) {
                        return info.getColor(tintIndex - 1);
                    }
                }
                return tintIndex == 1 ? 0x6b320f : 0x4b2a17;
            }
            return 0xFFFFFF;
        }, MyItems.SPAWNEGG_SEEDS.get());
    }

    // controls custom coloring for blocks
    // includes the spawnegg crop
    @SubscribeEvent
    static void registerBlockColors(final ColorHandlerEvent.Block event) {
        event.getBlockColors().register((state, world, pos, tintIndex) -> {
            if (world != null && pos != null) {
                BlockEntity te = world.getBlockEntity(pos);
                if (te instanceof SpawnEggBlockEntity sebe) {
                    if (tintIndex == 1 || tintIndex == 2) {
                        var entityid = sebe.getEntityID();
                        SpawnEggItem info = null;
                        ResourceLocation id = new ResourceLocation(entityid);
                        info = ForgeSpawnEggItem.fromEntityType(ForgeRegistries.ENTITIES.getValue(id));
                        if (info != null) {
                            var r = (SpawnEggSeedsItem) MyItems.SPAWNEGG_SEEDS.get();
                            if (r.canBeExtracted(info.getType(new CompoundTag()))) {
                                return info.getColor(tintIndex - 1);
                            }
                        }
                    }
                }
            }
            return -1;
        }, MyBlocks.SPAWNEGG_PLANT.get());
    }

    @SubscribeEvent
    public void onGameOverlay(RenderGameOverlayEvent.Post event) {
        // OverlayRegistry 
        // if (event.getType() == RenderGameOverlayEvent.ElementType.ALL && Minecraft.getInstance().screen == null) {
        //     Minecraft minecraft = Minecraft.getInstance();
        //     Entity entity = minecraft.crosshairPickEntity;

        //     if (entity instanceof IOverlayHandler) {
        //         ((IOverlayHandler) entity).drawOverlay();
        //     }
        // }
    }
}
