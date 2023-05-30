package workspacedead.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import net.minecraftforge.common.ForgeConfigSpec;

// 2023

public final class CommonConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<String> nether_opener;

    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> purify_mobs;
    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> purify_blocks;
    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> weak_purify_mobs;
    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> weak_purify_blocks;

    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> poop_mobs;
    public static HashMap<String, String[]> poop_mobs_cache;

    static {
        BUILDER.push("WorkspaceDead Config");

        var purifyBlocks = new ArrayList<String>();
        purifyBlocks.add("workspacedead:deadsand:minecraft:sand");
        purifyBlocks.add("workspacedead:deadstone:minecraft:stone");
        purifyBlocks.add("workspacedead:deadclay:minecraft:clay");
        purifyBlocks.add("workspacedead:deaddirt:minecraft:dirt");
        purifyBlocks.add("workspacedead:deadgravel:minecraft:gravel");
        purifyBlocks.add("workspacedead:deadslate:minecraft:slate");

        nether_opener = BUILDER.comment("id of item used to open Nether Portal. Blank = default fire.")
                .define("Nether Portal Opening Item", "");

        purify_blocks = BUILDER
                .comment("List of blocks to purify (strong), from:to, e.g. minecraft:sand:minecraft:stone")
                .defineList("Purify Crystal Block Transforms", Collections.emptyList(), o -> true);

        weak_purify_blocks = BUILDER
                .comment("List of blocks to purify (weak), from:to, e.g. minecraft:sand:minecraft:stone")
                .defineList("Weak Purify Crystal Block Transforms", purifyBlocks, o -> true);

        var purifyMobs = new ArrayList<String>();
        purifyMobs.add("workspacedead:skeletonspider:minecraft:spider");
        purifyMobs.add("workspacedead:skeletonslime:minecraft:slime");

        purify_mobs = BUILDER
                .comment(
                        "List of living entities to purify (strong), from:to, e.g. minecraft:skeleton:minecraft:zombie")
                .defineList("Purify Crystal Entity Transforms", purifyMobs, o -> true);

        var purifyMobsWeak = new ArrayList<String>();
        purifyMobsWeak.add("workspacedead:skeletoncow:minecraft:cow");
        purifyMobsWeak.add("workspacedead:skeletonsheep:minecraft:sheep");
        purifyMobsWeak.add("workspacedead:skeletonpig:minecraft:pig");
        purifyMobsWeak.add("workspacedead:skeletonchicken:minecraft:chicken");

        weak_purify_mobs = BUILDER
                .comment("List of living entities to purify (weak), from:to, e.g. minecraft:chicken:minecraft:cow")
                .defineList("Weak Purify Crystal Entity Transforms", purifyMobsWeak, o -> true);

        var poopMobs = new ArrayList<String>();
        poopMobs.add("minecraft:skeleton:workspacedead:skeletonpoop");
        poopMobs.add("minecraft:creeper:workspacedead:creeperpoop");
        poopMobs.add("minecraft:zombie:workspacedead:zombiepoop");
        poopMobs.add("minecraft:enderman:workspacedead:enderpoop");
        poopMobs.add("minecraft:cow:workspacedead:cowpoop");
        poopMobs.add("minecraft:phantom:workspacedead:phantompoop");
        poopMobs.add("minecraft:chicken:workspacedead:chickenpoop");
        poopMobs.add("minecraft:sheep:workspacedead:sheeppoop");
        poopMobs.add("minecraft:pig:workspacedead:pigpoop");
        poopMobs.add("minecraft:iron_golem:workspacedead:golempoop");
        poopMobs.add("minecraft:witch:workspacedead:witchpoop");
        poopMobs.add("minecraft:spider:workspacedead:spiderpoop");
        poopMobs.add("minecraft:villager:workspacedead:villagerpoop");
        poopMobs.add("minecraft:slime:workspacedead:slimepoop");
        poopMobs.add("workspacedead:blaze:workspacedead:blazepoop");
        poopMobs.add("workspacedead:draconicblaze:workspacedead:dragonpoop");

        poop_mobs = BUILDER.comment(
                "List of living entities and poop types, from:to, e.g. minecraft:chicken:workspacedead:chickenpoop")
                .defineList("Poop Sources", poopMobs, o -> true);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }

    public static void buildCaches() {
        var list = CommonConfig.poop_mobs.get();
        if (list != null) {
            poop_mobs_cache = new HashMap<String, String[]>();
            for (var idx = 0; idx < list.size(); idx++) {
                var t = list.get(idx);
                var arr = t.split(":");
                if (arr.length == 4) {
                    poop_mobs_cache.put(arr[0] + ":" + arr[1], new String[] { arr[0], arr[1], arr[2], arr[3] });
                }
            }
        }
    }
}