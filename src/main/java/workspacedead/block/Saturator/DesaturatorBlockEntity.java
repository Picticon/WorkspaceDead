package workspacedead.block.Saturator;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import workspacedead.block.InventoryEntityBaseBlock;
import workspacedead.block.generators.CustomEnergyStorage;
import workspacedead.registry.MyBlockEntities;

public class DesaturatorBlockEntity extends InventoryEntityBaseBlock {

    public static final int POWERGEN_CAPACITY = 500000; // Max capacity
    public static final int POWERGEN_RECEIVE = 5000; // Power to send out per tick
    private final CustomEnergyStorage energy = createEnergy();
    private final LazyOptional<IEnergyStorage> energyCapability = LazyOptional.of(() -> energy);

    public DesaturatorBlockEntity(BlockPos position, BlockState state) {
        super(MyBlockEntities.DESATURATOR_BLOCK_ENTITY.get(), position, state);
    }

    public ItemStack getDisplayedItemStack() {
        return this.getItem(0);
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

    // normally this shouldn't be done, but since this is part of a pseudo-multiblock, we'll let the saturator take energy.
    public int extractEnergy(int amount) {
        return energy.extractEnergy(amount, false);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.energy.deserializeNBT(tag.get("energy"));
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("energy", this.energy.serializeNBT());
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

}