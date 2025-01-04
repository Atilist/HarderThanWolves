package net.atilist.harderthanwolves.events.init;

import net.atilist.harderthanwolves.block.*;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.modificationstation.stationapi.api.event.registry.BlockRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;

public class BlockListener {
    public static LazyBlockTemplate firePile;
    public static LazyBlockTemplate litFirePile;
    public static LazyBlockTemplate depletedFirePile;

    public static LazyBlockTemplate stoneBricks;
    public static MetalForgeBlock metalForge;

    public static LazyFallingBlockTemplate ironOreGravel;
    public static LazyFallingBlockTemplate goldOreGravel;

    public static LazyFallingBlockTemplate ironChunkBlock;
    public static LazyFallingBlockTemplate goldChunkBlock;

    public static RotarySieveBlock rotarySieve;

    public static LazyBlockTemplate mysticalCobblestone;
    public static LazyFallingBlockTemplate mysticalGravel;

    public static LazyFallingBlockTemplate lapisGravel;

    public static EmptyMonsterSiphonBlock emptyMonsterSiphon;
    public static LoadedMonsterSiphonBlock loadedMonsterSiphon;
    public static ChargedMonsterSiphonBlock chargedMonsterSiphon;

    public static MonsterSiphonExpanderBlock monsterSiphonExpander;
    public static LazyBlockTemplate activeMonsterSiphonExpander;

    public static LazyBlockTemplate mysticalInfuserBase;
    public static MysticalInfuserBlock mysticalInfuser;

    public static LazyBlockTemplate smoothObsidian;
    public static LazyBlockTemplate chiseledObsidian;

    public static LazyBlockTemplate engravedObsidian;
    public static LazyBlockTemplate activeEngravedObsidian;
    public static LazyFallingBlockTemplate obsidianGlassMixture;
    public static ObsidianGlassBlock obsidianGlass;

    public static ReinforcedMillStoneBlock reinforcedMillStone;
    public static ReinforcedSawBlock reinforcedSaw;
    public static MysticalDevourerBlock mysticalDevourer;
    public static DiamondDrillBlock diamondDrill;

    @Entrypoint.Namespace
    public static final Namespace NAMESPACE = Null.get();

