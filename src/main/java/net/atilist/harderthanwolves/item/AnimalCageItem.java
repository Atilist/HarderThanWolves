package net.atilist.harderthanwolves.item;

import net.atilist.harderthanwolves.events.init.ItemListener;
import net.danygames2014.tropicraft.entity.FrogEntity;
import net.danygames2014.tropicraft.entity.IguanaEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.util.Identifier;

public class AnimalCageItem extends LazyItemTemplate {
    public AnimalCageItem(Identifier identifier) {
        super(identifier);
        setMaxCount(1);
    }

    @Override
    public void useOnEntity(ItemStack stack, LivingEntity entity) {
        if (stack == null) {
            return;
        }
        if (entity instanceof FrogEntity) {
            entity.markDead();
            stack.itemId = ItemListener.cagedFrog.id;
        } else if (entity instanceof IguanaEntity) {
            entity.markDead();
            stack.itemId = ItemListener.cagedIguana.id;
        } else if (entity instanceof PigEntity) {
            entity.markDead();
            stack.itemId = ItemListener.cagedPig.id;
        } else if (entity instanceof CowEntity) {
            entity.markDead();
            stack.itemId = ItemListener.cagedCow.id;
        } else if (entity instanceof ChickenEntity) {
            entity.markDead();
            stack.itemId = ItemListener.cagedChicken.id;
        } else if (entity instanceof SheepEntity) {
            entity.markDead();
            stack.itemId = ItemListener.cagedSheep.id;
        }
    }
}
