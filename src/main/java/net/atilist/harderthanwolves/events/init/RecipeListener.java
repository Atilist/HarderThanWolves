package net.atilist.harderthanwolves.events.init;

import net.atilist.harderthanwolves.recipe.*;
import net.atilist.harderthanwolves.utils.RecipeRemover;
import net.kozibrodka.wolves.recipe.*;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;
import net.modificationstation.stationapi.api.recipe.CraftingRegistry;
import net.modificationstation.stationapi.api.util.Identifier;

public class RecipeListener {

    @EventListener
    public void registerRecipes(RecipeRegisterEvent event) {
        addAllModRecipes(event);
    }

    public static void addAllModRecipes(RecipeRegisterEvent event) {
        Identifier type = event.recipeId;
        if (type == RecipeRegisterEvent.Vanilla.CRAFTING_SHAPED.type()) {
            removeVanillaRecipes();
            CraftingRegistry.addShapedRecipe(new ItemStack(Item.BED, 1), "XXX", "YYY", "Z Z", 'X', net.kozibrodka.wolves.events.ItemListener.hempCloth, 'Y', new ItemStack(Block.WOOL, 1, -1), 'Z', net.kozibrodka.wolves.events.BlockListener.moulding);
            addToolAndArmourRecipes();
            addBlockRecipes();
            addInfusionRecipes();
        }
        if (type == RecipeRegisterEvent.Vanilla.CRAFTING_SHAPELESS.type()) {
            CraftingRegistry.addShapelessRecipe(new ItemStack(BlockListener.firePile), new ItemStack(net.kozibrodka.wolves.events.ItemListener.coalDust), new ItemStack(Block.PLANKS));
            CraftingRegistry.addShapelessRecipe(new ItemStack(BlockListener.mysticalInfuserBase), new ItemStack(BlockListener.mysticalCobblestone), new ItemStack(ItemListener.monsterCloth), new ItemStack(ItemListener.lapisIngot));
            CraftingRegistry.addShapelessRecipe(new ItemStack(BlockListener.monsterSiphonExpander), new ItemStack(BlockListener.mysticalCobblestone), new ItemStack(ItemListener.lapisIngot));
            CraftingRegistry.addShapelessRecipe(new ItemStack(BlockListener.obsidianGlassMixture), new ItemStack(Block.SAND), new ItemStack(ItemListener.obsidianDust));
            addMetallurgyRecipes();
            addToolUpgrades();
            addMillingRecipes();
            addReinforcedMillStoneRecipes();
            addReinforcedSawRecipes();
            addMysticalDevourerRecipes();
            addDiamondDrillRecipes();
            addCrucibleRecipes();
            addMetalForgeRecipes();
            addCauldronRecipes();
            addTurntableRecipes();
        }
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

    private static void addToolUpgrades() {
        CraftingRegistry.addShapelessRecipe(new ItemStack(ItemListener.reinforcedGoldenSword), new ItemStack(ItemListener.diamondIngot), new ItemStack(Item.GOLDEN_SWORD));
        CraftingRegistry.addShapelessRecipe(new ItemStack(ItemListener.reinforcedGoldenShovel), new ItemStack(ItemListener.diamondIngot), new ItemStack(Item.GOLDEN_SHOVEL));
        CraftingRegistry.addShapelessRecipe(new ItemStack(ItemListener.reinforcedGoldenPickaxe), new ItemStack(ItemListener.diamondIngot), new ItemStack(Item.GOLDEN_PICKAXE));
        CraftingRegistry.addShapelessRecipe(new ItemStack(ItemListener.reinforcedGoldenAxe), new ItemStack(ItemListener.diamondIngot), new ItemStack(Item.GOLDEN_AXE));
        CraftingRegistry.addShapelessRecipe(new ItemStack(ItemListener.reinforcedGoldenHoe), new ItemStack(ItemListener.diamondIngot), new ItemStack(Item.GOLDEN_HOE));
    }

    private static void addMetallurgyRecipes() {
        CraftingRegistry.addShapelessRecipe(new ItemStack(ItemListener.rawLapisIngot), new ItemStack(ItemListener.mysticalHempFibers), new ItemStack(ItemListener.crystallizedLapis), new ItemStack(Item.IRON_INGOT), new ItemStack(ItemListener.chargedMysticalRock), new ItemStack(ItemListener.mysticalStick));
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
    }

    private static void addBlockRecipes() {
        CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.stoneBricks, 1), "##", "##", '#', ItemListener.stoneBrick);
        CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.metalForge, 1), "XX", "##", 'X', Block.SLAB, '#', ItemListener.stoneBrick);
        CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.rotarySieve, 1), "XYX", "YZY", "XYX", 'X', net.kozibrodka.wolves.events.BlockListener.corner, 'Y', net.kozibrodka.wolves.events.ItemListener.wicker, 'Z', net.kozibrodka.wolves.events.ItemListener.grate);
        CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.ironChunkBlock, 1), "##", "##", '#', ItemListener.ironChunks);
        CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.goldChunkBlock, 1), "##", "##", '#', ItemListener.goldChunks);
        CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.emptyMonsterSiphon, 1), "XXX", "###", "XYX", 'X', BlockListener.mysticalCobblestone, 'Y', Item.GOLD_INGOT, '#', ItemListener.monsterCloth);
        CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.mysticalInfuser, 1), "XXX", "YZY", "###", 'X', ItemListener.lapisIngot, 'Y', BlockListener.mysticalCobblestone, 'Z', Block.CRAFTING_TABLE, '#', BlockListener.stoneBricks);
    }

    private static void addCrucibleRecipes() {
        // Recycling (diamond)
        addCrucibleRecipe(new ItemStack(ItemListener.diamondIngot, 8), new ItemStack[]{new ItemStack(Item.DIAMOND_CHESTPLATE, 1, -1)});
        addCrucibleRecipe(new ItemStack(ItemListener.diamondIngot, 7), new ItemStack[]{new ItemStack(Item.DIAMOND_LEGGINGS, 1, -1)});
        addCrucibleRecipe(new ItemStack(ItemListener.diamondIngot, 5), new ItemStack[]{new ItemStack(Item.DIAMOND_HELMET, 1, -1)});
        addCrucibleRecipe(new ItemStack(ItemListener.diamondIngot, 4), new ItemStack[]{new ItemStack(Item.DIAMOND_BOOTS, 1, -1)});
        addCrucibleRecipe(new ItemStack(ItemListener.diamondIngot, 3), new ItemStack[]{new ItemStack(Item.DIAMOND_PICKAXE, 1, -1)});
        addCrucibleRecipe(new ItemStack(ItemListener.diamondIngot, 3), new ItemStack[]{new ItemStack(Item.DIAMOND_AXE, 1, -1)});
        addCrucibleRecipe(new ItemStack(ItemListener.diamondIngot, 2), new ItemStack[]{new ItemStack(Item.DIAMOND_SWORD, 1, -1)});
        addCrucibleRecipe(new ItemStack(ItemListener.diamondIngot, 2), new ItemStack[]{new ItemStack(Item.DIAMOND_HOE, 1, -1)});
        addCrucibleRecipe(new ItemStack(ItemListener.diamondIngot, 1), new ItemStack[]{new ItemStack(Item.DIAMOND_SHOVEL, 1, -1)});
    }

    private static void addMetalForgeRecipes() {
        MetalForgeRecipeRegistry.getInstance().addForgingRecipe(BlockListener.ironOreGravel.id, new ItemStack(Item.IRON_INGOT, 1));
        MetalForgeRecipeRegistry.getInstance().addForgingRecipe(BlockListener.ironChunkBlock.id, new ItemStack(Item.IRON_INGOT, 1));
        MetalForgeRecipeRegistry.getInstance().addForgingRecipe(BlockListener.goldChunkBlock.id, new ItemStack(Item.GOLD_INGOT, 1));
        MetalForgeRecipeRegistry.getInstance().addForgingRecipe(BlockListener.lapisGravel.id, new ItemStack(ItemListener.crystallizedLapis, 36));
        MetalForgeRecipeRegistry.getInstance().addForgingRecipe(BlockListener.obsidianGlassMixture.id, new ItemStack(BlockListener.obsidianGlass, 1));
    }

    private static void addCauldronRecipes() {
        addCauldronRecipe(new ItemStack(ItemListener.lapisIngot, 1), new ItemStack[]{
                new ItemStack(ItemListener.rawLapisIngot, 1), new ItemStack(Item.FLINT), new ItemStack(net.kozibrodka.wolves.events.ItemListener.hempCloth)
        });
    }

    private static void addMillingRecipes() {
        MillingRecipeRegistry.getInstance().addMillingRecipe(Block.IRON_ORE.id, new ItemStack(BlockListener.ironOreGravel, 1));
        MillingRecipeRegistry.getInstance().addMillingRecipe(Block.GOLD_ORE.id, new ItemStack(BlockListener.goldOreGravel, 1));
        MillingRecipeRegistry.getInstance().addMillingRecipe(Block.LAPIS_BLOCK.id, new ItemStack(BlockListener.lapisGravel, 1));

        MillingRecipeRegistry.getInstance().addMillingRecipe(ItemListener.mysticalHemp.id, new ItemStack(ItemListener.mysticalHempFibers, 4));
    }

    private static void addTurntableRecipes() {
        TurntableRecipeRegistry.getInstance().addTurntableRecipe(BlockListener.rotarySieve, 0, new ItemStack(BlockListener.rotarySieve, 8, 1));
    }

    private static void addInfusionRecipes() {
        addShapelessInfusionRecipe(new ItemStack(ItemListener.diamondIngot, 1), new Object[]{
                ItemListener.rawDiamondIngot, Item.GUNPOWDER, Item.REDSTONE
        });
        addShapelessInfusionRecipe(new ItemStack(ItemListener.rawDiamondIngot, 1), new Object[]{
                new ItemStack(net.kozibrodka.wolves.events.ItemListener.coalDust), new ItemStack(Item.DIAMOND), new ItemStack(ItemListener.lapisIngot), new ItemStack(ItemListener.chargedMysticalRock)
        });
        addShapelessInfusionRecipe(new ItemStack(ItemListener.rawLapisIngot, 1), new Object[]{
                new ItemStack(ItemListener.mysticalHempFibers), new ItemStack(ItemListener.crystallizedLapis), new ItemStack(Item.IRON_INGOT), new ItemStack(ItemListener.mysticalStick)
        });

        // Diamond Tools
        addInfusionRecipe(new ItemStack(Item.DIAMOND_PICKAXE, 1), new Object[]{"###", "YXY", " X ", '#', ItemListener.diamondIngot, 'X', net.kozibrodka.wolves.events.BlockListener.moulding, 'Y', net.kozibrodka.wolves.events.ItemListener.hempFibers});
        addInfusionRecipe(new ItemStack(Item.DIAMOND_AXE, 1), new Object[]{"##Y", "#XY", " X ", '#', ItemListener.diamondIngot, 'X', net.kozibrodka.wolves.events.BlockListener.moulding, 'Y', net.kozibrodka.wolves.events.ItemListener.hempFibers});
        addInfusionRecipe(new ItemStack(Item.DIAMOND_AXE, 1), new Object[]{"Y##", "YX#", " X ", '#', ItemListener.diamondIngot, 'X', net.kozibrodka.wolves.events.BlockListener.moulding, 'Y', net.kozibrodka.wolves.events.ItemListener.hempFibers});
        addInfusionRecipe(new ItemStack(Item.DIAMOND_HOE, 1), new Object[]{"##Y", " XY", " X ", '#', ItemListener.diamondIngot, 'X', net.kozibrodka.wolves.events.BlockListener.moulding, 'Y', net.kozibrodka.wolves.events.ItemListener.hempFibers});
        addInfusionRecipe(new ItemStack(Item.DIAMOND_HOE, 1), new Object[]{"Y##", "YX ", " X ", '#', ItemListener.diamondIngot, 'X', net.kozibrodka.wolves.events.BlockListener.moulding, 'Y', net.kozibrodka.wolves.events.ItemListener.hempFibers});
        addInfusionRecipe(new ItemStack(Item.DIAMOND_SHOVEL, 1), new Object[]{"Y#Y", " X ", " X ", '#', ItemListener.diamondIngot, 'X', net.kozibrodka.wolves.events.BlockListener.moulding, 'Y', net.kozibrodka.wolves.events.ItemListener.hempFibers});
        addInfusionRecipe(new ItemStack(Item.DIAMOND_SWORD, 1), new Object[]{" # ", "Y#Y", " X ", '#', ItemListener.diamondIngot, 'X', net.kozibrodka.wolves.events.BlockListener.moulding, 'Y', net.kozibrodka.wolves.events.ItemListener.hempFibers});

        // Armour
        addInfusionRecipe(new ItemStack(Item.DIAMOND_HELMET, 1), new Object[]{"###", "# #", '#', ItemListener.diamondIngot});
        addInfusionRecipe(new ItemStack(Item.DIAMOND_CHESTPLATE, 1), new Object[]{"# #", "###", "###", '#', ItemListener.diamondIngot});
        addInfusionRecipe(new ItemStack(Item.DIAMOND_LEGGINGS, 1), new Object[]{"###", "# #", "# #", '#', ItemListener.diamondIngot});
        addInfusionRecipe(new ItemStack(Item.DIAMOND_BOOTS, 1), new Object[]{"# #", "# #", '#', ItemListener.diamondIngot});

        // Diamond age crafting components
        addInfusionRecipe(new ItemStack(ItemListener.diamondGear, 8), new Object[]{" X ", "XYX", " X ", 'X', ItemListener.diamondIngot, 'Y', ItemListener.obsidianPlate});
        addInfusionRecipe(new ItemStack(ItemListener.diamondGear, 4), new Object[]{" X ", "XYX", " X ", 'X', ItemListener.diamondIngot, 'Y', Block.OBSIDIAN});

        // Diamond age machines
        addInfusionRecipe(new ItemStack(BlockListener.reinforcedSaw, 1), new Object[]{"YYY", "XZX", "#X#", '#', Block.OBSIDIAN, 'X', Item.IRON_INGOT, 'Y', ItemListener.diamondIngot, 'Z', net.kozibrodka.wolves.events.BlockListener.saw});
        addInfusionRecipe(new ItemStack(BlockListener.reinforcedSaw, 1), new Object[]{"YYY", "XZX", "#X#", '#', ItemListener.obsidianPlate, 'X', Item.IRON_INGOT, 'Y', ItemListener.diamondIngot, 'Z', net.kozibrodka.wolves.events.BlockListener.saw});
        addInfusionRecipe(new ItemStack(BlockListener.reinforcedMillStone, 1), new Object[]{"YYY", "#Z#", "#X#", 'X', ItemListener.diamondGear, 'Y', Block.OBSIDIAN, 'Z', net.kozibrodka.wolves.events.BlockListener.millStone, '#', ItemListener.stoneBrick});
        addInfusionRecipe(new ItemStack(BlockListener.reinforcedMillStone, 1), new Object[]{"YYY", "#Z#", "#X#", 'X', ItemListener.diamondGear, 'Y', ItemListener.obsidianPlate, 'Z', net.kozibrodka.wolves.events.BlockListener.millStone, '#', ItemListener.stoneBrick});
        addInfusionRecipe(new ItemStack(BlockListener.diamondDrill, 1), new Object[]{" Y ", "YXY", "#Z#", '#', ItemListener.obsidianPlate, 'X', Item.IRON_INGOT, 'Y', ItemListener.diamondIngot, 'Z', ItemListener.diamondGear});
    }

    private static void addReinforcedMillStoneRecipes() {
        ReinforcedMillStoneRecipeRegistry.getInstance().addMillingRecipe(Block.OBSIDIAN.id, new ItemStack(ItemListener.obsidianDust, 1));
        ReinforcedMillStoneRecipeRegistry.getInstance().addMillingRecipe(ItemListener.obsidianChunk.id, new ItemStack(ItemListener.obsidianDust, 1));
    }

    private static void addReinforcedSawRecipes() {
        ReinforcedSawRecipeRegistry.getInstance().addSawingRecipe(Block.OBSIDIAN.id, new ItemStack(ItemListener.obsidianPlate, 4));
        ReinforcedSawRecipeRegistry.getInstance().addSawingRecipe(Block.STONE.id, new ItemStack(ItemListener.stoneBrick, 4));
    }

    private static void addMysticalDevourerRecipes() {
        MysticalDevourerRecipeRegistry.getInstance().addDevouringRecipe(Block.COBBLESTONE.id, new ItemStack(BlockListener.mysticalCobblestone, 1));
        MysticalDevourerRecipeRegistry.getInstance().addDevouringRecipe(Block.GRAVEL.id, new ItemStack(BlockListener.mysticalGravel, 1));
        MysticalDevourerRecipeRegistry.getInstance().addDevouringRecipe(Block.PLANKS.id, new ItemStack(ItemListener.mysticalStick, 2));
        MysticalDevourerRecipeRegistry.getInstance().addDevouringRecipe(net.kozibrodka.wolves.events.BlockListener.hempCrop.id, new ItemStack(ItemListener.mysticalHemp, 1));
        MysticalDevourerRecipeRegistry.getInstance().addDevouringRecipe(BlockListener.smoothObsidian.id, new ItemStack(BlockListener.engravedObsidian, 1));
    }

    private static void addDiamondDrillRecipes() {
        DiamondDrillRecipeRegistry.getInstance().addDrillingRecipe(Block.OBSIDIAN.id, new ItemStack(ItemListener.obsidianChunk, 4));
    }

    private static void addCrucibleRecipe(ItemStack output, ItemStack[] inputs) {
        CrucibleCraftingManager.getInstance().addRecipe(output, inputs);
    }

    private static void addCauldronRecipe(ItemStack output, ItemStack[] inputs) {
        CauldronCraftingManager.getInstance().addRecipe(output, inputs);
    }

    private static void addInfusionRecipe(ItemStack output, Object[] inputs) {
        MysticalInfuserCraftingManager.getInstance().addRecipe(output, inputs);
    }

    private static void addShapelessInfusionRecipe(ItemStack output, Object[] inputs) {
        MysticalInfuserCraftingManager.getInstance().addShapelessRecipe(output, inputs);
    }
}
