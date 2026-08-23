package net.atilist.harderthanwolves.events.init;

import net.atilist.harderthanwolves.recipe.DiamondDrillRecipeRegistry;
import net.atilist.harderthanwolves.recipe.MetalForgeRecipeRegistry;
import net.atilist.harderthanwolves.recipe.MysticalDevourerRecipeRegistry;
import net.atilist.harderthanwolves.recipe.MysticalInfuserCraftingManager;
import net.atilist.harderthanwolves.recipe.ReinforcedMillStoneRecipeRegistry;
import net.atilist.harderthanwolves.recipe.ReinforcedSawRecipeRegistry;
import net.atilist.harderthanwolves.recipe.RotarySieveRecipeRegistry;
import net.atilist.harderthanwolves.utils.RecipeRemover;
import net.kozibrodka.wolves.recipe.CauldronCraftingManager;
import net.kozibrodka.wolves.recipe.CrucibleCraftingManager;
import net.kozibrodka.wolves.recipe.MillingRecipeRegistry;
import net.kozibrodka.wolves.recipe.TurntableByproduct;
import net.kozibrodka.wolves.recipe.TurntableInput;
import net.kozibrodka.wolves.recipe.TurntableOutput;
import net.kozibrodka.wolves.recipe.TurntableRecipe;
import net.kozibrodka.wolves.recipe.TurntableRecipeRegistry;
import net.kozibrodka.wolves.recipe.TurntableResult;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;
import net.modificationstation.stationapi.api.recipe.CraftingRegistry;
import net.modificationstation.stationapi.api.registry.BlockRegistry;
import net.modificationstation.stationapi.api.registry.ItemRegistry;
import net.modificationstation.stationapi.api.tag.TagKey;
import net.modificationstation.stationapi.api.util.Identifier;

