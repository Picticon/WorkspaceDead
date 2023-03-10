package workspacedead.mixin.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.core.Position;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;
//import workspacedead.advancement.ModCriteriaTriggers;
//import workspacedead.advancement.WaterCleanedTrigger;
import workspacedead.fluid.ModFluids;
import workspacedead.item.ModItems;

// parts of code ripped from AE2.

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity {
    public ItemEntityMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Shadow
    public abstract ItemStack getItem();

    private int workspacedead_client_time = 0;
    private int workspacedead_server_time = 0;

    @Inject(at = @At("RETURN"), method = "tick")
    void handleEntityTransform(CallbackInfo ci) {
        if (this.isRemoved()) {
            return;
        }
        var self = (ItemEntity) (Object) this;

        // purify dead water
        if (self.getItem().is(ModItems.PURIFYSHARD.get())) {
            var fluidblock = this.level.getFluidState(this.blockPosition());
            boolean isValidFluid = !fluidblock.isEmpty() && (fluidblock.is(ModFluids.DEADWATER_BLOCK.get().getFluid()));
            if (isValidFluid) {
                if (level.isClientSide()) {
                    workspacedead_client_time++;
                    if (this.workspacedead_client_time > 30) {
                        Particleify(this.level, this.position(), ParticleTypes.ENCHANTED_HIT);
                    }
                } else {
                    this.workspacedead_server_time++;
                    if (this.workspacedead_server_time > 500) {
                        this.level.setBlock(this.blockPosition(), Blocks.WATER.defaultBlockState(), 1 | 2);
                        this.setRemoved(RemovalReason.DISCARDED);
                        for (var i = 0; i < 10; i++) {
                            Particleify(this.level, this.position(), ParticleTypes.EXPLOSION);
                        }
                        // ModCriteriaTriggers.WATER_CLEANED.trigger(player); Too lazy to find out who
                        // threw it...
                        this.workspacedead_server_time = -60000;
                    }
                }
            } else {
                workspacedead_client_time = 0;
                workspacedead_server_time = 0;
            }
        }

        // wash poop
        if (self.getItem().is(ModItems.SEEDED_POOP.get())) {
            var fluidblock = this.level.getFluidState(this.blockPosition());
            boolean isValidFluid = !fluidblock.isEmpty() && fluidblock.is(Fluids.WATER);
            if (isValidFluid) {
                if (level.isClientSide()) {
                    workspacedead_client_time++;
                    if (this.workspacedead_client_time > 30) {
                        Particleify(this.level, this.position(), ParticleTypes.BUBBLE);
                    }
                } else {
                    this.workspacedead_server_time++;
                    if (this.workspacedead_server_time > 500) {
                        var itemstack = new ItemStack(Items.WHEAT_SEEDS, this.getItem().getCount());
                        this.spawnAtLocation(itemstack);
                        this.setRemoved(RemovalReason.DISCARDED);
                        this.workspacedead_server_time = -60000;
                    }
                }
            } else {
                workspacedead_client_time = 0;
                workspacedead_server_time = 0;
            }
        }
    }

    private void Particleify(Level level, Position pos, SimpleParticleType bubble) {
        if (level.isClientSide()) {
            // for (var i = 0; i < 1; i++) {
            var x = pos.x();
            var y = pos.y();
            var z = pos.z();
            // want more Y so particles go "up"
            // bubble = ParticleTypes.BUBBLE;
            level.addParticle(bubble, true, x, y, z, level.random.nextFloat() - .5f, level.random.nextFloat() - .25f, level.random.nextFloat() - .5f);
            // }
        }
    }

}