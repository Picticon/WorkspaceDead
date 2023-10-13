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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.ForgeRegistries;
import workspacedead.WorkspaceDead;

public class PurificationRecipe extends BaseRecipe {
    public static final String STATICID = "purification";
    private final ResourceLocation id;
    protected final Block inputBlock;
    protected final Block outputBlock;
    protected final String inputEntityName;
    protected final String outputEntityName;
    private int power;
    public @Nullable EntityType<?> inputEntity;
    public @Nullable EntityType<?> outputEntity;

    public PurificationRecipe(ResourceLocation name, Block output, Block input, int power) {
        super(name);
        this.inputBlock = input;
        this.outputBlock = output;
        this.id = name;
        if (power < 0)
            power = 0;
        if (power > 2)
            power = 2;
        this.power = power;
        this.inputEntityName = null;
        this.outputEntityName = null;
    }

    public PurificationRecipe(ResourceLocation name, String outentity, String inentity, int power2) {
        super(name);
        this.id = name;
        this.inputBlock = null;
        this.outputBlock = null;
        this.inputEntityName = inentity;
        this.outputEntityName = outentity;
        this.power = power2;
    }

    @Override
    public ItemStack getResultItem() {
        return ItemStack.EMPTY;
    }

    public Block getInputBlock() {
        return this.inputBlock;
    };

    public Block getOutputBlock() {
        return this.outputBlock;
    };

    public boolean isBlockMode() {
        return this.inputBlock != null;
    }

    public boolean isEntityMode() {
        return this.inputEntityName != null;
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

    public static class Type implements RecipeType<PurificationRecipe> {
        private Type() {
        }

        public static final Type INSTANCE = new Type();
        public static final String ID = STATICID;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return false;
    }

    public static class Serializer implements RecipeSerializer<PurificationRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(WorkspaceDead.MOD_ID, STATICID);

        @Override
        public PurificationRecipe fromJson(ResourceLocation id, JsonObject json) {
            var input = GsonHelper.getAsString(json, "input", null);
            var output = GsonHelper.getAsString(json, "output", null);
            var power = GsonHelper.getAsInt(json, "power", 0);
            var inblock = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(input));
            var outblock = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(output));
            if (inblock != null && outblock != null && inblock != Blocks.AIR && outblock != Blocks.AIR) {

                return new PurificationRecipe(id, outblock, inblock, power);
            }
            var inentity = ForgeRegistries.ENTITIES.getValue(new ResourceLocation(input));
            var outentity = ForgeRegistries.ENTITIES.getValue(new ResourceLocation(output));
            if (inentity != null && outentity != null) {
                return new PurificationRecipe(id, output, input, power);
            }
            return null;
        }

        @Override
        public PurificationRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {

            var output = buf.readUtf();
            var input = buf.readUtf();
            var inblock = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(input));
            var outblock = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(output));
            var inent = buf.readUtf();
            var outent = buf.readUtf();
            var power = buf.readInt();
            if (inblock != null && outblock != null)
                return new PurificationRecipe(id, outblock, inblock, power);
            return new PurificationRecipe(id, outent, inent, power);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, PurificationRecipe recipe) {
            buf.writeUtf(recipe.inputBlock == null ? null : recipe.inputBlock.getRegistryName().toString());
            buf.writeUtf(recipe.outputBlock == null ? null : recipe.outputBlock.getRegistryName().toString());
            buf.writeUtf(recipe.inputEntityName);
            buf.writeUtf(recipe.outputEntityName);
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

    public int getPower() {
        return this.power;
    }

    public @Nullable EntityType<?> getInputEntity() {
        if (this.inputEntity == null)
            this.inputEntity = ForgeRegistries.ENTITIES.getValue(new ResourceLocation(inputEntityName));
        return this.inputEntity;
    }

    public @Nullable EntityType<?> getOutputEntity() {
        if (this.outputEntity == null)
            this.outputEntity = ForgeRegistries.ENTITIES.getValue(new ResourceLocation(outputEntityName));
        return this.outputEntity;
    }

    public Entity makeInputEntity(Level level, BlockPos pos) {
        // passed position is zero on the client, so we don't want to do initialization
        // stuff for the entity
        if (pos == BlockPos.ZERO)
            return this.getInputEntity().create(level);
        return this.getInputEntity().create((ServerLevel) level, null, null, null, pos, MobSpawnType.SPAWNER, false, false);
    }

    public Entity makeOutputEntity(Level level, BlockPos pos) {
        // passed position is zero on the client, so we don't want to do initialization
        // stuff for the entity
        if (pos == BlockPos.ZERO)
            return this.getOutputEntity().create(level);
        return this.getOutputEntity().create((ServerLevel) level, null, null, null, pos, MobSpawnType.SPAWNER, false, false);
    }
}
