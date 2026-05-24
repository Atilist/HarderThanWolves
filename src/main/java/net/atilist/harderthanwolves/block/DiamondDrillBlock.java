package net.atilist.harderthanwolves.block;

import net.atilist.harderthanwolves.recipe.DiamondDrillRecipeRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.kozibrodka.wolves.utils.BlockPosition;
import net.kozibrodka.wolves.utils.MechanicalDevice;
import net.kozibrodka.wolves.utils.UnsortedUtils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.state.StateManager;
import net.modificationstation.stationapi.api.state.property.IntProperty;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class DiamondDrillBlock extends LazyBlockTemplate implements MechanicalDevice {

    public static final IntProperty TYPES = IntProperty.of("types", 0, 1);

    public DiamondDrillBlock(Identifier identifier, Material material, float hardness, BlockSoundGroup blockSounds) {
        super(identifier, material, hardness, blockSounds);
        setTickRandomly(true);
    }

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(TYPES);
    }

    @Override
    @Environment(EnvType.CLIENT)
    public int getRenderType() {
        return 0;
    }

    @Override
    public boolean isFullCube() {
        return false;
    }

    @Override
    public boolean isOpaque() {
        return false;
    }

    @Override
    public void onTick(World world, int i, int j, int k, Random random) {
        if (this.isBlockOn(world, i, j, k)) {
            BlockPosition targetPos = new BlockPosition(i, j, k);
            targetPos.addFacingAsOffset(1);
            drillBlock(world, targetPos.x, targetPos.y, targetPos.z);
        }

    }

    public void setBlockOn(World world, int x, int y, int z) {
        world.setBlockMeta(x, y, z, 1);
    }

    public void setBlockOff(World world, int x, int y, int z) {
        world.setBlockMeta(x, y, z, 0);
    }

    @Override
    public void randomDisplayTick(World world, int i, int j, int k, Random random) {
        if(isBlockOn(world, i, j, k)) {
            for(int counter = 0; counter < 5; counter++) {
                float smokeX = (float)i + random.nextFloat();
                float smokeY = (float)j + random.nextFloat() * 0.5F + 1.0F;
                float smokeZ = (float)k + random.nextFloat();
                world.addParticle("smoke", smokeX, smokeY, smokeZ, 0.0D, 0.0D, 0.0D);
            }
            if(random.nextInt(5) == 0) {
                world.playSound((double)i + 0.5D, (double)j + 0.5D, (double)k + 0.5D, "random.explode", 0.1F + random.nextFloat() * 0.1F, 1.25F + random.nextFloat() * 0.1F);
            }
        }
    }

    public boolean isBlockOn(World world, int i, int j, int k) {
        return world.getBlockMeta(i, j, k) > 0;
    }

    void drillBlock(World world, int i, int j, int k) {
        if (!world.isAir(i, j, k)) {
            int targetId = world.getBlockId(i, j, k);
            ItemStack targetItem = new ItemStack(targetId, 1, world.getBlockMeta(i, j, k));
            boolean drilledBlock = false;
            ItemStack output = DiamondDrillRecipeRegistry.getInstance().getResult(targetItem);
            if (output != null) {
                if (output.count == 0) {
                    output.count = 1;
                }
                for(int iTempCount = 0; iTempCount < output.count; ++iTempCount) {
                    UnsortedUtils.EjectSingleItemWithRandomOffset(world, i, j, k, output.itemId, output.getDamage());
                }
                drilledBlock = true;
            }
            if (drilledBlock) {
                world.playSound((double)i + 0.5, (double)j + 0.5, (double)k + 0.5, "random.explode", 0.2F, 1.25F);
                world.setBlock(i, j, k, 0);
            }
        }
    }

    @Override
    public void neighborUpdate(World world, int i, int j, int k, int iid) {
        world.scheduleBlockUpdate(i, j, k, net.atilist.harderthanwolves.events.init.BlockListener.diamondDrill.id, getTickRate());
    }

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
        return side == 0;
    }

    @Override
    public void powerMachine(World world, int x, int y, int z, int side) {
        world.playSound((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, "random.explode", 0.2F, 1.25F);
        setBlockOn(world, x, y, z);
    }

    @Override
    public void unpowerMachine(World world, int x, int y, int z, int side) {
        world.playSound((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, "random.explode", 0.2F, 1.25F);
        setBlockOff(world, x, y, z);
    }

    @Override
    public boolean isMachinePowered(World world, int i, int i1, int i2) {
        return isBlockOn(world, i, i1, i2);
    }
}
