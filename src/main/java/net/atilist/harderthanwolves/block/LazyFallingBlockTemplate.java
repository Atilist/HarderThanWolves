package net.atilist.harderthanwolves.block;

import net.minecraft.block.material.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.template.block.TemplateSandBlock;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class LazyFallingBlockTemplate extends TemplateBlock {

    int texture;

    public LazyFallingBlockTemplate(Identifier identifier, float hardness, BlockSoundGroup blockSounds) {
        super(identifier, Material.SAND);
        setTranslationKey(identifier.namespace, identifier.path);
        setHardness(hardness);
        setSoundGroup(blockSounds);
    }

    public void specifyTextures(int texture) {
        this.texture = texture;
    }

    @Override
    public int getTexture(int i, int j) {
        return texture;
    }

    @Override
    public void onTick(World world, int x, int y, int z, Random random) {
        int blockId = world.getBlockId(x, y, z);
        if (world.getBlockId(x, y - 1, z) == 0) {
            world.setBlock(x, y, z, 0);
            world.setBlock(x, y - 1, z, blockId);
            world.scheduleBlockUpdate(x, y - 1, z, blockId, getTickRate());
        }
    }

    @Override
    public void onPlaced(World world, int x, int y, int z) {
        super.onPlaced(world, x, y, z);
        int blockId = world.getBlockId(x, y, z);
        world.scheduleBlockUpdate(x, y, z, blockId, getTickRate());
    }

    @Override
    public void neighborUpdate(World world, int x, int y, int z, int id) {
        super.neighborUpdate(world, x, y, z, id);
        int blockId = world.getBlockId(x, y, z);
        world.scheduleBlockUpdate(x, y, z, blockId, getTickRate());
    }

    @Override
    public int getTickRate() {
        return 10;
    }
}
