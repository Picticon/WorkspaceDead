package workspacedead.datagen;

import org.jetbrains.annotations.NotNull;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import workspacedead.WorkspaceDead;
import workspacedead.registry.MyBlocks;

///
/// This class creates  MODEL, BLOCKSTATE, and ITEM  data for BLOCKS
/// This can be used instead of 100 JSON files
///
public class ModBlocksStateProvider extends BlockStateProvider {
    public ModBlocksStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, WorkspaceDead.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        // ALL CUSTOM CALLS START WITH "make"

        // simpleBlock(ModBlocks.BIOMASS.get());
        simpleBlock(MyBlocks.BIOMASS_BLOCK.get(),
                new ModelFile.UncheckedModelFile(new ResourceLocation(WorkspaceDead.MOD_ID, "block/biomass_block")));
        itemBlock(MyBlocks.BIOMASS_BLOCK.get());

        // simpleBlock(ModBlocks.MRHANKY_BLOCK.get(),
        // new ModelFile.UncheckedModelFile(new ResourceLocation(WorkspaceDead.MOD_ID,
        // "block/mrhanky_block")));
        this.horizontalBlock(MyBlocks.MRHANKY_BLOCK.get(),
                new ModelFile.UncheckedModelFile(new ResourceLocation(WorkspaceDead.MOD_ID, "block/mrhanky_block")));
        itemBlock(MyBlocks.MRHANKY_BLOCK.get());

        doorBlock((DoorBlock) MyBlocks.DEADDOOR.get(),
                new ResourceLocation(WorkspaceDead.MOD_ID, "block/deaddoor_bottom"), //
                new ResourceLocation(WorkspaceDead.MOD_ID, "block/deaddoor_top"));
        itemGenerated(MyBlocks.DEADDOOR);

        doorBlock((DoorBlock) MyBlocks.BURNTDOOR.get(),
                new ResourceLocation(WorkspaceDead.MOD_ID, "block/burntdoor_bottom"), //
                new ResourceLocation(WorkspaceDead.MOD_ID, "block/burntdoor_top"));
        itemGenerated(MyBlocks.BURNTDOOR);

        trapdoorBlock((TrapDoorBlock) MyBlocks.DEADTRAPDOOR.get(), blockTexture(MyBlocks.DEADTRAPDOOR.get()), true);
        makeItemFromParent(MyBlocks.DEADTRAPDOOR, WorkspaceDead.MOD_ID + ":block/deadtrapdoor_bottom");

        trapdoorBlock((TrapDoorBlock) MyBlocks.BURNTTRAPDOOR.get(), blockTexture(MyBlocks.BURNTTRAPDOOR.get()), true);
        makeItemFromParent(MyBlocks.BURNTTRAPDOOR, WorkspaceDead.MOD_ID + ":block/burnttrapdoor_bottom");

        itemBlock(MyBlocks.DEADLOG);
        logBlock((RotatedPillarBlock) MyBlocks.DEADLOG.get());

        itemBlock(MyBlocks.DEADWOOD);
        axisBlock((RotatedPillarBlock) MyBlocks.DEADWOOD.get(), blockTexture(MyBlocks.DEADLOG.get()),
                blockTexture(MyBlocks.DEADLOG.get()));

        itemBlock(MyBlocks.DEADLOG_STRIPPED);
        axisBlock((RotatedPillarBlock) MyBlocks.DEADLOG_STRIPPED.get(),
                new ResourceLocation(WorkspaceDead.MOD_ID, "block/deadlog_stripped"), //
                new ResourceLocation(WorkspaceDead.MOD_ID, "block/deadlog_top_stripped"));

        axisBlock((RotatedPillarBlock) MyBlocks.DEADWOOD_STRIPPED.get(),
                new ResourceLocation(WorkspaceDead.MOD_ID, "block/deadwood_stripped"), //
                new ResourceLocation(WorkspaceDead.MOD_ID, "block/deadwood_top_stripped"));
        itemBlock(MyBlocks.DEADWOOD_STRIPPED);

        makeSimpleBlockAndItem(MyBlocks.DEADPLANKS.get());
        makeSimpleBlockAndItem(MyBlocks.BURNTPLANKS.get());
        makeSimpleBlockAndItem(MyBlocks.DEADLEAVES.get());
        makeSimpleBlockAndItem(MyBlocks.DEADDIRT.get());
        makeSimpleBlockAndItem(MyBlocks.DEADSAND.get());
        makeSimpleBlockAndItem(MyBlocks.DEADGRAVEL.get());
        makeSimpleBlockAndItem(MyBlocks.DEADSTONE.get());
        makeSimpleBlockAndItem(MyBlocks.DEADCLAY.get());
        makeSimpleBlockAndItem(MyBlocks.DEADSLATE.get());
        makeSimpleBlockAndItem(MyBlocks.DEADORE.get());
        makeSimpleBlockAndItem(MyBlocks.POOPBLOCK.get());
        makeSimpleBlockAndItem(MyBlocks.POOPBLOCK2X.get());

