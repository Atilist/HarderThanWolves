package net.atilist.harderthanwolves.mixin;

import net.atilist.harderthanwolves.events.init.ItemListener;
import net.minecraft.block.Block;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public class StoneMixin {
    @Shadow @Final public int id;

    @Inject(at = @At("HEAD"), method = "afterBreak", remap = false, cancellable = true)
    private void addChiselOutcome(World world, PlayerEntity playerEntity, int x, int y, int z, int meta, CallbackInfo ci) {
        if (this.id == Block.STONE.id && playerEntity.getHand() != null && playerEntity.getHand().itemId == ItemListener.stoneChisel.id) {
            float randomizerAmplitude = 0.7F;
            double xOffset = (double)(world.field_214.nextFloat() * randomizerAmplitude) + (double)(1.0F - randomizerAmplitude) * 0.5;
            double yOffset = (double)(world.field_214.nextFloat() * randomizerAmplitude) + (double)(1.0F - randomizerAmplitude) * 0.5;
            double zOffset = (double)(world.field_214.nextFloat() * randomizerAmplitude) + (double)(1.0F - randomizerAmplitude) * 0.5;
            ItemEntity itemEntity = new ItemEntity(world, (double)x + xOffset, (double)y + yOffset, (double)z + zOffset, new ItemStack(ItemListener.stoneBrick, 1 + world.field_214.nextInt(4)));
            itemEntity.pickupDelay = 10;
            world.method_210(itemEntity);
            ci.cancel();
        }
    }
}
