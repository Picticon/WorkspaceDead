package workspacedead.registry;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import workspacedead.WorkspaceDead;
import workspacedead.block.BioMass.BioMassBlockEntity;
import workspacedead.block.FullMetalAlchemiser.FullMetalAlchemiserBlockEntity;
import workspacedead.block.MrHanky.MrHankyBlockEntity;
import workspacedead.block.Saturator.DesaturatorBlockEntity;
import workspacedead.block.Saturator.SaturatorBlockEntity;
import workspacedead.block.SpawnEggPlant.SpawnEggBlockEntity;
import workspacedead.block.generators.BlackGeneratorBlockEntity;
import workspacedead.block.generators.BlueGeneratorBlockEntity;
import workspacedead.block.generators.BrownGeneratorBlockEntity;
import workspacedead.block.generators.CyanGeneratorBlockEntity;
import workspacedead.block.generators.GrayGeneratorBlockEntity;
import workspacedead.block.generators.GreenGeneratorBlockEntity;
import workspacedead.block.generators.LightBlueGeneratorBlockEntity;
import workspacedead.block.generators.LightGrayGeneratorBlockEntity;
import workspacedead.block.generators.LimeGeneratorBlockEntity;
import workspacedead.block.generators.MagentaGeneratorBlockEntity;
import workspacedead.block.generators.OrangeGeneratorBlockEntity;
import workspacedead.block.generators.PinkGeneratorBlockEntity;
import workspacedead.block.generators.PurpleGeneratorBlockEntity;
import workspacedead.block.generators.RedGeneratorBlockEntity;
import workspacedead.block.generators.WhiteGeneratorBlockEntity;
import workspacedead.block.generators.YellowGeneratorBlockEntity;

