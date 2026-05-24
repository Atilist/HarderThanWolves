package net.atilist.harderthanwolves.block;

import net.atilist.harderthanwolves.block.entity.ReinforcedMillStoneBlockEntity;
import net.atilist.harderthanwolves.container.ReinforcedMillStoneScreenHandler;
import net.kozibrodka.wolves.utils.InventoryHandler;
import net.kozibrodka.wolves.utils.MechanicalDevice;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.gui.screen.container.GuiHelper;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class ReinforcedMillStoneBlock extends LazyBlockWithEntityTemplate implements MechanicalDevice {
    public ReinforcedMillStoneBlock(Identifier identifier, Material material, float hardness, BlockSoundGroup blockSounds) {
        super(identifier, material, hardness, blockSounds);
    }

    public int getTickRate() {
        return iMillStoneTickRate;
    }

    public boolean onUse(World world, int i, int j, int k, PlayerEntity entityplayer) {
        ReinforcedMillStoneBlockEntity tileEntityMillStone = (ReinforcedMillStoneBlockEntity)world.getBlockEntity(i, j, k);
        GuiHelper.openGUI(entityplayer, Identifier.of("harderthanwolves:openReinforcedMillStone"), (Inventory) tileEntityMillStone, new ReinforcedMillStoneScreenHandler(entityplayer.inventory, (ReinforcedMillStoneBlockEntity) tileEntityMillStone));
        return true;
    }

    protected BlockEntity createBlockEntity() {
        return new ReinforcedMillStoneBlockEntity();
    }

    public void randomDisplayTick(World world, int i, int j, int k, Random random) {
        if(isBlockOn(world, i, j, k)) {
            EmitMillingParticles(world, i, j, k, random);
            if(random.nextInt(5) == 0) {
                world.playSound((double)i + 0.5D, (double)j + 0.5D, (double)k + 0.5D, "random.explode", 0.1F + random.nextFloat() * 0.1F, 1.25F + random.nextFloat() * 0.1F);
            }
        }
    }

    public void onBreak(World world, int i, int j, int k) {
        InventoryHandler.ejectInventoryContents(world, i, j, k, (Inventory)world.getBlockEntity(i, j, k));
        super.onBreak(world, i, j, k);
    }

    public boolean isBlockOn(World world, int x, int y, int z) {
        return world.getBlockMeta(x, y, z) > 0;
    }

    public void toggleBlockActivity(World world, int x, int y, int z, boolean on) {
        if(on) {
            world.setBlockMeta(x, y, z, 1);
            world.blockUpdateEvent(x, y, z);
        } else {
            world.setBlockMeta(x, y, z, 0);
            world.blockUpdateEvent(x, y, z);
        }
    }

    void EmitMillingParticles(World world, int i, int j, int k, Random random) {
        for(int counter = 0; counter < 5; counter++) {
            float smokeX = (float)i + random.nextFloat();
            float smokeY = (float)j + random.nextFloat() * 0.5F + 1.0F;
            float smokeZ = (float)k + random.nextFloat();
            world.addParticle("smoke", smokeX, smokeY, smokeZ, 0.0D, 0.0D, 0.0D);
        }
    }

    private static int iMillStoneTickRate = 10;

    @Override
    public boolean canOutputMechanicalPower() {
        return false;
    }

    @Override
    public boolean canInputMechanicalPower() {
        return true;
    }

    @Override
    public boolean canInputMechanicalPower(World world, int x, int y, int z, int side) {
        return side == 0 || side == 1;
    }

    @Override
    public void powerMachine(World world, int x, int y, int z, int i3) {
        toggleBlockActivity(world, x, y, z, true);
    }

    @Override
    public void unpowerMachine(World world, int x, int y, int z, int i3) {
        toggleBlockActivity(world, x, y, z, false);
    }

    @Override
    public boolean isMachinePowered(World world, int x, int y, int z) {
        return isBlockOn(world, x, y, z);
    }
}
