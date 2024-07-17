package net.atilist.harderthanwolves.block;

import net.atilist.harderthanwolves.events.init.BlockListener;
import net.kozibrodka.wolves.events.ItemListener;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class DepletedFirePileBlock extends LazyBlockTemplate {
    public DepletedFirePileBlock(Identifier identifier, Material material, float hardness, BlockSoundGroup blockSounds) {
        super(identifier, material, hardness, blockSounds);
    }

    @Override
    public boolean onUse(World world, int x, int y, int z, PlayerEntity player) {
        ItemStack item = player.getHand();
        if (item == null) {
            return false;
        }
        if (item.itemId != Block.PLANKS.id) {
            return false;
        }
        world.playSound((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, "step.wood", 1.0F, 0.8F);
        world.setBlock(x, y, z, BlockListener.firePile.id);
        item.count--;
        return true;
    }

    @Override
    public int getDroppedItemId(int blockMeta, Random random) {
        return ItemListener.coalDust.id;
    }
}
