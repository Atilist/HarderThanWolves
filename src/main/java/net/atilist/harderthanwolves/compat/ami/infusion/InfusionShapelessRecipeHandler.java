package net.atilist.harderthanwolves.compat.ami.infusion;

import net.atilist.harderthanwolves.recipe.MysticalInfuserShapelessRecipe;
import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeHandler;
import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeWrapper;
import org.jetbrains.annotations.NotNull;

public class InfusionShapelessRecipeHandler implements RecipeHandler<MysticalInfuserShapelessRecipe> {
    @NotNull
    @Override
    public Class<MysticalInfuserShapelessRecipe> getRecipeClass() {
        return MysticalInfuserShapelessRecipe.class;
    }

    @NotNull
    @Override
    public String getRecipeCategoryUid() {
        return "mystical_infuser_shapeless";
    }

    @NotNull
    @Override
    public RecipeWrapper getRecipeWrapper(@NotNull MysticalInfuserShapelessRecipe recipe) {
        return new InfusionShapelessRecipeWrapper(recipe);
    }

    @Override
    public boolean isRecipeValid(@NotNull MysticalInfuserShapelessRecipe recipe) {
        return true;
    }
}
