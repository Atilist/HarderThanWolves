package net.atilist.harderthanwolves.container;

import net.atilist.harderthanwolves.block.entity.MysticalInfuserBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.FurnaceOutputSlot;
import net.minecraft.screen.slot.Slot;

public class MysticalInfuserScreenHandler extends ScreenHandler {

    public MysticalInfuserScreenHandler(PlayerInventory playerInventory, MysticalInfuserBlockEntity mysticalInfuserBlockEntity) {
        localMysticalInfuserBlockEntity = mysticalInfuserBlockEntity;

        // Machine inventory
        this.addSlot(new Slot(mysticalInfuserBlockEntity, 0, 56, 91)); // Fuel input
        this.addSlot(new Slot(mysticalInfuserBlockEntity, 1, 92, 91)); // Fuel output
        this.addSlot(new FurnaceOutputSlot(playerInventory.player, mysticalInfuserBlockEntity, 2, 131, 38)); // Infusion result
        int yOffset;
        int xOffset;
        for(yOffset = 0; yOffset < 3; ++yOffset) { // Infusion inputs
            for(xOffset = 0; xOffset < 3; ++xOffset) {
                this.addSlot(new Slot(mysticalInfuserBlockEntity, 3 + xOffset + yOffset * 3, 38 + xOffset * 18, 19 + yOffset * 18));
            }
        }

        // Player inventory
        int primaryPlayerOffset;
        for(primaryPlayerOffset = 0; primaryPlayerOffset < 3; ++primaryPlayerOffset) {
            for(int secondaryPlayerOffset = 0; secondaryPlayerOffset < 9; ++secondaryPlayerOffset) {
                this.addSlot(new Slot(playerInventory, secondaryPlayerOffset + primaryPlayerOffset * 9 + 9, 8 + secondaryPlayerOffset * 18, 122 + primaryPlayerOffset * 18));
            }
        }
        for(primaryPlayerOffset = 0; primaryPlayerOffset < 9; ++primaryPlayerOffset) {
            this.addSlot(new Slot(playerInventory, primaryPlayerOffset, 8 + primaryPlayerOffset * 18, 180));
        }
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return localMysticalInfuserBlockEntity.canPlayerUse(player);
    }

    private final MysticalInfuserBlockEntity localMysticalInfuserBlockEntity;
}
