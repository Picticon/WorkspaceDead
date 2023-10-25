package workspacedead.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import workspacedead.registry.MyItems;

public class ModCreativeModeTab {
    // this is called when the jar is loaded.
    public static final CreativeModeTab ITEMS_TAB = new CreativeModeTab("workspacedead") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(MyItems.POOP.get());
        }

        @Override
        public void fillItemList(net.minecraft.core.NonNullList<ItemStack> pItems) {
            super.fillItemList(pItems);
        };
    };
}