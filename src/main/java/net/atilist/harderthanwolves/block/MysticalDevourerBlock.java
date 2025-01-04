package net.atilist.harderthanwolves.block;

import net.atilist.harderthanwolves.events.init.TextureListener;
import net.atilist.harderthanwolves.recipe.MysticalDevourerRecipeRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.FabricLoader;
import net.kozibrodka.wolves.block.entity.BlockDispenserBlockEntity;
import net.kozibrodka.wolves.container.BlockDispenserScreenHandler;
import net.kozibrodka.wolves.entity.BroadheadArrowEntity;
import net.kozibrodka.wolves.events.ItemListener;
import net.kozibrodka.wolves.events.ScreenHandlerListener;
import net.kozibrodka.wolves.network.ParticlePacket;
import net.kozibrodka.wolves.network.RenderPacket;
import net.kozibrodka.wolves.network.ScreenPacket;
import net.kozibrodka.wolves.network.SoundPacket;
import net.kozibrodka.wolves.utils.*;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.DispenserBlockEntity;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.thrown.EggEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.entity.vehicle.MinecartEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SeedsItem;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.Box;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.gui.screen.container.GuiHelper;
import net.modificationstation.stationapi.api.network.packet.PacketHelper;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.List;
import java.util.Random;

public class MysticalDevourerBlock extends LazyBlockWithEntityTemplate implements RotatableBlock {
    public final int blockDispenserTextureIDFront = 7;
    public final int blockDispenserTextureIDSide = 8;
    public final int blockDispenserTextureIDTop = 6;
    public final int blockDispenserTextureIDBottom = 9;
    private final int iBlockDispenserTickRate = 4;

    public MysticalDevourerBlock(Identifier identifier, Material material, float hardness, BlockSoundGroup blockSounds) {
        super(identifier, material, hardness, blockSounds);
    }

    public int getTextureId(BlockView iblockaccess, int i, int j, int k, int iSide) {
        int iFacing = this.GetFacing(iblockaccess, i, j, k);
        if (iSide == iFacing) {
            return TextureListener.mysticalDevourerFront;
        } else if (iSide == 1) {
            return TextureListener.mysticalDevourerTop;
        } else {
            return iSide == 0 ? TextureListener.mysticalDevourerBottom : TextureListener.mysticalDevourerSide;
        }
    }

    public int getTexture(int iSide) {
        if (iSide == 3) {
            return TextureListener.mysticalDevourerFront;
        } else if (iSide == 1) {
            return TextureListener.mysticalDevourerTop;
        } else {
            return iSide == 0 ? TextureListener.mysticalDevourerBottom : TextureListener.mysticalDevourerSide;
        }
    }

    public int getTickRate() {
        return 4;
    }

    public int getDroppedItemId(int i, Random random) {
        return net.atilist.harderthanwolves.events.init.BlockListener.mysticalDevourer.id;
    }

    public void onPlaced(World world, int i, int j, int k, int iFacing) {
        this.SetFacing(world, i, j, k, UnsortedUtils.getOppositeFacing(iFacing));
        world.scheduleBlockUpdate(i, j, k, net.atilist.harderthanwolves.events.init.BlockListener.mysticalDevourer.id, this.getTickRate());
    }

    public void onPlaced(World world, int i, int j, int k, LivingEntity entityLiving) {
        int iFacing = UnsortedUtils.ConvertPlacingEntityOrientationToBlockFacing(entityLiving);
        this.SetFacing(world, i, j, k, iFacing);
        world.scheduleBlockUpdate(i, j, k, net.atilist.harderthanwolves.events.init.BlockListener.mysticalDevourer.id, this.getTickRate());
    }

    public boolean onUse(World world, int i, int j, int k, PlayerEntity entityplayer) {
        if (world == null) {
            return true;
        } else {
            BlockDispenserBlockEntity tileEntityBlockDispenser = (BlockDispenserBlockEntity)world.getBlockEntity(i, j, k);
            ScreenHandlerListener.TempGuiX = i;
            ScreenHandlerListener.TempGuiY = j;
            ScreenHandlerListener.TempGuiZ = k;
            if (world.isRemote) {
                PacketHelper.send(new ScreenPacket("dispenser", 0, i, j, k));
            }

            GuiHelper.openGUI(entityplayer, Identifier.of("wolves:openBlockDispenser"), tileEntityBlockDispenser, new BlockDispenserScreenHandler(entityplayer.inventory, tileEntityBlockDispenser));
            return true;
        }
    }

