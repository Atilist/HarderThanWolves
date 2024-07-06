package net.atilist.harderthanwolves.events.init;

import net.atilist.harderthanwolves.utils.RecipeRemover;
import net.kozibrodka.wolves.recipe.CauldronCraftingManager;
import net.kozibrodka.wolves.recipe.CrucibleCraftingManager;
import net.kozibrodka.wolves.recipe.MillingRecipeRegistry;
import net.kozibrodka.wolves.recipe.TurntableRecipeRegistry;
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
            addBlockRecipes();
        }
        if (type == RecipeRegisterEvent.Vanilla.CRAFTING_SHAPELESS.type()) {
            CraftingRegistry.addShapelessRecipe(new ItemStack(BlockListener.firePile), new ItemStack(net.kozibrodka.wolves.events.ItemListener.coalDust), new ItemStack(Block.PLANKS));
            addMetallurgyRecipes();
        }
        addCrucibleRecipes();
        addCauldronRecipes();
        addMillingRecipes();
        addTurntableRecipes();
    }

    private static void removeVanillaRecipes() {
        // Stone tools
        RecipeRemover.removeRecipe(Item.STONE_PICKAXE);
        RecipeRemover.removeRecipe(Item.STONE_AXE);
        RecipeRemover.removeRecipe(Item.STONE_HOE);
        RecipeRemover.removeRecipe(Item.STONE_SHOVEL);
        RecipeRemover.removeRecipe(Item.STONE_SWORD);

        // Iron tools
        RecipeRemover.removeRecipe(Item.IRON_PICKAXE);
        RecipeRemover.removeRecipe(Item.IRON_AXE);
        RecipeRemover.removeRecipe(Item.IRON_HOE);
        RecipeRemover.removeRecipe(Item.IRON_SHOVEL);
        RecipeRemover.removeRecipe(Item.IRON_SWORD);

        // Gold tools
        RecipeRemover.removeRecipe(Item.GOLDEN_PICKAXE);
        RecipeRemover.removeRecipe(Item.GOLDEN_AXE);
        RecipeRemover.removeRecipe(Item.GOLDEN_HOE);
        RecipeRemover.removeRecipe(Item.GOLDEN_SHOVEL);
        RecipeRemover.removeRecipe(Item.GOLDEN_SWORD);

        // Diamond tools and armour
        RecipeRemover.removeRecipe(Item.DIAMOND_PICKAXE);
        RecipeRemover.removeRecipe(Item.DIAMOND_AXE);
        RecipeRemover.removeRecipe(Item.DIAMOND_HOE);
        RecipeRemover.removeRecipe(Item.DIAMOND_SHOVEL);
        RecipeRemover.removeRecipe(Item.DIAMOND_SWORD);
        RecipeRemover.removeRecipe(Item.DIAMOND_HELMET);
        RecipeRemover.removeRecipe(Item.DIAMOND_CHESTPLATE);
        RecipeRemover.removeRecipe(Item.DIAMOND_LEGGINGS);
        RecipeRemover.removeRecipe(Item.DIAMOND_BOOTS);

        // Crafting blocks / furniture
        RecipeRemover.removeRecipe(Item.BED);

        // Ore smelting
        RecipeRemover.removeSmeltingRecipe(Block.IRON_ORE.id);
        RecipeRemover.removeSmeltingRecipe(Block.GOLD_ORE.id);
    }

    private static void addMetallurgyRecipes() {
        CraftingRegistry.addShapelessRecipe(new ItemStack(ItemListener.rawDiamondIngot), new ItemStack(net.kozibrodka.wolves.events.ItemListener.coalDust), new ItemStack(Item.DIAMOND), new ItemStack(Item.IRON_INGOT));
        CraftingRegistry.addShapelessRecipe(new ItemStack(BlockListener.chiseledObsidian), new ItemStack(BlockListener.smoothObsidian), new ItemStack(Item.DIAMOND), new ItemStack(Item.GOLD_INGOT));
        CraftingRegistry.addShapelessRecipe(new ItemStack(ItemListener.ironChunks, 4), new ItemStack(BlockListener.ironChunkBlock));
        CraftingRegistry.addShapelessRecipe(new ItemStack(ItemListener.goldChunks, 4), new ItemStack(BlockListener.goldChunkBlock));
    }

    private static void addToolAndArmourRecipes() {
        // Stone Tools
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.STONE_PICKAXE, 1), "###", "YXY", " X ", '#', Block.COBBLESTONE, 'X', Item.STICK, 'Y', net.kozibrodka.wolves.events.ItemListener.hempFibers);
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.STONE_AXE, 1), "##Y", "#XY", " X ", '#', Block.COBBLESTONE, 'X', Item.STICK, 'Y', net.kozibrodka.wolves.events.ItemListener.hempFibers);
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.STONE_AXE, 1), "Y##", "YX#", " X ", '#', Block.COBBLESTONE, 'X', Item.STICK, 'Y', net.kozibrodka.wolves.events.ItemListener.hempFibers);
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.STONE_HOE, 1), "##Y", " XY", " X ", '#', Block.COBBLESTONE, 'X', Item.STICK, 'Y', net.kozibrodka.wolves.events.ItemListener.hempFibers);
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.STONE_HOE, 1), "Y##", "YX ", " X ", '#', Block.COBBLESTONE, 'X', Item.STICK, 'Y', net.kozibrodka.wolves.events.ItemListener.hempFibers);
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.STONE_SHOVEL, 1), "Y#Y", " X ", " X ", '#', Block.COBBLESTONE, 'X', Item.STICK, 'Y', net.kozibrodka.wolves.events.ItemListener.hempFibers);
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.STONE_SWORD, 1), " # ", "Y#Y", " X ", '#', Block.COBBLESTONE, 'X', Item.STICK, 'Y', net.kozibrodka.wolves.events.ItemListener.hempFibers);
        CraftingRegistry.addShapedRecipe(new ItemStack(ItemListener.stoneChisel, 1), "Y#Y", " X ", '#', Block.COBBLESTONE, 'X', Item.STICK, 'Y', net.kozibrodka.wolves.events.ItemListener.hempFibers);

        // Iron Tools
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.IRON_PICKAXE, 1), "###", "YXY", " X ", '#', Item.IRON_INGOT, 'X', net.kozibrodka.wolves.events.BlockListener.moulding, 'Y', net.kozibrodka.wolves.events.ItemListener.hempFibers);
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.IRON_AXE, 1), "##Y", "#XY", " X ", '#', Item.IRON_INGOT, 'X', net.kozibrodka.wolves.events.BlockListener.moulding, 'Y', net.kozibrodka.wolves.events.ItemListener.hempFibers);
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.IRON_AXE, 1), "Y##", "YX#", " X ", '#', Item.IRON_INGOT, 'X', net.kozibrodka.wolves.events.BlockListener.moulding, 'Y', net.kozibrodka.wolves.events.ItemListener.hempFibers);
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.IRON_HOE, 1), "##Y", " XY", " X ", '#', Item.IRON_INGOT, 'X', net.kozibrodka.wolves.events.BlockListener.moulding, 'Y', net.kozibrodka.wolves.events.ItemListener.hempFibers);
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.IRON_HOE, 1), "Y##", "YX ", " X ", '#', Item.IRON_INGOT, 'X', net.kozibrodka.wolves.events.BlockListener.moulding, 'Y', net.kozibrodka.wolves.events.ItemListener.hempFibers);
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.IRON_SHOVEL, 1), "Y#Y", " X ", " X ", '#', Item.IRON_INGOT, 'X', net.kozibrodka.wolves.events.BlockListener.moulding, 'Y', net.kozibrodka.wolves.events.ItemListener.hempFibers);
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.IRON_SWORD, 1), " # ", "Y#Y", " X ", '#', Item.IRON_INGOT, 'X', net.kozibrodka.wolves.events.BlockListener.moulding, 'Y', net.kozibrodka.wolves.events.ItemListener.hempFibers);

        // Gold Tools
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.GOLDEN_PICKAXE, 1), "###", "YXY", " X ", '#', Item.GOLD_INGOT, 'X', ItemListener.mysticalStick, 'Y', ItemListener.mysticalHempFibers);
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.GOLDEN_AXE, 1), "##Y", "#XY", " X ", '#', Item.GOLD_INGOT, 'X', Item.STICK, 'Y', net.kozibrodka.wolves.events.ItemListener.hempFibers);
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.GOLDEN_AXE, 1), "Y##", "YX#", " X ", '#', Item.GOLD_INGOT, 'X', Item.STICK, 'Y', net.kozibrodka.wolves.events.ItemListener.hempFibers);
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.GOLDEN_HOE, 1), "##Y", " XY", " X ", '#', Item.GOLD_INGOT, 'X', ItemListener.mysticalStick, 'Y', net.kozibrodka.wolves.events.ItemListener.hempFibers);
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.GOLDEN_HOE, 1), "Y##", "YX ", " X ", '#', Item.GOLD_INGOT, 'X', ItemListener.mysticalStick, 'Y', net.kozibrodka.wolves.events.ItemListener.hempFibers);
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.GOLDEN_SHOVEL, 1), "Y#Y", " X ", " X ", '#', Item.GOLD_INGOT, 'X', ItemListener.mysticalStick, 'Y', ItemListener.mysticalHempFibers);
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.GOLDEN_SWORD, 1), " # ", "Y#Y", " X ", '#', Item.GOLD_INGOT, 'X', ItemListener.mysticalStick, 'Y', ItemListener.mysticalHempFibers);

        // Diamond Tools
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.DIAMOND_PICKAXE, 1), "###", "YXY", " X ", '#', ItemListener.diamondIngot, 'X', net.kozibrodka.wolves.events.BlockListener.moulding, 'Y', net.kozibrodka.wolves.events.ItemListener.hempFibers);
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.DIAMOND_AXE, 1), "##Y", "#XY", " X ", '#', ItemListener.diamondIngot, 'X', net.kozibrodka.wolves.events.BlockListener.moulding, 'Y', net.kozibrodka.wolves.events.ItemListener.hempFibers);
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.DIAMOND_AXE, 1), "Y##", "YX#", " X ", '#', ItemListener.diamondIngot, 'X', net.kozibrodka.wolves.events.BlockListener.moulding, 'Y', net.kozibrodka.wolves.events.ItemListener.hempFibers);
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.DIAMOND_HOE, 1), "##Y", " XY", " X ", '#', ItemListener.diamondIngot, 'X', net.kozibrodka.wolves.events.BlockListener.moulding, 'Y', net.kozibrodka.wolves.events.ItemListener.hempFibers);
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.DIAMOND_HOE, 1), "Y##", "YX ", " X ", '#', ItemListener.diamondIngot, 'X', net.kozibrodka.wolves.events.BlockListener.moulding, 'Y', net.kozibrodka.wolves.events.ItemListener.hempFibers);
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.DIAMOND_SHOVEL, 1), "Y#Y", " X ", " X ", '#', ItemListener.diamondIngot, 'X', net.kozibrodka.wolves.events.BlockListener.moulding, 'Y', net.kozibrodka.wolves.events.ItemListener.hempFibers);
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.DIAMOND_SWORD, 1), " # ", "Y#Y", " X ", '#', ItemListener.diamondIngot, 'X', net.kozibrodka.wolves.events.BlockListener.moulding, 'Y', net.kozibrodka.wolves.events.ItemListener.hempFibers);

        // Armour
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.DIAMOND_HELMET, 1), "###", "# #", '#', ItemListener.diamondIngot);
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.DIAMOND_CHESTPLATE, 1), "# #", "###", "###", '#', ItemListener.diamondIngot);
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.DIAMOND_LEGGINGS, 1), "###", "# #", "# #", '#', ItemListener.diamondIngot);
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.DIAMOND_BOOTS, 1), "# #", "# #", '#', ItemListener.diamondIngot);
    }

    private static void addBlockRecipes() {
        CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.stoneBricks, 1), "##", "##", '#', ItemListener.stoneBrick);
        CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.metalForge, 1), "XX", "##", 'X', Block.SLAB, '#', ItemListener.stoneBrick);
        CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.rotarySieve, 1), "XYX", "YZY", "XYX", 'X', net.kozibrodka.wolves.events.BlockListener.corner, 'Y', net.kozibrodka.wolves.events.ItemListener.wicker, 'Z', net.kozibrodka.wolves.events.ItemListener.grate);
        CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.ironChunkBlock, 1), "##", "##", '#', ItemListener.ironChunks);
        CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.goldChunkBlock, 1), "##", "##", '#', ItemListener.goldChunks);
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

        MillingRecipeRegistry.getInstance().addMillingRecipe(Block.IRON_ORE.id, new ItemStack(BlockListener.ironOreGravel, 1));
        MillingRecipeRegistry.getInstance().addMillingRecipe(Block.GOLD_ORE.id, new ItemStack(BlockListener.goldOreGravel, 1));

        MillingRecipeRegistry.getInstance().addMillingRecipe(ItemListener.mysticalHemp.id, new ItemStack(ItemListener.mysticalHempFibers, 4));
    }

    private static void addTurntableRecipes() {
        TurntableRecipeRegistry.getInstance().addTurntableRecipe(BlockListener.rotarySieve, 0, new ItemStack(BlockListener.rotarySieve, 8, 1));
    }

    private static void addCrucibleRecipe(ItemStack output, ItemStack[] inputs) {
        CrucibleCraftingManager.getInstance().addRecipe(output, inputs);
    }

    private static void addCauldronRecipe(ItemStack output, ItemStack[] inputs) {
        CauldronCraftingManager.getInstance().addRecipe(output, inputs);
    }
}
