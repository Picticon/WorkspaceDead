package workspacedead.recipe;

import org.jetbrains.annotations.Nullable;

import com.google.gson.JsonObject;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import workspacedead.WorkspaceDead;

public class DeadInsideEffectRecipe extends BaseRecipe {

    public static final String STATICID = "deadinsideeffect";
    private final ResourceLocation id;

    protected final String inputEntityName;
    public @Nullable EntityType<?> inputEntity;
    private final ItemStack output; // the result of the craft

    public DeadInsideEffectRecipe(ResourceLocation name, ItemStack output, String inentity) {
        super(name);
        this.output = output;
        this.inputEntityName = inentity;
        this.id = name;
    }

    @Override
    public ItemStack getResultItem() {
        return output.copy();
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        var ret = NonNullList.withSize(0, Ingredient.EMPTY);
        return ret;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<DeadInsideEffectRecipe> {
        private Type() {
        }

        public static final Type INSTANCE = new Type();
        public static final String ID = STATICID;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return false;
    }

    public @Nullable EntityType<?> getInputEntity()
    {
        if (this.inputEntity == null)
            this.inputEntity = ForgeRegistries.ENTITIES.getValue(new ResourceLocation(inputEntityName));
            return inputEntity;
    }

    public Entity makeInputEntity(Level level, BlockPos pos) {
        // passed position is zero on the client, so we don't want to do initialization
        // stuff for the entity
        if (pos == BlockPos.ZERO)
            return this.getInputEntity().create(level);
        return this.getInputEntity().create((ServerLevel) level, null, null, null, pos, MobSpawnType.SPAWNER, false, false);
    }


    public static class Serializer implements RecipeSerializer<DeadInsideEffectRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(WorkspaceDead.MOD_ID, STATICID);

        @Override
        public DeadInsideEffectRecipe fromJson(ResourceLocation id, JsonObject json) {
            var output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "output"));
            var input = GsonHelper.getAsString(json, "input", null);
            // check?
            // var inentity = ForgeRegistries.ENTITIES.getValue(new ResourceLocation(input));
            return new DeadInsideEffectRecipe(id, output, input);
        }

        @Override
        public DeadInsideEffectRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {

            var output = buf.readItem();
            var inputEntityName = buf.readUtf();
            return new DeadInsideEffectRecipe(id, output, inputEntityName);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, DeadInsideEffectRecipe recipe) {
            buf.writeItem(recipe.output);
            buf.writeUtf(recipe.inputEntityName);
        }

        @SuppressWarnings("unchecked") // Need this wrapper, because generics
        private static <G> Class<G> castClass(Class<?> cls) {
            return (Class<G>) cls;
        }

        private ResourceLocation name;

        @Override
        public RecipeSerializer<?> setRegistryName(ResourceLocation name) {
            this.name = name;
            return this;
        }

        @Override
        public ResourceLocation getRegistryName() {
            return name;
        }

        @Override
        public Class<RecipeSerializer<?>> getRegistryType() {
            return Serializer.<RecipeSerializer<?>>castClass(RecipeSerializer.class);
        }
    }
}