    protected BlockEntity createBlockEntity() {
        return new BlockDispenserBlockEntity();
    }

    public void neighborUpdate(World world, int i, int j, int k, int iChangedid) {
        world.scheduleBlockUpdate(i, j, k, net.atilist.harderthanwolves.events.init.BlockListener.mysticalDevourer.id, this.getTickRate());
    }

    public void onBreak(World world, int i, int j, int k) {
        InventoryHandler.ejectInventoryContents(world, i, j, k, (Inventory)world.getBlockEntity(i, j, k));
        super.onBreak(world, i, j, k);
    }

    public void onTick(World world, int i, int j, int k, Random random) {
        this.ValidateBlockDispenser(world, i, j, k);
        boolean bIsPowered = world.canTransferPower(i, j, k) || world.canTransferPower(i, j + 1, k);
        if (bIsPowered) {
            if (!this.IsOn(world, i, j, k)) {
                this.TurnOn(world, i, j, k);
            }
        } else if (this.IsOn(world, i, j, k)) {
            this.TurnOff(world, i, j, k);
        }

    }

    public int GetFacing(BlockView iBlockAccess, int i, int j, int k) {
        return iBlockAccess.getBlockMeta(i, j, k) & -9;
    }

    public void SetFacing(World world, int i, int j, int k, int iFacing) {
        int iMetaData = world.getBlockMeta(i, j, k) & 8;
        iMetaData |= iFacing;
        world.setBlockMeta(i, j, k, iMetaData);
    }

    public boolean CanRotate(BlockView iBlockAccess, int i, int j, int l) {
        return true;
    }

    public boolean CanTransmitRotation(BlockView iBlockAccess, int i, int j, int l) {
        return true;
    }

    public void Rotate(World world, int i, int j, int k, boolean bReverse) {
        int iFacing = this.GetFacing(world, i, j, k);
        int iNewFacing = UnsortedUtils.RotateFacingAroundJ(iFacing, bReverse);
        if (iNewFacing != iFacing) {
            this.SetFacing(world, i, j, k, iNewFacing);
            world.setBlocksDirty(i, j, k, i, j, k);
        }

    }

    public boolean IsOn(World world, int i, int j, int k) {
        int iMetaData = world.getBlockMeta(i, j, k);
        return (iMetaData & 8) > 0;
    }

    private void TurnOn(World world, int i, int j, int k) {
        int iMetaData = world.getBlockMeta(i, j, k);
        iMetaData |= 8;
        world.setBlockMeta(i, j, k, iMetaData);
        world.blockUpdateEvent(i, j, k);
        this.DispenseBlockOrItem(world, i, j, k, world.random);
    }

    private void TurnOff(World world, int i, int j, int k) {
        int iMetaData = world.getBlockMeta(i, j, k);
        iMetaData &= -9;
        world.setBlockMeta(i, j, k, iMetaData);
        world.blockUpdateEvent(i, j, k);
        this.ConsumeFacingBlock(world, i, j, k);
    }

    private boolean ConsumeEntityAtTargetLoc(World world, int i, int j, int k, int targeti, int targetj, int targetk) {
        this.ValidateBlockDispenser(world, i, j, k);
        List list;
        list = world.collectEntitiesByClass(Entity.class, Box.createCached((double)targeti, (double)targetj, (double)targetk, (double)(targeti + 1), (double)(targetj + 1), (double)(targetk + 1)));
        if (list == null || list.isEmpty()) {
            return false;
        }
        BlockDispenserBlockEntity blockDispenserBlockEntity = (BlockDispenserBlockEntity)world.getBlockEntity(i, j, k);
        for (Object o : list) {
            Entity targetEntity = (Entity) o;
            if (targetEntity.dead) {
                continue;
            }
            if (!(targetEntity instanceof MonsterEntity)) {
                continue;
            }
            targetEntity.markDead();
            InventoryHandler.addSingleItemToInventory(blockDispenserBlockEntity, net.atilist.harderthanwolves.events.init.ItemListener.monsterCloth.id, 0);
            return true;
        }
        return false;
    }

