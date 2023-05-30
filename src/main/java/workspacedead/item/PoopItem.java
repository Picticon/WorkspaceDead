package workspacedead.item;

import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public class PoopItem extends BoneMealItem {
    private int _lifespan = 300;

    public PoopItem(Properties pProperties) {
        super(pProperties);
        _lifespan = 300;
    }

    public PoopItem(Properties pProperties, int lifespan) {
        super(pProperties);
        _lifespan = lifespan;
    }

    @Override
    public int getBurnTime(ItemStack itemStack, RecipeType<?> recipeType) {
        return 800; // half the time of coal
    }

    @Override
    public int getEntityLifespan(ItemStack itemStack, Level level) {
        return _lifespan;
    }
}
