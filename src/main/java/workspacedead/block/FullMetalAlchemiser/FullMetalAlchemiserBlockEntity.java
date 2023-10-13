package workspacedead.block.FullMetalAlchemiser;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import workspacedead.block.generators.CustomEnergyStorage;
import workspacedead.recipe.FullMetalAlchemiserRecipe;
import workspacedead.registry.MySounds;
import workspacedead.registry.MyBlockEntities;
import workspacedead.network.EntityInts;
import workspacedead.network.IHandleClientInt;

public class FullMetalAlchemiserBlockEntity extends BlockEntity implements IHandleClientInt {
    public static final int POWERGEN_CAPACITY = 500000; // Max capacity
    public static final int POWERGEN_RECEIVE = 5000; // Power to send out per tick
    private final CustomEnergyStorage energy = createEnergyInterface();
    private final LazyOptional<IEnergyStorage> energyCapability = LazyOptional.of(() -> energy);
    private int _cycle;
    private int _beamlength;
    private int _redstonemode;
    private int _playsound = 1;
    private boolean _signalwentoff;
    private static final int MAXBEAMLENGTH = 5;
    private static final int WARMUPTICKS = 5;
    private static final int FIRINGTICKS = 15;
    private static final int WAITTICKS = 40;

    public FullMetalAlchemiserBlockEntity(BlockPos position, BlockState state) {
        super(MyBlockEntities.FULLMETALALCHEMISER_BLOCK_ENTITY.get(), position, state);
    }

