package net.atilist.harderthanwolves.recipe;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;

public class CraftingInventoryWithoutHandler implements Inventory {

    private ItemStack[] stacks;
    private int width;

    public CraftingInventoryWithoutHandler(int width, int height) {
        int var4 = width * height;
        this.stacks = new ItemStack[var4];
        this.width = width;
    }

    @Override
    public int size() {
        return this.stacks.length;
    }

    @Override
    public ItemStack getStack(int slot) {
        return slot >= this.size() ? null : this.stacks[slot];
    }

    public ItemStack getStack(int x, int y) {
        if (x >= 0 && x < this.width) {
            int var3 = x + y * this.width;
            return this.getStack(var3);
        } else {
            return null;
        }
    }

    @Override
    public String getName() {
        return "Crafting";
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        if (this.stacks[slot] != null) {
            ItemStack var3;
            if (this.stacks[slot].count <= amount) {
                var3 = this.stacks[slot];
                this.stacks[slot] = null;
                return var3;
            } else {
                var3 = this.stacks[slot].split(amount);
                if (this.stacks[slot].count == 0) {
                    this.stacks[slot] = null;
                }

                return var3;
            }
        } else {
            return null;
        }
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        this.stacks[slot] = stack;
    }

    public int getMaxCountPerStack() {
        return 64;
    }

    public void markDirty() {
    }

    public boolean canPlayerUse(PlayerEntity player) {
        return true;
    }
}
