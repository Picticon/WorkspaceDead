package workspacedead.advancement;

import workspacedead.WorkspaceDead;
import org.jetbrains.annotations.NotNull;
import com.google.gson.JsonObject;
import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
import net.minecraft.advancements.critereon.DeserializationContext;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public class PurificationTrigger extends SimpleCriterionTrigger<PurificationTrigger.TriggerInstance> {
    private static ResourceLocation ID = new ResourceLocation(WorkspaceDead.MOD_ID, "purification");

    public PurificationTrigger() {
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
            super(ModCriteriaTriggers.PURIFICATION.getId(), playerPredicate);
        }

        public static PurificationTrigger.TriggerInstance block() {
            return new PurificationTrigger.TriggerInstance(EntityPredicate.Composite.ANY);
        }
    }
}
