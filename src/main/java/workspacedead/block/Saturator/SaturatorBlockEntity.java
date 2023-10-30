package workspacedead.block.Saturator;

import javax.annotation.Nonnull;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;
import workspacedead.client.IPowerHUD;
import workspacedead.gfx.ElectricityGFX;
import workspacedead.recipe.SaturatorRecipe;
import workspacedead.registry.MyBlockEntities;

public class SaturatorBlockEntity extends BlockEntity implements IPowerHUD {

    final static private int INPUTSLOT = 0;
    final static private int OUTPUTSLOT = 1;
    final static private int NUMBEROFSLOTS = 2;
    private int _tick;
    private boolean _crafting;
    private SaturatorRecipe _recipe;
    private int[] _powerremaining = new int[4];
    private int _ticksRemaining;
    private int _energyPerTick;
    private float _percentageDone;
    public ElectricityGFX[] bolts = null;

    public final SaturatorItemStackHandler inv;
    private final LazyOptional<SaturatorItemStackHandler> lazyInv;
    private int _color;
    private float[] _colors = new float[] { .5f, .5f, .5f, 1 };

    public SaturatorBlockEntity(BlockPos position, BlockState state) {
        super(MyBlockEntities.SATURATOR_BLOCK_ENTITY.get(), position, state);
        inv = new SaturatorItemStackHandler();
        this.lazyInv = LazyOptional.of(() -> this.inv);
    }

    // this is coming from the renderering system on the client.
    public void setColors(float[] colors) {
        _colors = colors;
        var desats = getDesaturators();
        if (ValidDesaturators(desats)) {
            for (var i = 0; i < 4; i++) {
                desats[i].setColors(colors);
            }
        }
    }

    public float percentageDone() {
        return _percentageDone;
    }

