package workspacedead.item.custom;

import java.util.HashMap;

import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.CapabilityItemHandler;
import workspacedead.util.Chatter;
import workspacedead.util.KubeJSScripting;
import workspacedead.util.NBTHelper;

//import dev.latvian.mods.kubejs.item.ItemStackJS;

public class KubeWand extends Item {

    public KubeWand(Properties pProperties) {
        super(pProperties.stacksTo(1));
    }

    @Override
    public InteractionResult onItemUseFirst(ItemStack stack, UseOnContext context) {
        var player = context.getPlayer();
        var pos = context.getClickedPos();
        var facing = context.getClickedFace();
        var level = context.getLevel();

        if (player == null || !player.mayUseItemAt(pos.relative(facing), facing, stack))
            return InteractionResult.PASS;

        if (level.isClientSide()) {
            var tile = level.getBlockEntity(pos);
            if (tile == null)
                return InteractionResult.PASS;
            var inv = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, facing);
            var items = new ItemStack[9];
            if (inv.isPresent()) {
                //var map = new HashMap<String, ItemStack>();
                inv.ifPresent(ih -> {
                    for (var i = 0; i < 9; i++) {
                        if (ih.getStackInSlot(i).getItem() != Items.AIR)
                            items[i] = ih.getStackInSlot(i).copy();
                    }
                });
                String s = "";
                // var offhand = player.getOffhandItem().getItem().getRegistryName().toString();
                // if (player.getOffhandItem().getCount() > 1) {
                //     offhand = player.getOffhandItem().getCount() + "x " + offhand;
                // }
                var type = "";
                if (isShapeless(stack)) {
                    s = KubeJSScripting.makeShapelessDatapackTableRecipe(player.getOffhandItem(), items);
                    type = "shapeless";
                } else {
                    s = KubeJSScripting.makeShapedDatapackTableRecipe(player.getOffhandItem(), items);
                    type = "shaped";
                }
                if (s != null) {
                    setClipboard(s);
                    Chatter.sendToPlayer(player, "Copied " + type + " recipe to clipboard.");
                }
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (player.isCrouching()) {
            var stack = player.getItemInHand(hand);
            NBTHelper.flipBoolean(stack, "Shapeless");
            if (level.isClientSide()) {
                if (NBTHelper.getBoolean(stack, "Shapeless"))
                    Chatter.chat("Mode changed to Shapeless.");
                else
                    Chatter.chat("Mode changed to Shaped.");
            }
        }
        return super.use(level, player, hand);
    }

    // private static String getModeString(ItemStack stack) {
    // return isShapeless(stack) ? "Shapeless" : "Shaped";
    // }

    private static boolean isShapeless(ItemStack stack) {
        return NBTHelper.getBoolean(stack, "Shapeless");
    }

    private static void setClipboard(String string) {
        if (string == null || string.compareTo("") == 0)
            return;
        Minecraft minecraft = Minecraft.getInstance();
        minecraft.keyboardHandler.setClipboard(string);
    }

}