    private void ConsumeFacingBlock(World world, int i, int j, int k) {
        int iFacingDirection = this.GetFacing(world, i, j, k);
        BlockPosition targetPos = new BlockPosition(i, j, k);
        targetPos.AddFacingAsOffset(iFacingDirection);
        if (this.ConsumeEntityAtTargetLoc(world, i, j, k, targetPos.i, targetPos.j, targetPos.k) && !world.isAir(targetPos.i, targetPos.j, targetPos.k)) {
            return;
        }
        int targetId = world.getBlockId(targetPos.i, targetPos.j, targetPos.k);
        Block targetBlock = Block.BLOCKS[targetId];
        ItemStack input = new ItemStack(targetId, 1, 0);
        ItemStack result = MysticalDevourerRecipeRegistry.getInstance().getResult(input);
        if (result == null) {
            return;
        }
        result = result.copy();
        int iTargetMetaData = world.getBlockMeta(targetPos.i, targetPos.j, targetPos.k);
        BlockDispenserBlockEntity blockDispenserBlockEntity = (BlockDispenserBlockEntity)world.getBlockEntity(i, j, k);
        InventoryHandler.addItemInstanceToInventory(blockDispenserBlockEntity, result);
        if (FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER) {
            this.renderPacket(world, targetPos.i, targetPos.j, targetPos.k, targetId, iTargetMetaData);
        } else {
            ((Minecraft)Minecraft.class.cast(net.fabricmc.loader.api.FabricLoader.getInstance().getGameInstance())).field_2808.method_322(targetPos.i, targetPos.j, targetPos.k, targetId, iTargetMetaData);
            world.playSound((double)((float)targetPos.i + 0.5F), (double)((float)targetPos.j + 0.5F), (double)((float)targetPos.k + 0.5F), targetBlock.soundGroup.getSound(), (targetBlock.soundGroup.method_1976() + 1.0F) / 2.0F, targetBlock.soundGroup.method_1977() * 0.8F);
        }
        world.setBlock(targetPos.i, targetPos.j, targetPos.k, 0);
    }

    @Environment(EnvType.SERVER)
    public void renderPacket(World world, int x, int y, int z, int blockId, int metaId) {
        List list2 = world.players;
        if (list2.size() != 0) {
            for(int k = 0; k < list2.size(); ++k) {
                ServerPlayerEntity player1 = (ServerPlayerEntity)list2.get(k);
                PacketHelper.sendTo(player1, new RenderPacket(x, y, z, blockId, metaId));
            }
        }

    }

    private void SpitOutItem(World world, int i, int j, int k, ItemStack ItemInstance, Random random) {
        int facing = this.GetFacing(world, i, j, k);
        float deltaj = 0.0F;
        float f = 0.0F;
        float f1 = 0.0F;
        switch (facing) {
            case 0 -> deltaj = -1.0F;
            case 1 -> deltaj = 1.0F;
            case 2 -> f1 = -1.0F;
            case 3 -> f1 = 1.0F;
            case 4 -> f = -1.0F;
            default -> f = 1.0F;
        }

        double d = (double)i + (double)f * 0.5 + 0.5;
        double d1 = (double)j + (double)deltaj + 0.5;
        double d2 = (double)k + (double)f1 * 0.5 + 0.5;
        if (deltaj < 0.1F && deltaj > -0.1F) {
            deltaj = 0.1F;
        }

        ItemEntity entityitem = new ItemEntity(world, d, d1 - 0.3, d2, ItemInstance);
        double d3 = random.nextDouble() * 0.1 + 0.2;
        entityitem.velocityX = (double)f * d3;
        entityitem.velocityY = (double)deltaj * d3 + 0.2000000029802322;
        entityitem.velocityZ = (double)f1 * d3;
        entityitem.velocityX += random.nextGaussian() * 0.007499999832361937 * 6.0;
        entityitem.velocityY += random.nextGaussian() * 0.007499999832361937 * 6.0;
        entityitem.velocityZ += random.nextGaussian() * 0.007499999832361937 * 6.0;
        world.spawnEntity(entityitem);
    }

