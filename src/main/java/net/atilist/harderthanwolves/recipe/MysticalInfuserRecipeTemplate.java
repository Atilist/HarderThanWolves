package net.atilist.harderthanwolves.recipe;

import net.minecraft.item.ItemStack;

public interface MysticalInfuserRecipeTemplate {
    boolean canCraft(CraftingInventoryWithoutHandler arg);

    ItemStack craft(CraftingInventoryWithoutHandler arg);

    int getIngredientCount();

    ItemStack getOutput();
}
