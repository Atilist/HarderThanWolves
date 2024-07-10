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

    public static EmptyMonsterSiphon emptyMonsterSiphon;
    public static LoadedMonsterSiphon loadedMonsterSiphon;
    public static ChargedMonsterSiphon chargedMonsterSiphon;

    public static LazyBlockTemplate smoothObsidian;
    public static LazyBlockTemplate chiseledObsidian;

    @Entrypoint.Namespace
    public static final Namespace NAMESPACE = Null.get();

    @EventListener
    public void registerBlocks(BlockRegistryEvent event) {
        firePile = new FirePile(Identifier.of(NAMESPACE, "fire_pile"), Material.WOOD, 1.5F, Block.WOOD_SOUND_GROUP);
        litFirePile = new LitFirePile(Identifier.of(NAMESPACE, "lit_fire_pile"), Material.WOOD, 1.5F, Block.WOOD_SOUND_GROUP);
        depletedFirePile = new DepletedFirePile(Identifier.of(NAMESPACE, "depleted_fire_pile"), Material.WOOD, 1.5F, Block.WOOD_SOUND_GROUP);

        stoneBricks = new LazyBlockTemplate(Identifier.of(NAMESPACE, "stone_bricks"), Material.STONE, 1.5F, Block.STONE_SOUND_GROUP);
        metalForge = new MetalForgeBlock(Identifier.of(NAMESPACE, "metal_forge"), Material.STONE, 1.5F, Block.STONE_SOUND_GROUP);

        ironOreGravel = new LazyFallingBlockTemplate(Identifier.of(NAMESPACE, "iron_ore_gravel"), 1.5F, Block.GRAVEL_SOUND_GROUP);
        goldOreGravel = new LazyFallingBlockTemplate(Identifier.of(NAMESPACE, "gold_ore_gravel"), 1.5F, Block.GRAVEL_SOUND_GROUP);

        ironChunkBlock = new LazyFallingBlockTemplate(Identifier.of(NAMESPACE, "iron_chunk_block"), 1.5F, Block.GRAVEL_SOUND_GROUP);
        goldChunkBlock = new LazyFallingBlockTemplate(Identifier.of(NAMESPACE, "gold_chunk_block"), 1.5F, Block.GRAVEL_SOUND_GROUP);

        rotarySieve = new RotarySieveBlock(Identifier.of(NAMESPACE, "rotary_sieve"), Material.WOOD, 1.5F, Block.WOOD_SOUND_GROUP);

        mysticalCobblestone = new LazyBlockTemplate(Identifier.of(NAMESPACE, "mystical_cobblestone"), Material.STONE, 1.5F, Block.STONE_SOUND_GROUP);
        mysticalGravel = new LazyFallingBlockTemplate(Identifier.of(NAMESPACE, "mystical_gravel"), 1.5F, Block.GRAVEL_SOUND_GROUP);

        emptyMonsterSiphon = new EmptyMonsterSiphon(Identifier.of(NAMESPACE, "empty_monster_siphon"), Material.STONE, 1.5F, Block.STONE_SOUND_GROUP);
        loadedMonsterSiphon = new LoadedMonsterSiphon(Identifier.of(NAMESPACE, "loaded_monster_siphon"), Material.STONE, 1.5F, Block.STONE_SOUND_GROUP);
        chargedMonsterSiphon = new ChargedMonsterSiphon(Identifier.of(NAMESPACE, "charged_monster_siphon"), Material.STONE, 1.5F, Block.STONE_SOUND_GROUP);

        smoothObsidian = new LazyBlockTemplate(Identifier.of(NAMESPACE, "smooth_obsidian"), Material.STONE, 2.5F, Block.STONE_SOUND_GROUP);
        chiseledObsidian = new LazyBlockTemplate(Identifier.of(NAMESPACE, "chiseled_obsidian"), Material.STONE, 2.5F, Block.STONE_SOUND_GROUP);
    }
}
