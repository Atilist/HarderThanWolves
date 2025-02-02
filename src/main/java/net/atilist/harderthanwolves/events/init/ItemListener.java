package net.atilist.harderthanwolves.events.init;

import net.atilist.harderthanwolves.item.DiamondChiselItem;
import net.atilist.harderthanwolves.item.IronChiselItem;
import net.atilist.harderthanwolves.item.*;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.item.ToolMaterial;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;

public class ItemListener {

    public static StoneChiselItem stoneChisel;
    public static IronChiselItem ironChisel;
    public static DiamondChiselItem diamondChisel;

    public static LazyItemTemplate stoneBrick;

    public static LazyItemTemplate ironChunks;
    public static LazyItemTemplate goldChunks;

    public static LazyItemTemplate mysticalStick;
    public static LazyItemTemplate mysticalHemp;
    public static LazyItemTemplate mysticalHempFibers;
    public static LazyItemTemplate monsterCloth;
    public static LazyItemTemplate mysticalRock;
    public static LazyItemTemplate chargedMysticalRock;

    public static LazyItemTemplate crystallizedLapis;
    public static LazyItemTemplate rawLapisIngot;
    public static LazyItemTemplate lapisIngot;

    public static LazyItemTemplate rawDiamondIngot;
    public static LazyItemTemplate diamondIngot;

    public static LazySwordItem reinforcedGoldenSword;
    public static LazyShovelItem reinforcedGoldenShovel;
    public static LazyPickaxeItem reinforcedGoldenPickaxe;
    public static LazyAxeItem reinforcedGoldenAxe;
    public static LazyHoeItem reinforcedGoldenHoe;

    public static LazyItemTemplate diamondGear;

    public static LazyItemTemplate obsidianDust;
    public static LazyItemTemplate obsidianChunk;
    public static LazyItemTemplate obsidianPlate;

    @Entrypoint.Namespace
    public static final Namespace NAMESPACE = Null.get();

    @EventListener
    public void registerItems(ItemRegistryEvent event) {
        stoneChisel = new StoneChiselItem(Identifier.of(NAMESPACE, "stone_chisel"));
        ironChisel = new IronChiselItem(Identifier.of(NAMESPACE, "iron_chisel"));
        diamondChisel = new DiamondChiselItem(Identifier.of(NAMESPACE, "diamond_chisel"));

        stoneBrick = new LazyItemTemplate(Identifier.of(NAMESPACE, "stone_brick"));

        ironChunks = new LazyItemTemplate(Identifier.of(NAMESPACE, "iron_chunks"));
        goldChunks = new LazyItemTemplate(Identifier.of(NAMESPACE, "gold_chunks"));

        mysticalStick = new LazyItemTemplate(Identifier.of(NAMESPACE, "mystical_stick"));
        mysticalHemp = new LazyItemTemplate(Identifier.of(NAMESPACE, "mystical_hemp"));
        mysticalHempFibers = new LazyItemTemplate(Identifier.of(NAMESPACE, "mystical_hemp_fibers"));
        monsterCloth = new LazyItemTemplate(Identifier.of(NAMESPACE, "monster_cloth"));
        mysticalRock = new LazyItemTemplate(Identifier.of(NAMESPACE, "mystical_rock"));
        chargedMysticalRock = new LazyItemTemplate(Identifier.of(NAMESPACE, "charged_mystical_rock"));

        crystallizedLapis = new LazyItemTemplate(Identifier.of(NAMESPACE, "crystallized_lapis"));
        rawLapisIngot = new LazyItemTemplate(Identifier.of(NAMESPACE, "raw_lapis_ingot"));
        lapisIngot = new LazyItemTemplate(Identifier.of(NAMESPACE, "lapis_ingot"));

        rawDiamondIngot = new LazyItemTemplate(Identifier.of(NAMESPACE, "raw_diamond_ingot"));
        diamondIngot = new LazyItemTemplate(Identifier.of(NAMESPACE, "diamond_ingot"));

        reinforcedGoldenSword = new LazySwordItem(Identifier.of(NAMESPACE, "reinforced_golden_sword"), ToolMaterial.IRON);
        reinforcedGoldenShovel = new LazyShovelItem(Identifier.of(NAMESPACE, "reinforced_golden_shovel"), ToolMaterial.IRON);
        reinforcedGoldenPickaxe = new LazyPickaxeItem(Identifier.of(NAMESPACE, "reinforced_golden_pickaxe"), ToolMaterial.IRON);
        reinforcedGoldenAxe = new LazyAxeItem(Identifier.of(NAMESPACE, "reinforced_golden_axe"), ToolMaterial.IRON);
        reinforcedGoldenHoe = new LazyHoeItem(Identifier.of(NAMESPACE, "reinforced_golden_hoe"), ToolMaterial.IRON);

        diamondGear = new LazyItemTemplate(Identifier.of(NAMESPACE, "diamond_gear"));

        obsidianDust = new LazyItemTemplate(Identifier.of(NAMESPACE, "obsidian_dust"));
        obsidianChunk = new LazyItemTemplate(Identifier.of(NAMESPACE, "obsidian_chunk"));
        obsidianPlate = new LazyItemTemplate(Identifier.of(NAMESPACE, "obsidian_plate"));
    }
}
