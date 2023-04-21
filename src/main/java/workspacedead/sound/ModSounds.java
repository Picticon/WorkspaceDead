package workspacedead.sound;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import workspacedead.WorkspaceDead;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(
            ForgeRegistries.SOUND_EVENTS,
            WorkspaceDead.MOD_ID);

    public static RegistryObject<SoundEvent> PURIFY = registerSoundEvent("purify");
    public static RegistryObject<SoundEvent> SKELETONCOW_ATTACKS = registerSoundEvent("skeletoncow_attack");
    public static RegistryObject<SoundEvent> SKELETONCHICKEN_ATTACKS = registerSoundEvent("skeletonchicken_attack");
    public static RegistryObject<SoundEvent> SKELETONPIG_ATTACKS = registerSoundEvent("skeletonpig_attack");
    public static RegistryObject<SoundEvent> SKELETONSHEEP_ATTACKS = registerSoundEvent("skeletonsheep_attack");
    public static RegistryObject<SoundEvent> SKELETONSLIME_ATTACKS = registerSoundEvent("skeletonslime_attack");
    public static RegistryObject<SoundEvent> SKELETONSPIDER_ATTACKS = registerSoundEvent("skeletonspider_attack");

    public static RegistryObject<SoundEvent> GRASSYPOTATO_HURT = registerSoundEvent("grassypotato_hurt");
    public static RegistryObject<SoundEvent> GRASSYPOTATO_AMBIENT = registerSoundEvent("grassypotato_ambient");
    public static RegistryObject<SoundEvent> GRASSYPOTATO_DEATH = registerSoundEvent("grassypotato_death");

    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        return SOUND_EVENTS.register(name, () -> new SoundEvent(new ResourceLocation(WorkspaceDead.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}