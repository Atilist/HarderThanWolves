package net.atilist.harderthanwolves.block;

import net.atilist.harderthanwolves.recipe.RotarySieveRecipeRegistry;
import net.minecraft.block.material.Material;
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
        world.scheduleBlockUpdate(x, y, z, this.id, this.getTickRate());
        if (world.getBlockMeta(x, y, z) != 1) {
            return;
        }
        int aboveId = world.getBlockId(x, y + 1, z);
        ItemStack input = new ItemStack(aboveId, 1, 0);
        ItemStack[] output = RotarySieveRecipeRegistry.getInstance().getResult(input);
        if (output == null) {
            return;
        }
        world.setBlockMeta(x, y, z, 0);
        world.setBlock(x, y + 1, z, 0);
        float randomizerAmplitude = 0.7F;
        double xOffset = (double)(world.random.nextFloat() * randomizerAmplitude) + (double)(1.0F - randomizerAmplitude) * 0.5;
        double yOffset = (double)(world.random.nextFloat() * randomizerAmplitude) + (double)(1.0F - randomizerAmplitude) * 0.5;
        double zOffset = (double)(world.random.nextFloat() * randomizerAmplitude) + (double)(1.0F - randomizerAmplitude) * 0.5;
        ItemEntity itemEntity = new ItemEntity(world, (double)x + xOffset, (double)y + yOffset, (double)z + zOffset, output[0].copy());
        itemEntity.pickupDelay = 10;
        world.spawnEntity(itemEntity);
    }
}