        makeSimpleBlockAndItem(MyBlocks.GLOD_BLOCK.get());
        makeSimpleBlockAndItem(MyBlocks.GLOD_SATURATED_BLOCK.get());
        makeSimpleBlockAndItem(MyBlocks.CALO_BLOCK.get());
        makeSimpleBlockAndItem(MyBlocks.CALO_SATURATED_BLOCK.get());
        makeSimpleBlockAndItem(MyBlocks.CEPPRO_BLOCK.get());
        makeSimpleBlockAndItem(MyBlocks.CEPPRO_SATURATED_BLOCK.get());
        makeSimpleBlockAndItem(MyBlocks.DANIDOM_BLOCK.get());
        makeSimpleBlockAndItem(MyBlocks.DANIDOM_SATURATED_BLOCK.get());
        makeSimpleBlockAndItem(MyBlocks.EMELDAR_BLOCK.get());
        makeSimpleBlockAndItem(MyBlocks.EMELDAR_SATURATED_BLOCK.get());
        makeSimpleBlockAndItem(MyBlocks.INRO_BLOCK.get());
        makeSimpleBlockAndItem(MyBlocks.INRO_SATURATED_BLOCK.get());
        makeSimpleBlockAndItem(MyBlocks.RETDESON_BLOCK.get());
        makeSimpleBlockAndItem(MyBlocks.RETDESON_SATURATED_BLOCK.get());
        makeSimpleBlockAndItem(MyBlocks.LIPAS_BLOCK.get());
        makeSimpleBlockAndItem(MyBlocks.LIPAS_SATURATED_BLOCK.get());

        cubeColumn(MyBlocks.DEAD_FARMLAND.get(), blockTexture(Blocks.DIRT),
                new ResourceLocation(WorkspaceDead.MOD_ID, "block/deadfarmland"));
        itemBlock(MyBlocks.DEAD_FARMLAND);

        // cubeColumn(MyBlocks.MUTATING_FARMLAND.get(), blockTexture(Blocks.DIRT),
        //         new ResourceLocation(WorkspaceDead.MOD_ID, "block/mutating_farmland"));
        itemBlock(MyBlocks.MUTATING_FARMLAND);

        makeFacingBlock(MyBlocks.CARVED_POOPBLOCK);
        itemFromBlock(MyBlocks.CARVED_POOPBLOCK);
        makeFacingBlock(MyBlocks.POOP_O_LANTERN);
        itemFromBlock(MyBlocks.POOP_O_LANTERN);

        // inv item is simpleitem
        simpleBlock(MyBlocks.DEADSAPLING.get(), models().cross(MyBlocks.DEADSAPLING.get().getRegistryName().getPath(),
                blockTexture(MyBlocks.DEADSAPLING.get())));
        itemGenerated(MyBlocks.DEADSAPLING);

        // inv item is simpleitem
        simpleBlock(MyBlocks.DEADGRASS.get(), models().cross(MyBlocks.DEADGRASS.get().getRegistryName().getPath(),
                blockTexture(MyBlocks.DEADGRASS.get())));
        itemGenerated(MyBlocks.DEADGRASS);

        slabBlock(MyBlocks.DEADSLAB, MyBlocks.DEADPLANKS);
        itemFromBlock(MyBlocks.DEADSLAB);
        slabBlock(MyBlocks.BURNTSLAB, MyBlocks.BURNTPLANKS);
        itemFromBlock(MyBlocks.BURNTSLAB);
        fenceBlock(MyBlocks.DEADFENCE, MyBlocks.DEADPLANKS);
        parentBlockWithTexture(MyBlocks.DEADFENCE, "block/fence_inventory", "texture", MyBlocks.DEADPLANKS);
        fenceBlock(MyBlocks.BURNTFENCE, MyBlocks.BURNTPLANKS);
        parentBlockWithTexture(MyBlocks.BURNTFENCE, "block/fence_inventory", "texture", MyBlocks.BURNTPLANKS);
        fenceGateBlock(MyBlocks.DEADFENCE_GATE, MyBlocks.DEADPLANKS);
        itemFromBlock(MyBlocks.DEADFENCE_GATE);
        fenceGateBlock(MyBlocks.BURNTFENCE_GATE, MyBlocks.BURNTPLANKS);
        itemFromBlock(MyBlocks.BURNTFENCE_GATE);
        stairsBlock(MyBlocks.DEADSTAIRS, MyBlocks.DEADPLANKS);
        itemFromBlock(MyBlocks.DEADSTAIRS);
        stairsBlock(MyBlocks.BURNTSTAIRS, MyBlocks.BURNTPLANKS);
        itemFromBlock(MyBlocks.BURNTSTAIRS);

