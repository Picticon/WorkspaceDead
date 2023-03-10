package workspacedead.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    // this is called when the jar is loaded.
    public static final CreativeModeTab ITEMS_TAB = new CreativeModeTab("workspacedead") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.POOP.get());
        }

    };
}