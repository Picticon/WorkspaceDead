package workspacedead.world;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import workspacedead.WorkspaceDead;

public class ModDimensions {
    public static final ResourceKey<Level> POTATO_DIM_KEY//
            = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(WorkspaceDead.MOD_ID, "potatodim"));
    public static final ResourceKey<DimensionType> POTATO_DIM_TYPE//
            = ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, POTATO_DIM_KEY.getRegistryName());

    public static void register() {
        System.out.println("Registering the dimensions for " + WorkspaceDead.MOD_ID);
    }

}
