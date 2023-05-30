package workspacedead.item.custom;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import workspacedead.entity.ModEntityTypes;
import workspacedead.entity.projectile.DeadArrow;

public class DeadArrowItem extends ArrowItem {

    public DeadArrowItem(Properties pProperties) {
        super(pProperties);

    }

    @Override
    public AbstractArrow createArrow(Level pLevel, ItemStack pStack, LivingEntity pShooter) {
        var arrow = new DeadArrow(ModEntityTypes.DEAD_ARROW.get(), pShooter, pLevel);
        return arrow;
    }

    
}
