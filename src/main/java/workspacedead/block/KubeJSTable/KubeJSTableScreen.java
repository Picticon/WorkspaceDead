package workspacedead.block.KubeJSTable;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import workspacedead.WorkspaceDead;
import workspacedead.gui.ItemButton;
import workspacedead.network.EntityIntsMessage;
import workspacedead.network.MyMessages;
import workspacedead.util.Chatter;
import workspacedead.util.KubeJSScripting;

public class KubeJSTableScreen extends AbstractContainerScreen<KubeJSTableContainer> {

    private final ResourceLocation GUI = new ResourceLocation(WorkspaceDead.MOD_ID,
            "textures/gui/kubejs_table_container.png");
    private Checkbox _btn_removeoriginalrecipe;
    private KubeJSTableContainer container;

    public KubeJSTableScreen(KubeJSTableContainer container, Inventory inv, Component name) {
        super(container, inv, name);
        this.container = container;
        this.imageWidth = 256;
        this.imageHeight = 186;
    }

    @Override
    public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(PoseStack matrixStack, int mouseX, int mouseY) {
        Minecraft minecraft = Minecraft.getInstance();
        if (container.blockEntity.getOriginalRecipe() != null)
            drawString(matrixStack, minecraft.font, container.blockEntity.getOriginalRecipe().toString(), 30, 90,
                    0xffffffff);
        // drawString(matrixStack, minecraft.font, "aaa", 150, 20,
        //         0xffff0000);
    }

    @Override
    protected void renderBg(PoseStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.setShaderTexture(0, GUI);
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        this.blit(matrixStack, relX, relY, 0, 0, this.imageWidth, this.imageHeight);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    protected void init() {
        clearWidgets();
        super.init();

        this.addRenderableWidget(new Button(this.width / 2 + 10, this.height / 2 - 82, 110, 20,
                new TranslatableComponent("button.shapedcraft"), (btn) -> {
                    makerecipe(false);
                }));
        this.addRenderableWidget(new Button(this.width / 2 + 10, this.height / 2 - 60, 110, 20,
                new TranslatableComponent("button.shapelesscraft"), (btn) -> {
                    makerecipe(true);
                }));
        _btn_removeoriginalrecipe = this
                .addRenderableWidget(new CheckBoxNotification(this.width / 2 + 10, this.height / 2 - 38, 100, 20,
                        new TranslatableComponent("button.removerecipe"), container.blockEntity.getRemoveCheckbox()));
        _btn_removeoriginalrecipe.setFGColor(0x88888888);
        //_btn_removeoriginalrecipe.onClick(height, DOUBLE_CLICK_THRESHOLD_MS);

        this.addRenderableWidget(new ItemButton(this.width / 2 - 122, this.height / 2 - 12, 20, 20,
                new TranslatableComponent("X"), Items.AIR, (btn) -> {
                    MyMessages.sendToServer(
                            new EntityIntsMessage(minecraft.player.level, container.blockEntity.getBlockPos(),
                                    KubeJSTableBlockEntity.CLEAR_ORIGINAL_RECIPE, 0));
                }));

        this.addRenderableWidget(new ImageButton(0, 0, 16, 16, 1, 206, GUI, (a) -> {
            MyMessages.sendToServer(
                    new EntityIntsMessage(minecraft.player.level, container.blockEntity.getBlockPos(),
                            KubeJSTableBlockEntity.ADJUST_OUTPUT_QUANTITY, 1));

        }));
        this.addRenderableWidget(new ImageButton(0, 0, 16, 16, 10, 206, GUI, (a) -> {
            MyMessages.sendToServer(
                    new EntityIntsMessage(minecraft.player.level, container.blockEntity.getBlockPos(),
                            KubeJSTableBlockEntity.ADJUST_OUTPUT_QUANTITY, -1));
        }));

    }

    private void makerecipe(boolean shapeless) {
        var minecraft = Minecraft.getInstance();
        var player = minecraft.player;
        var output = this.container.getSlot(9).getItem();
        var items = new ItemStack[9];
        for (var i = 0; i < 9; i++) {
            items[i] = container.getSlot(i).getItem();
        }
        var type = "";
        var s = "";
        if (!shapeless) {
            s = KubeJSScripting.makeShapedDatapackTableRecipe(output, items);
            type = "shaped";
        } else {
            s = KubeJSScripting.makeShapelessDatapackTableRecipe(output, items);
            type = "shapeless";
        }
        if (s != null) {
            if (_btn_removeoriginalrecipe.selected() && container.blockEntity.getOriginalRecipe() != null) {
                s = "event.remove({id:'" + container.blockEntity.getOriginalRecipe().toString() + "'});\r\n" + s;
            }
            setClipboard(s);
            Chatter.sendToPlayer(player, "Copied " + type + " recipe to clipboard.");
        }
    }

    private static void setClipboard(String string) {
        Minecraft minecraft = Minecraft.getInstance();
        minecraft.keyboardHandler.setClipboard(string);
    }

    class CheckBoxNotification extends Checkbox {

        public CheckBoxNotification(int pX, int pY, int pWidth, int pHeight, Component pMessage, boolean pSelected) {
            super(pX, pY, pWidth, pHeight, pMessage, pSelected);
        }

        @Override
        public int getFGColor() {
            return 0xff00ff00;
        }

        @Override
        public void onPress() {
            super.onPress();
            MyMessages.sendToServer(
                    new EntityIntsMessage(minecraft.player.level, container.blockEntity.getBlockPos(),
                            KubeJSTableBlockEntity.REMOVE_ORIGINAL_VALUE_CHANGE, selected() ? 1 : 0));

        }

    }
}
