package net.atilist.harderthanwolves.events.init;

import net.atilist.harderthanwolves.utils.RecipeRemover;
import net.kozibrodka.wolves.recipe.CauldronCraftingManager;
import net.kozibrodka.wolves.recipe.CrucibleCraftingManager;
import net.kozibrodka.wolves.recipe.MillingRecipeRegistry;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;
import net.modificationstation.stationapi.api.recipe.CraftingRegistry;
import net.modificationstation.stationapi.api.recipe.SmeltingRegistry;
import net.modificationstation.stationapi.api.util.Identifier;

public class RecipeListener {

    @EventListener
    public void registerRecipes(RecipeRegisterEvent event) {

        Identifier type = event.recipeId;
        if (type == RecipeRegisterEvent.Vanilla.SMELTING.type()) {
            SmeltingRegistry.addSmeltingRecipe(new ItemStack(Item.APPLE, 1), new ItemStack(Block.WOOL));
        }
        addAllModRecipes(event);

    }

    public static void addAllModRecipes(RecipeRegisterEvent event) {
        Identifier type = event.recipeId;
        if (type == RecipeRegisterEvent.Vanilla.CRAFTING_SHAPED.type()) {
            removeVanillaRecipes();
            CraftingRegistry.addShapedRecipe(new ItemStack(Item.BED, 1), "XXX", "YYY", "Z Z", 'X', net.kozibrodka.wolves.events.ItemListener.hempCloth, 'Y', new ItemStack(Block.WOOL, 1, -1), 'Z', net.kozibrodka.wolves.events.BlockListener.moulding);
            addToolAndArmourRecipes();
        }
        if (type == RecipeRegisterEvent.Vanilla.CRAFTING_SHAPELESS.type()) {
            CraftingRegistry.addShapelessRecipe(new ItemStack(BlockListener.exampleBlock, 1), new ItemStack(Block.DIRT));
            CraftingRegistry.addShapelessRecipe(new ItemStack(BlockListener.exampleBlock2, 1), new ItemStack(Block.DIRT), new ItemStack(Block.DIRT));
            addMetallurgyRecipes();
        }
        addCrucibleRecipes();
        addCauldronRecipes();
        addMillingRecipes();
    }

    private static void removeVanillaRecipes() {
        RecipeRemover.removeRecipe(Item.DIAMOND_PICKAXE);
        RecipeRemover.removeRecipe(Item.DIAMOND_AXE);
        RecipeRemover.removeRecipe(Item.DIAMOND_HOE);
        RecipeRemover.removeRecipe(Item.DIAMOND_SHOVEL);
        RecipeRemover.removeRecipe(Item.DIAMOND_SWORD);

        RecipeRemover.removeRecipe(Item.DIAMOND_HELMET);
        RecipeRemover.removeRecipe(Item.DIAMOND_CHESTPLATE);
        RecipeRemover.removeRecipe(Item.DIAMOND_LEGGINGS);
        RecipeRemover.removeRecipe(Item.DIAMOND_BOOTS);

        RecipeRemover.removeRecipe(Item.BED);
    }

    private static void addMetallurgyRecipes() {
        CraftingRegistry.addShapelessRecipe(new ItemStack(ItemListener.rawDiamondIngot), new ItemStack(net.kozibrodka.wolves.events.ItemListener.coalDust), new ItemStack(Item.DIAMOND), new ItemStack(Item.IRON_INGOT));
        CraftingRegistry.addShapelessRecipe(new ItemStack(BlockListener.chiseledObsidian), new ItemStack(BlockListener.smoothObsidian), new ItemStack(Item.DIAMOND), new ItemStack(Item.GOLD_INGOT));
    }

