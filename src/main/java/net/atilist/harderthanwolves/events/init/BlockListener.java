package net.atilist.harderthanwolves.events.init;

import net.atilist.harderthanwolves.block.*;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
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

        smoothObsidian = new LazyBlockTemplate(Identifier.of(NAMESPACE, "smooth_obsidian"), Material.STONE, 2.5F, Block.STONE_SOUND_GROUP);
        chiseledObsidian = new LazyBlockTemplate(Identifier.of(NAMESPACE, "chiseled_obsidian"), Material.STONE, 2.5F, Block.STONE_SOUND_GROUP);
    }
}
