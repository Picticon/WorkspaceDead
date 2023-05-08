package workspacedead.mobs;

import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.EntityTeleportEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import workspacedead.effect.ModEffects;

@Mod.EventBusSubscriber()
public class ModMobs {
    // if an entity is trying to teleport with the DEADINSIDE or DOOMED effect,
    // cancel the
    // teleport.
    @SubscribeEvent
    public static void onEnderTeleport(EntityTeleportEvent.EnderEntity event) {
        if (event.getEntity().getLevel().isClientSide())
            return;
        if (event.getEntity() instanceof LivingEntity) {
            var livingEntity = (LivingEntity) event.getEntity();
            if (livingEntity.hasEffect(ModEffects.DEADINSIDE.get())) {
                event.setCanceled(true);
            }
            if (livingEntity.hasEffect(ModEffects.DOOMED.get())) {
                event.setCanceled(true);
            }
        }
    }
}