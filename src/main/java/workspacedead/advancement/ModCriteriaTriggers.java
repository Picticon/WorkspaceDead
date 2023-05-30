package workspacedead.advancement;

import net.minecraft.advancements.CriteriaTriggers;

import org.jetbrains.annotations.ApiStatus.Internal;
import org.jetbrains.annotations.ApiStatus.NonExtendable;

///
///  This class handles all the advancement triggers for the modpack
///

@NonExtendable
public interface ModCriteriaTriggers {
    public static final WaterCleanedTrigger WATER_CLEANED = CriteriaTriggers.register(new WaterCleanedTrigger());
    public static final PurificationTrigger PURIFICATION = CriteriaTriggers.register(new PurificationTrigger());

    @Internal
    static void register() {
    }
}