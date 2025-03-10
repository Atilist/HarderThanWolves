package net.atilist.harderthanwolves.recipe;

import net.minecraft.item.ItemStack;

public class DiamondDrillRecipe {
    private final ItemStack input;
    private final ItemStack output;

    public DiamondDrillRecipe(ItemStack input, ItemStack output) {
        this.input = input;
        this.output = output;
    }

    public ItemStack getInput() {
        return input;
    }

    public ItemStack getOutput() {
        return output;
    }
}
