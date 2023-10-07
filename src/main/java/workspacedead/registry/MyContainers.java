package workspacedead.registry;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import workspacedead.WorkspaceDead;
import workspacedead.block.generators.YellowPowergenContainer;

public class MyContainers {

    public static final DeferredRegister<MenuType<?>> MOD_CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS,
            WorkspaceDead.MOD_ID);

    public static void register(IEventBus eventBus) {
        MOD_CONTAINERS.register(eventBus);
    }

    public static final RegistryObject<MenuType<YellowPowergenContainer>> YELLOW_POWERGEN_CONTAINER = MOD_CONTAINERS
            .register("yellowpowergen", () -> IForgeMenuType.create(
                    (windowId, inv, data) -> new YellowPowergenContainer(windowId, data.readBlockPos(), inv, inv.player)));

}
