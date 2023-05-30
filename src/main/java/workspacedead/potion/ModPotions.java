package workspacedead.potion;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import workspacedead.WorkspaceDead;
import workspacedead.effect.ModEffects;

public class ModPotions {
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS,
            WorkspaceDead.MOD_ID);

    public static final RegistryObject<Potion> DEADINSIDE_POTION = POTIONS.register("deadinside_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.DEADINSIDE.get(), 200*60, 0)));

    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }
}