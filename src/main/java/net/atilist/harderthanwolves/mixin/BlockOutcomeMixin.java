package net.atilist.harderthanwolves.mixin;

import net.atilist.harderthanwolves.events.init.BlockListener;
import net.atilist.harderthanwolves.events.init.ItemListener;
import net.minecraft.block.Block;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public class BlockOutcomeMixin {
    @Shadow @Final public int id;

    @Inject(at = @At("HEAD"), method = "afterBreak", remap = false, cancellable = true)
    private void addCustomOutcome(World world, PlayerEntity playerEntity, int x, int y, int z, int meta, CallbackInfo ci) {
        if (playerEntity.getHand() == null) {
            return;
        }
        int itemInHand = playerEntity.getHand().itemId;
        ItemStack result = null;
        if (this.id == Block.STONE.id && (itemInHand == ItemListener.stoneChisel.id || itemInHand == ItemListener.ironChisel.id || itemInHand == ItemListener.diamondChisel.id)) {
            result = new ItemStack(ItemListener.stoneBrick, 1 + world.random.nextInt(4));
        } else if (this.id == Block.STONE.id && (itemInHand == Item.GOLDEN_PICKAXE.id || itemInHand == ItemListener.reinforcedGoldenPickaxe.id)) {
            result = new ItemStack(BlockListener.mysticalCobblestone, 1);
        } else if (this.id == Block.PLANKS.id && (itemInHand == Item.GOLDEN_AXE.id || itemInHand == ItemListener.reinforcedGoldenAxe.id)) {
            result = new ItemStack(ItemListener.mysticalStick, 2);
        } else if (this.id == Block.GRAVEL.id && (itemInHand == Item.GOLDEN_SHOVEL.id || itemInHand == ItemListener.reinforcedGoldenShovel.id)) {
            result = new ItemStack(BlockListener.mysticalGravel, 1);
        } else if (this.id == net.kozibrodka.wolves.events.BlockListener.hempCrop.id && (itemInHand == Item.GOLDEN_HOE.id || itemInHand == ItemListener.reinforcedGoldenHoe.id) && meta == 7) {
            result = new ItemStack(ItemListener.mysticalHemp, 1);
        }
        if (result == null) {
            return;
        }
        float randomizerAmplitude = 0.7F;
        double xOffset = (double)(world.random.nextFloat() * randomizerAmplitude) + (double)(1.0F - randomizerAmplitude) * 0.5;
        double yOffset = (double)(world.random.nextFloat() * randomizerAmplitude) + (double)(1.0F - randomizerAmplitude) * 0.5;
        double zOffset = (double)(world.random.nextFloat() * randomizerAmplitude) + (double)(1.0F - randomizerAmplitude) * 0.5;
        ItemEntity itemEntity = new ItemEntity(world, (double)x + xOffset, (double)y + yOffset, (double)z + zOffset, result);
        itemEntity.pickupDelay = 10;
        world.spawnEntity(itemEntity);
        ci.cancel();
    }
}
