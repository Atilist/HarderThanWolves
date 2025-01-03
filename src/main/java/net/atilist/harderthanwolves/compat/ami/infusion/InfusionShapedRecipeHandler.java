package net.atilist.harderthanwolves.compat.ami.infusion;

import net.atilist.harderthanwolves.recipe.MysticalInfuserShapedRecipe;
import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeHandler;
import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeWrapper;
import org.jetbrains.annotations.NotNull;

public class InfusionShapedRecipeHandler implements RecipeHandler<MysticalInfuserShapedRecipe> {
    @NotNull
    @Override
    public Class<MysticalInfuserShapedRecipe> getRecipeClass() {
        return MysticalInfuserShapedRecipe.class;
    }

    @NotNull
    @Override
    public String getRecipeCategoryUid() {
        return "mystical_infuser_shaped";
    }

    @NotNull
    @Override
    public RecipeWrapper getRecipeWrapper(@NotNull MysticalInfuserShapedRecipe recipe) {
        return new InfusionShapedRecipeWrapper(recipe);
    }

    @Override
    public boolean isRecipeValid(@NotNull MysticalInfuserShapedRecipe recipe) {
        return true;
    }
}
