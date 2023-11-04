package workspacedead.item.custom;

import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;
import workspacedead.block.SpawnEggPlant.SpawnEggBlockEntity;
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
        if (r) {
            if (pContext.getLevel().getBlockEntity(pContext.getClickedPos()) instanceof SpawnEggBlockEntity entity) {
                entity.setEntityID(getMobID(pContext.getItemInHand()));
            }
        }
        return r;
    }

    @Override
    public void appendHoverText(ItemStack pStack, Level pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
        if (hasEssence(pStack)) {
            var ent = ForgeRegistries.ENTITIES.getValue(new ResourceLocation(getMobID(pStack)));
            if (ent != null) {
                TranslatableComponent desc = (TranslatableComponent) ent.getDescription();
                pTooltip.add(desc.withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.AQUA));
            }
        }
    }

    // @Override
    // public Component getName(ItemStack stack) {
    //     if (hasEssence(stack))
    //         return new TextComponent("Spawn Seed").append(new TranslatableComponent(getMobID(stack)));
    //     //super.getDescriptionId(stack)
    //     return new TranslatableComponent(super.getDescriptionId(stack));
    // }
}
