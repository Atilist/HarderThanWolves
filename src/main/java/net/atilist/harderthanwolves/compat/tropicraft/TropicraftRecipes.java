package net.atilist.harderthanwolves.compat.tropicraft;

import net.atilist.harderthanwolves.events.init.BlockListener;
import net.atilist.harderthanwolves.events.init.ItemListener;
import net.atilist.harderthanwolves.recipe.MetalForgeRecipeRegistry;
import net.danygames2014.tropicraft.Tropicraft;
import net.kozibrodka.wolves.recipe.CrucibleCraftingManager;
import net.kozibrodka.wolves.recipe.MillingRecipeRegistry;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;
import net.modificationstation.stationapi.api.recipe.CraftingRegistry;
import net.modificationstation.stationapi.api.util.Identifier;

public class TropicraftRecipes {

    public static void addAllTropicraftRecipes(RecipeRegisterEvent event) {
        Identifier type = event.recipeId;
        if (type == RecipeRegisterEvent.Vanilla.CRAFTING_SHAPED.type()) {
            addBlockRecipes();
            //addInfusionRecipes();
        }
        if (type == RecipeRegisterEvent.Vanilla.CRAFTING_SHAPELESS.type()) {
            addShapelessItemRecipes();
            addMillingRecipes();
            //addReinforcedMillStoneRecipes();
            //addReinforcedSawRecipes();
            //addMysticalDevourerRecipes();
            //addDiamondDrillRecipes();
            addCrucibleRecipes();
            addMetalForgeRecipes();
            //addCauldronRecipes();
            //addTurntableRecipes();
        }
    }

    private static void addBlockRecipes() {
        CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.tropifuelMixturePile, 1), "##", "##", '#', ItemListener.tropifuelMixture);
    }

    private static void addShapelessItemRecipes() {
        CraftingRegistry.addShapelessRecipe(new ItemStack(ItemListener.tropifuelMixture, 2), new ItemStack(Tropicraft.poisonousFrogSkin), new ItemStack(ItemListener.seashellDust));
    }

    private static void addMillingRecipes() {
        MillingRecipeRegistry.getInstance().addMillingRecipe(Tropicraft.froxShell.id, new ItemStack(ItemListener.seashellDust, 1));
        MillingRecipeRegistry.getInstance().addMillingRecipe(Tropicraft.pabShell.id, new ItemStack(ItemListener.seashellDust, 1));
        MillingRecipeRegistry.getInstance().addMillingRecipe(Tropicraft.rubeShell.id, new ItemStack(ItemListener.seashellDust, 1));
        MillingRecipeRegistry.getInstance().addMillingRecipe(Tropicraft.solonoxShell.id, new ItemStack(ItemListener.seashellDust, 1));
        MillingRecipeRegistry.getInstance().addMillingRecipe(Tropicraft.starfishShell.id, new ItemStack(ItemListener.seashellDust, 1));
    }

    private static void addCrucibleRecipes() {
        addCrucibleRecipe(new ItemStack(ItemListener.tropisteelIngot, 1), new ItemStack[]{new ItemStack(ItemListener.frogiumComposite), new ItemStack(net.kozibrodka.wolves.events.ItemListener.steel)});
    }

    private static void addMetalForgeRecipes() {
        MetalForgeRecipeRegistry.getInstance().addForgingRecipe(BlockListener.tropifuelMixturePile.id, new ItemStack(ItemListener.tropifuel, 1));
    }

    private static void addCrucibleRecipe(ItemStack output, ItemStack[] inputs) {
        CrucibleCraftingManager.getInstance().addRecipe(output, inputs);
    }
}
