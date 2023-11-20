package workspacedead.jei;

import workspacedead.block.KubeJSTable.KubeJSTableContainer;

import javax.annotation.Nullable;

import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.recipe.transfer.IRecipeTransferError;
import mezz.jei.api.recipe.transfer.IRecipeTransferHandler;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
class KubeJSTableTransferHandler implements IRecipeTransferHandler<KubeJSTableContainer, CraftingRecipe> {

    @Override
    public Class<KubeJSTableContainer> getContainerClass() {
        return KubeJSTableContainer.class;
    }

    @Override
    public Class<CraftingRecipe> getRecipeClass() {
        return CraftingRecipe.class;
    }

    @Nullable
    @Override
    public IRecipeTransferError transferRecipe(KubeJSTableContainer container, CraftingRecipe recipe,
            IRecipeSlotsView recipeSlots, Player player, boolean maxTransfer, boolean doTransfer) {
        if (doTransfer) {
            container.sendRecipeToServer(recipe);
        }
        return null;
    }
}