package workspacedead.item.custom;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import workspacedead.item.ModItems;

public class PlungerItem extends Item {

    public PlungerItem(Properties pProperties) {
        super(pProperties.stacksTo(1));
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack pStack, Player pPlayer, LivingEntity pInteractionTarget,
            InteractionHand pUsedHand) {
        if (!pPlayer.level.isClientSide()) {
            if (!(pInteractionTarget instanceof Player)) {
                var regname = pInteractionTarget.getType().getRegistryName().toString();
                if (regname != null) {
                    ItemStack stack = new ItemStack(ModItems.SPAWNEGG_SEEDS.get(), 1);
                    stack.getOrCreateTag().putString(SpawnEggSeedsItem.EssenceTagID, regname);
                    pInteractionTarget.spawnAtLocation(stack);
                    return InteractionResult.SUCCESS;
                }
            }
        }
        return InteractionResult.PASS;
    }
}
