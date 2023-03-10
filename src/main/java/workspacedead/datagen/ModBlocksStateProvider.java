package workspacedead.datagen;

import org.jetbrains.annotations.NotNull;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
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
import workspacedead.block.ModBlocks;

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
        simpleBlock(ModBlocks.BIOMASS_BLOCK.get(), new ModelFile.UncheckedModelFile(new ResourceLocation(WorkspaceDead.MOD_ID, "block/biomass_block")));
        itemBlock(ModBlocks.BIOMASS_BLOCK.get());

        doorBlock((DoorBlock) ModBlocks.DEADDOOR.get(), new ResourceLocation(WorkspaceDead.MOD_ID, "block/deaddoor_bottom"), //
                new ResourceLocation(WorkspaceDead.MOD_ID, "block/deaddoor_top"));
        itemGenerated(ModBlocks.DEADDOOR);

        doorBlock((DoorBlock) ModBlocks.BURNTDOOR.get(), new ResourceLocation(WorkspaceDead.MOD_ID, "block/burntdoor_bottom"), //
                new ResourceLocation(WorkspaceDead.MOD_ID, "block/burntdoor_top"));
        itemGenerated(ModBlocks.BURNTDOOR);

        trapdoorBlock((TrapDoorBlock) ModBlocks.DEADTRAPDOOR.get(), blockTexture(ModBlocks.DEADTRAPDOOR.get()), true);
        makeItemFromParent(ModBlocks.DEADTRAPDOOR, WorkspaceDead.MOD_ID + ":block/deadtrapdoor_bottom");

        trapdoorBlock((TrapDoorBlock) ModBlocks.BURNTTRAPDOOR.get(), blockTexture(ModBlocks.BURNTTRAPDOOR.get()), true);
        makeItemFromParent(ModBlocks.BURNTTRAPDOOR, WorkspaceDead.MOD_ID + ":block/burnttrapdoor_bottom");

        itemBlock(ModBlocks.DEADLOG);
        logBlock((RotatedPillarBlock) ModBlocks.DEADLOG.get());

        itemBlock(ModBlocks.DEADWOOD);
        axisBlock((RotatedPillarBlock) ModBlocks.DEADWOOD.get(), blockTexture(ModBlocks.DEADLOG.get()), blockTexture(ModBlocks.DEADLOG.get()));

        itemBlock(ModBlocks.DEADLOG_STRIPPED);
        axisBlock((RotatedPillarBlock) ModBlocks.DEADLOG_STRIPPED.get(), new ResourceLocation(WorkspaceDead.MOD_ID, "block/deadlog_stripped"), //
                new ResourceLocation(WorkspaceDead.MOD_ID, "block/deadlog_top_stripped"));

        axisBlock((RotatedPillarBlock) ModBlocks.DEADWOOD_STRIPPED.get(), new ResourceLocation(WorkspaceDead.MOD_ID, "block/deadwood_stripped"), //
                new ResourceLocation(WorkspaceDead.MOD_ID, "block/deadwood_top_stripped"));
        itemBlock(ModBlocks.DEADWOOD_STRIPPED);

        makeSimpleBlockAndItem(ModBlocks.DEADPLANKS.get());
        makeSimpleBlockAndItem(ModBlocks.BURNTPLANKS.get());
        makeSimpleBlockAndItem(ModBlocks.DEADLEAVES.get());
        makeSimpleBlockAndItem(ModBlocks.DEADDIRT.get());
        makeSimpleBlockAndItem(ModBlocks.DEADSAND.get());
        makeSimpleBlockAndItem(ModBlocks.DEADGRAVEL.get());
        makeSimpleBlockAndItem(ModBlocks.DEADSTONE.get());
        makeSimpleBlockAndItem(ModBlocks.DEADCLAY.get());
        makeSimpleBlockAndItem(ModBlocks.DEADSLATE.get());
        makeSimpleBlockAndItem(ModBlocks.POOPBLOCK.get());
        makeSimpleBlockAndItem(ModBlocks.POOPBLOCK2X.get());
        // makeSimpleBlockAndItem(ModBlocks.CARVED_POOPBLOCK.get());
        // makeSimpleBlockAndItem(ModBlocks.POOP_O_LANTERN.get());

        makeFacingBlock(ModBlocks.CARVED_POOPBLOCK);
        itemFromBlock(ModBlocks.CARVED_POOPBLOCK);
        makeFacingBlock(ModBlocks.POOP_O_LANTERN);
        itemFromBlock(ModBlocks.POOP_O_LANTERN);

        // inv item is simpleitem
        simpleBlock(ModBlocks.DEADSAPLING.get(), models().cross(ModBlocks.DEADSAPLING.get().getRegistryName().getPath(), blockTexture(ModBlocks.DEADSAPLING.get())));
        itemGenerated(ModBlocks.DEADSAPLING);

        slabBlock(ModBlocks.DEADSLAB, ModBlocks.DEADPLANKS);
        itemFromBlock(ModBlocks.DEADSLAB);
        slabBlock(ModBlocks.BURNTSLAB, ModBlocks.BURNTPLANKS);
        itemFromBlock(ModBlocks.BURNTSLAB);
        fenceBlock(ModBlocks.DEADFENCE, ModBlocks.DEADPLANKS);
        parentBlockWithTexture(ModBlocks.DEADFENCE, "block/fence_inventory", "texture", ModBlocks.DEADPLANKS);
        fenceBlock(ModBlocks.BURNTFENCE, ModBlocks.BURNTPLANKS);
        parentBlockWithTexture(ModBlocks.BURNTFENCE, "block/fence_inventory", "texture", ModBlocks.BURNTPLANKS);
        fenceGateBlock(ModBlocks.DEADFENCE_GATE, ModBlocks.DEADPLANKS);
        itemFromBlock(ModBlocks.DEADFENCE_GATE);
        fenceGateBlock(ModBlocks.BURNTFENCE_GATE, ModBlocks.BURNTPLANKS);
        itemFromBlock(ModBlocks.BURNTFENCE_GATE);
        stairsBlock(ModBlocks.DEADSTAIRS, ModBlocks.DEADPLANKS);
        itemFromBlock(ModBlocks.DEADSTAIRS);
        stairsBlock(ModBlocks.BURNTSTAIRS, ModBlocks.BURNTPLANKS);
        itemFromBlock(ModBlocks.BURNTSTAIRS);
    }

    private <T extends Block> void makeFacingBlock(RegistryObject<Block> regBlock) {
        // // EXAMPLE_BLOCK_2: Has Property BlockStateProperties#HORIZONTAL_FACING
        // this.getVariantBuilder(regBlock.get()) // Get variant builder
        //         .forAllStates(state -> // For all possible states
        //         ConfiguredModel.builder() // Creates configured model builder
        //                 .modelFile(modelFile(regBlock.get())) // Can show 'modelFile'
        //                 .rotationY((int) state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot()) // Rotates 'modelFile' on the Y axis depending on the property
        //                 .build() // Creates the array of configured models
        //         );
        this.horizontalBlock(regBlock.get(), //
                new ResourceLocation(regBlock.getId().getNamespace(), "block/" + regBlock.getId().getPath()+"_side"), //
                new ResourceLocation(regBlock.getId().getNamespace(), "block/" + regBlock.getId().getPath() + "_front" ), //
                new ResourceLocation(regBlock.getId().getNamespace(), "block/" + regBlock.getId().getPath()+"_top") );
    }

    private <T extends Block, U extends Block> void parentBlockWithTexture(RegistryObject<T> registeredBlock, String parent, String layername, RegistryObject<U> textureblock) {
        this.itemModels().getBuilder(registeredBlock.get().getRegistryName().getPath()).parent(new ModelFile.UncheckedModelFile(parent)) //
                .texture(layername, new ResourceLocation(textureblock.getId().getNamespace(), "block/" + textureblock.getId().getPath()));
    }

    /// Creates an item model using a block model
    private <T extends Block> void itemFromBlock(RegistryObject<T> registeredBlock) {
        makeItemFromParent(registeredBlock, WorkspaceDead.MOD_ID + ":block/" + registeredBlock.get().getRegistryName().getPath());
    }

    // private <T extends Block> void itemFromBlock(RegistryObject<T>
    // registeredBlock, String suffix) {
    // makeItemFromParent(registeredBlock, WorkspaceDead.MOD_ID + ":block/" +
    // registeredBlock.get().getRegistryName().getPath() + suffix);
    // }

    // Makes a model entry from the given parent
    private <T extends Block> void makeItemFromParent(RegistryObject<T> registeredBlock, String parent) {
        this.itemModels().getBuilder(registeredBlock.get().getRegistryName().getPath()).parent(new ModelFile.UncheckedModelFile(parent));
    }

    // This makes a simple BLOCK MODEL AND ITEM for a BLOCK
    private void itemBlock(RegistryObject<Block> registeredBlock) {
        itemBlock(registeredBlock.get());
    }

    // This makes BLOCK MODEL AND ITEM for a BLOCK
    private void itemBlock(Block block) {
        itemModels().getBuilder(block.getRegistryName().getPath()).parent(modelFile(block));
        // simpleBlockItem(block, cubeAll(block));
    }

    private ModelFile modelFile(Block block) {
        return new ModelFile.UncheckedModelFile(WorkspaceDead.MOD_ID + ":block/" + block.getRegistryName().getPath());
    }

    // This makes FLAT/GENERATED ITEM MODEL for a BLOCK
    private <T extends Block> void itemGenerated(RegistryObject<T> registeredBlock) {
        this.itemModels().getBuilder(registeredBlock.get().getRegistryName().getPath()).parent(new ModelFile.UncheckedModelFile("item/generated"))//
                .texture("layer0", new ResourceLocation(registeredBlock.getId().getNamespace(), "item/" + registeredBlock.getId().getPath()));
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

    private <T extends Block> void fenceGateBlock(RegistryObject<T> gateBlockObj, RegistryObject<Block> textureBlockObj) {
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
            slabBlock(slabBlock, models().getExistingFile(fullBlockObj.getId()).getLocation(), modLoc("block/%s".formatted(fullBlockObj.getId().getPath())));
        } else {
            throw new IllegalArgumentException("%s is not an instance of SlabBlock".formatted(slabObj.get()));
        }
    }
}