    private void DispenseBlockOrItem(World world, int i, int j, int k, Random random) {
        this.ValidateBlockDispenser(world, i, j, k);
        int iFacing = this.GetFacing(world, i, j, k);
        BlockPosition targetPos = new BlockPosition(i, j, k);
        targetPos.AddFacingAsOffset(iFacing);
        int iTargetid = world.getBlockId(targetPos.i, targetPos.j, targetPos.k);
        Block targetBlock = Block.BLOCKS[iTargetid];
        boolean shouldDispense = false;
        boolean bSuccessfullyDispensed = false;
        if (targetBlock == null) {
            shouldDispense = true;
        } else if (ReplaceableBlockChecker.IsReplaceableBlock(world, targetPos.i, targetPos.j, targetPos.k) || !targetBlock.material.isSolid()) {
            shouldDispense = true;
        }

        if (shouldDispense) {
            float deltaj = 0.0F;
            float f = 0.0F;
            float f1 = 0.0F;
            if (iFacing == 0) {
                deltaj = -1.0F;
            } else if (iFacing == 1) {
                deltaj = 1.0F;
            } else if (iFacing == 3) {
                f1 = 1.0F;
            } else if (iFacing == 2) {
                f1 = -1.0F;
            } else if (iFacing == 5) {
                f = 1.0F;
            } else {
                f = -1.0F;
            }

            BlockDispenserBlockEntity tileEntityBlockDispenser = (BlockDispenserBlockEntity)world.getBlockEntity(i, j, k);
            ItemStack iteminstance = tileEntityBlockDispenser.GetNextStackFromInventory();
            double d = (double)targetPos.i + (double)f * 0.5 + 0.5;
            double d1 = (double)targetPos.j + (double)deltaj + 0.5;
            double d2 = (double)targetPos.k + (double)f1 * 0.5 + 0.5;
            if (iteminstance != null) {
                if (deltaj < 0.1F && deltaj > -0.1F) {
                    deltaj = 0.1F;
                }

                if (iteminstance.itemId == Item.ARROW.id) {
                    ArrowEntity entityarrow = new ArrowEntity(world, d, d1, d2);
                    entityarrow.setVelocity((double)f, (double)deltaj, (double)f1, 1.1F, 6.0F);
                    world.spawnEntity(entityarrow);
                    world.playSound((double)i, (double)j, (double)k, "random.bow", 1.0F, 1.2F);
                    if (FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER) {
                        this.voicePacket(world, "random.bow", i, j, k, 1.0F, 1.2F);
                    }

                    bSuccessfullyDispensed = true;
                } else if (iteminstance.itemId == ItemListener.broadHeadArrow.id) {
                    BroadheadArrowEntity entityarrow = new BroadheadArrowEntity(world, d, d1, d2);
                    entityarrow.method_1291((double)f, (double)deltaj, (double)f1, 1.1F, 6.0F);
                    entityarrow.velocityX *= 1.5;
                    entityarrow.velocityY *= 1.5;
                    entityarrow.velocityZ *= 1.5;
                    world.spawnEntity(entityarrow);
                    world.playSound((double)i, (double)j, (double)k, "random.bow", 1.0F, 1.2F);
                    if (FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER) {
                        this.voicePacket(world, "random.bow", i, j, k, 1.0F, 1.2F);
                    }

                    bSuccessfullyDispensed = true;
                } else if (iteminstance.itemId == Item.EGG.id) {
                    EggEntity entityegg = new EggEntity(world, d, d1, d2);
                    entityegg.setVelocity((double)f, (double)deltaj, (double)f1, 1.1F, 6.0F);
                    world.spawnEntity(entityegg);
                    world.playSound((double)i, (double)j, (double)k, "random.bow", 1.0F, 1.2F);
                    if (FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER) {
                        this.voicePacket(world, "random.bow", i, j, k, 1.0F, 1.2F);
                    }

                    bSuccessfullyDispensed = true;
                } else if (iteminstance.itemId == Item.SNOWBALL.id) {
                    SnowballEntity entitysnowball = new SnowballEntity(world, d, d1, d2);
                    entitysnowball.setVelocity((double)f, (double)deltaj, (double)f1, 1.1F, 6.0F);
                    world.spawnEntity(entitysnowball);
                    world.playSound((double)i, (double)j, (double)k, "random.bow", 1.0F, 1.2F);
                    if (FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER) {
                        this.voicePacket(world, "random.bow", i, j, k, 1.0F, 1.2F);
                    }

                    bSuccessfullyDispensed = true;
                } else {
                    MinecartEntity entityMinecart;
                    if (iteminstance.itemId == Item.MINECART.id) {
                        entityMinecart = new MinecartEntity(world, d + (double)f * 0.75, d1 - 0.5, d2 + (double)f1 * 0.75, 0);
                        world.spawnEntity(entityMinecart);
                        world.playSound((double)i, (double)j, (double)k, "random.click", 1.0F, 1.0F);
                        if (FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER) {
                            this.voicePacket(world, "random.click", i, j, k, 1.0F, 1.0F);
                        }

                        bSuccessfullyDispensed = true;
                    } else if (iteminstance.itemId == Item.CHEST_MINECART.id) {
                        entityMinecart = new MinecartEntity(world, d + (double)f * 0.75, d1 - 0.5, d2 + (double)f1 * 0.75, 1);
                        world.spawnEntity(entityMinecart);
                        world.playSound((double)i, (double)j, (double)k, "random.click", 1.0F, 1.0F);
                        if (FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER) {
                            this.voicePacket(world, "random.click", i, j, k, 1.0F, 1.0F);
                        }

                        bSuccessfullyDispensed = true;
                    } else if (iteminstance.itemId == Item.FURNACE_MINECART.id) {
                        entityMinecart = new MinecartEntity(world, d + (double)f * 0.75, d1 - 0.5, d2 + (double)f1 * 0.75, 2);
                        world.spawnEntity(entityMinecart);
                        world.playSound((double)i, (double)j, (double)k, "random.click", 1.0F, 1.0F);
                        if (FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER) {
                            this.voicePacket(world, "random.click", i, j, k, 1.0F, 1.0F);
                        }

                        bSuccessfullyDispensed = true;
                    } else if (iteminstance.itemId == Item.BOAT.id) {
                        BoatEntity entityBoat = new BoatEntity(world, d + (double)f, d1 - 0.5, d2 + (double)f1);
                        world.spawnEntity(entityBoat);
                        world.playSound((double)i, (double)j, (double)k, "random.click", 1.0F, 1.0F);
                        if (FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER) {
                            this.voicePacket(world, "random.click", i, j, k, 1.0F, 1.0F);
                        }

                        bSuccessfullyDispensed = true;
                    } else {
                        Block newBlock;
                        if (iteminstance.getItem() instanceof SeedsItem) {
                            ++iteminstance.count;
                            if (!iteminstance.getItem().useOnBlock(iteminstance, (PlayerEntity)null, world, targetPos.i, targetPos.j - 1, targetPos.k, 1)) {
                                InventoryHandler.addSingleItemToInventory(tileEntityBlockDispenser, iteminstance.itemId, 0);
                            } else {
                                newBlock = Block.WHEAT;
                                world.playSound((double)((float)i + 0.5F), (double)((float)j + 0.5F), (double)((float)k + 0.5F), newBlock.soundGroup.getSound(), (newBlock.soundGroup.method_1976() + 1.0F) / 2.0F, newBlock.soundGroup.method_1977() * 0.8F);
                                if (FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER) {
                                    this.voicePacket(world, newBlock.soundGroup.getSound(), i, j, k, (newBlock.soundGroup.method_1976() + 1.0F) / 2.0F, newBlock.soundGroup.method_1977() * 0.8F);
                                }

                                bSuccessfullyDispensed = true;
                            }
                        } else {
                            newBlock = null;
                            if (iteminstance.itemId < 256) {
                                newBlock = Block.BLOCKS[iteminstance.itemId];
                            } else if (iteminstance.itemId == Item.SUGAR_CANE.id) {
                                newBlock = Block.SUGAR_CANE;
                            } else if (iteminstance.itemId == Item.CAKE.id) {
                                newBlock = Block.CAKE;
                            } else if (iteminstance.itemId == Item.REPEATER.id) {
                                newBlock = Block.REPEATER;
                            } else if (iteminstance.itemId == Item.REDSTONE.id) {
                                newBlock = Block.REDSTONE_WIRE;
                            }

                            if (newBlock != null) {
                                int iNewid = newBlock.id;
                                int iTargetDirection = UnsortedUtils.getOppositeFacing(iFacing);
                                if (!world.canPlace(iNewid, targetPos.i, targetPos.j, targetPos.k, true, iTargetDirection)) {
                                    InventoryHandler.addSingleItemToInventory(tileEntityBlockDispenser, iteminstance.itemId, iteminstance.getDamage());
                                } else {
                                    if (iNewid != Block.PISTON.id && iNewid != Block.STICKY_PISTON.id) {
                                        world.setBlock(targetPos.i, targetPos.j, targetPos.k, iNewid, iteminstance.getItem().getPlacementMetadata(iteminstance.getDamage()));
                                        newBlock.onPlaced(world, targetPos.i, targetPos.j, targetPos.k, iTargetDirection);
                                    } else {
                                        world.setBlock(targetPos.i, targetPos.j, targetPos.k, iNewid, iFacing);
                                    }

                                    world.playSound((double)((float)i + 0.5F), (double)((float)j + 0.5F), (double)((float)k + 0.5F), newBlock.soundGroup.getSound(), (newBlock.soundGroup.method_1976() + 1.0F) / 2.0F, newBlock.soundGroup.method_1977() * 0.8F);
                                    if (FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER) {
                                        this.voicePacket(world, newBlock.soundGroup.getSound(), i, j, k, (newBlock.soundGroup.method_1976() + 1.0F) / 2.0F, newBlock.soundGroup.method_1977() * 0.8F);
                                    }

                                    bSuccessfullyDispensed = true;
                                }
                            } else if (world.isAir(targetPos.i, targetPos.j, targetPos.k)) {
                                this.SpitOutItem(world, i, j, k, iteminstance, random);
                                world.playSound((double)i, (double)j, (double)k, "random.click", 1.0F, 1.0F);
                                if (FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER) {
                                    this.voicePacket(world, "random.click", i, j, k, 1.0F, 1.0F);
                                }

                                bSuccessfullyDispensed = true;
                            } else {
                                InventoryHandler.addSingleItemToInventory(tileEntityBlockDispenser, iteminstance.itemId, iteminstance.getDamage());
                            }
                        }
                    }
                }
            }

            if (bSuccessfullyDispensed) {
                for(int i1 = 0; i1 < 10; ++i1) {
                    double d4 = random.nextDouble() * 0.2 + 0.01;
                    double d5 = d + (double)f * 0.01 + (random.nextDouble() - 0.5) * (double)f1 * 0.5;
                    double d6 = d1 + (double)deltaj * 0.01 + (random.nextDouble() - 0.5) * 0.5;
                    double d7 = d2 + (double)f1 * 0.01 + (random.nextDouble() - 0.5) * (double)f * 0.5;
                    double d8 = (double)f * d4 + random.nextGaussian() * 0.01;
                    double d9 = (double)deltaj * d4 + -0.03 + random.nextGaussian() * 0.01;
                    double d10 = (double)f1 * d4 + random.nextGaussian() * 0.01;
                    world.addParticle("smoke", d5, d6, d7, d8, d9, d10);
                    if (FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER) {
                        this.particlePacket(world, "reddust", d5, d6, d7, d8, d9, d10);
                    }
                }
            }
        }

        if (!bSuccessfullyDispensed) {
            world.playSound((double)i, (double)j, (double)k, "random.click", 1.0F, 1.2F);
            if (FabricLoader.INSTANCE.getEnvironmentType() == EnvType.SERVER) {
                this.voicePacket(world, "random.click", i, j, k, 1.0F, 1.2F);
            }
        }

    }

