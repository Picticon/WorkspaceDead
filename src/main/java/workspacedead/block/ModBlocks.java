package workspacedead.block;

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
import workspacedead.block.BioMass.BioMassBlock;
import workspacedead.block.GrassyPotatoPlant.GrassyPotatoPlantBlock;
import workspacedead.block.PoopLantern.CarvedPoopBlock;
import workspacedead.block.PoopLantern.PoopBlock;
import workspacedead.item.ModCreativeModeTab;
//import workspacedead.block.animatedblock.AnimatedBlock;
import workspacedead.item.ModItems;
import workspacedead.world.feature.tree.DeadSaplingBlock;
import workspacedead.world.feature.tree.DeadTreeGrower;

public class ModBlocks {
    public static final DeferredRegister<Block> MOD_BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, WorkspaceDead.MOD_ID);

    public static final RegistryObject<Block> BIOMASS_BLOCK = registerBlockWithoutBlockItem("biomass_block", //
            () -> new BioMassBlock(BlockBehaviour.Properties.of(Material.MOSS).strength(.9f).sound(SoundType.FUNGUS)));

    public static final RegistryObject<Block> DEADSTONE = registerBlock("deadstone", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(.9f).sound(SoundType.STONE)), ModCreativeModeTab.ITEMS_TAB);
    public static final RegistryObject<Block> DEADSAND = registerBlock("deadsand", () -> new FallingBlock(BlockBehaviour.Properties.of(Material.SAND).strength(.35f).sound(SoundType.SAND)), ModCreativeModeTab.ITEMS_TAB);
    public static final RegistryObject<Block> DEADGRAVEL = registerBlock("deadgravel", () -> new FallingBlock(BlockBehaviour.Properties.of(Material.SAND).strength(.45f).sound(SoundType.GRAVEL)), ModCreativeModeTab.ITEMS_TAB);
    public static final RegistryObject<Block> DEADDIRT = registerBlock("deaddirt", () -> new Block(BlockBehaviour.Properties.of(Material.DIRT).strength(.35f).sound(SoundType.GRASS)), ModCreativeModeTab.ITEMS_TAB);
    public static final RegistryObject<Block> DEADCLAY = registerBlock("deadclay", () -> new Block(BlockBehaviour.Properties.of(Material.CLAY).strength(.40f).sound(SoundType.GRAVEL)), ModCreativeModeTab.ITEMS_TAB);
    public static final RegistryObject<Block> DEADSLATE = registerBlock("deadslate", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(1.25f).sound(SoundType.STONE)), ModCreativeModeTab.ITEMS_TAB);

    public static final RegistryObject<Block> POOPBLOCK2X = registerBlock("poopblock2x", //
            () -> new Block(BlockBehaviour.Properties.of(Material.SPONGE).strength(1).sound(SoundType.SLIME_BLOCK)), ModCreativeModeTab.ITEMS_TAB);

    public static final RegistryObject<Block> POOPBLOCK = registerBlock("poopblock", () -> new PoopBlock(BlockBehaviour.Properties.of(Material.SPONGE).strength(1).sound(SoundType.SLIME_BLOCK)), ModCreativeModeTab.ITEMS_TAB);
    public static final RegistryObject<Block> CARVED_POOPBLOCK = registerBlock("carvedpoopblock", () -> new CarvedPoopBlock(BlockBehaviour.Properties.of(Material.SPONGE).strength(1).sound(SoundType.SLIME_BLOCK)), ModCreativeModeTab.ITEMS_TAB);
    public static final RegistryObject<Block> POOP_O_LANTERN = registerBlock("poopolantern", //
            () -> new CarvedPoopBlock(BlockBehaviour.Properties.of(Material.SPONGE).strength(1).sound(SoundType.SLIME_BLOCK).lightLevel((p_187437_) -> {
                return 15;
            })), ModCreativeModeTab.ITEMS_TAB);

    public static final RegistryObject<Block> DEADLOG = registerBlock("deadlog", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)), ModCreativeModeTab.ITEMS_TAB);
    public static final RegistryObject<Block> DEADWOOD = registerBlock("deadwood", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)), ModCreativeModeTab.ITEMS_TAB);
    public static final RegistryObject<Block> DEADLOG_STRIPPED = registerBlock("deadlog_stripped", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)), ModCreativeModeTab.ITEMS_TAB);
    public static final RegistryObject<Block> DEADWOOD_STRIPPED = registerBlock("deadwood_stripped", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)), ModCreativeModeTab.ITEMS_TAB);
    public static final RegistryObject<Block> DEADSAPLING = registerBlock("deadsapling", () -> new DeadSaplingBlock(new DeadTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)), ModCreativeModeTab.ITEMS_TAB);

    public static final RegistryObject<DoorBlock> DEADDOOR = registerBlock("deaddoor", () -> new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(5f).noOcclusion()), ModCreativeModeTab.ITEMS_TAB);
    public static final RegistryObject<TrapDoorBlock> DEADTRAPDOOR = registerBlock("deadtrapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(5f).noOcclusion()), ModCreativeModeTab.ITEMS_TAB);
    public static final RegistryObject<SlabBlock> DEADSLAB = registerBlock("deadslab", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(5f).noOcclusion()), ModCreativeModeTab.ITEMS_TAB);

    public static final RegistryObject<DoorBlock> BURNTDOOR = registerBlock("burntdoor", () -> new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(5f).noOcclusion()), ModCreativeModeTab.ITEMS_TAB);
    public static final RegistryObject<TrapDoorBlock> BURNTTRAPDOOR = registerBlock("burnttrapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(5f).noOcclusion()), ModCreativeModeTab.ITEMS_TAB);
    public static final RegistryObject<SlabBlock> BURNTSLAB = registerBlock("burntslab", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(5f).noOcclusion()), ModCreativeModeTab.ITEMS_TAB);

    public static final RegistryObject<FenceBlock> DEADFENCE = registerBlock("deadfence", () -> new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(5f).noOcclusion()), ModCreativeModeTab.ITEMS_TAB);
    public static final RegistryObject<FenceGateBlock> DEADFENCE_GATE = registerBlock("deadfence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(5f).noOcclusion()), ModCreativeModeTab.ITEMS_TAB);
    public static final RegistryObject<FenceBlock> BURNTFENCE = registerBlock("burntfence", () -> new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(5f).noOcclusion()), ModCreativeModeTab.ITEMS_TAB);
    public static final RegistryObject<FenceGateBlock> BURNTFENCE_GATE = registerBlock("burntfence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(5f).noOcclusion()), ModCreativeModeTab.ITEMS_TAB);
    public static final RegistryObject<Block> GRASSYPOTATO_PLANT = registerBlockWithoutBlockItem("grassypotato_plant", () -> new GrassyPotatoPlantBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion()));

    public static final RegistryObject<Block> DEADPLANKS = registerBlock("deadplanks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)) {
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

    public static final RegistryObject<Block> BURNTPLANKS = registerBlock("burntplanks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)) {
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

    public static final RegistryObject<StairBlock> DEADSTAIRS = registerBlock("deadstairs", () -> new StairBlock(() -> DEADPLANKS.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.WOOD).strength(5f).noOcclusion()), ModCreativeModeTab.ITEMS_TAB);
    public static final RegistryObject<StairBlock> BURNTSTAIRS = registerBlock("burntstairs", () -> new StairBlock(() -> BURNTPLANKS.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.WOOD).strength(5f).noOcclusion()), ModCreativeModeTab.ITEMS_TAB);

    public static final RegistryObject<Block> DEADLEAVES = registerBlock("deadleaves", () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)) {
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

    // public static final RegistryObject<Block> ANIMATED_BLOCK =
    // registerBlockWithoutBlockItem("animated_block",
    // () -> new
    // AnimatedBlock(BlockBehaviour.Properties.of(Material.STONE).noOcclusion()));

    // private static <T extends Block> RegistryObject<T>
    // registerBlockWithoutBlockItem(String name, Supplier<T> block) {
    // return MOD_BLOCKS.register(name, block);
    // }

    // private static <T extends Block> RegistryObject<T> registerBlock(String name,
    // Supplier<T> block, CreativeModeTab tab, String tooltipKey) {
    // RegistryObject<T> toReturn = MOD_BLOCKS.register(name, block);
    // registerBlockItem(name, toReturn, tab, tooltipKey);
    // return toReturn;
    // }

    // private static <T extends Block> RegistryObject<Item>
    // registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab,
    // String tooltipKey) {
    // return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new
    // Item.Properties().tab(tab)) {
    // @Override
    // public void appendHoverText(ItemStack pStack, @Nullable Level pLevel,
    // List<Component> pTooltip, TooltipFlag pFlag) {
    // pTooltip.add(new TranslatableComponent(tooltipKey));
    // }
    // });
    // }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = MOD_BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    private static <T extends Block> RegistryObject<T> registerBlockWithoutBlockItem(String name, Supplier<T> block) {
        return MOD_BLOCKS.register(name, block);
    }

    public static void register(IEventBus eventBus) {
        MOD_BLOCKS.register(eventBus);
    }
}
