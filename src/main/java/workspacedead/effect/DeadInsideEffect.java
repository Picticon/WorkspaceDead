package workspacedead.effect;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.registries.ForgeRegistries;
import workspacedead.config.CommonConfig;
import workspacedead.item.ModItems;

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
                var itemToDrop = ModItems.POOP.get(); // default poop
                if (CommonConfig.poop_mobs_cache == null)
                    return;
                var entry = CommonConfig.poop_mobs_cache.get(pLivingEntity.getType().getRegistryName().toString());
                if (entry != null) {
                    var ent = ForgeRegistries.ENTITIES.getValue(new ResourceLocation(entry[0], entry[1]));
                    if (ent != null && pLivingEntity.getType() == ent) {
                        var testitem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(entry[2], entry[3]));
                        if (testitem != null) {
                            itemToDrop = testitem;
                        }
                    }
                }
                // var c = pLivingEntity.getClass().toString();
                if (pLivingEntity instanceof Player)

                {
                    if (pLivingEntity.level.random.nextDouble() < .05) {
                        itemToDrop = ModItems.SEEDED_POOP.get();
                    }
                }
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
}