    private CustomEnergyStorage createEnergyInterface() {
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

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        if (tag.get("energy") != null)
            this.energy.deserializeNBT(tag.get("energy"));
        this._cycle = tag.getInt("cycle");
        this._beamlength = tag.getInt("beamlen");
        this._playsound = tag.getInt("play");
        this._redstonemode = tag.getInt("rsm");
        this._signalwentoff = tag.getBoolean("sigoff");
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("energy", this.energy.serializeNBT());
        tag.putInt("cycle", _cycle);
        tag.putInt("beamlen", _beamlength);
        tag.putInt("play", _playsound);
        tag.putInt("rsm", _redstonemode);
        tag.putBoolean("sigoff", _signalwentoff);
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

    public boolean isFiring() {
        // return true;
        return _cycle >= WARMUPTICKS && _cycle < WARMUPTICKS + FIRINGTICKS;
    }

    public boolean isCooldown() {
        return _cycle > 0 && _cycle < WAITTICKS;
    }

    public boolean isActive() {
        return _cycle > 0;
    }

    public void tickServer(FullMetalAlchemiserBlock entity, Level lvl, BlockPos pos, BlockState blockState) {
        if (_cycle > 0) {
            _cycle++;
            if (_cycle > WARMUPTICKS + FIRINGTICKS + WAITTICKS) {
                _cycle = 0; // end of cycle
            } else if (_cycle == WARMUPTICKS) {
                fireLazors(pos, blockState);
                updateBlock(); // turn to firing
            } else if (_cycle == WARMUPTICKS + FIRINGTICKS + 1) {
                updateBlock(); // go into cooldown
            }
        }

        var signal = lvl.hasNeighborSignal(pos);
        // start cycle if redstone signal
        if (_cycle == 0) {
            if ((_redstonemode == 0 || (_redstonemode == 1 && signal)))
                _cycle = 1;
            if ((_redstonemode == 2) && !signal)
                _cycle = 1;
            if ((_redstonemode == 3) && signal && _signalwentoff) {
                // start on pulse
                _cycle = 1;
                _signalwentoff = false;
            }
        }
        if (!signal && !_signalwentoff) {
            // reset pulse
            _signalwentoff = true;
            setChanged();
        }
    }

    protected static final AABB TOUCH_AABB = new AABB(0D, 0.0D, 0D, 1D, 1D, 1D);

    private void fireLazors(BlockPos pos, BlockState blockStatepos) {
        Direction d = this.getBlockState().getValue(DirectionalBlock.FACING);
        var len = 0;
        var recipes = this.level.getRecipeManager().getAllRecipesFor(FullMetalAlchemiserRecipe.Type.INSTANCE);
        if (getPlaySound() == 1)
            level.playSound(null, getBlockPos().getX(), getBlockPos().getY(), getBlockPos().getZ(),
                    MySounds.FULLMETALALCHEMISER_FIRING.get(), SoundSource.BLOCKS, 1, 1);
        if (getPlaySound() == 2)
            level.playSound(null, getBlockPos().getX(), getBlockPos().getY(), getBlockPos().getZ(),
                    MySounds.FULLMETALALCHEMISER_FIRING_ALT.get(), SoundSource.BLOCKS, 1, 1);
        for (var i = 1; i <= MAXBEAMLENGTH; i++) {
            var pos2 = pos.relative(d, i);
            if (this.level.getBlockState(pos2).isCollisionShapeFullBlock(level, pos))
                break;
            // var aabb = new AABB(pos);
            // aabb.inflate(5);
            AABB aabb = TOUCH_AABB.move(pos2);
            var list2 = level.getEntitiesOfClass(ItemEntity.class, aabb);
            // var items = level.getEntities((Entity) null, aabb);
            // if (list2.size() > 0) {
            // Chatter.sendToAllPlayers("It finally worked!");
            // }
            for (var its : list2) {
                // if (!(its instanceof ItemEntity))
                // continue;
                var ie = ((ItemEntity) its);
                var is = ie.getItem();
                if (is == null)
                    continue;
                for (var r : recipes) {
                    if (r.getInput().test(is)) {
                        int maxitems = (int) Math.min(is.getCount(),
                                Math.floor(this.energy.getEnergyStored() / r.getPower()));
                        while (maxitems > 0) {
                            var spawnitems = Math.min(maxitems, r.getResultItem().getMaxStackSize());
                            if (spawnitems == 0)// Avoid endless loop; can this even happen?
                                break;
                            maxitems -= spawnitems;
                            this.energy.extractEnergy(r.getPower() * spawnitems, false);
                            is.shrink(spawnitems);
                            var pStack = new ItemStack(r.getResultItem().getItem(), spawnitems);
                            ItemEntity itementity = new ItemEntity(this.level, pos2.getX() + .5f, pos2.getY() + .5f,
                                    pos2.getZ() + .5f, pStack);
                            // itementity.setDefaultPickUpDelay();
                            itementity.setPickUpDelay(10);
                            this.level.addFreshEntity(itementity);
                        }
                    }
                }
            }
            len++;
        }
        _beamlength = len;

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
        handleUpdateTag(pkt.getTag() == null ? new CompoundTag() : pkt.getTag());
    }

    public float getBeamLength() {
        return _beamlength;
    }

    public int getEnergy() {
        return energy.getEnergyStored();
    }

    public void setRedstoneMode(int mode) {
        _redstonemode = mode;
        updateBlock();
    }

    public int getRedstoneMode() {
        return _redstonemode;
    }

    public void setPlaySound(int mode) {
        _playsound = mode;
        updateBlock();
    }

    public int getPlaySound() {
        return _playsound;
    }

    @Override
    public void handleClientInt(int controlId, int value) {
        if (controlId == 1)
            setRedstoneMode(value);
        if (controlId == 2)
            setPlaySound(value);
        if (controlId == EntityInts.TRIGGER_UPDATEBLOCK)
            updateBlock();
    }

    // @Override
    // @OnlyIn(Dist.CLIENT)
    // public void drawOverlay() {
    // var minecraft = Minecraft.getInstance();
    // var window = minecraft.getWindow();
    // minecraft.font.drawShadow(pose, new TextComponent()
    // // minecraft.fontRenderer.drawStringWithShadow(
    // // TextFormatting.YELLOW + "" + TextFormatting.ITALIC +
    // displayString.getFormattedText(),
    // // window.getGuiScaledWidth() / 2 + 35, window.getGuiScaledHeight() / 2 - 15,
    // // StringUtil.DECIMAL_COLOR_WHITE);
    // }
}
