package workspacedead.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import workspacedead.WorkspaceDead;

public class CropMutationRecipe extends BaseRecipe {
    public static final String STATICID = "cropmutation";
    protected StateIngredient input1;
    protected StateIngredient input2;
    protected StateIngredient input3;
    protected StateIngredient input4;
    protected StateIngredient catalyst;
    protected StateIngredient output;
    protected float chance;

    public CropMutationRecipe(ResourceLocation id, StateIngredient output, StateIngredient input1,
            StateIngredient input2, StateIngredient input3, StateIngredient input4, StateIngredient catalyst,
            float chance) {
        super(id);
        this.output = output;
        this.input1 = input1;
        this.input2 = input2;
        this.input3 = input3; // not used
        this.input4 = input4; // not used
        this.catalyst = catalyst;
        this.chance = chance;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public boolean matches(RecipeWrapper inv, Level levelIn) {
        return false; // will never be crafted in a container/table
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<CropMutationRecipe> {
        private Type() {
        }

        public static final Type INSTANCE = new Type();
        public static final String ID = STATICID;
    }

    public static class Serializer implements RecipeSerializer<CropMutationRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(WorkspaceDead.MOD_ID, STATICID);

        @Override
        public CropMutationRecipe fromJson(ResourceLocation id, JsonObject json) {

            StateIngredient _output = null;
            if (json.has("output")) {
                JsonElement element = json.get("output");
                _output = StateIngredientHelper.deserialize(element.getAsJsonObject());
            }
            StateIngredient _input1 = null;
            StateIngredient _input2 = null;
            StateIngredient _input3 = null;
            StateIngredient _input4 = null;
            if (json.has("inputs")) {
                var inputs = GsonHelper.getAsJsonArray(json, "inputs");

                _input1 = inputs.size() > 0 ? StateIngredientHelper.deserialize(inputs.get(0).getAsJsonObject())
                        : null;
                _input2 = inputs.size() > 1 ? StateIngredientHelper.deserialize(inputs.get(1).getAsJsonObject())
                        : null;
                _input3 = inputs.size() > 2 ? StateIngredientHelper.deserialize(inputs.get(2).getAsJsonObject())
                        : null;
                _input4 = inputs.size() > 3 ? StateIngredientHelper.deserialize(inputs.get(3).getAsJsonObject())
                        : null;
            }
            StateIngredient _catalyst = null;
            if (json.has("catalyst")) {
                JsonElement element = json.get("catalyst");
                _catalyst = StateIngredientHelper.deserialize(element.getAsJsonObject());
            }
            var chance = GsonHelper.getAsFloat(json, "chance");
            if (chance <= 0)
                chance = 0.01f;
            if (chance > 1)
                chance = 1;
            var ret = new CropMutationRecipe(id, _output, _input1, _input2, _input3, _input4, _catalyst, chance);
            return ret;
        }

        @Override
        public CropMutationRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            var _output = StateIngredientHelper.read(buf);
            var _input1 = StateIngredientHelper.read(buf);
            var _input2 = StateIngredientHelper.read(buf);
            StateIngredient _catalyst = null;
            if (buf.readBoolean()) {
                _catalyst = StateIngredientHelper.read(buf);
            }
            var chance = buf.readFloat();
            var ret = new CropMutationRecipe(id, _output, _input1, _input2, null, null, _catalyst, chance);
            return ret;
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, CropMutationRecipe recipe) {
            recipe.getOutputBlock().write(buf);
            recipe.getInput1Block().write(buf);
            recipe.getInput2Block().write(buf);
            boolean hasCatalyst = recipe.getCatalyst() != null;
            buf.writeBoolean(hasCatalyst);
            if (hasCatalyst) {
                recipe.getCatalyst().write(buf);
            }
            buf.writeFloat(recipe.getChance());
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

    @Override
    public ItemStack getResultItem() {
        // don't call this :P
        return new ItemStack(output.first().getBlock().asItem(), 1).copy();
    }

    public float getChance() {
        return chance;
    }

    public StateIngredient getOutputBlock() {
        return output;
    }

    public StateIngredient getInput1Block() {
        return input1;
    }

    public StateIngredient getInput2Block() {
        return input2;
    }

    public StateIngredient getCatalyst() {
        return catalyst;
    }

}
