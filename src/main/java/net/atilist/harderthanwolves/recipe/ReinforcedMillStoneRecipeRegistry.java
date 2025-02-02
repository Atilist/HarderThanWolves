package net.atilist.harderthanwolves.recipe;

import net.kozibrodka.wolves.recipe.MillStoneRecipe;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ReinforcedMillStoneRecipeRegistry {
    private static final ReinforcedMillStoneRecipeRegistry INSTANCE = new ReinforcedMillStoneRecipeRegistry();
    private Map recipes = new HashMap();

    public ReinforcedMillStoneRecipeRegistry() {
    }

    public static final ReinforcedMillStoneRecipeRegistry getInstance() {
        return INSTANCE;
    }

    public void addMillingRecipe(int i, ItemStack arg) {
        this.recipes.put(i, arg);
    }

    public ItemStack getResult(int i) {
        return (ItemStack)this.recipes.get(i);
    }

    public ArrayList<ReinforcedMillStoneRecipe> getRecipes() {
        ArrayList<ReinforcedMillStoneRecipe> convertedRecipes = new ArrayList();
        ArrayList<ItemStack> inputs = new ArrayList();
        ArrayList<ItemStack> outputs = new ArrayList();
        Iterator var4 = this.recipes.keySet().iterator();

        while(var4.hasNext()) {
            Object obj = var4.next();
            if (obj instanceof Integer) {
                inputs.add(new ItemStack((Integer)obj, 1, 0));
                outputs.add(this.getResult((Integer)obj));
            }
        }

        for(int i = 0; i < inputs.size() && i < outputs.size(); ++i) {
            convertedRecipes.add(new ReinforcedMillStoneRecipe((ItemStack)inputs.get(i), (ItemStack)outputs.get(i)));
        }

        return convertedRecipes;
    }
}
