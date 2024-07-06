package net.atilist.harderthanwolves.block;

import net.atilist.harderthanwolves.events.init.BlockListener;
import net.atilist.harderthanwolves.events.init.ItemListener;
import net.minecraft.block.Material;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class RotarySieveBlock extends LazyBlockTemplate {
    public RotarySieveBlock(Identifier identifier, Material material, float hardness, BlockSoundGroup blockSounds) {
        super(identifier, material, hardness, blockSounds);
        setTickRandomly(true);
    }

    @Override
    public int getTickRate() {
        return 80;
    }

    @Override
    public void onTick(World world, int x, int y, int z, Random random) {
        world.method_216(x, y, z, this.id, this.getTickRate());
        if (world.getBlockMeta(x, y, z) != 1) {
            return;
        }
        int aboveId = world.getBlockId(x, y + 1, z);
        ItemStack output = null;
        if (aboveId == BlockListener.ironOreGravel.id) {
            output = new ItemStack(ItemListener.ironChunks, 5);
        } else if (aboveId == BlockListener.goldOreGravel.id) {
            output = new ItemStack(ItemListener.goldChunks, 5);
        }
        if (output == null) {
            return;
        }
        world.method_215(x, y, z, 0);
        world.setBlock(x, y + 1, z, 0);
        float randomizerAmplitude = 0.7F;
        double xOffset = (double)(world.field_214.nextFloat() * randomizerAmplitude) + (double)(1.0F - randomizerAmplitude) * 0.5;
        double yOffset = (double)(world.field_214.nextFloat() * randomizerAmplitude) + (double)(1.0F - randomizerAmplitude) * 0.5;
        double zOffset = (double)(world.field_214.nextFloat() * randomizerAmplitude) + (double)(1.0F - randomizerAmplitude) * 0.5;
        ItemEntity itemEntity = new ItemEntity(world, (double)x + xOffset, (double)y + yOffset, (double)z + zOffset, output);
        itemEntity.pickupDelay = 10;
        world.method_210(itemEntity);
    }
}