public class MyBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister
            .create(ForgeRegistries.BLOCK_ENTITIES, WorkspaceDead.MOD_ID);

    public static final RegistryObject<BlockEntityType<FullMetalAlchemiserBlockEntity>> FULLMETALALCHEMISER_BLOCK_ENTITY = BLOCK_ENTITIES
            .register("fullmetalalchemiser_block_entity", () -> BlockEntityType.Builder
                    .of(FullMetalAlchemiserBlockEntity::new, MyBlocks.FULLMETALALCHEMISER_BLOCK.get()).build(null));

    public static final RegistryObject<BlockEntityType<SaturatorBlockEntity>> SATURATOR_BLOCK_ENTITY = BLOCK_ENTITIES
            .register("saturator_block_entity", () -> BlockEntityType.Builder
                    .of(SaturatorBlockEntity::new, MyBlocks.SATURATOR_BLOCK.get()).build(null));

    public static final RegistryObject<BlockEntityType<DesaturatorBlockEntity>> DESATURATOR_BLOCK_ENTITY = BLOCK_ENTITIES
            .register("desaturator_block_entity", () -> BlockEntityType.Builder
                    .of(DesaturatorBlockEntity::new, MyBlocks.DESATURATOR_BLOCK.get()).build(null));

    public static final RegistryObject<BlockEntityType<BioMassBlockEntity>> BIOMASS_BLOCK_ENTITY = BLOCK_ENTITIES
            .register("biomass_block_entity", () -> BlockEntityType.Builder
                    .of(BioMassBlockEntity::new, MyBlocks.BIOMASS_BLOCK.get()).build(null));

    public static final RegistryObject<BlockEntityType<MrHankyBlockEntity>> MRHANKY_BLOCK_ENTITY = BLOCK_ENTITIES
            .register("mrhanky_block_entity", () -> BlockEntityType.Builder
                    .of(MrHankyBlockEntity::new, MyBlocks.MRHANKY_BLOCK.get()).build(null));

    public static final RegistryObject<BlockEntityType<SpawnEggBlockEntity>> SPAWNEGG_BLOCK_ENTITY = BLOCK_ENTITIES
            .register("spawnegg_block_entity", () -> BlockEntityType.Builder
                    .of(SpawnEggBlockEntity::new, MyBlocks.SPAWNEGG_PLANT.get()).build(null));

    public static final RegistryObject<BlockEntityType<YellowGeneratorBlockEntity>> YELLOW_GENERATOR_ENTITY = BLOCK_ENTITIES
            .register("yellow_generator_entity", () -> BlockEntityType.Builder
                    .of(YellowGeneratorBlockEntity::new, MyBlocks.YELLOW_GENERATOR.get()).build(null));

    public static final RegistryObject<BlockEntityType<BlackGeneratorBlockEntity>> BLACK_GENERATOR_ENTITY = BLOCK_ENTITIES
            .register("black_generator_entity", () -> BlockEntityType.Builder
                    .of(BlackGeneratorBlockEntity::new, MyBlocks.BLACK_GENERATOR.get()).build(null));

    public static final RegistryObject<BlockEntityType<BlueGeneratorBlockEntity>> BLUE_GENERATOR_ENTITY = BLOCK_ENTITIES
            .register("blue_generator_entity", () -> BlockEntityType.Builder
                    .of(BlueGeneratorBlockEntity::new, MyBlocks.BLUE_GENERATOR.get()).build(null));

    public static final RegistryObject<BlockEntityType<BrownGeneratorBlockEntity>> BROWN_GENERATOR_ENTITY = BLOCK_ENTITIES
            .register("brown_generator_entity", () -> BlockEntityType.Builder
                    .of(BrownGeneratorBlockEntity::new, MyBlocks.BROWN_GENERATOR.get()).build(null));

    public static final RegistryObject<BlockEntityType<CyanGeneratorBlockEntity>> CYAN_GENERATOR_ENTITY = BLOCK_ENTITIES
            .register("cyan_generator_entity", () -> BlockEntityType.Builder
                    .of(CyanGeneratorBlockEntity::new, MyBlocks.CYAN_GENERATOR.get()).build(null));

    public static final RegistryObject<BlockEntityType<GrayGeneratorBlockEntity>> GRAY_GENERATOR_ENTITY = BLOCK_ENTITIES
            .register("gray_generator_entity", () -> BlockEntityType.Builder
                    .of(GrayGeneratorBlockEntity::new, MyBlocks.GRAY_GENERATOR.get()).build(null));

    public static final RegistryObject<BlockEntityType<GreenGeneratorBlockEntity>> GREEN_GENERATOR_ENTITY = BLOCK_ENTITIES
            .register("green_generator_entity", () -> BlockEntityType.Builder
                    .of(GreenGeneratorBlockEntity::new, MyBlocks.GREEN_GENERATOR.get()).build(null));

    public static final RegistryObject<BlockEntityType<LightBlueGeneratorBlockEntity>> LIGHTBLUE_GENERATOR_ENTITY = BLOCK_ENTITIES
            .register("light_blue_generator_entity", () -> BlockEntityType.Builder
                    .of(LightBlueGeneratorBlockEntity::new, MyBlocks.LIGHTBLUE_GENERATOR.get()).build(null));

    public static final RegistryObject<BlockEntityType<LightGrayGeneratorBlockEntity>> LIGHTGRAY_GENERATOR_ENTITY = BLOCK_ENTITIES
            .register("light_gray_generator_entity", () -> BlockEntityType.Builder
                    .of(LightGrayGeneratorBlockEntity::new, MyBlocks.LIGHTGRAY_GENERATOR.get()).build(null));

    public static final RegistryObject<BlockEntityType<LimeGeneratorBlockEntity>> LIME_GENERATOR_ENTITY = BLOCK_ENTITIES
            .register("lime_generator_entity", () -> BlockEntityType.Builder
                    .of(LimeGeneratorBlockEntity::new, MyBlocks.LIME_GENERATOR.get()).build(null));

    public static final RegistryObject<BlockEntityType<MagentaGeneratorBlockEntity>> MAGENTA_GENERATOR_ENTITY = BLOCK_ENTITIES
            .register("magenta_generator_entity", () -> BlockEntityType.Builder
                    .of(MagentaGeneratorBlockEntity::new, MyBlocks.MAGENTA_GENERATOR.get()).build(null));

    public static final RegistryObject<BlockEntityType<OrangeGeneratorBlockEntity>> ORANGE_GENERATOR_ENTITY = BLOCK_ENTITIES
            .register("orange_generator_entity", () -> BlockEntityType.Builder
                    .of(OrangeGeneratorBlockEntity::new, MyBlocks.ORANGE_GENERATOR.get()).build(null));

    public static final RegistryObject<BlockEntityType<PinkGeneratorBlockEntity>> PINK_GENERATOR_ENTITY = BLOCK_ENTITIES
            .register("pink_generator_entity", () -> BlockEntityType.Builder
                    .of(PinkGeneratorBlockEntity::new, MyBlocks.PINK_GENERATOR.get()).build(null));

    public static final RegistryObject<BlockEntityType<PurpleGeneratorBlockEntity>> PURPLE_GENERATOR_ENTITY = BLOCK_ENTITIES
            .register("purple_generator_entity", () -> BlockEntityType.Builder
                    .of(PurpleGeneratorBlockEntity::new, MyBlocks.PURPLE_GENERATOR.get()).build(null));

    public static final RegistryObject<BlockEntityType<RedGeneratorBlockEntity>> RED_GENERATOR_ENTITY = BLOCK_ENTITIES
            .register("red_generator_entity", () -> BlockEntityType.Builder
                    .of(RedGeneratorBlockEntity::new, MyBlocks.RED_GENERATOR.get()).build(null));

    public static final RegistryObject<BlockEntityType<WhiteGeneratorBlockEntity>> WHITE_GENERATOR_ENTITY = BLOCK_ENTITIES
            .register("white_generator_entity", () -> BlockEntityType.Builder
                    .of(WhiteGeneratorBlockEntity::new, MyBlocks.WHITE_GENERATOR.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
