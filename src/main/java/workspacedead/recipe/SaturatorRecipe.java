package workspacedead.recipe;

import com.google.gson.JsonObject;
import com.mojang.logging.LogUtils;

import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import workspacedead.WorkspaceDead;

import java.util.ArrayList;
import java.util.List;

public class SaturatorRecipe extends BaseRecipe {
    private final ResourceLocation id;

    private final ItemStack output; // the result of the craft

    protected final Ingredient input; // the center item

    protected final Ingredient catalyst1; // the items in the desaturators
    protected final Ingredient catalyst2;
    protected final Ingredient catalyst3;
    protected final Ingredient catalyst4;

    protected final int color;

    protected final int power; // how much power per desaturator. total power is this * 4
    protected final int craftTicks; // how long it takes

    public SaturatorRecipe(ResourceLocation id, ItemStack output, Ingredient input, Ingredient catalyst1,
            Ingredient catalyst2, Ingredient catalyst3, Ingredient catalyst4, int inputPower, int craftTicks,
            int color) {
        super(id);
        this.id = id;
        this.output = output;
        this.input = input;
        this.catalyst1 = catalyst1;
        this.catalyst2 = catalyst2;
        this.catalyst3 = catalyst3;
        this.catalyst4 = catalyst4;
        this.power = inputPower;
        this.craftTicks = craftTicks;
        this.color = color;
    }

    public boolean matches(Level pLevel, ItemStack saturator, ItemStack desaturator1, ItemStack desaturator2,
            ItemStack desaturator3, ItemStack desaturator4) {
        if (pLevel.isClientSide()) {
            return false;
        }

        if (!this.input.test(saturator))
            return false;

        List<Ingredient> matches = new ArrayList<>();
        ItemStack[] stacks = { desaturator1, desaturator2, desaturator3, desaturator4 };
        boolean found1 = false, found2 = false, found3 = false, found4 = false;
        for (ItemStack s : stacks) {
            if (!found1 && this.catalyst1.test(s)) {
                matches.add(this.catalyst1);
                found1 = true;
            } else if (!found2 && this.catalyst2.test(s)) {
                matches.add(this.catalyst2);
                found2 = true;
            } else if (!found3 && this.catalyst3.test(s)) {
                matches.add(this.catalyst3);
                found3 = true;
            } else if (!found4 && this.catalyst4.test(s)) {
                matches.add(this.catalyst4);
                found4 = true;
            }
        }

        return matches.size() == 4;
    }

    public Ingredient getInput() {
        return this.input;
    };

    @Override
    public NonNullList<Ingredient> getIngredients() {
        var ret = NonNullList.withSize(5, Ingredient.EMPTY);
        ret.set(0, this.input);
        ret.set(1, this.catalyst1);
        ret.set(2, this.catalyst2);
        ret.set(3, this.catalyst3);
        ret.set(4, this.catalyst4);
        return ret;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return false;
    }

    @Override
    public ItemStack getResultItem() {
        return output.copy();
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

    public static class Type implements RecipeType<SaturatorRecipe> {
        private Type() {
        }

        public static final Type INSTANCE = new Type();
        public static final String ID = "saturator";
    }

    public static class Serializer implements RecipeSerializer<SaturatorRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(WorkspaceDead.MOD_ID, "saturator");

        @Override
        public SaturatorRecipe fromJson(ResourceLocation id, JsonObject json) {
            var output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "output"));
            var input = Ingredient.fromJson(json.getAsJsonObject("input"));
            var catalysts = GsonHelper.getAsJsonArray(json, "catalysts");
            var power = GsonHelper.getAsInt(json, "power");
            var time = GsonHelper.getAsInt(json, "time");
            Ingredient i0 = catalysts.size() >= 0 ? Ingredient.fromJson(catalysts.get(0)) : Ingredient.of(Blocks.DIRT);
            Ingredient i1 = catalysts.size() >= 1 ? Ingredient.fromJson(catalysts.get(1)) : Ingredient.of(Blocks.DIRT);
            Ingredient i2 = catalysts.size() >= 2 ? Ingredient.fromJson(catalysts.get(2)) : Ingredient.of(Blocks.DIRT);
            Ingredient i3 = catalysts.size() >= 3 ? Ingredient.fromJson(catalysts.get(3)) : Ingredient.of(Blocks.DIRT);
            if (catalysts.size() != 4 || power <= 0 || time <= 0) {
                LogUtils.getLogger().info("Invalid Saturator recipe {}", id.toString());
            }
            var color = GsonHelper.getAsInt(json, "color", -1);
            return new SaturatorRecipe(id, output, input, i0, i1, i2, i3, power, time, color);
        }

        // network packet structure:
        // output
        // input
        // c1, c2, c3, c4
        // power
        // time

        @Override
        public SaturatorRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {

            var output = buf.readItem();
            var input = Ingredient.fromNetwork(buf);
            var c1 = Ingredient.fromNetwork(buf);
            var c2 = Ingredient.fromNetwork(buf);
            var c3 = Ingredient.fromNetwork(buf);
            var c4 = Ingredient.fromNetwork(buf);
            var power = buf.readInt();
            var time = buf.readInt();
            var color = buf.readInt();
            return new SaturatorRecipe(id, output, input, c1, c2, c3, c4, power, time, color);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, SaturatorRecipe recipe) {
            buf.writeItem(recipe.output);
            recipe.input.toNetwork(buf);
            recipe.catalyst1.toNetwork(buf);
            recipe.catalyst2.toNetwork(buf);
            recipe.catalyst3.toNetwork(buf);
            recipe.catalyst4.toNetwork(buf);
            buf.writeInt(recipe.power);
            buf.writeInt(recipe.craftTicks);
            buf.writeInt(recipe.color);
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

    public int getPower() {
        return this.power;
    }

    public int getCraftingTicks() {
        return this.craftTicks;
    }

    public int getColor() {
        return this.color;
    }

}