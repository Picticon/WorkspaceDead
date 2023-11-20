package workspacedead.registry;

import java.util.function.Supplier;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import workspacedead.WorkspaceDead;
import workspacedead.block.DeadFarmland;
import workspacedead.block.DeadGrassBlock;
import workspacedead.block.GrassyPotatoPlantBlock;
import workspacedead.block.ModFlammableRotatedPillarBlock;
import workspacedead.block.MutatingFarmland;
import workspacedead.block.PotatoPortalBlock;
import workspacedead.block.BioMass.BioMassBlock;
import workspacedead.block.FullMetalAlchemiser.FullMetalAlchemiserBlock;
import workspacedead.block.KubeJSTable.KubeJSTableBlock;
import workspacedead.block.MrHanky.MrHankyBlock;
import workspacedead.block.PoopLantern.CarvedPoopBlock;
import workspacedead.block.PoopLantern.PoopBlock;
import workspacedead.block.Saturator.DesaturatorBlock;
import workspacedead.block.Saturator.SaturatorBlock;
import workspacedead.block.SpawnEggPlant.SpawnEggPlantBlock;
import workspacedead.block.generators.BlackGeneratorBlock;
import workspacedead.block.generators.BlueGeneratorBlock;
import workspacedead.block.generators.BrownGeneratorBlock;
import workspacedead.block.generators.CyanGeneratorBlock;
import workspacedead.block.generators.GrayGeneratorBlock;
import workspacedead.block.generators.GreenGeneratorBlock;
import workspacedead.block.generators.LightBlueGeneratorBlock;
import workspacedead.block.generators.LightGrayGeneratorBlock;
import workspacedead.block.generators.LimeGeneratorBlock;
import workspacedead.block.generators.MagentaGeneratorBlock;
import workspacedead.block.generators.OrangeGeneratorBlock;
import workspacedead.block.generators.PinkGeneratorBlock;
import workspacedead.block.generators.PurpleGeneratorBlock;
import workspacedead.block.generators.RedGeneratorBlock;
import workspacedead.block.generators.WhiteGeneratorBlock;
import workspacedead.block.generators.YellowGeneratorBlock;
import workspacedead.item.ModCreativeModeTab;
import workspacedead.item.custom.PoopBlock2xItem;
import workspacedead.item.custom.PoopBlockItem;
import workspacedead.world.feature.tree.DeadSaplingBlock;
import workspacedead.world.feature.tree.DeadTreeGrower;

