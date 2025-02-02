package net.atilist.harderthanwolves.recipe;

import net.kozibrodka.wolves.recipe.SawRecipe;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Iterator;

public class MetalForgeRecipeRegistry {
    private static final MetalForgeRecipeRegistry INSTANCE = new MetalForgeRecipeRegistry();
    private ArrayList<ItemStack[]> recipes = new ArrayList();

    public MetalForgeRecipeRegistry() {
    }

    public static MetalForgeRecipeRegistry getInstance() {
        return INSTANCE;
    }

    public void addForgingRecipe(int input, ItemStack output) {
        this.recipes.add(new ItemStack[]{new ItemStack(input, 1, 0), output});
    }

    public void addForgingRecipe(ItemStack input, ItemStack output) {
        this.recipes.add(new ItemStack[]{input, output});
    }

    public ItemStack getResult(ItemStack item) {
        Iterator var2 = this.recipes.iterator();

        ItemStack[] items;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            items = (ItemStack[])var2.next();
        } while(!items[0].isItemEqual(item));

        return items[1];
    }

    public ArrayList<MetalForgeRecipe> getRecipes() {
        ArrayList<MetalForgeRecipe> convertedRecipes = new ArrayList();
        ArrayList<ItemStack> inputs = new ArrayList();
        ArrayList<ItemStack> outputs = new ArrayList();
        Iterator var4 = this.recipes.iterator();

        while(var4.hasNext()) {
            ItemStack[] recipe = (ItemStack[])var4.next();
            inputs.add(recipe[0]);
            outputs.add(recipe[1]);
        }

        for(int i = 0; i < inputs.size() && i < outputs.size(); ++i) {
            convertedRecipes.add(new MetalForgeRecipe((ItemStack)inputs.get(i), (ItemStack)outputs.get(i)));
        }

        return convertedRecipes;
    }
}
