package net.atilist.harderthanwolves.block;

import net.atilist.harderthanwolves.events.init.BlockListener;
import net.atilist.harderthanwolves.events.init.ItemListener;
import net.minecraft.block.material.Material;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class ChargedMonsterSiphonBlock extends LazyBlockTemplate {
    public ChargedMonsterSiphonBlock(Identifier identifier, Material material, float hardness, BlockSoundGroup blockSounds) {
        super(identifier, material, hardness, blockSounds);
        this.setTickRandomly(true);
    }

    @Override
    public boolean onUse(World world, int x, int y, int z, PlayerEntity player) {
        world.setBlock(x, y, z, BlockListener.emptyMonsterSiphon.id);
        world.spawnEntity(new ItemEntity(world, (double)x + 0.5, (double)y + 1, (double)z + 0.5, new ItemStack(ItemListener.chargedMysticalRock, 1)));
        return true;
    }

    @Override
    public Box getCollisionShape(World world, int x, int y, int z) {
        return Box.createCached((double)x + this.minX, (double)y + this.minY, (double)z + this.minZ, (double)x + this.maxX, (double)y + 0.99, (double)z + this.maxZ);
    }

    @Override
    public void onTick(World world, int x, int y, int z, Random random) {
        if ((world.isEmittingRedstonePower(x, y, z) || world.isEmittingRedstonePower(x, y + 1, z)) && world.getBlockId(x, y - 2, z) == 0) {
            world.setBlock(x, y, z, BlockListener.emptyMonsterSiphon.id);
            world.spawnEntity(new ItemEntity(world, (double)x + 0.5, (double)y - 1.25, (double)z + 0.5, new ItemStack(ItemListener.chargedMysticalRock, 1)));
        }
    }
}