public class MyBlocks {
    public static final DeferredRegister<Block> MOD_BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
            WorkspaceDead.MOD_ID);

    public static final RegistryObject<Block> FULLMETALALCHEMISER_BLOCK = registerBlockAndItem(
            "fullmetalalchemiser_block", () -> new FullMetalAlchemiserBlock(), ModCreativeModeTab.ITEMS_TAB);

    public static final RegistryObject<Block> SATURATOR_BLOCK = registerBlockAndItem("saturator_block",
            () -> new SaturatorBlock(), ModCreativeModeTab.ITEMS_TAB);

    public static final RegistryObject<Block> DESATURATOR_BLOCK = registerBlockAndItem("desaturator_block",
            () -> new DesaturatorBlock(), ModCreativeModeTab.ITEMS_TAB);

    public static final RegistryObject<Block> KUBEJS_TABLE_BLOCK = registerBlockAndItem("kubejs_table_block",
            () -> new KubeJSTableBlock(), ModCreativeModeTab.ITEMS_TAB);

    public static final RegistryObject<Block> BIOMASS_BLOCK = registerBlockWithoutBlockItem("biomass_block", //
            () -> new   BioMassBlock(BlockBehaviour.Properties.of(Material.MOSS).strength(.9f).sound(SoundType.FUNGUS)));

    public static final RegistryObject<Block> MRHANKY_BLOCK = registerBlockWithoutBlockItem("mrhanky_block", //
            () -> new MrHankyBlock(BlockBehaviour.Properties.of(Material.STONE).strength(.9f).sound(SoundType.STONE)));

    public static final RegistryObject<Block> POTATOBLOCK = registerBlockAndItem("potato_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.CACTUS).strength(.9f).sound(SoundType.CROP)),
            ModCreativeModeTab.ITEMS_TAB);

    public static final RegistryObject<Block> POTATOPORTALBLOCK = registerBlockWithoutBlockItem("potato_portal_block",
            PotatoPortalBlock::new);

    public static final RegistryObject<Block> DEAD_FARMLAND = registerBlockAndItem("deadfarmland",
            () -> new DeadFarmland(BlockBehaviour.Properties.of(Material.DIRT).strength(.9f).sound(SoundType.GRAVEL)),
            ModCreativeModeTab.ITEMS_TAB);

    public static final RegistryObject<Block> MUTATING_FARMLAND = registerBlockAndItem("mutating_farmland", 
            () -> new MutatingFarmland(
                    BlockBehaviour.Properties.of(Material.DIRT).strength(.9f).sound(SoundType.GRAVEL)),
            ModCreativeModeTab.ITEMS_TAB);

    public static final RegistryObject<Block> DEADSTONE = registerBlockAndItem("deadstone",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(.9f).sound(SoundType.STONE)),
            ModCreativeModeTab.ITEMS_TAB);
    public static final RegistryObject<Block> DEADSAND = registerBlockAndItem("deadsand",
            () -> new FallingBlock(BlockBehaviour.Properties.of(Material.SAND).strength(.35f).sound(SoundType.SAND)),
            ModCreativeModeTab.ITEMS_TAB);
    public static final RegistryObject<Block> DEADGRAVEL = registerBlockAndItem("deadgravel",
            () -> new FallingBlock(BlockBehaviour.Properties.of(Material.SAND).strength(.45f).sound(SoundType.GRAVEL)),
            ModCreativeModeTab.ITEMS_TAB);
    public static final RegistryObject<Block> DEADDIRT = registerBlockAndItem("deaddirt",
            () -> new Block(BlockBehaviour.Properties.of(Material.DIRT).strength(.35f).sound(SoundType.GRASS)),
            ModCreativeModeTab.ITEMS_TAB);
    public static final RegistryObject<Block> DEADCLAY = registerBlockAndItem("deadclay",
            () -> new Block(BlockBehaviour.Properties.of(Material.CLAY).strength(.40f).sound(SoundType.GRAVEL)),
            ModCreativeModeTab.ITEMS_TAB);
    public static final RegistryObject<Block> DEADSLATE = registerBlockAndItem("deadslate",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(1.25f).sound(SoundType.STONE)),
            ModCreativeModeTab.ITEMS_TAB);
    public static final RegistryObject<Block> DEADORE = registerBlockAndItem("deadore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(2.25f).sound(SoundType.STONE)),
            ModCreativeModeTab.ITEMS_TAB);

    public static final RegistryObject<Block> DEADLOG = registerBlockAndItem("deadlog",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)),
            ModCreativeModeTab.ITEMS_TAB);
    public static final RegistryObject<Block> DEADWOOD = registerBlockAndItem("deadwood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)),
            ModCreativeModeTab.ITEMS_TAB);

    public static final RegistryObject<Block> DEADLOG_STRIPPED = registerBlockAndItem("deadlog_stripped",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)),
            ModCreativeModeTab.ITEMS_TAB);
    public static final RegistryObject<Block> DEADWOOD_STRIPPED = registerBlockAndItem("deadwood_stripped",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)),
            ModCreativeModeTab.ITEMS_TAB);
    public static final RegistryObject<Block> DEADSAPLING = registerBlockAndItem("deadsapling",
            () -> new DeadSaplingBlock(new DeadTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)),
            ModCreativeModeTab.ITEMS_TAB);

    /// GENERATORS

    public static final RegistryObject<Block> YELLOW_GENERATOR = registerBlockAndItem("yellow_generator_block",
            () -> new YellowGeneratorBlock(BlockBehaviour.Properties.of(Material.STONE)), ModCreativeModeTab.ITEMS_TAB);

    public static final RegistryObject<Block> BLACK_GENERATOR = registerBlockAndItem("black_generator_block",
            () -> new BlackGeneratorBlock(BlockBehaviour.Properties.of(Material.STONE)), ModCreativeModeTab.ITEMS_TAB);

    public static final RegistryObject<Block> BLUE_GENERATOR = registerBlockAndItem("blue_generator_block",
            () -> new BlueGeneratorBlock(BlockBehaviour.Properties.of(Material.STONE)), ModCreativeModeTab.ITEMS_TAB);

    public static final RegistryObject<Block> BROWN_GENERATOR = registerBlockAndItem("brown_generator_block",
            () -> new BrownGeneratorBlock(BlockBehaviour.Properties.of(Material.STONE)), ModCreativeModeTab.ITEMS_TAB);

    public static final RegistryObject<Block> CYAN_GENERATOR = registerBlockAndItem("cyan_generator_block",
            () -> new CyanGeneratorBlock(BlockBehaviour.Properties.of(Material.STONE)), ModCreativeModeTab.ITEMS_TAB);

    public static final RegistryObject<Block> GRAY_GENERATOR = registerBlockAndItem("gray_generator_block",
            () -> new GrayGeneratorBlock(BlockBehaviour.Properties.of(Material.STONE)), ModCreativeModeTab.ITEMS_TAB);

    public static final RegistryObject<Block> GREEN_GENERATOR = registerBlockAndItem("green_generator_block",
            () -> new GreenGeneratorBlock(BlockBehaviour.Properties.of(Material.STONE)), ModCreativeModeTab.ITEMS_TAB);

    public static final RegistryObject<Block> LIGHTBLUE_GENERATOR = registerBlockAndItem("light_blue_generator_block",
            () -> new LightBlueGeneratorBlock(BlockBehaviour.Properties.of(Material.STONE)), ModCreativeModeTab.ITEMS_TAB);

    public static final RegistryObject<Block> LIGHTGRAY_GENERATOR = registerBlockAndItem("light_gray_generator_block",
            () -> new LightGrayGeneratorBlock(BlockBehaviour.Properties.of(Material.STONE)), ModCreativeModeTab.ITEMS_TAB);

    public static final RegistryObject<Block> LIME_GENERATOR = registerBlockAndItem("lime_generator_block",
            () -> new LimeGeneratorBlock(BlockBehaviour.Properties.of(Material.STONE)), ModCreativeModeTab.ITEMS_TAB);

    public static final RegistryObject<Block> MAGENTA_GENERATOR = registerBlockAndItem("magenta_generator_block",
            () -> new MagentaGeneratorBlock(BlockBehaviour.Properties.of(Material.STONE)), ModCreativeModeTab.ITEMS_TAB);

    public static final RegistryObject<Block> ORANGE_GENERATOR = registerBlockAndItem("orange_generator_block",
            () -> new OrangeGeneratorBlock(BlockBehaviour.Properties.of(Material.STONE)), ModCreativeModeTab.ITEMS_TAB);

    public static final RegistryObject<Block> PINK_GENERATOR = registerBlockAndItem("pink_generator_block",
            () -> new PinkGeneratorBlock(BlockBehaviour.Properties.of(Material.STONE)), ModCreativeModeTab.ITEMS_TAB);

    public static final RegistryObject<Block> PURPLE_GENERATOR = registerBlockAndItem("purple_generator_block",
            () -> new PurpleGeneratorBlock(BlockBehaviour.Properties.of(Material.STONE)), ModCreativeModeTab.ITEMS_TAB);

    public static final RegistryObject<Block> RED_GENERATOR = registerBlockAndItem("red_generator_block",
            () -> new RedGeneratorBlock(BlockBehaviour.Properties.of(Material.STONE)), ModCreativeModeTab.ITEMS_TAB);

    public static final RegistryObject<Block> WHITE_GENERATOR = registerBlockAndItem("white_generator_block",
            () -> new WhiteGeneratorBlock(BlockBehaviour.Properties.of(Material.STONE)), ModCreativeModeTab.ITEMS_TAB);


    /// GENERATORS END

    public static final RegistryObject<Block> GLOD_BLOCK = registerBlockAndItem("glod_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(1f).sound(SoundType.STONE)),
            ModCreativeModeTab.ITEMS_TAB);
    public static final RegistryObject<Block> GLOD_SATURATED_BLOCK = registerBlockAndItem(
            "glod_saturated_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.HEAVY_METAL).strength(2f).sound(SoundType.METAL)),
            ModCreativeModeTab.ITEMS_TAB);

    public static final RegistryObject<Block> INRO_BLOCK = registerBlockAndItem("inro_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(1f).sound(SoundType.STONE)),
            ModCreativeModeTab.ITEMS_TAB);
    public static final RegistryObject<Block> INRO_SATURATED_BLOCK = registerBlockAndItem("inro_saturated_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.HEAVY_METAL).strength(2f).sound(SoundType.METAL)),
            ModCreativeModeTab.ITEMS_TAB);

    public static final RegistryObject<Block> RETDESON_BLOCK = registerBlockAndItem("retdeson_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(1f).sound(SoundType.STONE)),
            ModCreativeModeTab.ITEMS_TAB);
    public static final RegistryObject<Block> RETDESON_SATURATED_BLOCK = registerBlockAndItem(
            "retdeson_saturated_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.HEAVY_METAL).strength(2f).sound(SoundType.METAL)),
            ModCreativeModeTab.ITEMS_TAB);

    public static final RegistryObject<Block> CEPPRO_BLOCK = registerBlockAndItem("ceppro_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(1f).sound(SoundType.STONE)),
            ModCreativeModeTab.ITEMS_TAB);
    public static final RegistryObject<Block> CEPPRO_SATURATED_BLOCK = registerBlockAndItem("ceppro_saturated_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.HEAVY_METAL).strength(2f).sound(SoundType.METAL)),
            ModCreativeModeTab.ITEMS_TAB);

    public static final RegistryObject<Block> CALO_BLOCK = registerBlockAndItem("calo_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(1f).sound(SoundType.STONE)),
            ModCreativeModeTab.ITEMS_TAB);
    public static final RegistryObject<Block> CALO_SATURATED_BLOCK = registerBlockAndItem("calo_saturated_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.HEAVY_METAL).strength(2f).sound(SoundType.METAL)),
            ModCreativeModeTab.ITEMS_TAB);

    public static final RegistryObject<Block> DANIDOM_BLOCK = registerBlockAndItem("danidom_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(1f).sound(SoundType.STONE)),
            ModCreativeModeTab.ITEMS_TAB);
    public static final RegistryObject<Block> DANIDOM_SATURATED_BLOCK = registerBlockAndItem("danidom_saturated_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.HEAVY_METAL).strength(2f).sound(SoundType.METAL)),
            ModCreativeModeTab.ITEMS_TAB);

    public static final RegistryObject<Block> EMELDAR_BLOCK = registerBlockAndItem("emeldar_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(1f).sound(SoundType.STONE)),
            ModCreativeModeTab.ITEMS_TAB);
    public static final RegistryObject<Block> EMELDAR_SATURATED_BLOCK = registerBlockAndItem("emeldar_saturated_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.HEAVY_METAL).strength(2f).sound(SoundType.METAL)),
            ModCreativeModeTab.ITEMS_TAB);

    public static final RegistryObject<Block> LIPAS_BLOCK = registerBlockAndItem("lipas_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(1f).sound(SoundType.STONE)),
            ModCreativeModeTab.ITEMS_TAB);
    public static final RegistryObject<Block> LIPAS_SATURATED_BLOCK = registerBlockAndItem("lipas_saturated_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.HEAVY_METAL).strength(2f).sound(SoundType.METAL)),
            ModCreativeModeTab.ITEMS_TAB);

    // public static final RegistryObject<Block> POOPBLOCK =
    // RegisterBlockAndItem("poopblock", () -> new PoopBlock(),
    // () -> new PoopBlockItem(new PoopBlock()), ModCreativeModeTab.ITEMS_TAB);
    public static final RegistryObject<Block> POOPBLOCK = MOD_BLOCKS.register("poopblock", PoopBlock::new);
    public static final RegistryObject<Item> POOPBLOCK_ITEM = MyItems.ITEMS.register("poopblock",
            () -> new PoopBlockItem(POOPBLOCK.get()));
    public static final RegistryObject<Block> POOPBLOCK2X = MOD_BLOCKS.register("poopblock2x", //
            () -> new Block(BlockBehaviour.Properties.of(Material.SPONGE).strength(1).sound(SoundType.SLIME_BLOCK)));
    public static final RegistryObject<Item> POOPBLOCK2X_ITEM = MyItems.ITEMS.register("poopblock2x",
            () -> new PoopBlock2xItem(POOPBLOCK2X.get()));

    public static final RegistryObject<Block> CARVED_POOPBLOCK = registerBlockAndItem("carvedpoopblock",
            () -> new CarvedPoopBlock(
                    BlockBehaviour.Properties.of(Material.SPONGE).strength(1).sound(SoundType.SLIME_BLOCK)),
            ModCreativeModeTab.ITEMS_TAB);
    public static final RegistryObject<Block> POOP_O_LANTERN = registerBlockAndItem("poopolantern", //
            () -> new CarvedPoopBlock(BlockBehaviour.Properties.of(Material.SPONGE).strength(1)
                    .sound(SoundType.SLIME_BLOCK).lightLevel((p_187437_) -> {
                        return 15;
                    })),
            ModCreativeModeTab.ITEMS_TAB);

    public static final RegistryObject<DoorBlock> DEADDOOR = registerBlockAndItem("deaddoor",
            () -> new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(5f).noOcclusion()),
            ModCreativeModeTab.ITEMS_TAB);
    public static final RegistryObject<TrapDoorBlock> DEADTRAPDOOR = registerBlockAndItem("deadtrapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(5f).noOcclusion()),
            ModCreativeModeTab.ITEMS_TAB);
    public static final RegistryObject<SlabBlock> DEADSLAB = registerBlockAndItem("deadslab",
            () -> new SlabBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(5f).noOcclusion()),
            ModCreativeModeTab.ITEMS_TAB);
    public static final RegistryObject<FenceBlock> DEADFENCE = registerBlockAndItem("deadfence",
            () -> new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(5f).noOcclusion()),
            ModCreativeModeTab.ITEMS_TAB);
    public static final RegistryObject<FenceGateBlock> DEADFENCE_GATE = registerBlockAndItem("deadfence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(5f).noOcclusion()),
            ModCreativeModeTab.ITEMS_TAB);

    public static final RegistryObject<Block> DEADPLANKS = registerBlockAndItem("deadplanks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
                    return 5;
                }
            }, ModCreativeModeTab.ITEMS_TAB);

    public static final RegistryObject<DoorBlock> BURNTDOOR = registerBlockAndItem("burntdoor",
            () -> new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(5f).noOcclusion()),
            ModCreativeModeTab.ITEMS_TAB);
    public static final RegistryObject<TrapDoorBlock> BURNTTRAPDOOR = registerBlockAndItem("burnttrapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(5f).noOcclusion()),
            ModCreativeModeTab.ITEMS_TAB);
    public static final RegistryObject<SlabBlock> BURNTSLAB = registerBlockAndItem("burntslab",
            () -> new SlabBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(5f).noOcclusion()),
            ModCreativeModeTab.ITEMS_TAB);
    public static final RegistryObject<FenceBlock> BURNTFENCE = registerBlockAndItem("burntfence",
            () -> new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(5f).noOcclusion()),
            ModCreativeModeTab.ITEMS_TAB);
    public static final RegistryObject<FenceGateBlock> BURNTFENCE_GATE = registerBlockAndItem("burntfence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(5f).noOcclusion()),
            ModCreativeModeTab.ITEMS_TAB);

    public static final RegistryObject<Block> BURNTPLANKS = registerBlockAndItem("burntplanks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
                    return 5;
                }
            }, ModCreativeModeTab.ITEMS_TAB);

    public static final RegistryObject<StairBlock> DEADSTAIRS = registerBlockAndItem("deadstairs",
            () -> new StairBlock(() -> DEADPLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.of(Material.WOOD).strength(5f).noOcclusion()),
            ModCreativeModeTab.ITEMS_TAB);
    public static final RegistryObject<StairBlock> BURNTSTAIRS = registerBlockAndItem("burntstairs",
            () -> new StairBlock(() -> BURNTPLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.of(Material.WOOD).strength(5f).noOcclusion()),
            ModCreativeModeTab.ITEMS_TAB);

    public static final RegistryObject<Block> DEADLEAVES = registerBlockAndItem("deadleaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
                    return 60;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
                    return 30;
                }
            }, ModCreativeModeTab.ITEMS_TAB);

    public static final RegistryObject<Block> DEADGRASS = registerBlockAndItem("deadgrass",
            () -> new DeadGrassBlock(BlockBehaviour.Properties.copy(Blocks.GRASS).noOcclusion()),
            ModCreativeModeTab.ITEMS_TAB);

    public static final RegistryObject<Block> GRASSYPOTATO_PLANT = registerBlockWithoutBlockItem("grassypotato_plant",
            () -> new GrassyPotatoPlantBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion()));

    public static final RegistryObject<Block> SPAWNEGG_PLANT = registerBlockWithoutBlockItem("spawnegg_plant",
            () -> new SpawnEggPlantBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion()));

    private static <T extends Block> RegistryObject<T> registerBlockAndItem(String name, Supplier<T> block,
            CreativeModeTab tab) {
        RegistryObject<T> toReturn = MOD_BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
            CreativeModeTab tab) {
        return MyItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    private static <T extends Block> RegistryObject<T> registerBlockWithoutBlockItem(String name, Supplier<T> block) {
        return MOD_BLOCKS.register(name, block);
    }

    public static void register(IEventBus eventBus) {
        MOD_BLOCKS.register(eventBus);
    }
}
