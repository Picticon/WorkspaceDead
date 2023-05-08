package workspacedead.world;

import com.google.common.collect.ImmutableSet;

import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import workspacedead.WorkspaceDead;
import workspacedead.block.ModBlocks;

public class ModPOIs {
    public static final DeferredRegister<PoiType> POI = DeferredRegister.create(ForgeRegistries.POI_TYPES,
            WorkspaceDead.MOD_ID);

    public static final RegistryObject<PoiType> POTATO_PORTAL = //
            POI.register("potato_portal", () -> new PoiType("potato_portal",
                    ImmutableSet.copyOf(ModBlocks.POTATOPORTALBLOCK.get().getStateDefinition().getPossibleStates()), 0,
                    1));

    public static void register(IEventBus eventBus) {
        POI.register(eventBus);
    }
}