    @Override
    @Nullable
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        super.onDataPacket(net, pkt);
        handleUpdateTag(pkt.getTag() == null ? new CompoundTag() : pkt.getTag());
    }

    public boolean updateBlock() {
        if (level != null) {
            BlockState state = level.getBlockState(worldPosition);
            level.sendBlockUpdated(worldPosition, state, state, 3);
            setChanged();
            return true;
        }
        return false;
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        var istack = inv.getStackInSlot(INPUTSLOT);
        var ostack = inv.getStackInSlot(OUTPUTSLOT);
        if (istack != null) {
            CompoundTag stackTag = new CompoundTag();
            istack.save(stackTag);
            tag.put("iis", stackTag);
        }
        if (ostack != null) {
            CompoundTag stackTag = new CompoundTag();
            ostack.save(stackTag);
            tag.put("ois", stackTag);
        }
        CompoundTag localTag = new CompoundTag();
        localTag.putInt("ept", this._energyPerTick);
        localTag.putInt("tr", this._ticksRemaining);
        localTag.putBoolean("cr", this._crafting);
        localTag.putInt("c", this._color);
        localTag.putInt("pr0", this._powerremaining[0]);
        localTag.putInt("pr1", this._powerremaining[1]);
        localTag.putInt("pr2", this._powerremaining[2]);
        localTag.putInt("pr3", this._powerremaining[3]);
        localTag.putString("r", _recipe == null ? "" : this._recipe.getId().toString());
        localTag.putFloat("rem", this._percentageDone);
        tag.put("saturator", localTag);
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, final @Nullable Direction side) {
        if (cap == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return lazyInv.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void invalidateCaps() {
        // itemHandler.invalidate();
        super.invalidateCaps();
    }

    @Override
    public void load(CompoundTag compound) {
        super.load(compound);
        setInputStack(ItemStack.of((CompoundTag) compound.get("iis")));
        setOutputStack(ItemStack.of((CompoundTag) compound.get("ois")));
        var localTag = compound.getCompound("saturator");
        if (localTag != null) {
            _energyPerTick = localTag.getInt("ept");
            _ticksRemaining = localTag.getInt("tr");
            _crafting = localTag.getBoolean("cr");
            if (localTag.contains("c"))
                _color = localTag.getInt("c");
            else
                _color = -1;
            _powerremaining[0] = localTag.getInt("pr0");
            _powerremaining[1] = localTag.getInt("pr1");
            _powerremaining[2] = localTag.getInt("pr2");
            _powerremaining[3] = localTag.getInt("pr3");
            _percentageDone = localTag.getFloat("rem");
            var rn = localTag.getString("r");
            _recipe = null;
            if (this.level != null && !this.level.isClientSide()) {
                if (rn != null) {
                    var recipes = this.level.getRecipeManager().getAllRecipesFor(SaturatorRecipe.Type.INSTANCE);
                    for (var i = 0; i < recipes.size(); i++) {
                        if (recipes.get(i).getId().toString().equals(rn)) {
                            _recipe = recipes.get(i);
                        }
                    }
                }
            }
        }
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = new CompoundTag();
        this.saveAdditional(tag);
        return tag;
    }

    public ItemStack getDisplayedItemStack() {
        if (!this.inv.getStackInSlot(OUTPUTSLOT).isEmpty())
            return this.inv.getStackInSlot(OUTPUTSLOT);
        return this.inv.getStackInSlot(INPUTSLOT);
    }

    protected class SaturatorItemStackHandler extends ItemStackHandler {
        public SaturatorItemStackHandler() {
            super(NUMBEROFSLOTS);
        }

        // max number of items in a slot
        @Override
        public int getSlotLimit(int slot) {
            return 1;
        }

        @Override
        @Nonnull
        public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
            // if empty (hand) do nothing
            if (stack.isEmpty())
                return ItemStack.EMPTY;

            // don't allow inserting into outputslot
            if (slot == OUTPUTSLOT)
                return stack;

            // don't accept item if trying to go into inputslot and outputslot is not empty.
            if (slot == INPUTSLOT && !getStackInSlot(OUTPUTSLOT).isEmpty())
                return stack;

            // don't accept item if something is already in inputslot
            if (slot == INPUTSLOT && !getStackInSlot(INPUTSLOT).isEmpty())
                return stack;

            int limit = 1;
            boolean reachedLimit = stack.getCount() > limit;

            if (!simulate) {
                this.stacks.set(slot, reachedLimit ? ItemHandlerHelper.copyStackWithSize(stack, limit) : stack);
                onContentsChanged(slot);
            }

            return reachedLimit ? ItemHandlerHelper.copyStackWithSize(stack, stack.getCount() - limit)
                    : ItemStack.EMPTY;

        }

        @Override
        @Nonnull
        public ItemStack extractItem(int slot, int amount, boolean simulate) {
            if (amount == 0)
                return ItemStack.EMPTY;

            if (slot != OUTPUTSLOT)
                return ItemStack.EMPTY;

            ItemStack existing = this.stacks.get(slot);
            if (existing.isEmpty())
                return ItemStack.EMPTY;

            int toExtract = 1;

            if (existing.getCount() <= toExtract) {
                if (!simulate) {
                    this.stacks.set(slot, ItemStack.EMPTY);
                    onContentsChanged(slot);
                    return existing;
                } else {
                    return existing.copy();
                }
            } else {
                if (!simulate) {
                    this.stacks.set(slot,
                            ItemHandlerHelper.copyStackWithSize(existing, existing.getCount() - toExtract));
                    onContentsChanged(slot);
                }
                return ItemHandlerHelper.copyStackWithSize(existing, toExtract);
            }
        }

        @Override
        protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            updateBlock(); // send inventory change to client?
        }
    }

    public ItemStack getInputStack() {
        return inv.getStackInSlot(INPUTSLOT);
    }

    public ItemStack getOutputStack() {
        return inv.getStackInSlot(OUTPUTSLOT);
    }

    public void setInputStack(ItemStack stack) {
        inv.setStackInSlot(INPUTSLOT, stack);
    }

    public boolean isCrafting() {
        return _crafting;
    }

    public void setOutputStack(ItemStack stack) {
        inv.setStackInSlot(OUTPUTSLOT, stack);
    }

    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, SaturatorBlockEntity pBlockEntity) {
        if (pLevel.isClientSide)
            return;
        pBlockEntity._tick++;
        if (pBlockEntity._crafting) {
            pBlockEntity.progressCrafting(pLevel, pPos, pBlockEntity);
        }
        if (pBlockEntity._crafting && pBlockEntity._tick > 5) {
            // check crafting setup is valid.
            pBlockEntity._tick = 0;
            var recipe = getRecipeSetup(pLevel, pPos, pBlockEntity);
            if (recipe == null || recipe != pBlockEntity._recipe)
                pBlockEntity.stopCrafting();

        }
        if (!pBlockEntity._crafting && pBlockEntity._tick > 20) {
            pBlockEntity._tick = 0;
            var recipe = getRecipeSetup(pLevel, pPos, pBlockEntity);
            pBlockEntity.startCrafting(recipe);
        }
    }

    private static SaturatorRecipe getRecipeSetup(Level pLevel, BlockPos pPos, SaturatorBlockEntity pBlockEntity) {
        var input = pBlockEntity.getInputStack();
        var recipes = pLevel.getRecipeManager().getAllRecipesFor(SaturatorRecipe.Type.INSTANCE);
        var desats = pBlockEntity.getDesaturators();
        if (pBlockEntity.ValidDesaturators(desats)) {
            for (SaturatorRecipe recipe : recipes) {
                if (recipe.getInput().test(input)) {
                    if (recipe.matches(pLevel, pBlockEntity.getInputStack(), desats[0].getStack(), desats[1].getStack(),
                            desats[2].getStack(), desats[3].getStack())) {
                        // Chatter.sendToAllPlayers("recipe match");
                        return recipe;
                    } else {
                        // Chatter.sendToAllPlayers("recipe mismatch");
                    }
                }
            }
        }
        return null;
    }

    private void startCrafting(SaturatorRecipe recipe) {
        if (recipe == null)
            return;
        // Chatter.sendToAllPlayers("Starting craft " + recipe.name.toString());
        _recipe = recipe;
        _crafting = true;
        _color = recipe.getColor();
        _powerremaining[0] = recipe.getPower();
        _powerremaining[1] = recipe.getPower();
        _powerremaining[2] = recipe.getPower();
        _powerremaining[3] = recipe.getPower();
        _ticksRemaining = recipe.getCraftingTicks();
        _energyPerTick = recipe.getPower() / recipe.getCraftingTicks();
        _percentageDone = 0;
        if (_energyPerTick <= 0)
            _energyPerTick = 1;
        updateBlock();
    }

    private void progressCrafting(Level pLevel, BlockPos pPos, SaturatorBlockEntity pBlockEntity) {
        var desats = getDesaturators();
        if (_recipe != null && ValidDesaturators(desats)) {
            for (var i = 0; i < 4; i++) {
                var extracted = desats[i].extractEnergy(Math.min(_energyPerTick, _powerremaining[i]));
                desats[i].setStarved(extracted < _energyPerTick && _powerremaining[i] > extracted);
                _powerremaining[i] -= extracted;
                desats[i].setCrafting(true);
            }
            _ticksRemaining--;
            if (_ticksRemaining < 0)
                _ticksRemaining = 0;
            // Chatter.sendToAllPlayers(_ticksRemaining + " ticks 0:" + _powerremaining[0] +
            // " 1:" + _powerremaining[1]
            // + " 2:" + _powerremaining[2] + " 3:" + _powerremaining[3]);

            float total = (float) _recipe.getCraftingTicks() + (float) _recipe.getPower() * 4;
            float done = (_recipe.getCraftingTicks() - _ticksRemaining) + (_recipe.getPower() - _powerremaining[0])
                    + (_recipe.getPower() - _powerremaining[1]) + (_recipe.getPower() - _powerremaining[2])
                    + (_recipe.getPower() - _powerremaining[3]);
            if (total == 0)
                _percentageDone = 0;
            else
                _percentageDone = Math.min(1, done / total);

            if (_ticksRemaining <= 0) {
                if (_powerremaining[0] <= 0 && _powerremaining[1] <= 0 && _powerremaining[2] <= 0
                        && _powerremaining[3] <= 0) {
                    finishCraft(pLevel, pPos, pBlockEntity);
                }
            } /* else {
                 if (pLevel instanceof ServerLevel) {
                     ((ServerLevel) pLevel).sendParticles(
                             new ItemParticleOption(ParticleTypes.ITEM, _recipe.getIngredients().get(0).getItems()[0]),
                             pPos.getX(), pPos.getY() + 1.5f, pPos.getZ(), 1, 0, 0.5D, 0, .1);
                 }
              }*/
        } else
            stopCrafting();
        updateBlock();
    }

    private void stopCrafting() {
        // Chatter.sendToAllPlayers("Stopping craft");
        if (_crafting && _percentageDone < 1)
            if (level instanceof ServerLevel)
                ((ServerLevel) level).sendParticles(ParticleTypes.SMOKE, getBlockPos().getX() + .5f,
                        getBlockPos().getY() + .5f, getBlockPos().getZ() + .5f, 20, .1f, .1f, .1f, .1f);

        _crafting = false;
        _recipe = null;
        _ticksRemaining = 0;
        _powerremaining[0] = 0;
        _powerremaining[1] = 0;
        _powerremaining[2] = 0;
        _powerremaining[3] = 0;
        _energyPerTick = 0;
        _percentageDone = 0;
        updateBlock();
    }

    private void finishCraft(Level pLevel, BlockPos pPos, SaturatorBlockEntity pBlockEntity) {
        var recipe = getRecipeSetup(pLevel, pPos, pBlockEntity);
        if (recipe == null || recipe != pBlockEntity._recipe) {
            pBlockEntity.stopCrafting();
            return;
        }
        var desats = getDesaturators();
        if (!ValidDesaturators(desats)) {
            pBlockEntity.stopCrafting();
            return;
        }
        // Chatter.sendToAllPlayers("Crafting complete");
        desats[0].setItem(0, ItemStack.EMPTY);
        desats[1].setItem(0, ItemStack.EMPTY);
        desats[2].setItem(0, ItemStack.EMPTY);
        desats[3].setItem(0, ItemStack.EMPTY);
        this.inv.setStackInSlot(OUTPUTSLOT, _recipe.getResultItem());
        this.inv.setStackInSlot(INPUTSLOT, ItemStack.EMPTY);
        this.stopCrafting();

        if (level instanceof ServerLevel)
            ((ServerLevel) level).sendParticles(ParticleTypes.ELECTRIC_SPARK, pBlockEntity.getBlockPos().getX() + .5f,
                    pBlockEntity.getBlockPos().getY() + .5f, pBlockEntity.getBlockPos().getZ() + .5f, 20, .2f, .3f, .2f,
                    .2f);
        updateBlock();

    }

    private boolean ValidDesaturators(DesaturatorBlockEntity[] desats) {
        if (desats == null)
            desats = getDesaturators();
        return desats[3] != null;
    }

    private DesaturatorBlockEntity[] getDesaturators() {
        var desats = new DesaturatorBlockEntity[4];
        var north = this.level.getBlockEntity(this.getBlockPos().north(3));
        if (north instanceof DesaturatorBlockEntity)
            desats[0] = (DesaturatorBlockEntity) north;
        else
            return desats;
        var east = this.level.getBlockEntity(this.getBlockPos().east(3));
        if (east instanceof DesaturatorBlockEntity)
            desats[1] = (DesaturatorBlockEntity) east;
        else
            return desats;
        var south = this.level.getBlockEntity(this.getBlockPos().south(3));
        if (south instanceof DesaturatorBlockEntity)
            desats[2] = (DesaturatorBlockEntity) south;
        else
            return desats;
        var west = this.level.getBlockEntity(this.getBlockPos().west(3));
        if (west instanceof DesaturatorBlockEntity)
            desats[3] = (DesaturatorBlockEntity) west;
        return desats;
    }

    public int getColor() {
        return _color;
    }

    public float[] getColors() {
        return _colors;
    }

    @Override
    public Component getMessage() {
        if (!_crafting)
            return TextComponent.EMPTY;
        return new TextComponent(Math.round(_percentageDone * 100) + "%");
    }

}
