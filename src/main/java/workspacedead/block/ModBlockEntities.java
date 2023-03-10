package workspacedead.block;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import workspacedead.WorkspaceDead;
import workspacedead.block.BioMass.BioMassBlockEntity;
//import workspacedead.block.animatedblock.AnimatedBlockEntity;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, WorkspaceDead.MOD_ID);

    public static final RegistryObject<BlockEntityType<BioMassBlockEntity>> BIOMASS_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("biomass_block_entity", () ->
                    BlockEntityType.Builder.of(BioMassBlockEntity::new,
                            ModBlocks.BIOMASS_BLOCK.get()).build(null));

//    public static final RegistryObject<BlockEntityType<AnimatedBlockEntity>> ANIMATED_BLOCK_ENTITY =
//            BLOCK_ENTITIES.register("animated_block_entity", () ->
//                    BlockEntityType.Builder.of(AnimatedBlockEntity::new,
//                            ModBlocks.ANIMATED_BLOCK.get()).build(null));



    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
