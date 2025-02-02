package net.atilist.harderthanwolves.compat.ami.rotarysieve;

import net.atilist.harderthanwolves.recipe.RotarySieveRecipe;
import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeHandler;
import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeWrapper;
import net.kozibrodka.wolves.compat.ami.turntable.TurntableRecipeWrapper;
import org.jetbrains.annotations.NotNull;

public class RotarySieveRecipeHandler implements RecipeHandler<RotarySieveRecipe> {
    @NotNull
    @Override
    public Class<RotarySieveRecipe> getRecipeClass() {
        return RotarySieveRecipe.class;
    }

    @NotNull
    @Override
    public String getRecipeCategoryUid() {
        return "rotary_sieve";
    }

    @NotNull
    @Override
    public RecipeWrapper getRecipeWrapper(@NotNull RotarySieveRecipe recipe) {
        return new RotarySieveRecipeWrapper(recipe);
    }

    @Override
    public boolean isRecipeValid(@NotNull RotarySieveRecipe recipe) {
        return true;
    }
}
