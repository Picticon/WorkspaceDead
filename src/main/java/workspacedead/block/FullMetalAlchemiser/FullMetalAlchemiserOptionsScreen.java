package workspacedead.block.FullMetalAlchemiser;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import workspacedead.WorkspaceDead;
import workspacedead.gui.ItemButton;
import workspacedead.network.EntityInts;
import workspacedead.network.MyMessages;

public class FullMetalAlchemiserOptionsScreen extends Screen {

    private static Component _title = new TranslatableComponent("workspacedead.fullmetalalchemiseroptionsscreen.title");
    private static final ResourceLocation BACKGROUND = new ResourceLocation(WorkspaceDead.MOD_ID,
            "textures/gui/blank_180x152.png");
    private FullMetalAlchemiserBlockEntity _entity;
    private int xSize = 180;
    private int ySize = 152;
    protected int guiLeft;
    protected int guiTop;
    private ItemButton _btn_rs_0;
    private ItemButton _btn_rs_1;
    private ItemButton _btn_rs_2;
    private ItemButton _btn_rs_3;
    private int ctr;

    public FullMetalAlchemiserOptionsScreen(FullMetalAlchemiserBlockEntity be) {
        super(_title);
        _entity = be;

        // CycleButton _cyclebutton= new CycleButton(0,0,xSize-20,20, new
        // TranslatableComponent("REDSTONE"), new TranslatableComponent("NAME"), 0,
        // true,
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    protected void init() {
        clearWidgets();
        super.init();

        this.guiLeft = (width - this.xSize) / 2;
        this.guiTop = (height - this.ySize) / 2;

        // addRenderableWidget(
        // new Button(guiLeft + 10, guiTop + 20, xSize - 20, 20, new
        // TranslatableComponent("CLOSE"), button -> {
        // minecraft.setScreen(null);
        // }));
        // addRenderableWidget(
        // new Button(guiLeft + 10, guiTop + 40, xSize - 20, 20, new
        // TranslatableComponent("555"), button -> {
        // MyMessages.sendToServer(
        // new EntityInts(Minecraft.getInstance().player.level, _entity.getBlockPos(),
        // 1, 555));
        // }));

        // addRenderableWidget(
        // new Button(guiLeft + 10, guiTop + 60, xSize - 20, 20, new
        // TranslatableComponent("666"), button -> {
        // MyMessages.sendToServer(
        // new EntityInts(Minecraft.getInstance().player.level, _entity.getBlockPos(),
        // 1, 666));
        // }));

        Minecraft minecraft = Minecraft.getInstance();
        _btn_rs_0 = new ItemButton(guiLeft + 10, guiTop + 90, 20, 20, new TextComponent(""), Items.REDSTONE_TORCH,
                button -> {
                    MyMessages.sendToServer(new EntityInts(minecraft.player.level, _entity.getBlockPos(), 1, 0));
                });
        _btn_rs_1 = new ItemButton(guiLeft + 35, guiTop + 90, 20, 20, new TextComponent(""), Items.REDSTONE, button -> {
            MyMessages.sendToServer(new EntityInts(minecraft.player.level, _entity.getBlockPos(), 1, 1));
        });
        _btn_rs_2 = new ItemButton(guiLeft + 60, guiTop + 90, 20, 20, new TextComponent(""), Items.COMPARATOR,
                button -> {
                    MyMessages.sendToServer(new EntityInts(minecraft.player.level, _entity.getBlockPos(), 1, 2));
                });
        _btn_rs_3 = new ItemButton(guiLeft + 85, guiTop + 90, 20, 20, new TextComponent(""), Items.REPEATER, button -> {
            MyMessages.sendToServer(new EntityInts(minecraft.player.level, _entity.getBlockPos(), 1, 3));
        });
        addRenderableWidget(_btn_rs_0);
        addRenderableWidget(_btn_rs_1);
        addRenderableWidget(_btn_rs_2);
        addRenderableWidget(_btn_rs_3);
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float delta) {
        renderBackground(poseStack, 0, 0, 0);
        super.render(poseStack, mouseX, mouseY, delta);
        renderForeground(poseStack, mouseX, mouseY, delta);
    }

    public void renderBackground(PoseStack poseStack, int mouseX, int mouseY, float delta) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
        RenderSystem.setShaderTexture(0, BACKGROUND);
        blit(poseStack, guiLeft, guiTop, 0, 0, xSize, ySize);
        RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
    }

    public void renderForeground(PoseStack poseStack, int mouseX, int mouseY, float delta) {
        font.draw(poseStack, _title.getVisualOrderText(), (float) (guiLeft + 10), guiTop + 7, 0);
        var msg = new TextComponent("Power: " + _entity.getEnergy());
        font.draw(poseStack, msg.getVisualOrderText(), (float) guiLeft + 10, guiTop + 20, 0);

        _btn_rs_0.setSelected(_entity.getRedstoneMode() == 0);
        _btn_rs_1.setSelected(_entity.getRedstoneMode() == 1);
        _btn_rs_2.setSelected(_entity.getRedstoneMode() == 2);
        _btn_rs_3.setSelected(_entity.getRedstoneMode() == 3);
        var redmsg = new TranslatableComponent("text.workspacedead.runsalways");
        if (_entity.getRedstoneMode() == 1)
            redmsg = new TranslatableComponent("text.workspacedead.runsonsignal");
        if (_entity.getRedstoneMode() == 2)
            redmsg = new TranslatableComponent("text.workspacedead.runswithoutsignal");
        if (_entity.getRedstoneMode() == 3)
            redmsg = new TranslatableComponent("text.workspacedead.runswithpulse");
        font.draw(poseStack, redmsg.getVisualOrderText(), (float) (guiLeft + 10), guiTop + 120, 0);
    }

    // the following is a hack to get the amount of power sent back to the client.
    // too lazy, don't care
    @Override
    public void tick() {
        super.tick();
        if (ctr % 10 == 0)
            MyMessages.sendToServer(
                    new EntityInts(minecraft.player.level, _entity.getBlockPos(), EntityInts.TRIGGER_UPDATEBLOCK, 0));
        ctr++;
    }

    @Override
    public boolean keyPressed(int pKeyCode, int pScanCode, int pModifiers) {
        if (super.keyPressed(pKeyCode, pScanCode, pModifiers)) {
            return true;
        }
        InputConstants.Key mouseKey = InputConstants.getKey(pKeyCode, pScanCode);
        if (this.minecraft.options.keyInventory.isActiveAndMatches(mouseKey)) {
            this.onClose();
            return true;
        }
        return false;
    }
}
