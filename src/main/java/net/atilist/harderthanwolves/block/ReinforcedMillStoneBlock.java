package net.atilist.harderthanwolves.block;

import net.atilist.harderthanwolves.block.entity.ReinforcedMillStoneBlockEntity;
import net.atilist.harderthanwolves.container.ReinforcedMillStoneScreenHandler;
import net.kozibrodka.wolves.block.AxleBlock;
import net.kozibrodka.wolves.events.BlockListener;
import net.kozibrodka.wolves.utils.BlockPosition;
import net.kozibrodka.wolves.utils.InventoryHandler;
import net.kozibrodka.wolves.utils.MechanicalDevice;
import net.minecraft.block.Block;
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

    public int getTickRate()
    {
        return iMillStoneTickRate;
    }

    public void onPlaced(World world, int i, int j, int k) {
        super.onPlaced(world, i, j, k);
        world.scheduleBlockUpdate(i, j, k, net.atilist.harderthanwolves.events.init.BlockListener.reinforcedMillStone.id, getTickRate());
    }

    public void neighborUpdate(World world, int i, int j, int k, int iid)
    {
        boolean bReceivingPower = IsInputtingMechanicalPower(world, i, j, k);
        if(IsBlockOn(world, i, j, k) != bReceivingPower) {
            world.scheduleBlockUpdate(i, j, k, net.atilist.harderthanwolves.events.init.BlockListener.reinforcedMillStone.id, getTickRate());
        }
    }

    public boolean onUse(World world, int i, int j, int k, PlayerEntity entityplayer)
    {
        ReinforcedMillStoneBlockEntity tileEntityMillStone = (ReinforcedMillStoneBlockEntity)world.getBlockEntity(i, j, k);
        GuiHelper.openGUI(entityplayer, Identifier.of("harderthanwolves:openReinforcedMillStone"), (Inventory) tileEntityMillStone, new ReinforcedMillStoneScreenHandler(entityplayer.inventory, (ReinforcedMillStoneBlockEntity) tileEntityMillStone));
        return true;
    }

    protected BlockEntity createBlockEntity()
    {
        return new ReinforcedMillStoneBlockEntity();
    }

    public void onTick(World world, int i, int j, int k, Random random)
    {
        boolean bReceivingPower = IsInputtingMechanicalPower(world, i, j, k);
        boolean bOn = IsBlockOn(world, i, j, k);
        if(bOn != bReceivingPower)
        {
            SetBlockOn(world, i, j, k, !bOn);
        }
    }

    public void randomDisplayTick(World world, int i, int j, int k, Random random)
    {
        if(IsBlockOn(world, i, j, k))
        {
            EmitMillingParticles(world, i, j, k, random);
            if(random.nextInt(5) == 0)
            {
                world.playSound((double)i + 0.5D, (double)j + 0.5D, (double)k + 0.5D, "random.explode", 0.1F + random.nextFloat() * 0.1F, 1.25F + random.nextFloat() * 0.1F);
            }
        }
    }

    public void onBreak(World world, int i, int j, int k)
    {
        InventoryHandler.ejectInventoryContents(world, i, j, k, (Inventory)world.getBlockEntity(i, j, k));
        super.onBreak(world, i, j, k);
    }

    public boolean IsBlockOn(World world, int i, int j, int k)
    {
        return world.getBlockMeta(i, j, k) > 0;
    }

    public void SetBlockOn(World world, int i, int j, int k, boolean bOn)
    {
        if(bOn)
        {
            world.setBlockMeta(i, j, k, 1);
            world.blockUpdateEvent(i, j, k);
        } else
        {
            world.setBlockMeta(i, j, k, 0);
            world.blockUpdateEvent(i, j, k);
        }
    }

    void EmitMillingParticles(World world, int i, int j, int k, Random random)
    {
        for(int counter = 0; counter < 5; counter++)
        {
            float smokeX = (float)i + random.nextFloat();
            float smokeY = (float)j + random.nextFloat() * 0.5F + 1.0F;
            float smokeZ = (float)k + random.nextFloat();
            world.addParticle("smoke", smokeX, smokeY, smokeZ, 0.0D, 0.0D, 0.0D);
        }

    }

    public boolean CanOutputMechanicalPower()
    {
        return false;
    }

    public boolean CanInputMechanicalPower()
    {
        return true;
    }

    public boolean IsInputtingMechanicalPower(World world, int i, int j, int k)
    {
        for(int iFacing = 0; iFacing <= 1; iFacing++)
        {
            BlockPosition targetPos = new BlockPosition(i, j, k);
            targetPos.AddFacingAsOffset(iFacing);
            int iTargetid = world.getBlockId(targetPos.i, targetPos.j, targetPos.k);
            if(iTargetid != BlockListener.axleBlock.id)
            {
                continue;
            }
            AxleBlock axleBlock = (AxleBlock)BlockListener.axleBlock;
            if(axleBlock.IsAxleOrientedTowardsFacing(world, targetPos.i, targetPos.j, targetPos.k, iFacing) && axleBlock.GetPowerLevel(world, targetPos.i, targetPos.j, targetPos.k) > 0)
            {
                return true;
            }
        }

        for(int iFacing = 2; iFacing <= 5; iFacing++)
        {
            BlockPosition targetPos = new BlockPosition(i, j, k);
            targetPos.AddFacingAsOffset(iFacing);
            int iTargetid = world.getBlockId(targetPos.i, targetPos.j, targetPos.k);
            if(iTargetid != BlockListener.handCrank.id)
            {
                continue;
            }
            Block targetBlock = Block.BLOCKS[iTargetid];
            MechanicalDevice device = (MechanicalDevice)targetBlock;
            if(device.IsOutputtingMechanicalPower(world, targetPos.i, targetPos.j, targetPos.k))
            {
                return true;
            }
        }

        return false;
    }

    public boolean IsOutputtingMechanicalPower(World world, int i, int j, int l)
    {
        return false;
    }

    private static int iMillStoneTickRate = 10;
}
