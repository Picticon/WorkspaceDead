package workspacedead.registry;

import net.minecraft.core.Registry;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import workspacedead.WorkspaceDead;
import workspacedead.recipe.PurificationRecipe;
import workspacedead.recipe.CropMutationRecipe;
import workspacedead.recipe.DeadInsideEffectRecipe;
import workspacedead.recipe.FullMetalAlchemiserRecipe;
import workspacedead.recipe.SaturatorRecipe;

public class MyRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister
            .create(ForgeRegistries.RECIPE_SERIALIZERS, WorkspaceDead.MOD_ID);

    public static final RegistryObject<RecipeSerializer<SaturatorRecipe>> SATURATOR_SERIALIZER = SERIALIZERS
            .register(SaturatorRecipe.STATICID, () -> SaturatorRecipe.Serializer.INSTANCE);
    public static final RecipeType<SaturatorRecipe> SATURATOR_RECIPES = new RecipeType<>();

    public static final RegistryObject<RecipeSerializer<FullMetalAlchemiserRecipe>> FULLMETALALCHEMISER_SERIALIZER = SERIALIZERS
            .register(FullMetalAlchemiserRecipe.STATICID, () -> FullMetalAlchemiserRecipe.Serializer.INSTANCE);
    public static final RecipeType<FullMetalAlchemiserRecipe> FULLMETALALCHEMISER_RECIPES = new RecipeType<>();

    public static final RegistryObject<RecipeSerializer<PurificationRecipe>> PURIFICATION_SERIALIZER = SERIALIZERS
            .register(PurificationRecipe.STATICID, () -> PurificationRecipe.Serializer.INSTANCE);
    public static final RecipeType<PurificationRecipe> PURIFICATION_RECIPES = new RecipeType<>();

    public static final RegistryObject<RecipeSerializer<DeadInsideEffectRecipe>> DEADINSIDEEFFECT_SERIALIZER = SERIALIZERS
            .register(DeadInsideEffectRecipe.STATICID, () -> DeadInsideEffectRecipe.Serializer.INSTANCE);
    public static final RecipeType<DeadInsideEffectRecipe> DEADINSIDEEFFECT_RECIPES = new RecipeType<>();

    public static final RegistryObject<RecipeSerializer<CropMutationRecipe>> CROPMUTATION_SERIALIZER = SERIALIZERS
            .register(CropMutationRecipe.STATICID, () -> CropMutationRecipe.Serializer.INSTANCE);
    public static final RecipeType<CropMutationRecipe> CROPMUTATION_RECIPES = new RecipeType<>();

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }

    private static class RecipeType<T extends Recipe<?>> implements net.minecraft.world.item.crafting.RecipeType<T> {

        @Override
        public String toString() {
            return Registry.RECIPE_TYPE.getKey(this).toString();
        }
    }

}