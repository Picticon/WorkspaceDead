package workspacedead.block.KubeJSTable;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import workspacedead.network.MyMessages;
import workspacedead.network.RecipeMessage;
import workspacedead.registry.MyBlocks;
import workspacedead.registry.MyContainers;

@OnlyIn(Dist.CLIENT)
public class KubeJSTableContainer extends AbstractContainerMenu {
    public KubeJSTableBlockEntity blockEntity;
    private Player playerEntity;
    private IItemHandler playerInventory;
    private static int OUTPUTSLOT = 9;
    private static int KEEPSLOT = 10;

    public KubeJSTableContainer(int windowId, BlockPos pos, Inventory playerInventory, Player playerEntity) {
        super(MyContainers.KUBEJS_TABLE_CONTAINER.get(), windowId);
        blockEntity = (KubeJSTableBlockEntity) playerEntity.getCommandSenderWorld().getBlockEntity(pos);
        this.playerEntity = playerEntity;
        this.playerInventory = new InvWrapper(playerInventory);

        if (blockEntity != null) {
            blockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
                for (var y = 0; y < 3; y++)
                    for (var x = 0; x < 3; x++)
                        addSlot(new SlotItemHandler(h, y * 3 + x, 8 + x * 18, 8 + y * 18));
                addSlot(new SlotItemHandler(h, OUTPUTSLOT, 92, 12));
                addSlot(new SlotItemHandler(h, KEEPSLOT, 116, 12));
            });
        }
        layoutPlayerInventorySlots(8, 104);
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos()), playerEntity,
                MyBlocks.KUBEJS_TABLE_BLOCK.get());
    }

    private void layoutPlayerInventorySlots(int leftCol, int topRow) {
        // Player inventory
        addSlotBox(playerInventory, 9, leftCol, topRow, 9, 18, 3, 18);

        // Hotbar
        topRow += 58;
        addSlotRange(playerInventory, 0, leftCol, topRow, 9, 18);
    }

    private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
        for (int i = 0; i < amount; i++) {
            addSlot(new SlotItemHandler(handler, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

    private int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount,
            int dy) {
        for (int j = 0; j < verAmount; j++) {
            index = addSlotRange(handler, index, x, y, horAmount, dx);
            y += dy;
        }
        return index;
    }

    @OnlyIn(Dist.CLIENT)
    public void sendRecipeToServer(CraftingRecipe recipe) {
        var minecraft = Minecraft.getInstance();
        //Chatter.sendToPlayer(minecraft.player, recipe.getId().toString());
        MyMessages.sendToServer(new RecipeMessage(minecraft.level, this.blockEntity.getBlockPos(), recipe.getId()));
    }

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        return ItemStack.EMPTY;
    }
}