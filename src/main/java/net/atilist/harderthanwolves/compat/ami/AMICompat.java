package net.atilist.harderthanwolves.compat.ami;

import net.atilist.harderthanwolves.compat.ami.diamonddrill.DiamondDrillRecipeCategory;
import net.atilist.harderthanwolves.compat.ami.diamonddrill.DiamondDrillRecipeHandler;
import net.atilist.harderthanwolves.compat.ami.infusion.InfusionShapedRecipeCategory;
import net.atilist.harderthanwolves.compat.ami.infusion.InfusionShapedRecipeHandler;
import net.atilist.harderthanwolves.compat.ami.infusion.InfusionShapelessRecipeCategory;
import net.atilist.harderthanwolves.compat.ami.infusion.InfusionShapelessRecipeHandler;
import net.atilist.harderthanwolves.compat.ami.metalforge.MetalForgeRecipeCategory;
import net.atilist.harderthanwolves.compat.ami.metalforge.MetalForgeRecipeHandler;
import net.atilist.harderthanwolves.compat.ami.mysticaldevourer.MysticalDevourerRecipeCategory;
import net.atilist.harderthanwolves.compat.ami.mysticaldevourer.MysticalDevourerRecipeHandler;
import net.atilist.harderthanwolves.compat.ami.reinforcedmillstone.ReinforcedMillStoneRecipeCategory;
import net.atilist.harderthanwolves.compat.ami.reinforcedmillstone.ReinforcedMillStoneRecipeHandler;
import net.atilist.harderthanwolves.compat.ami.reinforcedsaw.ReinforcedSawRecipeCategory;
import net.atilist.harderthanwolves.compat.ami.reinforcedsaw.ReinforcedSawRecipeHandler;
import net.atilist.harderthanwolves.compat.ami.rotarysieve.RotarySieveRecipeCategory;
import net.atilist.harderthanwolves.compat.ami.rotarysieve.RotarySieveRecipeHandler;
import net.atilist.harderthanwolves.recipe.*;
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
        return ConfigListener.MOD_ID.id("harderthanwolves");
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

        registry.addRecipeCategories(new ReinforcedSawRecipeCategory());
        registry.addRecipeHandlers(new ReinforcedSawRecipeHandler());
        registry.addRecipes(ReinforcedSawRecipeRegistry.getInstance().getRecipes());

        registry.addRecipeCategories(new ReinforcedMillStoneRecipeCategory());
        registry.addRecipeHandlers(new ReinforcedMillStoneRecipeHandler());
        registry.addRecipes(ReinforcedMillStoneRecipeRegistry.getInstance().getRecipes());

        registry.addRecipeCategories(new MysticalDevourerRecipeCategory());
        registry.addRecipeHandlers(new MysticalDevourerRecipeHandler());
        registry.addRecipes(MysticalDevourerRecipeRegistry.getInstance().getRecipes());

        registry.addRecipeCategories(new DiamondDrillRecipeCategory());
        registry.addRecipeHandlers(new DiamondDrillRecipeHandler());
        registry.addRecipes(DiamondDrillRecipeRegistry.getInstance().getRecipes());

        registry.addRecipeCategories(new MetalForgeRecipeCategory());
        registry.addRecipeHandlers(new MetalForgeRecipeHandler());
        registry.addRecipes(MetalForgeRecipeRegistry.getInstance().getRecipes());

        registry.addRecipeCategories(new RotarySieveRecipeCategory());
        registry.addRecipeHandlers(new RotarySieveRecipeHandler());
        registry.addRecipes(RotarySieveRecipeRegistry.getInstance().getRecipes());
    }

    @Override
    public void onRecipeRegistryAvailable(RecipeRegistry recipeRegistry) {

    }

    @Override
    public SyncableRecipe deserializeRecipe(NbtCompound recipe) {
        return null;
    }
}
