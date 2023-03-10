package workspacedead.event;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import workspacedead.WorkspaceDead;
import workspacedead.entity.ModEntityTypes;
import workspacedead.entity.mob.BoneGolem;
import workspacedead.entity.mob.GrassyPotato;
import workspacedead.entity.mob.SkeletonChicken;
import workspacedead.entity.mob.SkeletonCow;
import workspacedead.entity.mob.SkeletonPig;
import workspacedead.entity.mob.SkeletonSheep;
import workspacedead.entity.mob.SkeletonSlime;
import workspacedead.entity.mob.SkeletonSpider;

@Mod.EventBusSubscriber(modid = WorkspaceDead.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        // CUSTOMMOBS //
        event.put(ModEntityTypes.SKELETONCOW.get(), SkeletonCow.setAttributes());
        event.put(ModEntityTypes.SKELETONCHICKEN.get(), SkeletonChicken.setAttributes());
        event.put(ModEntityTypes.SKELETONSHEEP.get(), SkeletonSheep.setAttributes());
        event.put(ModEntityTypes.SKELETONPIG.get(), SkeletonPig.setAttributes());
        event.put(ModEntityTypes.SKELETONSLIME.get(), SkeletonSlime.setAttributes());
        event.put(ModEntityTypes.SKELETONSPIDER.get(), SkeletonSpider.setAttributes());
        event.put(ModEntityTypes.GRASSYPOTATO.get(), GrassyPotato.setAttributes());
        event.put(ModEntityTypes.BONE_GOLEM.get(), BoneGolem.setAttributes());
        event.put(ModEntityTypes.DRACONICBLAZE.get(), BoneGolem.setAttributes());
    }

}