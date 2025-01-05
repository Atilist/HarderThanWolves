package net.atilist.harderthanwolves.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;

public class ChiseledObsidianBlock extends LazyBlockTemplate {
    public ChiseledObsidianBlock(Identifier identifier, Material material, float hardness, BlockSoundGroup blockSounds) {
        super(identifier, material, hardness, blockSounds);
    }

    @Override
    public void onBreak(World world, int x, int y, int z) {
        super.onBreak(world, x, y, z);
        for (int xOffset = -1; xOffset <= 1; xOffset++) {
            for (int yOffset = -1; yOffset <= 1; yOffset++) {
                for (int zOffset = -1; zOffset <= 1; zOffset++) {
                    if (world.getBlockId(x + xOffset, y + yOffset, z + zOffset) == Block.NETHER_PORTAL.id) {
                        world.setBlock(x + xOffset, y + yOffset, z + zOffset, 0);
                        world.blockUpdateEvent(x + xOffset, y + yOffset, z + zOffset);
                    }
                }
            }
        }
    }
}
