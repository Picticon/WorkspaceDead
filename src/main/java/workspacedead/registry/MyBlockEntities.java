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

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
