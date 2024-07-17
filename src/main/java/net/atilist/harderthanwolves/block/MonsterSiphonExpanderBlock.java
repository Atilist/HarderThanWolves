package net.atilist.harderthanwolves.block;

import net.atilist.harderthanwolves.events.init.BlockListener;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class MonsterSiphonExpanderBlock extends LazyBlockTemplate {
    public MonsterSiphonExpanderBlock(Identifier identifier, Material material, float hardness, BlockSoundGroup blockSounds) {
        super(identifier, material, hardness, blockSounds);
        this.setTickRandomly(true);
    }

    @Override
    public void onTick(World world, int x, int y, int z, Random random) {
        for (int xOffset = -2; xOffset <= 2; xOffset++) {
            for (int zOffset = -2; zOffset <= 2; zOffset++) {
                if (world.getBlockId(x + xOffset, y, z + zOffset) == Block.SPAWNER.id) {
                    world.setBlock(x, y, z, BlockListener.activeMonsterSiphonExpander.id);
                    return;
                }
            }
        }
    }
}
