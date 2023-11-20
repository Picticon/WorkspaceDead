package workspacedead.util;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class KubeJSScripting {
    public static String makeShapedDatapackTableRecipe(ItemStack output, ItemStack[] recipe) {
        // for (var i = 0; i < recipe.length; i++) {
        //     if (recipe[i].getCount() > 1)
        //         recipe[i].setCount(1);
        // }
        if (output == null)
            return "";

        var uniques = makeUniques(recipe);
        var offhand = makeIngredient(output, false);
        var bools = new boolean[Math.max(9, recipe.length)];
        for (var i = 0; i < recipe.length; i++) {
            bools[i] = (getMap(uniques, recipe[i], true).compareTo(" ") != 0); // true if full
        }

        // 012
        // 345
        // 678
        var line0 = bools[0] || bools[1] || bools[2];
        var line2 = bools[6] || bools[7] || bools[8];
        var line1 = (bools[3] || bools[4] || bools[5]) || (line0 && line2);

        var col0 = bools[0] || bools[3] || bools[6];
        var col2 = bools[2] || bools[5] || bools[8];
        var col1 = (bools[1] || bools[4] || bools[7]) || (col0 && col2);

        String s = "event.shaped('" + offhand + "',";
        s += "[";
        if (line0) {
            s += "'";
            s += (col0 ? getMap(uniques, recipe[0], true) : "")
                    + (col1 ? getMap(uniques, recipe[1], true)
                            : "")
                    + (col2 ? getMap(uniques, recipe[2], true) : "");
            s += "'";
            if (line1)
                s += ",";
        }
        if (line1) {
            s += "'";
            s += (col0 ? getMap(uniques, recipe[3], true) : "")
                    + (col1 ? getMap(uniques, recipe[4], true) : "")
                    + (col2 ? getMap(uniques, recipe[5], true) : "");
            s += "'";
            if (line2)
                s += ",";
        }
        if (line2) {
            s += "'";
            s += (col0 ? getMap(uniques, recipe[6], true) : "")
                    + (col1 ? getMap(uniques, recipe[7], true) : "")
                    + (col2 ? getMap(uniques, recipe[8], true) : "");
            s += "'";
        }
        s += "],{";
        var first = true;
        for (var key : uniques.keySet()) {
            if (!first)
                s += ",";
            first = false;
            s += "" + uniques.get(key) + ":'" + key + "'";
        }
        s += "});\r\n";
        return s;

    }

    private static String getMap(Map<String, String> uniques, ItemStack itemStack, boolean ignorecount) {
        var r = makeIngredient(itemStack, ignorecount);
        if (r != "") {
            if (uniques.containsKey(r))
                return uniques.get(r);
        }
        return " ";
    }

    private static String makeIngredient(ItemStack output, boolean ignorecount) {
        if (output.getItem() == Items.AIR)
            return "";
        var ret = output.getItem().getRegistryName().toString();
        if (!ignorecount && output.getCount() > 1) {
            ret = output.getCount() + "x " + ret;
        }
        return ret;
    }

    //
    //
    //  
    public static String makeShapelessDatapackTableRecipe(ItemStack output, ItemStack[] recipe) {
        // for (var i = 0; i < recipe.length; i++) {
        //     if (recipe[i].getCount() > 1)
        //         recipe[i].setCount(1);
        // }
        if (output == null)
            return null;

        var uniques = makeUniques(recipe); // "minecraft:coal"->A ,"minecraft:stone"->B , etc
        var mats = new String[9]; // minecraft:coal
        var indexer = new String[9]; // A
        var counts = new Integer[9]; // 1
        for (var i = 0; i < counts.length; i++)
            counts[i] = 0;
        var cnt = 0;

        for (var x = 0; x < recipe.length; x++) {
            var ingredient = recipe[x];
            var item = makeIngredient(ingredient, false);
            if (item == null || item.compareTo("") == 0)
                continue;
            var letter = "";
            if (uniques.containsKey(item))
                letter = uniques.get(item);
            var foundexisting = false;
            for (var i = 0; i < cnt; i++) {
                if (mats[i].compareTo(item) == 0) {
                    counts[i]++;
                    foundexisting = true;
                    break;
                }
            }
            if (!foundexisting) {
                counts[cnt]++;
                mats[cnt] = item;
                indexer[cnt] = letter;
                cnt++;
            }
        }

        String s = "event.shapeless('" + makeIngredient(output, false) + "',";
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
        s += ");\r\n";
        return s;
    }

    private static Map<String, String> makeUniques(ItemStack[] recipe) {
        var ret = new HashMap<String, String>();
        for (var i = 0; i < recipe.length; i++) {
            var t = makeIngredient(recipe[i], true); // minecraft:coal
            if (t.compareTo("") == 0)
                continue;
            if (!ret.containsKey(t)) {
                var key = Next(ret);
                ret.put(t, key);
            }
        }
        return ret;
    }

    private static String Next(HashMap<String, String> map) {
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

}
