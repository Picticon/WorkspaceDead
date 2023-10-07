package workspacedead.entity.projectile;

import net.minecraft.core.Position;
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
import workspacedead.entity.ModEntityTypes;
import workspacedead.registry.MyItems;

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

    public DeadArrow(Level level, Position position) {
        super(ModEntityTypes.DEAD_ARROW.get(), position.x(), position.y(), position.z(), level);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(MyItems.DEAD_ARROW.get());
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        if (pResult.getEntity() instanceof LivingEntity) {
            ((LivingEntity) pResult.getEntity()).addEffect(new MobEffectInstance(ModEffects.DEADINSIDE.get(), 200));
        }
        super.onHitEntity(pResult);
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
