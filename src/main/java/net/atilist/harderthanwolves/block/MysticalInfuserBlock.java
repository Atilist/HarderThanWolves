package net.atilist.harderthanwolves.block;

import net.atilist.harderthanwolves.block.entity.MysticalInfuserBlockEntity;
import net.atilist.harderthanwolves.container.MysticalInfuserScreenHandler;
import net.atilist.harderthanwolves.events.init.ScreenHandlerListener;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.material.Material;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.gui.screen.container.GuiHelper;
import net.modificationstation.stationapi.api.template.block.TemplateBlockWithEntity;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class MysticalInfuserBlock extends TemplateBlockWithEntity {

    private Random random = new Random();

    int topTexture;
    int sideTexture;
    int bottomTexture;

    public MysticalInfuserBlock(Identifier identifier, Material material, float hardness, BlockSoundGroup blockSounds) {
        super(identifier, material);
        setTranslationKey(identifier.namespace, identifier.path);
        setHardness(hardness);
        setSoundGroup(blockSounds);
    }

    @Override
    public MysticalInfuserBlock setHardness(float Hardness) {
        return (MysticalInfuserBlock) super.setHardness(Hardness);
    }

    public void specifyTextures(int topTexture, int sideTexture, int bottomTexture) {
        this.topTexture = topTexture;
        this.sideTexture = sideTexture;
        this.bottomTexture = bottomTexture;
    }

    @Override
    public int getTexture(int i, int j) {
        if (i == 0) return bottomTexture;
        if (i == 1) return topTexture;
        return sideTexture;
    }

    @Override
    public boolean onUse(World world, int i, int j, int k, PlayerEntity playerEntity) {
        MysticalInfuserBlockEntity mysticalInfuserBlockEntity = (MysticalInfuserBlockEntity)world.getBlockEntity(i, j, k);
        ScreenHandlerListener.tempGuiX = i;
        ScreenHandlerListener.tempGuiY = j;
        ScreenHandlerListener.tempGuiZ = k;
        GuiHelper.openGUI(playerEntity, Identifier.of("harderthanwolves:openMysticalInfuser"), (Inventory) mysticalInfuserBlockEntity, new MysticalInfuserScreenHandler(playerEntity.inventory, (MysticalInfuserBlockEntity) mysticalInfuserBlockEntity));
        return true;
    }

    @Override
    protected BlockEntity createBlockEntity() {
        return new MysticalInfuserBlockEntity();
    }

    public void onBreak(World world, int x, int y, int z) {
        MysticalInfuserBlockEntity var5 = (MysticalInfuserBlockEntity)world.getBlockEntity(x, y, z);
        for(int var6 = 0; var6 < var5.size(); ++var6) {
            ItemStack var7 = var5.getStack(var6);
            if (var7 != null) {
                float var8 = this.random.nextFloat() * 0.8F + 0.1F;
                float var9 = this.random.nextFloat() * 0.8F + 0.1F;
                float var10 = this.random.nextFloat() * 0.8F + 0.1F;
                while(var7.count > 0) {
                    int var11 = this.random.nextInt(21) + 10;
                    if (var11 > var7.count) {
                        var11 = var7.count;
                    }
                    var7.count -= var11;
                    ItemEntity var12 = new ItemEntity(world, (float)x + var8, (float)y + var9, (float)z + var10, new ItemStack(var7.itemId, var11, var7.getDamage()));
                    float var13 = 0.05F;
                    var12.velocityX = (float)this.random.nextGaussian() * var13;
                    var12.velocityY = (float)this.random.nextGaussian() * var13 + 0.2F;
                    var12.velocityZ = (float)this.random.nextGaussian() * var13;
                    world.spawnEntity(var12);
                }
            }
        }

        super.onBreak(world, x, y, z);
    }
}