        makeFacingBlock(MyBlocks.YELLOW_GENERATOR);
        itemFromBlock(MyBlocks.YELLOW_GENERATOR);

        makeFacingBlock(MyBlocks.BLACK_GENERATOR);
        itemFromBlock(MyBlocks.BLACK_GENERATOR);
        makeFacingBlock(MyBlocks.BLUE_GENERATOR);
        itemFromBlock(MyBlocks.BLUE_GENERATOR);
        makeFacingBlock(MyBlocks.BROWN_GENERATOR);
        itemFromBlock(MyBlocks.BROWN_GENERATOR);
        makeFacingBlock(MyBlocks.CYAN_GENERATOR);
        itemFromBlock(MyBlocks.CYAN_GENERATOR);
        makeFacingBlock(MyBlocks.GRAY_GENERATOR);
        itemFromBlock(MyBlocks.GRAY_GENERATOR);
        makeFacingBlock(MyBlocks.GREEN_GENERATOR);
        itemFromBlock(MyBlocks.GREEN_GENERATOR);
        makeFacingBlock(MyBlocks.LIGHTBLUE_GENERATOR);
        itemFromBlock(MyBlocks.LIGHTBLUE_GENERATOR);
        makeFacingBlock(MyBlocks.LIGHTGRAY_GENERATOR);
        itemFromBlock(MyBlocks.LIGHTGRAY_GENERATOR);
        makeFacingBlock(MyBlocks.LIME_GENERATOR);
        itemFromBlock(MyBlocks.LIME_GENERATOR);
        makeFacingBlock(MyBlocks.MAGENTA_GENERATOR);
        itemFromBlock(MyBlocks.MAGENTA_GENERATOR);
        makeFacingBlock(MyBlocks.ORANGE_GENERATOR);
        itemFromBlock(MyBlocks.ORANGE_GENERATOR);
        makeFacingBlock(MyBlocks.PINK_GENERATOR);
        itemFromBlock(MyBlocks.PINK_GENERATOR);
        makeFacingBlock(MyBlocks.PURPLE_GENERATOR);
        itemFromBlock(MyBlocks.PURPLE_GENERATOR);
        makeFacingBlock(MyBlocks.RED_GENERATOR);
        itemFromBlock(MyBlocks.RED_GENERATOR);
        makeFacingBlock(MyBlocks.WHITE_GENERATOR);
        itemFromBlock(MyBlocks.WHITE_GENERATOR);

