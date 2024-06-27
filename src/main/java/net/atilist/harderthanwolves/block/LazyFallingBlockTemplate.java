package net.atilist.harderthanwolves.block;

import net.minecraft.sound.BlockSoundGroup;
import net.modificationstation.stationapi.api.template.block.TemplateSandBlock;
import net.modificationstation.stationapi.api.util.Identifier;

public class LazyFallingBlockTemplate extends TemplateSandBlock {

    int texture;

    public LazyFallingBlockTemplate(Identifier identifier, float hardness, BlockSoundGroup blockSounds) {
        super(identifier, 0);
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
}
