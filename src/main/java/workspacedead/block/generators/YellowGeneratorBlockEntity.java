package workspacedead.block.generators;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import workspacedead.registry.MyBlockEntities;

public class YellowGeneratorBlockEntity extends BaseGeneratorBlockEntity {// implements IInventoryHolder {
    public static final int POWERGEN_CAPACITY = 50000; // Max capacity
    public static final int POWERGEN_GENERATE = 60; // Generation per tick
    public static final int POWERGEN_SEND = 200; // Power to send out per tick

    public YellowGeneratorBlockEntity(BlockPos position, BlockState state) {
        super(MyBlockEntities.YELLOW_GENERATOR_ENTITY.get(), position, state);
    }

    @Override
    public int getCapacity() {
        return POWERGEN_CAPACITY;
    }

    @Override
    public int getGeneration() {
        return POWERGEN_GENERATE;
    }

    @Override
    public int getOutputSpeed() {
        return POWERGEN_SEND;
    }

}
