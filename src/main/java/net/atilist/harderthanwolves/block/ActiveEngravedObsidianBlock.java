package net.atilist.harderthanwolves.block;

import net.atilist.harderthanwolves.events.init.BlockListener;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class ActiveEngravedObsidianBlock extends LazyBlockTemplate {
    public ActiveEngravedObsidianBlock(Identifier identifier, Material material, float hardness, BlockSoundGroup blockSounds) {
        super(identifier, material, hardness, blockSounds);
        setTickRandomly(true);
    }

    @Override
    public void onTick(World world, int x, int y, int z, Random random) {
        super.onTick(world, x, y, z, random);
        boolean failedStructureRequirement = false;
        for (int xOffset = -2; xOffset <= 2; xOffset++) {
            for (int zOffset = -2; zOffset <= 2; zOffset++) {
                if (world.getBlockId(x + xOffset, y - 1, z + zOffset) != BlockListener.obsidianGlass.id
                        && world.getBlockId(x + xOffset, y - 2, z + zOffset) != Block.LAVA.id) {
                    failedStructureRequirement = true;
                    break;
                }
                if (failedStructureRequirement) {
                    break;
                }
            }
        }
        if (!failedStructureRequirement) {
            return;
        }
        for (int xOffset = -1; xOffset <= 1; xOffset++) {
            for (int zOffset = -1; zOffset <= 1; zOffset++) {
                if (world.getBlockId(x + xOffset, y + 1, z + zOffset) == Block.NETHER_PORTAL.id) {
                    world.setBlock(x + xOffset, y + 1, z + zOffset, 0);
                    world.blockUpdateEvent(x + xOffset, y + 1, z + zOffset);
                }
            }
        }
        world.setBlock(x, y, z, BlockListener.engravedObsidian.id);
        world.blockUpdateEvent(x, y, z);
    }
}
