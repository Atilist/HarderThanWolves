package net.atilist.harderthanwolves.block;

import net.atilist.harderthanwolves.events.init.BlockListener;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class ObsidianGeneratorBlock extends LazyBlockTemplate {
    private int otherSideTexture;

    public ObsidianGeneratorBlock(Identifier identifier, Material material, float hardness, BlockSoundGroup blockSounds) {
        super(identifier, material, hardness, blockSounds);
        setTickRandomly(true);
    }

    public void specifyTextures(int topTexture, int sideTexture, int otherSideTexture, int bottomTexture) {
        this.otherSideTexture = otherSideTexture;
        super.specifyTextures(topTexture, sideTexture, bottomTexture);
    }

    @Override
    public int getTexture(int i, int j) {
        if (i > 1 && j > 1) {
            return otherSideTexture;
        }
        return super.getTexture(i, j);
    }

    @Override
    public int getTickRate() {
        return 20;
    }

    @Override
    public void onTick(World world, int x, int y, int z, Random random) {
        super.onTick(world, x, y, z, random);
        world.scheduleBlockUpdate(x, y, z, BlockListener.obsidianGenerator.id, getTickRate());
        int meta = world.getBlockMeta(x, y, z);
        if (meta < 1) {
            return;
        }
        if (world.getBlockId(x, y + 1, z) != Block.WATER.id) {
            return;
        }
        if (world.getBlockId(x, y - 1, z) != 0) {
            return;
        }
        world.setBlock(x, y - 1, z, Block.OBSIDIAN.id);
        world.setBlockMeta(x, y, z, meta - 1);
        world.playSound((float)x + 0.5F, (float)y - 0.5F, (float)z + 0.5F, "random.fizz", 0.5F, 2.6F + (world.random.nextFloat() - world.random.nextFloat()) * 0.8F);
        for(int i = 0; i < 8; i++) {
            world.addParticle("largesmoke", (double)x + Math.random(), (double)y + 0.2, (double)z + Math.random(), 0.0, 0.0, 0.0);
        }
        world.blockUpdateEvent(x, y, z);
    }

    @Override
    public boolean onUse(World world, int x, int y, int z, PlayerEntity player) {
        if (world.getBlockMeta(x, y, z) > 0) {
            return false;
        }
        if (player == null) {
            return false;
        }
        if (player.getHand() == null) {
            return false;
        }
        if (player.getHand().itemId != Item.LAVA_BUCKET.id) {
            return false;
        }
        player.inventory.setStack(player.inventory.selectedSlot, new ItemStack(Item.BUCKET, 1));
        world.setBlockMeta(x, y, z, 8);
        world.blockUpdateEvent(x, y, z);
        return true;
    }
}