    @EventListener
    public void registerBlocks(BlockRegistryEvent event) {
        firePile = new FirePileBlock(Identifier.of(NAMESPACE, "fire_pile"), Material.WOOD, 1.5F, Block.WOOD_SOUND_GROUP);
        litFirePile = new LitFirePileBlock(Identifier.of(NAMESPACE, "lit_fire_pile"), Material.WOOD, 1.5F, Block.WOOD_SOUND_GROUP);
        depletedFirePile = new DepletedFirePileBlock(Identifier.of(NAMESPACE, "depleted_fire_pile"), Material.WOOD, 1.5F, Block.WOOD_SOUND_GROUP);

        stoneBricks = new LazyBlockTemplate(Identifier.of(NAMESPACE, "stone_bricks"), Material.STONE, 1.5F, Block.STONE_SOUND_GROUP);
        metalForge = new MetalForgeBlock(Identifier.of(NAMESPACE, "metal_forge"), Material.STONE, 1.5F, Block.STONE_SOUND_GROUP);

        ironOreGravel = new LazyFallingBlockTemplate(Identifier.of(NAMESPACE, "iron_ore_gravel"), 1.5F, Block.GRAVEL_SOUND_GROUP);
        goldOreGravel = new LazyFallingBlockTemplate(Identifier.of(NAMESPACE, "gold_ore_gravel"), 1.5F, Block.GRAVEL_SOUND_GROUP);

        ironChunkBlock = new LazyFallingBlockTemplate(Identifier.of(NAMESPACE, "iron_chunk_block"), 1.5F, Block.GRAVEL_SOUND_GROUP);
        goldChunkBlock = new LazyFallingBlockTemplate(Identifier.of(NAMESPACE, "gold_chunk_block"), 1.5F, Block.GRAVEL_SOUND_GROUP);

        rotarySieve = new RotarySieveBlock(Identifier.of(NAMESPACE, "rotary_sieve"), Material.WOOD, 1.5F, Block.WOOD_SOUND_GROUP);

        mysticalCobblestone = new LazyBlockTemplate(Identifier.of(NAMESPACE, "mystical_cobblestone"), Material.STONE, 1.5F, Block.STONE_SOUND_GROUP);
        mysticalGravel = new LazyFallingBlockTemplate(Identifier.of(NAMESPACE, "mystical_gravel"), 1.5F, Block.GRAVEL_SOUND_GROUP);

        lapisGravel = new LazyFallingBlockTemplate(Identifier.of(NAMESPACE, "lapis_gravel"), 1.5F, Block.GRAVEL_SOUND_GROUP);

        emptyMonsterSiphon = new EmptyMonsterSiphonBlock(Identifier.of(NAMESPACE, "empty_monster_siphon"), Material.STONE, 1.5F, Block.STONE_SOUND_GROUP);
        loadedMonsterSiphon = new LoadedMonsterSiphonBlock(Identifier.of(NAMESPACE, "loaded_monster_siphon"), Material.STONE, 1.5F, Block.STONE_SOUND_GROUP);
        chargedMonsterSiphon = new ChargedMonsterSiphonBlock(Identifier.of(NAMESPACE, "charged_monster_siphon"), Material.STONE, 1.5F, Block.STONE_SOUND_GROUP);

        monsterSiphonExpander = new MonsterSiphonExpanderBlock(Identifier.of(NAMESPACE, "monster_siphon_expander"), Material.STONE, 1.5F, Block.STONE_SOUND_GROUP);
        activeMonsterSiphonExpander = new LazyBlockTemplate(Identifier.of(NAMESPACE, "active_monster_siphon_expander"), Material.STONE, 1.5F, Block.STONE_SOUND_GROUP);

        mysticalInfuserBase = new LazyBlockTemplate(Identifier.of(NAMESPACE, "mystical_infuser_base"), Material.STONE, 1.5F, Block.STONE_SOUND_GROUP);
        mysticalInfuser = new MysticalInfuserBlock(Identifier.of(NAMESPACE, "mystical_infuser"), Material.STONE, 1.5F, Block.STONE_SOUND_GROUP);

        smoothObsidian = new LazyBlockTemplate(Identifier.of(NAMESPACE, "smooth_obsidian"), Material.STONE, 2.5F, Block.STONE_SOUND_GROUP);
        chiseledObsidian = new LazyBlockTemplate(Identifier.of(NAMESPACE, "chiseled_obsidian"), Material.STONE, 2.5F, Block.STONE_SOUND_GROUP);

        engravedObsidian = new LazyBlockTemplate(Identifier.of(NAMESPACE, "engraved_obsidian"), Material.STONE, 2.5F, Block.STONE_SOUND_GROUP);
        activeEngravedObsidian = new LazyBlockTemplate(Identifier.of(NAMESPACE, "active_engraved_obsidian"), Material.STONE, 2.5F, Block.STONE_SOUND_GROUP);
        obsidianGlassMixture = new LazyFallingBlockTemplate(Identifier.of(NAMESPACE, "obsidian_glass_mixture"), 2.5F, Block.SAND_SOUND_GROUP);
        obsidianGlass = new ObsidianGlassBlock(Identifier.of(NAMESPACE, "obsidian_glass"), Material.GLASS, 1.5F, Block.GLASS_SOUND_GROUP);

        reinforcedMillStone = new ReinforcedMillStoneBlock(Identifier.of(NAMESPACE, "reinforced_mill_stone"), Material.STONE, 4.5F, Block.STONE_SOUND_GROUP);
        reinforcedSaw = new ReinforcedSawBlock(Identifier.of(NAMESPACE, "reinforced_saw"), Material.STONE, 4.5F, Block.STONE_SOUND_GROUP);

        diamondDrill = new DiamondDrillBlock(Identifier.of(NAMESPACE, "diamond_drill"), Material.METAL, 4.5F, Block.STONE_SOUND_GROUP);
        mysticalDevourer = new MysticalDevourerBlock(Identifier.of(NAMESPACE, "mystical_devourer"), Material.STONE, 1.5F, Block.STONE_SOUND_GROUP);
    }
}
