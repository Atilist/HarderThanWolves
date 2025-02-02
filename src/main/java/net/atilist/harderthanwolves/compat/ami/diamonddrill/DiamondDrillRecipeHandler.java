package net.atilist.harderthanwolves.compat.ami.diamonddrill;

import net.atilist.harderthanwolves.recipe.DiamondDrillRecipe;
import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeHandler;
import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeWrapper;
import org.jetbrains.annotations.NotNull;

public class DiamondDrillRecipeHandler implements RecipeHandler<DiamondDrillRecipe> {
    @NotNull
    @Override
    public Class<DiamondDrillRecipe> getRecipeClass() {
        return DiamondDrillRecipe.class;
    }

    @NotNull
    @Override
    public String getRecipeCategoryUid() {
        return "diamond_drill";
    }

    @NotNull
    @Override
    public RecipeWrapper getRecipeWrapper(@NotNull DiamondDrillRecipe recipe) {
        return new DiamondDrillRecipeWrapper(recipe);
    }

    @Override
    public boolean isRecipeValid(@NotNull DiamondDrillRecipe recipe) {
        return true;
    }
}
