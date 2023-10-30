package workspacedead.block.Saturator;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import workspacedead.block.InventoryEntityBaseBlock;
import workspacedead.block.generators.CustomEnergyStorage;
import workspacedead.client.IPowerHUD;
import workspacedead.gfx.ElectricityGFX;
import workspacedead.network.EntityInts;
import workspacedead.network.IHandleClientInt;
import workspacedead.network.MyMessages;
import workspacedead.registry.MyBlockEntities;

public class DesaturatorBlockEntity extends InventoryEntityBaseBlock implements IPowerHUD, IHandleClientInt {

    public static final int POWERGEN_CAPACITY = 500000; // Max capacity
    public static final int POWERGEN_RECEIVE = 5000; // Power to send out per tick
    private final CustomEnergyStorage energy = createEnergy();
    private final LazyOptional<IEnergyStorage> energyCapability = LazyOptional.of(() -> energy);
    private int _peeticks;
    public ElectricityGFX[] bolts = null;
    private float[] _colors = new float[] { 0, 0, 0, 0 };
    private int _craftingClock;
    private boolean _starved;

    public DesaturatorBlockEntity(BlockPos position, BlockState state) {
        super(MyBlockEntities.DESATURATOR_BLOCK_ENTITY.get(), position, state);
    }

    public ItemStack getDisplayedItemStack() {
        return this.getItem(0);
    }

    // this is coming from the renderering system on the client.
    public void setColors(float[] colors) {
        _colors[0] = colors[0];
        _colors[1] = colors[1];
        _colors[2] = colors[2];
    }

    // this is coming from the renderering system on the client.
    public float[] getColors() {
        return _colors;
    }

    // oh god, the hacks
    public boolean isCrafting() {
        _craftingClock--;
        return _craftingClock > 0;
    }

    @Override
    public Component getMessage() {
        _peeticks++;
        if (_peeticks % 40 == 0) {
            MyMessages.sendToServer(new EntityInts(this.level, this.getBlockPos(), EntityInts.TRIGGER_UPDATEBLOCK, 0));
        }
        return new TextComponent(energy.getEnergyStored() + " FE");
    }

    @Override
    public void handleClientInt(int controlId, int value) {
        if (controlId == EntityInts.TRIGGER_UPDATEBLOCK)
            updateBlock();
    }

    private CustomEnergyStorage createEnergy() {
        return new CustomEnergyStorage(POWERGEN_CAPACITY, POWERGEN_RECEIVE, 100000) {
            @Override
            protected void onEnergyChanged() {
                setChanged();
            }

            @Override
            public boolean canReceive() {
                return true;
            }
        };
    }

    public int storedenergy() {
        return energy.getEnergyStored();
    }

    // normally this shouldn't be done, but since this is part of a pseudo-multiblock, we'll let the saturator take energy.
    public int extractEnergy(int amount) {
        return energy.extractEnergy(amount, false);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.energy.deserializeNBT(tag.get("energy"));
        _starved = tag.getBoolean("starved");
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("energy", this.energy.serializeNBT());
        tag.putBoolean("starved", this._starved);
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        if (!this.isRemoved()) {
            if (cap == CapabilityEnergy.ENERGY) {
                return CapabilityEnergy.ENERGY.orEmpty(cap, this.energyCapability);
            }
        }
        return super.getCapability(cap, side);
    }

    public void setCrafting(boolean b) {
        _craftingClock = b ? 2 : 0;
    }

    public void setStarved(boolean b) {
        var needupdate = false;
        if (!this.level.isClientSide() && b != _starved)
            needupdate = true;
        _starved = b;
        if (needupdate)
            updateBlock();
    }

    public boolean getStarved() {
        return _starved;
    }
}