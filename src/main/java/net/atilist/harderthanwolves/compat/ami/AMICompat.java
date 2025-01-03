package net.atilist.harderthanwolves.compat.ami;

import net.atilist.harderthanwolves.compat.ami.infusion.InfusionShapedRecipeCategory;
import net.atilist.harderthanwolves.compat.ami.infusion.InfusionShapedRecipeHandler;
import net.atilist.harderthanwolves.compat.ami.infusion.InfusionShapelessRecipeCategory;
import net.atilist.harderthanwolves.compat.ami.infusion.InfusionShapelessRecipeHandler;
import net.atilist.harderthanwolves.recipe.MysticalInfuserCraftingManager;
import net.glasslauncher.mods.alwaysmoreitems.api.*;
import net.kozibrodka.wolves.events.ConfigListener;
import net.minecraft.nbt.NbtCompound;
import net.modificationstation.stationapi.api.util.Identifier;

public class AMICompat implements ModPluginProvider {

    @Override
    public String getName() {
        return "Harder Than Wolves";
    }

    @Override
    public Identifier getId() {
        return ConfigListener.MOD_ID.id("hardertahnwolves");
    }

    @Override
    public void onAMIHelpersAvailable(AMIHelpers amiHelpers) {

    }

    @Override
    public void onItemRegistryAvailable(ItemRegistry itemRegistry) {

    }

    @Override
    public void register(ModRegistry registry) {
        registry.addRecipeCategories(new InfusionShapedRecipeCategory());
        registry.addRecipeHandlers(new InfusionShapedRecipeHandler());
        registry.addRecipes(MysticalInfuserCraftingManager.getInstance().getShapedRecipes());

        registry.addRecipeCategories(new InfusionShapelessRecipeCategory());
        registry.addRecipeHandlers(new InfusionShapelessRecipeHandler());
        registry.addRecipes(MysticalInfuserCraftingManager.getInstance().getShapelessRecipes());
    }

    @Override
    public void onRecipeRegistryAvailable(RecipeRegistry recipeRegistry) {

    }

    @Override
    public SyncableRecipe deserializeRecipe(NbtCompound recipe) {
        return null;
    }
}