    private void ValidateBlockDispenser(World world, int i, int j, int k) {
        BlockEntity tileentity = world.getBlockEntity(i, j, k);
        if (tileentity instanceof BlockDispenserBlockEntity) {
        } else {
            BlockDispenserBlockEntity fctileentityblockdispenser = new BlockDispenserBlockEntity();
            if (tileentity instanceof DispenserBlockEntity) {
                DispenserBlockEntity tileentitydispenser = (DispenserBlockEntity)tileentity;
                int l = tileentitydispenser.size();
                int i1 = fctileentityblockdispenser.size();

                for(int j1 = 0; j1 < l && j1 < i1; ++j1) {
                    ItemStack itemstack = tileentitydispenser.getStack(j1);
                    if (itemstack != null) {
                        fctileentityblockdispenser.setStack(j1, itemstack.copy());
                    }
                }
            }

            world.setBlockEntity(i, j, k, fctileentityblockdispenser);
        }
    }

    @Environment(EnvType.SERVER)
    public void voicePacket(World world, String name, int x, int y, int z, float g, float h) {
        List list2 = world.players;
        if (list2.size() != 0) {
            for(int k = 0; k < list2.size(); ++k) {
                ServerPlayerEntity player1 = (ServerPlayerEntity)list2.get(k);
                PacketHelper.sendTo(player1, new SoundPacket(name, x, y, z, g, h));
            }
        }

    }

    @Environment(EnvType.SERVER)
    public void particlePacket(World world, String name, double x, double y, double z, double i, double j, double k) {
        List list2 = world.players;
        if (list2.size() != 0) {
            for(int k1 = 0; k1 < list2.size(); ++k1) {
                ServerPlayerEntity player1 = (ServerPlayerEntity)list2.get(k1);
                PacketHelper.sendTo(player1, new ParticlePacket(name, x, y, z, i, j, k));
            }
        }

    }
}