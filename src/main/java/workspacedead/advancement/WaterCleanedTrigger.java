// package workspacedead.advancement;

// import org.jetbrains.annotations.NotNull;

// import com.google.gson.JsonObject;

// import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
// import net.minecraft.advancements.critereon.DeserializationContext;
// import net.minecraft.advancements.critereon.EntityPredicate;
// import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
// import net.minecraft.resources.ResourceLocation;
// import net.minecraft.server.level.ServerPlayer;
// import workspacedead.WorkspaceDead;

// public class WaterCleanedTrigger extends
// SimpleCriterionTrigger<WaterCleanedTrigger.TriggerInstance> {

// public static final ResourceLocation ID = new
// ResourceLocation(WorkspaceDead.MOD_ID, "water_cleaned");

// @Override
// public @NotNull ResourceLocation getId() {
// return ID;
// }

// @Override
// protected WaterCleanedTrigger.@NotNull TriggerInstance
// createInstance(@NotNull JsonObject pJson,
// EntityPredicate.@NotNull Composite pPlayer,
// @NotNull DeserializationContext pContext) {

// return new WaterCleanedTrigger.TriggerInstance(pPlayer);
// }

// /**
// * Triggers the advancement trigger.
// *
// * @param pPlayer The affected player.
// */
// public void trigger(ServerPlayer pPlayer) {
// trigger(pPlayer, triggerInstance -> true);
// }

// public static class TriggerInstance extends AbstractCriterionTriggerInstance
// {

// public TriggerInstance(EntityPredicate.Composite pPlayer) {
// super(WaterCleanedTrigger.ID, pPlayer);
// }
// }
// }