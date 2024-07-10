package net.atilist.harderthanwolves.block;

import net.atilist.harderthanwolves.events.init.BlockListener;
import net.atilist.harderthanwolves.events.init.ItemListener;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class LoadedMonsterSiphon extends LazyBlockTemplate {
    public LoadedMonsterSiphon(Identifier identifier, Material material, float hardness, BlockSoundGroup blockSounds) {
        super(identifier, material, hardness, blockSounds);
        setTickRandomly(true);
    }

    @Override
    public int getTickRate() {
        return 10;
    }

    @Override
    public boolean onUse(World world, int x, int y, int z, PlayerEntity player) {
        world.setBlock(x, y, z, BlockListener.emptyMonsterSiphon.id);
        world.spawnEntity(new ItemEntity(world, (double)x + 0.5, (double)y + 1, (double)z + 0.5, new ItemStack(ItemListener.mysticalRock, 1)));
        return true;
    }

    @Override
    public void onTick(World world, int x, int y, int z, Random random) {
        world.scheduleBlockUpdate(x, y, z, this.id, getTickRate());
        if (world.getBlockId(x, y - 1, z) != Block.SPAWNER.id) {
            return;
        }
        int meta = world.getBlockMeta(x, y, z);
        if (meta < 15) {
            world.setBlockMeta(x, y, z, meta + 1);
            return;
        }
        world.setBlockMeta(x, y, z, 0);
        world.setBlock(x, y, z, BlockListener.chargedMonsterSiphon.id);
    }
}
