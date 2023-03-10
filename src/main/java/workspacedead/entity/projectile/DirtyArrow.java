package workspacedead.entity.projectile;

import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;
import workspacedead.item.ModItems;

public class DirtyArrow extends AbstractArrow {

    public DirtyArrow(EntityType<DirtyArrow> entityType, Level world) {
        super(entityType, world);
    }

    public DirtyArrow(EntityType<DirtyArrow> entityType, double x, double y, double z, Level world) {
        super(entityType, x, y, z, world);
    }

    public DirtyArrow(EntityType<DirtyArrow> entityType, LivingEntity shooter, Level world) {
        super(entityType, shooter, world);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ModItems.DIRTY_ARROW.get());
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

}
