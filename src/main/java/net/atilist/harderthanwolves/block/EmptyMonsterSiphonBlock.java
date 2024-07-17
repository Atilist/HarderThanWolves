package net.atilist.harderthanwolves.block;

import net.atilist.harderthanwolves.events.init.BlockListener;
import net.atilist.harderthanwolves.events.init.ItemListener;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;

public class EmptyMonsterSiphonBlock extends LazyBlockTemplate {
    public EmptyMonsterSiphonBlock(Identifier identifier, Material material, float hardness, BlockSoundGroup blockSounds) {
        super(identifier, material, hardness, blockSounds);
    }

    @Override
    public boolean onUse(World world, int x, int y, int z, PlayerEntity player) {
        ItemStack playerItem = player.getHand();
        if (playerItem == null) {
            return false;
        }
        if (playerItem.itemId != ItemListener.mysticalRock.id) {
            return false;
        }
        playerItem.count--;
        world.setBlock(x, y, z, BlockListener.loadedMonsterSiphon.id);
        return true;
    }

    @Override
    public Box getCollisionShape(World world, int x, int y, int z) {
        return Box.createCached((double)x + this.minX, (double)y + this.minY, (double)z + this.minZ, (double)x + this.maxX, (double)y + 0.99, (double)z + this.maxZ);
    }

    @Override
    public void onEntityCollision(World world, int x, int y, int z, Entity entity) {
        if (entity == null) {
            return;
        }
        if (!(entity instanceof ItemEntity itemEntity)) {
            return;
        }
        if (itemEntity.stack == null) {
            return;
        }
        if (itemEntity.stack.itemId != ItemListener.mysticalRock.id) {
            return;
        }
        world.setBlock(x, y, z, BlockListener.loadedMonsterSiphon.id);
        itemEntity.stack.count--;
        if (itemEntity.stack.count < 1) {
            itemEntity.markDead();
        }
    }
}
