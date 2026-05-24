package net.atilist.harderthanwolves.block;

import net.atilist.harderthanwolves.events.init.TextureListener;
import net.atilist.harderthanwolves.recipe.ReinforcedSawRecipeRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.EnvironmentInterface;
import net.kozibrodka.wolves.block.OmniSlabBlock;
import net.kozibrodka.wolves.events.BlockListener;
import net.kozibrodka.wolves.events.ItemListener;
import net.kozibrodka.wolves.utils.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.Box;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.client.model.block.BlockWithInventoryRenderer;
import net.modificationstation.stationapi.api.client.model.block.BlockWithWorldRenderer;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.List;
import java.util.Random;

@EnvironmentInterface(value= EnvType.CLIENT, itf=BlockWithWorldRenderer.class)
@EnvironmentInterface(value=EnvType.CLIENT, itf=BlockWithInventoryRenderer.class)
public class ReinforcedSawBlock extends LazyBlockTemplate implements MechanicalDevice, RotatableBlock, BlockWithWorldRenderer, BlockWithInventoryRenderer {
    private static final int SAW_TICK_RATE = 10;

    public ReinforcedSawBlock(Identifier identifier, Material material, float hardness, BlockSoundGroup blockSounds) {
        super(identifier, material, hardness, blockSounds);
        this.setTickRandomly(true);
        this.setBoundingBox(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
    }

    public int getTextureId(BlockView blockAccess, int i, int j, int k, int iSide) {
        int iFacing = this.getFacing(blockAccess, i, j, k);
        return iSide != iFacing ? TextureListener.reinforcedSawSide : TextureListener.reinforcedSawFace;
    }

    public int getTexture(int iSide, int meta) {
        return iSide != 1 ? TextureListener.reinforcedSawSide : TextureListener.reinforcedSawFace;
    }

    public int getTickRate() {
        return SAW_TICK_RATE;
    }

    public void onPlaced(World world, int i, int j, int k, int iFacing) {
        this.setFacing(world, i, j, k, UnsortedUtils.getOppositeFacing(iFacing));
    }

    public void onPlaced(World world, int i, int j, int k, LivingEntity entityLiving) {
        int iFacing = UnsortedUtils.ConvertPlacingEntityOrientationToBlockFacing(entityLiving);
        this.setFacing(world, i, j, k, iFacing);
    }

    public void onPlaced(World world, int i, int j, int k) {
        super.onPlaced(world, i, j, k);
        world.scheduleBlockUpdate(i, j, k, this.id, this.getTickRate());
    }

    public boolean isOpaque() {
        return false;
    }

    public boolean isFullCube() {
        return false;
    }

    public Box getCollisionShape(World world, int i, int j, int k) {
        int iFacing = this.getFacing(world, i, j, k);
        return switch (iFacing) {
            case 0 ->
                    Box.createCached((double) ((float) i), (double) ((float) j + 1.0F - 0.75F), (double) ((float) k), (double) ((float) i + 1.0F), (double) ((float) j + 1.0F), (double) ((float) k + 1.0F));
            case 1 ->
                    Box.createCached((double) ((float) i), (double) ((float) j), (double) ((float) k), (double) ((float) i + 1.0F), (double) ((float) j + 0.75F), (double) ((float) k + 1.0F));
            case 2 ->
                    Box.createCached((double) ((float) i), (double) ((float) j), (double) ((float) k + 1.0F - 0.75F), (double) ((float) i + 1.0F), (double) ((float) j + 1.0F), (double) ((float) k + 1.0F));
            case 3 ->
                    Box.createCached((double) ((float) i), (double) ((float) j), (double) ((float) k), (double) ((float) i + 1.0F), (double) ((float) j + 1.0F), (double) ((float) k + 0.75F));
            case 4 ->
                    Box.createCached((double) ((float) i + 1.0F - 0.75F), (double) ((float) j), (double) ((float) k), (double) ((float) i + 1.0F), (double) ((float) j + 1.0F), (double) ((float) k + 1.0F));
            default ->
                    Box.createCached((double) ((float) i), (double) ((float) j), (double) ((float) k), (double) ((float) i + 0.75F), (double) ((float) j + 1.0F), (double) ((float) k + 1.0F));
        };
    }

    public void updateBoundingBox(BlockView iblockaccess, int i, int j, int k) {
        int iFacing = this.getFacing(iblockaccess, i, j, k);
        switch (iFacing) {
            case 0 -> this.setBoundingBox(0.0F, 0.25F, 0.0F, 1.0F, 1.0F, 1.0F);
            case 1 -> this.setBoundingBox(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
            case 2 -> this.setBoundingBox(0.0F, 0.0F, 0.25F, 1.0F, 1.0F, 1.0F);
            case 3 -> this.setBoundingBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.75F);
            case 4 -> this.setBoundingBox(0.25F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            default -> this.setBoundingBox(0.0F, 0.0F, 0.0F, 0.75F, 1.0F, 1.0F);
        }

    }

    public void setupRenderBoundingBox() {
        this.setBoundingBox(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
    }

    public void neighborUpdate(World world, int i, int j, int k, int id) {
        if (id != BlockListener.axleBlock.id && id != BlockListener.handCrank.id) {
            world.scheduleBlockUpdate(i, j, k, this.id, this.getTickRate() + world.random.nextInt(6));
        }
    }

    public void onTick(World world, int x, int y, int z, Random random) {
        if (this.isBlockOn(world, x, y, z)) {
            int iFacing = this.getFacing(world, x, y, z);
            BlockPosition targetPos = new BlockPosition(x, y, z);
            targetPos.addFacingAsOffset(iFacing);
            if (!this.attemptToSawBlock(world, targetPos.x, targetPos.y, targetPos.z, random)) {
                this.breakSaw(world, x, y, z);
            }
        }

    }

    public void randomDisplayTick(World world, int i, int j, int k, Random random) {
        if (this.isBlockOn(world, i, j, k)) {
            this.emitSawParticles(world, i, j, k, random);
        }

    }

    public void onEntityCollision(World world, int i, int j, int k, Entity entity) {
        if (this.isBlockOn(world, i, j, k) && entity instanceof LivingEntity) {
            int iFacing = this.getFacing(world, i, j, k);
            float fHalfLength = 0.3125F;
            float fHalfWidth = 0.0078125F;
            float fBlockHeight = 0.25F;
            Box sawBox;
            switch (iFacing) {
                case 0 -> sawBox = Box.createCached((double)(0.5F - fHalfLength), 0.0, (double)(0.5F - fHalfWidth), (double)(0.5F + fHalfLength), (double)fBlockHeight, (double)(0.5F + fHalfWidth));
                case 1 -> sawBox = Box.createCached((double)(0.5F - fHalfLength), (double)(1.0F - fBlockHeight), (double)(0.5F - fHalfWidth), (double)(0.5F + fHalfLength), 1.0, (double)(0.5F + fHalfWidth));
                case 2 -> sawBox = Box.createCached((double)(0.5F - fHalfLength), (double)(0.5F - fHalfWidth), 0.0, (double)(0.5F + fHalfLength), (double)(0.5F + fHalfWidth), (double)fBlockHeight);
                case 3 -> sawBox = Box.createCached((double)(0.5F - fHalfLength), (double)(0.5F - fHalfWidth), (double)(1.0F - fBlockHeight), (double)(0.5F + fHalfLength), (double)(0.5F + fHalfWidth), 1.0);
                case 4 -> sawBox = Box.createCached(0.0, (double)(0.5F - fHalfWidth), (double)(0.5F - fHalfLength), (double)fBlockHeight, (double)(0.5F + fHalfWidth), (double)(0.5F + fHalfLength));
                default -> sawBox = Box.createCached((double)(1.0F - fBlockHeight), (double)(0.5F - fHalfWidth), (double)(0.5F - fHalfLength), 1.0, (double)(0.5F + fHalfWidth), (double)(0.5F + fHalfLength));
            }

            sawBox = sawBox.offset((double)i, (double)j, (double)k);
            List collisionList = null;
            collisionList = world.collectEntitiesByClass(LivingEntity.class, sawBox);
            if (collisionList != null && collisionList.size() > 0) {
                for(int iTempListIndex = 0; iTempListIndex < collisionList.size(); ++iTempListIndex) {
                    LivingEntity tempTargetEntity = (LivingEntity)collisionList.get(iTempListIndex);
                    if (tempTargetEntity.damage((Entity)null, 4)) {
                        world.playSound((double)i + 0.5, (double)j + 0.5, (double)k + 0.5, "random.explode", 0.2F, 1.25F);

                        this.emitBloodParticles(world, i, j, k, world.random);
                    }
                }
            }
        }

    }

    @Override
    public int getFacing(BlockView iBlockAccess, int i, int j, int k) {
        return iBlockAccess.getBlockMeta(i, j, k) & 7;
    }

    @Override
    public void setFacing(World world, int i, int j, int k, int iFacing) {
        int iMetaData = world.getBlockMeta(i, j, k) & -8;
        iMetaData |= iFacing;
        world.setBlockMeta(i, j, k, iMetaData);
    }

    @Override
    public boolean canRotate(BlockView iBlockAccess, int i, int j, int k) {
        int iFacing = this.getFacing(iBlockAccess, i, j, k);
        return iFacing != 0;
    }

    @Override
    public boolean canTransmitRotation(BlockView iBlockAccess, int i, int j, int k) {
        int iFacing = this.getFacing(iBlockAccess, i, j, k);
        return iFacing != 0 && iFacing != 1;
    }

    @Override
    public void rotate(World world, int i, int j, int k, boolean bReverse) {
        int iFacing = this.getFacing(world, i, j, k);
        int iNewFacing = UnsortedUtils.RotateFacingAroundJ(iFacing, bReverse);
        if (iNewFacing != iFacing) {
            this.setFacing(world, i, j, k, iNewFacing);
            world.setBlocksDirty(i, j, k, i, j, k);
            world.scheduleBlockUpdate(i, j, k, this.id, this.getTickRate());
            world.blockUpdate(i, j, k, this.id);
        }

        UnsortedUtils.DestroyHorizontallyAttachedAxles(world, i, j, k);
    }

    public boolean isBlockOn(BlockView blockView, int x, int y, int z) {
        return (blockView.getBlockMeta(x, y, z) & 8) > 0;
    }

    public void toggleBlockActivity(World world, int x, int y, int z, boolean on) {
        int iMetaData = world.getBlockMeta(x, y, z) & 7;
        if (on) {
            iMetaData |= 8;
        }

        world.setBlockMeta(x, y, z, iMetaData);
        world.blockUpdateEvent(x, y, z);
    }

    void emitSawParticles(World world, int x, int y, int z, Random random) {
        int iFacing = this.getFacing(world, x, y, z);
        float fBladeXPos = (float)x;
        float fBladeYPos = (float)y;
        float fBladeZPos = (float)z;
        float fBladeXExtent = 0.0F;
        float fBladeZExtent = 0.0F;
        switch (iFacing) {
            case 0:
                fBladeXPos += 0.5F;
                fBladeZPos += 0.5F;
                fBladeXExtent = 1.0F;
                break;
            case 1:
                fBladeXPos += 0.5F;
                fBladeZPos += 0.5F;
                ++fBladeYPos;
                fBladeXExtent = 1.0F;
                break;
            case 2:
                fBladeXPos += 0.5F;
                fBladeYPos += 0.5F;
                fBladeXExtent = 1.0F;
                break;
            case 3:
                fBladeXPos += 0.5F;
                fBladeYPos += 0.5F;
                ++fBladeZPos;
                fBladeXExtent = 1.0F;
                break;
            case 4:
                fBladeYPos += 0.5F;
                fBladeZPos += 0.5F;
                fBladeZExtent = 1.0F;
                break;
            default:
                fBladeYPos += 0.5F;
                fBladeZPos += 0.5F;
                ++fBladeXPos;
                fBladeZExtent = 1.0F;
        }

        for(int counter = 0; counter < 5; ++counter) {
            float smokeX = fBladeXPos + (random.nextFloat() - 0.5F) * fBladeXExtent;
            float smokeY = fBladeYPos + random.nextFloat() * 0.1F;
            float smokeZ = fBladeZPos + (random.nextFloat() - 0.5F) * fBladeZExtent;
            world.addParticle("smoke", (double)smokeX, (double)smokeY, (double)smokeZ, 0.0, 0.0, 0.0);
        }

    }

    void emitBloodParticles(World world, int x, int y, int z, Random random) {
        int iFacing = this.getFacing(world, x, y, z);
        BlockPosition targetPos = new BlockPosition(x, y, z);
        targetPos.addFacingAsOffset(iFacing);

        for(int counter = 0; counter < 10; ++counter) {
            float smokeX = (float)targetPos.x + random.nextFloat();
            float smokeY = (float)targetPos.y + random.nextFloat();
            float smokeZ = (float)targetPos.z + random.nextFloat();
            world.addParticle("reddust", (double)smokeX, (double)smokeY, (double)smokeZ, 0.0, 0.0, 0.0);
        }

    }

    boolean attemptToSawBlock(World world, int x, int y, int z, Random random) {
        if (!world.isAir(x, y, z)) {
            int iTargetid = world.getBlockId(x, y, z);
            ItemStack targetItem = new ItemStack(iTargetid, 1, world.getBlockMeta(x, y, z));
            boolean bSawedBlock = false;
            Block targetBlock = Block.BLOCKS[iTargetid];
            boolean bRemoveOriginalBlockIfSawed = true;
            if (iTargetid == BlockListener.omniSlab.id && !((OmniSlabBlock)BlockListener.omniSlab).IsSlabWood(world, x, y, z)) {
                return false;
            }

            ItemStack output = ReinforcedSawRecipeRegistry.getInstance().getResult(targetItem);
            if (output != null) {
                if (output.count == 0) {
                    output.count = 1;
                }

                for(int iTempCount = 0; iTempCount < output.count; ++iTempCount) {
                    UnsortedUtils.EjectSingleItemWithRandomOffset(world, x, y, z, output.itemId, output.getDamage());
                }

                bSawedBlock = true;
            } else if (iTargetid != Block.LEAVES.id && iTargetid != Block.SUGAR_CANE.id && iTargetid != Block.WHEAT.id && iTargetid != BlockListener.hempCrop.id) {
                if (iTargetid != Block.PISTON_HEAD.id && targetBlock != null) {
                    Material targetMaterial = targetBlock.material;
                    if (targetMaterial != Material.WOOD) {
                        if (targetMaterial.isSolid()) {
                            return false;
                        }
                    } else {
                        targetBlock.dropStacks(world, x, y, z, world.getBlockMeta(x, y, z));
                        bSawedBlock = true;
                    }
                }
            } else {
                targetBlock.dropStacks(world, x, y, z, world.getBlockMeta(x, y, z));
                bSawedBlock = true;
            }

            if (bSawedBlock) {
                world.playSound((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, "random.explode", 0.2F, 1.25F);

                this.emitSawParticles(world, x, y, z, random);
                if (bRemoveOriginalBlockIfSawed) {
                    world.setBlock(x, y, z, 0);
                }
            }
        }

        return true;
    }

    public void breakSaw(World world, int i, int j, int k) {
        int iTemp;
        for(iTemp = 0; iTemp < 2; ++iTemp) {
            UnsortedUtils.EjectSingleItemWithRandomOffset(world, i, j, k, ItemListener.gear.id, 0);
        }

        for(iTemp = 0; iTemp < 2; ++iTemp) {
            UnsortedUtils.EjectSingleItemWithRandomOffset(world, i, j, k, Block.PLANKS.id, 0);
        }

        for(iTemp = 0; iTemp < 2; ++iTemp) {
            UnsortedUtils.EjectSingleItemWithRandomOffset(world, i, j, k, Item.IRON_INGOT.id, 0);
        }

        for(iTemp = 0; iTemp < 1; ++iTemp) {
            UnsortedUtils.EjectSingleItemWithRandomOffset(world, i, j, k, ItemListener.belt.id, 0);
        }

        world.playSound((double)i + 0.5, (double)j + 0.5, (double)k + 0.5, "random.explode", 0.2F, 1.25F);

        world.setBlock(i, j, k, 0);
    }

    public boolean renderWorld(BlockRenderManager tileRenderer, BlockView tileView, int x, int y, int z) {
        float f = 0.5F;
        float f1 = 0.5F;
        float f2 = 0.75F;
        int l = this.getFacing(tileView, x, y, z);
        switch (l) {
            case 0 -> this.setBoundingBox(0.5F - f1, 1.0F - f2, 0.5F - f, 0.5F + f1, 1.0F, 0.5F + f);
            case 1 -> this.setBoundingBox(0.5F - f1, 0.0F, 0.5F - f, 0.5F + f1, f2, 0.5F + f);
            case 2 -> this.setBoundingBox(0.5F - f1, 0.5F - f, 1.0F - f2, 0.5F + f1, 0.5F + f, 1.0F);
            case 3 -> this.setBoundingBox(0.5F - f1, 0.5F - f, 0.0F, 0.5F + f1, 0.5F + f, f2);
            case 4 -> this.setBoundingBox(1.0F - f2, 0.5F - f1, 0.5F - f, 1.0F, 0.5F + f1, 0.5F + f);
            default -> this.setBoundingBox(0.0F, 0.5F - f1, 0.5F - f, f2, 0.5F + f1, 0.5F + f);
        }

        tileRenderer.renderBlock(this, x, y, z);
        f = 0.3125F;
        f1 = 0.0078125F;
        f2 = 0.25F;
        switch (l) {
            case 0 -> this.setBoundingBox(0.5F - f, 0.0F, 0.5F - f1, 0.5F + f, 0.999F, 0.5F + f1);
            case 1 -> this.setBoundingBox(0.5F - f, 0.001F, 0.5F - f1, 0.5F + f, 1.0F, 0.5F + f1);
            case 2 -> this.setBoundingBox(0.5F - f, 0.5F - f1, 0.0F, 0.5F + f, 0.5F + f1, f2);
            case 3 -> this.setBoundingBox(0.5F - f, 0.5F - f1, 1.0F - f2, 0.5F + f, 0.5F + f1, 1.0F);
            case 4 -> this.setBoundingBox(0.0F, 0.5F - f1, 0.5F - f, f2, 0.5F + f1, 0.5F + f);
            default -> this.setBoundingBox(1.0F - f2, 0.5F - f1, 0.5F - f, 1.0F, 0.5F + f1, 0.5F + f);
        }

        CustomBlockRendering.renderStandardBlockWithTexture(tileRenderer, this, x, y, z, TextureListener.reinforcedSawSaw);
        return true;
    }

    public void renderInventory(BlockRenderManager tileRenderer, int meta) {
        this.setupRenderBoundingBox();
        CustomBlockRendering.RenderInvBlockWithMetaData(tileRenderer, this, -0.5F, -0.5F, -0.5F, 1);
        float f = 0.3125F;
        float f1 = 0.0078125F;
        this.setBoundingBox(0.5F - f, 0.001F, 0.5F - f1, 0.5F + f, 1.0F, 0.5F + f1);
        CustomBlockRendering.RenderInvBlockWithTexture(tileRenderer, this, -0.5F, -0.5F, -0.5F, TextureListener.reinforcedSawSaw);
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
        return side != getFacing(world, x, y, z);
    }

    @Override
    public void powerMachine(World world, int x, int y, int z, int side) {
        toggleBlockActivity(world, x, y, z, true);
    }

    @Override
    public void unpowerMachine(World world, int x, int y, int z, int side) {
        toggleBlockActivity(world, x, y, z, false);
    }

    @Override
    public boolean isMachinePowered(World world, int x, int y, int z) {
        return isBlockOn(world, x, y, z);
    }
}
