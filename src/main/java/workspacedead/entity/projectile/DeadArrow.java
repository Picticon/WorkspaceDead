package workspacedead.entity.projectile;

import net.minecraft.network.protocol.Packet;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.NetworkHooks;
import workspacedead.effect.ModEffects;
import workspacedead.item.ModItems;

public class DeadArrow extends AbstractArrow {

    public DeadArrow(EntityType<DeadArrow> entityType, Level world) {
        super(entityType, world);
    }

    public DeadArrow(EntityType<DeadArrow> entityType, double x, double y, double z, Level world) {
        super(entityType, x, y, z, world);
    }

    public DeadArrow(EntityType<DeadArrow> entityType, LivingEntity shooter, Level world) {
        super(entityType, shooter, world);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ModItems.DEAD_ARROW.get());
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        if (pResult.getEntity() instanceof LivingEntity) {
            ((LivingEntity) pResult.getEntity()).addEffect(new MobEffectInstance(ModEffects.DEADINSIDE.get(), 240));
        }
        super.onHitEntity(pResult);
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
