package workspacedead.recipe;

import com.google.gson.JsonObject;

import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;
import workspacedead.WorkspaceDead;

public class FullMetalAlchemiserRecipe extends BaseRecipe {
    public static final String STATICID = "fullmetalalchemiser";
    private final ResourceLocation id;
    private final ItemStack output; // the result of the craft
    protected final Ingredient input; // the input item
    protected final int power; // how much power per item

    public FullMetalAlchemiserRecipe(ResourceLocation name, ItemStack output, Ingredient input, int power) {
        super(name);
        this.output = output;
        this.input = input;
        this.power = power;
        this.id = name;
    }

    @Override
    public ItemStack getResultItem() {
        return output.copy();
    }

    public Ingredient getInput() {
        return this.input;
    };

    public int getPower() {
        return this.power;
    };

    @Override
    public NonNullList<Ingredient> getIngredients() {
        var ret = NonNullList.withSize(1, Ingredient.EMPTY);
        ret.set(0, this.input);
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

    public static class Type implements RecipeType<FullMetalAlchemiserRecipe> {
        private Type() {
        }

        public static final Type INSTANCE = new Type();
        public static final String ID = STATICID;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return false;
    }

    public static class Serializer implements RecipeSerializer<FullMetalAlchemiserRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(WorkspaceDead.MOD_ID, "fullmetalalchemiser");

        @Override
        public FullMetalAlchemiserRecipe fromJson(ResourceLocation id, JsonObject json) {
            var output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "output"));
            var input = Ingredient.fromJson(json.getAsJsonObject("input"));
            var power = GsonHelper.getAsInt(json, "power");
            return new FullMetalAlchemiserRecipe(id, output, input, power);
        }

        @Override
        public FullMetalAlchemiserRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {

            var output = buf.readItem();
            var input = Ingredient.fromNetwork(buf);
            var power = buf.readInt();
            return new FullMetalAlchemiserRecipe(id, output, input, power);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, FullMetalAlchemiserRecipe recipe) {
            buf.writeItem(recipe.output);
            recipe.input.toNetwork(buf);
            buf.writeInt(recipe.power);
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
