package net.atilist.harderthanwolves.recipe;

import net.minecraft.item.ItemStack;

public class MetalForgeRecipe {
    private final ItemStack input;
    private final ItemStack output;

    public MetalForgeRecipe(ItemStack input, ItemStack output) {
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
