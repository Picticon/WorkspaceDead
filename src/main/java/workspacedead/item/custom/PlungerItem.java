package workspacedead.item.custom;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.PlayerMainInvWrapper;
import workspacedead.registry.MySounds;
import workspacedead.registry.MyItems;
import workspacedead.util.Chatter;

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
                    boolean flag = pPlayer.getAbilities().instabuild;
                    boolean found = flag;
                    IItemHandler inventory = new PlayerMainInvWrapper(pPlayer.getInventory());
                    if (!flag) {
                        for (int i = 0; i < inventory.getSlots(); i++)
                            if (inventory.getStackInSlot(i).getItem() == MyItems.SPAWNEGG_SEEDS_EMPTY.get()) {
                                ItemStack extract = inventory.extractItem(i, 1, flag);
                                if (extract != null && extract.getCount() > 0) {
                                    found = true;
                                    break;
                                }
                            }
                    }
                    if (found) {
                        ItemStack stack = new ItemStack(MyItems.SPAWNEGG_SEEDS.get(), 1);
                        stack.getOrCreateTag().putString(SpawnEggSeedsItem.EssenceTagID, regname);
                        pInteractionTarget.spawnAtLocation(stack);
                        pPlayer.playSound(MySounds.PLUNGER.get(), 0.7F, pPlayer.getVoicePitch());
                        pInteractionTarget.hurt(DamageSource.MAGIC, 2.0f);
                        return InteractionResult.SUCCESS;
                    } else {

                        Chatter.chat(MyItems.SPAWNEGG_SEEDS_EMPTY.get().getDescription().getString() + " not found.");
                        return InteractionResult.FAIL;
                    }
                }
            }
        }
        return InteractionResult.PASS;
    }
}
