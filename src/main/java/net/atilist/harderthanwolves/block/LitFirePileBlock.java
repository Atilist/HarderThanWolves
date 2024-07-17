package net.atilist.harderthanwolves.block;

import net.atilist.harderthanwolves.events.init.BlockListener;
import net.kozibrodka.wolves.events.ItemListener;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class LitFirePileBlock extends LazyBlockTemplate {
    public LitFirePileBlock(Identifier identifier, Material material, float hardness, BlockSoundGroup blockSounds) {
        super(identifier, material, hardness, blockSounds);
        setTickRandomly(true);
    }

    @Override
    public Block setLuminance(float fractionalValue) {
        return super.setLuminance(0.5F);
    }

    @Override
    public void onTick(World world, int x, int y, int z, Random random) {
        int meta = world.getBlockMeta(x, y, z);
        if (world.getBlockId(x, y + 1, z) != Block.FIRE.id) {
            return;
        }
        if (meta < 3) {
            world.setBlockMeta(x, y, z, meta + 1);
        } else {
            world.setBlock(x, y, z, BlockListener.depletedFirePile.id);
        }
    }

    @Override
    public boolean onUse(World world, int x, int y, int z, PlayerEntity player) {
        ItemStack item = player.getHand();
        if (item == null) {
            return false;
        }
        if (item.itemId != Block.TORCH.id && item.itemId != Item.FLINT_AND_STEEL.id) {
            return false;
        }
        if (world.getBlockId(x, y + 1, z) == 0) {
            world.playSound((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, "fire.ignite", 1.0F, 0.8F);
            world.setBlock(x, y + 1, z, Block.FIRE.id);
            if (item.itemId == Item.FLINT_AND_STEEL.id) {
                item.damage(1, player);
            }
        }
        return true;
    }

    @Override
    public int getDroppedItemId(int blockMeta, Random random) {
        return ItemListener.coalDust.id;
    }
}
