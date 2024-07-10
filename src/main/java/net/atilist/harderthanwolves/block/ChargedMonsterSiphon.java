package net.atilist.harderthanwolves.block;

import net.atilist.harderthanwolves.events.init.BlockListener;
import net.atilist.harderthanwolves.events.init.ItemListener;
import net.minecraft.block.material.Material;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;

public class ChargedMonsterSiphon extends LazyBlockTemplate {
    public ChargedMonsterSiphon(Identifier identifier, Material material, float hardness, BlockSoundGroup blockSounds) {
        super(identifier, material, hardness, blockSounds);
    }

    @Override
    public boolean onUse(World world, int x, int y, int z, PlayerEntity player) {
        world.setBlock(x, y, z, BlockListener.emptyMonsterSiphon.id);
        world.spawnEntity(new ItemEntity(world, (double)x + 0.5, (double)y + 1, (double)z + 0.5, new ItemStack(ItemListener.chargedMysticalRock, 1)));
        return true;
    }
}
