package workspacedead.world;

import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import workspacedead.WorkspaceDead;
//import workspacedead.world.gen.ModEntityGeneration;

@Mod.EventBusSubscriber(modid = WorkspaceDead.MOD_ID)
public class ModWorldEvents {
    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent event) {
        //ModEntityGeneration.onEntitySpawn(event);
    }
}
