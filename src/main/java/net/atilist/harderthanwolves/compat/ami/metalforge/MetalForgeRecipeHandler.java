package net.atilist.harderthanwolves.compat.ami.metalforge;

import net.atilist.harderthanwolves.recipe.MetalForgeRecipe;
import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeHandler;
import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeWrapper;
import org.jetbrains.annotations.NotNull;

public class MetalForgeRecipeHandler implements RecipeHandler<MetalForgeRecipe> {
    @NotNull
    @Override
    public Class<MetalForgeRecipe> getRecipeClass() {
        return MetalForgeRecipe.class;
    }

    @NotNull
    @Override
    public String getRecipeCategoryUid() {
        return "metal_forge";
    }

    @NotNull
    @Override
    public RecipeWrapper getRecipeWrapper(@NotNull MetalForgeRecipe recipe) {
        return new MetalForgeRecipeWrapper(recipe);
    }

    @Override
    public boolean isRecipeValid(@NotNull MetalForgeRecipe recipe) {
        return true;
    }
}