public class RecipeListener {
    public static final String
            ALUMINIUM = "aluminium",
            BISMUTH = "bismuth",
            BORON = "boron",
            BRASS = "brass",
            BRONZE = "bronze",
            CHROME = "chrome",
            COBALT = "cobalt",
            COPPER = "copper",
            CUPRONICKEL = "cupronickel",
            LEAD = "lead",
            MAGNET = "magnet",
            NICKEL = "nickel",
            OSMIUM = "osmium",
            PLATINUM = "platinum",
            SILICON = "silicon",
            SILVER = "silver",
            STEEL = "steel",
            TIN = "tin",
            TITANIUM = "titanium",
            TUNGSTEN = "tungsten",
            URANIUM = "uranium",
            ZINC = "zinc",
            EMERALD = "emerald",
            ONYX = "onyx",
            RUBY = "ruby",
            SAPPHIRE = "sapphire",
            DIAMOND = "diamond",
            IRON = "iron",
            GOLD = "gold",
            REDSTONE = "redstone",
            LAPIS = "lapis",
            COAL = "coal",
            OBSIDIAN = "obsidian";

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
            CraftingRegistry.addShapelessRecipe(new ItemStack(BlockListener.firePile), getDustTagKey(COAL), getPlanksTagKey());
            CraftingRegistry.addShapelessRecipe(new ItemStack(BlockListener.mysticalInfuserBase), new ItemStack(BlockListener.mysticalCobblestone), new ItemStack(ItemListener.monsterCloth), getIngotTagKey(LAPIS));
            CraftingRegistry.addShapelessRecipe(new ItemStack(BlockListener.monsterSiphonExpander), new ItemStack(BlockListener.mysticalCobblestone), getIngotTagKey(LAPIS));
            CraftingRegistry.addShapelessRecipe(new ItemStack(BlockListener.obsidianGlassMixture), getSandTagKey(), getDustTagKey(OBSIDIAN));
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
            addRotarySieveRecipes();
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
        CraftingRegistry.addShapelessRecipe(new ItemStack(ItemListener.reinforcedGoldenSword), getIngotTagKey(DIAMOND), new ItemStack(Item.GOLDEN_SWORD, 1, -1));
        CraftingRegistry.addShapelessRecipe(new ItemStack(ItemListener.reinforcedGoldenShovel), getIngotTagKey(DIAMOND), new ItemStack(Item.GOLDEN_SHOVEL, 1, -1));
        CraftingRegistry.addShapelessRecipe(new ItemStack(ItemListener.reinforcedGoldenPickaxe), getIngotTagKey(DIAMOND), new ItemStack(Item.GOLDEN_PICKAXE, 1, -1));
        CraftingRegistry.addShapelessRecipe(new ItemStack(ItemListener.reinforcedGoldenAxe), getIngotTagKey(DIAMOND), new ItemStack(Item.GOLDEN_AXE, 1, -1));
        CraftingRegistry.addShapelessRecipe(new ItemStack(ItemListener.reinforcedGoldenHoe), getIngotTagKey(DIAMOND), new ItemStack(Item.GOLDEN_HOE, 1, -1));
    }

    private static void addMetallurgyRecipes() {
        CraftingRegistry.addShapelessRecipe(new ItemStack(ItemListener.rawLapisIngot), new ItemStack(ItemListener.mysticalHempFibers), new ItemStack(ItemListener.crystallizedLapis), getIngotTagKey(IRON), new ItemStack(ItemListener.chargedMysticalRock), new ItemStack(ItemListener.mysticalStick));
        CraftingRegistry.addShapelessRecipe(new ItemStack(BlockListener.chiseledObsidian), new ItemStack(BlockListener.smoothObsidian), getGemTagKey(DIAMOND), getIngotTagKey(GOLD), new ItemStack(ItemListener.obsidianChunk));
        CraftingRegistry.addShapelessRecipe(new ItemStack(ItemListener.ironChunks, 4), new ItemStack(BlockListener.ironChunkBlock));
        CraftingRegistry.addShapelessRecipe(new ItemStack(ItemListener.goldChunks, 4), new ItemStack(BlockListener.goldChunkBlock));
    }

    private static void addToolAndArmourRecipes() {
        // Stone Tools
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.STONE_PICKAXE, 1), "###", "YXY", " X ", '#', getCobblestoneTagKey(), 'X', getStickTagKey(), 'Y', net.kozibrodka.wolves.events.ItemListener.hempFibers);
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.STONE_AXE, 1), "##Y", "#XY", " X ", '#', getCobblestoneTagKey(), 'X', getStickTagKey(), 'Y', net.kozibrodka.wolves.events.ItemListener.hempFibers);
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.STONE_AXE, 1), "Y##", "YX#", " X ", '#', getCobblestoneTagKey(), 'X', getStickTagKey(), 'Y', net.kozibrodka.wolves.events.ItemListener.hempFibers);
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.STONE_HOE, 1), "##Y", " XY", " X ", '#', getCobblestoneTagKey(), 'X', getStickTagKey(), 'Y', net.kozibrodka.wolves.events.ItemListener.hempFibers);
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.STONE_HOE, 1), "Y##", "YX ", " X ", '#', getCobblestoneTagKey(), 'X', getStickTagKey(), 'Y', net.kozibrodka.wolves.events.ItemListener.hempFibers);
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.STONE_SHOVEL, 1), "Y#Y", " X ", " X ", '#', getCobblestoneTagKey(), 'X', getStickTagKey(), 'Y', net.kozibrodka.wolves.events.ItemListener.hempFibers);
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.STONE_SWORD, 1), " # ", "Y#Y", " X ", '#', getCobblestoneTagKey(), 'X', getStickTagKey(), 'Y', net.kozibrodka.wolves.events.ItemListener.hempFibers);
        CraftingRegistry.addShapedRecipe(new ItemStack(ItemListener.stoneChisel, 1), "Y#Y", " X ", '#', getCobblestoneTagKey(), 'X', getStickTagKey(), 'Y', net.kozibrodka.wolves.events.ItemListener.hempFibers);

        // Iron Tools
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.IRON_PICKAXE, 1), "###", "YXY", " X ", '#', getIngotTagKey(IRON), 'X', net.kozibrodka.wolves.events.BlockListener.moulding, 'Y', net.kozibrodka.wolves.events.ItemListener.hempFibers);
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.IRON_AXE, 1), "##Y", "#XY", " X ", '#', getIngotTagKey(IRON), 'X', net.kozibrodka.wolves.events.BlockListener.moulding, 'Y', net.kozibrodka.wolves.events.ItemListener.hempFibers);
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.IRON_AXE, 1), "Y##", "YX#", " X ", '#', getIngotTagKey(IRON), 'X', net.kozibrodka.wolves.events.BlockListener.moulding, 'Y', net.kozibrodka.wolves.events.ItemListener.hempFibers);
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.IRON_HOE, 1), "##Y", " XY", " X ", '#', getIngotTagKey(IRON), 'X', net.kozibrodka.wolves.events.BlockListener.moulding, 'Y', net.kozibrodka.wolves.events.ItemListener.hempFibers);
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.IRON_HOE, 1), "Y##", "YX ", " X ", '#', getIngotTagKey(IRON), 'X', net.kozibrodka.wolves.events.BlockListener.moulding, 'Y', net.kozibrodka.wolves.events.ItemListener.hempFibers);
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.IRON_SHOVEL, 1), "Y#Y", " X ", " X ", '#', getIngotTagKey(IRON), 'X', net.kozibrodka.wolves.events.BlockListener.moulding, 'Y', net.kozibrodka.wolves.events.ItemListener.hempFibers);
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.IRON_SWORD, 1), " # ", "Y#Y", " X ", '#', getIngotTagKey(IRON), 'X', net.kozibrodka.wolves.events.BlockListener.moulding, 'Y', net.kozibrodka.wolves.events.ItemListener.hempFibers);
        CraftingRegistry.addShapedRecipe(new ItemStack(ItemListener.ironChisel, 1), "Y#Y", " X ", '#', getIngotTagKey(IRON), 'X', net.kozibrodka.wolves.events.BlockListener.moulding, 'Y', net.kozibrodka.wolves.events.ItemListener.hempFibers);

        // Gold Tools
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.GOLDEN_PICKAXE, 1), "###", "YXY", " X ", '#', getIngotTagKey(GOLD), 'X', ItemListener.mysticalStick, 'Y', ItemListener.mysticalHempFibers);
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.GOLDEN_AXE, 1), "##Y", "#XY", " X ", '#', getIngotTagKey(GOLD), 'X', getStickTagKey(), 'Y', net.kozibrodka.wolves.events.ItemListener.hempFibers);
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.GOLDEN_AXE, 1), "Y##", "YX#", " X ", '#', getIngotTagKey(GOLD), 'X', getStickTagKey(), 'Y', net.kozibrodka.wolves.events.ItemListener.hempFibers);
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.GOLDEN_HOE, 1), "##Y", " XY", " X ", '#', getIngotTagKey(GOLD), 'X', ItemListener.mysticalStick, 'Y', net.kozibrodka.wolves.events.ItemListener.hempFibers);
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.GOLDEN_HOE, 1), "Y##", "YX ", " X ", '#', getIngotTagKey(GOLD), 'X', ItemListener.mysticalStick, 'Y', net.kozibrodka.wolves.events.ItemListener.hempFibers);
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.GOLDEN_SHOVEL, 1), "Y#Y", " X ", " X ", '#', getIngotTagKey(GOLD), 'X', ItemListener.mysticalStick, 'Y', ItemListener.mysticalHempFibers);
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.GOLDEN_SWORD, 1), " # ", "Y#Y", " X ", '#', getIngotTagKey(GOLD), 'X', ItemListener.mysticalStick, 'Y', ItemListener.mysticalHempFibers);
    }

    private static void addBlockRecipes() {
        CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.stoneBricks, 1), "##", "##", '#', ItemListener.stoneBrick);
        CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.metalForge, 1), "XX", "##", 'X', Block.SLAB, '#', ItemListener.stoneBrick);
        CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.rotarySieve, 1), "XYX", "YZY", "XYX", 'X', net.kozibrodka.wolves.events.BlockListener.corner, 'Y', net.kozibrodka.wolves.events.ItemListener.wicker, 'Z', net.kozibrodka.wolves.events.ItemListener.grate);
        CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.ironChunkBlock, 1), "##", "##", '#', ItemListener.ironChunks);
        CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.goldChunkBlock, 1), "##", "##", '#', ItemListener.goldChunks);
        CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.emptyMonsterSiphon, 1), "XXX", "###", "XYX", 'X', BlockListener.mysticalCobblestone, 'Y', getIngotTagKey(GOLD), '#', ItemListener.monsterCloth);
        CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.mysticalInfuser, 1), "XXX", "YZY", "###", 'X', getIngotTagKey(LAPIS), 'Y', BlockListener.mysticalCobblestone, 'Z', Block.CRAFTING_TABLE, '#', BlockListener.stoneBricks);
        CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.smoothObsidian, 2), " X ", "XYX", " X ", 'X', ItemListener.obsidianPlate, 'Y', getDustTagKey(OBSIDIAN));
        CraftingRegistry.addShapedRecipe(new ItemStack(net.kozibrodka.wolves.events.BlockListener.blockDispenser), "###", "# #", "YXY", '#', Block.MOSSY_COBBLESTONE, 'X', getDustTagKey(REDSTONE), 'Y', ItemListener.stoneBrick);
        CraftingRegistry.addShapedRecipe(new ItemStack(net.kozibrodka.wolves.events.BlockListener.detectorBlock), "X#X", "###", "ZYZ", '#', getCobblestoneTagKey(), 'X', getIngotTagKey(LAPIS), 'Y', getDustTagKey(REDSTONE), 'Z', ItemListener.stoneBrick);
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

        // Soulforged Steel
        addCrucibleRecipe(new ItemStack(net.kozibrodka.wolves.events.ItemListener.steel, 4), new ItemStack[]{new ItemStack(Item.IRON_INGOT, 3), new ItemStack(net.kozibrodka.wolves.events.ItemListener.concentratedHellfire), new ItemStack(net.kozibrodka.wolves.events.ItemListener.coalDust), new ItemStack(ItemListener.obsidianDust)});
    }

    private static void addMetalForgeRecipes() {
        MetalForgeRecipeRegistry.getInstance().addForgingRecipe(BlockListener.NAMESPACE.id("iron_ore_gravel"), new ItemStack(Item.IRON_INGOT, 1));
        MetalForgeRecipeRegistry.getInstance().addForgingRecipe(BlockListener.NAMESPACE.id("iron_chunk_block"), new ItemStack(Item.IRON_INGOT, 1));
        MetalForgeRecipeRegistry.getInstance().addForgingRecipe(BlockListener.NAMESPACE.id("gold_chunk_block"), new ItemStack(Item.GOLD_INGOT, 1));
        MetalForgeRecipeRegistry.getInstance().addForgingRecipe(BlockListener.NAMESPACE.id("lapis_gravel"), new ItemStack(ItemListener.crystallizedLapis, 36));
        MetalForgeRecipeRegistry.getInstance().addForgingRecipe(BlockListener.NAMESPACE.id("obsidian_glass_mixture"), new ItemStack(BlockListener.obsidianGlass, 1));
    }

    private static void addCauldronRecipes() {
        addCauldronRecipe(new ItemStack(ItemListener.lapisIngot, 1), new ItemStack[]{
                new ItemStack(ItemListener.rawLapisIngot, 1), new ItemStack(Item.FLINT), new ItemStack(net.kozibrodka.wolves.events.ItemListener.hempCloth)
        });
    }

    private static void addMillingRecipes() {
        MillingRecipeRegistry.getInstance().addMillingRecipe(BlockRegistry.INSTANCE.getId(Block.IRON_ORE), new ItemStack(BlockListener.ironOreGravel, 1));
        MillingRecipeRegistry.getInstance().addMillingRecipe(BlockRegistry.INSTANCE.getId(Block.GOLD_ORE), new ItemStack(BlockListener.goldOreGravel, 1));
        MillingRecipeRegistry.getInstance().addMillingRecipe(BlockRegistry.INSTANCE.getId(Block.LAPIS_BLOCK), new ItemStack(BlockListener.lapisGravel, 1));

        MillingRecipeRegistry.getInstance().addMillingRecipe(ItemListener.NAMESPACE.id("mystical_hemp"), new ItemStack(ItemListener.mysticalHempFibers, 4));
    }

    private static void addTurntableRecipes() {
        TurntableRecipeRegistry.getInstance().addTurntableRecipe(new TurntableRecipe(new TurntableInput(BlockListener.rotarySieve, 0), new TurntableResult(new TurntableOutput(BlockListener.rotarySieve, 8, 1), new TurntableByproduct(net.kozibrodka.wolves.events.ItemListener.nothing, 0))));
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
        addInfusionRecipe(new ItemStack(ItemListener.diamondChisel, 1), new Object[]{"Y#Y", " X ", '#', ItemListener.diamondIngot, 'X', net.kozibrodka.wolves.events.BlockListener.moulding, 'Y', net.kozibrodka.wolves.events.ItemListener.hempFibers});

        // Armour
        addInfusionRecipe(new ItemStack(Item.DIAMOND_HELMET, 1), new Object[]{"###", "# #", '#', ItemListener.diamondIngot});
        addInfusionRecipe(new ItemStack(Item.DIAMOND_CHESTPLATE, 1), new Object[]{"# #", "###", "###", '#', ItemListener.diamondIngot});
        addInfusionRecipe(new ItemStack(Item.DIAMOND_LEGGINGS, 1), new Object[]{"###", "# #", "# #", '#', ItemListener.diamondIngot});
        addInfusionRecipe(new ItemStack(Item.DIAMOND_BOOTS, 1), new Object[]{"# #", "# #", '#', ItemListener.diamondIngot});

        // Diamond age crafting components
        addInfusionRecipe(new ItemStack(ItemListener.diamondGear, 8), new Object[]{" X ", "XYX", " X ", 'X', ItemListener.diamondIngot, 'Y', ItemListener.obsidianPlate});
        addInfusionRecipe(new ItemStack(ItemListener.diamondGear, 4), new Object[]{" X ", "XYX", " X ", 'X', ItemListener.diamondIngot, 'Y', Block.OBSIDIAN});

        // Diamond age machines
        addInfusionRecipe(new ItemStack(BlockListener.obsidianGenerator, 1), new Object[]{"XYX", "YZY", "X#X", '#', ItemListener.diamondIngot, 'X', ItemListener.obsidianPlate, 'Y', Item.IRON_INGOT, 'Z', Item.BUCKET});
        addInfusionRecipe(new ItemStack(BlockListener.reinforcedSaw, 1), new Object[]{"YYY", "+Z+", "#X#", '#', Block.OBSIDIAN, 'X', Item.IRON_INGOT, 'Y', ItemListener.diamondIngot, 'Z', net.kozibrodka.wolves.events.BlockListener.saw, '+', ItemListener.stoneBrick});
        addInfusionRecipe(new ItemStack(BlockListener.reinforcedSaw, 1), new Object[]{"YYY", "+Z+", "#X#", '#', ItemListener.obsidianPlate, 'X', Item.IRON_INGOT, 'Y', ItemListener.diamondIngot, 'Z', net.kozibrodka.wolves.events.BlockListener.saw, '+', ItemListener.stoneBrick});
        addInfusionRecipe(new ItemStack(BlockListener.reinforcedMillStone, 1), new Object[]{"YYY", "#Z#", "#X#", 'X', ItemListener.diamondGear, 'Y', Block.OBSIDIAN, 'Z', net.kozibrodka.wolves.events.BlockListener.millStone, '#', ItemListener.stoneBrick});
        addInfusionRecipe(new ItemStack(BlockListener.reinforcedMillStone, 1), new Object[]{"YYY", "#Z#", "#X#", 'X', ItemListener.diamondGear, 'Y', ItemListener.obsidianPlate, 'Z', net.kozibrodka.wolves.events.BlockListener.millStone, '#', ItemListener.stoneBrick});
        addInfusionRecipe(new ItemStack(BlockListener.mysticalDevourer, 1), new Object[]{"STU", "VXW", "YZY", 'S', ItemListener.reinforcedGoldenShovel, 'T', ItemListener.reinforcedGoldenSword, 'U', ItemListener.reinforcedGoldenHoe, 'V', ItemListener.reinforcedGoldenAxe, 'W', ItemListener.reinforcedGoldenPickaxe, 'X', net.kozibrodka.wolves.events.BlockListener.blockDispenser, 'Y', Item.GOLD_INGOT, 'Z', ItemListener.diamondGear});
        addInfusionRecipe(new ItemStack(BlockListener.diamondDrill, 1), new Object[]{" Y ", "YXY", "#Z#", '#', ItemListener.obsidianPlate, 'X', Item.IRON_INGOT, 'Y', ItemListener.diamondIngot, 'Z', ItemListener.diamondGear});

        // Nether age machines and components
        addShapelessInfusionRecipe(new ItemStack(net.kozibrodka.wolves.events.ItemListener.soulFilter, 1), new Object[]{
                net.kozibrodka.wolves.events.ItemListener.hempCloth, net.kozibrodka.wolves.events.ItemListener.hempCloth, Block.SOUL_SAND, ItemListener.mysticalHempFibers
        });
        addInfusionRecipe(new ItemStack(net.kozibrodka.wolves.events.BlockListener.hibachi, 1), new Object[]{"X+X", "YZY", "Y#Y", 'X', net.kozibrodka.wolves.events.ItemListener.concentratedHellfire, 'Y', ItemListener.stoneBrick, 'Z', Item.GLOWSTONE_DUST, '#', Item.REDSTONE, '+', ItemListener.obsidianPlate});
    }

    private static void addReinforcedMillStoneRecipes() {
        ReinforcedMillStoneRecipeRegistry.getInstance().addMillingRecipe(BlockRegistry.INSTANCE.getId(Block.OBSIDIAN), new ItemStack(ItemListener.obsidianDust, 1));
        ReinforcedMillStoneRecipeRegistry.getInstance().addMillingRecipe(ItemListener.NAMESPACE.id("obsidian_chunk"), new ItemStack(ItemListener.obsidianDust, 1));
        ReinforcedMillStoneRecipeRegistry.getInstance().addMillingRecipe(BlockRegistry.INSTANCE.getId(Block.NETHERRACK), new ItemStack(net.kozibrodka.wolves.events.ItemListener.groundNetherrack, 1));
    }

    private static void addReinforcedSawRecipes() {
        ReinforcedSawRecipeRegistry.getInstance().addSawingRecipe(BlockRegistry.INSTANCE.getId(Block.OBSIDIAN), new ItemStack(ItemListener.obsidianPlate, 4));
        ReinforcedSawRecipeRegistry.getInstance().addSawingRecipe(BlockRegistry.INSTANCE.getId(Block.STONE), new ItemStack(ItemListener.stoneBrick, 4));
    }

    private static void addMysticalDevourerRecipes() {
        MysticalDevourerRecipeRegistry.getInstance().addDevouringRecipe(BlockRegistry.INSTANCE.getId(Block.COBBLESTONE), new ItemStack(BlockListener.mysticalCobblestone, 1));
        MysticalDevourerRecipeRegistry.getInstance().addDevouringRecipe(BlockRegistry.INSTANCE.getId(Block.GRAVEL), new ItemStack(BlockListener.mysticalGravel, 1));
        MysticalDevourerRecipeRegistry.getInstance().addDevouringRecipe(BlockRegistry.INSTANCE.getId(Block.PLANKS), new ItemStack(ItemListener.mysticalStick, 2));
        MysticalDevourerRecipeRegistry.getInstance().addDevouringRecipe(net.kozibrodka.wolves.events.BlockListener.NAMESPACE.id("hempCrop"), new ItemStack(ItemListener.mysticalHemp, 1));
        MysticalDevourerRecipeRegistry.getInstance().addDevouringRecipe(BlockListener.NAMESPACE.id("smooth_obsidian"), new ItemStack(BlockListener.engravedObsidian, 1));
    }

    private static void addDiamondDrillRecipes() {
        DiamondDrillRecipeRegistry.getInstance().addDrillingRecipe(BlockRegistry.INSTANCE.getId(Block.OBSIDIAN), new ItemStack(ItemListener.obsidianChunk, 4));
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

    private static void addRotarySieveRecipes() {
        RotarySieveRecipeRegistry.getInstance().addRotarySieveRecipe(BlockListener.ironOreGravel, new ItemStack(ItemListener.ironChunks, 5));
        RotarySieveRecipeRegistry.getInstance().addRotarySieveRecipe(BlockListener.goldOreGravel, new ItemStack(ItemListener.goldChunks, 5));
        RotarySieveRecipeRegistry.getInstance().addRotarySieveRecipe(BlockListener.mysticalGravel, new ItemStack(ItemListener.mysticalRock, 1));
    }

    public static TagKey<Item> getPlanksTagKey() {
        return TagKey.of(ItemRegistry.KEY, Identifier.of("c:planks"));
    }

    public static TagKey<Item> getStoneTagKey() {
        return TagKey.of(ItemRegistry.KEY, Identifier.of("c:stones"));
    }

    public static TagKey<Item> getCobblestoneTagKey() {
        return TagKey.of(ItemRegistry.KEY, Identifier.of("c:cobblestones"));
    }

    public static TagKey<Item> getGravelTagKey() {
        return TagKey.of(ItemRegistry.KEY, Identifier.of("c:gravels"));
    }

    public static TagKey<Item> getGlassTagKey() {
        return TagKey.of(ItemRegistry.KEY, Identifier.of("c:glass_blocks"));
    }

    public static TagKey<Item> getObsidianTagKey() {
        return TagKey.of(ItemRegistry.KEY, Identifier.of("c:glass_blocks"));
    }

    public static TagKey<Item> getStickTagKey() {
        return TagKey.of(ItemRegistry.KEY, Identifier.of("c:sticks"));
    }

    public static TagKey<Item> getSandTagKey() {
        return TagKey.of(ItemRegistry.KEY, Identifier.of("c:sands"));
    }

    public static TagKey<Item> getSlimeBallTagKey() {
        return TagKey.of(ItemRegistry.KEY, Identifier.of("c:slime_balls"));
    }

    public static TagKey<Item> getDustTagKey(String tag) {
        return TagKey.of(ItemRegistry.KEY, Identifier.of("c:dusts/" + tag));
    }

    public static TagKey<Item> getIngotTagKey(String tag) {
        return TagKey.of(ItemRegistry.KEY, Identifier.of("c:ingots/" + tag));
    }

    public static TagKey<Item> getGemTagKey(String tag) {
        return TagKey.of(ItemRegistry.KEY, Identifier.of("c:gems/" + tag));
    }

}
