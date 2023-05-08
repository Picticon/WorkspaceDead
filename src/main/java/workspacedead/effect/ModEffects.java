package workspacedead.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import workspacedead.WorkspaceDead;;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOD_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS,
            WorkspaceDead.MOD_ID);

    public static final RegistryObject<MobEffect> DEADINSIDE = MOD_EFFECTS.register("deadinside",
            () -> new DeadInsideEffect(MobEffectCategory.HARMFUL, 0x334466));
    
            public static final RegistryObject<MobEffect> DOOMED = MOD_EFFECTS.register("doomed",
            () -> new DoomedEffect(MobEffectCategory.HARMFUL, 0x334466));

    public static void register(IEventBus eventBus) {
        MOD_EFFECTS.register(eventBus);
    }
}
