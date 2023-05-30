package workspacedead.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;
import workspacedead.block.SpawnEggBlockEntity;
import workspacedead.util.ModTags;

public class SpawnEggSeedsItem extends ItemNameBlockItem {

    public SpawnEggSeedsItem(Block pBlock, Properties pProperties) {
        super(pBlock, pProperties);
    }

    public static final String EssenceTagID = "wd_essence";

    public String getMobID(ItemStack stack) {
        if (!stack.hasTag())
            return "";
        return stack.getTag().getString(EssenceTagID);
    }

    public boolean hasEssence(ItemStack stack) {
        return !stack.isEmpty() && stack.hasTag() && stack.getTag().contains(EssenceTagID);
    }

    public boolean canBeExtracted(EntityType<?> entity) {
        return !ForgeRegistries.ENTITIES.tags().getTag(ModTags.Entities.BLACKLISTED_ESSENCE).contains(entity);
    }

    @Override
    protected boolean placeBlock(BlockPlaceContext pContext, BlockState pState) {
        var r = super.placeBlock(pContext, pState);
        if (r)
        {
            if (pContext.getLevel().getBlockEntity(pContext.getClickedPos()) instanceof SpawnEggBlockEntity entity)
            {
                entity.setEntityID(getMobID(pContext.getItemInHand()));
            }
        }
        return r;
    }

    @Override
    public Component getName(ItemStack stack) {
        if (hasEssence(stack))
            return new TranslatableComponent(super.getDescriptionId(stack)).append(" (" + getMobID(stack) + ")");
        return new TranslatableComponent(super.getDescriptionId(stack));
    }
}