        // makeSimpleBlockAndItem(ModBlocks.POTATOBLOCK.get());
        // simpleBlock(ModBlocks.POTATOBLOCK.get(), (a) -> {
        // return "";
        // });
        // itemBlock(ModBlocks.POTATOBLOCK.get());
    }

    private <T extends Block> void makeFacingBlock(RegistryObject<Block> regBlock) {
        // // EXAMPLE_BLOCK_2: Has Property BlockStateProperties#HORIZONTAL_FACING
        // this.getVariantBuilder(regBlock.get()) // Get variant builder
        // .forAllStates(state -> // For all possible states
        // ConfiguredModel.builder() // Creates configured model builder
        // .modelFile(modelFile(regBlock.get())) // Can show 'modelFile'
        // .rotationY((int)
        // state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot()) // Rotates
        // 'modelFile' on the Y axis depending on the property
        // .build() // Creates the array of configured models
        // );
        this.horizontalBlock(regBlock.get(), //
                new ResourceLocation(regBlock.getId().getNamespace(), "block/" + regBlock.getId().getPath() + "_side"), //
                new ResourceLocation(regBlock.getId().getNamespace(), "block/" + regBlock.getId().getPath() + "_front"), //
                new ResourceLocation(regBlock.getId().getNamespace(), "block/" + regBlock.getId().getPath() + "_top"));
    }

    private <T extends Block, U extends Block> void parentBlockWithTexture(RegistryObject<T> registeredBlock,
            String parent, String layername, RegistryObject<U> textureblock) {
        this.itemModels().getBuilder(registeredBlock.get().getRegistryName().getPath())
                .parent(new ModelFile.UncheckedModelFile(parent)) //
                .texture(layername, new ResourceLocation(textureblock.getId().getNamespace(),
                        "block/" + textureblock.getId().getPath()));
    }

    /// Creates an item model using a block model
    private <T extends Block> void itemFromBlock(RegistryObject<T> registeredBlock) {
        makeItemFromParent(registeredBlock,
                WorkspaceDead.MOD_ID + ":block/" + registeredBlock.get().getRegistryName().getPath());
    }

    // private <T extends Block> void itemFromBlock(RegistryObject<T>
    // registeredBlock, String suffix) {
    // makeItemFromParent(registeredBlock, WorkspaceDead.MOD_ID + ":block/" +
    // registeredBlock.get().getRegistryName().getPath() + suffix);
    // }

    // Makes a model entry from the given parent
    private <T extends Block> void makeItemFromParent(RegistryObject<T> registeredBlock, String parent) {
        this.itemModels().getBuilder(registeredBlock.get().getRegistryName().getPath())
                .parent(new ModelFile.UncheckedModelFile(parent));
    }

    // This makes a simple ITEM/MODEL FROM A BLOCK
    private void itemBlock(RegistryObject<Block> registeredBlock) {
        itemBlock(registeredBlock.get());
    }

    // This makes BLOCK MODEL AND ITEM for a BLOCK
    private void itemBlock(Block block) {
        itemModels().getBuilder(block.getRegistryName().getPath()).parent(modelFile(block));
        // simpleBlockItem(block, cubeAll(block));
    }

    private void cubeColumn(Block block, ResourceLocation side, ResourceLocation end) {
        simpleBlock(block, models().cubeColumn(block.getRegistryName().getPath(), side, end));
    }

    private ModelFile modelFile(Block block) {
        return new ModelFile.UncheckedModelFile(WorkspaceDead.MOD_ID + ":block/" + block.getRegistryName().getPath());
    }

    // This makes FLAT/GENERATED ITEM MODEL for a BLOCK
    private <T extends Block> void itemGenerated(RegistryObject<T> registeredBlock) {
        this.itemModels().getBuilder(registeredBlock.get().getRegistryName().getPath())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))//
                .texture("layer0", new ResourceLocation(registeredBlock.getId().getNamespace(),
                        "item/" + registeredBlock.getId().getPath()));
    }

    // This makes BLOCK, BLOCKSTATE, and ITEM(BLOCK)
    private void makeSimpleBlockAndItem(@NotNull Block block) {
        simpleBlock(block);
        itemBlock(block);
    }

    private <T extends Block> void fenceBlock(RegistryObject<T> gateBlockObj, RegistryObject<Block> textureBlockObj) {
        if (gateBlockObj.get() instanceof FenceBlock gateBlock) {
            fenceBlock(gateBlock, modLoc("block/%s".formatted(textureBlockObj.getId().getPath())));
        } else {
            throw new IllegalArgumentException("%s is not an instance of FenceBlock".formatted(gateBlockObj.get()));
        }
    }

    private <T extends Block> void fenceGateBlock(RegistryObject<T> gateBlockObj,
            RegistryObject<Block> textureBlockObj) {
        if (gateBlockObj.get() instanceof FenceGateBlock gateBlock) {
            fenceGateBlock(gateBlock, modLoc("block/%s".formatted(textureBlockObj.getId().getPath())));
        } else {
            throw new IllegalArgumentException("%s is not an instance of FenceGateBlock".formatted(gateBlockObj.get()));
        }
    }

    private <T extends Block> void stairsBlock(RegistryObject<T> stairBlockObj, RegistryObject<Block> textureBlockObj) {
        if (stairBlockObj.get() instanceof StairBlock stairBlock) {
            stairsBlock(stairBlock, modLoc("block/%s".formatted(textureBlockObj.getId().getPath())));
        } else {
            throw new IllegalArgumentException("%s is not an instance of StairBlock".formatted(stairBlockObj.get()));
        }
    }

    private <T extends Block> void slabBlock(RegistryObject<T> slabObj, RegistryObject<Block> fullBlockObj) {
        if (slabObj.get() instanceof SlabBlock slabBlock) {
            slabBlock(slabBlock, models().getExistingFile(fullBlockObj.getId()).getLocation(),
                    modLoc("block/%s".formatted(fullBlockObj.getId().getPath())));
        } else {
            throw new IllegalArgumentException("%s is not an instance of SlabBlock".formatted(slabObj.get()));
        }
    }
}