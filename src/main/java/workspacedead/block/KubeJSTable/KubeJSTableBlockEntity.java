package workspacedead.block.KubeJSTable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import workspacedead.network.IHandleClientInt;
import workspacedead.network.IHandleRecipe;
import workspacedead.registry.MyBlockEntities;

public class KubeJSTableBlockEntity extends BlockEntity implements IHandleRecipe, IHandleClientInt {
    protected final ItemStackHandler itemHandler = createHandler();
    protected final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);
    private ResourceLocation _original;
    private boolean _removeCheck;
    public static final int CLEAR_ORIGINAL_RECIPE = 99;
    public static final int REMOVE_ORIGINAL_VALUE_CHANGE = 98;
    public static final int ADJUST_OUTPUT_QUANTITY = 97;

    public KubeJSTableBlockEntity(BlockPos pPos, BlockState pState) {
        super(MyBlockEntities.KUBEJS_TABLE_BLOCK_ENTITY.get(), pPos, pState);
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
        handler.invalidate();
    }

    public void setOriginalRecipe(ResourceLocation recipeId) {
        _original = recipeId;
        this.updateBlock();
    }

    private void setRemoveCheckbox(boolean b) {
        _removeCheck = b;
        this.updateBlock();
    }

    // notify the client this block changed.
    public boolean updateBlock() {
        if (level != null) {
            BlockState state = level.getBlockState(worldPosition);
            level.sendBlockUpdated(worldPosition, state, state, 3);
            setChanged();
            return true;
        }
        return false;
    }

    public ResourceLocation getOriginalRecipe() {
        return _original;
    }

    public boolean getRemoveCheckbox() {
        return _removeCheck;
    }

    @Override
    public void load(CompoundTag tag) {
        if (tag.contains("Inventory")) {
            itemHandler.deserializeNBT(tag.getCompound("Inventory"));
        }
        if (tag.contains("recipe")) {
            var r = tag.getString("recipe");
            if (r == null || r == "")
                _original = null;
            else
                _original = new ResourceLocation(r);
        }
        if (tag.contains("removecheck")) {
            var r = tag.getBoolean("removecheck");
            _removeCheck = r;
        } else
            _removeCheck = false;
        super.load(tag);
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        tag.put("Inventory", itemHandler.serializeNBT());
        if (_original == null)
            tag.putString("recipe", "");
        else
            tag.putString("recipe", _original.toString());
        tag.putBoolean("removecheck", _removeCheck);
    }

    // required to update client with state
    @Override
    @Nullable
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    // required to update client with state
    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = new CompoundTag();
        this.saveAdditional(tag);
        return tag;
    }

    // I think this is when client receives packet
    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        super.onDataPacket(net, pkt);
        handleUpdateTag(pkt.getTag() == null ? new CompoundTag() : pkt.getTag()); // is this required?
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(11) {

            @Override
            protected void onContentsChanged(int slot) {
                // To make sure the TE persists when the chunk is saved later we need to
                // mark it dirty every time the item handler changes
                setChanged();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return true; // accept anything
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void handleRecipe(ResourceLocation recipeId) {
        if (this.level.isClientSide)
            return;
        setOriginalRecipe(recipeId);
        //Chatter.sendToAllPlayers(recipeId.toString());
        var rm = this.level.getRecipeManager();
        var recipeO = rm.byKey(recipeId);
        if (recipeO.isPresent() && recipeO.get() instanceof CraftingRecipe) {
            var recipe = (CraftingRecipe) recipeO.get();
            this.itemHandler.extractItem(9, 64, false);
            if (recipe.getResultItem().getItem() != Items.AIR)
                this.itemHandler.insertItem(9, recipe.getResultItem().copy(), false);
            for (var i = 0; i < 9; i++) {
                this.itemHandler.extractItem(i, 64, false);
            }
            if (recipe instanceof ShapedRecipe) {
                var shaped = (ShapedRecipe) recipe;
                var w = shaped.getWidth();
                var h = shaped.getHeight();
                for (var y = 0; y < h; y++) {
                    for (var x = 0; x < w; x++) {
                        var ing = recipe.getIngredients().get(y * w + x);
                        var items = ing.getItems();
                        if (items.length == 0)
                            continue;
                        var item = ing.getItems()[0].copy();
                        if (item.getItem() != Items.AIR)
                            this.itemHandler.insertItem(y * 3 + x, item, false);
                    }
                }
            }
            if (recipe instanceof ShapelessRecipe) {
                //var shapeless = (ShapelessRecipe) recipe;
                for (var i = 0; i < recipe.getIngredients().size(); i++) {
                    Ingredient ing = recipe.getIngredients().get(i);
                    var itemstack = ing.getItems()[0];
                    if (itemstack.getItem() != Items.AIR)
                        this.itemHandler.insertItem(i, itemstack.copy(), false);
                    //this.itemHandler.setStackInSlot(i, recipe.getIngredients().get(i));
                }
            }
        }
    }

    @Override
    public void handleClientInt(int controlId, int value) {
        if (controlId == CLEAR_ORIGINAL_RECIPE)
            setOriginalRecipe(null);
        if (controlId == REMOVE_ORIGINAL_VALUE_CHANGE)
            setRemoveCheckbox(value == 1 ? true : false);
        if (controlId == ADJUST_OUTPUT_QUANTITY) {
            itemHandler.setSize(Math.max(Math.min(itemHandler.getStackInSlot(9).getCount() + value, 64), 1));
        }

    }

}