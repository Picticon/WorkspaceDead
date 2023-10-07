package workspacedead.item.custom;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import workspacedead.item.ModCreativeModeTab;

public class PoopBlockItem extends BlockItem {

    public PoopBlockItem(Block pBlock) {
        super(pBlock, new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB));
    }

    @Override
    public int getBurnTime(ItemStack itemStack, RecipeType<?> recipeType) {
        return 4000; // quarter the time of coal block
    }
}
