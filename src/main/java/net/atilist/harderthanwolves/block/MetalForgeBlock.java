package net.atilist.harderthanwolves.block;

import net.atilist.harderthanwolves.events.init.BlockListener;
import net.atilist.harderthanwolves.events.init.ItemListener;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class MetalForgeBlock extends LazyBlockTemplate {
    public MetalForgeBlock(Identifier identifier, Material material, float hardness, BlockSoundGroup blockSounds) {
        super(identifier, material, hardness, blockSounds);
        this.setTickRandomly(true);
    }

    @Override
    public int getTickRate() {
        return 80;
    }

    @Override
    public void onTick(World world, int x, int y, int z, Random random) {
        super.onTick(world, x, y, z, random);
        world.scheduleBlockUpdate(x, y, z, this.id, this.getTickRate());
        int blockId = world.getBlockId(x, y + 1, z);
        if (world.getBlockId(x + 1, y + 1, z) != BlockListener.stoneBricks.id) {
            return;
        }
        if (world.getBlockId(x - 1, y + 1, z) != BlockListener.stoneBricks.id) {
            return;
        }
        if (world.getBlockId(x, y + 1, z + 1) != BlockListener.stoneBricks.id) {
            return;
        }
        if (world.getBlockId(x, y + 1, z - 1) != BlockListener.stoneBricks.id) {
            return;
        }
        if ((blockId != BlockListener.ironOreGravel.id && blockId != BlockListener.ironChunkBlock.id && blockId != BlockListener.goldChunkBlock.id && blockId != BlockListener.lapisGravel.id)) {
            return;
        }
        int smeltingMultiplier = 0;
        for (int xOffset = -1; xOffset <= 1; xOffset++) {
            for (int yOffset = -1; yOffset <= 0; yOffset++) {
                for (int zOffset = -1; zOffset <= 1; zOffset++) {
                    if (world.getBlockId(x + xOffset, y + yOffset, z + zOffset) == Block.FIRE.id) {
                        smeltingMultiplier++;
                    } else if (world.getBlockId(x + xOffset, y + yOffset, z + zOffset) == net.kozibrodka.wolves.events.BlockListener.stokedFire.id) {
                        smeltingMultiplier += 2;
                    }
                }
            }
        }
        Item resultItem = null;
        int itemCount = 1;
        if (world.getBlockMeta(x, y + 1, z) < 15) {
            world.setBlockMeta(x, y + 1, z, Math.min(15, world.getBlockMeta(x, y + 1, z) + smeltingMultiplier));
        } else if (blockId == BlockListener.ironOreGravel.id || blockId == BlockListener.ironChunkBlock.id) {
            resultItem = Item.IRON_INGOT;
        } else if (blockId == BlockListener.lapisGravel.id) {
            resultItem = ItemListener.crystallizedLapis;
            itemCount = 36;
        } else {
            resultItem = Item.GOLD_INGOT;
        }
        if (resultItem == null) {
            return;
        }
        world.playSound((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F, "random.fizz", 0.5F, 2.6F + (world.random.nextFloat() - world.random.nextFloat()) * 0.8F);
        world.setBlock(x, y + 1, z, 0);
        ItemEntity itemEntity = new ItemEntity(world, (double)x + 0.5, (double)y + 1.5, (double)z + 0.5, new ItemStack(resultItem, itemCount));
        itemEntity.pickupDelay = 10;
        world.spawnEntity(itemEntity);
    }
}
