package net.atilist.harderthanwolves.compat.ami.mysticaldevourer;

import net.atilist.harderthanwolves.recipe.MysticalDevourerRecipe;
import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeHandler;
import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeWrapper;
import org.jetbrains.annotations.NotNull;

public class MysticalDevourerRecipeHandler implements RecipeHandler<MysticalDevourerRecipe> {
    @NotNull
    @Override
    public Class<MysticalDevourerRecipe> getRecipeClass() {
        return MysticalDevourerRecipe.class;
    }

    @NotNull
    @Override
    public String getRecipeCategoryUid() {
        return "mystical_devourer";
    }

    @NotNull
    @Override
    public RecipeWrapper getRecipeWrapper(@NotNull MysticalDevourerRecipe recipe) {
        return new MysticalDevourerRecipeWrapper(recipe);
    }

    @Override
    public boolean isRecipeValid(@NotNull MysticalDevourerRecipe recipe) {
        return true;
    }
}
