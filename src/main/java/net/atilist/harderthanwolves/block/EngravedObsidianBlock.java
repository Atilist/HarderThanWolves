package net.atilist.harderthanwolves.block;

import net.atilist.harderthanwolves.events.init.BlockListener;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class EngravedObsidianBlock extends LazyBlockTemplate {
    public EngravedObsidianBlock(Identifier identifier, Material material, float hardness, BlockSoundGroup blockSounds) {
        super(identifier, material, hardness, blockSounds);
        setTickRandomly(true);
    }

    @Override
    public void onTick(World world, int x, int y, int z, Random random) {
        super.onTick(world, x, y, z, random);
        for (int xOffset = -2; xOffset <= 2; xOffset++) {
            for (int zOffset = -2; zOffset <= 2; zOffset++) {
                if (world.getBlockId(x + xOffset, y - 1, z + zOffset) != BlockListener.obsidianGlass.id
                    && world.getBlockId(x + xOffset, y - 2, z + zOffset) != Block.LAVA.id) {
                    return;
                }
            }
        }
        world.setBlock(x, y, z, BlockListener.activeEngravedObsidian.id);
        world.blockUpdateEvent(x, y, z);
    }
}
