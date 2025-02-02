package net.atilist.harderthanwolves.recipe;

import net.minecraft.item.ItemStack;

public class RotarySieveRecipe {
    private final ItemStack input;
    private final ItemStack output;
    private final ItemStack byproduct;

    public RotarySieveRecipe(ItemStack input, ItemStack output, ItemStack byproduct) {
        this.input = input;
        this.output = output;
        this.byproduct = byproduct;
    }

    public ItemStack getInput() {
        return input;
    }

    public ItemStack[] getOutputs() {
        return new ItemStack[] {output, byproduct};
    }
}
