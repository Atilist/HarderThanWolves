package net.atilist.harderthanwolves.block;

import net.atilist.harderthanwolves.events.init.BlockListener;
import net.atilist.harderthanwolves.events.init.ItemListener;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;

public class EmptyMonsterSiphon extends LazyBlockTemplate {
    public EmptyMonsterSiphon(Identifier identifier, Material material, float hardness, BlockSoundGroup blockSounds) {
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
}
