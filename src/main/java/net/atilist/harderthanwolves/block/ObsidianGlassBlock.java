package net.atilist.harderthanwolves.block;

import net.atilist.harderthanwolves.events.init.ItemListener;
import net.minecraft.block.material.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class ObsidianGlassBlock extends LazyBlockTemplate {
    public ObsidianGlassBlock(Identifier identifier, Material material, float hardness, BlockSoundGroup blockSounds) {
        super(identifier, material, hardness, blockSounds);
    }

    @Override
    public boolean isOpaque() {
        return false;
    }

    @Override
    public int getDroppedItemId(int blockMeta, Random random) {
        return ItemListener.obsidianDust.id;
    }
}
