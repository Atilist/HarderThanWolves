package net.atilist.harderthanwolves.compat.ami.reinforcedsaw;

import net.atilist.harderthanwolves.recipe.ReinforcedSawRecipe;
import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeHandler;
import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeWrapper;
import org.jetbrains.annotations.NotNull;

public class ReinforcedSawRecipeHandler implements RecipeHandler<ReinforcedSawRecipe> {
    @NotNull
    @Override
    public Class<ReinforcedSawRecipe> getRecipeClass() {
        return ReinforcedSawRecipe.class;
    }

    @NotNull
    @Override
    public String getRecipeCategoryUid() {
        return "reinforced_saw";
    }

    @NotNull
    @Override
    public RecipeWrapper getRecipeWrapper(@NotNull ReinforcedSawRecipe recipe) {
        return new ReinforcedSawRecipeWrapper(recipe);
    }

    @Override
    public boolean isRecipeValid(@NotNull ReinforcedSawRecipe recipe) {
        return true;
    }
}