    private static void addToolAndArmourRecipes() {
        // Tools
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.DIAMOND_PICKAXE, 1), "###", " X ", " X ", '#', ItemListener.diamondIngot, 'X', Item.STICK);
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.DIAMOND_AXE, 1), "##", "#X", " X", '#', ItemListener.diamondIngot, 'X', Item.STICK);
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.DIAMOND_AXE, 1), "##", "X#", "X ", '#', ItemListener.diamondIngot, 'X', Item.STICK);
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.DIAMOND_HOE, 1), "##", " X", " X", '#', ItemListener.diamondIngot, 'X', Item.STICK);
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.DIAMOND_HOE, 1), "##", "X ", "X ", '#', ItemListener.diamondIngot, 'X', Item.STICK);
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.DIAMOND_SHOVEL, 1), "#", "X", "X", '#', ItemListener.diamondIngot, 'X', Item.STICK);
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.DIAMOND_SWORD, 1), "#", "#", "X", '#', ItemListener.diamondIngot, 'X', Item.STICK);

        // Armour
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.DIAMOND_HELMET, 1), "###", "# #", '#', ItemListener.diamondIngot);
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.DIAMOND_CHESTPLATE, 1), "# #", "###", "###", '#', ItemListener.diamondIngot);
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.DIAMOND_LEGGINGS, 1), "###", "# #", "# #", '#', ItemListener.diamondIngot);
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.DIAMOND_BOOTS, 1), "# #", "# #", '#', ItemListener.diamondIngot);
    }

    private static void addCrucibleRecipes() {
        // Recycling (diamond)
        addCrucibleRecipe(new ItemStack(ItemListener.diamondIngot, 8), new ItemStack[] { new ItemStack(Item.DIAMOND_CHESTPLATE, 1, -1) });
        addCrucibleRecipe(new ItemStack(ItemListener.diamondIngot, 7), new ItemStack[] { new ItemStack(Item.DIAMOND_LEGGINGS, 1, -1) });
        addCrucibleRecipe(new ItemStack(ItemListener.diamondIngot, 5), new ItemStack[] { new ItemStack(Item.DIAMOND_HELMET, 1, -1) });
        addCrucibleRecipe(new ItemStack(ItemListener.diamondIngot, 4), new ItemStack[] { new ItemStack(Item.DIAMOND_BOOTS, 1, -1) });
        addCrucibleRecipe(new ItemStack(ItemListener.diamondIngot, 3), new ItemStack[] { new ItemStack(Item.DIAMOND_PICKAXE, 1, -1) });
        addCrucibleRecipe(new ItemStack(ItemListener.diamondIngot, 3), new ItemStack[] { new ItemStack(Item.DIAMOND_AXE, 1, -1) });
        addCrucibleRecipe(new ItemStack(ItemListener.diamondIngot, 2), new ItemStack[] { new ItemStack(Item.DIAMOND_SWORD, 1, -1) });
        addCrucibleRecipe(new ItemStack(ItemListener.diamondIngot, 2), new ItemStack[] { new ItemStack(Item.DIAMOND_HOE, 1, -1) });
        addCrucibleRecipe(new ItemStack(ItemListener.diamondIngot, 1), new ItemStack[] { new ItemStack(Item.DIAMOND_SHOVEL, 1, -1) });
    }

    private static void addCauldronRecipes() {
        addCauldronRecipe(new ItemStack(ItemListener.diamondIngot, 1), new ItemStack[] {
                new ItemStack(ItemListener.rawDiamondIngot, 1), new ItemStack(Item.GUNPOWDER)
        });
    }

    private static void addMillingRecipes() {
        MillingRecipeRegistry.getInstance().addMillingRecipe(Block.OBSIDIAN.id, new ItemStack(BlockListener.smoothObsidian, 1));
    }

    public static void addCrucibleRecipe(ItemStack output, ItemStack[] inputs) {
        CrucibleCraftingManager.getInstance().addRecipe(output, inputs);
    }

    public static void addCauldronRecipe(ItemStack output, ItemStack[] inputs) {
        CauldronCraftingManager.getInstance().addRecipe(output, inputs);
    }
}
