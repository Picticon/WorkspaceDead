package workspacedead.fluid;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import workspacedead.WorkspaceDead;
import workspacedead.block.ModBlocks;
import workspacedead.item.ModItems;

public class ModFluids {

    public static final ResourceLocation WATER_STILL_RL = new ResourceLocation("block/water_still");
    public static final ResourceLocation WATER_FLOWING_RL = new ResourceLocation("block/water_flow");
    public static final ResourceLocation WATER_OVERLAY_RL = new ResourceLocation("block/water_overlay");

    public static final DeferredRegister<Fluid> MOD_FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS,
            WorkspaceDead.MOD_ID);

    public static final RegistryObject<FlowingFluid> DEADWATER_FLUID = MOD_FLUIDS.register("deadwater_fluid",
            () -> new ForgeFlowingFluid.Source(ModFluids.DEADWATER_PROPERTIES));

    public static final RegistryObject<FlowingFluid> DEADWATER_FLOWING = MOD_FLUIDS.register("deadwater_flowing",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.DEADWATER_PROPERTIES));

    public static final ForgeFlowingFluid.Properties DEADWATER_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> DEADWATER_FLUID.get(), () -> DEADWATER_FLOWING.get(),
            FluidAttributes.builder(WATER_STILL_RL, WATER_FLOWING_RL)
                    .density(15).luminosity(2).viscosity(5).sound(SoundEvents.WATER_AMBIENT).overlay(WATER_OVERLAY_RL)
                    .color(0xC3333333))
            .slopeFindDistance(2).levelDecreasePerBlock(2)
            .block(() -> ModFluids.DEADWATER_BLOCK.get()).bucket(() -> ModItems.DEADWATER_BUCKET.get());

    public static final RegistryObject<LiquidBlock> DEADWATER_BLOCK = ModBlocks.MOD_BLOCKS.register("deadwater",
            () -> new LiquidBlock(() -> ModFluids.DEADWATER_FLUID.get(), BlockBehaviour.Properties.of(Material.WATER)
                    .noCollission().strength(100f).noDrops()));

    public static void register(IEventBus eventBus) {
        MOD_FLUIDS.register(eventBus);
    }

}
