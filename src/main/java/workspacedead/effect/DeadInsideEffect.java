package workspacedead.effect;

import java.util.HashMap;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
import workspacedead.recipe.DeadInsideEffectRecipe;
import workspacedead.registry.MyItems;

public class DeadInsideEffect extends MobEffect {

    protected DeadInsideEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    // if (!pLivingEntity.level.isClientSide()) {
    // var x = pLivingEntity.getX();
    // var y = pLivingEntity.getY();
    // var z = pLivingEntity.getZ();
    // pLivingEntity.teleportTo(x, y, z);
    // pLivingEntity.setDeltaMovement(0, 0, 0);
    // }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        super.applyEffectTick(pLivingEntity, pAmplifier);
        if (!pLivingEntity.level.isClientSide()) {
            if (pLivingEntity.level.random.nextDouble() < .05) {
                var itemToDrop = new ItemStack(MyItems.POOP.get(), 1); // default poop
                //Minecraft minecraft = Minecraft.getInstance();
                //RecipeManager rm = Objects.requireNonNull(minecraft.level).getRecipeManager();
                //List<DeadInsideEffectRecipe> recipes3 = rm.getAllRecipesFor(DeadInsideEffectRecipe.Type.INSTANCE);
                if (poop_mobs_cache == null)
                    buildCaches();
                if (poop_mobs_cache != null) {
                    // if cache is null, oops...
                    var entry = poop_mobs_cache.get(pLivingEntity.getType().getRegistryName().toString());
                    if (entry != null) {
                        var testitem = entry.getResultItem();
                        if (testitem != null) {
                            itemToDrop = testitem;
                        }
                    }
                }
                // var c = pLivingEntity.getClass().toString();
                if (pLivingEntity instanceof Player)

                {
                    if (pLivingEntity.level.random.nextDouble() < .05) {
                        itemToDrop = new ItemStack(MyItems.POOP_SEEDED.get());
                    }
                }
                // var stack =
                pLivingEntity.spawnAtLocation(itemToDrop);
                pLivingEntity.hurt(DamageSource.MAGIC, 0.5f);
            }

            // pLivingEntity.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0);
            // var entity=EntityType.create()
            // var itemEntity = pLivingEntity.level.addFreshEntity(())
            // .createEntity("minecraft:item");
            // itemEntity.setX(pLivingEntity.getX());
            // itemEntity.setY(pLivingEntity.getY());
            // itemEntity.setZ(pLivingEntity.getZ());
            // itemEntity.item = Item.of("minecraft:");
            // itemEntity.spawn();
            // entity.potionEffects.add("minecraft:slowness", 20, 0, false, true);
            // entity.attack(.5); // do small damage.
        }
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }

    public static HashMap<String, DeadInsideEffectRecipe> poop_mobs_cache;

    public static void buildCaches() {
        poop_mobs_cache = null;
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.level == null)
            return;
        RecipeManager rm = minecraft.level.getRecipeManager();
        if (rm == null)
            return;
        List<DeadInsideEffectRecipe> recipes3 = rm.getAllRecipesFor(DeadInsideEffectRecipe.Type.INSTANCE);
        poop_mobs_cache = new HashMap<String, DeadInsideEffectRecipe>();
        for (var idx = 0; idx < recipes3.size(); idx++) {
            var t = recipes3.get(idx);
            poop_mobs_cache.put(t.getInputEntity().getRegistryName().toString(), t);
        }

    }
}
