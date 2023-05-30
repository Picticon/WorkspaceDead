package workspacedead.advancement;

import org.jetbrains.annotations.NotNull;
import com.google.gson.JsonObject;
import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
import net.minecraft.advancements.critereon.DeserializationContext;
import net.minecraft.advancements.critereon.EntityPredicate;

// import org.jetbrains.annotations.NotNull;

// import com.google.gson.JsonObject;

// import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
// import net.minecraft.advancements.critereon.DeserializationContext;
// import net.minecraft.advancements.critereon.EntityPredicate;
// import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
// import net.minecraft.resources.ResourceLocation;
// import net.minecraft.server.level.ServerPlayer;
// import workspacedead.WorkspaceDead;

import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import workspacedead.WorkspaceDead;

///
/// 
///
public class WaterCleanedTrigger extends SimpleCriterionTrigger<WaterCleanedTrigger.TriggerInstance> {

    private static ResourceLocation ID = new ResourceLocation(WorkspaceDead.MOD_ID, "water_cleaned");

    public WaterCleanedTrigger() {
    }

    @NotNull
    @Override
    public ResourceLocation getId() {
        return ID;
    }

    public void trigger(ServerPlayer player) {
        this.trigger(player, (instance) -> true);
    }

    @NotNull
    @Override
    protected TriggerInstance createInstance(@NotNull JsonObject json,
            @NotNull EntityPredicate.Composite playerPredicate, @NotNull DeserializationContext context) {
        return new TriggerInstance(playerPredicate);
    }

    public static class TriggerInstance extends AbstractCriterionTriggerInstance {

        public TriggerInstance(EntityPredicate.Composite playerPredicate) {
            super(ModCriteriaTriggers.WATER_CLEANED.getId(), playerPredicate);
        }

        public static WaterCleanedTrigger.TriggerInstance block() {
            return new WaterCleanedTrigger.TriggerInstance(EntityPredicate.Composite.ANY);
        }
    }
}