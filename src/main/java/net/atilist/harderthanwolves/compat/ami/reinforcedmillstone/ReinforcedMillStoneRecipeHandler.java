package net.atilist.harderthanwolves.compat.ami.reinforcedmillstone;

import net.atilist.harderthanwolves.recipe.ReinforcedMillStoneRecipe;
import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeHandler;
import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeWrapper;
import org.jetbrains.annotations.NotNull;

public class ReinforcedMillStoneRecipeHandler implements RecipeHandler<ReinforcedMillStoneRecipe> {
    @NotNull
    @Override
    public Class<ReinforcedMillStoneRecipe> getRecipeClass() {
        return ReinforcedMillStoneRecipe.class;
    }

    @NotNull
    @Override
    public String getRecipeCategoryUid() {
        return "reinforced_mill_stone";
    }

    @NotNull
    @Override
    public RecipeWrapper getRecipeWrapper(@NotNull ReinforcedMillStoneRecipe recipe) {
        return new ReinforcedMillStoneRecipeWrapper(recipe);
    }

    @Override
    public boolean isRecipeValid(@NotNull ReinforcedMillStoneRecipe recipe) {
        return true;
    }
}
