package net.atilist.harderthanwolves.gui;

import net.atilist.harderthanwolves.block.entity.MysticalInfuserBlockEntity;
import net.atilist.harderthanwolves.container.MysticalInfuserScreenHandler;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import org.lwjgl.opengl.GL11;

public class MysticalInfuserScreen extends HandledScreen {
    public MysticalInfuserScreen(PlayerInventory playerInventory, MysticalInfuserBlockEntity mysticalInfuserBlockEntity, int locX, int locY, int locZ)
    {
        super(new MysticalInfuserScreenHandler(playerInventory, mysticalInfuserBlockEntity));
        backgroundHeight = 204;
        associatedMysticalInfuserBlockEntity = mysticalInfuserBlockEntity;
        guiX = locX;
        guiY = locY;
        guiZ = locZ;
    }

    protected void drawForeground() {
        this.textRenderer.draw("Mystical Infuser", 60, 6, 4210752);
        this.textRenderer.draw("Inventory", 8, this.backgroundHeight - 96 + 2, 4210752);
    }

    protected void drawBackground(float tickDelta) {
        int backgroundTexture = this.minecraft.textureManager.getTextureId("/assets/harderthanwolves/stationapi/textures/gui/mysticalinfuser.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.textureManager.bindTexture(backgroundTexture);
        int var3 = (this.width - this.backgroundWidth) / 2;
        int var4 = (this.height - this.backgroundHeight) / 2;
        this.drawTexture(var3, var4, 0, 0, this.backgroundWidth, this.backgroundHeight);
        int var5;
        if (this.associatedMysticalInfuserBlockEntity.isBurning()) {
            var5 = this.associatedMysticalInfuserBlockEntity.getFuelTimeDelta(12);
            this.drawTexture(var3 + 57, var4 + 87 - var5, 176, 12 - var5, 14, var5 + 2);
        }
        var5 = this.associatedMysticalInfuserBlockEntity.getCookTimeDelta(24);
        this.drawTexture(var3 + 94, var4 + 37, 176, 14, var5 + 1, 16);
    }

    private MysticalInfuserBlockEntity associatedMysticalInfuserBlockEntity;
    private int guiX;
    private int guiY;
    private int guiZ;
}
