package net.atilist.harderthanwolves.compat.ami.metalforge;

import net.atilist.harderthanwolves.recipe.MetalForgeRecipe;
import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeWrapper;
import net.minecraft.client.Minecraft;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MetalForgeRecipeWrapper implements RecipeWrapper {
    private final MetalForgeRecipe recipe;

    public MetalForgeRecipeWrapper(MetalForgeRecipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public List<?> getInputs() {
        return List.of(recipe.getInput());
    }

    @Override
    public List<?> getOutputs() {
        return List.of(recipe.getOutput());
    }

    @Override
    public void drawInfo(@NotNull Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {

    }

    @Override
    public void drawAnimations(@NotNull Minecraft minecraft, int recipeWidth, int recipeHeight) {

    }

    @Nullable
    @Override
    public ArrayList<Object> getTooltip(int mouseX, int mouseY) {
        return null;
    }

    @Override
    public boolean handleClick(@NotNull Minecraft minecraft, int mouseX, int mouseY, int mouseButton) {
        return false;
    }
}
