package net.atilist.harderthanwolves.recipe;

import net.kozibrodka.wolves.events.ItemListener;
import net.kozibrodka.wolves.recipe.TurntableRecipe;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public class RotarySieveRecipeRegistry {
    private static final RotarySieveRecipeRegistry INSTANCE = new RotarySieveRecipeRegistry();
    private ArrayList<ItemStack[]> recipes = new ArrayList<>();

    public static final RotarySieveRecipeRegistry getInstance() {
        return INSTANCE;
    }

    public void addRotarySieveRecipe(Block block, ItemStack output) {
        this.recipes.add(new ItemStack[] {new ItemStack(block, 1, 0), output, null});
    }

    public void addRotarySieveRecipe(Block block, int meta, ItemStack output) {
        this.recipes.add(new ItemStack[] {new ItemStack(block, 1, meta), output, null});
    }

    public void addRotarySieveRecipe(Block block, int meta, ItemStack output, ItemStack byproduct) {
        this.recipes.add(new ItemStack[] {new ItemStack(block, 1, meta), output, byproduct});
    }

    public ItemStack[] getResult(ItemStack item) {
        for (ItemStack[] items : recipes) {
            if (items[0].isItemEqual(item)) {
                return new ItemStack[]{items[1], items[2]};
            }
        }
        return null;
    }

    public ArrayList<RotarySieveRecipe> getRecipes() {
        ArrayList<RotarySieveRecipe> convertedRecipes = new ArrayList<>();
        ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        ArrayList<ItemStack> byproducts = new ArrayList<>();
        for (ItemStack[] recipe : recipes) {
            ItemStack input = recipe[0].copy();
            ItemStack output = recipe[1].copy();
            ItemStack byproduct;
            if (recipe[2] == null) {
                byproduct = new ItemStack(ItemListener.nothing, 1);
            } else {
                byproduct = recipe[2].copy();
            }
            inputs.add(input);
            outputs.add(output);
            byproducts.add(byproduct);
        }
        for (int i = 0; i < inputs.size(); i++) {
            if (i >= outputs.size()) break;
            convertedRecipes.add(new RotarySieveRecipe(inputs.get(i), outputs.get(i), byproducts.get(i)));
        }
        return convertedRecipes;
    }
}
