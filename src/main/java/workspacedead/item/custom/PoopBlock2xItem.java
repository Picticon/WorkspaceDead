package workspacedead.item.custom;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import workspacedead.item.ModCreativeModeTab;

public class PoopBlock2xItem extends BlockItem {

    public PoopBlock2xItem(Block pBlock) {
        super(pBlock, new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB));
    }

    @Override
    public int getBurnTime(ItemStack itemStack, RecipeType<?> recipeType) {
        return 40000; // 10x poop block item
    }
}