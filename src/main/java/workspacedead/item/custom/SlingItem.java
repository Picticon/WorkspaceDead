package workspacedead.item.custom;

import java.util.function.Predicate;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import workspacedead.item.ModItems;

public class SlingItem extends CrossbowItem {

    public SlingItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.CROSSBOW;
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return AMMO_ONLY;
    }

    public static int getChargeDuration2(ItemStack pCrossbowStack) {
        return 5;
    }

    @Override
    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving, int pTimeLeft) {
        int i = this.getUseDuration(pStack) - pTimeLeft;
        float f = getPowerForTime2(i, pStack);
        if (f >= 1.0F && !isCharged(pStack) && tryLoadProjectiles2(pEntityLiving, pStack)) {
            setCharged(pStack, true);
            SoundSource soundsource = pEntityLiving instanceof Player ? SoundSource.PLAYERS : SoundSource.HOSTILE;
            pLevel.playSound((Player) null, pEntityLiving.getX(), pEntityLiving.getY(), pEntityLiving.getZ(), SoundEvents.SLIME_JUMP, soundsource, 1.0F, 1.0F / (pLevel.getRandom().nextFloat() * 0.5F + 1.0F) + 0.2F);
        }
    }

    private static float getPowerForTime2(int pUseTime, ItemStack pCrossbowStack) {
        float f = (float) pUseTime / (float) getChargeDuration2(pCrossbowStack);
        if (f > 1.0F) {
            f = 1.0F;
        }
        return f;
    }

    private static boolean tryLoadProjectiles2(LivingEntity pShooter, ItemStack pCrossbowStack) {
        int i = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.MULTISHOT, pCrossbowStack);
        int j = i == 0 ? 1 : 3;
        boolean flag = pShooter instanceof Player && ((Player) pShooter).getAbilities().instabuild;
        ItemStack itemstack = pShooter.getProjectile(pCrossbowStack);
        ItemStack itemstack1 = itemstack.copy();

        for (int k = 0; k < j; ++k) {
            if (k > 0) {
                itemstack = itemstack1.copy();
            }

            if (itemstack.isEmpty() && flag) {
                itemstack = new ItemStack(Items.ARROW);
                itemstack1 = itemstack.copy();
            }

            if (!loadProjectile2(pShooter, pCrossbowStack, itemstack, k > 0, flag)) {
                return false;
            }
        }
        return true;
    }

    private static boolean loadProjectile2(LivingEntity pShooter, ItemStack pCrossbowStack, ItemStack pAmmoStack, boolean pHasAmmo, boolean pIsCreative) {
        if (pAmmoStack.isEmpty()) {
           return false;
        } else {
           boolean flag = pIsCreative && pAmmoStack.getItem() instanceof ArrowItem;
           ItemStack itemstack;
           if (!flag && !pIsCreative && !pHasAmmo) {
              itemstack = pAmmoStack.split(1);
              if (pAmmoStack.isEmpty() && pShooter instanceof Player) {
                 ((Player)pShooter).getInventory().removeItem(pAmmoStack);
              }
           } else {
              itemstack = pAmmoStack.copy();
           }
  
           addChargedProjectile2(pCrossbowStack, itemstack);
           return true;
        }
     }

     private static void addChargedProjectile2(ItemStack pCrossbowStack, ItemStack pAmmoStack) {
        CompoundTag compoundtag = pCrossbowStack.getOrCreateTag();
        ListTag listtag;
        if (compoundtag.contains("ChargedProjectiles", 9)) {
           listtag = compoundtag.getList("ChargedProjectiles", 10);
        } else {
           listtag = new ListTag();
        }
  
        CompoundTag compoundtag1 = new CompoundTag();
        pAmmoStack.save(compoundtag1);
        listtag.add(compoundtag1);
        compoundtag.put("ChargedProjectiles", listtag);
     }
  
    //  private static List<ItemStack> getChargedProjectiles(ItemStack pCrossbowStack) {
    //     List<ItemStack> list = Lists.newArrayList();
    //     CompoundTag compoundtag = pCrossbowStack.getTag();
    //     if (compoundtag != null && compoundtag.contains("ChargedProjectiles", 9)) {
    //        ListTag listtag = compoundtag.getList("ChargedProjectiles", 10);
    //        if (listtag != null) {
    //           for(int i = 0; i < listtag.size(); ++i) {
    //              CompoundTag compoundtag1 = listtag.getCompound(i);
    //              list.add(ItemStack.of(compoundtag1));
    //           }
    //        }
    //     }
  
    //     return list;
    //  }
  
  

    public static final Predicate<ItemStack> AMMO_ONLY = (i) -> {
        return i.is(ModItems.DIRTY_ARROW.get());
    };

}
