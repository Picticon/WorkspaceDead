package workspacedead.item.custom;

import java.util.HashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.CapabilityItemHandler;
import workspacedead.util.Chatter;
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
            var inv = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, facing);
            if (inv.isPresent()) {
                var map = new HashMap<String, String>();
                var items = new String[9];
                var recipe = new String[9];
                for (var i = 0; i < recipe.length; i++)
                    recipe[i] = " ";
                items[0] = inv.map(a -> {
                    return a.getStackInSlot(0).getItem().getRegistryName().toString();
                }).get().toString();
                items[1] = inv.map(a -> {
                    return a.getStackInSlot(1).getItem().getRegistryName().toString();
                }).get().toString();
                items[2] = inv.map(a -> {
                    return a.getStackInSlot(2).getItem().getRegistryName().toString();
                }).get().toString();
                items[3] = inv.map(a -> {
                    return a.getStackInSlot(3).getItem().getRegistryName().toString();
                }).get().toString();
                items[4] = inv.map(a -> {
                    return a.getStackInSlot(4).getItem().getRegistryName().toString();
                }).get().toString();
                items[5] = inv.map(a -> {
                    return a.getStackInSlot(5).getItem().getRegistryName().toString();
                }).get().toString();
                items[6] = inv.map(a -> {
                    return a.getStackInSlot(6).getItem().getRegistryName().toString();
                }).get().toString();
                items[7] = inv.map(a -> {
                    return a.getStackInSlot(7).getItem().getRegistryName().toString();
                }).get().toString();
                items[8] = inv.map(a -> {
                    return a.getStackInSlot(8).getItem().getRegistryName().toString();
                }).get().toString();
                for (var i = 0; i < 9; i++) {
                    if (items[i].equals("minecraft:air"))
                        continue;
                    Append(i, map, recipe, items[i]);
                }
                String s = "";
                var offhand = player.getOffhandItem().getItem().getRegistryName().toString();
                if (player.getOffhandItem().getCount() > 1) {
                    offhand = player.getOffhandItem().getCount() + "x " + offhand;
                }
                if (isShapeless(stack)) {
                    s = makeShapelessDatapackTableRecipe(offhand, map, recipe);
                    setClipboard(s);
                    Chatter.sendToPlayer(player, "Copied shapeless recipe to clipboard.");
                } else {
                    s = makeShapedDatapackTableRecipe(offhand, map, recipe);
                    setClipboard(s);
                    Chatter.sendToPlayer(player, "Copied shaped recipe to clipboard.");
                }
            }

            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    private void Append(Integer slot, HashMap<String, String> map, String[] recipe, String map2) {
        recipe[slot] = " ";
        var itemname = map2;
        if (!map.containsKey(itemname)) {
            var next = Next(map);
            map.put(itemname, next);
        }
        var key = map.get(itemname);
        recipe[slot] = key;
    }

    private String Next(HashMap<String, String> map) {
        String[] codes = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L" };
        for (var idx = 0; idx < codes.length; idx++) {
            var code = codes[idx];
            var kvp = map.values().contains(code.toString());
            if (!kvp) {
                return code;
            }
        }
        return " ";
    }

    private String makeShapedDatapackTableRecipe(String offhand, HashMap<String, String> map, String[] recipe) {

        String s = "event.shaped('" + offhand + "',";
        s += "['";
        s += recipe[0] + recipe[1] + recipe[2];
        s += "','";
        s += recipe[3] + recipe[4] + recipe[5];
        s += "','";
        s += recipe[6] + recipe[7] + recipe[8];
        s += "'],{";
        var first = true;
        for (var key : map.keySet()) {
            if (!first)
                s += ",";
            first = false;
            s += "" + map.get(key) + ":'" + key + "'";
        }
        s += "});";
        return s;

    }

    private String makeShapelessDatapackTableRecipe(String offhand, HashMap<String, String> map, String[] recipe) {
        var counts = new Integer[9];
        for (var i = 0; i < counts.length; i++)
            counts[i] = 0;
        var mats = new String[9];
        var cnt = 0;
        for (var key : map.keySet()) { // minecraft:bone, etc.
            var val = map.get(key); // "A","B",etc.
            for (var i = 0; i < recipe.length; i++) {
                if (recipe[i] == val) {
                    counts[cnt]++;
                    mats[cnt] = key;
                }
            }
            cnt++;
        }
        String s = "event.shapeless('" + offhand + "',";
        s += "[";
        var first = true;
        for (var i = 0; i < cnt; i++) {
            if (!first)
                s += ",";
            first = false;
            s += "'";
            if (counts[i] > 1)
                s += counts[i] + "x ";
            s += mats[i];
            s += "'";
        }
        s += "]";
        s += ");";
        return s;
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
        Minecraft.getInstance().keyboardHandler.setClipboard(string);
    }

}
