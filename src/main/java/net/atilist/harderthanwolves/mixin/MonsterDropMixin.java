package net.atilist.harderthanwolves.mixin;

import net.atilist.harderthanwolves.events.init.ItemListener;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.MonsterEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MonsterEntity.class)
public abstract class MonsterDropMixin extends MobEntity {

    public MonsterDropMixin(World world) {
        super(world);
    }

    @Inject(at = @At("HEAD"), method = "damage", remap = false)
    private void dropMonsterCloth(Entity damageSource, int amount, CallbackInfoReturnable<Boolean> cir) {
        if (random.nextInt(5) != 0) {
            return;
        }
        if (!(damageSource instanceof PlayerEntity)) {
            return;
        }
        ItemStack itemInHand = ((PlayerEntity) damageSource).getHand();
        if (itemInHand == null) {
            return;
        }
        if (itemInHand.itemId != Item.GOLDEN_SWORD.id) {
            return;
        }
        float randomizerAmplitude = 0.7F;
        double xOffset = (double)(world.field_214.nextFloat() * randomizerAmplitude) + (double)(1.0F - randomizerAmplitude) * 0.5;
        double yOffset = (double)(world.field_214.nextFloat() * randomizerAmplitude) + (double)(1.0F - randomizerAmplitude) * 0.5;
        double zOffset = (double)(world.field_214.nextFloat() * randomizerAmplitude) + (double)(1.0F - randomizerAmplitude) * 0.5;
        ItemEntity itemEntity = new ItemEntity(world, (double)x + xOffset, (double)y + yOffset, (double)z + zOffset, new ItemStack(ItemListener.monsterCloth, 1));
        itemEntity.pickupDelay = 10;
        world.method_210(itemEntity);
    }
}
